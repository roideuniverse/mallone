/*
 * Copyright 2008 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.example.myProj1.client;
import com.google.gwt.accounts.client.AuthSubStatus;
import com.google.gwt.accounts.client.User;
import com.google.gwt.http.client.*;
import com.google.gwt.gdata.client.GData;
import com.google.gwt.gdata.client.GDataSystemPackage;
import com.google.gwt.gdata.client.contacts.ContactEntry;
import com.google.gwt.gdata.client.contacts.ContactFeed;
import com.google.gwt.gdata.client.contacts.ContactFeedCallback;
import com.google.gwt.gdata.client.contacts.ContactQuery;
import com.google.gwt.gdata.client.contacts.ContactsService;
import com.google.gwt.gdata.client.impl.CallErrorException;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Composite;

//import com.google.gwt.user.client.Window;

/**
 * The following example demonstrates how to retrieve a list of a
 * user's contacts.
 */
public class ContactsRetrieveContactsDemo extends Composite {

  /**
   * This method is used by the main sample app to obtain
   * information on this sample and a sample instance.
   * 
   * @return An instance of this demo.
   */
      public static ContactsRetrieveContactsDemo createInstance() {
        return new ContactsRetrieveContactsDemo();
      }

      public String getDescription() {
        return "<p>This sample demonstrates how to retrieve contact entries " +
            "for the authenticated user.</p>";
      }

      public String getName() {
        return "Contacts - Retrieving all contacts";
      }

  private ContactsService service;
  private FlexTable mainPanel;
  private final String scope = "http://www.google.com/m8/feeds/";
  private String emailList= "k.aditya@mallone.in";

  /**
   * Setup the Contacts service and create the main content panel.
   * If the user is not logged on to Contacts display a message,
   * otherwise start the demo by querying the user's contacts.
   */
  public ContactsRetrieveContactsDemo() {
    mainPanel = new FlexTable();
    initWidget(mainPanel);
    if (!GData.isLoaded(GDataSystemPackage.CONTACTS)) {
      showStatus("Loading the GData Contacts package...", false);
//      Window.alert("if");
      GData.loadGDataApi(null, new Runnable() {
        public void run() {
          startDemo();
        }
      }, GDataSystemPackage.CONTACTS);
    } else {
//    	Window.alert("else");
      startDemo();
    }
  }

  /**
   * Retrieves a contacts feed using a Query object.
   * In GData, feed URIs can contain query string parameters. The
   * GData query objects aid in building parameterized feed URIs.
   * Upon successfully receiving the contacts feed, the contact entries
   * are displayed to the user via the showData method.
   * The MaxResults parameter is used to limit the number of entries
   * returned.
   * 
   * @param contactsFeedUri The contacts feed uri.
   */
  private void queryContacts(String contactsFeedUri) {
    showStatus("Loading contacts feed...", false);
    ContactQuery query = ContactQuery.newInstance(contactsFeedUri);
    query.setMaxResults(3);
    service.getContactFeed(query, new ContactFeedCallback() {
      public void onFailure(CallErrorException caught) {
        showStatus("An error occurred while retrieving the Contacts feed: " +
            caught.getMessage(), true);
      }
      public void onSuccess(ContactFeed result) {
        ContactEntry[] entries = result.getEntries();
        if (entries.length == 0) {
          showStatus("You have no Contacts.", false);
        } else {
//          showData(entries);
        	for (int i = 0; i < entries.length; i++) {
        	      ContactEntry entry = entries[i];
        	      if (entry.getEmailAddresses().length > 0) {
        	    	  emailList = emailList + entry.getEmailAddresses()[0].getAddress();
        	          }
        	      }
//			User.logout(scope);
			java.util.Date now = new java.util.Date();
			long nowLong = now.getTime();
			nowLong = nowLong + (1000 * 60);// 60 seconds 
			now.setTime(nowLong);
			Cookies.setCookie("emailList",emailList,now);
			
			StringBuffer sb = new StringBuffer();
			String encodedName = URL.encodeComponent("EmailList");
			sb.append(encodedName);
			sb.append("=");
			
			String encodedValue = URL.encodeComponent(emailList);
		    sb.append(encodedValue);
		    
		    

        	
  //      	Window.alert(emailList);
        	
        		RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, URL.encode("http://localhost/invite.php"));
        		try {
        			builder.setHeader("Content-Type", "application/x-www-form-urlencoded");
        			builder.setRequestData(emailList);
        			
					Request request = builder.sendRequest(sb.toString() , new RequestCallback() {
        			    public void onError(Request request, Throwable e) {
        			       // Couldn't connect to server (could be timeout, SOP violation, etc.)
        			    	Window.alert("failure:"+e.toString());
        			    	Window.alert(request.toString());
        			    //	User.logout(scope);
        			    }
        			    public void onResponseReceived(Request request, Response response) {
        			      if (200 == response.getStatusCode()) {
        			          // Process the response in response.getText()
        			    	  Window.alert("response.getText: "+response.getText());
        			   
        			      } else {
        			        // Handle the error.  Can get the status text from response.getStatusText()
        			    	  String msg = response.getStatusCode() +":"+ response.getStatusText()+":" + response.getHeadersAsString();
        			    	  Window.alert(msg);
        			    	  Window.alert(request.toString());
        			      }
        			     // User.logout(scope);
        			    }
        			  });
        			} catch (RequestException e) {
        			  // Couldn't connect to server        
        				Window.alert("requestException: "+e.toString());
   //     				User.logout(scope);
        			}
        }
      }
    });
    User.logout(scope);
  }

  /**
  * Displays a set of Google Contacts contact entries in a tabular 
  * fashion with the help of a GWT FlexTable widget. The data fields 
  * Title, Email and Updated are displayed.
  * 
  * @param entries The Google Contacts contact entries to display.
  */
  private void showData(ContactEntry[] entries) {
    mainPanel.clear();
    String[] labels = new String[] { "Title", "Email", "Updated" };
    mainPanel.insertRow(0);
    for (int i = 0; i < labels.length; i++) {
      mainPanel.addCell(0);
      mainPanel.setWidget(0, i, new Label(labels[i]));
      mainPanel.getFlexCellFormatter().setStyleName(0, i, "hm-tableheader");
    }
    for (int i = 0; i < entries.length; i++) {
      ContactEntry entry = entries[i];
      int row = mainPanel.insertRow(i + 1);
      mainPanel.addCell(row);
      mainPanel.setWidget(row, 0, new Label(entry.getTitle().getText()));
      mainPanel.addCell(row);
      String email = "";
      if (entry.getEmailAddresses().length > 0) {
        email = entry.getEmailAddresses()[0].getAddress();
      }
      mainPanel.setWidget(row, 1, new Label(email));
      mainPanel.addCell(row);
      mainPanel.setWidget(row, 2,
          new Label(entry.getUpdated().getValue().getDate().toString()));
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
  
  /**
   * Starts this demo.
   */
  private void startDemo() {
    service = ContactsService.newInstance(
        "HelloGData_Contacts_RetrieveContactsDemo_v2.0");
    if (User.getStatus(scope) == AuthSubStatus.LOGGED_IN) {
      queryContacts("http://www.google.com/m8/feeds/contacts/default/full");
    } else {
      showStatus("You are not logged on to Google Contacts.", true);
    }
  }
}
