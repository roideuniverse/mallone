package com.example.myProj1.client;
import com.google.gwt.accounts.client.AuthSubStatus;
import com.google.gwt.accounts.client.User;
import com.google.gwt.gdata.client.GData;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.FlexTable.*;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;



public class GmailInvite extends Composite {
	
	public static GmailInvite createInstance() {
		return new GmailInvite();
	}
	
	private FlexTable mainPanel;
	String scope = "http://www.google.com/m8/feeds/";

	
	public GmailInvite() {
		mainPanel = new FlexTable();
	    mainPanel.setCellPadding(2);
	    mainPanel.setCellSpacing(0);
	    initWidget(mainPanel);
	    
	    if (!GData.isLoaded(MyProj1.defaultPackage)) {
	        GData.loadGDataApi(null, new Runnable() {
	          public void run() {
	            startGmailInvite();
	          }
	        }, MyProj1.defaultPackage);
	      } else {
	        startGmailInvite();
	      }
	}
	
	  /**
	   * Displays a status message to the user.
	   * 
	   * @param message The message to display.
	   * @param isError Indicates whether the status is an error status.
	   */
	private void showStatus(String message, boolean isError) {
	    mainPanel.clear();
	    mainPanel.insertRow(0);
	    mainPanel.addCell(0);
	    Label msg = new Label(message);
	    if (isError) {
	      msg.setStylePrimaryName("hm-error");
	    }
	    mainPanel.setWidget(0, 0, msg);
	  }
	
	  private void refresh() {
		    mainPanel.clear();
		  }
	  
	  private void getContactList() {
		  ContactsRetrieveContactsDemo aaa = ContactsRetrieveContactsDemo.createInstance();
		  mainPanel.clear();
		  DecoratorPanel p = new DecoratorPanel();
		  p.setSize("100%", "100%");
		  p.add(aaa);
		  mainPanel.insertRow(0);
		  mainPanel.addCell(0);
		  mainPanel.setWidget(0, 0, p);
	  }

	/*
	 * If everything has gone fine, its time to proceed into the algorithm
	 */
	private void startGmailInviteInProgress() {
		String clickCookie = Cookies.getCookie("InviteButton");
		AuthSubStatus status = User.getStatus(scope);
		refresh();
//		Image image = new Image("details_bt_bg.gif");

		if(clickCookie == null) {
			if (status == AuthSubStatus.LOGGED_IN ) {
				PushButton logout = new PushButton("logout");
				logout.setSize("100%", "100%");
				PushButton invite = new PushButton("invite");
				mainPanel.clear();
				mainPanel.insertRow(0);
				mainPanel.addCell(0);
				mainPanel.addCell(0);
				mainPanel.setWidget(0,1,invite);
				mainPanel.setWidget(0,0,logout);
				
				logout.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent e) {
						User.logout(scope);
						refresh();
					}
				});
				//In this scene, user has not clicked on the on the login button but is till logged in
				//so show him a button to invite his friends over
				invite.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent e) {
						//call the invite functio
						getContactList();
					}
				});
			}
			else if (status == AuthSubStatus.LOGGED_OUT) {
				showStatus("default case. not logged in, nor clicked on logged in button, this is where the gmail app script will come",false);
				//default case. Not clicked on login button nor logged in
//				Window.alert("status="+ status.toString() + " cookie="+clickCookie);
//				Anchor loginLink = new Anchor();
	//			loginLink.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
				
				DecoratorPanel dPanel = new DecoratorPanel();
				AbsolutePanel aPanel = new AbsolutePanel();
				aPanel.setSize("540px", "250px");

				PushButton loginLink = new PushButton();
				loginLink.setText("Invite Gmail Friends :-)") ;
				
				mainPanel.clear();
				FlexCellFormatter cellFormatter = mainPanel.getFlexCellFormatter();
				mainPanel.setCellSpacing(5);
			    mainPanel.setCellPadding(3);
				cellFormatter.setColSpan(0, 0,2);
				
				Grid grid = new Grid(1, 1);
				grid.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
				grid.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);
				HTML google = new HTML();
				google.setHTML("<p style=\"font-size:6pc; \">Google </p>");
				google.setPixelSize(400, 200);
				
				grid.setWidget(0, 0, google);
//			    grid.setBorderWidth(1);
				aPanel.add(new Label("To Invite You Friends, Click on the Invite button below."), 70, 150);
			    aPanel.add(grid, 40 ,5);
			    aPanel.add(loginLink, 150, 180);
				dPanel.setWidget(aPanel);
				mainPanel.setWidget(0,0,dPanel);

				

				
				loginLink.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent e) {
						User.login(scope);
						java.util.Date now = new java.util.Date();
						long nowLong = now.getTime();
						nowLong = nowLong + (1000 * 60 );// 60 seconds 
						now.setTime(nowLong);
						Cookies.setCookie("InviteButton","yes",now);
						Cookies.setCookie("stackIndex","1",now);
						}
					});
				}
			}
		else {
			if(status == AuthSubStatus.LOGGING_IN) {
			    mainPanel.clear();
			    mainPanel.insertRow(0);
			    mainPanel.addCell(0);
			    Label msg = new Label("Processing...");
			    msg.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
			    msg.setSize("500%", "100%");
			    mainPanel.setWidget(0, 0, msg);

				}
			else if (status == AuthSubStatus.LOGGED_IN) {
//				Window.alert("status="+ status.toString() + " cookie="+clickCookie);
				showStatus("click cookie =yes, logged n yes", false);
				//run the background script to INVITE ALL CONTACTS
				getContactList();
				}
			}	
		}
	  
	private void startGmailInvite() {
	    if (Window.Location.getHref().startsWith("http")) {
	      startGmailInviteInProgress();
	    } else {
	      showStatus("This sample must be run over HTTP, AuthSub does not " +
	          "support requests initiated from non-HTTP URIs.", true);
	    }
	}
}
