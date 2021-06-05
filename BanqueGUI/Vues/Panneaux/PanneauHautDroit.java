package Vues.Panneaux;

import Vues.Fenetres.FenetreTransactions;
import banque.Banque;

import javax.swing.*;
import java.awt.*;

public class PanneauHautDroit extends JPanel {

    private String nomUtilisateur;
    private String numeroCompte;
    private String solde;

    private String infos;
    private JLabel lblInfos;

    private BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);

    public PanneauHautDroit() {
        mettreAJourInformation();
        initialiser();
    }

    public void initialiser() {
        this.setBackground(FenetreTransactions.COULEUR_FOND);
        this.setLayout(layout);

        Font policeTexte = new Font("SansSerif", Font.PLAIN, 22);

        lblInfos = new JLabel(infos);
        lblInfos.setForeground(Color.white);
        lblInfos.setFont(policeTexte);

        ajouterComposants();
    }

    private void ajouterComposants() {
        this.add(lblInfos);
    }

    private void mettreAJourInformation() {
        nomUtilisateur = Banque.getInstance().getUtilisateurActif().
                getNomUtilisateur();
        solde = "Solde : " + Banque.getInstance().getUtilisateurActif().
                getSolde() + " $";
        numeroCompte = "Numéro de compte : " + Banque.getInstance().
                getUtilisateurActif().getNumeroDeCompte();

        infos = "<html>" + "<h1>" + nomUtilisateur + "</h1>" +
                numeroCompte + "<br/>" + solde + "</html>";
    }
    
    public void mettreAJourPanneau() {
    	mettreAJourInformation();
    	this.lblInfos.setText(infos);
    }
}