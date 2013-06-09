package com.chazaqdev.ti4jexample.client.activity.youtube;

import java.util.ArrayList;
import java.util.List;

import com.chazaqdev.ti4jexample.client.activity.AbstractActivity;
import com.chazaqdev.ti4jexample.client.data.YoutubeResultItem;
import com.chazaqdev.ti4jexample.client.data.YoutubeSearchResult;
import com.chazaqdev.ti4jexample.client.place.home.HomePlace;
import com.chazaqdev.ti4jexample.client.utils.ClientFactory;
import com.chazaqdev.ti4jexample.client.view.youtube.YoutubeView;
import com.chazaqdev.ti4jexample.client.widgets.YoutubeResult;
import com.emitrom.ti4j.mobile.client.Titanium;
import com.emitrom.ti4j.mobile.client.api.API;
import com.emitrom.ti4j.mobile.client.core.events.ui.ClickEvent;
import com.emitrom.ti4j.mobile.client.core.handlers.ui.ClickHandler;
import com.emitrom.ti4j.mobile.client.ui.Window;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.jsonp.client.JsonpRequestBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class YoutubeActivity extends AbstractActivity{
	
	public YoutubeActivity(ClientFactory clientFactory) {
		super(clientFactory);
	}
	
	static int prepId = 0;
	
	private YoutubeView youtubeView;
	private Window youtubeWindow;
	
	private void init() {
		youtubeView = clientFactory.getYoutubeView();
		youtubeWindow = new Window();
		youtubeWindow.setNavBarHidden(true);
		youtubeWindow.add(youtubeView);
		
		youtubeView.setBackClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				clientFactory.getPlaceController().goTo(new HomePlace());
			}
		});
		
		youtubeView.setSearchClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				++prepId;
				if (youtubeView.getSearchString() != null && !youtubeView.getSearchString().equals("")) {
					String s = youtubeView.getSearchString();
					JsonpRequestBuilder jsonp = new JsonpRequestBuilder();
					UrlBuilder url = new UrlBuilder();
					url.setHost("gdata.youtube.com");
					url.setPort(80);
					url.setPath("/feeds/videos");
					url.setProtocol("http");
					url.setParameter("vq", s);
					url.setParameter("max-results", "12");
					url.setParameter("alt", "json");
					url.setParameter("prettyprint", "true");

					try {
						jsonp.requestObject(url.buildString(), new AsyncCallback<YoutubeSearchResult>() {
	
							@Override
							public void onFailure(Throwable caught) {
								Titanium.alert("Error in doing search");
							}
	
							@Override
							public void onSuccess(YoutubeSearchResult jso) {
								API.get().log("INFO", "Called success");
								
									List<YoutubeResult> results = new ArrayList<YoutubeResult>();
									int j = 0;
									for (int i = 0; i < jso.getEntries().length(); ++i) {
										try {
											YoutubeResultItem it = jso.getEntries().get(i);
											API.get().log("INFO", "" + i + ":" + it.getId());
											YoutubeResult r = new YoutubeResult();
											
											r.setTop("" + j * 62 + "dp");
											r.readInItem(it);
											results.add(r);
											++j;
										} catch (Exception e) {
										}	
									}
									youtubeView.setYoutubeResults(results);
								
							}
						}); 
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}
		});
	}
	
	@Override
	public void onStart() {
		if (youtubeView == null) {
			init();
		}
		clientFactory.closePreviousWindows(youtubeWindow);
		youtubeWindow.open();
		clientFactory.registerWindowForClosure(youtubeWindow);
		
	}

}
