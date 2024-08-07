package ec.edu.pucem.bocadeurna.formulario;

import javax.swing.*;
import ec.edu.pucem.bocadeurna.dominio.Curso;
import ec.edu.pucem.bocadeurna.dominio.Estudiante;
import ec.edu.pucem.bocadeurna.dominio.SistemaVoto;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FrmEstudiantes extends JInternalFrame {

    private SistemaVoto sistemaVoto;
    private long idGenerator; // Generador de IDs
    private final Lock lock = new ReentrantLock(); // Objeto de bloqueo para sincronización
    private JTextField txtNombre;


    public FrmEstudiantes(SistemaVoto sistemaVoto) {
        this.sistemaVoto = sistemaVoto;
        idGenerator = 001; // Inicia con 1

        setTitle("Gestión de Estudiantes");
        setSize(453, 278);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 437, 212);

        JLabel lblCedula = new JLabel("Cédula:");
        lblCedula.setBounds(44, 72, 119, 49);
        JTextField txtCedula = new JTextField();
        txtCedula.setBounds(173, 72, 243, 49);
        JLabel lblCurso = new JLabel("Curso:");
        lblCurso.setBounds(44, 132, 119, 49);
        JComboBox<Curso> cmbCurso = new JComboBox<>();
        cmbCurso.setBounds(173, 132, 243, 49);

        for (Curso curso : sistemaVoto.getCursos()) {
            cmbCurso.addItem(curso);
        }
        panel.setLayout(null);

        panel.add(lblCedula);
        panel.add(txtCedula);
        panel.add(lblCurso);
        panel.add(cmbCurso);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setForeground(Color.GRAY);
        btnGuardar.setBackground(new Color(255, 255, 255));
        btnGuardar.setBounds(0, 211, 437, 37);
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = txtCedula.getText();
                String nombre = txtNombre.getText();
                Curso curso = (Curso) cmbCurso.getSelectedItem();

                if (curso != null) {
                    long id = getNextId(); // Generar nuevo ID
                    Estudiante estudiante = new Estudiante(id, nombre, cedula, false, curso);
                    sistemaVoto.addEstudiante(estudiante);

                    JOptionPane.showMessageDialog(FrmEstudiantes.this, "Estudiante guardado exitosamente.");

                    txtNombre.setText("");
                    txtCedula.setText("");
                    cmbCurso.setSelectedIndex(0);
                } else {
                    JOptionPane.showMessageDialog(FrmEstudiantes.this, "Seleccione un curso.");
                }
            }
        });
        getContentPane().setLayout(null);

        getContentPane().add(panel);
        
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(44, 11, 119, 49);
        panel.add(lblNombre);
        
        txtNombre = new JTextField();
        txtNombre.setBounds(173, 11, 243, 49);
        panel.add(txtNombre);
        getContentPane().add(btnGuardar);
    }
    
    public long getNextId() {
        lock.lock(); // Adquirir el bloqueo
        try {
            return idGenerator++; // Incrementar y devolver el ID
        } finally {
            lock.unlock(); // Liberar el bloqueo
        }
    }
}
