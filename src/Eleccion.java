import java.awt.Color;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public  class Eleccion extends JFrame{

    private JLabel LabelImagen1;
    private Icon icono;
    private ImageIcon imagen;
    
public Eleccion(){
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


        JButton Vendedor = new JButton("Vendedor");
        Vendedor.setBounds(230, 250, 150, 40);
        Vendedor.setBackground(new Color(36, 186, 227));
        Vendedor.setForeground(Color.white);
        Vendedor.setFocusPainted(false);
        Vendedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiVendedor login = new GuiVendedor();
                login.setVisible(true); 
            }
        });

        JButton Producto = new JButton("Producto");
        Producto.setBounds(430, 250, 150, 40);
        Producto.setBackground(new Color(36, 186, 227));
        Producto.setForeground(Color.white);
        Producto.setFocusPainted(false);
        Producto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiProducto login = new GuiProducto();
                login.setVisible(true); 
            }
        });

        
        
        MainPanel.add(Vendedor);
        MainPanel.add(Producto);

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
