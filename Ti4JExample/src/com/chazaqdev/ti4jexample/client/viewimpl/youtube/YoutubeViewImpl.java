package com.chazaqdev.ti4jexample.client.viewimpl.youtube;

import java.util.List;

import com.chazaqdev.ti4jexample.client.view.youtube.YoutubeView;
import com.chazaqdev.ti4jexample.client.widgets.YoutubeResult;
import com.emitrom.ti4j.mobile.client.core.events.ui.ClickEvent;
import com.emitrom.ti4j.mobile.client.core.handlers.ui.ClickHandler;
import com.emitrom.ti4j.mobile.client.ui.Button;
import com.emitrom.ti4j.mobile.client.ui.ScrollView;
import com.emitrom.ti4j.mobile.client.ui.TextField;
import com.emitrom.ti4j.mobile.client.ui.View;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;

public class YoutubeViewImpl extends View implements YoutubeView{

	private static YoutubeViewImplUiBinder uiBinder = GWT
			.create(YoutubeViewImplUiBinder.class);

	interface YoutubeViewImplUiBinder extends UiBinder<View, YoutubeViewImpl> {
	}

	@UiField Button btnBack;
	@UiField TextField txtSearch;
	@UiField Button btnSearch;
	@UiField ScrollView lstResults;
	
	
	public YoutubeViewImpl() {
		setView(uiBinder.createAndBindUi(this));
	}
	
	private ClickHandler backClickHandler;
	private ClickHandler searchClickHandler;
	
	@UiHandler("btnBack")
	void btnBackClick(ClickEvent event) {
		if (backClickHandler != null) {
			backClickHandler.onClick(event);
		}
	}
	
	@UiHandler("btnSearch")
	void btnSearchClick(ClickEvent event) {
		if (searchClickHandler != null) {
			searchClickHandler.onClick(event);
		}
	}
	
	@Override
	public String getSearchString() {
		return txtSearch.getValue();
	}
	
	public void setBackClickHandler(ClickHandler backClickHandler) {
		this.backClickHandler = backClickHandler;
	}
	
	public void setSearchClickHandler(ClickHandler searchClickHandler) {
		this.searchClickHandler = searchClickHandler;
	}
	
	@Override
	public View asWidget() {
		return this;
	}
	
	@Override
	public void setYoutubeResults(List<YoutubeResult> results) {
		List<View> c = lstResults.getChildren();
		for (View v : c) {
			lstResults.remove(v);
		}
		for (int i = 0; i < results.size(); ++i) {
			lstResults.add(results.get(i));
		}
	}
		
}
