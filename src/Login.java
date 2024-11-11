import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public  class Login extends JFrame{

    private JLabel LabelImagen1, LabelImagen2;
    private Icon icono;
    private ImageIcon imagen;
    
public Login(){
    setLayout(null);
        setBounds(0, 0, 800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel MainPanel = new JPanel();
        setContentPane(MainPanel);
        getContentPane();
        MainPanel.setLayout(null);
        MainPanel.setBackground(new Color(220, 199, 165 ));

        LabelImagen1 = new JLabel();// 
        LabelImagen1.setBounds(265, 180, 37, 37);
        this.Pintar(this.LabelImagen1, "Imagenes\\LogoUsuario.png");

        LabelImagen2 = new JLabel();// 
        LabelImagen2.setBounds(265, 243, 37, 37);
        this.Pintar(this.LabelImagen2, "Imagenes\\LogoContra.png");

        JLabel Titulo = new JLabel("Ingresá tus datos");
        Titulo.setBounds(345, 125, 400, 40);
        Titulo.setForeground(Color.black);
        Font fuentee = new Font("Agency FB", Font.BOLD, 22);
        Titulo.setFont(fuentee);
        Titulo.setBorder(null);
        MainPanel.add(Titulo);
 
        JTextField TextUsuario = new JTextField("");
        TextUsuario.setBounds(310, 180, 200, 40);
        TextUsuario.setBackground(new Color(225, 255, 255));
        TextUsuario.setForeground(Color.black);
        MainPanel.add(TextUsuario);

        JTextField TextContra = new JTextField("");
        TextContra.setBounds(310, 245, 200, 40);
        TextContra.setBackground(new Color(255, 255, 255));
        TextContra.setForeground(Color.black);
        MainPanel.add(TextContra);

        JButton IniciarSesion = new JButton("Iniciar sesion");
        IniciarSesion.setBounds(310, 300, 200, 40);
        IniciarSesion.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
        IniciarSesion.setBackground(new Color(160, 82, 45 ));
        IniciarSesion.setForeground(new Color(61, 43, 31 ));
        IniciarSesion.setBorder(BorderFactory.createLineBorder(new Color(92, 51, 23), 2));
        IniciarSesion.setFocusPainted(false);
        IniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {String Usuario = TextUsuario.getText();
                String Contra = TextContra.getText();
                Connection conexion = null;
                PreparedStatement verificarUsuario = null;
                
                String verificarSQL = "SELECT COUNT(*) FROM usuarios WHERE NombreUsuario = ? AND Contraseña = ?";
                
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    conexion = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/db_tienda?verifyServerCertificate=false&useSSL=true", 
                            "root", "Deiby_R04");
                
                    verificarUsuario = conexion.prepareStatement(verificarSQL);
                    verificarUsuario.setString(1, Usuario);
                    verificarUsuario.setString(2, Contra);
                    ResultSet resultado = verificarUsuario.executeQuery();
                
                    if (resultado.next() && resultado.getInt(1) > 0) {
                        JOptionPane.showMessageDialog(null, "Bienvenido usuario" + Usuario);
                        Eleccion login = new Eleccion();
                        login.setVisible(true); 
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario y/o Contraseña incorrecta");
                    }
                
                } catch (ClassNotFoundException ee) {
                    JOptionPane.showMessageDialog(null, "Error al cargar el controlador de MySQL");
                    ee.printStackTrace();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta en la base de datos");
                    ex.printStackTrace();
                } finally {
                    try {
                        if (verificarUsuario != null)
                            verificarUsuario.close();
                        if (conexion != null)
                            conexion.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                
            }
        });

        
        
        MainPanel.add(IniciarSesion);
        MainPanel.add(LabelImagen1);
        MainPanel.add(LabelImagen2);
}//Fin del constructor

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


}//Fin de la clase
