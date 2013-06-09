package com.chazaqdev.ti4jexample.client.framework;

import java.util.ArrayList;
import java.util.List;

import com.chazaqdev.ti4jexample.client.utils.ClientFactory;
import com.google.gwt.place.shared.Place;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class AppHistoryObserver  implements HistoryObserver {
	private List<Place> historyPlaces = new ArrayList<Place>();
	private Place previousPlace = Place.NOWHERE;
	private ClientFactory clientFactory;
	
	@Override
	public void onPlaceChange(Place place, HistoryHandler handler) {
		if (!place.equals(previousPlace)) {
			historyPlaces.add(place);
			previousPlace = place;
		}
	}
	
	public AppHistoryObserver(ClientFactory clientFactory) {
		super();
		this.clientFactory = clientFactory;
		historyPlaces.add(previousPlace);
		if (historyPlaces.size() == 11) {
			historyPlaces.remove(0);
		}
	}
	
	public void goBack() {
		historyPlaces.remove(historyPlaces.size()-1);
		if (historyPlaces.size() == 1 && historyPlaces.get(0).equals(Place.NOWHERE)) {
			//Close the app? How?
			return;
	 	} else if (historyPlaces.size() == 1){
	 		//Ensure that we do have a nowhere to go to in order for the app exit code (above) to run
	 		historyPlaces.add(0, Place.NOWHERE);
	 	}
		//Go to the last place in the stack
		clientFactory.getPlaceController().goTo(historyPlaces.get(historyPlaces.size()-1));
	}
	

	@Override
	public void onHistoryChanged(Place place, HistoryHandler handler) {
	}

	@Override
	public void onAppStarted(Place place, HistoryHandler historyHandler) {
			onNav(place, historyHandler);
	}

	@Override
	public HandlerRegistration bind(EventBus eventBus, final HistoryHandler historyHandler) {


		HandlerRegistration register2 = ActionEvent.register(eventBus, ActionNames.BACK, new ActionEvent.Handler() {

			@Override
			public void onAction(ActionEvent event) {
				goBack();
			}
		});

		HandlerRegistrationCollection col = new HandlerRegistrationCollection();
		col.addHandlerRegistration(register2);
		return col;
	}

	private void onNav(Place place, HistoryHandler historyHandler) {
//		historyHandler.replaceCurrentPlace(place);
	}
}
