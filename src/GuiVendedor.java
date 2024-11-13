import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GuiVendedor extends JFrame {

    static int monto;
    private JLabel LabelImagen1;
    static int Tot, Total;
    private Icon icono;
    private ImageIcon imagen;

    public GuiVendedor() {
        setLayout(null);
        setBounds(0, 0, 800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel MainPanel = new JPanel();
        setContentPane(MainPanel);
        getContentPane();
        MainPanel.setLayout(null);
        MainPanel.setBackground(new Color(220, 199, 165));


        JPanel PanelAgregar = new JPanel();
        getContentPane();
        PanelAgregar.setLayout(null);
        PanelAgregar.setBackground(new Color(220, 199, 165));

        JPanel PanelEliminar = new JPanel();
        getContentPane();
        PanelEliminar.setLayout(null);
        PanelEliminar.setBackground(new Color(220, 199, 165));

        JPanel PanelActualizar = new JPanel();
        getContentPane();
        PanelActualizar.setLayout(null);
        PanelActualizar.setBackground(new Color(220, 199, 165));

        // Boton para hacer visible el panel de Agregar
        JButton Agregar = new JButton("AGREGAR");
        Agregar.setBounds(300, 70, 200, 45);
        Agregar.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
        Agregar.setBackground(new Color(160, 82, 45));
        Agregar.setForeground(new Color(61, 43, 31));
        Agregar.setBorder(BorderFactory.createLineBorder(new Color(92, 51, 23), 2));
        Agregar.setFocusPainted(false);
        Agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPanel.setVisible(false);
                PanelAgregar.setVisible(true);
                setContentPane(PanelAgregar);
            }
        });

        JButton Mostrar = new JButton("MOSTRAR");
        Mostrar.setBounds(300, 150, 200, 45);
        Mostrar.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
        Mostrar.setBackground(new Color(160, 82, 45));
        Mostrar.setForeground(new Color(61, 43, 31));
        Mostrar.setBorder(BorderFactory.createLineBorder(new Color(92, 51, 23), 2));
        Mostrar.setFocusPainted(false);
        Mostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conexion = null;
                PreparedStatement ptm = null;
                ResultSet result = null;

                try {

                    conexion = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/db_tienda?verifyServerCertificate=false&useSSL=true", "root",
                            "Deiby_R04");
                    conexion.setAutoCommit(true);
                    String Mostrar = "{CALL mostrar_vendedores}";
                    ptm = conexion.prepareStatement(Mostrar);
                    result = ptm.executeQuery();

                    ResultSetMetaData resul = result.getMetaData();
                    int Columnas = resul.getColumnCount();

                    DefaultTableModel tabla = new DefaultTableModel();
                    for (int a = 1; a <= Columnas; a++) {
                        tabla.addColumn(resul.getColumnName(a));
                    }

                    while (result.next()) {
                        Object[] fila = new Object[Columnas];
                        for (int i = 1; i <= Columnas; i++) {
                            fila[i - 1] = result.getObject(i);
                        }
                        tabla.addRow(fila);

                    }

                    JTable table = new JTable(tabla);
                    JScrollPane scrollPane = new JScrollPane(table);

                    // Mostrar la tabla en un JOptionPane
                    JOptionPane.showMessageDialog(null, scrollPane, "Base de datos de mi tienda",
                            JOptionPane.INFORMATION_MESSAGE);

                } catch (SQLException e1) {
                    e1.printStackTrace();
                } finally {
                    // Cerrar los recursos
                    try {
                        if (result != null)
                            result.close();
                        if (ptm != null)
                            ptm.close();
                        if (conexion != null)
                            conexion.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();

                    }
                }

            }
        });

        JButton Eliminar = new JButton("ELIMINAR");
        Eliminar.setBounds(300, 230, 200, 45);
        Eliminar.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
        Eliminar.setBackground(new Color(160, 82, 45));
        Eliminar.setForeground(new Color(61, 43, 31));
        Eliminar.setBorder(BorderFactory.createLineBorder(new Color(92, 51, 23), 2));
        Eliminar.setFocusPainted(false);
        Eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MainPanel.setVisible(false);
                PanelEliminar.setVisible(true);
                setContentPane(PanelEliminar);

            }
        });

        JButton Actualizar = new JButton("ACTUALIZAR");
        Actualizar.setBounds(300, 310, 200, 45);
        Actualizar.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
        Actualizar.setBackground(new Color(160, 82, 45));
        Actualizar.setForeground(new Color(61, 43, 31));
        Actualizar.setBorder(BorderFactory.createLineBorder(new Color(92, 51, 23), 2));
        Actualizar.setFocusPainted(false);
        Actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPanel.setVisible(false);
                PanelActualizar.setVisible(true);
                setContentPane(PanelActualizar);
            }
        });

        JButton Regresar = new JButton("REGRESAR");
        Regresar.setBounds(300, 390, 200, 45);
        Regresar.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
        Regresar.setBackground(new Color(160, 82, 45));
        Regresar.setForeground(new Color(61, 43, 31));
        Regresar.setBorder(BorderFactory.createLineBorder(new Color(92, 51, 23), 2));
        Regresar.setFocusPainted(false);
        Regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Eleccion login = new Eleccion();
                login.setVisible(true);
                dispose();
            }
        });

        // Componentes del PanelAgregar
        JLabel Nombre = new JLabel("Nombre");
        Nombre.setBounds(150, 120, 100, 40);
        Nombre.setForeground(new Color(61, 43, 31));
        Nombre.setBorder(null);
        Font fuente = new Font("Agency FB", Font.BOLD, 20);
        Nombre.setFont(fuente);
        PanelAgregar.add(Nombre);

        JLabel Apellido1 = new JLabel("Primer apellido");
        Apellido1.setBounds(150, 180, 100, 40);
        Apellido1.setForeground(new Color(61, 43, 31));
        Apellido1.setFont(fuente);
        Apellido1.setBorder(null);
        PanelAgregar.add(Apellido1);

        JLabel ID = new JLabel("Cédula");
        ID.setBounds(150, 240, 100, 40);
        ID.setForeground(new Color(61, 43, 31));
        ID.setFont(fuente);
        ID.setBorder(null);
        PanelAgregar.add(ID);

        JTextField TextNombre = new JTextField("");
        TextNombre.setBounds(270, 120, 150, 40);
        TextNombre.setBackground(new Color(101, 237, 225));
        TextNombre.setForeground(Color.black);
        TextNombre.setBorder(null);
        PanelAgregar.add(TextNombre);

        JTextField TextApellido = new JTextField("");
        TextApellido.setBounds(270, 180, 150, 40);
        TextApellido.setBackground(new Color(101, 237, 225));
        TextApellido.setForeground(Color.black);
        TextApellido.setBorder(null);
        PanelAgregar.add(TextApellido);

        JTextField TextID = new JTextField("");
        TextID.setBounds(270, 240, 150, 40);
        TextID.setBackground(new Color(101, 237, 225));
        TextID.setForeground(Color.black);
        TextID.setBorder(null);
        PanelAgregar.add(TextID);

        JButton AgregarPersona = new JButton("Agregar");
        AgregarPersona.setBounds(200, 330, 150, 40);
        AgregarPersona.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
        AgregarPersona.setBackground(new Color(160, 82, 45));
        AgregarPersona.setForeground(new Color(61, 43, 31));
        AgregarPersona.setBorder(BorderFactory.createLineBorder(new Color(92, 51, 23), 2));
        AgregarPersona.setFocusPainted(false);
        AgregarPersona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Nombre = TextNombre.getText();
                String Id = TextID.getText();
                String Ap1 = TextApellido.getText();

                Connection conexion = null;
                PreparedStatement preparar = null;

                String SQL = "{CALL agregar_vendedor(?,?,?)}";

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    conexion = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/db_tienda?verifyServerCertificate=false&useSSL=true", "root",
                            "Deiby_R04");
                    conexion.setAutoCommit(true);

                    preparar = conexion.prepareStatement(SQL);
                    preparar.setString(2, Nombre);
                    preparar.setString(3, Ap1);
                    preparar.setString(1, Id);

                    int exito = preparar.executeUpdate();

                    if (exito > 0) {
                        JOptionPane.showMessageDialog(null, "La persona se ha agregado a la base de datos");
                    }

                } catch (Exception ew) {
                    ew.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
                } finally {

                    try {
                        if (preparar != null)
                            preparar.close();
                        if (conexion != null)
                            conexion.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                TextNombre.setText("");
                TextApellido.setText("");
                TextID.setText("");
            }
        });
        PanelAgregar.add(AgregarPersona);

        JButton Salir = new JButton("Salir");
        Salir.setBounds(450, 330, 150, 40);
        Salir.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
        Salir.setBackground(new Color(160, 82, 45));
        Salir.setForeground(new Color(61, 43, 31));
        Salir.setBorder(BorderFactory.createLineBorder(new Color(92, 51, 23), 2));
        Salir.setFocusPainted(false);
        Salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPanel.setVisible(true);
                PanelAgregar.setVisible(false);
                setContentPane(MainPanel);
            }
        });
        PanelAgregar.add(Salir);

        // Componentes del Actualizar
        JLabel NombreNuevo = new JLabel("Nombre nuevo");
        NombreNuevo.setBounds(150, 180, 100, 40);
        NombreNuevo.setForeground(new Color(61, 43, 31));
        NombreNuevo.setBorder(null);
        NombreNuevo.setFont(fuente);
        PanelActualizar.add(NombreNuevo);

        JLabel ApellidoNuevo = new JLabel("Apellido Nuevo");
        ApellidoNuevo.setBounds(150, 240, 100, 40);
        ApellidoNuevo.setForeground(new Color(61, 43, 31));
        ApellidoNuevo.setFont(fuente);
        ApellidoNuevo.setBorder(null);
        PanelActualizar.add(ApellidoNuevo);

        JLabel Ced = new JLabel("Cedula del vendedor");
        Ced.setBounds(150, 120, 150, 40);
        Ced.setForeground(new Color(61, 43, 31));
        Ced.setFont(fuente);
        Ced.setBorder(null);
        PanelActualizar.add(Ced);

        JTextField TextNombreNuevo = new JTextField("");
        TextNombreNuevo.setBounds(300, 180, 150, 40);
        TextNombreNuevo.setBackground(new Color(101, 237, 225));
        TextNombreNuevo.setForeground(new Color(61, 43, 31));
        TextNombreNuevo.setBorder(null);
        PanelActualizar.add(TextNombreNuevo);

        JTextField TextApellidoNuevo = new JTextField("");
        TextApellidoNuevo.setBounds(300, 240, 150, 40);
        TextApellidoNuevo.setBackground(new Color(101, 237, 225));
        TextApellidoNuevo.setForeground(new Color(61, 43, 31));
        TextApellidoNuevo.setBorder(null);
        PanelActualizar.add(TextApellidoNuevo);

        JTextField TextCed = new JTextField("");
        TextCed.setBounds(300, 120, 150, 40);
        TextCed.setBackground(new Color(101, 237, 225));
        TextCed.setForeground(new Color(61, 43, 31));
        TextCed.setBorder(null);
        PanelActualizar.add(TextCed);

        JButton ActualizarVendedor = new JButton("Actualizar");
        ActualizarVendedor.setBounds(200, 330, 150, 40);
        ActualizarVendedor.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
        ActualizarVendedor.setBackground(new Color(160, 82, 45));
        ActualizarVendedor.setForeground(new Color(61, 43, 31));
        ActualizarVendedor.setBorder(BorderFactory.createLineBorder(new Color(92, 51, 23), 2));
        ActualizarVendedor.setFocusPainted(false);
        ActualizarVendedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Nombre = TextNombreNuevo.getText();
                String Id = TextCed.getText();
                String ApNu = TextApellidoNuevo.getText();

                Connection conexion = null;
                PreparedStatement preparar = null;

                String SQL = "{CALL actualizar_vendedor(?, ?, ?)}";

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    conexion = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/db_tienda?verifyServerCertificate=false&useSSL=true",
                            "root", "Deiby_R04");
                    conexion.setAutoCommit(true);

                    preparar = conexion.prepareStatement(SQL);
                    preparar.setString(1, Nombre);
                    preparar.setString(2, ApNu);
                    preparar.setString(3, Id);

                    int exito = preparar.executeUpdate();

                    if (exito > 0) {
                        JOptionPane.showMessageDialog(null, "Se actualizaron los datos correctamente");
                    }

                } catch (Exception ew) {
                    ew.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
                } finally {
                    try {
                        if (preparar != null)
                            preparar.close();
                        if (conexion != null)
                            conexion.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                TextNombreNuevo.setText("");
                TextApellidoNuevo.setText("");
                TextCed.setText("");
            }
        });
        PanelActualizar.add(ActualizarVendedor);

        JButton SalirAc = new JButton("Salir");
        SalirAc.setBounds(450, 330, 150, 40);
        SalirAc.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
        SalirAc.setBackground(new Color(160, 82, 45));
        SalirAc.setForeground(new Color(61, 43, 31));
        SalirAc.setBorder(BorderFactory.createLineBorder(new Color(92, 51, 23), 2));
        SalirAc.setFocusPainted(false);
        SalirAc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPanel.setVisible(true);
                PanelAgregar.setVisible(false);
                setContentPane(MainPanel);
            }
        });
        PanelActualizar.add(SalirAc);

        // Componentes para eliminar
        JLabel Cedulaa = new JLabel("Cedula del vendedor a eliminar");
        Cedulaa.setBounds(100, 200, 300, 40);
        Cedulaa.setForeground(Color.black);
        Cedulaa.setBorder(null);
        Cedulaa.setFont(fuente);
        PanelEliminar.add(Cedulaa);

        JTextField TextId = new JTextField("");
        TextId.setBounds(300, 200, 150, 40);
        TextId.setBackground(new Color(101, 237, 225));
        TextId.setForeground(new Color(61, 43, 31));
        TextId.setBorder(null);
        PanelEliminar.add(TextId);

        JButton EliminarVendedor = new JButton("Eliminar");
        EliminarVendedor.setBounds(200, 330, 150, 40);
        EliminarVendedor.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
        EliminarVendedor.setBackground(new Color(160, 82, 45));
        EliminarVendedor.setForeground(new Color(61, 43, 31));
        EliminarVendedor.setBorder(BorderFactory.createLineBorder(new Color(92, 51, 23), 2));
        EliminarVendedor.setFocusPainted(false);
        EliminarVendedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conexion = null;
                PreparedStatement preparar = null;

                String Id = TextId.getText();
                try {
                    conexion = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/db_tienda?verifyServerCertificate=false&useSSL=true", "root",
                            "Deiby_R04");
                    conexion.setAutoCommit(true);
                    String Sentencia = "{CALL eliminar_vendedor(?)}";
                    preparar = conexion.prepareStatement(Sentencia);
                    preparar.setString(1, Id);

                    int exito = preparar.executeUpdate();
                    if (exito > 0) {
                        JOptionPane.showMessageDialog(null, "El vendedor se eliminó con éxito");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encuentra vendedor con esa cedula");
                    }

                } catch (SQLException e1) {

                    e1.printStackTrace();
                } finally {
                    {
                        try {
                            if (conexion != null)
                                conexion.close();
                            if (preparar != null)
                                preparar.close();

                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
                TextId.setText("");
            }
        });
        PanelEliminar.add(EliminarVendedor);

        JButton Saliir = new JButton("Salir");
        Saliir.setBounds(450, 330, 150, 40);
        Saliir.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
        Saliir.setBackground(new Color(160, 82, 45));
        Saliir.setForeground(new Color(61, 43, 31));
        Saliir.setBorder(BorderFactory.createLineBorder(new Color(92, 51, 23), 2));
        Saliir.setFocusPainted(false);
        Saliir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPanel.setVisible(true);
                PanelEliminar.setVisible(false);
                setContentPane(MainPanel);
            }
        });
        PanelEliminar.add(Saliir);

        JLabel Titulo = new JLabel("Administración de los vendedores");
        Titulo.setBounds(250, 10, 400, 40);
        Titulo.setForeground(Color.black);
        Font fuentee = new Font("Agency FB", Font.BOLD, 30);
        Titulo.setFont(fuentee);
        Titulo.setBorder(null);
        MainPanel.add(Titulo);

        MainPanel.add(Agregar);
        MainPanel.add(Mostrar);
        MainPanel.add(Regresar);
        MainPanel.add(Eliminar);
        MainPanel.add(Actualizar);
    }

    private void Pintar(JLabel lbl, String ruta) { // Este metodo se utiliza para ponerle imagenes de fondo a los Labels
        this.imagen = new ImageIcon(ruta);
        this.icono = new ImageIcon(
                this.imagen.getImage().getScaledInstance(
                        lbl.getWidth(),
                        lbl.getHeight(),
                        Image.SCALE_DEFAULT));
        lbl.setIcon(this.icono);
        this.repaint();
    }// Fin del metodo Pintar

}// Fin de la clase
