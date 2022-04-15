package com.generador_cotizacion.controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

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
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == generador.btnSeleccionarImagen) {
			openFileChooser();
		}

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
