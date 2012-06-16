/**
 * 
 */
package com.tenline.pinecone.platform.web.store.client.widgets;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.FitData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.tenline.pinecone.platform.web.store.client.Messages;
import com.tenline.pinecone.platform.web.store.client.events.UserEvents;
import com.tenline.pinecone.platform.web.store.client.events.WidgetEvents;

/**
 * @author Bill
 *
 */
public class LoginViewport extends AbstractViewport {

	/**
	 * 
	 */
	public LoginViewport() {
		super();
		// TODO Auto-generated constructor stub
		LayoutContainer videoDemoContainer = new LayoutContainer(new FitLayout());
		videoDemoContainer.add(new VideoDemo(), new FitData(20));
		body.add(videoDemoContainer, new BorderLayoutData(LayoutRegion.CENTER));
		LayoutContainer loginContainer = new LayoutContainer(new FitLayout());
		LayoutContainer userFormContainer = new LayoutContainer(new CenterLayout());
		userFormContainer.add(new UserForm());
		loginContainer.add(userFormContainer, new FitData(20));
		body.add(loginContainer, new BorderLayoutData(LayoutRegion.EAST, 350));
	}
	
	/**
	 * 
	 * @author Bill
	 *
	 */
	private class VideoDemo extends ContentPanel {
		
		private VideoDemo() {
			setHeaderVisible(false);
			setUrl("http://player.youku.com/player.php/sid/XMzMzNjU3Mzgw/v.swf");
		}
		
	}
	
	/**
	 * 
	 * @author Bill
	 *
	 */
	private class UserForm extends FormPanel {
		
		private UserForm() {
			setHeaderVisible(false);
			setLabelSeparator("");
			
			final TextField<String> accountField = new TextField<String>();
			accountField.setAllowBlank(false);
			accountField.setEmptyText(((Messages) Registry.get(Messages.class.getName())).accountEmptyText());
			accountField.setFieldLabel(((Messages) Registry.get(Messages.class.getName())).account());
			accountField.setRegex("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
			accountField.getMessages().setBlankText(((Messages) Registry.get(Messages.class.getName())).accountBlankWarning());
			accountField.getMessages().setRegexText(((Messages) Registry.get(Messages.class.getName())).accountRegexWarning());
			
			final TextField<String> passwordField = new TextField<String>();
			passwordField.setPassword(true);
			passwordField.setAllowBlank(false);		
			passwordField.setFieldLabel(((Messages) Registry.get(Messages.class.getName())).password());
			passwordField.getMessages().setBlankText(((Messages) Registry.get(Messages.class.getName())).passwordBlankWarning());	
			
			final Button loginButton = new Button(((Messages) Registry.get(Messages.class.getName())).login());
			final Button registerButton = new Button(((Messages) Registry.get(Messages.class.getName())).register());
			loginButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

				@Override
				public void componentSelected(ButtonEvent event) {
					// TODO Auto-generated method stub
					if (UserForm.this.isValid()) {
						AppEvent appEvent = new AppEvent(UserEvents.LOGIN);
						appEvent.setData("email", accountField.getValue());
						appEvent.setData("password", passwordField.getValue());
						Dispatcher.get().dispatch(appEvent);
					}
				}
				
			});
			registerButton.addSelectionListener(new SelectionListener<ButtonEvent>() {

				@Override
				public void componentSelected(ButtonEvent event) {
					// TODO Auto-generated method stub
					Dispatcher.get().dispatch(new AppEvent(WidgetEvents.UPDATE_REGISTER_TO_PANEL));
				}
				
			});
			
			add(accountField);
			add(passwordField);
			addButton(loginButton);
			addButton(registerButton);
		}
		
	}

}
