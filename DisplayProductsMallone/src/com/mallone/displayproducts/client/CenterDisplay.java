package com.mallone.displayproducts.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FormPanel.*;
import com.google.gwt.user.client.ui.*;


public class CenterDisplay extends Composite {
	
	private Grid centerBlock;
	
	public static CenterDisplay createInstance() {
		return new CenterDisplay();
	}

	public CenterDisplay() {
	
		centerBlock = new Grid(3,3);
		initWidget(centerBlock);
		
		VerticalPanel v = new VerticalPanel();
		v.setSize("50%", "50%");
		v.setBorderWidth(2);
		
		for (int row = 0; row < 3; row++) {
		      for (int col = 0; col < 3; col++)
		        centerBlock.setWidget(row, col, v);
		   }
		
		for (int row=0; row<3 ; row++)
			for (int col =0 ; col <3 ; col++)
			{
				centerBlock.getCellFormatter().setWidth(row, col, "50%");
				centerBlock.getCellFormatter().setHeight(row, col, "50%");
				centerBlock.setBorderWidth(2);
			}		
	}
	
	

}
