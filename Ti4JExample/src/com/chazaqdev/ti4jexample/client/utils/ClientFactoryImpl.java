package com.chazaqdev.ti4jexample.client.utils;

import java.util.ArrayList;
import java.util.List;

import com.chazaqdev.ti4jexample.client.framework.AppHistoryObserver;
import com.chazaqdev.ti4jexample.client.view.HasOpenAndCloseAnimation;
import com.chazaqdev.ti4jexample.client.view.home.HomeView;
import com.chazaqdev.ti4jexample.client.view.x.XEditView;
import com.chazaqdev.ti4jexample.client.view.youtube.YoutubeView;
import com.chazaqdev.ti4jexample.client.viewimpl.home.HomeViewImpl;
import com.chazaqdev.ti4jexample.client.viewimpl.x.XEditViewImpl;
import com.chazaqdev.ti4jexample.client.viewimpl.youtube.YoutubeViewImpl;
import com.emitrom.ti4j.mobile.client.ui.View;
import com.emitrom.ti4j.mobile.client.ui.Window;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

public class ClientFactoryImpl implements ClientFactory {
	private PlaceController placeController;
	private EventBus eventBus = new SimpleEventBus();
	private AppHistoryObserver appHistoryObserver;
	
	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}
	
	@Override
	public EventBus getEventBus() {
		return eventBus;
	}
	
	private List<Window> windowList = new ArrayList<Window>();
	private List<View> viewList = new ArrayList<View>();
	
	@Override
	public void closePreviousWindows() {
		closePreviousWindows(null);
	}
	
	@Override
	public void closePreviousWindows(Window w) {
		boolean foundPreviously = false;
		for (Window window : windowList) {
			if (window != w) {
				if (window instanceof HasOpenAndCloseAnimation) {
					window.close(((HasOpenAndCloseAnimation)window).getCloseAnimation());
				} else {
					window.close();
				}
			} else {
				foundPreviously = true;
			}
		}
		windowList.clear();
		
		for (View v : viewList) {
			v.clear();
		}
		viewList.clear();
		if (foundPreviously) {
			windowList.add(w);
		}
	}

	public ClientFactoryImpl() {
		placeController = new PlaceController(eventBus);
	}
	
	@Override
	public void registerViewForClearing(View v) {
		viewList.add(v);
	}
	
	@Override
	public void registerWindowForClosure(Window window) {
		if (window == null) {
			return;
		}
		if (!windowList.contains(window)) {
			windowList.add(window);
		}
	}
	
	@Override
	public Window getLatestWindow() {
		return (windowList.size() > 0 ? windowList.get(windowList.size()-1) : null);
	}
	
	@Override
	public AppHistoryObserver getAppHistoryObserver() {
		return appHistoryObserver;
	}
	
	@Override
	public void setAppHistoryObserver(AppHistoryObserver appHistoryObserver) {
		this.appHistoryObserver = appHistoryObserver;
	}
	
	/* Home */
	private HomeView homeView;
	@Override
	public HomeView getHomeView() {
		return (homeView = (homeView == null ? new HomeViewImpl() : homeView));
	}
	
	/* Youtube */
	private YoutubeView youtubeView;
	@Override
	public YoutubeView getYoutubeView() {
		return (youtubeView = (youtubeView == null ? new YoutubeViewImpl() : youtubeView));
	}
	
	/* X */
	private XEditView xEditView;
	public XEditView getXEditView() {
		return (xEditView = (xEditView == null ? new XEditViewImpl() : xEditView));
	}
}
