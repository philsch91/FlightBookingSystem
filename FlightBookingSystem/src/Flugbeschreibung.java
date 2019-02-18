public class Flugbeschreibung 
{
   private String flugnummer;
   private String abflugsort;
   private String ankunftsort;
   private String geplanteAbflugszeit;
   private String geplanteAnkunftszeit;
   
   /**
   @param flugnummer
   @param abflugsort
   @param ankunftsort
   @param geplanteAbflugszeit
   @param geplanteAnkunftszeit
    */
   public Flugbeschreibung(String flugnummer, String abflugsort, String ankunftsort, String geplanteAbflugszeit, String geplanteAnkunftszeit) 
   {
	   this.flugnummer=flugnummer;
	   this.abflugsort=abflugsort;
	   this.ankunftsort=ankunftsort;
	   this.geplanteAbflugszeit=geplanteAbflugszeit;
	   this.geplanteAnkunftszeit=geplanteAnkunftszeit;
   }
   
   /**
   @return java.lang.String
    */
   public String getAbflugsort() 
   {
	   return this.abflugsort;
   }
   
   /**
   @return java.lang.String
    */
   public String getAnkunftsort() 
   {
	   return this.ankunftsort;
   }
   
   /**
   @return java.lang.String
    */
   public String getFlugnummer() 
   {
	   return this.flugnummer;
   }
   
   /**
   @return java.lang.String
    */
   public String getGeplanteAbflugszeit() 
   {
	   return this.geplanteAbflugszeit;
   }
   
   /**
   @return java.lang.String
    */
   public String getGeplanteAnkunftszeit() 
   {
	   return this.geplanteAnkunftszeit;
   }
   
   @Override
   public String toString() {
	   return this.flugnummer+" "+this.abflugsort+"-"+this.ankunftsort+" "+this.geplanteAbflugszeit+"-"+this.geplanteAnkunftszeit; 
   }
}
