package com.generador_cotizacion.view;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.generador_cotizacion.controller.EnterpriseDataController;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Toolkit;

public class EnterpriseData {

	private JFrame frmConfiguracin;
	public JTextField txtEnterpriseName;
	public JTextField txtResponsable;
	public JTextField txtLocatedAt;
	public JTextField txtPhone;
	public JButton btnGuardarDatos;
	public JTextField txtCP;

	/**
	 * Create the application.
	 */
	public EnterpriseData() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConfiguracin = new JFrame();
		frmConfiguracin.setTitle("Configuraci\u00F3n");
		frmConfiguracin.setIconImage(Toolkit.getDefaultToolkit().getImage(EnterpriseData.class.getResource("/com/generador_cotizacion/resources/logo.png")));
		frmConfiguracin.setBounds(100, 100, 763, 448);
		frmConfiguracin.setLocationRelativeTo(null);
		frmConfiguracin.getContentPane().setLayout(new GridLayout(0, 1));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Datos de la empresa cotizadora", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 12, 729, 400);
		panel.setLayout(new GridLayout(0, 2));
		frmConfiguracin.getContentPane().add(panel);
		
		JLabel lblNombreDeLa = new JLabel("Nombre de la empresa");
		panel.add(lblNombreDeLa);
		
		txtEnterpriseName = new JTextField();
		panel.add(txtEnterpriseName);
		txtEnterpriseName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Responsable");
		panel.add(lblNewLabel);
		
		txtResponsable = new JTextField();
		panel.add(txtResponsable);
		txtResponsable.setColumns(10);
		
		JLabel lblUbicacin = new JLabel("Ubicación");
		panel.add(lblUbicacin);
		
		txtLocatedAt = new JTextField();
		panel.add(txtLocatedAt);
		txtLocatedAt.setColumns(10);
		
		JLabel lblContacto = new JLabel("Contacto");
		panel.add(lblContacto);
		
		txtPhone = new JTextField();
		panel.add(txtPhone);
		txtPhone.setColumns(10);
		
		JLabel lblCP = new JLabel("Código Postal");
		panel.add(lblCP);
		
		txtCP = new JTextField();
		panel.add(txtCP);
		txtCP.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 308, 600, 46);
		frmConfiguracin.getContentPane().add(panel_1);
		
		btnGuardarDatos = new JButton("Guardar datos");
		final ImageIcon iconSave = new ImageIcon(
				new ImageIcon(EnterpriseData.class.getResource("/com/generador_cotizacion/resources/save_icon.png"))
						.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		btnGuardarDatos.setIcon(iconSave);
		panel_1.add(btnGuardarDatos);
		
		@SuppressWarnings("unused")
		EnterpriseDataController controller = new EnterpriseDataController(this);
	}

	public JFrame getFrame() {
		return frmConfiguracin;
	}

	public void setFrame(JFrame frame) {
		this.frmConfiguracin = frame;
	}
	
	
}
