package Vues.Panneaux;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Panneau qui contient un tableau de données en utilisant JTable.
 *
 * @author Thiago Ferreira, Louis-Joseph Laforest, Hanz Sami & Isaac D. Zolana
 * @version A2020
 */
public class PanneauBasDroit extends JPanel {

    /* La couleur de l'entête */
    public static final Color COULEUR_ENTETE = Color.decode("#627AAD");
    /* L'entête du tableau. contient les différents titre de colonne */
    public static final String[] ENTETE = {"Source", "Destination", "Montant", "Status"};

    /* Contient les données du tableau */
    private Object[][] donnees = {};
    /* Le tableau du panneau */
    JTable tableau;

    /* Le modèle du tableau dans le panneau */
    DefaultTableModel modeleTableau;

    public PanneauBasDroit() {
        /* Modifie le layout du panneau */
        super(new GridLayout(1, 0));

        tableau = new JTable(donnees, ENTETE);
        tableau.getTableHeader().setBackground(COULEUR_ENTETE);
        tableau.setPreferredScrollableViewportSize(
                new Dimension(500, 70));
        tableau.setFillsViewportHeight(true);

        // barre de déroulement du panneau
        JScrollPane barreDeroulement = new JScrollPane(tableau);

        // ajout de la barre de déroulement au panneau
        add(barreDeroulement);
    }

    /**
     * Permet de modifier les données présentes dans le tableau.
     *
     * @param modeleTableau Tableau contenant la forme du tableau
     *                      contenant les données des transactions.
     */
    public void initialiserDonnees(DefaultTableModel modeleTableau) {
        this.modeleTableau = modeleTableau;
        this.tableau.setModel(this.modeleTableau);
    }

    /**
     * <p>Cette foction permet de gérer les changements sur le
     * tableau de trasaction lorsqu'une transaction survient.</p>
     */
    public void mettreAJourTableau() {
        this.modeleTableau.fireTableDataChanged();
    }
}