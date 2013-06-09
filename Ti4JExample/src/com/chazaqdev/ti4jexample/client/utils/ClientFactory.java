package com.chazaqdev.ti4jexample.client.utils;

import com.chazaqdev.ti4jexample.client.framework.AppHistoryObserver;
import com.chazaqdev.ti4jexample.client.view.home.HomeView;
import com.chazaqdev.ti4jexample.client.view.x.XEditView;
import com.chazaqdev.ti4jexample.client.view.youtube.YoutubeView;
import com.emitrom.ti4j.mobile.client.ui.View;
import com.emitrom.ti4j.mobile.client.ui.Window;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

public interface ClientFactory {

	public PlaceController getPlaceController();
	public EventBus getEventBus();
	
	public AppHistoryObserver getAppHistoryObserver();
	public void setAppHistoryObserver(AppHistoryObserver appHistoryObserver);
	
	public void registerWindowForClosure(Window window);
	public void registerViewForClearing(View v);
	public void closePreviousWindows();
	public void closePreviousWindows(Window w);
	public Window getLatestWindow();
	
	/* Home */
	public HomeView getHomeView();
	
	/* Youtube */
	public YoutubeView getYoutubeView();
	
	/* X */
	public XEditView getXEditView();
}
