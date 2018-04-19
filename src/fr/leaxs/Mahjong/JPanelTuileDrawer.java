package fr.leaxs.Mahjong;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class JPanelTuileDrawer extends JPanel {

    private BufferedImage[] images;
    private Plateau plateau;

    public JPanelTuileDrawer(Plateau plateau) 
    {
        super();
        try {
            this.plateau = plateau;
            images = new BufferedImage[Type_Tuile.values().length];
            images[0] = ImageIO.read(new File("img/renard.png"));
            images[1] = ImageIO.read(new File("img/melanchon.png"));
            images[2] = ImageIO.read(new File("img/TubGuys.png"));
            images[3] = ImageIO.read(new File("img/Jeanne.png"));
            images[4] = ImageIO.read(new File("img/fry.png"));
            images[5] = ImageIO.read(new File("img/chien.png"));
        } catch (IOException ex) {
            Logger.getLogger(JPanelTuileDrawer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Tuile tuile;
        for (int z = 0; z < plateau.getHauteur(); z++) {
            for (int y = 0; y < plateau.getNombreLigne(); y++) {
                for (int x = 0; x < plateau.getNombreColone(); x++) {
                    tuile = plateau.getTuileAt(y,x,z);
                    if(tuile != null)
                        g.drawImage(images[tuile.getType().ordinal()], (int)((float)x/2*50)+2*z, (int)((float)y/2*60)+z, 50, 60, this);
                }
            }
        }
    }
}
