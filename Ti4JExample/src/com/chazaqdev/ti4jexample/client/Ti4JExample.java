package com.chazaqdev.ti4jexample.client;

import com.chazaqdev.ti4jexample.client.activity.MainActivityMapper;
import com.chazaqdev.ti4jexample.client.framework.AppHistoryObserver;
import com.chazaqdev.ti4jexample.client.place.AppPlaceHistoryMapper;
import com.chazaqdev.ti4jexample.client.place.PlaceHistoryHandler;
import com.chazaqdev.ti4jexample.client.place.home.HomePlace;
import com.chazaqdev.ti4jexample.client.utils.ClientFactory;
import com.chazaqdev.ti4jexample.client.utils.ClientFactoryImpl;
import com.chazaqdev.ti4jexample.client.utils.Utils;
import com.chazaqdev.ti4jexample.client.view.AcceptsOneWidgetView;
import com.emitrom.ti4j.mobile.client.core.TiEntryPoint;
import com.emitrom.ti4j.mobile.client.core.events.TiEvent;
import com.emitrom.ti4j.mobile.client.core.events.ui.UIEvent;
import com.emitrom.ti4j.mobile.client.core.handlers.EventHandler;
import com.emitrom.ti4j.mobile.client.ui.UI;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Ti4JExample extends TiEntryPoint  {
	
	@Override
	public void onStart() {
		final ClientFactory clientFactory = new ClientFactoryImpl();
		Utils.setClientFactory(clientFactory);
		
		AppHistoryObserver historyObserver = new AppHistoryObserver(clientFactory);
		AppPlaceHistoryMapper appPlaceHistoryMapper = GWT.create(AppPlaceHistoryMapper.class);
		

		/* Main window views*/
		MainActivityMapper mainActivityMapper = new MainActivityMapper(clientFactory);
		ActivityManager activityManager = new ActivityManager(mainActivityMapper, clientFactory.getEventBus());
		AcceptsOneWidgetView mainView = new AcceptsOneWidgetView();
		activityManager.setDisplay(mainView);
		


		PlaceHistoryHandler placeHistoryHandler = new PlaceHistoryHandler(appPlaceHistoryMapper, historyObserver, clientFactory.getPlaceController());
		Place p = new HomePlace();
		placeHistoryHandler.register(clientFactory.getPlaceController(), clientFactory.getEventBus(), p);
		clientFactory.setAppHistoryObserver(historyObserver);
		placeHistoryHandler.handleCurrentHistory();
		
		UI.get().addEventHandler(UIEvent.ANDROID_BACK, new EventHandler() {
			
			@Override
			public void onEvent(TiEvent event) {
				clientFactory.getAppHistoryObserver().goBack();
			}
		});
	}
}
