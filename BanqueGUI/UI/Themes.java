package UI;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * Classe qui permet de modifier le thème de l'application selon les
 * "LookAndFeels" installés.
 * 
 * @author Thiago Ferreira, Louis-Joseph Laforest, Hanz Sami & Isaac D. Zolana
 * @version A2020
 */
public class Themes {
	public static final String WINDOWS = "Windows";
	public static final String NIMBUS = "Nimbus";
	
	/**
	 * Permet de modifier le thème de l'application.
	 * 
	 * @param theme Thème désiré.
	 */
	public static void setTheme(String theme) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if (theme.equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
