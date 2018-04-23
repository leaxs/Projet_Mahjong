package fr.leaxs.GUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Gère les ressources graphiques du programme
 * @author Axel Schneider
 */
public class RessourceManager 
{
    private BufferedImage[] imageTuiles;
    private BufferedImage imageSelection;
    public static final String cheminAccesRessourceGraphique = "images/";
    public static final int NOMBRE_TUILE_DIFFERENTES = 6;
    public static final int LARGEUR_TUILE = 100;
    public static final int HAUTEUR_TUILE = 150;
    
    public static ArrayList<BufferedImage> getImagesDansDossier(String path)
    {
        ArrayList<BufferedImage> images = new ArrayList<>();
        File dossier = new File(path);
        FilenameFilter extentionImage = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".png");
            }
        };
        if(dossier.listFiles(extentionImage) != null)
        {
            for(File image : dossier.listFiles(extentionImage))
            {
                try {
                    images.add(ImageIO.read(image));
                } catch (IOException ex) {
                    Logger.getLogger(ChoixTextureTuile.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return images;
    }

    void setImages(ArrayList<BufferedImage> imagesSelectionnee) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
