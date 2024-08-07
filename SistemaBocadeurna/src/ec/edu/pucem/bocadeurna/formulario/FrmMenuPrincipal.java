package ec.edu.pucem.bocadeurna.formulario;


import ec.edu.pucem.bocadeurna.dominio.SistemaVoto;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;



public class FrmMenuPrincipal extends JFrame {
	private SistemaVoto sistemaVoto;
    private JDesktopPane desktopPane;
    private FrmMesas frmMesas;
    private FrmCandidatos frmCandidatos;
    private FrmCursos frmCursos;
    private FrmEstudiantes frmEstudiantes;
    private FrmSufragar frmSufragar;
    private FrmReportes frmReportes;
    private FrmListaEstudiantes frmListaEstudiantes;
    private FrmPadronElectoral frmPadronElectoral;

    

    public FrmMenuPrincipal() {
    	sistemaVoto = new SistemaVoto();
        desktopPane = new JDesktopPane();

        setTitle("Sistema de Votación");
        setSize(931, 775);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(desktopPane);
        
        JMenuBar menuBar = new JMenuBar();
        
        JMenu mOpciones = new JMenu("Opciones");

        /*miReportes.addActionListener(e -> {
            FrmReportes frmReportes = new FrmReportes(sistemaVoto);
            desktopPane.add(frmReportes);
            frmReportes.setVisible(true);
        });*/


        menuBar.add(mOpciones);
        
        JMenuItem mntmSalir = new JMenuItem("Salir");
        mntmSalir.setIcon(new ImageIcon(FrmMenuPrincipal.class.getResource("/ec/edu/pucem/bocadeurna/imagen/exit.png")));
        mntmSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mntmSalir.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        mOpciones.add(mntmSalir);
        
        setJMenuBar(menuBar);
        
        JMenu mnNewMenu = new JMenu("Administracion");
        menuBar.add(mnNewMenu);
        
        JMenuItem miMesas = new JMenuItem("Mesas");
        miMesas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (!FrameAbierto("Gestión de Mesas")) {
        			FrmMesas frmMesas = new FrmMesas(sistemaVoto);
                    desktopPane.add(frmMesas);
                    frmMesas.setVisible(true);
                }        		
        	}
        });
        mnNewMenu.add(miMesas);
        
        JMenuItem miCursos = new JMenuItem("Cursos");
        miCursos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (!FrameAbierto("Gestión de Cursos")) {
        			FrmCursos frmCursos = new FrmCursos(sistemaVoto);
                    desktopPane.add(frmCursos);
                    frmCursos.setVisible(true);
                }        		
        	}
        });
        mnNewMenu.add(miCursos);
        
        JMenuItem miEstudiantes = new JMenuItem("Estudiantes");
        miEstudiantes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (!FrameAbierto("Gestión de Estudiantes")) {
        			FrmEstudiantes frmEstudiantes = new FrmEstudiantes(sistemaVoto);
                    desktopPane.add(frmEstudiantes);
                    frmEstudiantes.setVisible(true);
                }        		
        	}
        });
        mnNewMenu.add(miEstudiantes);
        
        JMenuItem miCandidatos = new JMenuItem("Candidatos");
        miCandidatos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (!FrameAbierto("Gestión de Candidatos")) {
        			FrmCandidatos frmCandidatos = new FrmCandidatos(sistemaVoto);
                    desktopPane.add(frmCandidatos);
                    frmCandidatos.setVisible(true);
                }        		
        	}
        });
        mnNewMenu.add(miCandidatos);
        
        JMenu mnNewMenu_1 = new JMenu("Proceso");
        menuBar.add(mnNewMenu_1);
        
        JMenuItem miSufragar = new JMenuItem("Sufragar");
        miSufragar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (!FrameAbierto("Sufragio")) {
        			FrmSufragar frmSufragar = new FrmSufragar(sistemaVoto);
                    desktopPane.add(frmSufragar);
                    frmSufragar.setVisible(true);
                }        		
        	}
        });
        mnNewMenu_1.add(miSufragar);
        
        JMenu mnNewMenu_2 = new JMenu("Reportes");
        menuBar.add(mnNewMenu_2);
        
        JMenuItem mnPadron = new JMenuItem("Padron Electoral");
        mnPadron.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (!FrameAbierto("Padron Electoral")) {
        			FrmPadronElectoral frmPadronElectoral = new FrmPadronElectoral(sistemaVoto);
                    desktopPane.add(frmPadronElectoral);
                    frmPadronElectoral.setVisible(true);
                }
        	}
        });
        mnNewMenu_2.add(mnPadron);
        
        JMenuItem mnReporte = new JMenuItem("Reporte");
        mnReporte.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	
        		if (!FrameAbierto("Reportes")) {
    			FrmReportes frmReportes = new FrmReportes(sistemaVoto);
                desktopPane.add(frmReportes);
                frmReportes.setVisible(true);
            } 
        		}
        	
        });
        
        JMenuItem mnBarras = new JMenuItem("Resultados en Barras");
        mnBarras.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        	}
        });
        mnNewMenu_2.add(mnBarras);
        mnNewMenu_2.add(mnReporte);
        
        JMenuItem mntmNewMenuItem = new JMenuItem("Lista Estudiantes");
        mntmNewMenuItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (!FrameAbierto("Lista de Estudiantes")) {
        			FrmListaEstudiantes frmListaEstudiantes = new FrmListaEstudiantes(sistemaVoto);
                    desktopPane.add(frmListaEstudiantes);
                    frmListaEstudiantes.setVisible(true);
                } 
        	}
        });
        mnNewMenu_2.add(mntmNewMenuItem);
        
    }
    
    private boolean FrameAbierto(String frameName) {
        for (JInternalFrame frame : desktopPane.getAllFrames()) {
            if (frame.getTitle().equals(frameName)) {
                return true;
            }
        }
        return false;
    }
   
}
    