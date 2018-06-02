package com.appslabtest.ormtools.utilities;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class MessengerUtil {

	public void addMessage(Severity msg, String summary, String details) {
		FacesMessage message = new FacesMessage(msg,summary, details);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
