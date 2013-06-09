package com.chazaqdev.ti4jexample.client.data;

import com.google.gwt.core.client.JavaScriptObject;

public class YoutubeResultItem extends JavaScriptObject {
	protected YoutubeResultItem() {
	}	
	
	public final native String getId() /*-{
		return this.id.$t;
	}-*/;
	
	public final native String getPublished() /*-{
		return this.published.$t;
	}-*/;
	
	public final native String getTitle() /*-{
		return this.title.$t;
	}-*/;
	
	public final native double getRatingAverage() /*-{
		return this.gd$rating.average;
	}-*/;
	
	public final native String getContent() /*-{
		return this.content.$t;
	}-*/;
	
	public final String getImageUrl() {
		String ret = getContent();
		//search for something that starts with src=\"http://i.ytimg.com/vi
		ret = ret.substring(ret.indexOf("src=\"http://i.ytimg.com/vi"));
		ret = ret.substring(5); //remove the src=" part
		ret = ret.substring(0, ret.indexOf("\""));
		return ret;
	}
}
