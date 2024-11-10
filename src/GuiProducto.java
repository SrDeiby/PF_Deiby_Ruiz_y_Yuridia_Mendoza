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

public class GuiProducto extends JFrame {

    static int monto;
    private JLabel LabelImagen1;
    static int Tot, Total;
    private Icon icono;
    private ImageIcon imagen;

    public GuiProducto() {
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
        this.Pintar(this.LabelImagen1, "Imagenes\\.Store.jpg");
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

        JPanel PanelEliminar = new JPanel();
        getContentPane();
        PanelEliminar.setLayout(null);
        PanelEliminar.setBackground(new Color(255, 255, 255));

        JPanel PanelActualizar = new JPanel();
        getContentPane();
        PanelActualizar.setLayout(null);
        PanelActualizar.setBackground(new Color(255, 255, 255));

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

        JButton Mostrar = new JButton("Mostrar Productos");
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
                            "jdbc:mysql://localhost:3306/db_tienda?verifyServerCertificate=false&useSSL=true", "root",
                            "Deiby_R04");
                    conexion.setAutoCommit(true);
                    String Mostrar = "{CALL mostrar_productos}";
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

    
        JButton Eliminar = new JButton("Eliminar");
        Eliminar.setBounds(580, 380, 150, 40);
        Eliminar.setBackground(new Color(36, 186, 227));
        Eliminar.setForeground(Color.white);
        Eliminar.setFocusPainted(false);
        Eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                MainPanel.setVisible(false);
                PanelEliminar.setVisible(true);
                setContentPane(PanelEliminar);

            }     
        });

        JButton Actualizar = new JButton("Actualizar");
        Actualizar.setBounds(580, 300, 150, 40);
        Actualizar.setBackground(new Color(36, 186, 227));
        Actualizar.setForeground(Color.white);
        Actualizar.setFocusPainted(false);
        Actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPanel.setVisible(false);
                PanelActualizar.setVisible(true);
                setContentPane(PanelActualizar);    
            }
        });

        // Componentes del PanelAgregar
        JLabel IdP = new JLabel("Id del producto");
        IdP.setBounds(50, 120, 250, 40);
        IdP.setForeground(Color.black);
        IdP.setBorder(null);
        Font fuente = new Font("Agency FB", Font.BOLD, 16);
        IdP.setFont(fuente);
        PanelAgregar.add(IdP);

        JLabel NombreP = new JLabel("Nombre del producto");
        NombreP.setBounds(50, 180, 250, 40);
        NombreP.setForeground(Color.black);
        NombreP.setFont(fuente);
        NombreP.setBorder(null);
        PanelAgregar.add(NombreP);

        JLabel PrecioP = new JLabel("Precio del producto");
        PrecioP.setBounds(50, 240, 250, 40);
        PrecioP.setForeground(Color.black);
        PrecioP.setFont(fuente);
        PrecioP.setBorder(null);
        PanelAgregar.add(PrecioP);

        JLabel Des = new JLabel("Descripcion del producto");
        Des.setBounds(50, 300, 250, 40);
        Des.setForeground(Color.black);
        Des.setFont(fuente);
        Des.setBorder(null);
        PanelAgregar.add(Des);
    
        JTextField TextIdP = new JTextField("");
        TextIdP.setBounds(200, 120, 130, 40);
        TextIdP.setBackground(new Color(101, 237, 225));
        TextIdP.setForeground(Color.black);
        TextIdP.setBorder(null);
        PanelAgregar.add(TextIdP);

        JTextField TextNombreP = new JTextField("");
        TextNombreP.setBounds(200, 180, 130, 40);
        TextNombreP.setBackground(new Color(101, 237, 225));
        TextNombreP.setForeground(Color.black);
        TextNombreP.setBorder(null);
        PanelAgregar.add(TextNombreP);


        JTextField TextPrecio = new JTextField("");
        TextPrecio.setBounds(200, 240, 130, 40);
        TextPrecio.setBackground(new Color(101, 237, 225));
        TextPrecio.setForeground(Color.black);
        TextPrecio.setBorder(null);
        PanelAgregar.add(TextPrecio);

        JTextField TextDes = new JTextField("");
        TextDes.setBounds(200, 300, 130, 40);
        TextDes.setBackground(new Color(101, 237, 225));
        TextDes.setForeground(Color.black);
        TextDes.setBorder(null);
        PanelAgregar.add(TextDes);


        JButton AgregarProducto = new JButton("Agregar");
        AgregarProducto.setBounds(200, 400, 150, 40);
        AgregarProducto.setBackground(new Color(36, 186, 227));
        AgregarProducto.setForeground(Color.white);
        AgregarProducto.setFocusPainted(false);
        AgregarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Id = TextIdP.getText();
                int id = Integer.parseInt(Id);
                String Nombre = TextNombreP.getText();
                String Precio = TextPrecio.getText();
                int precio = Integer.parseInt(Precio);
                String Descripcion = TextDes.getText();
                
                Connection conexion = null;
                PreparedStatement verificarId = null;
                PreparedStatement preparar = null;
                
                String verificarSQL = "SELECT COUNT(*) FROM productos WHERE idProductos = ?";
                String agregarSQL = "{CALL agregar_productos(?,?,?,?)}";
                
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    conexion = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/db_tienda?verifyServerCertificate=false&useSSL=true", "root",
                            "Deiby_R04");
                
                    verificarId = conexion.prepareStatement(verificarSQL);
                    verificarId.setInt(1, id);
                    ResultSet resultado = verificarId.executeQuery();
                
                    if (resultado.next() && resultado.getInt(1) > 0) {
                        JOptionPane.showMessageDialog(null, "El producto con el id '" + id + "'' ya existe en la base de datos.");
                    } else {
                       
                        preparar = conexion.prepareStatement(agregarSQL);
                        preparar.setInt(1, id);
                        preparar.setString(2, Nombre);
                        preparar.setInt(3, precio);
                        preparar.setString(4, Descripcion);
                
                        int exito = preparar.executeUpdate();
                
                        if (exito > 0) {
                            JOptionPane.showMessageDialog(null, "El producto se ha agregado a la base de datos");
                        }
                    }
                
                } catch (ClassNotFoundException ee) {
                    JOptionPane.showMessageDialog(null, "Error al cargar el controlador de MySQL");
                    ee.printStackTrace();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta en la base de datos");
                    ex.printStackTrace();
                } finally {
                    try {
                        if (verificarId != null)
                            verificarId.close();
                        if (preparar != null)
                            preparar.close();
                        if (conexion != null)
                            conexion.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                TextIdP.setText("");
                TextNombreP.setText("");
                TextPrecio.setText("");
                TextDes.setText("");    
            }
        });
        PanelAgregar.add(AgregarProducto);

        JButton Salir = new JButton("Salir");
        Salir.setBounds(450, 400, 150, 40);
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

        // Componentes del Actualizar
        JLabel NombreNuevo = new JLabel("Nuevo Nombre");
        NombreNuevo.setBounds(50, 180, 100, 40);
        NombreNuevo.setForeground(Color.black);
        NombreNuevo.setBorder(null);
        NombreNuevo.setFont(fuente);
        PanelActualizar.add(NombreNuevo);

        JLabel PrecioNuevo = new JLabel("Nuevo Precio");
        PrecioNuevo.setBounds(50, 240, 100, 40);
        PrecioNuevo.setForeground(Color.black);
        PrecioNuevo.setFont(fuente);
        PrecioNuevo.setBorder(null);
        PanelActualizar.add(PrecioNuevo);

        JLabel Ced = new JLabel("Id del producto");
        Ced.setBounds(50, 120, 100, 40);
        Ced.setForeground(Color.black);
        Ced.setFont(fuente);
        Ced.setBorder(null);
        PanelActualizar.add(Ced);

        JLabel Descri = new JLabel("Descripcion nueva");
        Descri.setBounds(50, 300, 250, 40);
        Descri.setForeground(Color.black);
        Descri.setFont(fuente);
        Descri.setBorder(null);
        PanelActualizar.add(Descri);
    
        JTextField TextNombreNuevo = new JTextField("");
        TextNombreNuevo.setBounds(200, 180, 130, 40);
        TextNombreNuevo.setBackground(new Color(101, 237, 225));
        TextNombreNuevo.setForeground(Color.black);
        TextNombreNuevo.setBorder(null);
        PanelActualizar.add(TextNombreNuevo);

        JTextField TextPrecioNuevo = new JTextField("");
        TextPrecioNuevo.setBounds(200, 240, 130, 40);
        TextPrecioNuevo.setBackground(new Color(101, 237, 225));
        TextPrecioNuevo.setForeground(Color.black);
        TextPrecioNuevo.setBorder(null);
        PanelActualizar.add(TextPrecioNuevo);

        JTextField TextCed = new JTextField("");
        TextCed.setBounds(200, 120, 130, 40);
        TextCed.setBackground(new Color(101, 237, 225));
        TextCed.setForeground(Color.black);
        TextCed.setBorder(null);
        PanelActualizar.add(TextCed);

        JTextField TextDescri = new JTextField("");
        TextDescri.setBounds(200, 300, 130, 40);
        TextDescri.setBackground(new Color(101, 237, 225));
        TextDescri.setForeground(Color.black);
        TextDescri.setBorder(null);
        PanelActualizar.add(TextDescri);

        JButton ActualizarProductos = new JButton("Actualizar");
        ActualizarProductos.setBounds(200, 380, 150, 40);
        ActualizarProductos.setBackground(new Color(36, 186, 227));
        ActualizarProductos.setForeground(Color.white);
        ActualizarProductos.setFocusPainted(false);
        ActualizarProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String NombreNuevo = TextNombreNuevo.getText();
                String Id = TextCed.getText();
                String PrecioNu = TextPrecioNuevo.getText();
                int precionuevo = Integer.parseInt(PrecioNu);
                String DesNueva = TextDescri.getText();
                
                Connection conexion = null;
                PreparedStatement preparar = null;
                
                String SQL = "{CALL actualizar_productos(?, ?, ?, ?)}";
                
                try {
                    Class.forName("com.mysql.jdbc.Driver"); 
                    conexion = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/db_tienda?verifyServerCertificate=false&useSSL=true", 
                            "root", "Deiby_R04");
                    conexion.setAutoCommit(true);

                    preparar = conexion.prepareStatement(SQL);
                    preparar.setString(1, NombreNuevo); 
                    preparar.setInt(2, precionuevo);  
                    preparar.setString(3, DesNueva); 
                    preparar.setString(4, Id);    

                    int exito = preparar.executeUpdate();
                
                    if (exito > 0) {
                        JOptionPane.showMessageDialog(null, "Se actualizaron los datos correctamente");
                    }
                
                } catch (Exception ew) {
                    ew.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
                } finally {
                    // Cerrar los recursos
                    try {
                        if (preparar != null) preparar.close();
                        if (conexion != null) conexion.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                TextNombreNuevo.setText("");
                TextDescri.setText("");
                TextPrecioNuevo.setText("");
                TextCed.setText("");                
            }
        });
        PanelActualizar.add(ActualizarProductos);

        JButton SalirAc = new JButton("Salir");
        SalirAc.setBounds(450, 380, 150, 40);
        SalirAc.setBackground(new Color(36, 186, 227));
        SalirAc.setForeground(Color.white);
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


        //Componentes para eliminar
        JLabel ID = new JLabel("Id del producto a eliminar");
        ID.setBounds(200, 200, 300, 40);
        ID.setForeground(Color.black);
        ID.setBorder(null);
        Font fuene = new Font("Agency FB", Font.BOLD, 16);
        ID.setFont(fuene);
        PanelEliminar.add(ID);
        
        JTextField TextId = new JTextField("");
        TextId.setBounds(400, 200, 130, 40);
        TextId.setBackground(new Color(101, 237, 225));
        TextId.setForeground(Color.black);
        TextId.setBorder(null);
        PanelEliminar.add(TextId);

        JButton EliminarProducto = new JButton("Eliminar");
        EliminarProducto.setBounds(200, 330, 150, 40);
        EliminarProducto.setBackground(new Color(36, 186, 227));
        EliminarProducto.setForeground(Color.white);
        EliminarProducto.setFocusPainted(false);
        EliminarProducto.addActionListener(new ActionListener() {
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
                    String Sentencia = "{CALL eliminar_productos(?)}";
                    preparar = conexion.prepareStatement(Sentencia);
                    preparar.setString(1, Id);

                    int exito = preparar.executeUpdate();
                    if (exito > 0) {
                        JOptionPane.showMessageDialog(null, "El producto se eliminó con éxito");
                    } else {
                        JOptionPane.showMessageDialog(null, "No existe Productos con el id ingresado");
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
        PanelEliminar.add(EliminarProducto);


        JButton Saliir = new JButton("Salir");
        Saliir.setBounds(450, 330, 150, 40);
        Saliir.setBackground(new Color(36, 186, 227));
        Saliir.setForeground(Color.white);
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



        MainPanel.add(PanelArriba);
        MainPanel.add(PanelAbajo);
        MainPanel.add(Agregar);
        MainPanel.add(Mostrar);

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
