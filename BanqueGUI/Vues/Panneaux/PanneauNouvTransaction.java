package Vues.Panneaux;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Vues.Boutons.BoutonAnnuler;
import Vues.Boutons.BoutonConfirmer;
import Vues.Dialogues.DialogTransaction;
import Vues.Fenetres.FenetreTransactions;

/**
 * <p></p>
 *
 * @author Thiago Ferreira, Louis-Joseph Laforest, Hanz Sami & Isaac D. Zolana
 * @version A2020
 */
public class PanneauNouvTransaction extends JPanel {

	private JDialog dialogueTransaction;
	private JLabel lblDestinataire, lblMontant;
	private JTextField txtFldDestinataire, txtFldMontant;

	private FenetreTransactions fenetre;

	/**
	 *
	 * @param fenetre
	 * @param dialogueTransaction
	 */
	public PanneauNouvTransaction(FenetreTransactions fenetre,
			DialogTransaction dialogueTransaction) {
		this.fenetre = fenetre;

		this.dialogueTransaction = dialogueTransaction;
		initComposants();
	}

	/**
	 *
	 */
	private void initComposants() {

		lblDestinataire = new JLabel(
				"<html>Compte destination:" + "<br/>(00-150-00X)<html>");
		lblMontant = new JLabel("Montant à transferer:");
		txtFldDestinataire = new JTextField("00-150-00", 15);
		txtFldMontant = new JTextField(15);

		this.add(lblDestinataire);
		this.add(txtFldDestinataire);
		this.add(lblMontant);
		this.add(txtFldMontant);

		this.add(new BoutonAnnuler(dialogueTransaction));
		this.add(new BoutonConfirmer(fenetre, txtFldDestinataire, txtFldMontant,
				dialogueTransaction));
	}
}
