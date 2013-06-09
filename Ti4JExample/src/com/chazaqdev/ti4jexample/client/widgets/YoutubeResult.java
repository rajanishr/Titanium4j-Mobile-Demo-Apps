package com.chazaqdev.ti4jexample.client.widgets;

import com.chazaqdev.ti4jexample.client.data.YoutubeResultItem;
import com.emitrom.ti4j.mobile.client.Titanium;
import com.emitrom.ti4j.mobile.client.core.events.ui.ClickEvent;
import com.emitrom.ti4j.mobile.client.ui.ImageView;
import com.emitrom.ti4j.mobile.client.ui.Label;
import com.emitrom.ti4j.mobile.client.ui.View;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;

public class YoutubeResult extends View {

	private static YoutubeResultUiBinder uiBinder = GWT
			.create(YoutubeResultUiBinder.class);

	interface YoutubeResultUiBinder extends UiBinder<View, YoutubeResult> {
	}

	public YoutubeResult() {
		setView(uiBinder.createAndBindUi(this));
	}
	
	@UiField ImageView thumb;
	@UiField View main;
	@UiField Label title;
	@UiField Label date;
	@UiField Label rating;
	
	private YoutubeResultItem item;
	
	public void readInItem(YoutubeResultItem item) {
		thumb.setImage(item.getImageUrl());
		title.setText(item.getTitle());
		date.setText(item.getPublished());
		String theRating = NumberFormat.getFormat("0.0").format(item.getRatingAverage());
		rating.setText(theRating);
		this.item = item;
	}
	
	@UiHandler("main")
	void mainClick(ClickEvent event) {
		if (item != null) {
			Titanium.alert("Item id:" + item.getId() + " {img url:" + item.getImageUrl() + "}");
		}
	}

}
