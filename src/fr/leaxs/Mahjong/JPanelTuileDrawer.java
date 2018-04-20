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
    private final int[][] offsets;
    private BufferedImage[] images;
    private Plateau plateau;

    public JPanelTuileDrawer(Plateau plateau) {
        super();

        LARGEUR_TUILE = 100;
        HAUTEUR_TUILE = 150;
        this.offsets = new int[][]{{0, 0}, {LARGEUR_TUILE / 2, 0}, {0, HAUTEUR_TUILE / 2}, {LARGEUR_TUILE / 2, HAUTEUR_TUILE / 2}};

        try {
            this.plateau = plateau;
            images = new BufferedImage[Type_Tuile.values().length + 1];
            images[0] = ImageIO.read(new File("img/renard.png"));
            images[1] = ImageIO.read(new File("img/melanchon.png"));
            images[2] = ImageIO.read(new File("img/TubGuys.png"));
            images[3] = ImageIO.read(new File("img/Jeanne.png"));
            images[4] = ImageIO.read(new File("img/fry.png"));
            images[5] = ImageIO.read(new File("img/chien.png"));
            images[6] = ImageIO.read(new File("img/Selection.png"));
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
                for (int indexColone = plateau.getNombreColonne() - 1; indexColone >= 0; indexColone--) {
                    tuile = plateau.getTuile(indexLigne, indexColone, indexHauteur);
                    if (tuile != null) {
                        g.drawImage(
                                images[tuile.getType().ordinal()],
                                (int) ((float) indexColone / 2 * LARGEUR_TUILE) + 10 * indexHauteur - 5 * indexColone,
                                (int) ((float) indexLigne / 2 * HAUTEUR_TUILE) - 10 * indexHauteur - 5 * indexLigne,
                                LARGEUR_TUILE, HAUTEUR_TUILE, this);
                    }
                }
            }
        }
        TuileSelectionnee selection = plateau.getTuileSelectionnee();
        if (selection != null) {
            g.drawImage(
                    images[6],
                    (int) ((float) selection.getIndexColonne() / 2 * LARGEUR_TUILE) + 10 * selection.getHauteur() - 5 * selection.getIndexColonne(),
                    (int) ((float) selection.getIndexLigne() / 2 * HAUTEUR_TUILE) - 10 * selection.getHauteur() - 5 * selection.getIndexLigne(),
                    LARGEUR_TUILE, HAUTEUR_TUILE, this);
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
        int ligneTuile;
        int colonneTuile;
        final int curseurX = e.getX();
        final int curseurY = e.getY();

        int z = plateau.getHauteur() - 1;
        boolean tuileFound = false;

        while (!tuileFound && z >= 0) {
            //tuile paire
            colonneTuile = (curseurX - 10 * z) / (LARGEUR_TUILE - 10);
            ligneTuile = (curseurY + 10 * z) / (HAUTEUR_TUILE - 10);
            tuileFound = plateau.selectionnerTuile(ligneTuile * 2, colonneTuile * 2, z);

            //tuile impaire
            if (!tuileFound) {
                int i = 0;
                while (!tuileFound && i < offsets.length) {
                    colonneTuile = (int) (((float) (curseurX - offsets[i][0] - 10 * z) / (LARGEUR_TUILE - 10)) * 2);
                    ligneTuile = (int) (((float) (curseurY - offsets[i][1] + 10 * z) / (HAUTEUR_TUILE - 10)) * 2);
                    tuileFound = plateau.selectionnerTuile(ligneTuile, colonneTuile, z);
                    i++;
                }
            }

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
