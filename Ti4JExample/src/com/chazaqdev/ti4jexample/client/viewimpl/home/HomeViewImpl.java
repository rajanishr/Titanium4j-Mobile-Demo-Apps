package com.chazaqdev.ti4jexample.client.viewimpl.home;

import com.chazaqdev.ti4jexample.client.images.Icons;
import com.chazaqdev.ti4jexample.client.utils.Utils;
import com.chazaqdev.ti4jexample.client.view.home.HomeView;
import com.emitrom.ti4j.mobile.client.core.events.ui.ClickEvent;
import com.emitrom.ti4j.mobile.client.core.handlers.ui.ClickHandler;
import com.emitrom.ti4j.mobile.client.ui.Button;
import com.emitrom.ti4j.mobile.client.ui.ImageView;
import com.emitrom.ti4j.mobile.client.ui.View;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;

public class HomeViewImpl extends View implements HomeView {

	private static HomeViewImplUiBinder uiBinder = GWT
			.create(HomeViewImplUiBinder.class);

	interface HomeViewImplUiBinder extends UiBinder<View, HomeViewImpl> {
	}

	@UiField Button btnEdit1;
	@UiField Button btnEdit2;
	@UiField Button btnYoutube;
	@UiField ImageView whistle;
	@UiField ImageView finish;
	
	ClickHandler edit1ClickHandler;
	ClickHandler edit2ClickHandler;
	ClickHandler youtubeClickHandler;
	
	public HomeViewImpl() {
		setView(uiBinder.createAndBindUi(this));
		whistle.setImage(Icons.INSTANCE.whistle(), Utils.getImageUrl(Icons.INSTANCE.whistle()), true);
		finish.setImage(Icons.INSTANCE.finish(), Utils.getImageUrl(Icons.INSTANCE.finish()), true);
	}
	
	@UiHandler("btnEdit1")
	void onEdit1Click(ClickEvent event) {
		if (edit1ClickHandler != null) {
			edit1ClickHandler.onClick(event);
		}
	}
	
	@UiHandler("btnEdit2")
	void onEdit2Click(ClickEvent event) {
		if (edit2ClickHandler != null) {
			edit2ClickHandler.onClick(event);
		}
	}
	
	@UiHandler("btnYoutube")
	void onYoutubeClick(ClickEvent event) {
		if (youtubeClickHandler != null) {
			youtubeClickHandler.onClick(event);
		}
	}
	
	public void setEdit1ClickHandler(ClickHandler edit1ClickHandler) {
		this.edit1ClickHandler = edit1ClickHandler;
	}
	
	public void setEdit2ClickHandler(ClickHandler edit2ClickHandler) {
		this.edit2ClickHandler = edit2ClickHandler;
	}
	
	@Override
	public void setYoutubeClickHandler(ClickHandler clickHandler) {
		this.youtubeClickHandler = clickHandler;
	}

	@Override
	public View asWidget() {
		return this;
	}

}
