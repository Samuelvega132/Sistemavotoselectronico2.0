package ec.edu.pucem.bocadeurna;

import javax.swing.SwingUtilities;

import ec.edu.pucem.bocadeurna.formulario.FrmMenuPrincipal;

public class MainApp {

	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FrmMenuPrincipal app = new FrmMenuPrincipal();
            app.setVisible(true);
        });
    }
}
