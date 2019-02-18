public class Flug 
{
   private String datum;
   private String erwarteteAbflugszeit;
   private String erwarteteAnkunftszeit;
   private int freiePlaetze;
   private int hoechstpreis;
   private Flugbeschreibung beschreibung;
   private Flugzeug flieger;
   
   /**
   @param beschreibung
   @param flieger
   @param datum
   @param erwarteteAbflugszeit
   @param erwarteteAnkunftszeit
   @param hoechstpreis
    */
   public Flug(Flugbeschreibung beschreibung, Flugzeug flieger, String datum, String erwarteteAbflugszeit, String erwarteteAnkunftszeit, int hoechstpreis) 
   {
	   this.beschreibung=beschreibung;
	   this.flieger=flieger;
	   this.datum=datum;
	   this.erwarteteAbflugszeit=erwarteteAbflugszeit;
	   this.erwarteteAnkunftszeit=erwarteteAnkunftszeit;
	   this.hoechstpreis=hoechstpreis;
	   this.freiePlaetze=this.flieger.getSitzplaetze();
   }
   
   /**
   @return int
    */
   public int getFreiePlatze() 
   {
	   return this.freiePlaetze;
   }
   
   public double getSitzplatzAuslastung() {
	   Double freeSeats=this.freiePlaetze*1.0;
	   Double maxSeats=this.getFlieger().getSitzplaetze()*1.0;
	   Double freeSeatPercentage = ((freeSeats/maxSeats)*100);
	   Double usedSeatPercentage=100-freeSeatPercentage;
	   return usedSeatPercentage;
   }
   
   /**
   Der Preis eines Fluges berechnet sich folgendermassen:
   Die ersten 30% der Plaetze kosten 30% des Hoechstpreises, die naechsten 40% kosten 
   70% des Hoechstpreises und die restlichen Plaetze 100% des Hoechstpreises.
   
   z.B.: 100 Plaetze, Hoechstpreis >1000 
   Plaetze 1-30:     >300
   Plaetze 31-70:   >700
   Plaetze 71-100: >1000
   
   @return double
    */
   public double getPreis() 
   {
	   double usedRatio=this.getSitzplatzAuslastung();
	  
	   if(usedRatio<=30) {
		   return this.hoechstpreis*0.3;
	   }else if(usedRatio<=70) {
		   return this.hoechstpreis*0.7;
	   }
	   
	   return this.hoechstpreis;
   }
   
   public void incrementFreieplaetze() 
   {
	   if(this.freiePlaetze==this.getFlieger().getSitzplaetze()) {
		   return;
	   }
	   this.freiePlaetze++;
   }
   
   public void decrementFreieplaetze() 
   {
	   if(this.freiePlaetze==0) {
		   return;
	   }
	   
	   this.freiePlaetze--;
   }
   
   /**
   @return Flugbeschreibung
    */
   public Flugbeschreibung getBeschreibung() 
   {
	   return this.beschreibung;
   }
   
   /**
   @return Flugzeug
    */
   public Flugzeug getFlieger() 
   {
	   return this.flieger;
   }
   
   /**
   @return java.lang.String
    */
   public String getErwarteteAnkunftszeit() 
   {
	   return this.erwarteteAnkunftszeit;
   }
   
   /**
   @param erwarteteAnkunftszeit
    */
   public void setErwarteteAnkunftszeit(String erwarteteAnkunftszeit) 
   {
	   this.erwarteteAnkunftszeit=erwarteteAnkunftszeit;
   }
   
   /**
   @return java.lang.String
    */
   public String getErwarteteAbflugszeit() 
   {
	   return this.erwarteteAbflugszeit;
   }
   
   /**
   @param erwarteteAbflugszeit
    */
   public void setErwarteteAbflugszeit(String erwarteteAbflugszeit) 
   {
	   this.erwarteteAbflugszeit=erwarteteAbflugszeit;
   }
   
   /**
   @return java.lang.String
    */
   public String getDatum() 
   {
	   return this.datum;
   }
   
   @Override
   public String toString() {
	   return this.getBeschreibung().getFlugnummer() + " " + this.getDatum() + " "
			   + this.getErwarteteAbflugszeit() + "-" + this.getErwarteteAnkunftszeit()
			   + " Freie Plätze: " + this.getFreiePlatze() + " Ticketpreis: " + this.getPreis();
   }
}
