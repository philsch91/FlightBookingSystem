

/***************************************************************************
                               Menu.java
                             -------------------
    begin                : Wed Okt 10 - 18:00:00 CEST 2001
    copyright            : (C) 2001 Joseph Wenninger (Matr. Nr.: 9925371)
                           (C) 2003 Joseph Wenninger
    email                : jowenn@kde.org | e9925371@student.tuwien.ac.at
 ***************************************************************************/

/***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/

import java.util.*;
import java.io.*;


// Interne Hilfsklasse
class MenuEntry
{
    public MenuEntry(char k, String t){key=k; text=t;}
    public char key;
    public String text;
};


/**
 * Klasse: Menu
 * Vorfahre: implizit Object/Class
 *
 * Beschreibung: Diese Klasse uerbernimmt die Verwaltung/Darstellung/Abfrage der Menues vor.
 **/
public class Menu
{
    // private members
    
    private String titel_; // Ueberschrift
    private Vector vec; // Menueeintraege


    /**
     * Konstruktor Menu
     * Parameter ----
     *
     * Initialisierung der internen Datenstrukturen
     **/
    public Menu(){vec=new Vector();}


    /**
     * Methode setTitel
     * Parameter String titel
     *
     * Beschreibung: Diese Methode setzt die anzegeigte Ueberschrift des Menues
     **/
    public void setTitel(String titel){titel_=titel;}

    public String getTitel(){
    	return titel_;
    }
    /** 
     * Methode insert
     * Parameter: char key
     *            String text
     *
     * Beschreibung: Diese Methode fuegt einen neuen Menueeintrag hinzu. Der Parameter key
     * gibt das einzugebende Zeichen an. text ist die anzuzeigende Beschreibung
     **/
    public void insert(char key,String text)
	{
	    vec.addElement(new MenuEntry(key,text));
	}

    /**
     * Methode exec     //CASE INSENSITIVE CHARACTER HANDLING
     * Parameter ----
     * Rueckgabwert char
     *
     * Beschreibung: Diese Funktion zeigt das definierte Menue an und blockiert so lange, bis
     * ein Menueeintrag ausgewaehlt worden ist. Der Rueckgabe wert entspricht der Eigenschaft Key
     * es Menueeintrages
     **/

    public char exec()
	{
	    // Titel und Trennlinie ausgeben
	    System.out.println("\n\n"+titel_);
	    for (int i=0;i<titel_.length();i++)
		System.out.print("*");
	    System.out.print("\n");

	    // Alle Menueeingraege nacheinander ausgeben (Key + Beschreibung)
	    for (int i=0;i<vec.size();i++)
	    {
		MenuEntry ent=(MenuEntry)vec.elementAt(i);
		System.out.println(ent.key+")\t"+ent.text);
	    }

	    // Eingabeschleife
	    System.out.print("\n");
	    BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
	    do
	    {	       
		String value="\0";
		System.out.print(">");
		
		// Einlesen einer Zeile und eventuelles abfangen eienr IO Exception
		try
		{
		    value=inReader.readLine();
		} 
		catch ( IOException e)
		{
		    /* Wenn dieser Teil zur Ausfuehrung kommt, dann ist irgend etwas schlimmes
		       geschehen -->> Benutzer zum Abbruch bewegen */
		    System.out.println("Fehler waehrend readChar:"+e.toString()+
				       "\n Bitte beenden Sie die Anwendung mit CTRL-C");
		}
		if (value.length()>0) // Ueberpruefung ob die Eingabe ein Leerstring ist
		{
		    // Testen ob das erste eingelesene Zeichen einem Menueeintrag entspricht und 
		    // gegebenenfallst zum Aufrufer zurueckkehren
		    for (int i=0;i<vec.size();i++)
                        if (
                        (((MenuEntry)vec.elementAt(i)).key==Character.toUpperCase(value.charAt(0))) ||
                        (((MenuEntry)vec.elementAt(i)).key==Character.toLowerCase(value.charAt(0))))
                                return ((MenuEntry)vec.elementAt(i)).key;
		}
		// Benutzer benachrichtigen, dass sein Eingabe ungueltig war
		System.out.println("!!! Falsche Eingabe !!!");
	    } while (true);
	}
   
};
