package fr.leaxs.Mahjong;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Fenetre extends JFrame
{
    private Plateau plateau;
    public Fenetre()
    {
        super("mahjong");
        try {
            this.plateau = new Plateau();
        } catch (IOException ex) {
            Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.setSize(plateau.getNombreColonne()*30,plateau.getNombreLigne()*40);
        
        this.add(new JPanelTuileDrawer(plateau));
        
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
