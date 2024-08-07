package ec.edu.pucem.bocadeurna.formulario;

import javax.swing.*;
import ec.edu.pucem.bocadeurna.dominio.Candidato;
import ec.edu.pucem.bocadeurna.dominio.SistemaVoto;
import java.awt.*;


public class FrmReportes extends JInternalFrame {

    private SistemaVoto sistemaVoto;

    public FrmReportes(SistemaVoto sistemaVoto) {
        this.sistemaVoto = sistemaVoto;

        setTitle("Reportes");
        setSize(400, 300);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton btnGenerarReporte = new JButton("Generar Reporte");
        btnGenerarReporte.addActionListener(e -> {
            StringBuilder reporte = new StringBuilder();
            for (Candidato candidato : sistemaVoto.getCandidatos()) {
                long votosCount = sistemaVoto.getVotos().stream()
                        .filter(voto -> voto.getCandidato().getId() == candidato.getId())
                        .count();
                reporte.append(candidato.toString()).append(": ").append(votosCount).append(" votos\n");
            }
            textArea.setText(reporte.toString());
        });

        add(scrollPane, BorderLayout.CENTER);
        add(btnGenerarReporte, BorderLayout.SOUTH);
    }
}
