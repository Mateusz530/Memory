package memory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Wynik extends JFrame implements ActionListener {

private JButton b1;
private JButton b2;
private int wyb;
WyborRozmiaru wyb1;
JTextArea nick;
String score;
Gracz gracz;



    public Wynik(WyborRozmiaru wyb1, int wyb, String czas){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,200);
        setLocation(820, 400);
        setTitle("memory.Wynik");


        ImageIcon tlo = new ImageIcon("resources\\tlo2.jpg");
        setContentPane(new JLabel(tlo));

        this.wyb = wyb;
        this.wyb1 = wyb1;

        JPanel gora = new JPanel();
        gora.setPreferredSize(new Dimension(2000,40));
        gora.setBackground(new Color(0,0,0,0));

        Pattern p1 = Pattern.compile("(^\\w{2}):(\\w{2}):(\\w{2})");
        Matcher matcher = p1.matcher(czas);
        matcher.find();

        Integer punkty = (1000*wyb*wyb*wyb*wyb*wyb)/(Integer.valueOf(matcher.group(1))*3600 + Integer.valueOf(matcher.group(2))*60 + Integer.valueOf(matcher.group(3)));

        JPanel gora2 = new JPanel();
        gora2.setPreferredSize(new Dimension(2000,40));
        gora2.setBackground(new Color(0,0,0,0));

        JLabel nick1 = new JLabel("Wpisz nick: ");
        nick1.setFont(new Font("f",Font.PLAIN,20));
        JTextArea nick = new JTextArea(1,10);
        nick.setFont(new Font("f",Font.PLAIN,20));


        Gracz gracz = new Gracz(nick.getText(), czas, wyb, punkty);
        this.gracz = gracz;
        this.nick = nick;
        String score = " (Czas: " + czas + ", rozmiar: " + wyb + "x" + wyb + ", memory.Wynik: " + punkty + "pkt)";
        this.score = score;

        gora2.add(nick1);
        gora2.add(nick);

        JLabel napis = new JLabel("Wynik: " + czas + " , " + punkty + " pkt");
        napis.setFont(new Font("f",Font.PLAIN,25));
        gora.add(napis);
        add(gora);
        add(gora2);
        JButton tryAgain = new JButton("Zagraj ponownie");
        tryAgain.setPreferredSize(new Dimension(190,50));
        tryAgain.setFont(new Font("f",Font.PLAIN,20));;
        tryAgain.addActionListener(this);
        b1 = tryAgain;

        JButton wyjscie = new JButton("Wyjscie");
        wyjscie.setFont(new Font("f",Font.PLAIN,20));;
        wyjscie.setPreferredSize(new Dimension(130,50));
        wyjscie.addActionListener(this);
        b2 = wyjscie;

        add(tryAgain);
        add(wyjscie);


        setLayout(new FlowLayout());

        setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        Object o = e.getSource();
        ArrayList<Gracz> przechowalnia = new ArrayList<>();


        String s = nick.getText();
        String zapis = s + score;
        gracz.nick = nick.getText();
        przechowalnia.add(gracz);

        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("wyniki.bin"))) {
            Gracz g = new Gracz();
            while (g != null) {
                g = (Gracz)is.readObject();
                przechowalnia.add(g);

            }
        }catch (Exception ex) {
            System.out.print("");
        }

        Collections.sort(przechowalnia);

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("wyniki.bin"))) {
            for(Gracz x: przechowalnia) {
                outputStream.writeObject(x);
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        przechowalnia.clear();




        if(o == b1){

            Memory m = new Memory(wyb1);
            this.dispose();
            processWindowEvent(new WindowEvent(m, WindowEvent.WINDOW_OPENED));

        }
        else
            if(o == b2){

                MyFrame m = new MyFrame();
                this.dispose();
                processWindowEvent(new WindowEvent(m, WindowEvent.WINDOW_OPENED));

            }



    }
}
