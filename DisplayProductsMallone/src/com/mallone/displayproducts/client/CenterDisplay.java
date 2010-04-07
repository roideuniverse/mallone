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
import com.google.gwt.user.client.ui.HasVerticalAlignment.VerticalAlignmentConstant;
import com.google.gwt.user.client.ui.*;


public class CenterDisplay extends Composite {
	
	private HorizontalPanel centerBlock;
	private static String recStr = "";
	private FormPanel formCompare;
	private VerticalPanel formVPanel;
	private Integer selected =0;
	private Hidden selectedNo;
	
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
		
		selectedNo = new Hidden();
		selectedNo.setName("COUNT");
		
		formCompare = new FormPanel();
		formCompare.setMethod(FormPanel.METHOD_GET);
		formCompare.setAction("php/compare.php");
		formVPanel = new VerticalPanel();
		formCompare.add(formVPanel);
		formCompare.setVisible(false);
		formVPanel.add(selectedNo);

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
		VerticalPanel leftBottom = new VerticalPanel();
		leftBottom.setSize("100%", "100%");
		leftBottom.setBorderWidth(1);
		
		left.add(left1);
		left.setCellHeight(left1, "30%");
		left.add(left2);
		left.setCellHeight(left2, "30%");
		left.add(left3);
		left.setCellHeight(left3, "30%");
		left.add(leftBottom);
		left.setCellHeight(leftBottom, "10%");
		
		centerBlock.add(middle);
		centerBlock.setCellWidth(middle, "33%");
		
		VerticalPanel middle1 = new VerticalPanel();
		middle1.setSize("100%", "100%");
		middle1.setBorderWidth(1);
		VerticalPanel middle2 = new VerticalPanel();
		middle2.setSize("100%", "100%");
		middle2.setBorderWidth(1);
		VerticalPanel middle3 = new VerticalPanel();
		middle3.setSize("100%", "100%");
		middle2.setBorderWidth(1);
		VerticalPanel middleBottom = new VerticalPanel();
		middleBottom.setSize("100%" , "100%");
		middleBottom.setBorderWidth(1);
		
		//adding the form to the middle bottom panel
		middleBottom.add(formCompare);
		
		middle.add(middle1);
		middle.setCellHeight(middle1, "30%");
		middle.add(middle2);
		middle.setCellHeight(middle2, "30%");
		middle.add(middle3);
		middle.setCellHeight(middle3, "30%");
		middle.add(middleBottom);
		middle.setCellHeight(middleBottom, "10%");
		
		
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
		VerticalPanel rightBottom = new VerticalPanel();
		rightBottom.setSize("100%", "100%");
		rightBottom.setBorderWidth(1);

		right.add(right1);
		right.setCellHeight(right1, "30%");
		right.add(right2);
		right.setCellHeight(right2, "30%");
		right.add(right3);
		right.setCellHeight(right3, "30%");
		right.add(rightBottom);
		right.setCellHeight(rightBottom, "10%");
		
		initWidget(centerBlock);
		
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
			i1.setTitle("PRODUCT_ID");
			Label i2= new Label(bId);
			i2.setTitle("BRAND_ID");
			Label i3= new Label(bCode);
			i3.setTitle("BRAND_CATEGORY_CODE");
			Label i4= new Label(price);
			i4.setTitle("PRICE");
			Label i5= new Label(bDesc);
			i5.setTitle("BRAND_CATEGORY_DESC");
			Image image = new Image();
			
			//check box
			CheckBox checkBox = new CheckBox();
			checkBox.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent e) {
					boolean checked =  ((CheckBox) e.getSource()).getValue();
					if(checked)
					{
						selected++;
						Hidden itemData = new Hidden();
						itemData.setName(selected.toString());
						String value = "";
						
						VerticalPanel p =  (VerticalPanel)((CheckBox)e.getSource()).getParent();
						Hidden rank = new Hidden();
						rank.setName("RANK");
						rank.setValue(selected.toString());
						p.add(rank);
						
						for(int i=0; i< p.getWidgetCount() ; i++)
						{
							if ( p.getWidget(i) instanceof Label ) 
							{
								Window.alert( ((Label)p.getWidget(i)).getText() + 
										"<-" + ((Label)p.getWidget(i)).getTitle() );
								
								if( (((Label)p.getWidget(i)).getTitle()).compareTo("PRODUCT_ID")==0 )
								{
									value += "PRODUCT_ID=" + ((Label)p.getWidget(i)).getText() +":";
								}
								else if( (((Label)p.getWidget(i)).getTitle()).compareTo("BRAND_CATEGORY_CODE")==0 )
								{
									value += "CODE=" + ((Label)p.getWidget(i)).getText() ;
								}	
							}
						}
						itemData.setValue(value);
						formVPanel.add(itemData);
					}
					else
					{
						Window.alert("Unchecked");
						selected--;
						VerticalPanel p =  (VerticalPanel)((CheckBox)e.getSource()).getParent();
						if ( p.getWidget(p.getWidgetCount() -1) instanceof Hidden )
						{
							Hidden rank = (Hidden)p.getWidget(p.getWidgetCount()-1);
							String r = rank.getValue() ;
							formVPanel.remove(Integer.parseInt(r));
						}
						else 
							Window.alert("Not of type Hidden, need to test ur algo");
					}
					
					//set the number of selected items to be sent when form submitted for compare
					selectedNo.setValue(selected.toString());
				}
			});
			
			image.setUrl("http://localhost/m/images/" + iName );
			image.setSize("130px", "130px");
			
			x.add(image);
			x.add(i1);
			x.add(i3);
			x.add(i2);
			x.add(i4);
			x.add(i5);
			x.add(checkBox);
			
			VerticalPanel www = (VerticalPanel) centerBlock.getWidget(col);
			VerticalPanel www2 = (VerticalPanel) www.getWidget(row);
			www2.add(x);
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
		
		Image compare = new Image();
		compare.setUrl("http://localhost/m/images/details_bt_bg.png");
		middleBottom.add(compare);
		middleBottom.setCellVerticalAlignment(compare, HasVerticalAlignment.ALIGN_MIDDLE);
		middleBottom.setCellHorizontalAlignment(compare, HasHorizontalAlignment.ALIGN_CENTER);
		
		compare.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent e){
				if ( selected <2 ) 
				{
					Window.alert("You have selected less than 2 products");
				}
				formCompare.submit();
			}
		});
		
		formCompare.addSubmitHandler(new SubmitHandler(){
			public void onSubmit(SubmitEvent e){
				Window.alert("submitting to compare");
			}
		});
		
		formCompare.addSubmitCompleteHandler(new SubmitCompleteHandler(){
			public void onSubmitComplete(SubmitCompleteEvent e){
				Window.alert(e.getResults());
			}
		});
		
	}
}