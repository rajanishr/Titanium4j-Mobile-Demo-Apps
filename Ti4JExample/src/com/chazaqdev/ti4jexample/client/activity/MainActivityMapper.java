package com.chazaqdev.ti4jexample.client.activity;

import com.chazaqdev.ti4jexample.client.activity.home.HomeActivity;
import com.chazaqdev.ti4jexample.client.activity.x.XEditActivity;
import com.chazaqdev.ti4jexample.client.activity.youtube.YoutubeActivity;
import com.chazaqdev.ti4jexample.client.place.home.HomePlace;
import com.chazaqdev.ti4jexample.client.place.x.XEditPlace;
import com.chazaqdev.ti4jexample.client.place.youtube.YoutubePlace;
import com.chazaqdev.ti4jexample.client.utils.ClientFactory;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class MainActivityMapper implements ActivityMapper {
	private final ClientFactory clientFactory;

	private Place lastPlace;

	public MainActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}
	
	@Override
	public ActivityTi getActivity(Place place) {
		ActivityTi activity = getActivity(lastPlace, place);
		lastPlace = place;
		return activity;
	}

	private ActivityTi getActivity(Place oldPlace, Place newPlace) {
		/* Home*/
		if (newPlace instanceof HomePlace) {
			return getHomeActivity();
		}
		/* Youtube*/
		if (newPlace instanceof YoutubePlace) {
			return getYoutubeActivity();
		}
		
		/* X */
		if (newPlace instanceof XEditPlace) {
			return getxEditActivity();
		}
		
		return null;
	}
	
	
	/* Home */
	private HomeActivity homeActivity;
	private HomeActivity getHomeActivity() {
		return (homeActivity = (homeActivity == null
				? new HomeActivity(clientFactory)
				: homeActivity));
	}
	
	/* Youtube */
	private YoutubeActivity youtubeActivity;
	private YoutubeActivity getYoutubeActivity() {
		return (youtubeActivity = (youtubeActivity == null 
				? new YoutubeActivity(clientFactory)
				: youtubeActivity));
	}
	
	/* X */
	private XEditActivity xEditActivity;
	public XEditActivity getxEditActivity() {
		return (xEditActivity = (xEditActivity == null 
				? new XEditActivity(clientFactory)
				: xEditActivity));
	}
	
}
