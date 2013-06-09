package com.chazaqdev.ti4jexample.client.view.home;

import com.emitrom.ti4j.mobile.client.core.handlers.ui.ClickHandler;
import com.emitrom.ti4j.mobile.client.ui.IsWidget;

public interface HomeView extends IsWidget {
	public void setEdit1ClickHandler(ClickHandler clickHandler);
	public void setEdit2ClickHandler(ClickHandler clickHandler);
	public void setYoutubeClickHandler(ClickHandler clickHandler);
}
