package ec.edu.pucem.bocadeurna.formulario;

import javax.swing.JInternalFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import ec.edu.pucem.bocadeurna.dominio.Estudiante;
import ec.edu.pucem.bocadeurna.dominio.SistemaVoto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class FrmListaEstudiantes extends JInternalFrame {
    private JTable table;
    private SistemaVoto sistemaVoto;
    private DefaultTableModel model;
    private Estudiante estudianteSeleccionado; // Campo para almacenar el estudiante seleccionado

    public FrmListaEstudiantes(SistemaVoto sistemaVoto) {
        getContentPane().setBackground(new Color(153, 193, 241));
        this.sistemaVoto = sistemaVoto;
        setTitle("Lista de Estudiantes");
        setBounds(100, 100, 600, 500);
        getContentPane().setLayout(null);

        JButton btnCancelar = new JButton("Salir");
        btnCancelar.setBounds(453, 425, 117, 25);
        btnCancelar.setBackground(new Color(246, 97, 81));
        getContentPane().add(btnCancelar);

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(23, 11, 547, 400);
        getContentPane().add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "Cédula", "Nombre", "Estado", "Curso" }
        ));
        scrollPane.setViewportView(table);
        model = (DefaultTableModel) table.getModel();
        cargarEstudiantes();

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    estudianteSeleccionado = sistemaVoto.getEstudiantes().get(row);
                }
            }
        });
    }

    private void cargarEstudiantes() {
        model.setRowCount(0); 
        List<Estudiante> listaEstudiantes = sistemaVoto.getEstudiantes();
        for (Estudiante estudiante : listaEstudiantes) {
            agregarFila(estudiante);
        }
    }

    private void agregarFila(Estudiante estudiante) {
        Object[] fila = new Object[4];
        fila[0] = estudiante.getCedula();
        fila[1] = estudiante.getNombre();
        fila[2] = estudiante.isEstado() ? "Votó" : "No votó";
        fila[3] = estudiante.getCurso() != null ? estudiante.getCurso().toString() : "No asignado";
        model.addRow(fila);
    }

    // Método para obtener el estudiante seleccionado
    public Estudiante getEstudianteSeleccionado() {
        return estudianteSeleccionado;
    }
}
