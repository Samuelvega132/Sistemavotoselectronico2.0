package ec.edu.pucem.bocadeurna.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SistemaVoto {
    private List<Mesa> mesas;
    private List<Curso> cursos;
    private List<Estudiante> estudiantes;
    private List<Candidato> candidatos;
    private List<Voto> votos;

    public SistemaVoto() {
        mesas = new ArrayList<>();
        cursos = new ArrayList<>();
        estudiantes = new ArrayList<>();
        candidatos = new ArrayList<>();
        votos = new ArrayList<>();
    }

    // Methods to add entities
    public void addMesa(Mesa mesa) { mesas.add(mesa); }
    public void addCurso(Curso curso) { cursos.add(curso); }
    public void addEstudiante(Estudiante estudiante) { estudiantes.add(estudiante); }
    public void addCandidato(Candidato candidato) { candidatos.add(candidato); }
    public void addVoto(Voto voto) { votos.add(voto); }

    // Methods to get lists
    public List<Mesa> getMesas() { return mesas; }
    public List<Curso> getCursos() { return cursos; }
    public List<Estudiante> getEstudiantes() { return estudiantes; }
    public List<Candidato> getCandidatos() { return candidatos; }
    public List<Voto> getVotos() { return votos; }

    // Method to find Estudiante by cedula
    public Optional<Estudiante> findEstudianteByCedula(String cedula) {
        return estudiantes.stream().filter(estudiante -> estudiante.getCedula().equals(cedula)).findFirst();
    }

    // Method to register a vote
    public boolean registrarVoto(String cedula, Candidato candidato) {
        Optional<Estudiante> estudianteOpt = findEstudianteByCedula(cedula);
        if (estudianteOpt.isPresent()) {
            Estudiante estudiante = estudianteOpt.get();
            if (!estudiante.isEstado()) {
                Voto voto = new Voto(votos.size() + 1, estudiante, candidato);
                votos.add(voto);
                estudiante.setEstado(true);  // Mark as voted
                return true;
            }
        }
        return false;
    }
}
