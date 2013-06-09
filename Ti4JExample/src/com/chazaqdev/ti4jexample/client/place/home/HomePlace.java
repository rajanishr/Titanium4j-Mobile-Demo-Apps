package com.chazaqdev.ti4jexample.client.place.home;

import com.google.gwt.place.shared.Place;

public class HomePlace extends Place {
	@Override
	public boolean equals(Object obj) {
		return obj instanceof HomePlace;
	}
}
