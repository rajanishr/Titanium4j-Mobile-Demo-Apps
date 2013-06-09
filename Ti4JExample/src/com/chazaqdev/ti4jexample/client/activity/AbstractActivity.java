package com.chazaqdev.ti4jexample.client.activity;

import com.chazaqdev.ti4jexample.client.utils.ClientFactory;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public abstract class AbstractActivity implements ActivityTi {

	protected final ClientFactory clientFactory;
	
	public AbstractActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}
	
	@Override
	public void onStop() {
	}
	
	@Override
	public void onCancel() {
	}
	
	@Override
	public String mayStop() {
		return null;
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		onStart();
	}
	
}
