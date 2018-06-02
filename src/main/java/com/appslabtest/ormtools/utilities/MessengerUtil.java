package com.appslabtest.ormtools.utilities;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class MessengerUtil {

	public void addMessage(Severity msgType, String summary, String title) {
		FacesMessage message = new FacesMessage(msgType,summary, title);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
