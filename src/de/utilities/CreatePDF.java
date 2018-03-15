package de.utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 * Nutzt Apache PDFBox.
 * @author paul4
 *
 */
public class CreatePDF {
	// Diese Klasse erzeugt ein PDF File.
	final String destination = "WebContent/pdf/bestellung.pdf";
	
	public void create() throws InvalidPasswordException, IOException {
		// technische Voraussetzungen.
		File file = new File(destination);
		file.getParentFile().mkdirs();
		PDDocument doc = new PDDocument();
	 	PDDocument document = doc.load(file);
		PDPage ersteSeite = document.getPage(0);
		
		PDPageContentStream contentstream = new PDPageContentStream(doc, ersteSeite);
		
		// Der text wird geschrieben
		contentstream.beginText();
		contentstream.setFont(PDType1Font.TIMES_ROMAN, 12);
		contentstream.newLineAtOffset(25, 500);
		contentstream.setLeading(14.5f);
		String text = "Ihre Bestellung im Detail: Artikel a ";
		contentstream.showText(text);
		contentstream.newLine();
		Date date = new Date();
		contentstream.showText(date.toString());
		contentstream.endText();
		contentstream.close();
		doc.addPage(ersteSeite);
		doc.save(file);
		System.out.println("Hat funktioniert.");
		doc.close();
		
		
		
		
		
	}
	
}
