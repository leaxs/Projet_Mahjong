package fr.leaxs.Mahjong;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClassTester 
{
    public static void testTuile()
    {
        Tuile tuile = new Tuile(0);
        Tuile tuile2 = new Tuile(1);
        Tuile tuile3 = new Tuile(1);
        
        System.out.println("Test de la classe Tuile");
        System.out.println("> tuile1 equals tuile2 ? " + tuile.equals(tuile2));
        System.out.println("> tuile1 equals tuile3 ? " + tuile.equals(tuile3));
        System.out.println("> tuile2 equals tuile3 ? " + tuile2.equals(tuile3));
    }
    
    public static void testPlateau()
    {
        try {
            Plateau plateau = new Plateau();
            plateau.afficherConsole();
        } catch (IOException ex) {
            Logger.getLogger(ClassTester.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
