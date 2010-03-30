/**
 * 
 */
package com.example.myProj1.client;

import org.mortbay.jetty.security.Password;

import com.google.gwt.gdata.client.GData;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.*;
import com.google.gwt.user.client.Command; 
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
import com.google.gwt.event.dom.client.ChangeHandler;
//import com.google.gwt.gdata.client.GData;
import com.google.gwt.gdata.client.GDataSystemPackage;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.* ;
import com.google.gwt.user.client.ui.FormPanel.* ;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.Widget;

import com.google.gwt.event.logical.shared.*; 

/**
 * @author roide
 *
 */
public class Register extends Composite {
	
	public static Register createInstance() {
		return new Register();
	}
	
	private FlexTable mainPanel;
	private FormPanel form ;
	private TextBox firstname;
	private Label l_firstname;
	private ListBox pi_state;
	private Label l_pi_state;
	private TextBox pi_city;
	private TextBox email;
	private Label l_email;
	private PasswordTextBox pass;
	private Label l_pass;
	private Label measure;
	private PasswordTextBox repassword;
	private Label l_repassword;
	private TextBox address1;
	private Label l_address1;
	private ListBox shipping_city;
	private Label l_shipping_city;
	private ListBox shipping_state;
	private Label  l_shipping_state;
	private ListBox country;
	private Label l_country;
	private TextBox shipping_pincode;
	private Label l_shipping_pincode;
	private TextBox shipping_contactno;
	private Label l_shipping_contactno;
	private HorizontalPanel strength = new HorizontalPanel();
	
	private final static String EMAIL_VALIDATION_REGEX = 
		"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"; 
    
    
    String[] states_india = { 
    		"Andhra Pradesh", 
    		"Arunachal Pradesh",
    		"Assam",
    		"Bihar",
    		"Chhattisgarh",
    		"Goa",
    		"Gujarat",
    		"Haryana",
    		"Himachal Pradesh",
    		"Jammu and Kashmir",
    		"Jharkhand",
    		"Karnataka",
    		"Kerala",
    		"Madhya Pradesh",
    		"Maharashtra",
    		"Manipur",
    		"Meghalaya",
    		"Mizoram",
    		"Nagaland",
    		"Orissa",
    		"Punjab",
    		"Rajasthan",
    		"Sikkim",
    		"Tamil Nadu",
    		"Tripura",
    		"Uttar Pradesh",
    		"Uttarakhand",
    		"West Bengal",
    		"Andaman and Nicobar Islands",
    		"Chandigarh",
    		"Dadra and Nagar Haveli",
    		"Daman and Diu",
    		"Delhi",
    		"Lakshadweep",
    		"Puducherry" };
    
	public Register() { 
	    mainPanel = new FlexTable();
	    
//	    mainPanel.setCellPadding(4);
//	    mainPanel.setCellSpacing(0);
//	    mainPanel.setBorderWidth(1);
	    initWidget(mainPanel);
	    /* 
	     * Here we load the default package to make AuthSub available.
	     * For AuthSub any of the GData packages will do.
	     * */
	    if (!GData.isLoaded(MyProj1.defaultPackage)) {
	      GData.loadGDataApi(null, new Runnable() {
	        public void run() {
	          startRegistration();
	        }
	      }, MyProj1.defaultPackage);
	    } else {
	      startRegistration();
	    }
	  }
	
