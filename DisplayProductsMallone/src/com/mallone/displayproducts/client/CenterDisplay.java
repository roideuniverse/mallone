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
	
	private HorizontalPanel centerBlock;
	private static String recStr = "";
	
	public static CenterDisplay createInstance(String s) {
		s = "PRODUCT_ID=2:BRAND_ID=5:BRAND_CATEGORY_CODE= Samsung001:" +
				"PRICE=:BRAND_CATEGORY_DESC=:IMAGE_NAME=CorbyPlus_small.gif;"+
				"PRODUCT_ID=2:BRAND_ID=5:BRAND_CATEGORY_CODE=Samsung007:PRICE=:BRAND_CATEGORY_DESC=:IMAGE_NAME=;"+
				"PRODUCT_ID=2:BRAND_ID=5:BRAND_CATEGORY_CODE= Samsung003:PRICE=:BRAND_CATEGORY_DESC=:IMAGE_NAME=CorbyPlus_small.gif;"+
				"PRODUCT_ID=2:BRAND_ID=5:BRAND_CATEGORY_CODE= Samsung004:PRICE=:BRAND_CATEGORY_DESC=:IMA" +
				"GE_NAME=C5130.jpg;" +
				"PRODUCT_ID=2:BRAND_ID=5:BRAND_CATEGORY_CODE= Samsung002:PRICE=:BRAND_CATEGORY_DESC=:IMAGE_NAME=CorbyTXTB3210_small.jpg;";
		recStr += s;
		Window.alert("in static\n" + recStr);
		return new CenterDisplay();
	}

	public CenterDisplay() {
	
		centerBlock = new HorizontalPanel();
		centerBlock.setWidth("100%");
		centerBlock.setHeight("100%");
		centerBlock.setBorderWidth(1);
		
		
		VerticalPanel left = new VerticalPanel();
		left.setWidth("100%");
		left.setHeight("100%");
		
		VerticalPanel middle = new VerticalPanel();
		middle.setWidth("100%");
		middle.setHeight("100%");
		
		VerticalPanel right = new VerticalPanel();
		right.setWidth("100%");
		right.setHeight("100%");
		
		centerBlock.add(left);
		centerBlock.setCellWidth(left, "33%");
		
		VerticalPanel left1 = new VerticalPanel();
		left1.setSize("100%", "100%");
		left1.setBorderWidth(1);
		VerticalPanel left2 = new VerticalPanel();
		left2.setSize("100%", "100%");
		left2.setBorderWidth(1);
		VerticalPanel left3 = new VerticalPanel();
		left3.setBorderWidth(1);
		
		left.add(left1);
		left.setCellHeight(left1, "33%");
		left.add(left2);
		left.setCellHeight(left2, "33%");
		left.add(left3);
		left.setCellHeight(left3, "34%");
		
		centerBlock.add(middle);
		centerBlock.setCellWidth(middle, "33%");
		
		VerticalPanel middle1 = new VerticalPanel();
		middle1.setSize("100%", "100%");
		middle1.setBorderWidth(2);
		VerticalPanel middle2 = new VerticalPanel();
		middle2.setSize("100%", "100%");
		middle2.setBorderWidth(1);
		VerticalPanel middle3 = new VerticalPanel();
		middle3.setSize("100%", "100%");
		middle2.setBorderWidth(3);
		middle.add(middle1);
		middle.setCellHeight(middle1, "33%");
		middle.add(middle2);
		middle.setCellHeight(middle2, "33%");
		middle.add(middle3);
		middle.setCellHeight(middle3, "34%");
		
		
		centerBlock.add(right);
		
		VerticalPanel right1 = new VerticalPanel();
		right1.setSize("100%", "100%");
		right1.setBorderWidth(1);
		VerticalPanel right2 = new VerticalPanel();
		right2.setSize("100%", "100%");
		right2.setBorderWidth(1);
		VerticalPanel right3 = new VerticalPanel();
		right3.setSize("100%", "100%");
		right3.setBorderWidth(1);

		right.add(right1);
		right.setCellHeight(right1, "33%");
		right.add(right2);
		right.setCellHeight(right2, "33%");
		right.add(right3);
		right.setCellHeight(right3, "34%");
		
		initWidget(centerBlock);
/*	
*/
		
		String Split1[] = recStr.split(";");
		int col=0;
		int row=0;
		Window.alert("id this empty\n"+recStr);
		for(int i=0; i<Split1.length ; i++)
		{
			String s2[] = Split1[i].split(":") ;
			VerticalPanel x = new VerticalPanel();
			x.setSize("100%", "100%");
//			x.setBorderWidth(10);
			String pId ="" , bId="" , bCode="", price="" , bDesc="" , iName="" ;

			for(int j=0; j< s2.length ; j++)
			{
//				Window.alert(s2[j]);
				String s3[] = s2[j].split("=");
				if(s3.length>1)
				{
					if(s3[0].compareTo("PRODUCT_ID") ==0) 
						pId += s3[1];
					else if(s3[0].compareTo( "BRAND_ID")==0) 
						bId += s3[1];
					else if(s3[0].compareTo( "BRAND_CATEGORY_CODE")==0) 
						bCode += s3[1];
					else if(s3[0].compareTo("PRICE") ==0)
						price += s3[1];
					else if(s3[0].compareTo("BRAND_CATEGORY_DESC")==0) 
						bDesc += s3[1];
					else if( s3[0].compareTo("IMAGE_NAME") == 0 )
						iName += s3[1];
					else 
						Window.alert("FAILURE-\""+ s3[0] +"\"" );
				}
			}
			Label i1= new Label(pId);
			Label i2= new Label(bId);
			Label i3= new Label(bCode);
			Label i4= new Label(price);
			Label i5= new Label(bDesc);
			Image image = new Image();
			image.setUrl("http://localhost/m/images/" + iName );
			image.setSize("130px", "130px");
//			x.add(image, 0, 0);
			Label i6= new Label(iName);
//			x.add(i1);
//			x.add(i3);
			x.add(image);
			x.add(i2);
			x.add(i4);
			x.add(i5);
			
			VerticalPanel www = (VerticalPanel) centerBlock.getWidget(col);
			VerticalPanel www2 = (VerticalPanel) www.getWidget(row);
			www2.add(x);
	//		www2.setCellHeight(x, "20px");
			if (col >= 2)
			{
				col =0;
				row++;
			}
			else 
				col++;
			
			if (row >=2)
				row=0;
		}
	}
}