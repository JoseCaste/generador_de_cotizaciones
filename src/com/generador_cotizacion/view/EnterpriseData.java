package com.generador_cotizacion.view;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class EnterpriseData {

	private JFrame frame;
	public JTextField txtEnterpriseName;
	public JTextField txtResponsable;
	public JTextField txtLocatedAt;
	public JTextField txtPhone;
	public JButton btnGuardarDatos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnterpriseData window = new EnterpriseData();
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
	public EnterpriseData() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 763, 303);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1));
		//frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Datos de la empresa cotizadora", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 12, 729, 284);
		panel.setLayout(new GridLayout(0, 2));
		frame.getContentPane().add(panel);
		
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 308, 600, 46);
		frame.getContentPane().add(panel_1);
		
		btnGuardarDatos = new JButton("Guardar datos");
		panel_1.add(btnGuardarDatos);
	}
}
