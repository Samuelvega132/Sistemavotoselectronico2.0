package ec.edu.pucem.bocadeurna.dominio;

public class Estudiante extends Persona {
    private String cedula;
    private boolean estado;
    private Curso curso;

    public Estudiante(Long id, String nombre, String cedula, boolean estado, Curso curso) {
        super(id, nombre);
        this.cedula = cedula;
        this.estado = estado;
        this.curso = curso;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return getNombre() + " - " + cedula;
    }
}
