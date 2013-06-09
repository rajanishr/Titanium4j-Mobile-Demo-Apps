package com.chazaqdev.ti4jexample.client.data;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class YoutubeSearchResult extends JavaScriptObject {
	protected YoutubeSearchResult() {}
	
	public final native JsArray<YoutubeResultItem> getEntries() /*-{
		return this.feed.entry;
	}-*/;
	
}
