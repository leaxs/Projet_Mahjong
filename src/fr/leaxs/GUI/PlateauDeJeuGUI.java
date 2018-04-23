package fr.leaxs.GUI;

import fr.leaxs.Mahjong.Plateau;
import fr.leaxs.Mahjong.Tuile;
import fr.leaxs.Mahjong.TuileSelectionnee;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PlateauDeJeuGUI extends JPanel implements MouseListener {

    private final int LARGEUR_TUILE;
    private final int HAUTEUR_TUILE;
    private final int[][] offsets;
    private BufferedImage[] images;
    private Plateau plateau;

    public PlateauDeJeuGUI(Plateau plateau) {
        super();

        LARGEUR_TUILE = 80;
        HAUTEUR_TUILE = 120;
        this.offsets = new int[][]{{0, 0}, {LARGEUR_TUILE / 2, 0}, {0, HAUTEUR_TUILE / 2}, {LARGEUR_TUILE / 2, HAUTEUR_TUILE / 2}};

        try {
            this.plateau = plateau;
            images = new BufferedImage[RessourceManager.NOMBRE_TUILE_DIFFERENTES];
            images[0] = ImageIO.read(new File("img/renard.png"));
            images[1] = ImageIO.read(new File("img/melanchon.png"));
            images[2] = ImageIO.read(new File("img/TubGuys.png"));
            images[3] = ImageIO.read(new File("img/Jeanne.png"));
            images[4] = ImageIO.read(new File("img/fry.png"));
            images[5] = ImageIO.read(new File("img/chien.png"));
            images[6] = ImageIO.read(new File("img/Selection.png"));
        } catch (IOException ex) {
            Logger.getLogger(PlateauDeJeuGUI.class.getName()).log(Level.SEVERE, null, ex);
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
                        if (tuile == plateau.tuileSelectionnee.getTuile()) {
                            BufferedImageOp op = new RescaleOp(new float[]{0.8f, 1.2f, 0.8f, 1.0f}, new float[4], null);
                            g.drawImage(
                                op.filter(images[tuile.getType()],null),
                                (int) ((float) indexColone / 2 * LARGEUR_TUILE) + 8 * indexHauteur - 4 * indexColone,
                                (int) ((float) indexLigne / 2 * HAUTEUR_TUILE) - 8 * indexHauteur - 4 * indexLigne,
                                LARGEUR_TUILE, HAUTEUR_TUILE, this);
                        } else {
                            g.drawImage(
                                    images[tuile.getType()],
                                    (int) ((float) indexColone / 2 * LARGEUR_TUILE) + 8 * indexHauteur - 4 * indexColone,
                                    (int) ((float) indexLigne / 2 * HAUTEUR_TUILE) - 8 * indexHauteur - 4 * indexLigne,
                                    LARGEUR_TUILE, HAUTEUR_TUILE, this);
                        }
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
        int ligneTuile;
        int colonneTuile;
        final int curseurX = e.getX();
        final int curseurY = e.getY();

        int z = plateau.getHauteur() - 1;
        boolean tuileFound = false;

        while (!tuileFound && z >= 0) {
            //tuile paire
            colonneTuile = (curseurX - 8 * z) / (LARGEUR_TUILE - 8);
            ligneTuile = (curseurY + 8 * z) / (HAUTEUR_TUILE - 8);
            tuileFound = plateau.selectionnerTuile(ligneTuile * 2, colonneTuile * 2, z);

            //tuile impaire
            if (!tuileFound) {
                int i = 0;
                while (!tuileFound && i < offsets.length) {
                    colonneTuile = (int) (((float) (curseurX - offsets[i][0] - 8 * z) / (LARGEUR_TUILE - 8)) * 2);
                    ligneTuile = (int) (((float) (curseurY - offsets[i][1] + 8 * z) / (HAUTEUR_TUILE - 8)) * 2);
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
