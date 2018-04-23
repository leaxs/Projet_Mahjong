package fr.leaxs.Mahjong;

public class ClassTester {

    public static void testTuile() {
        Tuile tuile = new Tuile(0);
        Tuile tuile2 = new Tuile(1);
        Tuile tuile3 = new Tuile(1);

        System.out.println("Test de la classe Tuile");
        System.out.println("> tuile1 equals tuile2 ? " + tuile.equals(tuile2));
        System.out.println("> tuile1 equals tuile3 ? " + tuile.equals(tuile3));
        System.out.println("> tuile2 equals tuile3 ? " + tuile2.equals(tuile3));
    }

    public static void testPlateau() {

        Plateau plateau = new Plateau();
        plateau.afficherConsole();

    }
}
