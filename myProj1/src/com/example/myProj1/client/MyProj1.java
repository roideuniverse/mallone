package com.example.myProj1.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.DecoratedStackPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.HasVerticalAlignment;//.VerticalAlignmentConstant;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.dom.client.Style.Unit;
//import com.google.gwt.gdata.client.GData;
import com.google.gwt.gdata.client.GDataSystemPackage;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.user.client.ui.* ;
import com.google.gwt.user.client.Cookies;

import com.google.gwt.user.client.ui.Widget;

public class MyProj1 implements EntryPoint {
	
	public static GDataSystemPackage defaultPackage =
	    GDataSystemPackage.CONTACTS;	
	DecoratedStackPanel stackPanel = new DecoratedStackPanel ();
	Integer stackIndexer;

		  public void onModuleLoad() {
		    
			  
		    VerticalSplitPanel mainPanel = new VerticalSplitPanel ();
		    mainPanel.setWidth("100%");
		    mainPanel.setHeight("700px");

		    mainPanel.setSplitPosition("30px");
		    
		    String cookie = Cookies.getCookie("stackIndex");
		    if(cookie != null) {
		    	stackIndexer = Integer.decode(cookie);
		    }
		    else
		    	stackIndexer = 0;
		    
		    HorizontalPanel vPanel = new HorizontalPanel();
		    vPanel.setWidth("100%");
		    vPanel.setHeight("100%");
		    
		    
		    DecoratorPanel register = new DecoratorPanel();
		    register.setStyleName("my_css");
		    Anchor r = new Anchor(" Register ");
		    r.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_DEFAULT);
		    register.setSize("100%", "100%");
		    register.add(r);

		    r.addMouseOverHandler(new MouseOverHandler() {
		    	public void onMouseOver(MouseOverEvent e) {
		    		stackPanel.showStack(0);
		    	}
		    });
		    
		    DecoratorPanel dPanelGmail = new DecoratorPanel();
		    dPanelGmail.setStyleName("my_css");
		    Anchor gmail = new Anchor("Invite Gmail Friends");
//		    gmail.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		    dPanelGmail.add(gmail);
		    dPanelGmail.setSize("100%", "100%");

		    gmail.addMouseOverHandler(new MouseOverHandler() {
		    	public void onMouseOver(MouseOverEvent e) {
		    		stackPanel.showStack(1);
		    	}
		    });
		    
		    gmail.addFocusHandler(new FocusHandler() {
		    	public void onFocus(FocusEvent e) {
		    		//same code as omouse over event;
		    	}
		    });
		    
		    
		    DecoratorPanel dPanelYahoo = new DecoratorPanel();
		    dPanelYahoo.setStyleName("my_css");
		    Anchor yahoo = new Anchor("Invite Yahoo Friends");
		    dPanelYahoo.add(yahoo);
		    dPanelYahoo.setSize("100%", "100%");
		    
		    yahoo.addMouseOverHandler(new MouseOverHandler() {
		    	public void onMouseOver(MouseOverEvent e) {
		    		stackPanel.showStack(2);
		    	}
		    });
		    
		    DecoratorPanel dPanelMsn = new DecoratorPanel();
		    dPanelMsn.setStyleName("my_css");
		    Anchor msn = new Anchor("Invite Hotmail Friends");
//		    msn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		    dPanelMsn.add(msn);
		    dPanelMsn.setSize("100%", "100%");
		    
		    msn.addMouseOverHandler(new MouseOverHandler() {
		    	public void onMouseOver(MouseOverEvent e) {
		    		stackPanel.showStack(3);
		    	}
		    });

		    
		    vPanel.add(register);
		    vPanel.add(dPanelGmail);
		    vPanel.add(dPanelYahoo);
		    vPanel.add(dPanelMsn);

		    
		    NamedFrame documentFrame = new NamedFrame ("_framex");
		    documentFrame.setUrl("http://www.yahoo.com");
		    documentFrame.setSize("100%", "100%");
		    
		    Frame f = new Frame("http://www.msn.com");
		    f.setSize("100%", "100%");
		
		    stackPanel.setWidth("99%");
		    stackPanel.setHeight("100%");

		    mainPanel.add(vPanel);
		    mainPanel.add(stackPanel);
		    
		    GmailInvite g = GmailInvite.createInstance();
		    stackPanel.add( Register.createInstance() );
		    stackPanel.add(g);
		    stackPanel.add(documentFrame);
//		    stackPanel.add(f);
		    stackPanel.showStack(stackIndexer);
		    RootPanel.get("_invitation_").add(mainPanel);
		    
		  }
		}