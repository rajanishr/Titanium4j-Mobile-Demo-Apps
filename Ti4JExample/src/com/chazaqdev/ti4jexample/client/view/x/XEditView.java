package com.chazaqdev.ti4jexample.client.view.x;

import com.emitrom.ti4j.mobile.client.core.handlers.ui.ClickHandler;
import com.emitrom.ti4j.mobile.client.ui.IsWidget;

public interface XEditView extends IsWidget {
	public void setSaveClickHandler(ClickHandler clickHandler);
	public void setBackClickHandler(ClickHandler clickHandler);
	public void setHeaderText(String text);
	public String getName();
	public int getAmount();
	public void setName(String name);
	public void setAmount(int amount);
}
