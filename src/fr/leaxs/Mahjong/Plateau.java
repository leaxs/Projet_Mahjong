package fr.leaxs.Mahjong;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Plateau {

    public SelectionnedTuile selection1;

    private int NOMBRE_LIGNE, NOMBRE_COLONNE, HAUTEUR;
    private Tuile[][][] plateau;

    public Plateau() throws FileNotFoundException, IOException {
        ArrayList<Tuile> tuiles = new ArrayList<>();

        for (Type_Tuile type : Type_Tuile.values()) {
            for (int i = 0; i < 36; i++) {
                tuiles.add(new Tuile(type));
            }
        }
        Collections.shuffle(tuiles);

        BufferedReader fichier = new BufferedReader(new FileReader("Mahjong2.csv"));
        int indexLigneFichier = 0;
        while (fichier.ready()) {
            String line = fichier.readLine();
            if (indexLigneFichier == 1) {
                String[] grandeursTable = line.split(";");
                NOMBRE_LIGNE = Integer.parseInt(grandeursTable[0]);
                NOMBRE_COLONNE = Integer.parseInt(grandeursTable[1]);
                HAUTEUR = Integer.parseInt(grandeursTable[2]);

                plateau = new Tuile[NOMBRE_LIGNE][NOMBRE_COLONNE][HAUTEUR];
            } else if (indexLigneFichier > 1) {
                String[] repartitionPlateau = line.split(";");
                for (int i = 0; i < repartitionPlateau.length; i++) {
                    if (repartitionPlateau[i].equals("x")) {
                        plateau[(indexLigneFichier - 2) % NOMBRE_LIGNE][i][(indexLigneFichier - 2) / NOMBRE_LIGNE] = tuiles.remove(0);
                    }
                }
            }
            indexLigneFichier++;
        }
        fichier.close();
    }

    void afficherConsole() {
        System.out.println("Mahjong");
        for (int indexHauteur = 0; indexHauteur < HAUTEUR; indexHauteur++) {
            System.out.println("Etage " + indexHauteur);
            for (int indexLigne = 0; indexLigne < NOMBRE_LIGNE; indexLigne++) {
                for (int indexColonne = 0; indexColonne < NOMBRE_COLONNE; indexColonne++) {
                    if (plateau[indexLigne][indexColonne][indexHauteur] != null) {
                        System.out.print(plateau[indexLigne][indexColonne][indexHauteur].toString().substring(0, 1));
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }
    }

    public int getNombreLigne() {
        return NOMBRE_LIGNE;
    }

    public int getNombreColonne() {
        return NOMBRE_COLONNE;
    }

    public int getHauteur() {
        return HAUTEUR;
    }

    public Tuile getTuileAt(int indexLigne, int indexColonne, int indexHauteur) {
        if (indexLigne >= NOMBRE_LIGNE || indexColonne >= NOMBRE_COLONNE) {
            return null;
        }
        return plateau[indexLigne][indexColonne][indexHauteur];
    }

    //TODO decalage ligne et colonne
    boolean selectTuileAt(int indexLigne, int indexColonne, int indexHauteur) {
        
        System.out.println("Recherche de tuile en z=" + indexHauteur+" ("+indexLigne+"/"+indexColonne+")");
        Tuile tuile;
        boolean tuileFound;

        tuile = getTuileAt(indexLigne, indexColonne, indexHauteur);
        tuileFound = tuile != null;
        if (tuileFound) {
            System.out.println("Tuile Found");
            if (isSelectionnable(indexLigne, indexColonne, indexHauteur)) {
                System.out.println("Tuile selectable");
                if (selection1 == null) {
                    selection1 = new SelectionnedTuile(indexLigne, indexColonne, indexHauteur, tuile);
                    System.out.println("selectionned tuile");
                } else if (selection1.getTuile() == tuile) {
                    selection1 = null;
                    System.out.println("same tuile");
                    tuileFound = true;
                } else if (selection1.getTuile().equals(tuile)) {
                    System.out.println("removing");
                    plateau[selection1.getX()][selection1.getY()][selection1.getZ()] = null;
                    plateau[indexLigne][indexColonne][indexHauteur] = null;
                    selection1 = null;
                    tuileFound = true;
                }
            }

        }
        return tuileFound;
    }

    public boolean isSelectionnable(int indexLigne, int indexColonne, int indexHauteur) {
        int index1 = indexColonne - 2;
        int index2 = indexColonne + 2;
        
        if(index1<0)
            index1=0;
        if(index2>NOMBRE_COLONNE)
            index2=NOMBRE_COLONNE;
        
        return plateau[indexLigne][index1][indexHauteur] == null || plateau[indexLigne][index2][indexHauteur] == null;
    }

}
