package ec.edu.pucem.bocadeurna.dominio;

public class Mesa {
    private Long Id;
    private String nombre;
    private String presidente;
    private String secretario;

    public Mesa(Long Id, String nombre, String presidente,String secretario) {
        this.Id = Id;
        this.nombre = nombre;
        this.presidente = presidente;
        this.secretario = secretario;

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


	public String getPresidente() {
		return presidente;
	}


	public void setPresidente(String presidente) {
		this.presidente = presidente;
	}


	public String getSecretario() {
		return secretario;
	}


	public void setSecretario(String secretario) {
		this.secretario = secretario;
	}


	@Override
    public String toString() {
        return nombre;
    }
}
