package Vues.Panneaux;

import Vues.Fenetres.FenetreTransactions;

import javax.swing.*;
import java.awt.*;

/**
 * <p>Panneau vide permttant la dispotion des autres panneaux.
 * Aucune information ne sera affichée sur ce panneau.</p>
 *
 * @author Thiago Ferreira, Louis-Joseph Laforest, Hanz Sami & Isaac D. Zolana
 * @version A2020
 */
public class PanneauBasGauche extends JPanel {

    /**
     * <p>Constructeur par défaut qui configure la couleur du panneau.</p>
     */
    public PanneauBasGauche() {
        this.setBackground(FenetreTransactions.COULEUR_FOND);
    }
}