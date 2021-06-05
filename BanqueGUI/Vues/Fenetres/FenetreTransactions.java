package Vues.Fenetres;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import Vues.BarresMenu.MenuBarAccueil;
import Vues.Panneaux.PanneauBasDroit;
import Vues.Panneaux.PanneauBasGauche;
import Vues.Panneaux.PanneauHautDroit;
import Vues.Panneaux.PanneauHautGauche;
import Vues.Panneaux.PanneauMilieuGauche;
import banque.Banque;
import baseDonnees.modeles.Transaction;

/**
 * <p>Cette classe permet de créer l'interface d'accueil de
 * l'application de transaction bancaire.</p>
 *
 * @author Thiago Ferreira, Louis-Joseph Laforest, Hanz Sami & Isaac D. Zolana
 * @version A20-11-18
 */
public class FenetreTransactions extends JFrame {
    /* Titre de la fenêtre d'accueil de l'application. */
    public static final String TITRE = "Application de transactions bancaires";
    public static final String MSG_SUCESS = "Vous avez bien été authentifié(e)";

    public static final Color COULEUR_FOND = Color.decode("#0F141F");

    private PanneauBasGauche panBasGauche;
    private PanneauHautDroit panHautDroit;
    private PanneauHautGauche panHautGauche;
    private PanneauMilieuGauche panMilieuGauche;
    private PanneauBasDroit panBasDroit;

    /* Les contraintes de position des panneaux dans la fenêtre */
    private GridBagConstraints contraintes;

    /*
     * STRATÉGIE : Premièrement, le constructeur Vues.Fenetres.Accueil permet
     * d'initialiser tous les éléments à leur état inital. Deuxièmement,
     * la méthode initialiser permet d'afficher l'interface sur l'écran de
     * l'usager. Finalement, les fonctions ajouterBarreMenuAccueil et
     * ajouterPanneauPrincipal permettent d'ajouter les éléments
     * essentiel à la fênetre d'accueil.
     */

    /**
     * <p>Constructeur par défaut qui permet d'initialiser les composants de la
     * fenêtre d'accueil.</p>
     */
    public FenetreTransactions() {
        /* Configure les paramètres du frame */
        this.setTitle(TITRE);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(COULEUR_FOND);
        this.setLayout(new GridBagLayout());

        contraintes = new GridBagConstraints();
        contraintes.fill = GridBagConstraints.BOTH;

        //JOptionPane.showMessageDialog(this, MSG_SUCESS);
    }

	/**
	 * <p>
	 * Fonction qui permet d'ajouter les éléments à l'interface, d'afficher
	 * celle-ci et mets à jour le tableau.
	 * </p>
	 */
	public void initialiser() {
		ajouterBarreMenuAccueil();
		ajouterPanneaux();
		mettreAJourTableau();

		this.setVisible(true);
	}
    
	/**
	 * Ajoute les panneaux dans la fenêtre et les dispose correctement.
	 */
	private void ajouterPanneaux() {
		initialiserPanneaux();
		positionnerPanneaux();
	}

	/**
	 * Instancie les panneaux qui seront ajoutés à la fenêtre.
	 */
	private void initialiserPanneaux() {
		panHautDroit = new PanneauHautDroit();
        panHautGauche = new PanneauHautGauche();
        panMilieuGauche = new PanneauMilieuGauche(this);
        panBasGauche = new PanneauBasGauche();
        panBasDroit = new PanneauBasDroit();
	}

	/**
	 * Positionne les panneaux à leur emplacement respectif.
	 */
	private void positionnerPanneaux() {
		positionnerHautDroit(panHautDroit);
        positionnerHautGauche(panHautGauche);
        positionnerMilieuGauche(panMilieuGauche);
        positionnerBasGauche(panBasGauche);
        positionnerBasDroit(panBasDroit);
	}

