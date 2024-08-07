package ec.edu.pucem.bocadeurna.dominio;

public class Voto {
    private long id;
    private Estudiante estudiante;
    private Candidato candidato;

    public Voto(long id, Estudiante estudiante, Candidato candidato) {
        this.id = id;
        this.estudiante = estudiante;
        this.candidato = candidato;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

   
}
