package memory;

import java.awt.*;

public class Main {

    public static final String ROOT_DIR = "resources\\";


    public static void main(String[] args) {


        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MyFrame();
            }
        });


    }

}
