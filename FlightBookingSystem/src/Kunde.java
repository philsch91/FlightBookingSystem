public class Kunde 
{
   private String name;
   private String strasse;
   private String postleitzahl;
   private String staat;
   
   /**
   @param name
   @param postleitzahl
   @param strasse
   @param staat
    */
   public Kunde(String name, String postleitzahl, String strasse, String staat) 
   {
	   this.name=name;
	   this.postleitzahl=postleitzahl;
	   this.strasse=strasse;
	   this.staat=staat;
   }
   
   /**
   @return java.lang.String
    */
   public String getStaat() 
   {
	   return this.staat;
   }
   
   /**
   @return java.lang.String
    */
   public String getStrasse() 
   {
	   return this.strasse;
   }
   
   /**
   @return java.lang.String
    */
   public String getPostleitzahl() 
   {
	   return this.postleitzahl;
   }
   
   /**
   @return java.lang.String
    */
   public String getName() 
   {
	   return this.name;
   }
   
   @Override
   public String toString() {
	   return this.name + " " + this.strasse + " " + this.postleitzahl + " " + this.staat;
   }
}
