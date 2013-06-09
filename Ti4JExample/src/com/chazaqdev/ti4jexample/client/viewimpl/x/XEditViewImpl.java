package com.chazaqdev.ti4jexample.client.viewimpl.x;

import com.chazaqdev.ti4jexample.client.view.x.XEditView;
import com.emitrom.ti4j.mobile.client.core.events.ui.ClickEvent;
import com.emitrom.ti4j.mobile.client.core.handlers.ui.ClickHandler;
import com.emitrom.ti4j.mobile.client.ui.Button;
import com.emitrom.ti4j.mobile.client.ui.Label;
import com.emitrom.ti4j.mobile.client.ui.TextField;
import com.emitrom.ti4j.mobile.client.ui.UI;
import com.emitrom.ti4j.mobile.client.ui.View;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;

public class XEditViewImpl extends View implements XEditView {

	private static XEditViewImplUiBinder uiBinder = GWT
			.create(XEditViewImplUiBinder.class);

	interface XEditViewImplUiBinder extends UiBinder<View, XEditViewImpl> {
	}

	@UiField Button btnBack;
	@UiField Button btnSave;
	@UiField Label lblHeader;
	@UiField TextField txtName;
	@UiField TextField txtAmount;
	
	public XEditViewImpl() {
		setView(uiBinder.createAndBindUi(this));
		txtAmount.setKeyboardType(UI.KEYBOARD_NAMEPHONE_PAD());
	}

	private ClickHandler backClickHandler;
	private ClickHandler saveClickHandler;
	
	@Override
	public View asWidget() {
		return this;
	}
	
	@UiHandler("btnBack")
	void onBackClick(ClickEvent event) {
		if (backClickHandler != null) {
			backClickHandler.onClick(event);
		}
	}
	
	@UiHandler("btnSave")
	void onSaveClick(ClickEvent event) {
		if (saveClickHandler != null) {
			saveClickHandler.onClick(event);
		}
	}
	
	public void setHeaderText(String text) {
		lblHeader.setText(text);
	}
	
	public void setBackClickHandler(ClickHandler backClickHandler) {
		this.backClickHandler = backClickHandler;
	}
	
	public void setSaveClickHandler(ClickHandler saveClickHandler) {
		this.saveClickHandler = saveClickHandler;
	}
	
	@Override
	public int getAmount() {
		return 0;
	}
	
	@Override
	public String getName() {
		return txtName.getValue();
	}
	
	@Override
	public void setAmount(int amount) {
		txtAmount.setValue("" + amount);
	}
	
	@Override
	public void setName(String name) {
		txtName.setValue(name);
	}

}
