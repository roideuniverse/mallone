package com.mallone.displayproducts.client;

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

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class DisplayProductsMallone implements EntryPoint {
	
//		private VerticalPanel x;
		private VerticalPanel formProductBrand ;
	    private ListBox productList;
		private FormPanel productForm;
		private FormPanel brandForm;
		private ListBox brandsList;
		private String[] brand_names ;
		private Integer[] brand_id;
		private VerticalPanel mainPanel;
		private ListBox productNameSelected ;
		
		Integer IndexProduct=0;
		Integer IndexBrand= -1;
		
		String[] products = {
				"Air Cooler",
				"Aircondition",

				"Aircondition(window)",
				"Audio System",
				"Camcorder",
				"Clothes Dryer",
				"Cooking Range", 

				"DVD player",
				"Digi camera",
				"Digital Projector",
		        "Dishwasher",
		        "Electric Chimney",

		        "Fax Machine",
		        "Food Processor",
		        "Juicer/Mixer/Grinder",
		        "Laptops",
		        "MP3 Player",

		        "Microwave",
		        "Mobile phones",
		        "Monitor",
		        "Multi Fuctional Devices",
		        "Netbook",
		        "Oven Toaster and griller",
		        "PDA", 
		        "Printer(Dot Matrix)",
		        "Printer(Inkjet)",
		        "Printer(Laser)",

		        "Refrigerator",
		        "Scanner",
		        "Television",
		        "Vaccum Cleaner",
		        "Washing Machine",
		        "Water Purifier"
		};
		
		

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		VerticalPanel x = new VerticalPanel();
		
		productForm = new FormPanel();
		productForm.setHeight("20px");
		productForm.setMethod(FormPanel.METHOD_GET);
		productForm.setAction("php/returnBrandList.php");
		//MenuBar productsList = new MenuBar(true);
		productList = new ListBox();
		productList.setName("Product");
		
		productList.addItem("Products", "0" );
		Integer i;
		for( i=1; i <=33 ; i++) 
		{
			productList.addItem(products[i-1], i.toString() );
		}
        
        productList.setWidth("100px");
        
 
        
        brandsList = new ListBox();
        brandsList.setName("Brands");
        brandsList.setWidth("100px");
        brandsList.addItem("Brands","0");
 
        //BRAND FORM
        brandForm = new FormPanel();
        brandForm.setMethod(FormPanel.METHOD_POST);
        brandForm.setAction("php/getData.php");
        
        
        formProductBrand = new VerticalPanel();
        formProductBrand.add(brandsList);
        
        productForm.add(productList);
        brandForm.add(formProductBrand);
        
        x.add(productForm);
        x.add(brandForm);        

        
        brandsList.addClickHandler(new ClickHandler() {
        	public void onClick(ClickEvent e ) {
 //       		Window.alert("brand clicked");
				if ( brandsList.getSelectedIndex() != IndexBrand) 
				{
					IndexBrand = brandsList.getSelectedIndex();			
					Window.alert("calling brandform submit");
					productNameSelected = new ListBox();
					productNameSelected.setVisible(false);
					productNameSelected.setName("Product");
					productNameSelected.addItem("product", IndexProduct.toString());
					formProductBrand.add(productNameSelected);
					brandForm.submit();
					
				}
				else 
					IndexBrand = 0;
        	}
        });

        brandForm.addSubmitHandler(new FormPanel.SubmitHandler() {
        	public void onSubmit(SubmitEvent e) {
        		if(IndexProduct == 0)
        		{
        		//	Window.alert("You have not selected a product");
        		//	e.cancel(); uncomment this later on
        		}
        	Window.alert("submit of brands");
        	
        	}
        });
        
        brandForm.addSubmitCompleteHandler( new FormPanel.SubmitCompleteHandler() {
        	public void onSubmitComplete(SubmitCompleteEvent e){
        		Window.alert("brandFormSubmit output=" + e.getResults());
        		
        		brandForm.remove(productNameSelected);
        		mainPanel = new VerticalPanel();
        		mainPanel.setSize("100%", "100%");
        		mainPanel.setHeight("100%");
        		mainPanel.setBorderWidth(1);
        		CenterDisplay c = CenterDisplay.createInstance(e.getResults()) ;
        		
        		mainPanel.add(c);
        		RootPanel.get("_center_display_").add(mainPanel);
        	}
        });

        productList.addClickHandler(new ClickHandler() {
        	public void onClick(ClickEvent e ) {
//        		Window.alert("ProductList clicked");
				if ( productList.getSelectedIndex() != IndexProduct) 
				{
					IndexProduct = productList.getSelectedIndex();
					productForm.submit();
				}
				else 
					IndexProduct = 0;
        	}
        });
        
        productForm.addSubmitHandler( new FormPanel.SubmitHandler() {
        	public void onSubmit(SubmitEvent event) {
    //    		Window.alert("sumit of product");
        		int brandListcount = brandsList.getItemCount();
        		for( int it=1 ; it < brandListcount ; it++ )
        		{
        			brandsList.removeItem(it);
        		}
        	}
        });
        

        productForm.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
            public void onSubmitComplete(SubmitCompleteEvent event) {
                // When the form submission is successfully completed, this event is
                // fired. Assuming the service returned a response of type text/html,
                // we can get the result text here (see the FormPanel documentation for
                // further explanation).
            	
            	
                Window.alert(" from submit complete \n" +event.getResults() );
                String[] results = event.getResults().split(";");
                Integer test = (results.length) / 2;
                
                Window.alert(test.toString() );
  //              String msg = ":P\n";
                brand_names = new String[test] ;
                brand_id = new Integer[test];
                
                for(int i=0; i < test ; i++)
                {
                	String[] var = results[i].split(":");
                	brand_names[i] = var[1];
                	brand_id[i] = Integer.parseInt( var[0] ) ;
               	
 //               	msg= msg + brand_names[i] + "=" + brand_id[i].toString() + ":::::";
                }
                
                int pos=0;
                for( String s : brand_names )
                {
                	Window.alert("adding-" + s + brand_id[pos].toString() );
                	brandsList.addItem(s, brand_id[pos].toString() ) ;
                	pos++;
                }
 //               Window.alert(msg ); 
                
                
                
              } 
            });
        
        
        RootPanel.get("_product_brands_buttons_").add(x);

	}
}
