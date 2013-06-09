package com.chazaqdev.ti4jexample.client.place.youtube;

import com.google.gwt.place.shared.Place;

public class YoutubePlace extends Place{
	@Override
	public boolean equals(Object obj) {
		return obj instanceof YoutubePlace;
	}
}
