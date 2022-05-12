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
    private Object[] columnNames = {"Cantidad", "Unidad de medida","Código","Descripción", "Precio U."};
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
    	setIconImage(Toolkit.getDefaultToolkit().getImage(Generador.class.getResource("/com/generador_cotizacion/resources/logo.png")));
    	setTitle("Generador de cotizaciones");
        model = new DefaultTableModel(data, columnNames) {

            private static final long serialVersionUID = 1L;

            @SuppressWarnings("unchecked")
			@Override
            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };
        final ImageIcon iconGenerate = new ImageIcon(
				new ImageIcon(Generador.class.getResource("/com/generador_cotizacion/resources/create_pdf_icon.png"))
						.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        final ImageIcon iconFolder = new ImageIcon(
				new ImageIcon(Generador.class.getResource("/com/generador_cotizacion/resources/folder-yellow-icon.png"))
						.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        
        final ImageIcon iconRemove = new ImageIcon(
				new ImageIcon(Generador.class.getResource("/com/generador_cotizacion/resources/delete_icon.png"))
						.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        final ImageIcon iconAdd = new ImageIcon(
				new ImageIcon(Generador.class.getResource("/com/generador_cotizacion/resources/add-file_icon.png"))
						.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
        
        JPanel panel_header = new JPanel();
        getContentPane().add(panel_header);
        panel_header.setLayout(new GridLayout(0, 3, 0, 0));
        
        JPanel panel_logo = new JPanel();
        panel_header.add(panel_logo);
        panel_logo.setBorder(new TitledBorder(null, "Logo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_logo.setLayout(new GridLayout(0, 1, 0, 25));
        
        JPanel panel = new JPanel();
        panel_logo.add(panel);
        panel.setLayout(null);
        
        lblYourimage = new JLabel("");
        lblYourimage.setBounds(38, 0, 144, 82);
        panel.add(lblYourimage);
        
        btnSeleccionarImagen = new JButton("Seleccionar imagen");
        btnSeleccionarImagen.setIcon(iconFolder);
        panel_logo.add(btnSeleccionarImagen);
        
        JPanel panel_data_client = new JPanel();
        panel_header.add(panel_data_client);
        panel_data_client.setBorder(new TitledBorder(null, "Datos del cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_data_client.setLayout(new GridLayout(0, 2));
        
        JLabel lblNombre = new JLabel("Nombre");
        panel_data_client.add(lblNombre);
        
        txtEName = new JTextField();
        //txtEName.setText("Empresa S.A");
        panel_data_client.add(txtEName);
        txtEName.setColumns(10);
        
        JLabel lblAddress = new JLabel("Domicilio");
        panel_data_client.add(lblAddress);
        
        txtAddress = new JTextField();
        //txtAddress.setText("Patito suarez");
        panel_data_client.add(txtAddress);
        txtAddress.setColumns(10);
        
        JLabel lblCorreoElectrnico = new JLabel("Correo electrónico");
        panel_data_client.add(lblCorreoElectrnico);
        
        txtEmail = new JTextField();
        //txtEmail.setText("abc@gmail.com");
        panel_data_client.add(txtEmail);
        txtEmail.setColumns(10);
        
        JLabel lblTelefonoDeContacto = new JLabel("Telefono de contacto");
        panel_data_client.add(lblTelefonoDeContacto);
        
        txtPhoneNumber = new JTextField();
        //txtPhoneNumber.setText("9871223872");
        panel_data_client.add(txtPhoneNumber);
        txtPhoneNumber.setColumns(10);
        
        JLabel lblAtendi = new JLabel("Atendió");
        panel_data_client.add(lblAtendi);
        
        txtDoneBy = new JTextField();
        //txtDoneBy.setText("Atendido Jimenze");
        panel_data_client.add(txtDoneBy);
        txtDoneBy.setColumns(10);
        
        JPanel panel_cotizacion_number = new JPanel();
        panel_header.add(panel_cotizacion_number);
        panel_cotizacion_number.setBorder((new TitledBorder(null, "Cotización", TitledBorder.LEADING, TitledBorder.TOP, null, null)));
        panel_cotizacion_number.setLayout(null);
        
        JLabel lblCotizacin = new JLabel("Número");
        lblCotizacin.setForeground(Color.BLUE);
        lblCotizacin.setBounds(12, 52, 66, 27);
        panel_cotizacion_number.add(lblCotizacin);
        
        txtNumberCotizacion = new JTextField();
        txtNumberCotizacion.setBounds(79, 52, 114, 27);
        panel_cotizacion_number.add(txtNumberCotizacion);
        txtNumberCotizacion.setColumns(10);
        
        JPanel panel_product_table = new JPanel();
        getContentPane().add(panel_product_table);
        panel_product_table.setLayout(new GridLayout(0, 1, 2, 100));
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
        
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(26, 282, 873, 304);
        //getContentPane().add(scrollPane);
        
        panel_product_table.add(scrollPane);
        btnRemoveRow= new JButton("Remover fila");
        btnRemoveRow.setIcon(iconRemove);
        
        btnRemoveRow.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
            	model.removeRow(table.getSelectedRow());
                
            	System.out.println("model.getRowCount() --->" + model.getRowCount());
            }
        });
        JButton add_rows = new JButton("Agregar fila");
        add_rows.setIcon(iconAdd);
        add_rows.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
            	 updateCol();
            }
        });
        JPanel panel_buttons = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel_buttons.getLayout();
        panel_buttons.add(btnRemoveRow);
        panel_buttons.add(add_rows);
        getContentPane().add(panel_buttons);
        
        btnGenerarCotizacion = new JButton("Generar cotización");
        btnGenerarCotizacion.setIcon(iconGenerate);
        panel_buttons.add(btnGenerarCotizacion);
        
        JLabel label = new JLabel("");
        getContentPane().add(label);
        
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
                /*Object value = table.getValueAt(row == -1 ? 0 : row, col);
                System.out.println(String.valueOf(value));*/
                
            }
        });
       
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu mnConfiguracin = new JMenu("Configuración");
        final ImageIcon iconSettings = new ImageIcon(
				new ImageIcon(Generador.class.getResource("/com/generador_cotizacion/resources/settings_icon.png"))
						.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        mnConfiguracin.setIcon(iconSettings);
        menuBar.add(mnConfiguracin);
        
        menuDatosDeEmpresa = new JMenuItem("Datos de empresa cotizadora");
        final ImageIcon iconEnterpriseSettings = new ImageIcon(
				new ImageIcon(Generador.class.getResource("/com/generador_cotizacion/resources/enterprise_icon.png"))
						.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        menuDatosDeEmpresa.setIcon(iconEnterpriseSettings);
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
