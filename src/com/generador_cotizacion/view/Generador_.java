package com.generador_cotizacion.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.generador_cotizacion.controller.GeneradorController;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class Generador_ {

	private JFrame frame;
	public JButton btnSeleccionarImagen;
	public JLabel lblYourimage;
	private JTable table;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Generador_ window = new Generador_();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Generador_() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 950, 628);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Logo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 12, 221, 258);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		btnSeleccionarImagen = new JButton("Seleccionar imagen");
		final ImageIcon iconFolder = new ImageIcon(
				new ImageIcon(Generador_.class.getResource("/com/generador_cotizacion/resources/folder-yellow-icon.png"))
						.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		btnSeleccionarImagen.setIcon(iconFolder);
		btnSeleccionarImagen.setBounds(12, 208, 197, 25);
		panel.add(btnSeleccionarImagen);

		lblYourimage = new JLabel("");
		lblYourimage.setBounds(12, 12, 197, 184);
		panel.add(lblYourimage);


		

		//GeneradorController generadorController = new GeneradorController(this);
	}
}
