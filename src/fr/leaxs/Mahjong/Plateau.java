package fr.leaxs.Mahjong;

import fr.leaxs.GUI.RessourceManager;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

public class Plateau {

    public TuileSelectionnee tuileSelectionnee;

    private int NOMBRE_LIGNE, NOMBRE_COLONNE, HAUTEUR;
    private Tuile[][][] plateau;
    private int tuileEnJeu;

    public Plateau() throws FileNotFoundException, IOException {
        ArrayList<Tuile> tuiles = new ArrayList<>();

        for (int j = 0; j<RessourceManager.NOMBRE_TUILE_DIFFERENTES; j++) {
            for (int i = 0; i < 24; i++) {
                tuiles.add(new Tuile(j));
            }
        }
        Collections.shuffle(tuiles);
        tuileEnJeu = tuiles.size();

        BufferedReader fichier = new BufferedReader(new FileReader("Mahjong.csv"));
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

    public void afficherConsole() {
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

    public boolean selectionnerTuile(int indexLigne, int indexColonne, int indexHauteur) {
        Tuile tuile = getTuile(indexLigne, indexColonne, indexHauteur);
        boolean tuileTrouvee = tuile != null;

        if (tuileTrouvee) {
            if (estJouable(indexLigne, indexColonne, indexHauteur)) {
                if (tuileSelectionnee == null) {
                    //Si aucune tuile n'est selectionné, on en selectionne une
                    tuileSelectionnee = new TuileSelectionnee(indexLigne, indexColonne, indexHauteur, tuile);
                } else if (tuileSelectionnee.getTuile() == tuile) {
                    //Si la tuile est la meme que la selectionné, on la deselectionne
                    tuileSelectionnee = null;
                    tuileTrouvee = true;
                } else if (tuileSelectionnee.getTuile().equals(tuile)) {
                    //Si la tuile est du meme type que celle selectionné, on supprime les deux
                    plateau[tuileSelectionnee.getIndexLigne()][tuileSelectionnee.getIndexColonne()][tuileSelectionnee.getHauteur()] = null;
                    plateau[indexLigne][indexColonne][indexHauteur] = null;
                    tuileSelectionnee = null;
                    tuileTrouvee = true;
                    
                    tuileEnJeu -= 2;
                    verificationFinDeJeu();
                }
            }

        }
        return tuileTrouvee;
    }

    public boolean estJouable(int indexLigne, int indexColonne, int indexHauteur) {
        boolean estJouable = true;
        if (indexHauteur != HAUTEUR - 1) {
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    if (estJouable) {
                        estJouable = getTuile(indexLigne + x, indexColonne + y, indexHauteur + 1) == null;
                    }
                }
            }
        }
        if (estJouable) {
            boolean libreCoteDroit = true;
            boolean libreCoteGauche = true;
            for (int x = -1; x <= 1; x++) {
                libreCoteGauche = getTuile(indexLigne + x, indexColonne - 2, indexHauteur) == null ? libreCoteGauche : false;
                libreCoteDroit = getTuile(indexLigne + x, indexColonne + 2, indexHauteur) == null ? libreCoteDroit : false;
            }
            estJouable = libreCoteDroit || libreCoteGauche;
        }
        return estJouable;
    }

    public Tuile getTuile(int indexLigne, int indexColonne, int indexHauteur) {
        Tuile tuile = null;
        if (indexLigne < NOMBRE_LIGNE && indexColonne < NOMBRE_COLONNE
                && indexLigne >= 0 && indexColonne >= 0) {
            tuile = plateau[indexLigne][indexColonne][indexHauteur];
        }
        return tuile;
    }

    public TuileSelectionnee getTuileSelectionnee() {
        return tuileSelectionnee;
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

    private void verificationFinDeJeu() 
    {
        if(tuileEnJeu<=0)
        {
            JOptionPane.showMessageDialog(null, "Vous avez gagné !", "Victoire", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
