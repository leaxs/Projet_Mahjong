package fr.leaxs.Mahjong;

public class TuileSelectionnee 
{
    private final int indexLigne;
    private final int indexColonne;
    private final int hauteur;
    private final Tuile tuile;

    public TuileSelectionnee(int indexLigne, int indexColonne, int hauteur, Tuile tuile) {
        this.indexLigne = indexLigne;
        this.indexColonne = indexColonne;
        this.hauteur = hauteur;
        this.tuile = tuile;
    }

    public int getIndexLigne() {
        return indexLigne;
    }

    public int getIndexColonne() {
        return indexColonne;
    }

    public int getHauteur() {
        return hauteur;
    }

    public Tuile getTuile() {
        return tuile;
    }
    
}