	private void startRegistration() {
		
		final FlexTable flexTable = new FlexTable();
	    FlexCellFormatter cellFormatter = flexTable.getFlexCellFormatter();
//	    flexTable.addStyleName("cw-FlexTable");
	    flexTable.setWidth("32em");
	    flexTable.setCellSpacing(4);
	    flexTable.setCellPadding(2);
//	    flexTable.setBorderWidth(1);

	    // Add some text
	    cellFormatter.setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
	    flexTable.setHTML(0, 0, 
	    		"<p style=\"font-size:1pc;border:1px;background-color:#EBDDE2; \">Personal Information </p>");
	    cellFormatter.setColSpan(0, 0, 3);

	    firstname = new TextBox();
	    firstname.setName("firstname");
	    
	    TextBox lastname = new TextBox();
	    lastname.setName("lastname");
	    
	    l_firstname = new Label("Name*");
	    flexTable.setWidget(1, 0, l_firstname );
	    flexTable.setWidget(1, 1, firstname );
	    flexTable.setWidget(1, 2, lastname );
	    
	    flexTable.setWidget(2, 0, new Label("Birthday"));
	    
	    MenuBar date = new MenuBar();
	    date.setAutoOpen(true);
	    date.setAnimationEnabled(true);
	    
	    ListBox month = new ListBox(false);
	    month.setName("bday_month");
	    month.addItem("January");
	    month.addItem("February");
	    month.addItem("March");
	    month.addItem("April");
	    month.addItem("May");
	    month.addItem("June");
	    month.addItem("July");
	    month.addItem("August");
	    month.addItem("September");
	    month.addItem("October");
	    month.addItem("November");
	    month.addItem("December");
	    
	    
	    HorizontalPanel h = new HorizontalPanel();
	    TextBox day = new TextBox();
	    day.setName("bday_day");
	    day.setMaxLength(2);
	    day.setWidth("50px");
	    h.add(day); 
	    h.add(month);
	    TextBox year = new TextBox();
	    year.setName("bday_year");
	    year.setMaxLength(4);
	    year.setWidth("80");
	    h.add(year);
	    
	    flexTable.setWidget(2, 1, h );
	    
	    ListBox sex = new ListBox();
	    sex.setName("sex");
	    sex.addItem("Male", "");
	    sex.addItem("Female", "Female");
	    flexTable.setWidget(3, 0, new Label("Sex"));
	    flexTable.setWidget(3, 1, sex);
	    
	    pi_state = new ListBox();
	    pi_state.setName("state");
	    pi_state.addItem("State");
	    for(int i=0;i<states_india.length; i++) {
	    	pi_state.addItem(states_india[i], states_india[i]);
	    }
	    
	    l_pi_state = new Label("State*");
	    flexTable.setWidget(4, 0, l_pi_state );
	    flexTable.setWidget(4, 1, pi_state);
	    
	    pi_city = new TextBox();
	    pi_city.setName("city");
	    pi_city.addClickHandler(new ClickHandler() {
	    	public void onClick(ClickEvent e) {
	    		pi_city.removeStyleName("defaultText");
	    		pi_city.setText("");
	    		
	    	}
	    });
	    pi_city.setStyleName("defaultText");
	    pi_city.setText("*Enter your city*");
	    flexTable.setWidget(4, 2, pi_city);
	    
	    flexTable.setHTML(5, 0,
	    		"<p style=\"font-size:1pc;border:1px;background-color:#EBDDE2; \">Mallone Related Information </p>");
	    cellFormatter.setColSpan(5, 0, 3);
	    
	    l_email = new Label("Email*");
	    flexTable.setWidget(6, 0, l_email);
	    email = new TextBox();
	    email.setName("email");
	    flexTable.setWidget(6, 1, email);
	
	    pass = new PasswordTextBox();
	    measure = new Label("");
	    measure.setStyleName("measure_background");
	    pass.setName("password");
	    pass.addKeyUpHandler(new KeyUpHandler() {
	    	public void onKeyUp(KeyUpEvent e) {
	    		String str;
	    		str = CheckPasswordStrength(pass.getText());
	    		int max =0;
	    		if(str == PASSWORD_LEVEL_VERYWEAK )
	    			max = 1;
	    		else if(str == PASSWORD_LEVEL_WEAK)
	    			max =2;
	    		else if(str == PASSWORD_LEVEL_MEDIOCRE)
	    			max = 3;
	    		else if(str == PASSWORD_LEVEL_STRONG)
	    			max = 4;
	    		else if (str == PASSWORD_LEVEL_VERYSTRONG)
	    			max =5;
	    		
	    		switch(max){
	    		case 1 : measure.setText("Very Weak"); break;
	    		case 2: measure.setText("Weak");break;
	    		case 3: measure.setText("Medium");break;
	    		case 4: measure.setText("Strong");break;
	    		case 5: measure.setText("Very Strong");break;
	    		default: measure.setText("Empty");break;
	    		}
	    			
	    	}
	    });
	    
	    Label strengthText = new Label("Strength:");
	    strength.add(strengthText);
	    strength.add(measure);
	    
	    l_pass = new Label("Password*");
	    flexTable.setWidget(7, 0, l_pass );
	    flexTable.setWidget(7, 1, pass);
	    flexTable.setWidget(7, 2, strength);
	    
	    l_repassword = new Label("Re-type Password*");
	    flexTable.setWidget(8, 0,l_repassword );
	    repassword = new PasswordTextBox();
	    
	    flexTable.setWidget(8, 1, repassword);
	  
	    flexTable.setHTML(9 , 0, 
	    		"<p style=\"font-size:1pc;border:1px;background-color:#EBDDE2; \">Shipping Address </p>");
	    cellFormatter.setColSpan(9, 0 , 3);
	    
	    l_address1 = new Label("Address1*");
	    flexTable.setWidget(10, 0, l_address1 );
	    address1 = new TextBox();
	    address1.setName("shipping_address1");
	    flexTable.setWidget(10, 1, address1);
	    cellFormatter.setColSpan(10, 1, 2);

	    TextBox address2 = new TextBox();
	    address2.setName("shipping_address2");
	    flexTable.setWidget(11, 0, new Label("Address2"));
	    flexTable.setWidget(11, 1, address2);
	    cellFormatter.setColSpan(11, 1, 2);

	    
	    shipping_city = new ListBox();
	    shipping_city.setName("shipping_city");
	    shipping_city.addItem("City");
	    shipping_city.addItem("New Delhi", "delhi");
	    shipping_city.addItem("Mumbai","mumbai");
	    shipping_city.addItem("Kolkata","kolkata");
	    shipping_city.addItem("Chennai", "chennai");
	    
	    l_shipping_city = new Label("City/Town*");
	    flexTable.setWidget(12, 0, l_shipping_city);
	    shipping_city.setName("shipping_city");
	    flexTable.setWidget(12, 1, shipping_city);
	    
	    l_shipping_state = new Label("State*");
	    flexTable.setWidget(13, 0,l_shipping_state );
	    shipping_state = new ListBox();
	    shipping_state.setName("shipping_state");
	    shipping_state.addItem("State");
	    for(int i=0; i < states_india.length ; i++) {
	    	shipping_state.addItem(states_india[i], states_india[i]);
	    }
	    flexTable.setWidget(13, 1, shipping_state);

	    l_country = new Label("Country");
	    flexTable.setWidget(14, 0, l_country );
	    country = new ListBox();
	    country.setName("shipping_country");
	    country.addItem("India", "India");
	    flexTable.setWidget(14, 1, country);
	    	    
	    shipping_pincode = new TextBox();
	    shipping_pincode.setName("pincode");
	    shipping_pincode.setMaxLength(6);
	    shipping_pincode.setWidth("100px");
	    l_shipping_pincode = new Label("Pincode*");
	    flexTable.setWidget(15, 0, l_shipping_pincode );
	    flexTable.setWidget(15, 1, shipping_pincode);
	    
	    shipping_contactno = new TextBox();
	    shipping_contactno.setName("contactno");
	    shipping_contactno.setText("+91-");
	    shipping_contactno.setWidth("150px");
	    l_shipping_contactno = new Label("Contact No*");
	    flexTable.setWidget(16, 0 , l_shipping_contactno);
	    flexTable.setWidget(16, 1, shipping_contactno);
	    
	    
	    form = new FormPanel();
	    form.add(flexTable);
	    
	    HorizontalPanel panel = new HorizontalPanel();
	    flexTable.setWidget(17, 0 , panel);
	    cellFormatter.setAlignment(17, 0,
	    		HasHorizontalAlignment.ALIGN_CENTER, HasVerticalAlignment.ALIGN_MIDDLE);
	    cellFormatter.setColSpan(17, 0, 3);
	    	    
	    panel.add(new Button("Submit", new ClickHandler() {
	        public void onClick(ClickEvent event) {
	          form.submit();
	        }
	      }));
	    panel.add(new Button("Reset", new ClickHandler() {
	    	public void onClick(ClickEvent e) {
	    		form.reset();
	    		resetErrorLabels();
	    	}
	    }));

	      // Add an event handler to the form.
	      form.addSubmitHandler(new FormPanel.SubmitHandler() {
	        public void onSubmit(SubmitEvent event) {
	          // This event is fired just before the form is submitted. We can take
	          // this opportunity to perform validation.	        	
	        	String errorFields = "Some missing fields are \n" ;
	        	boolean error = false;
	        	resetErrorLabels();
	        	
	        	if(firstname.getText().length() == 0) {
	        		errorFields = errorFields + "First Name is empty \n";
	        		l_firstname.setStyleName("errorField_style");
	        		error = true;
	        	}
	        	if(pi_state.getSelectedIndex() == 0){
	        		errorFields = errorFields + "No State selected \n";
	        		l_pi_state.setStyleName("errorField_style");
	        		error = true;
	        	}
	        	
	        	if (!validEmail() ) {
	        		errorFields = errorFields + "Email Field is empty \n";
	        		l_email.setStyleName("errorField_style");
	        		error = true;
//	        		event.cancel();
	        	}
	        	if(pass.getText().length() < 6 ) {
	        		errorFields = errorFields + "Password is less than 6 characters \n";
	        		l_pass.setStyleName("errorField_style");
	        		error = true;
	        	}
	        	
	        	if(repassword.getText() != pass.getText() ) {
	        		errorFields = errorFields + "Password Fields don't match \n";
	        		l_repassword.setStyleName("errorField_style");
	        		error = true;
	        	}
	        	if(address1.getText().length() == 0 ){
	        		errorFields = errorFields + "Address1 Field is empty \n";
	        		l_address1.setStyleName("errorField_style");
	        		error = true;
	        	}
	        	if(shipping_city.getSelectedIndex() == 0) {
	        		errorFields = errorFields + "Shipping City not selected \n";
	        		l_shipping_city.setStyleName("errorField_style");
	        		error = true;
	        	}
	        	if(shipping_state.getSelectedIndex() == 0 ) {
	        		errorFields = errorFields + "State not Selected \n";
	        		l_shipping_state.setStyleName("errorField_style");
	        		error = true;
	        	}
	        	if(shipping_pincode.getText().length() < 6) {
	        		errorFields = errorFields + "Check Pincode Field\n";
	        		l_shipping_pincode.setStyleName("errorField_style");
	        		error = true;
	        	}
	        	if ( shipping_contactno.getText().length() == 0) {
	        		errorFields = errorFields + "Pincode Field is empty \n";
	        		l_shipping_contactno.setStyleName("errorField_style");
	        		error = true;
	        	}
	        	
	        	if( error == true ){
	        		Window.alert(errorFields);
	        		event.cancel();
	        	}	        
	        }
	      });
	      form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
	        public void onSubmitComplete(SubmitCompleteEvent event) {
	          // When the form submission is successfully completed, this event is
	          // fired. Assuming the service returned a response of type text/html,
	          // we can get the result text here (see the FormPanel documentation for
	          // further explanation).
	        	if(event.getResults() == "success") {
	        		mainPanel.clear();
	        		DecoratorPanel dPanel = new DecoratorPanel();
	        		AbsolutePanel aPanel = new AbsolutePanel();
	        		Grid grid = new Grid(1,1);
					grid.getCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
					grid.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);

	        		aPanel.setSize("540px", "250px");
					HTML success = new HTML();
					success.setHTML("<p style=\"font-size:2pc; \"> Thank you :-) </p>");
					success.setPixelSize(400, 200);
					
					grid.setWidget(0, 0, success);
	//			    grid.setBorderWidth(1);
					aPanel.add(new Label("Would you like to invite your friends over."), 130, 70);
					
					Button gmail = new Button();
					gmail.setText("Gmail!");
					gmail.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent e) {
							Widget w = mainPanel.getParent().getParent();
							DecoratedStackPanel s = (DecoratedStackPanel) w;
							s.showStack(1);
						}
					});
					Button yahoo = new Button();
					yahoo.setText("Yahoo!");
					yahoo.setEnabled(false);
					yahoo.addClickHandler(new ClickHandler() {
						public void onClick(ClickEvent e) {
							Widget w = mainPanel.getParent().getParent();
							DecoratedStackPanel s = (DecoratedStackPanel) w;
							s.showStack(2);
						}
					});
				
				    aPanel.add(grid, 40 ,5);
				    aPanel.add(gmail,140, 120 );
				    aPanel.add(yahoo, 250, 120);
					dPanel.setWidget(aPanel);
					mainPanel.setWidget(0,0,dPanel);

	        	}
	          Window.alert(event.getResults());
	        }
	      });

	    form.setAction("formtest.php");
	    form.setMethod(FormPanel.METHOD_POST);
	    mainPanel.setWidget(0, 0, form);
	}
	private boolean validEmail() {
		if ( email.getText().matches(EMAIL_VALIDATION_REGEX) ) {
			return true;
		}
		return false;
	}
	
	private void resetErrorLabels() {
   
    		l_firstname.removeStyleName("errorField_style");
    		l_pi_state.removeStyleName("errorField_style");
        	l_email.removeStyleName("errorField_style");
        	l_pass.removeStyleName("errorField_style");
        	l_repassword.removeStyleName("errorField_style");
        	l_address1.removeStyleName("errorField_style");
        	l_shipping_city.removeStyleName("errorField_style");
        	l_shipping_state.removeStyleName("errorField_style");
        	l_shipping_pincode.removeStyleName("errorField_style");
        	l_shipping_contactno.removeStyleName("errorField_style");

	}
	 public static final String PASSWORD_LEVEL_VERYWEAK = "VW";
     public static final String PASSWORD_LEVEL_WEAK = "W";
     public static final String PASSWORD_LEVEL_MEDIOCRE = "M";
     public static final String PASSWORD_LEVEL_STRONG = "S";
     public static final String PASSWORD_LEVEL_VERYSTRONG = "VS";
     /**
      * Derived  from :
      *              Jim Sloey - jsl...@justwild.us PasswordCheck.Java
      *              which were derived from Steve Moitozo's passwdmeter
      *              See http://www.geekwisdom.com/dyn/passwdmeter
      */
     private static String CheckPasswordStrength(String passwd) {
//    	 		Window.alert(passwd);
             boolean upper = false, lower = false, numbers = false, special =false;
             Integer intScore = 0;
             int length = 0;
             if (passwd == null)
                     return "Empty";
             // PASSWORD LENGTH
             length = passwd.length();
             if (length < 5) // length 4 or less
                     intScore = (intScore + 3);
             else if (length > 4 && passwd.length() < 8) // length between 5 and 7
            	 intScore = (intScore + 6);
             else if (length > 7 && passwd.length() < 16) // length between 8 and 15
                     intScore = (intScore + 12);
             else if (length > 15) // length 16 or more
                     intScore = (intScore + 18);
    
            // LETTERS
             if (passwd.matches(".*[a-z]+.*")) {
                     intScore = (intScore + 1);
                     lower=true;
             }
             if (passwd.matches(".*[A-Z]+.*")){
                     intScore = (intScore + 5);
                     upper=true;
             }

             // NUMBERS
             if (passwd.matches(".*[0-9]+.*")){
                     intScore = (intScore + 5);
                     numbers=true;
             }
             if (passwd.matches(".*[0-9].*[0-9].*[0-9].*"))
                     intScore = (intScore + 3);

             // SPECIAL CHAR
             if (passwd.matches(".*[:,!,@,#,$,%,^,&,*,?,_,~]+.*")){
                     intScore = (intScore + 5);
                     special=true;
             }

             if (passwd.matches(".*[:,!,@,#,$,%,^,&,*,?,_,~].*[:,!,@,#,$,%,^,&,*,?,_,~].*"))
                     intScore = (intScore + 3);

             // COMBOS
             if (upper && lower ) // [verified] both upper and lower case
                     intScore = (intScore + 2);

             if ((upper || lower ) && numbers ) // [verified] both letters and numbers
                     intScore = (intScore + 2);

             if ((upper || lower ) && numbers && special) // [verified] letters, numbers, and special characters
                     intScore = (intScore + 2);
             
             if (upper  && lower  && numbers  && special ) // [verified] upper, loweer, numbers, and special characters
                     intScore = (intScore + 2);
             
//             Window.alert(intScore.toString());
             if (intScore <= 10)
                     return PASSWORD_LEVEL_VERYWEAK;
             else if (intScore > 10 && intScore <= 15)
                     return PASSWORD_LEVEL_WEAK;
             else if (intScore > 15 && intScore <=23)
                     return PASSWORD_LEVEL_MEDIOCRE;
             else if (intScore > 23 && intScore < 40)
                     return PASSWORD_LEVEL_STRONG;
             else
                     return PASSWORD_LEVEL_VERYSTRONG;
     } 
}
