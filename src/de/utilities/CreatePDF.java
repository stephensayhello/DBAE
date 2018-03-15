package de.utilities;

import java.io.File;
import java.io.IOException;

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
	final String destination = "/pdf/bestellung.pdf";
	
	public void create() throws InvalidPasswordException, IOException {
		// technische Voraussetzungen.
		File file = new File(destination);
		PDDocument doc = new PDDocument();
		PDDocument document = doc.load(file);
		PDPage ersteSeite = document.getPage(0);
		PDPageContentStream contentstream = new PDPageContentStream(document, ersteSeite);
		
		// Der text wird geschrieben
		contentstream.beginText();
		contentstream.setFont(PDType1Font.TIMES_ROMAN, 12);
		contentstream.newLineAtOffset(25, 500);
		String text = "Ihre Bestellung im Detail: ";
		contentstream.showText(text);
		contentstream.endText();
		contentstream.close();
		document.save(file);
		document.close();
		doc.close();
		
		
		
		
		
	}
	
}
