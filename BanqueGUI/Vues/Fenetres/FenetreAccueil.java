package Vues.Fenetres;

import javax.swing.JFrame;

import Controleurs.ControleurSoumettre;
import Vues.BarresMenu.MenuBarAccueil;
import Vues.Boutons.BoutonConnexion;
import Vues.Panneaux.PanneauAccueil;

/**
 * <p>
 * Cette classe permet de créer l'interface d'accueil de l'application de
 * transaction bancaire.
 * </p>
 *
 * @author Thiago Ferreira, Louis-Joseph Laforest, Hanz Sami & Isaac D. Zolana
 * @version A2020
 */
public class FenetreAccueil extends JFrame implements Runnable {
    /* Titre de la fenêtre d'accueil de l'application. */
    public static final String TITRE = "Application de transactions bancaires";
    /* Dimensions de la fenêtre d'accueil. */
    public static final int LONGUEUR = 400;
    public static final int HAUTEUR = 400;

    // Emplacement de l'image de la fenêtre accueil.
    public static final String REPERTOIRE = "/images/";
    public static final String NOM_IMAGE = "logo_ets_blanc.png";

    /*
     * STRATÉGIE : Premièrement, le constructeur Accueil permet configurer
     * certaines propriétés du JFrame à leur état initial. Deuxièmement, la
     * méthode initialiser permet d'ajouter les éléments graphiques à la fenêtre
     * d'accueil et la rendre visible. Finalement, les fonctions
     * ajouterBarreMenuAccueil et ajouterPanneauPrincipal permettent d'ajouter
     * les éléments graphiques à la fênetre d'accueil.
     */

    /**
     * <p>
     * Constructeur par défaut qui permet configurer certaines propriétés du
     * JFrame à leur état initial.
     * </p>
     */
    public FenetreAccueil() {
        initComposants();
    }

    public void initComposants() {

        this.setTitle(TITRE);
        this.setSize(LONGUEUR, HAUTEUR);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    /**
     * <p>
     * Fonction qui permet d'ajouter les éléments graphiques à la fenêtre
     * d'accueil et la rendre visible.
     * </p>
     */
    public void initialiser() {
        ajouterPanneauPrincipal();

        this.setVisible(true);
    }

    @Override
    public void run() {
        initComposants();
    }

    /**
     * <p>
     * Ajoute le panneau principal de la fenêtre d'accueil.
     * </p>
     */
    private void ajouterPanneauPrincipal() {
        PanneauAccueil panneau = new PanneauAccueil();
        panneau.ajouterBouton(new BoutonConnexion(this, "Connexion"));
        panneau.ajouterImage(REPERTOIRE, NOM_IMAGE);

        this.setContentPane(panneau);
    }

}
