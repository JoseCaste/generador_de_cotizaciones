package com.generador_cotizacion.controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import com.generador_cotizacion.enums.Elements;
import com.generador_cotizacion.exceptions.ExceptionConvert;
import com.generador_cotizacion.model.CotizadoData;
import com.generador_cotizacion.model.Product;
import com.generador_cotizacion.pdfGenerator.CotizacionGenerador;
import com.generador_cotizacion.view.Generador;

public class GeneradorController implements ActionListener {

	private Generador generador;
	private String imagePath;
	
	public GeneradorController() {}
	
	public GeneradorController(Generador generador) {
		this.generador = generador;
		initListeners();
	}
	
	private void initListeners() {
		this.generador.btnSeleccionarImagen.addActionListener(this);
		this.generador.btnGenerarCotizacion.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == generador.btnSeleccionarImagen) {
			openFileChooser();
		}
		if(arg0.getSource() == generador.btnGenerarCotizacion) {
			createCotizacion();
		}

	}

	private void createCotizacion() {
		DefaultTableModel model = (DefaultTableModel) generador.table.getModel();
		createModelToList(model.getDataVector());
	}

	private void createModelToList(final Vector<?> data) {
		try {
			validateField(data);
			generateCotizacionPDF(data);
		} catch (ExceptionConvert e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}

	private void generateCotizacionPDF(final Vector<?> data) {
		final CotizacionGenerador cotizacionGenerador = new CotizacionGenerador(data);
		CotizadoData enterprise = createDataEnterprise();
		try {
			cotizacionGenerador.createPDF(imagePath, enterprise, data);
			JOptionPane.showMessageDialog(null, "La cotización se ha creado con éxito");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		
	}

	private CotizadoData createDataEnterprise() {
		
		return new CotizadoData(generador.txtEName.getText(), generador.txtResponsable.getText(), generador.txtEmail.getText(), generador.txtPhoneNumber.getText(), generador.txtDoneBy.getText());
	}

	private void validateField(final Vector<?> data) throws ExceptionConvert{
		for (Object object : data) {
			Product product = new Product();
			@SuppressWarnings("unchecked")
			Vector<Object> fields = (Vector<Object> )object;
			try {
				product.setTotalItem(Integer.parseInt(((String) fields.get(Elements.TOTAL.getId())).trim()));
				product.setUnidadMedida((String)fields.get(Elements.UNIDAD_MEDIDA.getId()));
				product.setCodigo((String)fields.get(Elements.CODIGO.getId()));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new ExceptionConvert("La cantidad del producto no es un número válido");
			}
			
			try {
				product.setUnitPrice(Double.parseDouble(((String) fields.get(Elements.UNIT_PRICE.getId())).trim()));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new ExceptionConvert("El precio unitario no es un número válido");
			}
			
			try {
				product.setSale(Integer.parseInt(((String) fields.get(Elements.SALE.getId())).trim()));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				throw new ExceptionConvert("El descuento no es un número válido");
			}
			
		}
		
	}

	private void openFileChooser() {
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("jpg", "png","jpeg"));
		
		if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			imagePath = fileChooser.getSelectedFile().getPath();
			ImageIcon imageIcon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(generador.lblYourimage.getWidth(), generador.lblYourimage.getHeight(), Image.SCALE_DEFAULT));
			generador.lblYourimage.setIcon(imageIcon);
		}
	}

}
