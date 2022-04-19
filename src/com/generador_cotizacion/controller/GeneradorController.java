package com.generador_cotizacion.controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import com.generador_cotizacion.view.Generador;

public class GeneradorController implements ActionListener {

	private Generador generador;
	
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
		List<Object> data = model.getDataVector().stream().collect(Collectors.toCollection(ArrayList::new));
		data.forEach(System.out::println);
	}

	private void openFileChooser() {
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("jpg", "png","jpeg"));
		
		if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			final String imagePath = fileChooser.getSelectedFile().getPath();
			ImageIcon imageIcon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(generador.lblYourimage.getWidth(), generador.lblYourimage.getHeight(), Image.SCALE_DEFAULT));
			generador.lblYourimage.setIcon(imageIcon);
		}
	}

}
