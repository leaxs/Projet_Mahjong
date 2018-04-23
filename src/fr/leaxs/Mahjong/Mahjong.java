package fr.leaxs.Mahjong;

import javax.swing.SwingUtilities;

public class Mahjong {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Fenetre();
            }
        });
    }

}
