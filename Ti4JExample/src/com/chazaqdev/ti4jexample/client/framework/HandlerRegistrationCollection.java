package com.chazaqdev.ti4jexample.client.framework;

import java.util.LinkedList;

import com.google.web.bindery.event.shared.HandlerRegistration;

public class HandlerRegistrationCollection implements HandlerRegistration, com.google.gwt.event.shared.HandlerRegistration{
	private LinkedList<com.google.web.bindery.event.shared.HandlerRegistration> collectedHandlers = new LinkedList<com.google.web.bindery.event.shared.HandlerRegistration>();

	/**
	 * Construct an empty HandlerRegistrationCollection
	 */
	public HandlerRegistrationCollection() {

	}

	/**
	 * Add a {@link HandlerRegistration} to the collection
	 *
	 * @param handlerRegistration the handlerregistration to add
	 */
	public void addHandlerRegistration(HandlerRegistration handlerRegistration) {
		collectedHandlers.add(handlerRegistration);
	}

	public void addHandlerRegistration(com.google.gwt.event.shared.HandlerRegistration handlerRegistration) {
		collectedHandlers.add(handlerRegistration);
	}

	/**
	 * {@inheritDoc}
	 *
	 * Remove all handlers: Call remove on each Handler
	 */
	@Override
	public void removeHandler() {
		for (com.google.web.bindery.event.shared.HandlerRegistration handlerRegistration : collectedHandlers) {
			handlerRegistration.removeHandler();
		}
		collectedHandlers.clear();

	}
}
