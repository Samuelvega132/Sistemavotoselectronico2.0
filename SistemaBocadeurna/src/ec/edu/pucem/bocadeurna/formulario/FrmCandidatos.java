package ec.edu.pucem.bocadeurna.formulario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import ec.edu.pucem.bocadeurna.dominio.Candidato;
import ec.edu.pucem.bocadeurna.dominio.Estudiante;
import ec.edu.pucem.bocadeurna.dominio.Persona;
import ec.edu.pucem.bocadeurna.dominio.SistemaVoto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    private JTable table;
    private DefaultTableModel model;
    private long idGenerator; // Generador de IDs
    private final Lock lock = new ReentrantLock(); // Objeto de bloqueo para sincronización

    public FrmCandidatos(SistemaVoto sistemaVoto) {
        this.sistemaVoto = sistemaVoto;
        idGenerator = 1; // Inicia con 1
        FrmCrearCandidato();
    }

    public void FrmCrearCandidato() {
        setTitle("Gestion de Candidatos");
        setBounds(100, 100, 408, 464);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(38, 22, 74, 26);
        txtId = new JTextField();
        txtId.setBounds(119, 23, 160, 26);
        txtId.setEditable(false); // El campo de ID no es editable
        txtId.setText(String.format("%03d", idGenerator)); // Inicializar con el valor actual de idGenerator

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(38, 58, 75, 30);
        txtNombre = new JTextField();
        txtNombre.setBounds(119, 59, 160, 30);

        JLabel lblFoto = new JLabel("Foto:");
        lblFoto.setBounds(38, 98, 75, 30);
        txtFoto = new JTextField();
        txtFoto.setBounds(119, 99, 160, 30);

        JLabel lblPartido = new JLabel("Partido:");
        lblPartido.setBounds(38, 138, 75, 30);
        txtPartido = new JTextField();
        txtPartido.setBounds(119, 139, 160, 30);

        getContentPane().setLayout(null);

        getContentPane().add(lblId);
        getContentPane().add(txtId);
        getContentPane().add(lblNombre);
        getContentPane().add(txtNombre);
        getContentPane().add(lblFoto);
        getContentPane().add(txtFoto);
        getContentPane().add(lblPartido);
        getContentPane().add(txtPartido);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(234, 189, 120, 30);
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    long id = idGenerator; // Obtener el ID actual
                    String nombre = txtNombre.getText();
                    String foto = txtFoto.getText();
                    String partido = txtPartido.getText();

                    Candidato candidato = new Candidato(id, nombre, foto, partido);
                    sistemaVoto.addCandidato(candidato);
                    agregarFila(candidato); // Actualizar la tabla

                    JOptionPane.showMessageDialog(FrmCandidatos.this, "Candidato guardado exitosamente.");

                    txtNombre.setText("");
                    txtFoto.setText("");
                    txtPartido.setText("");

                    updateId(); // Actualizar el ID después de guardar

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(FrmCandidatos.this, "ID debe ser un número.");
                }
            }
        });

        JButton btnNewButton = new JButton("Buscar Estudiante");
        btnNewButton.setBounds(35, 189, 119, 30);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarEstudiante();
            }
        });
        getContentPane().add(btnNewButton);
        getContentPane().add(btnGuardar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(38, 236, 316, 163);
        getContentPane().add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "ID", "Nombre", "Foto", "Partido"
            }
        ));
        scrollPane.setViewportView(table);
        model = (DefaultTableModel) table.getModel();
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
            // txtCedula.setText(estudianteSeleccionado.getCedula());
        }
    }

    private void agregarFila(Candidato candidato) {
        Object[] fila = new Object[4];
        fila[0] = candidato.getId();
        fila[1] = candidato.getNombre();
        fila[2] = candidato.getFoto();
        fila[3] = candidato.getNombrePartido();
        model.addRow(fila);
    }

    // Método para actualizar el ID
    private void updateId() {
        lock.lock(); // Adquirir el bloqueo
        try {
            idGenerator++; // Incrementar el ID solo después de guardar
            txtId.setText(String.format("%03d", idGenerator)); // Actualizar el campo de texto
        } finally {
            lock.unlock(); // Liberar el bloqueo
        }
    }

    // Este método no necesita más ser usado fuera del contexto de updateId()
    public long getNextId() {
        lock.lock(); // Adquirir el bloqueo
        try {
            return idGenerator; // Retornar el ID actual sin incrementarlo
        } finally {
            lock.unlock(); // Liberar el bloqueo
        }
    }
}
