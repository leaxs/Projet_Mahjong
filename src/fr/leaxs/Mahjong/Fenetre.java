package fr.leaxs.Mahjong;

import fr.leaxs.GUI.ChoixTextureTuile;
import fr.leaxs.GUI.MenuPricipale;
import fr.leaxs.GUI.PlateauDeJeuGUI;
import fr.leaxs.GUI.RessourceManager;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Fenetre extends JFrame {

    private final RessourceManager ressourceManager;
    private final Plateau plateau;
    private final ChoixTextureTuile choix;
    private final PlateauDeJeuGUI plateauGUI;
    private final MenuPricipale menuPrincipale;

    public Fenetre() {
        super("mahjong");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Fenetre.class.getName()).log(Level.SEVERE, null, ex);
        }

        ressourceManager = new RessourceManager();
        choix = new ChoixTextureTuile(this);
        plateau = new Plateau();
        plateauGUI = new PlateauDeJeuGUI(this);
        menuPrincipale = new MenuPricipale(this);

        this.add(menuPrincipale);

        this.setSize(300, 400);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void jouer() {
        
        if (!ressourceManager.packDeTuilleComplet()) {
            if(!ressourceManager.completerListe(null, RessourceManager.getImagesDansDossier(RessourceManager.cheminAccesRessourceGraphique)))
                return;
        }
        plateau.loadCarte("");
        this.setSize(plateau.getNombreColonne() * 30, plateau.getNombreLigne() * 40);
        this.remove(menuPrincipale);
        this.add(plateauGUI);

    }

    public void settings() {
        this.remove(menuPrincipale);
        this.setMinimumSize(choix.getMinimumSize());

        this.add(choix);
    }

    public void afficherMenuPricipale(JPanel panel) {
        this.remove(panel);
        this.add(menuPrincipale);
    }

    public void quitter() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public RessourceManager getRessourceManager() {
        return ressourceManager;
    }

    public Plateau getPlateau() {
        return plateau;
    }

}
