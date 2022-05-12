package com.generador_cotizacion.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

import com.generador_cotizacion.controller.GeneradorController;
import com.generador_cotizacion.enums.Elements;

import javax.swing.border.TitledBorder;

public class Generador extends JFrame {

    private static final long serialVersionUID = 1L;
    private Object[] columnNames = {"Cantidad", "Unidad de medida","C�digo","Descripci�n", "Precio U."};
    public Object[][] data = {
        {"","", "","",""}
    };
    public JTable table;
    public DefaultTableModel model;
    public JButton btnSeleccionarImagen;
	public JLabel lblYourimage;
	public JTextField txtNumberCotizacion;
	public JTextField txtAddress;
	public JTextField txtEName;
	public JTextField txtEmail;
	public JTextField txtPhoneNumber;
	public JTextField txtDoneBy;
	public JButton btnGenerarCotizacion;
	public JMenuItem menuDatosDeEmpresa;
	public JButton btnRemoveRow;

    public Generador() {
        model = new DefaultTableModel(data, columnNames) {

            private static final long serialVersionUID = 1L;

            @SuppressWarnings("unchecked")
			@Override
            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };
        table = new JTable(model) {

            private static final long serialVersionUID = 1L;

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (isRowSelected(row) && isColumnSelected(column)) {
                    ((JComponent) c).setBorder(new LineBorder(Color.red));
                }
                return c;
            }
        };
        ListSelectionModel rowSelMod = table.getSelectionModel();
        rowSelMod.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                int row = table.getSelectedRow();
                int col = table.getSelectedColumn();
                String str = "Selected Row(s): ";
                int[] rows = table.getSelectedRows();
                for (int i = 0; i < rows.length; i++) {
                    str += rows[i] + " ";
                }
                str += "Selected Column(s): ";
                int[] cols = table.getSelectedColumns();
                for (int i = 0; i < cols.length; i++) {
                    str += cols[i] + " ";
                }
                str += "Selected Cell: " + table.getSelectedRow() + ", " + table.getSelectedColumn();
                System.out.println(str);
                Object value = table.getValueAt(row == -1 ? 0 : row, col);
                System.out.println(String.valueOf(value));
                
            }
        });
        getContentPane().setLayout(null);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(26, 292, 863, 304);
        getContentPane().add(scrollPane);
        btnRemoveRow= new JButton("Remover fila");
        
        btnRemoveRow.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
            	model.removeRow(table.getSelectedRow());
                
            	System.out.println("model.getRowCount() --->" + model.getRowCount());
            }
        });
        JButton button2 = new JButton("Agregar fila");
        button2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
            	 updateCol();
            }
        });
        JPanel southPanel = new JPanel();
        southPanel.setBounds(12, 608, 877, 41);
        southPanel.add(btnRemoveRow);
        southPanel.add(button2);
        getContentPane().add(southPanel);
        
        btnGenerarCotizacion = new JButton("Generar cotizaci�n");
        southPanel.add(btnGenerarCotizacion);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(new TitledBorder(null, "Logo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(26, 12, 221, 258);
        getContentPane().add(panel);
        
        btnSeleccionarImagen = new JButton("Seleccionar imagen");
        btnSeleccionarImagen.setBounds(12, 208, 197, 25);
        final ImageIcon iconFolder = new ImageIcon(
				new ImageIcon(Generador.class.getResource("/com/generador_cotizacion/resources/folder-yellow-icon.png"))
						.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		btnSeleccionarImagen.setIcon(iconFolder);
        panel.add(btnSeleccionarImagen);
        
        lblYourimage = new JLabel("");
        lblYourimage.setBounds(12, 24, 197, 172);
        panel.add(lblYourimage);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBorder((new TitledBorder(null, "Cotizaci�n", TitledBorder.LEADING, TitledBorder.TOP, null, null)));
        panel_1.setBounds(668, 12, 221, 146);
        getContentPane().add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblCotizacin = new JLabel("N�mero");
        lblCotizacin.setForeground(Color.BLUE);
        lblCotizacin.setBounds(12, 52, 66, 27);
        panel_1.add(lblCotizacin);
        
        txtNumberCotizacion = new JTextField();
        txtNumberCotizacion.setBounds(79, 52, 114, 27);
        panel_1.add(txtNumberCotizacion);
        txtNumberCotizacion.setColumns(10);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new TitledBorder(null, "Datos del cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_2.setBounds(259, 12, 397, 223);
        getContentPane().add(panel_2);
        panel_2.setLayout(new GridLayout(0, 2));
        
        JLabel lblNombre = new JLabel("Nombre");
        panel_2.add(lblNombre);
        
        txtEName = new JTextField();
        //txtEName.setText("Empresa S.A");
        panel_2.add(txtEName);
        txtEName.setColumns(10);
        
        JLabel lblAddress = new JLabel("Domicilio");
        panel_2.add(lblAddress);
        
        txtAddress = new JTextField();
        //txtAddress.setText("Patito suarez");
        panel_2.add(txtAddress);
        txtAddress.setColumns(10);
        
        JLabel lblCorreoElectrnico = new JLabel("Correo electr�nico");
        panel_2.add(lblCorreoElectrnico);
        
        txtEmail = new JTextField();
        //txtEmail.setText("abc@gmail.com");
        panel_2.add(txtEmail);
        txtEmail.setColumns(10);
        
        JLabel lblTelefonoDeContacto = new JLabel("Telefono de contacto");
        panel_2.add(lblTelefonoDeContacto);
        
        txtPhoneNumber = new JTextField();
        //txtPhoneNumber.setText("9871223872");
        panel_2.add(txtPhoneNumber);
        txtPhoneNumber.setColumns(10);
        
        JLabel lblAtendi = new JLabel("Atendi�");
        panel_2.add(lblAtendi);
        
        txtDoneBy = new JTextField();
        //txtDoneBy.setText("Atendido Jimenze");
        panel_2.add(txtDoneBy);
        txtDoneBy.setColumns(10);
       
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu mnConfiguracin = new JMenu("Configuraci�n");
        menuBar.add(mnConfiguracin);
        
        menuDatosDeEmpresa = new JMenuItem("Datos de empresa cotizadora");
        mnConfiguracin.add(menuDatosDeEmpresa);
        
        GeneradorController generadorController = new GeneradorController(this);
    }

    public void updateCol(){
    	DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object[] data0 = {"","", "","","",""};
        model.addRow(data0);
        
        System.out.println("model.getRowCount() --->" + model.getRowCount());
        
        int rowIndex = table.getRowCount() - 1;
        table.changeSelection(rowIndex, 0, false, false);
    }
 

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
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                Generador frame = new Generador();
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                frame.pack();
                frame.setBounds(200, 250, 950, 700);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
