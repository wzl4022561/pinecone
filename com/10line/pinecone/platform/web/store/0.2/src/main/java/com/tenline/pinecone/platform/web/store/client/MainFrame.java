package com.tenline.pinecone.platform.web.store.client;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout.HBoxLayoutAlign;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.tenline.pinecone.platform.model.User;

public class MainFrame extends Viewport {
	/**登陆按钮*/
	private Button logoutButton;
	/**帮助按钮*/
	private Button helpButton;
	
	public MainFrame(User user) {
		setBorders(true);
		setLayout(new BorderLayout());
		/**
		 * 设置主页面Panel
		 */
		ContentPanel cntntpnlNewContentpanel = new ContentPanel();
		cntntpnlNewContentpanel.setHeaderVisible(false);
		cntntpnlNewContentpanel.setHeading("New ContentPanel");
		cntntpnlNewContentpanel.setCollapsible(true);
		cntntpnlNewContentpanel.setLayout(new BorderLayout());
		
		ContentPanel centerContentPanel = new ContentPanel();
		centerContentPanel.setHeaderVisible(false);
		centerContentPanel.setHeading("New ContentPanel");
		centerContentPanel.setCollapsible(true);
		centerContentPanel.setLayout(new BorderLayout());
		BorderLayoutData bld_centerContentPanel = new BorderLayoutData(LayoutRegion.CENTER);
		bld_centerContentPanel.setMargins(new Margins(5, 5, 5, 5));
		cntntpnlNewContentpanel.add(centerContentPanel, bld_centerContentPanel);
		BorderLayoutData bld_cntntpnlNewContentpanel = new BorderLayoutData(LayoutRegion.CENTER);
		bld_cntntpnlNewContentpanel.setMargins(new Margins(8, 8, 8, 8));
		add(cntntpnlNewContentpanel, bld_cntntpnlNewContentpanel);
		/**
		 * 初始化用户主页面
		 */
		centerContentPanel.add(EnvConfig.getHomePage(), new BorderLayoutData(LayoutRegion.CENTER));
		
//		/**
//		 * 初始化应用商店的Panel
//		 */
////		cntntpnlNewContentpanel_2.add(EnvConfig.getAppPage(), new BorderLayoutData(LayoutRegion.CENTER));
		
		/**
		 * 页面顶部的菜单，包括用户登录，设置， 帮助的功能
		 */
		ToolBar toolBar = new ToolBar();
		
		LayoutContainer layoutContainer = new LayoutContainer();
		HBoxLayout hbl_layoutContainer = new HBoxLayout();
		hbl_layoutContainer.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);
		layoutContainer.setLayout(hbl_layoutContainer);
		
		LayoutContainer layoutContainer_1 = new LayoutContainer();
		layoutContainer_1.setLayout(new FitLayout());
		
		Image image = new Image("../img/logo-2.png");
		layoutContainer_1.add(image);
		layoutContainer.add(layoutContainer_1);
		layoutContainer_1.setSize("250", "60");
		toolBar.add(layoutContainer);
		layoutContainer.setSize("300", "60");
		
		FillToolItem fillToolItem = new FillToolItem();
		toolBar.add(fillToolItem);
		
		Text txtNewText = new Text("<m-appstore-menu>"+user.getName()+",欢迎回来！</m-appstore-menu>");
		toolBar.add(txtNewText);
		
		logoutButton = new Button("<m-appstore-menu>注销</m-appstore-menu>");
		logoutButton.setHeight(40);
		logoutButton.addListener(Events.Select, new Listener<ButtonEvent>() {
			public void handleEvent(ButtonEvent e) {
				MainFrame.this.removeFromParent();
				RootPanel.get().add(new HomePage());
			}
		});
		
		SeparatorToolItem separatorToolItem_1 = new SeparatorToolItem();
		toolBar.add(separatorToolItem_1);
		toolBar.add(logoutButton);
		logoutButton.setHeight("");
		
		SeparatorToolItem separatorToolItem = new SeparatorToolItem();
		toolBar.add(separatorToolItem);
		
		helpButton = new Button("<m-appstore-menu>\u5E2E\u52A9</m-appstore-menu>");
		helpButton.setHeight(40);
		helpButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
			public void componentSelected(ButtonEvent ce) {
			}
		});
		toolBar.add(helpButton);
		helpButton.setHeight("");
		cntntpnlNewContentpanel.setTopComponent(toolBar);
	}
}