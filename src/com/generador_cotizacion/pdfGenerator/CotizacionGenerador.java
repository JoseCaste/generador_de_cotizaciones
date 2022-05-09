package com.generador_cotizacion.pdfGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Vector;

import javax.swing.JOptionPane;

import org.jasypt.util.text.AES256TextEncryptor;

import com.generador_cotizacion.enums.Elements;
import com.generador_cotizacion.enums.PropertiesKeys;
import com.generador_cotizacion.exceptions.FileException;
import com.generador_cotizacion.model.CotizadoData;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceCmyk;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.DashedBorder;
import com.itextpdf.layout.borders.RoundDotsBorder;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.BorderRadius;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;

public class CotizacionGenerador {

	private static final double IVA = 0.16;
	private Vector<?> data;
	private double totalPrice;

	public CotizacionGenerador(Vector<?> data) {
		this.data = data;
	}

	public boolean createPDF(final String imagePath, final CotizadoData enterprise, final Vector<?> listProducts,
			final String numeroCotizacion) throws Exception {
		try {
			final File file = new File("C:\\Users\\Jose\\OneDrive\\Escritorio\\generate.pdf");
			// final File file = new File("C:\\Users\\Wiliam\\Desktop\\generate.pdf");
			file.getParentFile().mkdir();
			// PdfWriter writer = new PdfWriter("C:\\Users\\Wiliam\\Desktop\\generate.pdf");
			PdfWriter writer = new PdfWriter("C:\\Users\\Jose\\OneDrive\\Escritorio\\generate.pdf");

			PdfDocument pdf = new PdfDocument(writer);

			Document document = new Document(pdf);

			Table table = new Table(UnitValue.createPercentArray(new float[] { 30f, 30f, 30f }));
			final ImageData data = ImageDataFactory.create(imagePath);
			Image img = new Image(data);
			table.setWidth(UnitValue.createPercentValue(100));

			PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
			table.addCell(createImageCell(img));
			table.addCell(createCentralText().setTextAlignment(TextAlignment.CENTER));
			table.addCell(createCotizacionCell("Cotizaci�n N�mero", numeroCotizacion).setBold()
					.setBorder(new RoundDotsBorder(1)).setFont(font).setFontSize(13f));
			document.add(table);

			document.add(
					new Paragraph(new Text("Fecha y hora: ").setFontSize(10f).setBold())
							.add(new Text(LocalDate.now() + " "
									+ LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss"))).setFontSize(10f))
							.setTextAlignment(TextAlignment.RIGHT));
			createCotizadoTable(document, enterprise);
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));

			createTableWithProducts(listProducts, document);

			createBottomText(document);

			document.close();
		} catch (NumberFormatException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("Error al crear el pdf");
		} catch (FileException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	private void createBottomText(Document document) {
		final PdfPage actualPAge = document.getPdfDocument().getLastPage();
		final PdfDocument pdfDocument = document.getPdfDocument();
		 

		String totalInWords = JOptionPane
				.showInputDialog(String.format("Introduza el valor en palabras (%s)", totalPrice)).toUpperCase();
		totalInWords = totalInWords.concat(" 00/100 MXN");
		
		document.add(new Paragraph(totalInWords).setVerticalAlignment(VerticalAlignment.BOTTOM).setFontSize(10f)
				.setTextAlignment(TextAlignment.CENTER));
		document.add(new Paragraph("Precios sujetos a cambios sin previo aviso")
				.setVerticalAlignment(VerticalAlignment.BOTTOM).setFontSize(10f)
				.setTextAlignment(TextAlignment.CENTER));
		document.add(new Paragraph("IVA incluido").setFontSize(10f).setVerticalAlignment(VerticalAlignment.BOTTOM)
				.setTextAlignment(TextAlignment.CENTER));
	}

	private void createCotizadoTable(Document document, CotizadoData cotizadoData) {
		Table tableHeader = new Table(UnitValue.createPercentArray(new float[] {60f}));
		tableHeader.setWidth(UnitValue.createPercentValue(100));
		tableHeader.addCell(simpleCell("Datos del cliente: ").setBackgroundColor(Color.convertRgbToCmyk(new DeviceRgb(88, 87, 87))).setFontColor(Color.convertRgbToCmyk(new DeviceRgb(1, 1, 1)),0));
		
		
		document.add(tableHeader);
		Table table = new Table(UnitValue.createPercentArray(new float[] { 60f, 60f }));
		table.setWidth(UnitValue.createPercentValue(100));
		table.setFontSize(10f);
		table.setBorder(new SolidBorder(Color.convertRgbToCmyk(new DeviceRgb(0, 0, 0)), 1));
		table.setBorderRadius(new BorderRadius(50f));

		table.addCell(new Cell().setBorder(null)
				.add(new Paragraph().add(new Text("Atendi�: ").setBold()).add(new Text(cotizadoData.getAtendidoBy()))));
		table.addCell(new Cell().setBorder(null).add(
				new Paragraph().add(new Text("Cliente: ").setBold()).add(new Text(cotizadoData.getNameEnterprise()))));
		table.addCell(new Cell().setBorder(null)
				.add(new Paragraph().add(new Text("Domicilio: ").setBold()).add(new Text(cotizadoData.getaddress()))));
		table.addCell(new Cell().setBorder(null).add(new Paragraph().add(new Text("Correo electr�nico: ").setBold())
				.add(new Text(cotizadoData.getEmail()))));
		table.addCell(new Cell().setBorder(null)
				.add(new Paragraph().add(new Text("Contacto: ").setBold()).add(new Text(cotizadoData.getPhone()))));
		tableHeader.addFooterCell(table);
		document.add(table);
	}

	private void createTableWithProducts(final Vector<?> listProducts, Document document) {
		Table tableProductos = new Table(UnitValue.createPercentArray(new float[] { 60f, 60f, 60f, 60f, 60f, 60f }));
		tableProductos.setWidth(UnitValue.createPercentValue(100));
		createHeaders(tableProductos);
		createProductCell(tableProductos, listProducts);

		document.add(tableProductos);
		document.add(new Paragraph(String.format("SUBTOTAL                         %s", String.valueOf(totalPrice)))
				.setTextAlignment(TextAlignment.RIGHT).setFontSize(8f));
		document.add(
				new Paragraph(String.format("     IVA                         %s", String.valueOf(totalPrice * IVA)))
						.setTextAlignment(TextAlignment.RIGHT).setFontSize(8f));
		document.add(new Paragraph(
				String.format("   TOTAL                         %s", String.valueOf(totalPrice + (totalPrice * IVA))))
						.setTextAlignment(TextAlignment.RIGHT).setFontSize(8f));
	}

	private void createProductCell(Table tableProductos, final Vector<?> listVector) {

		totalPrice = 0; // counter that has all product import

		for (Object object : listVector) {

			Vector<?> productFields = (Vector<?>) object;
			tableProductos.addCell(simpleCell((String) productFields.get(Elements.TOTAL.getId())).setFontSize(10f));
			tableProductos
					.addCell(simpleCell((String) productFields.get(Elements.UNIDAD_MEDIDA.getId())).setFontSize(10f));
			tableProductos.addCell(simpleCell((String) productFields.get(Elements.CODIGO.getId())).setFontSize(10f));
			tableProductos
					.addCell(simpleCell((String) productFields.get(Elements.DESCRIPTION.getId())).setFontSize(10f));
			tableProductos.addCell(simpleCell((String) productFields.get(Elements.UNIT_PRICE.getId()))
					.setTextAlignment(TextAlignment.RIGHT).setFontSize(8f));

			final double totalImporte = Double.parseDouble((String) productFields.get(Elements.TOTAL.getId()))
					* Double.parseDouble((String) productFields.get(Elements.UNIT_PRICE.getId()));

			// final double totalwithSale = totalWithSale(totalImporte,
			// Integer.parseInt((String)productFields.get(Elements.SALE.getId())));
			tableProductos.addCell(
					simpleCell(String.valueOf(totalImporte)).setTextAlignment(TextAlignment.RIGHT).setFontSize(10f));

			totalPrice += totalImporte;
		}

	}

	private double totalWithSale(final double total, final double sale) {
		double totalImport = total - (total * (sale / 100));
		return totalImport;
	}

	private void createHeaders(Table tableProductos) {
		tableProductos
				.addCell(simpleCell("CANTIDAD").setBackgroundColor(Color.convertRgbToCmyk(new DeviceRgb(88, 87, 87)))
						.setFontColor(Color.convertCmykToRgb(new DeviceCmyk(1, 1, 1, 1)), 1).setBold().setFontSize(10f));
		tableProductos.addCell(simpleCell("UNIDAD DE MEDIDA").setBold()
				.setBackgroundColor(Color.convertRgbToCmyk(new DeviceRgb(88, 87, 87)))
				.setFontColor(Color.convertCmykToRgb(new DeviceCmyk(1, 1, 1, 1)), 1).setBold().setFontSize(10f));
		tableProductos
				.addCell(simpleCell("C�DIGO").setBackgroundColor(Color.convertRgbToCmyk(new DeviceRgb(88, 87, 87)))
						.setFontColor(Color.convertCmykToRgb(new DeviceCmyk(1, 1, 1, 1)), 1).setBold().setFontSize(10f));
		tableProductos
				.addCell(simpleCell("DESCRIPCI�N").setBackgroundColor(Color.convertRgbToCmyk(new DeviceRgb(88, 87, 87)))
						.setFontColor(Color.convertCmykToRgb(new DeviceCmyk(1, 1, 1, 1)), 1).setBold().setFontSize(10f));
		tableProductos
				.addCell(simpleCell("PRECIO UNIT.")
						.setBackgroundColor(Color.convertRgbToCmyk(new DeviceRgb(88, 87, 87)))
						.setFontColor(Color.convertCmykToRgb(new DeviceCmyk(1, 1, 1, 1)), 1).setBold().setFontSize(10f))
				.setTextAlignment(TextAlignment.RIGHT);
		tableProductos
				.addCell(simpleCell("IMPORTE").setBackgroundColor(Color.convertRgbToCmyk(new DeviceRgb(88, 87, 87)))
						.setFontColor(Color.convertCmykToRgb(new DeviceCmyk(1, 1, 1, 1)), 1).setBold().setFontSize(10f))
				.setTextAlignment(TextAlignment.RIGHT);

	}

	private Cell simpleCell(final String text) {
		Cell cell = new Cell();
		cell.add(new Paragraph(text));
		return cell;
	}

	private Cell createImageCell(Image img) {
		// TODO Auto-generated method stub
		Cell cell = new Cell();
		cell.add(img.scaleAbsolute(100f, 100f));
		cell.setBorder(null);
		return cell;
	}

	public static Cell createCell(String text) {
		Cell cell = new Cell();
		cell.add(new Paragraph(text).setPaddingLeft(48).setFontSize(9));
		cell.setBorder(null);
		return cell;
	}

	public Cell createCotizacionCell(final String text, final String cotizacionNumber) {
		Cell cell = new Cell();
		cell.add(new Paragraph(new Text(text))).setPaddingLeft(48).setFontSize(9);
		cell.add(new Paragraph(String.valueOf(cotizacionNumber)).setTextAlignment(TextAlignment.CENTER)
				.setFontColor(Color.convertRgbToCmyk(new DeviceRgb(27, 1, 253))));

		return cell;
	}

	public Cell createCentralText() throws FileException {
		Cell cell = new Cell();
		Properties properties = new Properties();
		// File file = new File("C:\\Users\\Wiliam\\Desktop\\dataenterprise.xml");
		File file = new File("C:\\Users\\Jose\\OneDrive\\Escritorio\\dataenterprise.xml");
		if (!file.canRead())
			throw new FileException();

		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			properties.loadFromXML(fileInputStream);

			AES256TextEncryptor decrypt = new AES256TextEncryptor();
			decrypt.setPassword(PropertiesKeys.PASSWORD.getId());

			cell.add(new Paragraph(decrypt.decrypt(properties.getProperty(PropertiesKeys.NAME.getId()))).setBold());
			cell.add(new Paragraph(decrypt.decrypt(properties.getProperty(PropertiesKeys.RESPONSABLE.getId())))
					.setFontSize(10f));
			cell.add(new Paragraph("Tel: " + decrypt.decrypt(properties.getProperty(PropertiesKeys.PHONE.getId())))
					.setFontSize(10f));
			cell.add(new Paragraph(decrypt.decrypt(properties.getProperty(PropertiesKeys.LOCATED_AT.getId())))
					.setFontSize(8f));

			fileInputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
