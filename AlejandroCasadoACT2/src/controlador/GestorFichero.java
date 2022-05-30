package controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import modelo.Incidencia;
import modelo.Tecnico;

public class GestorFichero {
	public ArrayList<Incidencia> incidencias;
	public ArrayList<Tecnico> tecnicos;

	public GestorFichero() {
		super();
	}

	public void crearDatos() {
		this.incidencias = new ArrayList<>();
		this.tecnicos = new ArrayList<>();

		Tecnico andres = new Tecnico("Andrés Quirón Pérez", "N1");
		this.tecnicos.add(andres);
		Tecnico alberto = new Tecnico("Alberto Jiménez García", "N2");
		this.tecnicos.add(alberto);

		Incidencia i1 = new Incidencia("IC001", "Problema aplicación Outlook", "2/3/2020", null, "en progreso", andres,
				0.00);
		incidencias.add(i1);

		i1 = new Incidencia("IC002", "Problema en disco", "7/4/2021", null, "en progreso", alberto, 0.00);
		incidencias.add(i1);

		i1 = new Incidencia("IC003", "Problema en SharePoint", "7/4/2021", null, "en progreso", null, 0.00);
		incidencias.add(i1);

		i1 = new Incidencia("IC004", "Problema en SharePoint", "8/4/2021", null, "cerrada", alberto, 120.00);
		incidencias.add(i1);

		i1 = new Incidencia("IC005", "Problema en Adobe", "7/4/2021", null, "abierta", null, 0.00);
		incidencias.add(i1);
	}

