package memory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;


public class Memory extends JFrame implements ActionListener {

    ImageIcon tabi[] = new ImageIcon[18];
    Button tab[];

    boolean koniecGry = false;

    WyborRozmiaru wyb;
    Button pierwsza = null;
    Button druga = null;
    Button pierwsza2 = null;
    Button druga2 = null;
    int licznik = 0;
    int licznik2 = 0;
    String czas;
    int Klik;

    public Memory(WyborRozmiaru wyb) {


        ImageIcon tlo = new ImageIcon(Main.ROOT_DIR + "tlo.jpg");
        setContentPane(new JLabel(tlo));

        this.wyb = wyb;
        Klik = 0;

        tabi[0] = new ImageIcon(Main.ROOT_DIR + "karta.jpg");
        tabi[1] = new ImageIcon(Main.ROOT_DIR + "karta2.jpg");
        tabi[2] = new ImageIcon(Main.ROOT_DIR + "karta3.jpg");
        tabi[3] = new ImageIcon(Main.ROOT_DIR + "karta4.jpg");
        tabi[4] = new ImageIcon(Main.ROOT_DIR + "karta5.jpg");
        tabi[5] = new ImageIcon(Main.ROOT_DIR + "karta6.jpg");
        tabi[6] = new ImageIcon(Main.ROOT_DIR + "karta7.jpg");
        tabi[7] = new ImageIcon(Main.ROOT_DIR + "karta8.jpg");
        tabi[8] = new ImageIcon(Main.ROOT_DIR + "karta9.jpg");
        tabi[9] = new ImageIcon(Main.ROOT_DIR + "karta10.jpg");
        tabi[10] = new ImageIcon(Main.ROOT_DIR + "karta11.jpg");
        tabi[11] = new ImageIcon(Main.ROOT_DIR + "karta12.jpg");
        tabi[12] = new ImageIcon(Main.ROOT_DIR + "karta13.jpg");
        tabi[13] = new ImageIcon(Main.ROOT_DIR + "karta14.jpg");
        tabi[14] = new ImageIcon(Main.ROOT_DIR + "karta15.jpg");
        tabi[15] = new ImageIcon(Main.ROOT_DIR + "karta16.jpg");
        tabi[16] = new ImageIcon(Main.ROOT_DIR + "karta17.jpg");
        tabi[17] = new ImageIcon(Main.ROOT_DIR + "karta18.jpg");


        setTitle("Gra memory");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if (wyb.rozmiar == 2) {
            setSize(480, 500);
            setLocation(750, 200);
        } else if (wyb.rozmiar == 4) {
            setSize(800, 800);
            setLocation(550, 100);
        } else if (wyb.rozmiar == 6) {
            setSize(1040, 1080);
            setLocation(450, 0);
        }


        setVisible(true);
//        setResizable(false);

        JPanel jp = new JPanel();

        jp.setBackground(Color.LIGHT_GRAY);
        jp.setPreferredSize(new Dimension(2000, 35));


        JLabel jl = new JLabel();
        jl.setPreferredSize(new Dimension(120, 25));
        jl.setFont(new Font("font", Font.PLAIN, 25));
        jp.add(jl);
        add(jp);
//===========================================================
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int i = 0;
                    while (koniecGry == false) {
                        jl.setText(getTime(i));
                        Thread.sleep(1000);
                        i++;

                    }
                    czas = jl.getText();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });




        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (koniecGry == false) {

                        boolean czyKoniec = true;
                        boolean czyKliknieto = false;

                        Thread.sleep(300);
                        Klik = 0;
                        ImageIcon ikona = new ImageIcon(Main.ROOT_DIR + "kartatyl.jpg");


                        for (int i = 0; i < tab.length; i++) {
                            if (tab[i].stan == false && tab[i].znaleziona == false) {
                                if (pierwsza == null) {
                                    pierwsza = tab[i];
                                } else if (tab[i] != pierwsza) {
                                    druga = tab[i];
                                }

                            }

                        }

                        if (pierwsza != null && druga != null) {
                            licznik++;
                        }

                        if (licznik == 1) {


                            Nasluchiwanie:
                            for (int i = 0; i < 500; i++) {
                                if (Klik == 1) {
                                    czyKliknieto = true;
                                    break Nasluchiwanie;
                                }
                                Thread.sleep(1);
                            }


                            if (czyKliknieto == false) {

                                if (pierwsza.obraz != druga.obraz) {


                                    Thread.sleep(400);

                                    for (int i = 0; i < tab.length; i++) {
                                        if (tab[i] == pierwsza) {
                                            tab[i].stan = true;
                                            tab[i].setIcon(ikona);
                                        }
                                        if (tab[i] == druga) {
                                            tab[i].stan = true;
                                            tab[i].setIcon(ikona);

                                        }
                                    }

                                    licznik = 0;
                                } else {


                                    Thread.sleep(400);
                                    licznik = 0;
                                    pierwsza.znaleziona = true;
                                    druga.znaleziona = true;
                                    pierwsza.stan = true;
                                    druga.stan = true;
                                    setLayout(null);
                                    pierwsza.setVisible(false);
                                    druga.setVisible(false);

                                }

                            }
                            pierwsza = null;
                            druga = null;
                            licznik = 0;
                        }

                        for (int i = 0; i < tab.length; i++) {
                            if (tab[i].znaleziona == false) {
                                czyKoniec = false;
                            }
                        }

                        if (czyKoniec == true) {
                            koniecGry = true;
                            Thread.sleep(1100);
                            Koniec();
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
//==================================================================


        for (int i = 0; i < wyb.rozmiar * wyb.rozmiar; i++) {
            if (i == 0) {
                tab = new Button[wyb.rozmiar * wyb.rozmiar];
            }
            ImageIcon ikona = new ImageIcon(Main.ROOT_DIR + "kartatyl.jpg");
            Button jb = new Button();

            jb.setPreferredSize(new Dimension(155, 155));
            jb.setIcon(ikona);
            tab[i] = jb;
            jb.addActionListener(this);
            add(jb);
        }

        for (int i = 0; i < tab.length / 2; i++) {

            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    int x;
                    do {
                        x = (int) (Math.random() * tab.length / 2);
                    }
                    while (tab[x].obraz != null);

                    tab[x].obraz = tabi[i];
                } else {
                    int y;
                    do {
                        y = (int) (Math.random() * tab.length / 2);
                    }
                    while (tab[y + (tab.length / 2)].obraz != null);

                    tab[y + (tab.length / 2)].obraz = tabi[i];
                }
            }

        }


        setLayout(new FlowLayout());


    }


    public String getTime(int sec) {

        int hours = 0;
        int remainderOfHours = 0;
        int minutes = 0;
        int seconds = 0;

        if (sec >= 3600) {
            hours = sec / 3600;
            remainderOfHours = sec % 3600;

            if (remainderOfHours >= 60) {
                minutes = remainderOfHours / 60;
                seconds = remainderOfHours % 60;
            } else {
                seconds = remainderOfHours;
            }
        } else if (sec >= 60) {
            hours = 0;
            minutes = sec / 60;
            seconds = sec % 60;
        } else if (sec < 60) {
            hours = 0;
            minutes = 0;
            seconds = sec;
        }

        String strHours;
        String strMins;
        String strSecs;

        if (seconds < 10)
            strSecs = "0" + Integer.toString(seconds);
        else
            strSecs = Integer.toString(seconds);

        if (minutes < 10)
            strMins = "0" + Integer.toString(minutes);
        else
            strMins = Integer.toString(minutes);

        if (hours < 10)
            strHours = "0" + Integer.toString(hours);
        else
            strHours = Integer.toString(hours);

        String time = strHours + ":" + strMins + ":" + strSecs;
        return time;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        ImageIcon ikona = new ImageIcon(Main.ROOT_DIR + "kartatyl.jpg");

        boolean test = true;
        for (int t = 0; t < tab.length; t++) {
            if (source == tab[t]) {
                if (tab[t].stan == false) {
                    test = false;
                }
            }
        }

        if (test == true) {
            for (int yy = 0; yy < tab.length; yy++) {
                if (source == tab[yy]) {
                    if (tab[yy].stan == true) {

                        Klik = 1;


                        pierwsza2 = null;
                        druga2 = null;
                        for (int i = 0; i < tab.length; i++) {
                            if (tab[i].stan == false && tab[i].znaleziona == false) {
                                if (pierwsza2 == null) {
                                    pierwsza2 = tab[i];
                                } else if (tab[i] != pierwsza2) {
                                    druga2 = tab[i];
                                }

                            }

                        }

                        if (pierwsza2 != null && druga2 != null) {
                            licznik2++;
                        }

                        if (licznik2 == 1) {


                            if (pierwsza2.obraz != druga2.obraz) {


                                for (int i = 0; i < tab.length; i++) {
                                    if (tab[i] == pierwsza2) {
                                        tab[i].stan = true;
                                        tab[i].setIcon(ikona);
                                    }
                                    if (tab[i] == druga2) {
                                        tab[i].stan = true;
                                        tab[i].setIcon(ikona);

                                    }
                                }

                                licznik2 = 0;

                            } else {

                                licznik2 = 0;
                                pierwsza2.znaleziona = true;
                                druga2.znaleziona = true;
                                pierwsza2.stan = true;
                                druga2.stan = true;
                                setLayout(null);
                                pierwsza2.setVisible(false);
                                druga2.setVisible(false);

                            }


                            pierwsza2 = null;
                            druga2 = null;
                        }

                        //============================================================


                        for (int i = 0; i < tab.length; i++) {
                            if (source == tab[i]) {
                                if (tab[i].stan == true) {

                                    if (tab[i].znaleziona == false) {
                                        tab[i].setIcon(tab[i].obraz);
                                        tab[i].stan = false;
                                    }
                                }

                            }
                        }


                    }
                }
            }
            try {
                Thread.sleep(3);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Klik = 0;
        }
    }


    public void Koniec() {
        Wynik w = new Wynik(wyb, wyb.rozmiar, czas);
        this.dispose();
        processWindowEvent(new WindowEvent(w, WindowEvent.WINDOW_OPENED));

    }
}



