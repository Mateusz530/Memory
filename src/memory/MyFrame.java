package memory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.net.URL;


public class MyFrame extends JFrame implements ActionListener {

    private JButton b1;
    private JButton b2;
    private JButton b3;


    public MyFrame() {
        super("Gra memory");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1080, 640);
        setLocation(400, 150);


        URL urlBack = this.getClass().getClassLoader().getResource("dubai.jpg");
        ImageIcon tlo = new ImageIcon(urlBack);
        setContentPane(new JLabel(tlo));



        JLabel jl = new JLabel("Gra memory");
        jl.setFont(new Font("font", Font.PLAIN, 30));
        setLayout(new FlowLayout());

        JPanel jpanel = new JPanel();
        jpanel.setPreferredSize(new Dimension(2000, 45));

        jpanel.setBackground(new Color(0, 0, 0, 0));
        jpanel.add(jl);

        add(jpanel);

        JLabel jl2 = new JLabel("");
        jl2.setFont(new Font("font", Font.PLAIN, 22));

        JPanel jpanel2 = new JPanel();
        jpanel2.setPreferredSize(new Dimension(2000, 45));

        jpanel2.setBackground(new Color(0, 0, 0, 0));
        jpanel2.add(jl2);
        add(jpanel2);

        b1 = new JButton("Nowa Gra");
        b1.setFont(new Font("font", Font.PLAIN, 25));
        add(b1);

        b1.setLocation(300, 300);
        b1.setPreferredSize(new Dimension(500, 50));
        b1.addActionListener(this);


        b2 = new JButton("Wyniki");
        b2.setFont(new Font("font", Font.PLAIN, 25));
        add(b2);

        b2.setPreferredSize(new Dimension(500, 50));
        b2.addActionListener(this);

        b3 = new JButton("Wyjscie");
        b3.setFont(new Font("font", Font.PLAIN, 25));
        add(b3);

        b3.setPreferredSize(new Dimension(500, 50));
        b3.addActionListener(this);


        setVisible(true);


    }

    public void actionPerformed(ActionEvent ae) {
        Object o = ae.getSource();


        if (o == b1) {
            WyborRozmiaru w;
            w = new WyborRozmiaru();
            this.dispose();
            processWindowEvent(new WindowEvent(w, WindowEvent.WINDOW_OPENED));
        } else if (o == b2) {
            Wyniki w = new Wyniki();
            this.dispose();
            processWindowEvent(new WindowEvent(w, WindowEvent.WINDOW_OPENED));


        } else if (o == b3) {
            System.exit(0);
        }
    }


}


