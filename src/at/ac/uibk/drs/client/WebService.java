package at.ac.uibk.drs.client;


/**
 * @author Daniel Djindjic Krstic
 */


/**
 * 
 */

/**
 * 
 */
/*
 * 
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;











import org.json.JSONObject;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;


/**
 * Notizen :	Anmeldung mit "Admin" l�sst das Admin interface erscheinen
 * 
 * 
 * bsp anmelde daten : 
 * User : 		passenger@ride4earth.example
 * Password:	Test1234
 */
/*
 * des mit post get 
 * 
 * http://gizmoworks.wordpress.com/2007/05/15/post-with-gwt/
 * https://www.google.com/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8#q=gwt%20post%20to%20server
 * https://groups.google.com/forum/#!topic/google-web-toolkit/gZ4_QMuXaSQ
 * 
 * 
 */

/* GENERAL INFORMATION
 * known Bugs : 
 * 
 * not yet implemented :
 * 	
 * still to do : - 
 * 				 
 */

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class WebService implements EntryPoint {
	

	//	TODO : Multiple Pages :
	//	http://comments.gmane.org/gmane.org.google.gwt/35289
	
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	//geh�rt evtl in onModuleLoad()
	private ArrayList<String> user = new ArrayList<String>();
	private ArrayList<String> password = new ArrayList<String>();
	static Boolean login = false;			//TODO remove later -> for testing purpose
	
	
	final Button sendButton = new Button("Anmelden");
	final TextBox nameField = new TextBox();
	final PasswordTextBox pwField = new PasswordTextBox();
	Label teamdrs = new Label();
	Label teamdrsx = new Label();
	Label teamdrs2 = new Label();	//f�r Home panel -> mit eigene css daten
	Label carLabel = new Label();
	Label oebbLabel = new Label();
	Label bycycleLabel = new Label();
	Label vorname = new Label();
	final TextBox vorname2 = new TextBox();
	Label nachname = new Label();
	final TextBox nachname2 = new TextBox();
	final Button changePassword = new Button("Passwort aendern");	//�ffnet panel : 2 mal alte eingeben, + 1 mal neues
	final Button savePassword = new Button("Passwort speichern");
	final Button favoritenListe = new Button("Favoriten Liste aendern");
	//hier brauchst du noch Favoritenliste
	final Button saveData = new Button("Daten speichern");

	Label nameField2 = new Label();
	Label pwField2 = new Label();
	
	
	Label readOnlyTextBox = new Label();	//HOME
	Label readOnlyTextBox2 = new Label();	//PUNKTESTAND
	Label readOnlyTextBox3 = new Label();	//GRUPPENVERWALTUNG
	Label readOnlyTextBox4 = new Label();	//ABMELDEN

	Label readOnlyTextBox2v2 = new Label();	//ADMINVERWALTUNG
	Label readOnlyTextBox3v2 = new Label();	//GRUPPENVERWALTUNG
	Label readOnlyTextBox4v2 = new Label();	//ABMELDEN
	
	
	
	
	private static Label errorMsgLabel = new Label();
	Label errorLabel = new Label(); 
	
	RootPanel rootPanel = RootPanel.get("header");
	static VerticalPanel verticalPanel = new VerticalPanel();	//static ge�ndert weg Serv.Comm.
	HorizontalPanel horizontalPanel = new HorizontalPanel();
	HorizontalPanel horizontalPanelMaps = new HorizontalPanel();
	VerticalPanel verticalPanelDat = new VerticalPanel();
	HorizontalPanel horizontalPanelDatVerwalten = new HorizontalPanel();
	HorizontalPanel horizontalPanelDatVerwalten2 = new HorizontalPanel();
	VerticalPanel verticalPanelDatVerwalten = new VerticalPanel();
	VerticalPanel verticalPanelDatVerwalten2 = new VerticalPanel();
	VerticalPanel verticalPanelDatVerwalten3 = new VerticalPanel();
	VerticalPanel verticalPanelDatVerwalten4 = new VerticalPanel();
	VerticalPanel verticalPanelDatVerwalten5 = new VerticalPanel();
	VerticalPanel verticalPanelDrs = new VerticalPanel();
	VerticalPanel verticalPanel2 = new VerticalPanel();
	HorizontalPanel horizontalPanely = new HorizontalPanel();	//Passwort : [    ]
	HorizontalPanel horizontalPanelx = new HorizontalPanel();	//send button
	HorizontalPanel horizontalPanel2 = new HorizontalPanel();	//Home , Punkte, ...
	HorizontalPanel horizontalPanelAdmin = new HorizontalPanel();
	static VerticalPanel verticalPanel3 = new VerticalPanel();			//ADMIN PANEL
	
	
	//public LoginData2 logindata2 = getFirstLogin(); //schmei�t error
	
	//LoginResourceProxy loginResource = GWT.create(LoginResourceProxy.class); // mit dem ist gui unsichtbar


	
	
	
	/**
	 *
	 *
	 * This is the entry point method.
	 * 
	 * 
	 */
	
	@Override
	public void onModuleLoad() {
		
		//name adresse, zum klicken : hat fahrrad, �bbticket, auto, 
		
		
		//LoginData2 logindata2 = getFirstLogin();
		
		
		teamdrs.setText("TEAM DRS");
		teamdrs.setStyleName("teamdrs");
		teamdrsx.setText("TEAM DRS");
		teamdrsx.setStyleName("teamdrs3");
		teamdrs2.setText("TEAM DRS");
		teamdrs2.setStyleName("teamdrs2");
		nameField.setText("User");
		nameField.setStyleName("passwortbox");
		nameField2.setText("Benutzername :");
		nameField2.addStyleName("passwortbox5");
		pwField.setText("Password");
		pwField.setStyleName("passwortbox4");
		pwField2.setText("Passwort :");
		pwField2.addStyleName("passwortbox3");
		
		
		vorname.setText("Vorname :");
		vorname.setStyleName("text");
		nachname.setText("Nachname :");
		nachname.setStyleName("text");
		
		carLabel.setText("Auto : ");
		carLabel.setStyleName("text");
		oebbLabel.setText("Oebb : ");
		oebbLabel.setStyleName("text");
		bycycleLabel.setText("Fahrrad : ");
		bycycleLabel.setStyleName("text");
		
		
	    readOnlyTextBox.setText("HOME");
	    readOnlyTextBox.setStyleName("horizontalmainpanel");
	    //readOnlyTextBox2.setText("PUNKTESTAND");
	    //readOnlyTextBox2.setStyleName("horizontalmainpanel");
	    readOnlyTextBox3.setText("DATEN VERWALTEN");
	    readOnlyTextBox3.setStyleName("horizontalmainpanel");
	    readOnlyTextBox4.setText("ABMELDEN");
	    readOnlyTextBox4.setStyleName("horizontalmainpanel");
	    
	    
	    vorname2.setStyleName("format");
	    nachname2.setStyleName("format");
	    
	    //CheckBox car = new CheckBox("car");
	    CheckBox car = new CheckBox();
	    car.setValue(false);
	    car.setStyleName("format2");
	    
	    //CheckBox oebb = new CheckBox("oebb");
	    CheckBox oebb = new CheckBox();
	    oebb.setValue(false);
	    oebb.setStyleName("format2");
	    
	    //CheckBox bycycle = new CheckBox("bycycle");
	    CheckBox bycycle = new CheckBox();
	    bycycle.setValue(false);
	    bycycle.setStyleName("format2");
	    
	    saveData.setStyleName("loginbutton");
	    changePassword.setStyleName("smallbutton");
	    savePassword.setStyleName("smallbutton");
	    favoritenListe.setStyleName("smallbutton");
	    
	    
	    verticalPanelDatVerwalten.add(vorname);
	    verticalPanelDatVerwalten.add(nachname);
	    
	    verticalPanelDatVerwalten2.add(vorname2);
	    verticalPanelDatVerwalten2.add(nachname2);
	    
	    
	    verticalPanelDatVerwalten3.add(carLabel);
	    verticalPanelDatVerwalten3.add(oebbLabel);
	    verticalPanelDatVerwalten3.add(bycycleLabel);
	    
	    verticalPanelDatVerwalten4.add(car);
	    verticalPanelDatVerwalten4.add(oebb);
	    verticalPanelDatVerwalten4.add(bycycle);
	    
	    verticalPanelDatVerwalten5.add(changePassword);
	    verticalPanelDatVerwalten5.add(favoritenListe);
	    
	    /*
	    //TODO anstatt Labels f�r liste zum verwenden verwends Grid wen daten von DB holsch
	    gesamtrankingGrid.add(grName);
	    gesamtrankingGrid.add(grPunkte);
	    gesamtrankingGrid.add(grPlatz);
	    */
	    
		//styles
		sendButton.setStyleName("loginbutton");

		
		horizontalPanel.setStyleName("menuPanel");
		horizontalPanel2.setStyleName("menuPanel");
		horizontalPanelAdmin.setStyleName("menuPanel");
		
		
		verticalPanel.setStyleName("borderlogin");
		
		
		/*	//FOR TESTING
		verticalPanelDatVerwalten.setStyleName("bordertest");
		verticalPanelDatVerwalten2.setStyleName("bordertest");
		verticalPanelDatVerwalten3.setStyleName("bordertest");
		verticalPanelDatVerwalten4.setStyleName("bordertest");
		verticalPanelDatVerwalten5.setStyleName("bordertest");
		*/
		
		verticalPanelDat.setStyleName("bordertest");
		/*
		horizontalPanelDatVerwalten.setStyleName("bordertest");
		horizontalPanelDatVerwalten2.setStyleName("bordertest");
		*/
		
		
		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		
		errorMsgLabel.setVisible(false);
		verticalPanel3.setVisible(false);
		horizontalPanelAdmin.setVisible(false);
		verticalPanelDat.setVisible(false);
		verticalPanel2.setVisible(false);
		
		
		verticalPanel.setWidth("100%");
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel3.setWidth("100%");
		verticalPanel3.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		
		//verticalPanelDatVerwalten.add();
		
		//verticalPanel2.add(ride4Earth2);
		verticalPanel.add(horizontalPanel);
		verticalPanel.add(horizontalPanely);
		verticalPanel.add(horizontalPanelx);
		
		horizontalPanelDatVerwalten.add(verticalPanelDatVerwalten);
		horizontalPanelDatVerwalten.add(verticalPanelDatVerwalten2);
		horizontalPanelDatVerwalten.add(verticalPanelDatVerwalten3);
		horizontalPanelDatVerwalten.add(verticalPanelDatVerwalten4);
		horizontalPanelDatVerwalten.add(verticalPanelDatVerwalten5);
		
		horizontalPanelDatVerwalten2.add(saveData);
		
		
		horizontalPanel.add(nameField2);
		horizontalPanel.add(nameField);
		horizontalPanely.add(pwField2);
		horizontalPanely.add(pwField);
		horizontalPanelx.add(sendButton);
		
		verticalPanelDrs.add(teamdrsx);
		verticalPanel2.add(teamdrs);		//title that appears on the top of the screen
		verticalPanel2.add(horizontalPanel2);
		verticalPanel2.add(horizontalPanelAdmin);
		
		
		//horizontalPanel2.add(r4eImage);
		horizontalPanel2.add(readOnlyTextBox);
		horizontalPanel2.add(readOnlyTextBox2);
		horizontalPanel2.add(readOnlyTextBox3);
		horizontalPanel2.add(readOnlyTextBox4);
		
		//horizontalPanelAdmin.add(r4eImage2);
		horizontalPanelAdmin.add(readOnlyTextBox2v2);
		horizontalPanelAdmin.add(readOnlyTextBox3v2);
		horizontalPanelAdmin.add(readOnlyTextBox4v2);
		
		verticalPanelDat.add(horizontalPanelDatVerwalten);
		verticalPanelDat.add(horizontalPanelDatVerwalten2);
		
		
		/*
		 * Root Panel 
		 */
		
		

		rootPanel.add(verticalPanelDrs);
		rootPanel.add(verticalPanel2);
		rootPanel.add(verticalPanel);
		rootPanel.add(verticalPanelDat);
			
		//rootPanel.add(horizontalPanel);
	
		
		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();
		
		
		
		/**
		 * 
		 *  ACTION HANDLERS
		 * 
		 */
		
		
		// Listen for mouse events on the Add button. login Button
		sendButton.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						addData();
						}
				});
		
		
		// HOME Button
		readOnlyTextBox.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						home();
						}
				});
		
		// DATEN VERWALTEN Button
		readOnlyTextBox3.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						datenVerwalten();
						}
				});
		
		// ABMELDEN Button
		readOnlyTextBox4.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						abmelden();
						}
				});

				// Listen for keyboard events in the input box.
		pwField.addKeyPressHandler(new KeyPressHandler() {
					@Override
					public void onKeyPress(KeyPressEvent event) {
						if (event.getCharCode() == KeyCodes.KEY_ENTER) {
							addData();
						}
					}
				});
		
				
		
		/**
		 * 
		 * 
		 * 	METHODS CALLED IN THE ACTION HANDLERS
		 * 
		 * 
		 */
		
	}
	
	private void abmelden(){
		verticalPanel.setVisible(true);
		verticalPanel2.setVisible(false);
		verticalPanelDat.setVisible(false);
		verticalPanelDrs.setVisible(true);
	}
	
	private void home(){
		verticalPanel2.setVisible(true);
		verticalPanel.setVisible(false);
		verticalPanelDat.setVisible(false);
		verticalPanelDrs.setVisible(false);
	}
	
	private void datenVerwalten(){
		verticalPanel2.setVisible(true);
		verticalPanel.setVisible(false);
		verticalPanelDat.setVisible(true);
		verticalPanelDrs.setVisible(false);
		
	}
	
		private void addData() {
			
			
			//bei beiden war .getText().toUpperCase().trim();
			final String symbol1 = nameField.getText().trim();
			nameField.setFocus(true);
			final String symbol2 = pwField.getText().trim();
			
			
			// code must be between 1 and 10 chars that are numbers, letters,
			// or dots.
			
			
			if (!symbol1.matches("^[0-9a-zA-Z\\.]{1,10}$")) {
				Window.alert("'" + symbol1 + "' is not a valid symbol.");
				nameField.selectAll();
				return;
			}

			if (!symbol2.matches("^[0-9a-zA-Z\\.]{1,10}$")) {
				Window.alert("'" + symbol2 + "' is not a valid symbol.");
				nameField.selectAll();
				return;
			}
			
			nameField.setText("");

			// Don't add the user if he�s already in the table.	
			//TODO hier die liste aller user holen um dann zu schauen ob er drinn ist
			/*
			if (user.contains(symbol1))
				return;
			 */
			// Add the user and password to table 	
			
			user.add(symbol1);
			String encryptedPassword = encryptPasswordReceivedFromLoginPage(symbol2);
			password.add(encryptedPassword);

			
			//description : http://stackoverflow.com/questions/10970834/cannot-make-a-static-reference-to-the-non-static-method-acceptaddstring-from-t
			
			//logindata.setUsername(symbol1);
			//logindata.setUserpassword(symbol2);
			
			String url = "http://c703-w65220.uibk.ac.at/api/greeting";
			//String url = "http://c703-w65220.uibk.ac.at/api/testAuth/roleUser";
			
			//TODO schaffs von und in json zum wandla
			
			
			//String postData="bla";
			//"{"username":"Daniel","userpassword":"passwort","permission":null}"
			
			//System.out.println(postData);
			//String postDataq = ToJson.ToJsonLogin();	// -> in der klasse schmeist es einene error
			//String postData = ToJsonLogin.main(null);
					
			//System.out.println(postData);
			
			//ServerCommunication.POSTLogin(url, postData);	
			
			errorMsgLabel.setVisible(false);
			
			String adminstring = "Admin";
			String benutzerstring = "Benutzer";	//TODO remove later , for testing
			/*if(adminstring.equals(logindata.getUsername())==true){
				login=true;
				//ToJson.ToJsonLogin();	//schemi�t error -> falsche json parser
				horizontalPanel2.setVisible(false);
				horizontalPanelAdmin.setVisible(true);
				Ride4Earth_Web.verticalPanel.setVisible(false);
        		Ride4Earth_Web.verticalPanel3.setVisible(true);
			}else if(benutzerstring.equals(logindata.getUsername())==true){	//for testing , remove later
				login=true;
				Ride4Earth_Web.home();
				
			}else{
				
			/**
			 * hier gehts bei servercomm weiter ! -> nur wegen testzwecken //TODO	
			 */
				//ServerCommunication.GETLogin(url);
				//Ride4Earth_Web.home();	f�r testzwecke
			
				
			verticalPanel.setVisible(false);
			verticalPanelDrs.setVisible(false);
			verticalPanel2.setVisible(true);
			
			}
		
		
		/**
		 * 
		 * 
		 * Passwordencription and other methods
		 * 
		 * 
		 */
		
		
		// DESCRIPTION : http://www.quicklyjava.com/java-web-application-security/

		public String encryptPasswordReceivedFromLoginPage(String unecryptedPassword) {
			String salt = "RandomStringForExtraSecurity@#$!%^&*(*)1234567890";
			MessageDigest messageDigest = null;
			try {
				messageDigest = MessageDigest.getInstance("MD5");
				messageDigest.update((unecryptedPassword + salt).getBytes());
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			return (new BigInteger(messageDigest.digest())).toString(16);

		}
		
		public static void displayError(String error){
			errorMsgLabel.setText("Error: " + error);
			errorMsgLabel.setVisible(true);
		}
		
		public void setLabelText(Label label, String text){
			label.setText(text);
		}
		
		public String getLabelText(Label label){
			String text;
			text = label.getText();
			return text;
		}
		
	
}
