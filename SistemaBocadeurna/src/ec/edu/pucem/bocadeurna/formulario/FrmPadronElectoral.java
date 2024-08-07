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

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class FrmPadronElectoral extends JInternalFrame {
    private JTable table;
    private SistemaVoto sistemaVoto;
    private DefaultTableModel model;
    private Estudiante estudianteSeleccionado; // Campo para almacenar el estudiante seleccionado

    public FrmPadronElectoral(SistemaVoto sistemaVoto) {
        getContentPane().setBackground(new Color(153, 193, 241));
        this.sistemaVoto = sistemaVoto;
        setTitle("Padron Electoral");
        setBounds(100, 100, 800, 600);
        getContentPane().setLayout(null);

        JButton btnCancelar = new JButton("Salir");
        btnCancelar.setBounds(653, 525, 117, 25);
        btnCancelar.setBackground(new Color(246, 97, 81));
        getContentPane().add(btnCancelar);

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(23, 11, 747, 500);
        getContentPane().add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "Cédula", "Nombre", "Estado", "Curso", "Mesa" }
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
        Object[] fila = new Object[5];
        fila[0] = estudiante.getCedula();
        fila[1] = estudiante.getNombre();
        fila[2] = estudiante.isEstado() ? "Votó" : "No votó";
        fila[3] = estudiante.getCurso() != null ? estudiante.getCurso().toString() : "No asignado";
        fila[4] = obtenerMesaEstudiante(estudiante);
        model.addRow(fila);
    }

    private String obtenerMesaEstudiante(Estudiante estudiante) {
        if (estudiante.getCurso() != null) {
            // Asumimos que el curso tiene un método para obtener la mesa asignada
            return estudiante.getCurso().getMesa().toString();
        }
        return "No asignada";
    }

    // Método para obtener el estudiante seleccionado
    public Estudiante getEstudianteSeleccionado() {
        return estudianteSeleccionado;
    }
}
