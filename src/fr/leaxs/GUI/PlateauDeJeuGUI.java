package fr.leaxs.GUI;

import fr.leaxs.Mahjong.Fenetre;
import fr.leaxs.Mahjong.Plateau;
import fr.leaxs.Mahjong.Tuile;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImageOp;
import java.awt.image.RescaleOp;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PlateauDeJeuGUI extends JPanel implements MouseListener {

    private final int LARGEUR_TUILE;
    private final int HAUTEUR_TUILE;
    private final int[][] offsets;
    private final Fenetre fenetre;

    public PlateauDeJeuGUI(Fenetre fenetre) {
        super();

        LARGEUR_TUILE = 80;
        HAUTEUR_TUILE = 120;
        this.offsets = new int[][]{{0, 0}, {LARGEUR_TUILE / 2, 0}, {0, HAUTEUR_TUILE / 2}, {LARGEUR_TUILE / 2, HAUTEUR_TUILE / 2}};
        this.addMouseListener(this);
        this.fenetre = fenetre;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Tuile tuile;
        for (int indexHauteur = 0; indexHauteur < this.fenetre.getPlateau().getHauteur(); indexHauteur++) {
            for (int indexLigne = 0; indexLigne < this.fenetre.getPlateau().getNombreLigne(); indexLigne++) {
                for (int indexColone = this.fenetre.getPlateau().getNombreColonne() - 1; indexColone >= 0; indexColone--) {
                    tuile = this.fenetre.getPlateau().getTuile(indexLigne, indexColone, indexHauteur);
                    if (tuile != null) {
                        if (this.fenetre.getPlateau().getTuileSelectionnee() != null && this.fenetre.getPlateau().getTuileSelectionnee().getTuile() == tuile) {
                            BufferedImageOp op = new RescaleOp(new float[]{0.8f, 1.2f, 0.8f, 1.0f}, new float[4], null);
                            g.drawImage(
                                op.filter(this.fenetre.getRessourceManager().getImagesJeu().get(tuile.getType()),null),
                                (int) ((float) indexColone / 2 * LARGEUR_TUILE) + 8 * indexHauteur - 4 * indexColone,
                                (int) ((float) indexLigne / 2 * HAUTEUR_TUILE) - 8 * indexHauteur - 4 * indexLigne,
                                LARGEUR_TUILE, HAUTEUR_TUILE, this);
                        } else {
                            g.drawImage(
                                    this.fenetre.getRessourceManager().getImagesJeu().get(tuile.getType()),
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
        System.out.println(this.fenetre.getRessourceManager().getImagesJeu().get(0));
        int ligneTuile;
        int colonneTuile;
        final int curseurX = e.getX();
        final int curseurY = e.getY();

        int z = this.fenetre.getPlateau().getHauteur() - 1;
        boolean tuileFound = false;

        while (!tuileFound && z >= 0) {
            //tuile paire
            colonneTuile = (curseurX - 8 * z) / (LARGEUR_TUILE - 8);
            ligneTuile = (curseurY + 8 * z) / (HAUTEUR_TUILE - 8);
            tuileFound = this.fenetre.getPlateau().selectionnerTuile(ligneTuile * 2, colonneTuile * 2, z);

            //tuile impaire
            if (!tuileFound) {
                int i = 0;
                while (!tuileFound && i < offsets.length) {
                    colonneTuile = (int) (((float) (curseurX - offsets[i][0] - 8 * z) / (LARGEUR_TUILE - 8)) * 2);
                    ligneTuile = (int) (((float) (curseurY - offsets[i][1] + 8 * z) / (HAUTEUR_TUILE - 8)) * 2);
                    tuileFound = this.fenetre.getPlateau().selectionnerTuile(ligneTuile, colonneTuile, z);
                    i++;
                }
            }

            z--;
        }
        if(this.fenetre.getPlateau().partieTerminee())
        {
            JOptionPane.showMessageDialog(null, "Vous avez gagné !", "Victoire", JOptionPane.INFORMATION_MESSAGE);
            fenetre.afficherMenuPricipale(this);
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
