package memory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class WyborRozmiaru extends JFrame implements ActionListener {

public static int rozmiar;
private JComboBox cbox;





    public WyborRozmiaru(){
        super("Nowa gra");

        ImageIcon tlo = new ImageIcon(Main.ROOT_DIR+"tlo2.jpg");
        setContentPane(new JLabel(tlo));


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(480, 270);
        setLocation(720, 300);
        setVisible(true);

        JLabel jl = new JLabel("Wybierz rozmiar    ");
        jl.setFont(new Font("font",Font.PLAIN,25));
        setLayout(new FlowLayout(20,130,40));

        add(jl);

        JComboBox<String> box = new JComboBox();
        box.addItem("2x2");
        box.addItem("4x4");
        box.addItem("6x6");
        box.setPreferredSize(new Dimension(60,30));
        add(box);
        box.setFont(new Font("font", Font.PLAIN,20));
        JButton b = new JButton("Start");
        b.setFont(new Font("font", Font.PLAIN,20));
        add(b);

        b.addActionListener(this);

        cbox = box;



    }

    public void actionPerformed(ActionEvent e) {

        Object o = e.getSource();

        String s = (String)cbox.getSelectedItem();
        if(s.equals("2x2")){
            rozmiar = 2;
        }
        else if(s.equals("4x4")){
            rozmiar = 4;
        }
        else if(s.equals("6x6")){
            rozmiar = 6;
        }
            Memory m = new Memory(this);
            this.dispose();
            processWindowEvent(new WindowEvent(m, WindowEvent.WINDOW_OPENED));


    }

}
