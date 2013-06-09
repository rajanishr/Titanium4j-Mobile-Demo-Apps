package com.chazaqdev.ti4jexample.client.utils;

import com.emitrom.ti4j.mobile.client.api.API;
import com.emitrom.ti4j.mobile.client.filesystem.FileSystem;
import com.google.gwt.resources.client.ImageResource;

public class Utils {


	/* IMAGE + RESOURCE URLS*/
	public static final String APP_NAME = "ti4je";
	public static final String SEPERATOR = FileSystem.get().getSeparator();
	public static final String RESOURCES_DIR = FileSystem.get().getResourcesDirectory();
	public static String getImageUrl(ImageResource ir) {
		return "" + RESOURCES_DIR + APP_NAME + SEPERATOR + ir.getSafeUri().asString().substring(4);
	}
	
	public static String getImageUrl(String image) {
		return RESOURCES_DIR + "images" + SEPERATOR + image;
	}
	
	public static String getResourceDir() {
		return RESOURCES_DIR + APP_NAME + SEPERATOR;
	}
	
	/*Other*/
	private static ClientFactory clientFactory;
	public static ClientFactory getClientFactory() {
		return clientFactory;
	}
	public static void setClientFactory(ClientFactory clientFactory) {
		Utils.clientFactory = clientFactory;
	}
	
	public static void log(String log) {
		API.get().log("info", "" + log);
	}
}
