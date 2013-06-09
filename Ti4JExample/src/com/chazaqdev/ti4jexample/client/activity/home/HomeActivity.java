package com.chazaqdev.ti4jexample.client.activity.home;

import com.chazaqdev.ti4jexample.client.activity.AbstractActivity;
import com.chazaqdev.ti4jexample.client.data.X;
import com.chazaqdev.ti4jexample.client.place.x.XEditPlace;
import com.chazaqdev.ti4jexample.client.place.youtube.YoutubePlace;
import com.chazaqdev.ti4jexample.client.utils.ClientFactory;
import com.chazaqdev.ti4jexample.client.view.home.HomeView;
import com.emitrom.ti4j.mobile.client.core.events.ui.ClickEvent;
import com.emitrom.ti4j.mobile.client.core.handlers.ui.ClickHandler;
import com.emitrom.ti4j.mobile.client.ui.Window;

public class HomeActivity extends AbstractActivity {
	
	public HomeActivity(ClientFactory clientFactory) {
		super(clientFactory);
	}

	private HomeView homeView;
	private Window homeWindow;
	
	private void init() {
		homeView = clientFactory.getHomeView();
		homeWindow = new Window();
		homeWindow.setNavBarHidden(false);
		homeWindow.add(homeView);
		
		homeView.setYoutubeClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				clientFactory.getPlaceController().goTo(new YoutubePlace());
			}
		});
		
		homeView.setEdit1ClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				clientFactory.getPlaceController().goTo(new XEditPlace(X.xItems[0].getId()));
			}
		});
		
		homeView.setEdit2ClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				clientFactory.getPlaceController().goTo(new XEditPlace(X.xItems[1].getId()));
			}
		});
	}
	
	@Override
	public void onStart() {
		if (homeView == null) {
			init();
		}
		clientFactory.closePreviousWindows(homeWindow);
		homeWindow.open();
		clientFactory.registerWindowForClosure(homeWindow);
	}
	
}
