package memory;

import java.io.Serializable;

public class Gracz implements Serializable, Comparable<Gracz> {

    String nick;
    String czas;
    int rozmiar;
    Integer punkty;

        public Gracz(){

        }

        public Gracz(String nick, String czas, int rozmiar, Integer punkty){

            this.nick = nick;
            this.czas = czas;
            this.rozmiar = rozmiar;
            this.punkty = punkty;


        }

        public String toString(){
            return nick + " (Czas: " + czas + ", rozmiar: " + rozmiar + "x" + rozmiar + ", memory.Wynik: " + punkty + "pkt)";
        }


    @Override
    public int compareTo(Gracz o) {
        int compare = this.punkty.compareTo(o.punkty);
        if(compare > 0) return -1;
        if(compare < 0) return 1;
        return 0;

    }
}
