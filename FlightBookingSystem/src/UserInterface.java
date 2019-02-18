import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * copyright (c) 2018 Philipp Schunker
 */

public class UserInterface 
{
   private Controller controller;
   
   public UserInterface() 
   {
    
   }
   
   /**
   Mit dieser Methode werden die Daten eines Flugzeuges vom User abgefragt und an 
   den Controller uebergeben.
    */
   public void flugzeugEintragen() 
   {
	   System.out.println("Flugzeugtyp:");
	   String planeType = this.readLine();
	   System.out.println("Name des Flugzeuges:");
	   String planeName = this.readLine();
	   System.out.println("Sitzplätze:");
	   int seatCount=this.readInteger(0, 400);
	   
	   Controller controller = Controller.getInstance();
	   controller.FlugzeugEintragen(planeType, planeName, seatCount);
   }
   
   /**
   Mit dieser Methode werden die Daten eines Fluges vom User abgefragt und an den 
   Controller uebergeben.
    */
   public void flugEintragen() 
   {   
	   Controller controller = Controller.getInstance();
	   ArrayList<Flugbeschreibung> flightDescriptions = controller.getFlugbeschreibungen();
	   if(flightDescriptions.size() == 0) {
		   this.createFlugbeschreibungen();
	   }
	   Flugbeschreibung desc = this.flugbeschreibungAuswaehlen();
	   Flugzeug plane = this.flugzeugAuswaehlen();
	   if(plane == null) {
		   System.out.println("Keine Flugzeuge im System angelegt");
		   return;
	   }
	   System.out.println("Datum:");
	   String date = this.readLine();
	   System.out.println("Erwartete Abflugszeit:");
	   String departure = this.readLine();
	   System.out.println("Erwartete Ankunftszeit:");
	   String arrival = this.readLine();
	   System.out.println("Höchstpreis für einen Sitzplatz:");
	   int maxSeatPrice = this.readInteger(0, 20000);
	   
	   controller.FlugEintragen(desc, plane, date, departure, arrival, maxSeatPrice);
   }
   
   /**
   Mit dieser Methode werden die Daten eines neuen Kunden vom User abgefragt und 
   an den Controller uebergeben.
    */
   public void KundenEintragen() 
   {
	   System.out.println("Neuen Kunden anlegen");
	   System.out.println("Name:");
	   String name = this.readLine();
	   System.out.println("Postleitzahl:");
	   int number = this.readInteger(1000, 10000);
	   String zipcode = Integer.toString(number);
	   System.out.println("Straße:");
	   String street = this.readLine();
	   System.out.println("Staat:");
	   String country = this.readLine();
	   
	   Controller controller = Controller.getInstance();
	   controller.KundenEintragen(name, zipcode, street, country);
   }
   
   /**
   Mit dieser Methode wird ein Flug fuer einen Kunden gebucht. Dabei koennen sowohl 
   der Flug als auch der Kunde vom User ausgewaehlt werden.
    */
   public void flugBuchen() 
   {
	   Controller controller = Controller.getInstance();
	   
	   Flug flight = this.flugAuswaehlen();
	   if(flight == null) {
		   System.out.println("Keine Fluege im System");
		   return;
	   }
	   System.out.println("Kundename:");
	   String customerName = this.readLine();
	   ArrayList<Kunde> customers = controller.getKunden();
	   Kunde customer = null;
	   Boolean customerIsInSystem = false;
	   for(Kunde c : customers) {
		   if(c.getName().equals(customerName)) {
			   customerIsInSystem = true;
			   customer = c;
		   }
	   }
	   if(customerIsInSystem == false) {
		   this.KundenEintragen();
		   customers=controller.getKunden();
		   for(Kunde c : customers) {
			   if(c.getName().equals(customerName)) {
				   customer = c;
			   }
		   }
	   }
	   
	   controller.FlugBuchen(customer, flight);
	   System.out.println("Flug gebucht");
   }
   
    
   /**
   Durch Aufruf dieser Methode erscheint das Benutzer-Menue und zwar solange, 
   bis der User das Programm beendet. Die Auswahl des Benutzers wird abgefragt und 
   die entsprechenden Methoden aufgerufen.
    */
   public void startMenue() 
   {
    
   }
   
   /**
   Mit dieser Methode wird eine Integer-Zahl von stdin eingelesen, die von 
   lowerlimit bis upperlimit betragen darf. 
   
   @param lowerlimit
   @param upperlimit
   @return int
    */
   private int readInteger(int lowerlimit, int upperlimit) 
   {
	   Integer number=null;
	   while(number==null) {
		   String str = this.readLine();
		   try {
			   number = Integer.parseInt(str);
		   }catch(NumberFormatException e) {
			   number=null;
			   System.out.println("Geben Sie bitte eine gültige Zahl ein:");
			   e.printStackTrace();
			   continue;
		   }
		   if(number<lowerlimit) {
			   System.out.println("Geben Sie bitte eine größere Zahl ein:");
			   number=null;
		   }else if(number>upperlimit) {
			   System.out.println("Geben Sie bitte eine kleinere Zahl ein:");
			   number=null;
		   }
	   }
	   
	   //System.out.println(Integer.toString(number));
	   return number;
   }
   
