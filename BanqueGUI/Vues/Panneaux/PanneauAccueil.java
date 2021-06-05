package Vues.Panneaux;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * PanneauAccueil est un panneau qui apparaît à l'exécution de l'application 
 * dans la fenêtre d'accueil. Il présente le bouton de connexion et 
 * l'image au centre.
 *
 * @author Thiago Ferreira, Louis-Joseph Laforest, Hanz Sami & Isaac D. Zolana
 * @version A2020
 */
public class PanneauAccueil extends JPanel {
    /* Couleur d'arrière-plan du panneau d'accueil. */
    public static final Color COULEUR_FOND = Color.decode("#E73137");
    /* Dimensions de l'image dans le panneau d'accueil. */
    public static final int LONGUEUR_IMAGE = 350;
    public static final int HAUTEUR_IMAGE = 250;


    /**
     * Constructeur par défaut du panneau. Il initialise ses composants.
     */
    public PanneauAccueil() {
        this.setBorder(BorderFactory.
				createEmptyBorder(20,20,20,20));
		this.setBackground(COULEUR_FOND);
    }

    /**
     * Ajoute une image au panneau principal.
     * 
     * @param repertoire	Repertoire dans lequel l'image se trouve.
     * @param nomImage		Le nom de l'image avec son extension
     */
    public void ajouterImage(String repertoire, String nomImage) {
        try {
            BufferedImage bfETS;
            ImageIcon imgETS;
            JLabel logoETS;

            String chemin = repertoire + nomImage;

            bfETS = ImageIO.read(this.getClass().getResource(chemin));
            imgETS = new ImageIcon(bfETS);
            Image image = imgETS.getImage();
            Image newimg = image.getScaledInstance(LONGUEUR_IMAGE,
                    HAUTEUR_IMAGE, Image.SCALE_SMOOTH);
            imgETS = new ImageIcon(newimg);

            logoETS = new JLabel(imgETS);

            this.add(logoETS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ajoute un bouton au panneau d'accueil.
     * 
     * @param bouton Le JButton qu'on veut ajouter au Panneau
     */
    public void ajouterBouton(JButton bouton) {
        this.add(bouton);
    }
}
