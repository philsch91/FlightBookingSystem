public class Buchung 
{
   private double preis;
   private Flug flug;
   private Kunde kunde;
   
   /**
   @param kunde
   @param flug
   @param preis
    */
   public Buchung(Kunde kunde, Flug flug, double preis) 
   {
	   this.kunde=kunde;
	   this.flug=flug;
	   this.preis=preis;
   }
   
   /**
   @return Flug
    */
   public Flug getFlug() 
   {
	   return this.flug;
   }
   
   /**
   @return Kunde
    */
   public Kunde getKunde() 
   {
	   return this.kunde;
   }
   
   /**
   @return double
    */
   public double getPreis() 
   {
	   return this.preis;
   }
   
   @Override
   public String toString() {
	   return this.kunde.toString() + " " + this.flug.getBeschreibung().toString() + " " + Double.toString(this.preis);
   }
}
