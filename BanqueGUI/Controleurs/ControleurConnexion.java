package Controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vues.Dialogues.DialogAuth;

import javax.swing.*;

/**
 * <p>Ce contrôleur permet de gérer les événements qui surviennent sur le
 * bouton de connexion à l'écran principal grâce à l'implémentation de
 * l'interface ActionListener et ses méthodes.</p>
 *
 * @author Thiago Ferreira, Louis-Joseph Laforest, Hanz Sami & Isaac D. Zolana
 * @version A2020
 */

public class ControleurConnexion implements ActionListener {
    JFrame fenetre; //Instance de la fenêtre principale

    /**
     * <p>Constructeur qui permet de recevoir une instance JFrame en paramètre
     * qui sera transféré à un autre objet ultérieurement.</p>
     *
     * @param fenetre Instance de la fenêtre principale
     */
    public ControleurConnexion(JFrame fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Création d'une nouvelle boîte de dialogue d'authentification.
        DialogAuth auth = new DialogAuth(fenetre, "Authentification");
    }
}