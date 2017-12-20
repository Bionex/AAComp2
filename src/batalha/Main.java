package batalha;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import telas.TelaInicial;

public class Main {

	public static void main(String[] args) {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				TelaInicial telaInicial = new TelaInicial();
				telaInicial.setVisible(true);
			}
		});	

	}

}
