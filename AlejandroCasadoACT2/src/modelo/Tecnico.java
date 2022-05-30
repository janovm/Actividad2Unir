package modelo;

public class Tecnico {

	private String nombreCompleto;
	private String nivel;

	public Tecnico(String nombreCompleto, String nivel) {
		super();
		this.nombreCompleto = nombreCompleto;
		this.nivel = nivel;
	}

	public Tecnico() {
		super();
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	
	

}
