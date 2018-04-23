package fr.leaxs.GUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * Gère les ressources graphiques du programme
 *
 * @author Axel Schneider
 */
public class RessourceManager {

    private ArrayList<BufferedImage> imageTuiles;
    public static final String cheminAccesRessourceGraphique = "images/";
    public static final int NOMBRE_TUILE_DIFFERENTES = 6;
    public static final int LARGEUR_TUILE = 100;
    public static final int HAUTEUR_TUILE = 150;

    public RessourceManager() {
        imageTuiles = new ArrayList<>();
    }

    
    public static ArrayList<BufferedImage> getImagesDansDossier(String path) {
        ArrayList<BufferedImage> images = new ArrayList<>();
        File dossier = new File(path);
        FilenameFilter extentionImage = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".png");
            }
        };
        if (dossier.listFiles(extentionImage) != null) {
            for (File image : dossier.listFiles(extentionImage)) {
                try {
                    images.add(ImageIO.read(image));
                } catch (IOException ex) {
                    Logger.getLogger(ChoixTextureTuile.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return images;
    }

    public void genererImageSubsitution() {

    }

    public boolean completerListe(ArrayList<BufferedImage> listeACompleter, ArrayList<BufferedImage> listeImageDisponible) 
    {
        if(listeACompleter == null)
            listeACompleter = new ArrayList<>();
        
        boolean completeeListe = JOptionPane.showConfirmDialog(
                null,
                "Le design n'est pas complet. Voulez-vous poursuivre en le complétant automatiquement avec des images du dossier?",
                "Veuillez choisir une option",
                JOptionPane.YES_NO_OPTION
        ) == JOptionPane.OK_OPTION;

        if (completeeListe) {
            int nombreDImagesAAjouter = 6 - listeACompleter.size();
            for (int i = 0; i < nombreDImagesAAjouter; i++) {
                listeACompleter.add(listeImageDisponible.remove(0));
            }
            this.setImages(listeACompleter);
        }
        return completeeListe;
    }

    public void setImages(ArrayList<BufferedImage> imagesSelectionnee) 
    {
        this.imageTuiles = imagesSelectionnee;
    }

    public ArrayList<BufferedImage> getImagesJeu() {
        return imageTuiles;
    }
    
    public boolean packDeTuilleComplet()
    {
        return imageTuiles != null && imageTuiles.size() >= NOMBRE_TUILE_DIFFERENTES;
    }

}