   /**
   Durch Aufruf dieser Methode kann vom User ein Flug ausgewaehlt werden. Alle 
   vorhandenen Fluege sind waehlbar.
   
   @return Flug
    */
   private Flug flugAuswaehlen() 
   {
	   Controller controller = Controller.getInstance();
	   ArrayList<Flug> flights = controller.getFluege();
	   ArrayList<Object> items = new ArrayList<>(flights.size());
	   for(Flug f : flights){
		   items.add(f);
	   }
	   if(items.isEmpty()) {
		   return null;
	   }
	   //System.out.println("Wählen Sie einen Flug:");
	   Flug flight = (Flug)this.showMenu(items);
	   return flight;
   }
   
   /**
   Die Daten eines bestimmten Fluges werden von dieser Methode ausgegeben.
    */
   public void flugdatenAnsehen(Flug flight) 
   {
	   System.out.println("Vorgesehenes Flugzeug: " + flight.getFlieger().getTyp());
	   System.out.println("Maximale Plätze: " + flight.getFlieger().getSitzplaetze());
	   System.out.println("Freie Plätze: " + flight.getFreiePlatze() + " / " + flight.getFlieger().getSitzplaetze());
	   System.out.println("Sitzplatzauslastung: " + Double.toString(flight.getSitzplatzAuslastung())+"%");
	   System.out.println("Derzeitiger Ticketpreis: " + flight.getPreis() + " (Bitcoins)");
	   System.out.println("----------");
	   System.out.println("Erwartete Ablugszeit: " + flight.getErwarteteAbflugszeit());
	   System.out.println("Erwartete Ankunftszeit: " + flight.getErwarteteAnkunftszeit());
   }
   
   /**
   Mit dieser Methode kann eine Buchung storniert werden. Dazu wird zuerst der 
   Kunde ausgewaehlt. Anschliessend werden alle Buchungen dieses Kunden angezeigt. 
   Der User waehlt dann die zu stornierende Buchung.
    */
   public void buchungStornieren() 
   {
	   System.out.println("Kunde:");
	   Kunde customer = this.kundenAuswaehlen();
	   if(customer == null) {
		   return;
	   }
	   
	   Buchung booking = this.buchungAuswaehlen(customer);
	   if(booking == null) {
		   return;
	   }
	   Controller controller = Controller.getInstance();
	   controller.BuchungStornieren(booking);
	   System.out.println("Buchung storniert");
   }
   
   /**
   Mit dieser Methode kann ein Kunde ausgewaehlt werden. Zuerst wird der Name des 
   Kunden abgefragt und anschliessend alle Kunden dieses Namen angezeigt. Der User 
   waehlt dann den gewaenschten Kunden.
   
   @return Kunde
    */
   private Kunde kundenAuswaehlen() 
   {
	   String customerName = this.readLine();
	   Controller controller = Controller.getInstance();
	   ArrayList<Kunde> customers = controller.getKunden();
	   ArrayList<Object> items = new ArrayList<>(customers.size());
	   Boolean customerInSystem = false;
	   for(Kunde k : customers){
		   if(k.getName().contains(customerName)) {
			   customerInSystem = true;
			   items.add(k);
		   }
	   }
	   if(customerInSystem == false) {
		   System.out.println("Kunde konnte nicht im System gefunden werden");
		   return null;
	   }
	   Kunde customer = (Kunde)this.showMenu(items);
	   return customer;
   }
   
   /**
   Mit dieser Methode wird ein String von stdin gelesen.
   
   @return java.lang.String
    */
   private String readLine() 
   {
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
		} catch (IOException e) {
		}
		
