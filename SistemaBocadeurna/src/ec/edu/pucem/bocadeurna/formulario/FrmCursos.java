package ec.edu.pucem.bocadeurna.formulario;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import ec.edu.pucem.bocadeurna.dominio.Curso;
import ec.edu.pucem.bocadeurna.dominio.Mesa;
import ec.edu.pucem.bocadeurna.dominio.SistemaVoto;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FrmCursos extends JInternalFrame {

    private SistemaVoto sistemaVoto;
    private long idGenerator; // Generador de IDs
    private final Lock lock = new ReentrantLock(); // Objeto de bloqueo para sincronización


    public FrmCursos(SistemaVoto sistemaVoto) {
        this.sistemaVoto = sistemaVoto;
        idGenerator = 001; // Inicia con 1

        setTitle("Gestión de Cursos");
        setSize(400, 300);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel lblId = new JLabel("ID:");
        JTextField txtId = new JTextField();
        txtId.setEditable(false); // El campo de ID no es editable
        txtId.setText(String.valueOf("00" +getNextId())); // Establece el ID automáticamente

        JLabel lblNombre = new JLabel("Curso:");
        JTextField txtNombre = new JTextField();

        JLabel lblMesa = new JLabel("Mesa:");
        JComboBox<Mesa> cmbMesa = new JComboBox<>();
        
        // Rellenar el combo box con mesas
        for (Mesa mesa : sistemaVoto.getMesas()) {
            cmbMesa.addItem(mesa);
        }

        panel.add(lblId);
        panel.add(txtId);
        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblMesa);
        panel.add(cmbMesa);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener ID del campo, ya que el ID es generado automáticamente, solo se muestra
                    long id = Long.parseLong(txtId.getText());
                    String nombre = txtNombre.getText();
                    Mesa mesa = (Mesa) cmbMesa.getSelectedItem();
                    
                    if (mesa != null) {
                        Curso curso = new Curso(id, nombre, mesa);
                        sistemaVoto.addCurso(curso);
                        
                        JOptionPane.showMessageDialog(FrmCursos.this, "Curso guardado exitosamente.");
                        
                        // Preparar el formulario para un nuevo curso
                        txtId.setText(String.valueOf("00" +getNextId())); // Actualizar con el siguiente ID
                        txtNombre.setText("");
                        cmbMesa.setSelectedIndex(0);
                    } else {
                        JOptionPane.showMessageDialog(FrmCursos.this, "Seleccione una mesa.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(FrmCursos.this, "ID debe ser un número.");
                }
            }
        });

        add(panel, BorderLayout.CENTER);
        add(btnGuardar, BorderLayout.SOUTH);
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
