package com.generador_cotizacion.pdfGenerator;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.generador_cotizacion.enums.Elements;
import com.generador_cotizacion.model.DataEnterprise;
import com.generador_cotizacion.model.Product;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.RoundDotsBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

public class CotizacionGenerador {

	private Vector<?> data;
	private int totalPrice;

	public CotizacionGenerador(Vector<?> data) {
		this.data = data;
	}

	public boolean createPDF(final String imagePath, final DataEnterprise enterprise, final Vector<?> listProducts) {
		try {
			final File file = new File("/home/jose/Documents/PDFs/generate.pdf");
			file.getParentFile().mkdir();
			PdfWriter writer = new PdfWriter("/home/jose/Documents/PDFs/generate.pdf");
	        
	        PdfDocument pdf = new PdfDocument(writer);
	        
	        Document document = new Document(pdf);
	        
	        Table table= new Table(UnitValue.createPercentArray(new float[]{30f, 30f,30f}));
	        final ImageData data = ImageDataFactory.create(imagePath);
	        Image img = new Image(data);
	        
	        PdfFont font= PdfFontFactory.createFont(StandardFonts.HELVETICA);
	        table.addCell(createImageCell(img));
	        table.addCell(createCentralText(enterprise).setTextAlignment(TextAlignment.CENTER));
	        table.addCell(createCotizacionCell("Cotización Número",12).setBold().setBorder(new RoundDotsBorder(1)).setFont(font).setFontSize(13f));
	        document.add(table);
	       
	        document.add(new Paragraph(new Text("Fecha y hora: ").setFontSize(10f).setBold()).add(new Text(LocalDate.now()+" "+LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss"))).setFontSize(10f)).setTextAlignment(TextAlignment.RIGHT));
	        document.add(new Paragraph("A continuación se presentamos nuestra oferta que esperamos que sea de su conformidad"));
	        
	        createTableWithProducts(listProducts, document);
	        
	        document.add(new Paragraph("Precios sujetos a cambios sin previo aviso").setFontSize(10f).setTextAlignment(TextAlignment.CENTER));
	        document.add(new Paragraph("IVA incluido").setFontSize(10f).setTextAlignment(TextAlignment.CENTER));
	        document.close();
	        JOptionPane.showMessageDialog(null, "La cotización se ha creado con éxito");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
        
		return true;
	}
	private void createTableWithProducts(final Vector<?> listProducts, Document document) {
		Table tableProductos = new Table(UnitValue.createPercentArray(new float[] {60f,60f,60f,60f,60f,60f,60f}));
		tableProductos.setWidth(UnitValue.createPercentValue(100));
		createHeaders(tableProductos);
		createProductCell(tableProductos, listProducts);
		
		document.add(tableProductos);
		
		document.add(new Paragraph(String.format("TOTAL                                     %d", totalPrice)).setTextAlignment(TextAlignment.RIGHT).setFontSize(8f));
	}

	private void createProductCell(Table tableProductos, final Vector<?> listVector) {
		
		totalPrice = 0; //counter that has all product import
		
		for (Object object : listVector) {
			
			Vector<?> productFields = (Vector<?>) object;
			tableProductos.addCell(simpleCell((String) productFields.get(Elements.TOTAL.getId())).setFontSize(8f));
			tableProductos.addCell(simpleCell((String)productFields.get(Elements.UNIDAD_MEDIDA.getId())).setFontSize(8f));
			tableProductos.addCell(simpleCell((String)productFields.get(Elements.CODIGO.getId())).setFontSize(8f));
			tableProductos.addCell(simpleCell((String)productFields.get(Elements.DESCRIPTION.getId())).setFontSize(8f));
			tableProductos.addCell(simpleCell((String)productFields.get(Elements.UNIT_PRICE.getId())).setTextAlignment(TextAlignment.RIGHT).setFontSize(8f));
			tableProductos.addCell(simpleCell((String)productFields.get(Elements.SALE.getId())).setTextAlignment(TextAlignment.RIGHT).setFontSize(8f));
			tableProductos.addCell(simpleCell((String)productFields.get(Elements.IMPORTE.getId())).setTextAlignment(TextAlignment.RIGHT).setFontSize(8f));
			totalPrice += Integer.parseInt((String)productFields.get(Elements.IMPORTE.getId()));
		}
		
		
	}

	private void createHeaders(Table tableProductos) {
		tableProductos.addCell(simpleCell("Cantidad").setBold().setFontSize(8f));
		tableProductos.addCell(simpleCell("Unidad de medida").setBold().setFontSize(8f));
		tableProductos.addCell(simpleCell("Código").setBold().setFontSize(8f));
		tableProductos.addCell(simpleCell("Descripción").setBold().setFontSize(8f));
		tableProductos.addCell(simpleCell("Precio U.").setBold().setFontSize(8f).setTextAlignment(TextAlignment.RIGHT));
		tableProductos.addCell(simpleCell("Descuento %").setBold().setFontSize(8f).setTextAlignment(TextAlignment.RIGHT));
		tableProductos.addCell(simpleCell("Importe").setBold().setFontSize(8f).setTextAlignment(TextAlignment.RIGHT));
		
	}
	private Cell simpleCell(final String text) {
		Cell cell = new Cell();
		cell.add(new Paragraph(text));
		return cell;
	}

	private Cell createImageCell(Image img) {
		// TODO Auto-generated method stub
		Cell cell = new Cell();
		cell.add(img.setAutoScale(true));
		cell.setBorder(null);
		return cell;
	}

	public static Cell createCell(String text) {
		Cell cell= new Cell();
		cell.add(new Paragraph(text).setPaddingLeft(48).setFontSize(9));
		cell.setBorder(null);
		return cell;
	}
	
	public Cell createCotizacionCell(final String text, final int cotizacionNumber) {
		Cell cell= new Cell();
		cell.add(new Paragraph(new Text(text))).setPaddingLeft(48).setFontSize(9);
		cell.add(new Paragraph(String.valueOf(cotizacionNumber)).setTextAlignment(TextAlignment.CENTER).setFontColor(Color.convertRgbToCmyk(new DeviceRgb(27, 1, 253))));
		
		return cell;
	}
	
	public Cell createCentralText(DataEnterprise enterprise) {
		Cell cell = new Cell();
		cell.add(new Paragraph(enterprise.getNameEnterprise()).setBold());
		cell.add(new Paragraph(enterprise.getResponsable()).setFontSize(10f));
		cell.add(new Paragraph(enterprise.getEmail()).setFontSize(10f));
		cell.add(new Paragraph(enterprise.getPhone()).setFontSize(10f));
		cell.add(new Paragraph(enterprise.getAtendidoBy()).setFontSize(10f));
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