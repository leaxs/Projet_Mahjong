package fr.leaxs.Mahjong;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Plateau {

    private int NOMBRE_LIGNE, NOMBRE_COLONE, HAUTEUR;
    private Tuile[][][] plateau;

    public Plateau() throws FileNotFoundException, IOException {
        ArrayList<Tuile> tuiles = new ArrayList<>();

        for (Type_Tuile type : Type_Tuile.values()) {
            for (int i = 0; i < 36; i++) {
                tuiles.add(new Tuile(type));
            }
        }
        Collections.shuffle(tuiles);

        BufferedReader fichier = new BufferedReader(new FileReader("Mahjong.csv"));
        int indexLigneFichier = 0;
        while (fichier.ready()) {
            String line = fichier.readLine();
            if (indexLigneFichier == 1) {
                String[] grandeursTable = line.split(";");
                NOMBRE_LIGNE = Integer.parseInt(grandeursTable[0]);
                NOMBRE_COLONE = Integer.parseInt(grandeursTable[1]);
                HAUTEUR = Integer.parseInt(grandeursTable[2]);

                plateau = new Tuile[NOMBRE_LIGNE][NOMBRE_COLONE][HAUTEUR];
            } else if (indexLigneFichier > 1) {
                String[] repartitionPlateau = line.split(";");
                for (int i = 0; i < repartitionPlateau.length; i++) {
                    if (repartitionPlateau[i].equals("x")) {
                        plateau[(indexLigneFichier - 2) % NOMBRE_LIGNE][i][(indexLigneFichier-2) / NOMBRE_LIGNE] = tuiles.remove(0);
                    }
                }
            }
            indexLigneFichier++;
        }
        fichier.close();
    }

    void afficherConsole() {
        System.out.println("Mahjong");
        for (int z = 0; z < HAUTEUR; z++) {
            System.out.println("Etage "+z);
            for (int x = 0; x < NOMBRE_LIGNE; x++) {
                for (int y = 0; y < NOMBRE_COLONE; y++) {
                    if(plateau[x][y][z] != null)
                        System.out.print(plateau[x][y][z].toString().substring(0,1));
                    else
                        System.out.print(" ");
                }
                System.out.println();
            }
        }
    }

    public int getNombreLigne() {
        return NOMBRE_LIGNE;
    }

    public int getNombreColone() {
        return NOMBRE_COLONE;
    }

    public int getHauteur() {
        return HAUTEUR;
    }

    public Tuile getTuileAt(int x, int y, int z) {
        return plateau[x][y][z];
    }
    
    

}
