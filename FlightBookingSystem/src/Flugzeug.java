public class Flugzeug 
{
   private String typ;
   private String name;
   private int sitzplaetze;
   
   /**
   @param name
   @param sitzplaetze
   @param typ
    */
   public Flugzeug(String name, int sitzplaetze, String typ) 
   {
	   this.name=name;
	   this.sitzplaetze=sitzplaetze;
	   this.typ=typ;
   }
   
   /**
   @return int
    */
   public int getSitzplaetze() 
   {
     return this.sitzplaetze;
   }
   
   /**
   @return java.lang.String
    */
   public String getName() 
   {
	   return this.name;
   }
   
   /**
   @return java.lang.String
    */
   public String getTyp() 
   {
	   return this.typ;
   }
   
   @Override
   public String toString() {
	   return "Typ: "+this.getTyp() + " Name: " + this.getName() + " Sitzplätze: " + this.getSitzplaetze();
   }
}
