import UI.Themes;
import Vues.Fenetres.FenetreAccueil;
import banque.Banque;

import javax.swing.*;

/**
 * <p>Cette classe est le fichier exécutable qui permet de démarrer le
 * programme principal. Celui-ci contient l’appel de la classe Accueil et du
 * thème Nimbus. De plus, elle initialise l'instance unique de
 * la classe Banque.</p>
 *
 * @author Thiago Ferreira, Louis-Joseph Laforest, Hanz Sami & Isaac D. Zolana
 * @version A2020
 */
public class GuiBanque {
    public static void main(String[] args) {
        Themes.setTheme(Themes.NIMBUS);
        FenetreAccueil accueil = new FenetreAccueil();
        accueil.initialiser();
        SwingUtilities.invokeLater(accueil);
    }
}