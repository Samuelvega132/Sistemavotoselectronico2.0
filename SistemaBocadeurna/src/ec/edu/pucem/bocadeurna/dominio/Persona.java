package ec.edu.pucem.bocadeurna.dominio;

public class Persona {
    private Long Id;
    private String nombre;

    public Persona(Long Id, String nombre) {
        this.Id = Id;
        this.nombre = nombre;

    }


    public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
    public String toString() {
        return nombre;
    }
}
