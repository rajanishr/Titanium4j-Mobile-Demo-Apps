package com.chazaqdev.ti4jexample.client.place.x;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class XEditPlace extends Place {
	public static class XEditPlaceTokenizer implements PlaceTokenizer<XEditPlace> {
		@Override
		public XEditPlace getPlace(String token) {
			int id = -1;
			
			//Make sure we at least got an int of -1
			try {
				id = Integer.parseInt(token);
			} catch (Exception e) {
			}
			return new XEditPlace(id);
		}
		
		@Override
		public String getToken(XEditPlace place) {
			return "" + place.getId();
		}
	}

	private final int id;
	public XEditPlace() {
		this.id = -1;
	}
	
	public XEditPlace(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean xedit = obj instanceof XEditPlace;
		boolean sameId = xedit && ((XEditPlace)obj).getId() == id;
		return sameId;
	}
	
}
