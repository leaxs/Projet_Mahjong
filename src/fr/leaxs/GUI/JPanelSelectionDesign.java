package fr.leaxs.GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.RescaleOp;
import java.util.ArrayList;
import javax.swing.JPanel;

public class JPanelSelectionDesign extends JPanel implements MouseListener
{
    private ArrayList<BufferedImage> listeTuile;
    private int tuileSelected;

    public JPanelSelectionDesign(ArrayList<BufferedImage> listeTuile) {
        super();
        this.listeTuile = listeTuile;
        tuileSelected = -1;
        addMouseListener(this);
    }
    
    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        if(listeTuile != null)
        {
            final int nombreTuileHorizontale = (int) (this.getSize().getWidth()/(RessourceManager.LARGEUR_TUILE+10));
            for(int i = 0; i < listeTuile.size(); i++)
            {
                int x = i%nombreTuileHorizontale;
                int y = i/nombreTuileHorizontale;

                BufferedImageOp op = new RescaleOp(new float[]{0.8f,1.2f,0.8f,1.0f},new float[4],null);
                if(i == tuileSelected)
                    g.drawImage(op.filter(listeTuile.get(i), null), x*(RessourceManager.LARGEUR_TUILE+10), y*(RessourceManager.HAUTEUR_TUILE+10), this);
                else
                    g.drawImage(listeTuile.get(i), x*(RessourceManager.LARGEUR_TUILE+10), y*(RessourceManager.HAUTEUR_TUILE+10), this);
            }
        }
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        final int nombreTuileHorizontale = (int) (this.getSize().getWidth()/(RessourceManager.LARGEUR_TUILE+10));
        this.setPreferredSize(new Dimension(
                (listeTuile.size()%nombreTuileHorizontale)*(RessourceManager.LARGEUR_TUILE+10),
                (listeTuile.size()/nombreTuileHorizontale)*(RessourceManager.HAUTEUR_TUILE+10)
        ));
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {
        final int nombreTuileHorizontale = (int) (this.getSize().getWidth()/(RessourceManager.LARGEUR_TUILE+10));
        int coordonneX = e.getX()/(RessourceManager.LARGEUR_TUILE+10);
        int coordonneY = e.getY()/(RessourceManager.HAUTEUR_TUILE+10);
        tuileSelected = coordonneX + nombreTuileHorizontale*coordonneY;
        if(tuileSelected<-1 || tuileSelected>=listeTuile.size())
            tuileSelected = -1;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
    public BufferedImage removeSelectedImage()
    {
        BufferedImage result = null;
        if(tuileSelected!=-1)
        {
            result = listeTuile.remove(tuileSelected);
            if(tuileSelected>=listeTuile.size())
                tuileSelected = -1;
            repaint();
        }
        return result;  
    }
    
    public void addImage(BufferedImage img)
    {
        if(img != null)
        {
            listeTuile.add(img);
            repaint();
        }
    }

    public boolean estComplet() {
        return listeTuile.size()>=6;
    }
}
