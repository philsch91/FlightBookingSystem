import java.util.ArrayList;

/*
 * copyright (c) 2018 Philipp Schunker
 */

public class Controller 
{
   
   private ArrayList<Kunde> kunden;
   private ArrayList<Flug> fluege;
   private ArrayList<Flugzeug> flugzeuge;
   private ArrayList<Buchung> buchungen;
   private ArrayList<Flugbeschreibung> flugbeschreibungen;
   private static Controller ref=null;
   
   /**
   Privater Konstruktor. -> Singleton-Pattern
    */
   private Controller() 
   {
	   this.kunden = new ArrayList<Kunde>();
	   this.fluege = new ArrayList<Flug>();
	   this.flugzeuge = new ArrayList<Flugzeug>();
	   this.buchungen = new ArrayList<Buchung>();
	   this.flugbeschreibungen = new ArrayList<Flugbeschreibung>();
   }
   
   /**
   Returniert immer die selbe Instanz der Klasse Controller. -> Singleton-Pattern
   
   @return Controller
    */
   public static synchronized Controller getInstance() 
   {
	   if(ref==null) {
		   ref = new Controller();
	   }
	   return ref;
   }
   
   /**
   Gibt den Vector Fluege zurueck.
   
   @return java.util.Vector
    */
   public ArrayList<Flug> getFluege() 
   {
	return this.fluege;
    
   }
   
   /**
   Gibt den Vector Kunden zurueck.
   
   @return java.util.Vector
    */
   public ArrayList<Kunde> getKunden() 
   {
    return this.kunden;
   }
   
   /**
   @param k
   @param f
    */
   public void FlugBuchen(Kunde k, Flug f) 
   {
	   double price = f.getPreis();
	   f.decrementFreieplaetze();
	   Buchung booking = new Buchung(k, f, price);
	   this.buchungen.add(booking);
   }
   
   /**
   @param b
    */
   public void BuchungStornieren(Buchung b) 
   {
	   Flug flight = b.getFlug();
	   flight.incrementFreieplaetze();
	   //int index = 0;
	   ArrayList<Buchung> updatedBookings = new ArrayList<>();
	   for(Buchung booking : this.buchungen) {
		   if(booking.equals(b)==false) {
			   //removing while iterating throws a java.util.ConcurrentModificationException
			   //this.buchungen.remove(index);
			   //this.buchungen.remove(booking);
			   updatedBookings.add(booking);
		   }
		   //index++;
	   }
	   this.buchungen = updatedBookings;
   }
   
   /**
   Gibt den Vector Flugbeschreibungen zurueck.
   
   @return java.util.Vector
    */
   public ArrayList<Flugbeschreibung> getFlugbeschreibungen() 
   {
	   return this.flugbeschreibungen;
   }
   
   /**
   Gibt den Vector Flugzeuge zurueck.
   
   @return java.util.Vector
    */
   public ArrayList<Flugzeug> getFlugzeuge() 
   {
	   return this.flugzeuge;
   }
   
   /**
   Gibt alle Buchungen eines bestimmten Kunden zurueck
   
   @param k
   @return java.util.Vector
    */
   public ArrayList<Buchung> getBuchungen(Kunde k) 
   {
	   ArrayList<Buchung> bookings = new ArrayList<>();
	   for(Buchung booking : this.buchungen) {
		   if(booking.getKunde()!=null && booking.getKunde().equals(k)) {
			   bookings.add(booking);
		   }
	   }
	   
	   return bookings;
   }
   
   /**
   Gibt alle Kunden, die einen bestimmten Namen haben, zurueck.
   
   @param name
   @return java.util.Vector
    */
   public ArrayList<Kunde> getKunden(String name) 
   {
	   return this.kunden;
   }
   
   /**
   @param typ
   @param name
   @param sitzplaetze
    */
   public void FlugzeugEintragen(String typ, String name, int sitzplaetze) 
   {
	   Flugzeug plane = new Flugzeug(name,sitzplaetze,typ);
	   this.flugzeuge.add(plane);
   }
   
   /**
   @param beschr
   @param flieger
   @param datum
   @param erwarteteAbflugszeit
   @param erwarteteAnkunftszeit
   @param hoechstpreis
    */
   public void FlugEintragen(Flugbeschreibung beschr, Flugzeug flieger, String datum, String erwarteteAbflugszeit, String erwarteteAnkunftszeit, int hoechstpreis) 
   {
	   Flug flight = new Flug(beschr,flieger,datum,erwarteteAbflugszeit,erwarteteAnkunftszeit,hoechstpreis);
	   this.fluege.add(flight);
   }
   
   /**
   @param name
   @param postleitzahl
   @param strasse
   @param staat
    */
   public void KundenEintragen(String name, String postleitzahl, String strasse, String staat) 
   {
	   Kunde customer = new Kunde(name, postleitzahl, strasse, staat);
	   this.kunden.add(customer);
   }
   
   /**
   Aendert die erwartete Abflugs- und Ankunftszeit eines bestimmten Fluges.
   
   @param f
   @param erwarteteAbflugszeit
   @param erwarteteAnkunftszeit
    */
   public void FlugdatenAendern(Flug f, String erwarteteAbflugszeit, String erwarteteAnkunftszeit) 
   {
	   ArrayList<Flug> flights = this.getFluege();
	   for(Flug flight : flights) {
		   if(flight.equals(f) && 
				   flight.getBeschreibung().equals(f.getBeschreibung()) &&
				   flight.getBeschreibung().getFlugnummer() == f.getBeschreibung().getFlugnummer() ){
			   flight.setErwarteteAbflugszeit(erwarteteAbflugszeit);
			   flight.setErwarteteAnkunftszeit(erwarteteAnkunftszeit);
		   }
	   }
   }
   
   /**
   @param Flugnummer
   @param Abflugsort
   @param Ankunftsort
   @param GeplanteAbflugszeit
   @param GeplanteAnkunftszeit
    */
   public void FlugbeschreibungEintragen(String Flugnummer, String Abflugsort, String Ankunftsort, String GeplanteAbflugszeit, String GeplanteAnkunftszeit) 
   {
	   Flugbeschreibung desc = new Flugbeschreibung(Flugnummer, Abflugsort, Ankunftsort, GeplanteAbflugszeit, GeplanteAnkunftszeit);
	   this.flugbeschreibungen.add(desc);
   }
}
