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
        MainPanel.setBackground(new Color(255, 255, 255));

        LabelImagen1 = new JLabel();// 
        LabelImagen1.setBounds(0, 70, 400, 380);
        this.Pintar(this.LabelImagen1, "C:\\Users\\deiby\\OneDrive\\Practicas vacaciones\\Banco\\Fondo.png");
        MainPanel.add(LabelImagen1);

        JPanel PanelArriba = new JPanel();
        PanelArriba.setBounds(0, 0, 800, 60);
        PanelArriba.setBackground(new Color(0, 50, 131));

        JPanel PanelAbajo = new JPanel();
        PanelAbajo.setBounds(0, 451, 800, 60);
        PanelAbajo.setBackground(new Color(0, 50, 131));

        JPanel PanelAgregar = new JPanel();
        getContentPane();
        PanelAgregar.setLayout(null);
        PanelAgregar.setBackground(new Color(255, 255, 255));

        JPanel PanelArriba2 = new JPanel();
        PanelArriba2.setBounds(0, 0, 800, 60);
        PanelArriba2.setBackground(new Color(0, 50, 131));
        PanelAgregar.add(PanelArriba2);

        JPanel PanelAbajo2 = new JPanel();
        PanelAbajo2.setBounds(0, 451, 800, 60);
        PanelAbajo2.setBackground(new Color(0, 50, 131));
        PanelAgregar.add(PanelAbajo2);

        // Boton para hacer visible el panel de Agregar
        JButton Agregar = new JButton("Agregar");
        Agregar.setBounds(580, 140, 150, 40);
        Agregar.setBackground(new Color(36, 186, 227));
        Agregar.setForeground(Color.white);
        Agregar.setFocusPainted(false);
        Agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPanel.setVisible(false);
                PanelAgregar.setVisible(true);
                setContentPane(PanelAgregar);
            }
        });

        JButton Mostrar = new JButton("Mostrar");
        Mostrar.setBounds(580, 200, 150, 40);
        Mostrar.setBackground(new Color(36, 186, 227));
        Mostrar.setForeground(Color.white);
        Mostrar.setFocusPainted(false);
        Mostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conexion = null;
                PreparedStatement ptm = null;
                ResultSet result = null;

                try {

                    conexion = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/banco?verifyServerCertificate=false&useSSL=true", "root",
                            "Deiby_R04");
                    conexion.setAutoCommit(true);
                    String Mostrar = "{CALL lista_personas}";
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
                    JOptionPane.showMessageDialog(null, scrollPane, "Base de datos Banco",
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

        JButton Retiro = new JButton("Retiro");
        Retiro.setBounds(580, 260, 150, 40);
        Retiro.setBackground(new Color(36, 186, 227));
        Retiro.setForeground(Color.white);
        Retiro.setFocusPainted(false);
        Retiro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog("Digite el id de la persona");

                Connection conectar = null;
                PreparedStatement prepararprimera = null;
                PreparedStatement prepararsegunda = null;
                ResultSet Obtener = null;

                try {
                    conectar = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/banco?verifyServerCertificate=false&useSSL=true", "root","Deiby_R04");
                    conectar.setAutoCommit(true);
                    String SQL = "SELECT Monto from personas WHERE Id = ?";
                    prepararprimera = conectar.prepareStatement(SQL);
                    prepararprimera.setString(1, id);

                    Obtener = prepararprimera.executeQuery();

                    int monto = 0;
                    if (Obtener.next()) {
                        monto = Obtener.getInt("Monto");
                    }

                    String montore = JOptionPane.showInputDialog("Digite la cantidad de monto a retirar");
                    int MontoRetiro = Integer.parseInt(montore);

                    if (MontoRetiro <= monto) {
                        Total = 0;
                        Total = monto - MontoRetiro;
                        String indicacion = "UPDATE personas SET Monto = ? WHERE Id = ?";

                        prepararsegunda = conectar.prepareStatement(indicacion);
                        prepararsegunda.setInt(1, Total);
                        prepararsegunda.setString(2, id);
                    } else {
                        JOptionPane.showMessageDialog(null, "El monto a retirar excede el monto que tiene la persona");
                    }

                    int exito = prepararsegunda.executeUpdate();
                    if (exito > 0) {
                        JOptionPane.showMessageDialog(null, "Retiro realizado con exito \nNuevo saldo: " + Total);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "No se encontró ninguna persona con el número de Id especificado.");
                    }
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos: " + sqle.getMessage());
                } finally {
                    try {
                        if (Obtener != null)
                            Obtener.close();
                        if (prepararsegunda != null)
                            prepararsegunda.close();
                        if (prepararprimera != null)
                            prepararprimera.close();
                        if (conectar != null)
                            conectar.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });

        JButton Deposito = new JButton("Deposito");
        Deposito.setBounds(580, 320, 150, 40);
        Deposito.setBackground(new Color(36, 186, 227));
        Deposito.setForeground(Color.white);
        Deposito.setFocusPainted(false);
        Deposito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog("Id de la persona");

                Connection conexion = null;
                PreparedStatement preparar = null;
                PreparedStatement ptm = null;
                ResultSet rs = null;

                try {
                    conexion = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/banco?verifyServerCertificate=false&useSSL=true", "root",
                            "Deiby_R04");
                    conexion.setAutoCommit(true);
                    String SQL = "SELECT Monto FROM personas WHERE Id = ?";
                    ptm = conexion.prepareStatement(SQL);
                    ptm.setString(1, id);

                    rs = ptm.executeQuery();

                    int monto = 0;
                    if (rs.next()) {
                        monto = rs.getInt("Monto");
                    }

                    String mo = JOptionPane.showInputDialog("Monto a depositar");
                    int MontoNuevo = Integer.parseInt(mo);
                    Tot = 0;
                    Tot = monto + MontoNuevo;

                    String Indicacion = "UPDATE personas SET Monto = ? WHERE Id = ?";
                    preparar = conexion.prepareStatement(Indicacion);
                    preparar.setInt(1, Tot);
                    preparar.setString(2, id);

                    int exito = preparar.executeUpdate();

                    if (exito > 0) {
                        JOptionPane.showMessageDialog(null, "Deposito realizado con exito \nNuevo Saldo: " + Tot);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "No se encontró ninguna persona con el número de Id especificado.");
                    }
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos: " + sqle.getMessage());
                } finally {
                    try {
                        if (rs != null)
                            rs.close();
                        if (ptm != null)
                            ptm.close();
                        if (preparar != null)
                            preparar.close();
                        if (conexion != null)
                            conexion.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });

        JButton Eliminar = new JButton("Eliminar");
        Eliminar.setBounds(580, 380, 150, 40);
        Eliminar.setBackground(new Color(36, 186, 227));
        Eliminar.setForeground(Color.white);
        Eliminar.setFocusPainted(false);
        Eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conexion = null;
                PreparedStatement preparar = null;

                String Id = JOptionPane.showInputDialog("Id de la persona a eliminar");
                try {
                    conexion = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/banco?verifyServerCertificate=false&useSSL=true", "root",
                            "Deiby_R04");
                    conexion.setAutoCommit(true);
                    String Sentencia = "{CALL eliminar_persona(?)}";
                    preparar = conexion.prepareStatement(Sentencia);
                    preparar.setString(1, Id);

                    int exito = preparar.executeUpdate();
                    if (exito > 0) {
                        JOptionPane.showMessageDialog(null, "La persona se elimino con exito del banco");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encuentra persona con ese Id");
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

            }
        });

        JButton Actualizar = new JButton("Actualizar");
        Actualizar.setBounds(400, 380, 150, 40);
        Actualizar.setBackground(new Color(36, 186, 227));
        Actualizar.setForeground(Color.white);
        Actualizar.setFocusPainted(false);
        Actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conexion = null;
                PreparedStatement preparar = null;

                String Id = JOptionPane.showInputDialog("Id de la persona a actualizar");
                String NuevoNombre = JOptionPane.showInputDialog("Nuevo nombre de la persona");
                try {
                    conexion = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/banco?verifyServerCertificate=false&useSSL=true", "root",
                            "Deiby_R04");
                    conexion.setAutoCommit(true);
                    String Sentencia = "{CALL actualizar_nombre(?,?)}";
                    preparar = conexion.prepareStatement(Sentencia);
                    preparar.setString(1, Id);
                    preparar.setString(2, NuevoNombre);

                    int exito = preparar.executeUpdate();
                    if (exito > 0) {
                        JOptionPane.showMessageDialog(null, "La persona se ha actualizado con exito del banco");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encuentra persona con ese Id");
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

            }
        });

        // Componentes del PanelAgregar
        JLabel Nombre = new JLabel("Nombre");
        Nombre.setBounds(50, 100, 100, 40);
        Nombre.setForeground(Color.black);
        Nombre.setBorder(null);
        Font fuente = new Font("Agency FB", Font.BOLD, 15);
        Nombre.setFont(fuente);
        PanelAgregar.add(Nombre);

        JLabel Apellido1 = new JLabel("Primer apellido");
        Apellido1.setBounds(50, 140, 100, 40);
        Apellido1.setForeground(Color.black);
        Apellido1.setFont(fuente);
        Apellido1.setBorder(null);
        PanelAgregar.add(Apellido1);

        JLabel Apellido2 = new JLabel("Segundo apellido");
        Apellido2.setBounds(50, 180, 100, 40);
        Apellido2.setForeground(Color.black);
        Apellido2.setFont(fuente);
        Apellido2.setBorder(null);
        PanelAgregar.add(Apellido2);

        JLabel ID = new JLabel("ID");
        ID.setBounds(50, 220, 100, 40);
        ID.setForeground(Color.black);
        ID.setFont(fuente);
        ID.setBorder(null);
        PanelAgregar.add(ID);

        JLabel Monto = new JLabel("Monto");
        Monto.setBounds(50, 260, 100, 40);
        Monto.setForeground(Color.black);
        Monto.setFont(fuente);
        Monto.setBorder(null);
        PanelAgregar.add(Monto);

        JTextField TextNombre = new JTextField("");
        TextNombre.setBounds(200, 100, 130, 30);
        TextNombre.setBackground(new Color(101, 237, 225));
        TextNombre.setForeground(Color.black);
        TextNombre.setBorder(null);
        PanelAgregar.add(TextNombre);

        JTextField TextApellido = new JTextField("");
        TextApellido.setBounds(200, 140, 130, 30);
        TextApellido.setBackground(new Color(101, 237, 225));
        TextApellido.setForeground(Color.black);
        TextApellido.setBorder(null);
        PanelAgregar.add(TextApellido);

        JTextField TextApellido2 = new JTextField("");
        TextApellido2.setBounds(200, 180, 130, 30);
        TextApellido2.setBackground(new Color(101, 237, 225));
        TextApellido2.setForeground(Color.black);
        TextApellido2.setBorder(null);
        PanelAgregar.add(TextApellido2);

        JTextField TextID = new JTextField("");
        TextID.setBounds(200, 220, 130, 30);
        TextID.setBackground(new Color(101, 237, 225));
        TextID.setForeground(Color.black);
        TextID.setBorder(null);
        PanelAgregar.add(TextID);

        JTextField TextMonto = new JTextField("");
        TextMonto.setBounds(200, 260, 130, 30);
        TextMonto.setBackground(new Color(101, 237, 225));
        TextMonto.setForeground(Color.red);
        TextMonto.setBorder(null);
        PanelAgregar.add(TextMonto);

        JButton AgregarPersona = new JButton("Agregar");
        AgregarPersona.setBounds(150, 330, 150, 40);
        AgregarPersona.setBackground(new Color(36, 186, 227));
        AgregarPersona.setForeground(Color.white);
        AgregarPersona.setFocusPainted(false);
        AgregarPersona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Nombre = TextNombre.getText();
                String Id = TextID.getText();
                String Ap1 = TextApellido.getText();
                String Ap2 = TextApellido2.getText();
                String monto = TextMonto.getText();
                int Monto = Integer.parseInt(monto);

                Connection conexion = null;
                PreparedStatement preparar = null;

                String SQL = "{CALL insertar_persona(?,?,?,?,?)}";

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    conexion = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/banco?verifyServerCertificate=false&useSSL=true", "root",
                            "Deiby_R04");
                    conexion.setAutoCommit(true);

                    // Preparar la consulta
                    preparar = conexion.prepareStatement(SQL);
                    preparar.setString(1, Nombre);
                    preparar.setString(2, Ap1);
                    preparar.setString(3, Ap2);
                    preparar.setString(4, Id);
                    preparar.setInt(5, Monto);

                    // Ejecutar la consulta
                    int exito = preparar.executeUpdate();

                    if (exito > 0) {
                        JOptionPane.showMessageDialog(null, "La persona se ha agregado a la base de datos");
                    }

                } catch (Exception ew) {
                    ew.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
                } finally {
                    // Cerrar los recursos
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
                TextApellido2.setText("");
                TextMonto.setText("");
                TextID.setText("");
            }
        });
        PanelAgregar.add(AgregarPersona);

        JButton Salir = new JButton("Salir");
        Salir.setBounds(400, 330, 150, 40);
        Salir.setBackground(new Color(36, 186, 227));
        Salir.setForeground(Color.white);
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

        MainPanel.add(PanelArriba);
        MainPanel.add(PanelAbajo);
        MainPanel.add(Agregar);
        MainPanel.add(Mostrar);
        MainPanel.add(Retiro);
        MainPanel.add(Deposito);
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