		return value.trim();
   }
   
   /**
   Durch Aufruf dieser Methode werden alle Flugzeuge angezeigt, von denen der User 
   dann eines waehlen kann.
   
   @return Flugzeug
    */
   private Flugzeug flugzeugAuswaehlen() 
   {
	   Controller controller = Controller.getInstance();
	   ArrayList<Flugzeug> planes = controller.getFlugzeuge();
	   if(planes.isEmpty()) {
		   return null;
	   }
	   Menu menu = new Menu();
	   menu.setTitel("Flugzeug auswählen:");
	   int i=1;
	   for(Flugzeug plane : planes) {
		   char c = (char)(i + '0');
		   menu.insert(c, plane.toString());
		   i++;
	   }
	   int index;
	   System.out.println("planes.size: " + Integer.toString(planes.size()));
	   while((index = Character.getNumericValue(menu.exec()-1)) >= planes.size()) {
		   //wait for valid input
	   }
	   Flugzeug plane = planes.get(index);
	   return plane;
   }
   
   private void createFlugbeschreibungen() {
	   Controller controller = Controller.getInstance();
	   controller.FlugbeschreibungEintragen("OS12", "Wien", "Los Angeles", "08:00", "10:00");
	   controller.FlugbeschreibungEintragen("LH30", "Frankfurt", "Tokio", "12:00", "18:00");
	   controller.FlugbeschreibungEintragen("BA24", "London", "Kapstadt", "20:00", "07:00");
	   controller.FlugbeschreibungEintragen("AA60", "Amsterdam", "Melbourne", "20:00", "07:00");
	   controller.FlugbeschreibungEintragen("MFL1021", "London", "New York", "06:00", "12:30");
	   int count = controller.getFlugbeschreibungen().size();
	   System.out.println(Integer.toString(count) + " Flugbeschreibungen wurden angelegt");
   }
   
   /**
   Durch Aufruf dieser Methode werden zuerst alle Flugbeschreibungen aufgelistet. 
   Anschliessend kann der User eine dieser Flugbeschreibungen waehlen.
   
   @return Flugbeschreibung
    */
   private Flugbeschreibung flugbeschreibungAuswaehlen()
   {
	   Menu menu = new Menu();
	   menu.setTitel("Flugbeschreibung auswählen:");
	   Controller controller = Controller.getInstance();
	   ArrayList<Flugbeschreibung> descs = controller.getFlugbeschreibungen();
	   int i=1;
	   for(Flugbeschreibung desc : descs) {
		   char c = (char)(i + '0');
		   menu.insert(c, desc.toString());
		   i++;
	   }
	   System.out.println("descs.size: " + Integer.toString(descs.size()));
	   int index;
	   while( (index = Character.getNumericValue(menu.exec()-1)) >= descs.size()) {
		   //wait for valid input
	   }
	   Flugbeschreibung desc = descs.get(index);
	   return desc;
   }
   
   private Object showMenu(ArrayList<Object> items) {
	   String objectType = items.get(0).getClass().getTypeName();
	   Menu menu = new Menu();
	   menu.setTitel(objectType + " auswählen:");
	   int i=1;
	   for(Object obj : items) {
		   char c = (char)(i + '0');
		   menu.insert(c, obj.toString());
		   i++;
	   }
	   System.out.println("items.size: " + Integer.toString(items.size()));
	   int index;
	   while( (index = Character.getNumericValue(menu.exec()-1)) >= items.size()) {
		   //wait for valid input
	   }
	   Object object = items.get(index);
	   return object;
   }
   
   
   /**
   Durch Aufruf dieser Methode werden zuerst alle Buchungen eines Kunden 
   aufgelistet. Anschliessend kann der User eine dieser Buchungen auswaehlen.
   
   @param kunde
   @return Buchung
    */
   private Buchung buchungAuswaehlen(Kunde kunde) 
   {
	   Controller controller = Controller.getInstance();
	   ArrayList<Buchung> customerBookings = controller.getBuchungen(kunde);
	   if(customerBookings.isEmpty()) {
		   System.out.println("Keine Buchungen für diesen Kunden gefunden");
		   return null;
	   }
	   ArrayList<Object> items = new ArrayList<>(customerBookings.size());
	   for(Buchung b : customerBookings){
		   items.add(b);
	   }
	   Buchung booking = (Buchung)this.showMenu(items);
	   return booking;
   }
   
   private void flugDatenAendern() {
	   Flug flight = this.flugAuswaehlen();
	   this.flugdatenAnsehen(flight);
	   System.out.println("Neue Abflugszeit: ");
	   String newDepartureTime = this.readLine();
	   System.out.println("Neue Ankunftszeit: ");
	   String newArrivalTime = this.readLine();
	   Controller controller = Controller.getInstance();
	   controller.FlugdatenAendern(flight, newDepartureTime, newArrivalTime);
	   System.out.println("Flugdaten geändert");
   }
   
	public void start() {
		Menu menu = new Menu();
		menu.setTitel("FH Flughafen Tower");
		menu.insert('1', "Flugzeug eintragen");
		menu.insert('2', "Flug eintragen");
		menu.insert('3', "Kundendaten eintragen");
		menu.insert('4', "Flug buchen");
		menu.insert('5', "Buchung stornieren");
		menu.insert('6', "Flugdaten ändern");
		menu.insert('7', "Flugbeschreibungen anlegen");
		menu.insert('q', "Quit");
		char choice;
		while ((choice = menu.exec()) != 'q') {
			try {
				switch (choice) {
				case '1':
					this.flugzeugEintragen();
					break;
				case '2':
					this.flugEintragen();
					break;
				case '3':
					this.KundenEintragen();
					break;
				case '4':
					this.flugBuchen();
					break;
				case '5':
					this.buchungStornieren();
					break;
				case '6':
					this.flugDatenAendern();
					break;
				case '7':
					this.createFlugbeschreibungen();
					break;
				}
			} catch (ClassCastException e) {
			}
		}
		System.out.println("Program finished");
	}
	
}
