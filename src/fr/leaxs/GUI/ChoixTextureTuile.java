package fr.leaxs.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class ChoixTextureTuile extends javax.swing.JPanel {

    private ArrayList<BufferedImage> listeImageDossier;
    private ArrayList<BufferedImage> imagesSelectionnee;
    private final RessourceManager ressourceManager;

    public ChoixTextureTuile(RessourceManager ressourceManager) {
        this.ressourceManager = ressourceManager;
        listeImageDossier = RessourceManager.getImagesDansDossier("images/");
        imagesSelectionnee = new ArrayList<>(6);
        initComponents();
        this.setMinimumSize(new Dimension(420, 200));
        

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        conteneurPrincipal = new javax.swing.JPanel();              //contient les panneaux de tuiles + bouton d'ajout/retrait
        conteneurBoutonValidationCancel = new javax.swing.JPanel();

        jPanel1 = new javax.swing.JPanel();
        JButtonSelectionDesign = new javax.swing.JButton();
        JButtonDeselectionDesign = new javax.swing.JButton();

        JButtonValider = new javax.swing.JButton();
        JButtonAnnuler = new javax.swing.JButton();

        panelSelectionnable = new JPanelSelectionDesign(listeImageDossier);
        panelSelectionnee = new JPanelSelectionDesign(imagesSelectionnee);
        jScrollPane1 = new JScrollPane(panelSelectionnable);
        jScrollPane2 = new JScrollPane(panelSelectionnee);

        conteneurPrincipal.setLayout(new javax.swing.BoxLayout(conteneurPrincipal, javax.swing.BoxLayout.LINE_AXIS));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Design disponible"));
        jScrollPane1.setToolTipText("");
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setMinimumSize(new java.awt.Dimension(150, 200));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(150, 200));
        conteneurPrincipal.add(jScrollPane1);

        jPanel1.setMaximumSize(new java.awt.Dimension(60, 32767));
        jPanel1.setMinimumSize(new java.awt.Dimension(40, 60));
        jPanel1.setPreferredSize(new java.awt.Dimension(60, 300));
        jPanel1.setLayout(new java.awt.GridLayout(2, 0));

        JButtonSelectionDesign.setText(">>");
        JButtonSelectionDesign.setActionCommand("selectionnerDesign");
        JButtonSelectionDesign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonSelectionDesignActionPerformed(evt);
            }
        });
        jPanel1.add(JButtonSelectionDesign);

        JButtonDeselectionDesign.setText("<<");
        JButtonDeselectionDesign.setActionCommand("deselectionDesign");
        JButtonDeselectionDesign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonDeselectionDesignActionPerformed(evt);
            }
        });
        jPanel1.add(JButtonDeselectionDesign);

        conteneurPrincipal.add(jPanel1);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Design sélectionné"));
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setMaximumSize(new java.awt.Dimension(300, 32767));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(150, 200));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(250, 100));
        conteneurPrincipal.add(jScrollPane2);
        
        
        conteneurBoutonValidationCancel.setLayout(new java.awt.GridLayout(0, 2));

        JButtonAnnuler.setText("Annuler");
        JButtonAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActionPerformedAnnuler(evt);
            }
        });
        conteneurBoutonValidationCancel.add(JButtonAnnuler);

        JButtonValider.setText("Valider");
        JButtonValider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActionPerformedValider(evt);
            }
        });
        conteneurBoutonValidationCancel.add(JButtonValider);
                
        setLayout(new BorderLayout());
        add(conteneurPrincipal, BorderLayout.CENTER);
        add(conteneurBoutonValidationCancel,BorderLayout.SOUTH);
                
    }// </editor-fold>                        

    private void JButtonDeselectionDesignActionPerformed(java.awt.event.ActionEvent evt) {
        panelSelectionnable.addImage(panelSelectionnee.removeSelectedImage());
    }

    private void JButtonSelectionDesignActionPerformed(java.awt.event.ActionEvent evt) {
        if (!panelSelectionnee.estComplet()) {
            panelSelectionnee.addImage(panelSelectionnable.removeSelectedImage());
        } else {
            JOptionPane.showMessageDialog(null, "Il ne peut y avoir que 6 design de tuiles différents.", "Impossible de selectionner cette image", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ActionPerformedValider(java.awt.event.ActionEvent evt) {
        if(panelSelectionnee.estComplet())
        {
            ressourceManager.setImages(imagesSelectionnee);
            this.setVisible(false);
        }
        else if(listeImageDossier.size()+imagesSelectionnee.size()<6)
        {
           JOptionPane.showMessageDialog(null, "Pas assez d'images presente dans le dossier. Des substitues vont être générer!", "Erreur", JOptionPane.ERROR_MESSAGE); 
           //TODO generé des subsitues
        }
        else
        {
            int reponse = JOptionPane.showConfirmDialog(
                    this,
                    "Le design n'est pas complet. Voulez-vous poursuivre en le complétant automatiquement avec des images du dossier?",
                    "Veuillez choisir une option",                    
                    JOptionPane.YES_NO_OPTION
            );
            if(reponse==JOptionPane.OK_OPTION)
            {
                int nombreDImagesAAjouter = 6-imagesSelectionnee.size();
                for(int i = 0; i<nombreDImagesAAjouter;i++)
                    panelSelectionnee.addImage(listeImageDossier.remove(0));
                ressourceManager.setImages(imagesSelectionnee);
                this.setVisible(false);
            }
        }
    }

    private void ActionPerformedAnnuler(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        try {
            this.finalize();
        } catch (Throwable ex) {
            Logger.getLogger(ChoixTextureTuile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify 
    private javax.swing.JPanel conteneurPrincipal;
    private javax.swing.JPanel conteneurBoutonValidationCancel;

    private javax.swing.JButton JButtonValider;
    private javax.swing.JButton JButtonAnnuler;
    private javax.swing.JButton JButtonDeselectionDesign;
    private javax.swing.JButton JButtonSelectionDesign;
    private javax.swing.JPanel jPanel1;

    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JPanelSelectionDesign panelSelectionnable;
    private JPanelSelectionDesign panelSelectionnee;
    // End of variables declaration                   
}
