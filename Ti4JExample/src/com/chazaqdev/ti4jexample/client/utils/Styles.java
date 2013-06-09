package com.chazaqdev.ti4jexample.client.utils;

import com.emitrom.ti4j.mobile.client.ui.style.Font;
import com.emitrom.ti4j.mobile.client.ui.style.FontWeight;

public class Styles {
	public static Font YT_NAME_FONT = new Font("14sp", FontWeight.BOLD);
	public static Font YT_DATA_FONT = new Font("9sp", FontWeight.NORMAL);
	public static Font YT_RATING_FONT = new Font("24sp", FontWeight.BOLD);
	public static Font YT_HEADER_FONT = new Font("12sp", FontWeight.BOLD);
	
	public static Font getYT_NAME_FONT() {
		return YT_NAME_FONT;
	}
	
	public static Font getYT_DATA_FONT() {
		return YT_DATA_FONT;
	}
	
	public static Font getYT_RATING_FONT() {
		return YT_RATING_FONT;
	}
	
	public static Font getYT_HEADER_FONT() {
		return YT_HEADER_FONT;
	}
}
