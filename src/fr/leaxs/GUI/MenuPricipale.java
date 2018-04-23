package fr.leaxs.GUI;

import fr.leaxs.Mahjong.Fenetre;

public class MenuPricipale extends javax.swing.JPanel 
{
    private final Fenetre fenetre;
    
    public MenuPricipale(Fenetre fenetre) {
        initComponents();
        this.fenetre = fenetre;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        boutonJouer = new javax.swing.JButton();
        boutonSettings = new javax.swing.JButton();
        boutonQuitter = new javax.swing.JButton();

        jButton3.setText("jButton3");

        setLayout(new java.awt.GridLayout(4, 0));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Mah-jong");
        add(jLabel2);

        boutonJouer.setText("Jouer");
        boutonJouer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boutonJouerActionPerformed(evt);
            }
        });
        add(boutonJouer);

        boutonSettings.setText("Settings");
        boutonSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boutonSettingsActionPerformed(evt);
            }
        });
        add(boutonSettings);

        boutonQuitter.setText("Quitter");
        boutonQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boutonQuitterActionPerformed(evt);
            }
        });
        add(boutonQuitter);
    }// </editor-fold>//GEN-END:initComponents

    private void boutonJouerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boutonJouerActionPerformed
        fenetre.jouer();
    }//GEN-LAST:event_boutonJouerActionPerformed

    private void boutonSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boutonSettingsActionPerformed
       fenetre.settings();
    }//GEN-LAST:event_boutonSettingsActionPerformed

    private void boutonQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boutonQuitterActionPerformed
        fenetre.quitter();
    }//GEN-LAST:event_boutonQuitterActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boutonJouer;
    private javax.swing.JButton boutonQuitter;
    private javax.swing.JButton boutonSettings;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