	public void escribirFicheroIncidencias() {
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			fichero = new FileWriter("incidencias.txt");
			pw = new PrintWriter(fichero);

			for (int i = 0; i < incidencias.size(); i++) {
				pw.println(incidencias.get(i).getId());
				pw.println(incidencias.get(i).getDescripcionIncidencia());
				pw.println(incidencias.get(i).getEstado());
				pw.println(incidencias.get(i).getFechaCreacion().toString());
				if (incidencias.get(i).getFechaCierre() == null) {
					pw.println("null");
				} else {
					pw.println(incidencias.get(i).getFechaCierre().toString());
				}
				if (incidencias.get(i).getTecnicoAsociado() == null) {
					pw.println("null");
				} else {
					pw.println(incidencias.get(i).getTecnicoAsociado().getNombreCompleto());
				}
				pw.println(incidencias.get(i).getTiempoResolucion().toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void escribirFicheroTecnicos() {
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			fichero = new FileWriter("tecnicos.txt");
			pw = new PrintWriter(fichero);

			for (int i = 0; i < tecnicos.size(); i++) {
				pw.println(tecnicos.get(i).getNombreCompleto());
				pw.println(tecnicos.get(i).getNivel());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public ArrayList<Tecnico> leerFicheroTecnicos() {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		ArrayList<Tecnico> tecnicosAux = null;
		
		try {
			archivo = new File("tecnicos.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea;
			int contador=0;
			tecnicosAux = new ArrayList<>();
			String texto="";
			while ((linea = br.readLine()) != null) {
				texto= texto+linea+";";
			}
			String[] tecnicos=texto.split(";");
			for (int i = 0; i < tecnicos.length; i=i+2) {
				Tecnico aux = new Tecnico();
				aux.setNombreCompleto(tecnicos[i]);
				aux.setNivel(tecnicos[i+1]);
				tecnicosAux.add(aux);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return tecnicosAux;
	}
	
	public ArrayList<Incidencia> leerFicheroIncidencias() {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		ArrayList<Incidencia> incidenciasAux = null;
		
		try {
			archivo = new File("incidencias.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea;
			incidenciasAux = new ArrayList<>();
			String texto="";
			while ((linea = br.readLine()) != null) {
				texto= texto+linea+";";
			}
			String [] auxiliarIncidencias=texto.split(";");
			for (int i = 0; i < auxiliarIncidencias.length; i=i+7) {
				Incidencia auxiliar= new Incidencia();
				auxiliar.setId(auxiliarIncidencias[i]);
				auxiliar.setDescripcionIncidencia(auxiliarIncidencias[i+1]);
				auxiliar.setEstado(auxiliarIncidencias[i+2]);
				auxiliar.setFechaCierre("null");
				auxiliar.setFechaCreacion(auxiliarIncidencias[i+3]);
				auxiliar.setTecnicoAsociado(buscarTecnico(auxiliarIncidencias[i+5]));
				auxiliar.setTiempoResolucion(Double.parseDouble(auxiliarIncidencias[i+6]));
				incidenciasAux.add(auxiliar);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return incidenciasAux;
	}
	
	public Tecnico buscarTecnico(String nombre) {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		Tecnico aux = null;
		try {
			archivo = new File("tecnicos.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea;
			String texto="";
			while ((linea = br.readLine()) != null) {
				texto= texto+linea+";";
			}
			String[] tecnicos=texto.split(";");
			for (int i = 0; i < tecnicos.length; i=i+2) {
				if (tecnicos[i].equalsIgnoreCase(nombre)) {
					aux = new Tecnico();
					aux.setNombreCompleto(tecnicos[i]);
					aux.setNivel(tecnicos[i+1]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return aux;
	}
	
	public Incidencia buscarIncidencia(String id) {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		Incidencia incidenciaAux = null;
		
		try {
			archivo = new File("incidencias.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea;
			String texto="";
			while ((linea = br.readLine()) != null) {
				texto= texto+linea+";";
			}
			String [] auxiliarIncidencias=texto.split(";");
			for (int i = 0; i < auxiliarIncidencias.length; i=i+7) {
				if (auxiliarIncidencias[i].equals(id)) {
					incidenciaAux= new Incidencia();
					incidenciaAux.setId(auxiliarIncidencias[i]);
					incidenciaAux.setDescripcionIncidencia(auxiliarIncidencias[i+1]);
					incidenciaAux.setEstado(auxiliarIncidencias[i+2]);
					incidenciaAux.setFechaCierre("null");
					incidenciaAux.setFechaCreacion(auxiliarIncidencias[i+3]);
					incidenciaAux.setTecnicoAsociado(buscarTecnico(auxiliarIncidencias[i+5]));
					incidenciaAux.setTiempoResolucion(Double.parseDouble(auxiliarIncidencias[i+6]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return incidenciaAux;
	}
	
	public void reasignarIncidencia(String id, String tecnico) {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		ArrayList<Incidencia> incidenciasAux = null;
		
		try {
			archivo = new File("incidencias.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea;
			incidenciasAux = new ArrayList<>();
			String texto="";
			while ((linea = br.readLine()) != null) {
				texto= texto+linea+";";
			}
			String [] auxiliarIncidencias=texto.split(";");
			for (int i = 0; i < auxiliarIncidencias.length; i=i+7) {
				if (auxiliarIncidencias[i].equals(id)) {
					Incidencia auxiliar= new Incidencia();
					auxiliar.setId(auxiliarIncidencias[i]);
					auxiliar.setDescripcionIncidencia(auxiliarIncidencias[i+1]);
					auxiliar.setEstado(auxiliarIncidencias[i+2]);
					auxiliar.setFechaCierre("null");
					auxiliar.setFechaCreacion(auxiliarIncidencias[i+3]);
					auxiliar.setTecnicoAsociado(buscarTecnico(tecnico));
					auxiliar.setTiempoResolucion(Double.parseDouble(auxiliarIncidencias[i+6]));
					incidenciasAux.add(auxiliar);
				}else {
					Incidencia auxiliar= new Incidencia();
					auxiliar.setId(auxiliarIncidencias[i]);
					auxiliar.setDescripcionIncidencia(auxiliarIncidencias[i+1]);
					auxiliar.setEstado(auxiliarIncidencias[i+2]);
					auxiliar.setFechaCierre("null");
					auxiliar.setFechaCreacion(auxiliarIncidencias[i+3]);
					auxiliar.setTecnicoAsociado(buscarTecnico(auxiliarIncidencias[i+5]));
					auxiliar.setTiempoResolucion(Double.parseDouble(auxiliarIncidencias[i+6]));
					incidenciasAux.add(auxiliar);
				}
				this.incidencias=incidenciasAux;
				escribirFicheroIncidencias();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void sustituirIncidencia(String id, Incidencia incidencia) {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		ArrayList<Incidencia> incidenciasAux = null;
		
		try {
			archivo = new File("incidencias.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			String linea;
			incidenciasAux = new ArrayList<>();
			String texto="";
			while ((linea = br.readLine()) != null) {
				texto= texto+linea+";";
			}
			String [] auxiliarIncidencias=texto.split(";");
			for (int i = 0; i < auxiliarIncidencias.length; i=i+7) {
				if (auxiliarIncidencias[i].equals(id)) {
					Incidencia auxiliar= new Incidencia();
					auxiliar.setId(id);
					auxiliar.setDescripcionIncidencia(incidencia.getDescripcionIncidencia());
					auxiliar.setEstado(incidencia.getEstado());
					auxiliar.setFechaCierre("null");
					auxiliar.setFechaCreacion(incidencia.getFechaCreacion());
					auxiliar.setTecnicoAsociado(buscarTecnico(auxiliarIncidencias[i+5]));
					auxiliar.setTiempoResolucion(incidencia.getTiempoResolucion());
					incidenciasAux.add(auxiliar);
				}else {
					Incidencia auxiliar= new Incidencia();
					auxiliar.setId(auxiliarIncidencias[i]);
					auxiliar.setDescripcionIncidencia(auxiliarIncidencias[i+1]);
					auxiliar.setEstado(auxiliarIncidencias[i+2]);
					auxiliar.setFechaCierre("null");
					auxiliar.setFechaCreacion(auxiliarIncidencias[i+3]);
					auxiliar.setTecnicoAsociado(buscarTecnico(auxiliarIncidencias[i+5]));
					auxiliar.setTiempoResolucion(Double.parseDouble(auxiliarIncidencias[i+6]));
					incidenciasAux.add(auxiliar);
				}
				this.incidencias=incidenciasAux;
				escribirFicheroIncidencias();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
}
