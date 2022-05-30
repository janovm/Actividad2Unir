package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Incidencia {
	private String id;
	private String descripcionIncidencia;
	private String fechaCreacion;
	private String fechaCierre;
	private String estado;
	private Tecnico tecnicoAsociado;
	private Double tiempoResolucion;

	public Incidencia(String id, String descripcionIncidencia, String fecha, String fechaCierre, String estado,
			Tecnico tecnicoAsociado, Double tiempoResolucion) {
		super();
		this.id = id;
		this.descripcionIncidencia = descripcionIncidencia;
		this.fechaCreacion = fecha;
		this.fechaCierre = fechaCierre;
		this.estado = estado;
		this.tecnicoAsociado = tecnicoAsociado;
		this.tiempoResolucion = tiempoResolucion;
	}
	
	public Incidencia() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescripcionIncidencia() {
		return descripcionIncidencia;
	}

	public void setDescripcionIncidencia(String descripcionIncidencia) {
		this.descripcionIncidencia = descripcionIncidencia;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Tecnico getTecnicoAsociado() {
		return tecnicoAsociado;
	}

	public void setTecnicoAsociado(Tecnico tecnicoAsociado) {
		this.tecnicoAsociado = tecnicoAsociado;
	}

	public Double getTiempoResolucion() {
		return tiempoResolucion;
	}

	public void setTiempoResolucion(Double tiempoResolucion) {
		this.tiempoResolucion = tiempoResolucion;
	}
	
	

}
