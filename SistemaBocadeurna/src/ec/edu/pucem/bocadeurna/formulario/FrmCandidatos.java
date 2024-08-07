package ec.edu.pucem.bocadeurna.formulario;

import javax.swing.*;

import ec.edu.pucem.bocadeurna.dominio.Candidato;
import ec.edu.pucem.bocadeurna.dominio.Estudiante;
import ec.edu.pucem.bocadeurna.dominio.Persona;
import ec.edu.pucem.bocadeurna.dominio.SistemaVoto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class FrmCandidatos extends JInternalFrame {

    private SistemaVoto sistemaVoto;
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtFoto;
    private JTextField txtPartido;
    private long idGenerator; // Generador de IDs
    private final Lock lock = new ReentrantLock(); // Objeto de bloqueo para sincronización


    public FrmCandidatos(SistemaVoto sistemaVoto) {
        this.sistemaVoto = sistemaVoto;
        idGenerator = 001; // Inicia con 1

        setTitle("Gestión de Candidatos");
        setSize(398, 360);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        getContentPane().setLayout(new BorderLayout());
        
        

        JPanel panel = new JPanel();

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(0, 1, 192, 61);
        txtId = new JTextField();
        txtId.setBounds(192, 1, 192, 61);
        txtId.setEditable(false); // El campo de ID no es editable
        txtId.setText(String.valueOf("00" +getNextId()));
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(0, 114, 192, 61);
        txtNombre = new JTextField();
        txtNombre.setBounds(192, 114, 192, 61);
        JLabel lblFoto = new JLabel("Foto:");
        lblFoto.setBounds(0, 174, 192, 61);
        txtFoto = new JTextField();
        txtFoto.setBounds(192, 174, 192, 61);
        JLabel lblPartido = new JLabel("Partido:");
        lblPartido.setBounds(0, 235, 192, 61);
        txtPartido = new JTextField();
        txtPartido.setBounds(192, 235, 192, 61);
        panel.setLayout(null);

        panel.add(lblId);
        panel.add(txtId);
        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblFoto);
        panel.add(txtFoto);
        panel.add(lblPartido);
        panel.add(txtPartido);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
        	
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    long id = getNextId(); // Generar nuevo ID
                    String nombre = txtNombre.getText();
                    String foto = txtFoto.getText();
                    String partido = txtPartido.getText();

                    Candidato candidato = new Candidato(id, nombre, foto, partido);
                    sistemaVoto.addCandidato(candidato);

                    JOptionPane.showMessageDialog(FrmCandidatos.this, "Candidato guardado exitosamente.");

                    txtId.setText(String.valueOf("00" +getNextId()));
                    txtNombre.setText("");
                    txtFoto.setText("");
                    txtPartido.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(FrmCandidatos.this, "ID debe ser un número.");
                }
                
            }
            
        });
        getContentPane().add(panel, BorderLayout.CENTER);
        
        JButton btnNewButton = new JButton("Buscar Estudiante");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		buscarEstudiante();
        	}
        });
        btnNewButton.setBounds(192, 73, 119, 42);
        panel.add(btnNewButton);
        getContentPane().add(btnGuardar, BorderLayout.SOUTH);
    }
    
    private void buscarEstudiante() {
        FrmPadronElectoral listaEstudiantes = new FrmPadronElectoral(sistemaVoto);
        JDialog dialog = new JDialog();
        dialog.setContentPane(listaEstudiantes.getContentPane());
        dialog.setSize(listaEstudiantes.getSize());
        dialog.setModal(true);
        dialog.setVisible(true);

        Estudiante estudianteSeleccionado = listaEstudiantes.getEstudianteSeleccionado();
        if (estudianteSeleccionado != null) {
            txtNombre.setText(estudianteSeleccionado.getNombre());
            /*txtCedula.setText(estudianteSeleccionado.getCedula());*/

        }
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
