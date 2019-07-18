package memory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Wyniki extends JFrame implements ActionListener {

    JList wyniki;
    JButton b1;
    JButton b2;


    public Wyniki() {

        super("memory.Wyniki");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1080, 640);
        setLocation(400, 150);


        ImageIcon tlo = new ImageIcon("resources\\dubai.jpg");
        setContentPane(new JLabel(tlo));

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(2000, 60));
        JLabel jl = new JLabel("Wyniki");
        jl.setFont(new Font("font", Font.PLAIN, 30));
        panel.add(jl);
        add(panel);

        JList wyniki = new JList();
        this.wyniki = wyniki;
        DefaultListModel lm = new DefaultListModel();


        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("wyniki.bin"))) {
            Gracz g = new Gracz();
            while (g != null) {
                g = (Gracz) is.readObject();
                lm.addElement(g);
            }
        } catch (Exception ex) {
            System.out.print("");
        }

        wyniki.setModel(lm);
        wyniki.setFont(new Font("f", Font.PLAIN, 20));
        panel.setBackground(new Color(0, 0, 0, 0));
        wyniki.setBackground(Color.LIGHT_GRAY);


        JScrollPane scroll = new JScrollPane(wyniki, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBackground(Color.LIGHT_GRAY);
        scroll.setPreferredSize(new Dimension(550, 400));
        add(scroll);


        JButton wyjscie = new JButton("Wyjscie");
        wyjscie.setFont(new Font("f", Font.PLAIN, 20));
        JButton reset = new JButton("Reset");
        reset.setFont(new Font("f", Font.PLAIN, 20));
        JPanel panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(2000, 80));
        panel2.setBackground(new Color(0, 0, 0, 0));

        reset.addActionListener(this);
        wyjscie.addActionListener(this);

        b1 = reset;
        b2 = wyjscie;

        panel2.add(reset);
        panel2.add(wyjscie);

        add(panel2);


        setLayout(new FlowLayout());
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object o = e.getSource();


        if (o == b1) {

            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("wyniki.bin"))) {

            } catch (Exception ex) {

            }
            DefaultListModel lm = new DefaultListModel();
            wyniki.setModel(lm);

        } else if (o == b2) {
            MyFrame m = new MyFrame();
            this.dispose();
            processWindowEvent(new WindowEvent(m, WindowEvent.WINDOW_OPENED));
        }


    }
}
