package fr.leaxs.Mahjong;

import fr.leaxs.GUI.ChoixTextureTuile;
import fr.leaxs.GUI.PlateauDeJeuGUI;
import fr.leaxs.GUI.RessourceManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Fenetre extends JFrame
{
    private RessourceManager ressourceManager;
    private Plateau plateau;
    public Fenetre()
    {
        super("mahjong");
        ressourceManager = new RessourceManager();
        ChoixTextureTuile choix = new ChoixTextureTuile(ressourceManager);
        this.add(choix);
        this.setMinimumSize(choix.getMinimumSize());
        /*
        try {
            this.plateau = new Plateau();
        } catch (IOException ex) {
            Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
        }
        //*/
        //this.setSize(plateau.getNombreColonne()*30,plateau.getNombreLigne()*40);
        
        //this.add(new PlateauDeJeuGUI(plateau));
        
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }    
}
