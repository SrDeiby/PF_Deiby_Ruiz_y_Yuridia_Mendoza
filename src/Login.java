import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public  class Login extends JFrame{
    
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
               
            }
        });

        MainPanel.add(IniciarSesion);


}//Fin del constructor

}//Fin de la clase
