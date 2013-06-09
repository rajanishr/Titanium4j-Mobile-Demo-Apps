package com.chazaqdev.ti4jexample.client.place;

import com.chazaqdev.ti4jexample.client.framework.HistoryHandler;
import com.chazaqdev.ti4jexample.client.framework.HistoryObserver;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler.DefaultHistorian;
import com.google.gwt.place.shared.PlaceHistoryHandler.Historian;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class PlaceHistoryHandler {

	protected class DefaultHistoryHandler implements HistoryHandler {
		@Override
		public void replaceCurrentPlace(Place place) {
			String tokenForPlace = tokenForPlace(place);
			replaceToken(tokenForPlace);

		}

		@Override
		public void pushPlace(Place place) {
			String tokenForPlace = tokenForPlace(place);
			pushToken(tokenForPlace);

		}

		@Override
		public void goTo(Place place) {
			placeController.goTo(place);

		}

		@Override
		public void goTo(Place place, boolean ignore) {
			PlaceHistoryHandler.this.ignore = ignore;
			placeController.goTo(place);

		}
	}
	
	private boolean ignore = false;

	private PlaceController placeController;
	
	private final HistoryObserver historyObserver;
	
	private DefaultHistoryHandler defaultHistoryHandler;

	private static final Historian GWT_historian = (Historian) GWT.create(DefaultHistorian.class);
	
	private static final HTMLHistorian historian = new HTMLHistorian();
	
	private Place defaultPlace = Place.NOWHERE;
	
	private PlaceHistoryMapper placeHistoryMapper;

	private EventBus eventBus;
	
	public PlaceHistoryHandler(PlaceHistoryMapper placeHistoryMapper, HistoryObserver historyObserver, PlaceController placeController) {
		this.historyObserver = historyObserver;
		this.placeController = placeController;
		defaultHistoryHandler = new DefaultHistoryHandler();
		this.placeHistoryMapper = placeHistoryMapper;
	}

	public HandlerRegistration register(PlaceController placeController, EventBus eventBus, Place defaultPlace) {
		this.placeController = placeController;
		this.eventBus = eventBus;
		this.defaultPlace = defaultPlace;
		
		historian.eventBus = eventBus;

		final HandlerRegistration bind = bind();

		final HandlerRegistration handlerRegistration = historyObserver.bind(eventBus, defaultHistoryHandler);

		return new HandlerRegistration() {
			public void removeHandler() {

				bind.removeHandler();
				handlerRegistration.removeHandler();
			}
		};
	}

	protected HandlerRegistration bind() {

		final HandlerRegistration popHandler = historian.addPopStateHandler(new PopStateHandler() {

			@Override
			public void onPopStateEvent(PopStateEvent event) {
				onPopStateEventOccured(event.getData());
			}
		});

		final HandlerRegistration placeChangeHandler = eventBus.addHandler(PlaceChangeEvent.TYPE, new PlaceChangeEvent.Handler() {

			@Override
			public void onPlaceChange(PlaceChangeEvent event) {
				onPlaceChangeEvent(event);
			}
		});

		return new HandlerRegistration() {
			public void removeHandler() {
				PlaceHistoryHandler.this.defaultPlace = Place.NOWHERE;
				PlaceHistoryHandler.this.placeController = null;
				popHandler.removeHandler();
				placeChangeHandler.removeHandler();
			}
		};

	}

	protected void onPlaceChangeEvent(PlaceChangeEvent event) {

		if (ignore) {
			ignore = false;
			return;
		}

		Place newPlace = event.getNewPlace();

		historyObserver.onPlaceChange(newPlace, defaultHistoryHandler);

		pushToken(tokenForPlace(newPlace));
	}

	protected void onPopStateEventOccured(String token) {

		Place place = getPlaceForToken(token);

		historyObserver.onHistoryChanged(place, defaultHistoryHandler);
		// TODO maybe handle differently?
		ignore = true;
		placeController.goTo(place);
	}

	protected void replaceToken(String token) {
		if (token.length() > 0) {
//			historian.replaceState(token, Window.getTitle(), "#" + token);
			historian.replaceState(token, "", "#" + token);
		} else {
			historian.replaceState(token, "", "");
//			historian.replaceState(token, Window.getTitle(), "");
		}
	}

	protected void pushToken(String token) {
//		historian.pushState(token, Window.getTitle(), "#" + token);
		historian.pushState(token, "", "#" + token);
	}

	public void handleCurrentHistory() {
		Place place = getPlaceForToken(GWT_historian.getToken());
		historyObserver.onAppStarted(place, defaultHistoryHandler);
		if (defaultPlace.equals(place)) {
			ignore = true;
		}
		placeController.goTo(place);
	}


	protected Place getPlaceForToken(String token) {

		Place newPlace = null;

		if ("".equals(token)) {
			newPlace = defaultPlace;
		}

		if (newPlace == null) {
			newPlace = placeHistoryMapper.getPlace(token);
		}

		if (newPlace == null) {
			newPlace = defaultPlace;
		}
		return newPlace;

	}

	private String tokenForPlace(Place newPlace) {

		if (defaultPlace.equals(newPlace)) {
			return "";
		}

		String token = placeHistoryMapper.getToken(newPlace);
		if (token != null) {
			return token;
		}
		return "";
	}
	

	public static class HTMLHistorian {
		
		public EventBus eventBus;
		
		public HandlerRegistration addPopStateHandler(PopStateHandler handler) {
			return eventBus.addHandler(PopStateEvent.getType(), handler);
		}
		
		public void back() {
			
		}
		
		public void forward() {
			
		}
		
		public void go(int number) {
			
		}

		public int length() {
			return 0;
		}
		
		public void pushState(String data, String title, String url) {
			
		}
		
		public void replaceState(String data, String title, String url) {
			
		}
	}
}
