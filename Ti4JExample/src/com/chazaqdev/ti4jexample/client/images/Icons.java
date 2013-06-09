package com.chazaqdev.ti4jexample.client.images;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;


public interface Icons extends ClientBundle {
	public static final Icons INSTANCE = GWT.create(Icons.class);
	
	@Source("finish.png")
	ImageResource finish();
	
	@Source("whistle.png")
	ImageResource whistle();

}
