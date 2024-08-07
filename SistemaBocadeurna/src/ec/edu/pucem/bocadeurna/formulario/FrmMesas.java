package ec.edu.pucem.bocadeurna.formulario;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import ec.edu.pucem.bocadeurna.dominio.Mesa;
import ec.edu.pucem.bocadeurna.dominio.SistemaVoto;
import java.awt.event.ActionListener;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class FrmMesas extends JInternalFrame {

    private SistemaVoto sistemaVoto;
    private long idGenerator; // Generador de IDs
    private final Lock lock = new ReentrantLock(); // Objeto de bloqueo para sincronización

    public FrmMesas(SistemaVoto sistemaVoto) {
        this.sistemaVoto = sistemaVoto;
        idGenerator = 001; // Inicia con 1

        setTitle("Gestión de Mesas");
        setSize(400, 300);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(4, 2));
        
        JLabel lblId = new JLabel("ID:");
        JTextField txtId = new JTextField();
        txtId.setEditable(false); // El campo de ID no es editable
        txtId.setText(String.valueOf("00" + getNextId()));
        
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();
        JLabel lblPresidente = new JLabel("Presidente:");
        JTextField txtPresidente = new JTextField();
        JLabel lblSecretario = new JLabel("Secretario:");
        JTextField txtSecretario = new JTextField();
        
        panel.add(lblId);
        panel.add(txtId);
        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblPresidente);
        panel.add(txtPresidente);
        panel.add(lblSecretario);
        panel.add(txtSecretario);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                	// Obtener ID del campo, ya que el ID es generado automáticamente, solo se muestra
                    long id = Long.parseLong(txtId.getText());
                    String nombre = txtNombre.getText();
                    String presidente = txtPresidente.getText();
                    String secretario = txtSecretario.getText();
                    
                    Mesa mesa = new Mesa(id, nombre, presidente, secretario);
                    sistemaVoto.addMesa(mesa);
                    
                    JOptionPane.showMessageDialog(FrmMesas.this, "Mesa guardada exitosamente.");
                    
                    txtId.setText(String.valueOf("00" +getNextId())); // Actualizar con el siguiente ID                    
                    txtNombre.setText("");
                    txtPresidente.setText("");
                    txtSecretario.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(FrmMesas.this, "ID debe ser un número.");
                }
            }
        });

        add(panel, BorderLayout.CENTER);
        add(btnGuardar, BorderLayout.SOUTH);
        
        
    }
    
 // Método para generar un nuevo ID con sincronización
    public long getNextId() {
        lock.lock(); // Adquirir el bloqueo
        try {
            return idGenerator++; // Incrementar y devolver el ID
        } finally {
            lock.unlock(); // Liberar el bloqueo
        }
    }
}
