import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public  class Login extends JFrame{

    private JLabel LabelImagen1;
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
        MainPanel.setBackground(new Color(255, 255, 255));

        LabelImagen1 = new JLabel();// 
        LabelImagen1.setBounds(0, 0, 800, 550);
        this.Pintar(this.LabelImagen1, "Imagenes\\Store.jpg");

        JLabel Usuario = new JLabel("Usuario");
        Usuario.setBounds(330, 160, 100, 40);
        Usuario.setForeground(Color.black);
        Usuario.setBorder(null);
        Font fuente = new Font("Agency FB", Font.BOLD, 12);
        Usuario.setFont(fuente);
        MainPanel.add(Usuario);

        JLabel Contra = new JLabel("Contraseña");
        Contra.setBounds(330, 215, 100, 40);
        Contra.setForeground(Color.black);
        Contra.setFont(fuente);
        Contra.setBorder(null);
        MainPanel.add(Contra);

        JTextField TextUsuario = new JTextField("");
        TextUsuario.setBounds(330, 190, 150, 35);
        TextUsuario.setBackground(new Color(101, 237, 225));
        TextUsuario.setForeground(Color.black);
        TextUsuario.setBorder(null);
        MainPanel.add(TextUsuario);

        JTextField TextContra = new JTextField("");
        TextContra.setBounds(330, 245, 150, 35);
        TextContra.setBackground(new Color(101, 237, 225));
        TextContra.setForeground(Color.black);
        TextContra.setBorder(null);
        MainPanel.add(TextContra);

        JButton IniciarSesion = new JButton("Iniciar sesion");
        IniciarSesion.setBounds(330, 290, 150, 40);
        IniciarSesion.setBackground(new Color(36, 186, 227));
        IniciarSesion.setForeground(Color.white);
        IniciarSesion.setFocusPainted(false);
        IniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Eleccion login = new Eleccion();
                login.setVisible(true); 
            }
        });

        
        
        MainPanel.add(IniciarSesion);

        MainPanel.add(LabelImagen1);
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
