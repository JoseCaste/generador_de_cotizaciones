package com.generador_cotizacion.pdfGenerator;

import java.io.File;
import java.util.Vector;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;

public class CotizacionGenerador {

	private Vector<?> data;

	public CotizacionGenerador(Vector<?> data) {
		this.data = data;
	}

	public boolean createPDF(final String imagePath) {
		try {
			final File file = new File("/home/jose/Documents/PDFs/generate.pdf");
			file.getParentFile().mkdir();
			PdfWriter writer = new PdfWriter("/home/jose/Documents/PDFs/generate.pdf");
	        
	        PdfDocument pdf = new PdfDocument(writer);
	        
	        Document document = new Document(pdf);
	        
	        Table table= new Table(UnitValue.createPercentArray(new float[]{15f, 30f}));
	        final ImageData data = ImageDataFactory.create(imagePath);
	        Image img = new Image(data);
	        
	        //table.addCell(img.setAutoScale(true));
	        table.addCell(createCell("Imagen"));
	        table.addCell(createCell(imagePath));
	        document.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
        
		return true;
	}
	public static Cell createCell(String text) {
		Cell cell= new Cell();
		cell.add(new Paragraph(text).setPaddingLeft(48).setFontSize(9));
		cell.setBorder(null);
		return cell;
	}
	
	public Vector<?> getData() {
		return data;
	}

	public void setData(Vector<?> data) {
		this.data = data;
	}
	
	
}
