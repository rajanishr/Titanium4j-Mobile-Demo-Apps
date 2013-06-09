package com.chazaqdev.ti4jexample.client.view.youtube;

import java.util.List;

import com.chazaqdev.ti4jexample.client.widgets.YoutubeResult;
import com.emitrom.ti4j.mobile.client.core.handlers.ui.ClickHandler;
import com.emitrom.ti4j.mobile.client.ui.IsWidget;

public interface YoutubeView extends IsWidget {
	
	public void setBackClickHandler(ClickHandler clickHandler);
	public void setSearchClickHandler(ClickHandler clickHandler);
	public String getSearchString();
	public void setYoutubeResults(List<YoutubeResult> results);
}
