package controlador;

import vista.FrameInterfaz;

public class Controlador {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GestorFichero gestor = new GestorFichero();
		gestor.crearDatos();
		gestor.escribirFicheroIncidencias();
		gestor.escribirFicheroTecnicos();
		FrameInterfaz p = new FrameInterfaz();
		p.setVisible(true);
	}

}
