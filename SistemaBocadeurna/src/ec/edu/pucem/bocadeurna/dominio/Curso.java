package ec.edu.pucem.bocadeurna.dominio;

public class Curso {
    private long id;
    private String nombre;
    private Mesa mesa;

    public Curso(long id, String nombre, Mesa mesa) {
        this.id = id;
        this.nombre = nombre;
        this.mesa = mesa;
    }


    public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Mesa getMesa() {
		return mesa;
	}


	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}


	@Override
    public String toString() {
        return nombre;
    }
}
