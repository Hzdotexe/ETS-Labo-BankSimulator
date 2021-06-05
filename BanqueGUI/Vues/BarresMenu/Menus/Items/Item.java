package Vues.BarresMenu.Menus.Items;

import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * <p>Classe générale qui permet la création d'un item pour un JMenu.</p>
 *
 * @author Thiago Ferreira, Louis-Joseph Laforest, Hanz Sami & Isaac D. Zolana
 * @version A2020
 */

public class Item extends JMenuItem {

	/**
	 * <p>Constructeur principal de la classe Item qui permet de
	 * créer un Item pour un menu de type JMenu.</p>
	 *
	 * @param titre Le titre de l'option
	 * @param touche La touche raccourcie qui permet d'exécuter la tâche.
	 * @param ecouteur L'ActionListener qui définit la tâche à être exécuté.
	 */
	public Item (String titre, char touche, ActionListener ecouteur) {
		this.setText(titre);
		this.setAccelerator(
				KeyStroke.getKeyStroke(touche, InputEvent.CTRL_DOWN_MASK));
		this.addActionListener(ecouteur);
	}
}