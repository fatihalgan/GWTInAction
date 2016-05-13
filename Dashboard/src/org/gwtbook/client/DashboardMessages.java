package org.gwtbook.client;

import com.google.gwt.i18n.client.Messages;

public interface DashboardMessages extends Messages {
	public String DashboardDefaultNameMessage(String time);
	public String ConfirmDeleteMessage(String applicationName);
	public String BookmarkOnErrorMessage();
	public String BookmarkOnResponseErrorMessage(int errorResponseCode);
	public String WindowResizedMessage(int x, int y);
	public String SaveDashboardNameMessage();
	public String WindowClosingText();
	public String WindowClosedText();
}
