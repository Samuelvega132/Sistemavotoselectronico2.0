package ec.edu.pucem.bocadeurna.formulario;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import ec.edu.pucem.bocadeurna.dominio.Candidato;
import ec.edu.pucem.bocadeurna.dominio.Estudiante;
import ec.edu.pucem.bocadeurna.dominio.SistemaVoto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class FrmSufragar extends JInternalFrame {

    private SistemaVoto sistemaVoto;
    private JTextField txtCedula;

    public FrmSufragar(SistemaVoto sistemaVoto) {
        this.sistemaVoto = sistemaVoto;

        setTitle("Sufragio");
        setSize(400, 300);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(2, 2));
        
        JLabel lblCedula = new JLabel("Ingrese su cédula:");
        txtCedula = new JTextField();
        JButton btnBuscar = new JButton("Buscar");

        panel.add(lblCedula);
        panel.add(txtCedula);
        panel.add(btnBuscar);

        add(panel, BorderLayout.NORTH);

        // Acción del botón Buscar
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = txtCedula.getText();
                Optional<Estudiante> estudianteOpt = sistemaVoto.findEstudianteByCedula(cedula);

                if (estudianteOpt.isPresent()) {
                    Estudiante estudiante = estudianteOpt.get();
                    if (estudiante.isEstado()) {
                        JOptionPane.showMessageDialog(FrmSufragar.this, "Este estudiante ya ha votado.");
                    } else {
                        showVotingPanel();
                    }
                } else {
                    JOptionPane.showMessageDialog(FrmSufragar.this, "Estudiante no encontrado.");
                }
            }
        });
    }

    private void showVotingPanel() {
        // Elimina el contenido actual del marco
        getContentPane().removeAll();
        revalidate();
        repaint();
        
        JPanel candidatoPanel = new JPanel(new GridLayout(0, 1));
        ButtonGroup grupoCandidatos = new ButtonGroup();

        // Crear y agregar botones de radio para los candidatos
        for (Candidato candidato : sistemaVoto.getCandidatos()) {
            JRadioButton radioButton = new JRadioButton(candidato.toString());
            radioButton.setActionCommand(String.valueOf(candidato.getId()));
            grupoCandidatos.add(radioButton);
            candidatoPanel.add(radioButton);
        }

        add(candidatoPanel, BorderLayout.CENTER);

        JButton btnVotar = new JButton("Votar");
        btnVotar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = txtCedula.getText();
                String selectedCandidatoId = grupoCandidatos.getSelection().getActionCommand();
                Optional<Candidato> candidatoOpt = sistemaVoto.getCandidatos().stream()
                        .filter(c -> c.getId() == Long.parseLong(selectedCandidatoId))
                        .findFirst();

                if (candidatoOpt.isPresent()) {
                    if (sistemaVoto.registrarVoto(cedula, candidatoOpt.get())) {
                    	mensajeGracias();
                    } else {
                        JOptionPane.showMessageDialog(FrmSufragar.this, "No se pudo registrar el voto. Verifique su cédula o si ya ha votado.");
                    }
                } else {
                    JOptionPane.showMessageDialog(FrmSufragar.this, "Seleccione un candidato.");
                }
            }
        });

        add(btnVotar, BorderLayout.SOUTH);
    }

    private void mensajeGracias() {
        JOptionPane.showMessageDialog(FrmSufragar.this, "Gracias por votar.");
        
        // Volver al panel de verificación de cédula
        getContentPane().removeAll();
        revalidate();
        repaint();
        
        JPanel panel = new JPanel(new GridLayout(2, 2));
        
        JLabel lblCedula = new JLabel("Ingrese su cédula:");
        txtCedula = new JTextField(); // Re-inicializar el campo de texto
        JButton btnBuscar = new JButton("Buscar");

        panel.add(lblCedula);
        panel.add(txtCedula);
        panel.add(btnBuscar);

        add(panel, BorderLayout.NORTH);

        // Acción del botón Buscar
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = txtCedula.getText();
                Optional<Estudiante> estudianteOpt = sistemaVoto.findEstudianteByCedula(cedula);

                if (estudianteOpt.isPresent()) {
                    Estudiante estudiante = estudianteOpt.get();
                    if (estudiante.isEstado()) {
                        JOptionPane.showMessageDialog(FrmSufragar.this, "Este estudiante ya ha votado.");
                    } else {
                        showVotingPanel();
                    }
                } else {
                    JOptionPane.showMessageDialog(FrmSufragar.this, "Estudiante no encontrado.");
                }
            }
        });
    }
}

