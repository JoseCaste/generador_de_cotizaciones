package com.generador_cotizacion.controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.generador_cotizacion.enums.Elements;
import com.generador_cotizacion.exceptions.ExceptionConvert;
import com.generador_cotizacion.model.CotizadoData;
import com.generador_cotizacion.model.Product;
import com.generador_cotizacion.pdfGenerator.CotizacionGenerador;
import com.generador_cotizacion.view.EnterpriseData;
import com.generador_cotizacion.view.Generador;

public class GeneradorController implements ActionListener {

	private Generador generador;
	private String imagePath;
	private Logger logger = LogManager.getLogger(GeneradorController.class);

	public GeneradorController() {
	}

	public GeneradorController(Generador generador) {
		this.generador = generador;
		initListeners();
	}

	private void initListeners() {
		this.generador.btnSeleccionarImagen.addActionListener(this);
		this.generador.btnGenerarCotizacion.addActionListener(this);
		this.generador.menuDatosDeEmpresa.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (Objects.equals(arg0.getSource(), generador.btnSeleccionarImagen)) {
			openFileChooser();
		}
		if (Objects.equals(arg0.getSource(), generador.btnGenerarCotizacion)) {
			createCotizacion();
		}
		if (Objects.equals(arg0.getSource(), generador.menuDatosDeEmpresa)) {
			EnterpriseData enterpriseData = new EnterpriseData();
			enterpriseData.getFrame().setVisible(true);
		}

	}

	private void createCotizacion() {
		DefaultTableModel model = (DefaultTableModel) generador.table.getModel();
		createModelToList(model.getDataVector());
	}

	private void createModelToList(final Vector<?> data) {
		try {
			if(imagePath == null || imagePath.equals(""))
				throw new Exception("El logo no ha sido sido seleccionado");
			validateField(data);
			generateCotizacionPDF(data);
		} catch (ExceptionConvert e) {
			logger.error("Error createModelToList {}", e);
			JOptionPane.showMessageDialog(null, e.getMessage());
		}catch (Exception e) {
			logger.error("Error createModelToList {}", e);
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		

	}

	private void generateCotizacionPDF(final Vector<?> data) {
		final CotizacionGenerador cotizacionGenerador = new CotizacionGenerador(data);
		CotizadoData enterprise = createDataEnterprise();
		try {
			cotizacionGenerador.createPDF(imagePath, enterprise, data,
					generador.txtNumberCotizacion.getText());
			JOptionPane.showMessageDialog(null, "La cotizaci?n se ha creado con ?xito");
			
			if(JOptionPane.showConfirmDialog(null, "?Borrar los datos de esta tabla?","Seleccione una opcion",JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
				for (int i = this.generador.model.getRowCount() - 1; i >=0 ; i--) {
					this.generador.model.removeRow(i);
                }
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("Error generateCotizacionPDF {}", e);
			JOptionPane.showMessageDialog(generador, e.getMessage());
		}

	}

	private CotizadoData createDataEnterprise() {

		return new CotizadoData(generador.txtEName.getText(),generador.txtAddress.getText(),
				generador.txtEmail.getText(), generador.txtPhoneNumber.getText(), generador.txtDoneBy.getText());
	}

	private void validateField(final Vector<?> data) throws ExceptionConvert {
		for (Object object : data) {
			Product product = new Product();
			@SuppressWarnings("unchecked")
			Vector<Object> fields = (Vector<Object>) object;
			try {
				product.setTotalItem(Integer.parseInt(((String) fields.get(Elements.TOTAL.getId())).trim()));
				product.setUnidadMedida((String) fields.get(Elements.UNIDAD_MEDIDA.getId()));
				product.setCodigo((String) fields.get(Elements.CODIGO.getId()));

			} catch (NumberFormatException e) {
				logger.error("Error al convertir {} {}",String.format("La cantidad del producto con nombre %s no es un n?mero v?lido", (String) fields.get(Elements.CODIGO.getId())),e);
				e.printStackTrace();
				throw new ExceptionConvert(String.format("La cantidad del producto con nombre %s no es un n?mero v?lido", (String) fields.get(Elements.CODIGO.getId())));
			}

			try {
				product.setUnitPrice(Double.parseDouble(((String) fields.get(Elements.UNIT_PRICE.getId())).trim()));
			} catch (NumberFormatException e) {
				logger.error("Error al convertir {} {}",String.format("El precio unitario del producto %s no es un n?mero v?lido", (String) fields.get(Elements.CODIGO.getId())),e);
				e.printStackTrace();
				throw new ExceptionConvert(String.format("El precio unitario del producto %s no es un n?mero v?lido", (String) fields.get(Elements.CODIGO.getId())));
			}

		}

	}

	private void openFileChooser() {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("jpg", "png", "jpeg"));

		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			imagePath = fileChooser.getSelectedFile().getPath();
			ImageIcon imageIcon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(
					generador.lblYourimage.getWidth(), generador.lblYourimage.getHeight(), Image.SCALE_DEFAULT));
			generador.lblYourimage.setIcon(imageIcon);
		}
	}

}
