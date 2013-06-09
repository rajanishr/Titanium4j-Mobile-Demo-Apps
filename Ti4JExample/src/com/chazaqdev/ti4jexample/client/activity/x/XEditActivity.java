package com.chazaqdev.ti4jexample.client.activity.x;

import com.chazaqdev.ti4jexample.client.activity.AbstractActivity;
import com.chazaqdev.ti4jexample.client.data.X;
import com.chazaqdev.ti4jexample.client.place.home.HomePlace;
import com.chazaqdev.ti4jexample.client.place.x.XEditPlace;
import com.chazaqdev.ti4jexample.client.utils.ClientFactory;
import com.chazaqdev.ti4jexample.client.view.x.XEditView;
import com.emitrom.ti4j.mobile.client.Titanium;
import com.emitrom.ti4j.mobile.client.core.events.ui.ClickEvent;
import com.emitrom.ti4j.mobile.client.core.handlers.ui.ClickHandler;
import com.emitrom.ti4j.mobile.client.ui.Window;

public class XEditActivity extends AbstractActivity {
	
	public XEditActivity(ClientFactory clientFactory) {
		super(clientFactory);
	}
	
	private XEditView xEditView;
	private Window xEditWindow;
	private X x;
	
	private void init() {
		xEditView = clientFactory.getXEditView();
		xEditWindow = new Window();
		xEditWindow.setNavBarHidden(false);
		xEditWindow.add(xEditView);
		
		xEditView.setSaveClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				x.setName(xEditView.getName());
				x.setAmount(xEditView.getAmount());
			}
		});
		
		xEditView.setBackClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				clientFactory.getPlaceController().goTo(new HomePlace());
			}
		});
	}
	
	public X getX() {
		return x;
	}
	
	@Override
	public void onStart() {
		if (xEditView == null) {
			init();
		}
		
		//Get the id from the place
		XEditPlace p = (XEditPlace) clientFactory.getPlaceController().getWhere();
		if (p.getId() != -1) {
			x = null;
			for (X xi : X.xItems) {
				if (xi.getId() == p.getId()) {
					x = xi;
				}
			}
		} else {
			//We are to make a new X object and edit it
			x = new X();
		}
		
		if (x == null) {
			Titanium.alert("Could not find X with id " + p.getId());
			clientFactory.getPlaceController().goTo(new HomePlace());
			return;
		}
		
		xEditView.setName(x.getName());
		xEditView.setAmount(x.getAmount());
		
		clientFactory.closePreviousWindows(xEditWindow);
		xEditWindow.open();
		clientFactory.registerWindowForClosure(xEditWindow);
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean xedit = obj instanceof XEditActivity;
		boolean sameId = false;
		if (xedit) {
			XEditActivity xe = (XEditActivity)obj;
			sameId = xe.getX().getId() == getX().getId();
		}
		return sameId;
	}
}
