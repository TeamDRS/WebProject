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
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;


/**
 * Notizen :	Anmeldung mit "Admin" lässt das Admin interface erscheinen
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
 * known Bugs : - buttonhandler rufen net alle die richtigen methoden auf(Supporterverwalten _> gruppenverwaltungsbutton macht nix
 * 				- gruppenverwaltung button macht nix
 * 
 * not yet implemented : - ServerCOmmunication : Override for HTTP PUT & DELETE
 * 						 - Parser for DataClasses and for the JsonObjects or JavaNative Stub or Workaround for transforming Data to/from Json in GWT
 * 						 - some functionallity like on buttonclick -> send data to server
 * 	
 * still to do : - get this Project to work on the Server	
 * 				 - Gruppenverwaltung anders implementieren (evtl grid verwenden und alle 
 * 					gruppenmitglieder anzeigen -> daneben ein button mit User entfernen
 * 				 - fürs user und supporter suchen jew eine Liste machen
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

	//gehört evtl in onModuleLoad()
	private ArrayList<String> user = new ArrayList<String>();
	private ArrayList<String> password = new ArrayList<String>();
	static Boolean login = false;			//TODO remove later -> for testing purpose
	
	
	final Button sendButton = new Button("Anmelden");
	final TextBox nameField = new TextBox();
	final PasswordTextBox pwField = new PasswordTextBox();
	Label ride4Earth = new Label();
	Label ride4Earth2 = new Label();	//für Home panel -> mit eigene css daten
	Label gruppenranking = new Label();
	Label gesamtranking = new Label();
	Label nameField2 = new Label();
	Label pwField2 = new Label();
	Label grName = new Label();
	Label grPunkte = new Label();
	Label grPlatz = new Label();
	//Grid gesamtrankingGrid = new Grid(1,1);
	Label grrName = new Label();	//gruppenranking name -> muss von DB geholt werden
	Label grrPunkte = new Label();
	Label grrPlatz = new Label();
	Label gerName = new Label();	//gesamtranking name -> muss von DB geholt werden
	Label gerPunkte = new Label();
	
	Label name = new Label();
	TextBox name2 = new TextBox();
	Button anfrageSchicken = new Button("Anfrage schicken");
	
	Label readOnlyTextBox = new Label();	//HOME
	Label readOnlyTextBox2 = new Label();	//PUNKTESTAND
	Label readOnlyTextBox3 = new Label();	//GRUPPENVERWALTUNG
	Label readOnlyTextBox4 = new Label();	//ABMELDEN

	Label readOnlyTextBox2v2 = new Label();	//ADMINVERWALTUNG
	Label readOnlyTextBox3v2 = new Label();	//GRUPPENVERWALTUNG
	Label readOnlyTextBox4v2 = new Label();	//ABMELDEN
	
	
	Label verwaltenName = new Label();
	Label verwaltenEmail = new Label();
	Label verwaltenMobil = new Label();
	Label verwaltenPasswort = new Label();
	TextBox verwaltenName2 = new TextBox();		//Labels with 2 are there for the display of data
	TextBox verwaltenEmail2 = new TextBox();
	TextBox verwaltenMobil2 = new TextBox();
	TextBox verwaltenPasswort2 = new TextBox();
	//BEI BUTTONS NIE UMLAUTE Ä Ö , etc VERWENDEN!
	Button verwaltenStandorte = new Button("Standorte");
	Button verwaltenSpeichern = new Button("Speichern");
	Button verwaltenAutodatenAendern = new Button("Autodaten aendern");
	Button verwaltenProfilLoeschen = new Button("Profil loeschen");
	Button verwaltenGruppe = new Button("Gruppe verwalten");
	
	
	//TODO hier ist evtl nutzloses zeug dabei
	Label verwaltenNamev2 = new Label();
	Label verwaltenEmailv2 = new Label();
	Label verwaltenMobilv2 = new Label();
	Label verwaltenPasswortv2 = new Label();
	TextBox verwaltenName2v2 = new TextBox();		//Labels with 2 are there for the display of data
	TextBox verwaltenEmail2v2 = new TextBox();
	TextBox verwaltenMobil2v2 = new TextBox();
	TextBox verwaltenPasswort2v2 = new TextBox();
	//BEI BUTTONS NIE UMLAUTE Ä Ö , etc VERWENDEN!
	Button verwaltenStandortev2 = new Button("Standorte");
	Button verwaltenSpeichernv2 = new Button("Speichern");
	Button verwaltenAutodatenAendernv2 = new Button("Autodaten aendern");
	Button verwaltenProfilLoeschenv2 = new Button("Profil loeschen");
	Button verwaltenGruppev2 = new Button("Gruppe verwalten");
	
	
	Label standortName = new Label();
	Label standortFirmenstandort = new Label();
	Label standortWohnort = new Label();
	Label standortZusaetzlich = new Label();
	Button standortSpeichern = new Button("Speichern");
	TextBox standortName2 = new TextBox();
	TextBox standortFirmenstandort2 = new TextBox();
	TextBox standortWohnort2 = new TextBox();
	TextBox standortZusaetzlich2 = new TextBox();

	
	
	Label autodatenName = new Label();
	TextBox autodatenName2 = new TextBox();
	Label autodatenSitzplaetze = new Label();
	TextBox autodatenSitzplaetze2 = new TextBox();
	Label autodatenTreibstoff = new Label();
	TextBox autodatenTreibstoff2 = new TextBox();
	Label autodatenDurchschnitt = new Label();
	TextBox autodatenDurchschnitt2 = new TextBox();
	Label autodatenEmission = new Label();
	TextBox autodatenEmission2 = new TextBox();
	Button autodatenSpeichern = new Button("Speichern");
	
	//Image r4eImage = new Image("images/logo.svg");
	//Image r4eImage = new Image("images/logo_klein.png");
	//Image r4eImage2 = new Image("images/logo_klein.png");
	
	
	/*
	int height = r4eImage.getHeight();
	int width = r4eImage.getWidth();
	r4eImage.setPixelSize(width,height);
	*/
	Label seite2Name = new Label();				
	Label seite2SupporterName = new Label();	
	TextBox seite2Name2 = new TextBox();
	TextBox seite2SupporterName2 = new TextBox();
	Button seite2UserVerwalten = new Button("User verwalten");
	Button seite2SupporterVerwalten = new Button("User verwalten");	
	
	Button gruppenrankingAnzeigen = new Button("Gruppenranking anzeigen");
	Button gesamtrankingAnzeigen = new Button("Gesamtranking anzeigen");	
	
	Button gruppeVerwalten = new Button("Gruppe verwalten");
	Button punktestandAnzeigen = new Button("Punktestand anzeigen");	
	
	Label gruppenvBeschreibung = new Label();
	TextBox gruppenvBeschreibung2 = new TextBox();		//Gruppenverwaltung -> wird von Server geholt
	TextBox gruppenvName = new TextBox();				//wie oben
	
	
	Button gruppenvAnfragean = new Button("Anfrage annehmen");
	
	Button gruppenvAnfrageab = new Button("Anfrage ablehnen");	
	Button gruppenvUserEntfernen = new Button("User entfernen");
	Button gruppenvGruppeloeschen = new Button("Gruppe loeschen");
	Button gruppenvUserHinzufuegen = new Button("User hinzufuegen");
	
	
	private static Label errorMsgLabel = new Label();
	Label errorLabel = new Label(); 
	
	RootPanel rootPanel = RootPanel.get("header");
	static VerticalPanel verticalPanel = new VerticalPanel();	//static geändert weg Serv.Comm.
	HorizontalPanel horizontalPanel = new HorizontalPanel();
	VerticalPanel verticalPanel2 = new VerticalPanel();
	HorizontalPanel horizontalPanel2 = new HorizontalPanel();	//Home , Punkte, ...
	HorizontalPanel horizontalPanelAdmin = new HorizontalPanel();
	static VerticalPanel verticalPanel3 = new VerticalPanel();			//ADMIN PANEL
	HorizontalPanel horizontalPanel3 = new HorizontalPanel();	//2 seite
	HorizontalPanel horizontalPanel4 = new HorizontalPanel();	//2 seite
	static VerticalPanel homePanel = new VerticalPanel();			// Home Seite
	static VerticalPanel punktestandPanel = new VerticalPanel();
	static VerticalPanel gruppenverwaltungPanel = new VerticalPanel();
	static VerticalPanel gruppenrankingPanel = new VerticalPanel();
	static VerticalPanel gesamtrankingPanel = new VerticalPanel();
	HorizontalPanel rankingPanel = new HorizontalPanel(); //für Name , Punkte
	HorizontalPanel rankingPanel2 = new HorizontalPanel(); //für Name , Punkte , Platz
	HorizontalPanel grurankingPanel = new HorizontalPanel(); //für Name , Punkte von Gruppenranking -> von DB geholt
	HorizontalPanel gesrankingPanel = new HorizontalPanel(); //für Name , Punkte von Gesamtranking -> von DB geholt
	HorizontalPanel gruppenv1 = new HorizontalPanel();
	HorizontalPanel gruppenv2 = new HorizontalPanel();
	HorizontalPanel gruppenv3 = new HorizontalPanel();
	HorizontalPanel gruppenv4 = new HorizontalPanel();
	static HorizontalPanel userhinz = new HorizontalPanel();
	
	
	static VerticalPanel UserVerwalten = new VerticalPanel();
	static VerticalPanel SupporterVerwalten = new VerticalPanel();
	HorizontalPanel verwalten1 = new HorizontalPanel();
	HorizontalPanel verwalten2 = new HorizontalPanel();
	HorizontalPanel verwalten3 = new HorizontalPanel();
	HorizontalPanel verwalten4 = new HorizontalPanel();
	HorizontalPanel verwalten5 = new HorizontalPanel();
	HorizontalPanel verwalten6 = new HorizontalPanel();
	HorizontalPanel verwalten7 = new HorizontalPanel();
	HorizontalPanel verwalten8 = new HorizontalPanel();
	HorizontalPanel verwalten9 = new HorizontalPanel();
	
	HorizontalPanel verwalten1v2 = new HorizontalPanel();
	HorizontalPanel verwalten2v2 = new HorizontalPanel();
	HorizontalPanel verwalten3v2 = new HorizontalPanel();
	HorizontalPanel verwalten4v2 = new HorizontalPanel();
	HorizontalPanel verwalten5v2 = new HorizontalPanel();
	HorizontalPanel verwalten6v2 = new HorizontalPanel();
	HorizontalPanel verwalten7v2 = new HorizontalPanel();
	HorizontalPanel verwalten8v2 = new HorizontalPanel();
	HorizontalPanel verwalten9v2 = new HorizontalPanel();
	
	
	static VerticalPanel StandortSeite = new VerticalPanel();
	HorizontalPanel standort1 = new HorizontalPanel();
	HorizontalPanel standort2 = new HorizontalPanel();
	HorizontalPanel standort3 = new HorizontalPanel();
	HorizontalPanel standort4 = new HorizontalPanel();
	HorizontalPanel standort5 = new HorizontalPanel();
	
	
	static VerticalPanel AutodatenSeite = new VerticalPanel();
	HorizontalPanel autodaten1 = new HorizontalPanel();
	HorizontalPanel autodaten2 = new HorizontalPanel();
	HorizontalPanel autodaten3 = new HorizontalPanel();
	HorizontalPanel autodaten4 = new HorizontalPanel();
	HorizontalPanel autodaten5 = new HorizontalPanel();
	HorizontalPanel autodaten6 = new HorizontalPanel();
	
	

	
	//public LoginData2 logindata2 = getFirstLogin(); //schmeißt error
	
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
		
		
		//LoginData2 logindata2 = getFirstLogin();
		
		//r4eImage.setSize("250px","250px");
		
		ride4Earth.setText("RIDE4EARTH");
		ride4Earth.setStyleName("ride4earth");
		ride4Earth2.setText("RIDE4EARTH");
		ride4Earth2.setStyleName("ride4earth2");
		nameField.setText("User");
		nameField.setStyleName("passwortbox");
		nameField2.setText("Benutzername:");
		nameField2.addStyleName("passwortbox2");
		pwField.setText("Password");
		pwField.setStyleName("passwortbox");
		pwField2.setText("Passwort");
		pwField2.addStyleName("passwortbox2");
		
	    readOnlyTextBox.setText("HOME");
	    readOnlyTextBox.setStyleName("horizontalmainpanel");
	    readOnlyTextBox2.setText("PUNKTESTAND");
	    readOnlyTextBox2.setStyleName("horizontalmainpanel");
	    readOnlyTextBox3.setText("GRUPPENVERWALTUNG");
	    readOnlyTextBox3.setStyleName("horizontalmainpanel");
	    readOnlyTextBox4.setText("ABMELDEN");
	    readOnlyTextBox4.setStyleName("horizontalmainpanel");
	    
	    
	    /*
	    //TODO anstatt Labels für liste zum verwenden verwends Grid wen daten von DB holsch
	    gesamtrankingGrid.add(grName);
	    gesamtrankingGrid.add(grPunkte);
	    gesamtrankingGrid.add(grPlatz);
	    */
	    
		//styles
		sendButton.setStyleName("loginbutton");
		gruppeVerwalten.setStyleName("buttonLayout2");	
		punktestandAnzeigen.setStyleName("buttonLayout2");	
		gruppenrankingAnzeigen.setStyleName("buttonLayout2");
		gesamtrankingAnzeigen.setStyleName("buttonLayout2");
		
		gruppenvAnfragean.setStyleName("buttonLayout5");
		gruppenvAnfrageab.setStyleName("buttonLayout5");
		gruppenvUserEntfernen.setStyleName("buttonLayout5");
		gruppenvGruppeloeschen.setStyleName("buttonLayout4");
		gruppenvUserHinzufuegen.setStyleName("buttonLayout4");
		
		
		horizontalPanel.setStyleName("menuPanel");
		horizontalPanel2.setStyleName("menuPanel");
		horizontalPanelAdmin.setStyleName("menuPanel");
		horizontalPanel3.setStyleName("menuPanel2");
		horizontalPanel4.setStyleName("menuPanel2");
		
		
	
		
		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		
		errorMsgLabel.setVisible(false);
		verticalPanel3.setVisible(false);
		homePanel.setVisible(false);
		punktestandPanel.setVisible(false);
		gruppenverwaltungPanel.setVisible(false);
		gruppenrankingPanel.setVisible(false);
		gesamtrankingPanel.setVisible(false);
		userhinz.setVisible(false);
		UserVerwalten.setVisible(false);
		SupporterVerwalten.setVisible(false);
		StandortSeite.setVisible(false);
		AutodatenSeite.setVisible(false);
		horizontalPanelAdmin.setVisible(false);
		
		verticalPanel.setWidth("100%");
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel3.setWidth("100%");
		verticalPanel3.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		
		verticalPanel.add(ride4Earth);
		verticalPanel.add(horizontalPanel);
		
		horizontalPanel.add(nameField2);
		horizontalPanel.add(nameField);
		horizontalPanel.add(pwField2);
		horizontalPanel.add(pwField);
		verticalPanel.add(sendButton);
		
		
		verticalPanel2.add(horizontalPanel2);
		verticalPanel2.add(horizontalPanelAdmin);
		verticalPanel3.add(horizontalPanel4);	
		verticalPanel3.add(horizontalPanel3);
		
		
		//horizontalPanel2.add(r4eImage);
		horizontalPanel2.add(readOnlyTextBox);
		horizontalPanel2.add(readOnlyTextBox2);
		horizontalPanel2.add(readOnlyTextBox3);
		horizontalPanel2.add(readOnlyTextBox4);
		
		//horizontalPanelAdmin.add(r4eImage2);
		horizontalPanelAdmin.add(readOnlyTextBox2v2);
		horizontalPanelAdmin.add(readOnlyTextBox3v2);
		horizontalPanelAdmin.add(readOnlyTextBox4v2);
		
		//horizontalPanelAdmin.setVisible(false);

		
		horizontalPanel3.add(seite2SupporterName);
		horizontalPanel3.add(seite2SupporterName2);
		horizontalPanel3.add(seite2SupporterVerwalten);
		
		horizontalPanel4.add(seite2Name);
		horizontalPanel4.add(seite2Name2);
		horizontalPanel4.add(seite2UserVerwalten);
		
		/*
		horizontalPanel3.add(seite2Name);
		horizontalPanel3.add(seite2Name2);
		horizontalPanel3.add(seite2SupporterVerwalten);
		
		horizontalPanel4.add(seite2SupporterName);
		horizontalPanel4.add(seite2SupporterName2);
		horizontalPanel4.add(seite2UserVerwalten);
		*/
		
		
		homePanel.setWidth("100%");
		homePanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		homePanel.add(ride4Earth2);
		homePanel.add(gruppeVerwalten);
		homePanel.add(punktestandAnzeigen);
		
		
		
		
		/*
		 * Root Panel 
		 */
		
		rootPanel.add(verticalPanel2);	
	
		
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
	
		private void addData() {
			//bei beiden war .getText().toUpperCase().trim();
			final String symbol1 = nameField.getText().trim();
			nameField.setFocus(true);
			final String symbol2 = pwField.getText().trim();
			
			
			// code must be between 1 and 10 chars that are numbers, letters,
			// or dots.
			
			/*
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
			*/
			nameField.setText("");

			// Don't add the user if he´s already in the table.	
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
				//ToJson.ToJsonLogin();	//schemißt error -> falsche json parser
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
				//Ride4Earth_Web.home();	für testzwecke
				
			}
			/*
			//evtl LÖSUNG !!! : http://www.massapi.com/source/jiplet/console/src/org/cafesip/jiplet/console/client/FormsPanel.java.html
			
			
			/**
			 * das ist ein versuch mit Restlet
			 */
			// Set up the login resource
			//loginResource.getClientResource().setReference("/communityUser/{id}");


			
			
			
			// log muss jetzt in Json umgewandelt werden, 
			//und dann an server verschickt werden , 
			//permission muss auch gesetzt werden (server schickt permission)
			
			
			/*
			 * DESCRIPTION : short test to see if the data is saved in the
			 * Arraylists
			 * 
			 * int size = user.size();
			 * 
			 * for (int i = 0; i < user.size(); i++) { String item1 = user.get(i);
			 * System.out.println("User " + i + " : " + item1); String item2 =
			 * password.get(i); System.out.println("Password " + i + " : " + item2);
			 * }
			 */
			
			//}
		
		
		
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
