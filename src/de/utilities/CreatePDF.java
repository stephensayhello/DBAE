package de.utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import de.classes.Bestellung;
import de.classes.Produkt;

/**
 * Die Klasse erzeugt eine PDF Datei in dem ordner /WebContent/pdf
 * @author Paul Blanke
 *
 */
public class CreatePDF {
	/**
	 * Pfad der PDF Datei.
	 */
	final  static String destination = "WebContent/pdf/bestellung.pdf";
	/**
	 * Diese Methode mithilfe von Apache PDFbox eine PDF Datei.
	 * als erstes wird ein File in ein Document umgewandelt 
	 * und dann ein PDDocument erstellt, das mit Ihalt gef&uellt wird.
	 * 
	 * 
	 * 
	 * @param bestellung @Bestellung ein Objekt der Klasse Bestellung, was in eine PDF Datei geschrieben wird.
	 * @throws InvalidPasswordException PDFErzeugung Fehler fangen.
	 * @throws IOException PDFErzeugung Fehler fangen.
	 */
	public  static void create(Bestellung bestellung) throws InvalidPasswordException, IOException {
		// technische Voraussetzungen.
		File file = new File(destination);
		file.getParentFile().mkdirs();
		PDDocument doc = new PDDocument();
	 	PDDocument document = doc.load(file);
		PDPage ersteSeite = document.getPage(0);
		
		PDPageContentStream contentstream = new PDPageContentStream(doc, ersteSeite);
		List<Produkt> liste = bestellung.getBestellliste();
		// Der text wird geschrieben
		contentstream.beginText();
		contentstream.setFont(PDType1Font.TIMES_ROMAN, 12);
		contentstream.newLineAtOffset(25, 500);
		contentstream.setLeading(14.5f);
		String text = "Ihre Bestellung im Detail: ";
		contentstream.showText(text);
		contentstream.newLine();
		contentstream.newLine();
		text = "Bestelldetails: " + bestellung.getBestellnummer();
		contentstream.showText(text);
		contentstream.newLine();
		contentstream.newLine();
		for(Produkt produkt: liste) {
			contentstream.showText(produkt.getName() + " Anzahl:" +  produkt.getAnzahl() + "  Preis: " +  produkt.getPreismitanzahl());
			contentstream.newLine();
		}
		
		
		contentstream.newLine();
		Date date = new Date();
		contentstream.showText("Zeitpunkt der Bestellung:" +  date.toString());
		contentstream.endText();
		contentstream.close();
		doc.addPage(ersteSeite);
		doc.save(file);
		System.out.println("Hat funktioniert.");
		doc.close();
		
		
	}
	
	/**
	 * Diese Methode erzeugt eine PDF Datei f&uer den Mailversand.
	 * ToDo Methode unfertig
	 * @return eine PDf Datei f&uer den Mailversand
	 * @throws IOException 
	 */
	public static  PDDocument erstelleDocumetFuerMailVersand(Bestellung bestellung) throws IOException {
		PDDocument document = new PDDocument();
		PDPage ersteSeite = new PDPage();
		
		PDPageContentStream contentstream = new PDPageContentStream(document, ersteSeite);
		List<Produkt> liste = bestellung.getBestellliste();
		// Der text wird geschrieben
		contentstream.beginText();
		contentstream.setFont(PDType1Font.TIMES_ROMAN, 12);
		contentstream.newLineAtOffset(25, 500);
		contentstream.setLeading(14.5f);
		String text = "Ihre Bestellung im Detail: ";
		contentstream.showText(text);
		contentstream.newLine();
		contentstream.newLine();
		text = "Bestelldetails: " + bestellung.getBestellnummer();
		contentstream.showText(text);
		contentstream.newLine();
		contentstream.newLine();
		for(Produkt produkt: liste) {
			contentstream.showText(produkt.getName() + " Anzahl:" +  produkt.getAnzahl() + "  Preis: " +  produkt.getPreismitanzahl());
			contentstream.newLine();
		}
		
		
		contentstream.newLine();
		Date date = new Date();
		contentstream.showText("Zeitpunkt der Bestellung:" +  date.toString());
		contentstream.endText();
		contentstream.close();
		document.addPage(ersteSeite);
		
		
		return document;
	}
	
}
