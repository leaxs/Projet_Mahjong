package fr.leaxs.Mahjong;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class JPanelTuileDrawer extends JPanel implements MouseListener {

    private final int LARGEUR_TUILE;
    private final int HAUTEUR_TUILE;
    private BufferedImage[] images;
    private Plateau plateau;

    public JPanelTuileDrawer(Plateau plateau) {
        super();

        LARGEUR_TUILE = 50;
        HAUTEUR_TUILE = 60;

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

        this.addMouseListener(this);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Tuile tuile;
        for (int indexHauteur = 0; indexHauteur < plateau.getHauteur(); indexHauteur++) {
            for (int indexLigne = 0; indexLigne < plateau.getNombreLigne(); indexLigne++) {
                for (int indexColone = 0; indexColone < plateau.getNombreColonne(); indexColone++) {
                    tuile = plateau.getTuileAt(indexLigne, indexColone, indexHauteur);
                    if (tuile != null) {
                        g.drawImage(
                                images[tuile.getType().ordinal()],
                                (int) ((float) indexColone / 2 * LARGEUR_TUILE) + 2 * indexHauteur,
                                (int) ((float) indexLigne / 2 * HAUTEUR_TUILE) + indexHauteur,
                                50, 60, this);
                    }
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("---");
        int ligneTuile;
        int colonneTuile;
        int z = plateau.getHauteur()-1;
        boolean tuileFound = false;
        while (!tuileFound && z >= 0) {
            colonneTuile = (e.getX() - 2 * z) / LARGEUR_TUILE;
            ligneTuile = (e.getY() - z) / HAUTEUR_TUILE;
            tuileFound = plateau.selectTuileAt(ligneTuile*2, colonneTuile*2, z);
            z--;
        }
        this.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