	/**
     * Procédure qui permet de positionner un panneau dans le haut gauche.
     * 
     * @param panneau Panneau à ajouter à la position haut gauche.
     */
    private void positionnerHautGauche(JPanel panneau) {
        setContraintes(0, 0, 1, 1, 1, 1);
        this.add(panneau, contraintes);
    }

    /**
     * Procédure qui permet de positionner un panneau dans le milieu gauche.
     * 
     * @param panneau Panneau à ajouter à la position milieu gauche.
     */
    private void positionnerMilieuGauche(JPanel panneau) {
        setContraintes(0, 2, 1, 1, 1, 1);
        this.add(panneau, contraintes);
    }

    private void positionnerBasGauche(JPanel panneau) {
        setContraintes(0, 3, 1, 1, 1, 1);
        this.add(panneau, contraintes);
    }

    /**
     * Procédure qui permet de positionner un panneau dans le haut droit.
     * 
     * @param panneau Panneau à ajouter à la position haut droit.
     */
    private void positionnerHautDroit(JPanel panneau) {
        setContraintes(1, 0, 3, 1, 3, 1);
        this.add(panneau, contraintes);
    }

    /**
     * Procédure qui permet de positionner un panneau dans le bas droit. 
     * 
     * @param panneau Panneau à ajouter à la position bas droit.
     */
    private void positionnerBasDroit(JPanel panneau) {
        setContraintes(1, 2, 3, 2, 3, 2);
        this.add(panneau, contraintes);
    }

    /**
     * Définis les contraintes de positionnement des élements dans la grille.
     *
     * @param x
     * @param y
     * @param weigthX
     * @param weightY
     * @param width
     * @param height
     */
    private void setContraintes(int x, int y, int weigthX, int weightY, int width, int height) {
        contraintes.gridx = x;
        contraintes.gridy = y;
        contraintes.weightx = weigthX;
        contraintes.weighty = weightY;
        contraintes.gridwidth = width;
        contraintes.gridheight = height;
    }

    /**
     * <p>Ajoute une barre de menu à la fenêtre d'accueil.</p>
     */
    private void ajouterBarreMenuAccueil() {
        this.setJMenuBar(new MenuBarAccueil(this));
    }

	/**
	 * Retourne les données de transaction de l'utilisateur actif sous la forme
	 * d'un modèle de tableau.
	 * 
	 * @return le modele du tableau à faire afficher.
	 */
    private DefaultTableModel getDonneesTransactions() {
        Banque banque = Banque.getInstance();
        
        DefaultTableModel modeleTableau = new DefaultTableModel();
        
        modeleTableau.addColumn("Source");
        modeleTableau.addColumn("Destination");
        modeleTableau.addColumn("Montant");
        modeleTableau.addColumn("Status");
        
        Object[] data = new Object[4];
        for (Transaction transaction : banque.obtenirTransactionsPourCompte()) {
        	String noCompte = transaction.getNoCompteSource();
            String destination = transaction.getNoCompteDestination();
            String montant = "" + transaction.getMontant();
            String statut = transaction.getStatut();
            
            data[0] = noCompte;
            data[1] = destination;
            data[2] = montant;
            data[3] = statut;
            
            modeleTableau.addRow(data);
        }
        
        return modeleTableau;
    }
    
	/**
	 * Procédure qui mets à jour la fenêtre avec les informations qui n'étaient
	 * pas précédemment affichées.
	 */
    public void mettreAJourFenetre() {
    	mettreAJourTableau();
    	mettreAJoutPanneauHautDroit();
    	//mettreAJourPanneau(this.panHautDroit);
    }

	/**
     * Mets à jour le tableau de la fenêtre avec de nouvelles informations.
     */
	private void mettreAJourTableau() {
		this.panBasDroit.initialiserDonnees(getDonneesTransactions());
	}

	private void mettreAJoutPanneauHautDroit() {
		this.panHautDroit.mettreAJourPanneau();
	}
	
	
	private void mettreAJourPanneau(JPanel panneau) {
		panneau.validate();
	}
}
