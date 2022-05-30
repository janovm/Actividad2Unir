package vista;

import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import controlador.GestorFichero;
import modelo.Incidencia;
import modelo.Tecnico;

public class PanelInformes extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GestorFichero gestorDatos;
	private JTextField txtCerradas;
	private JTextField txtAbiertas;
	private JTextField txtEnProgreso;
	private JTextField txtResolucion;
	private JTextField txtMvp;
	private JTextField txtLento;
	private ArrayList<Incidencia> arrayIncidencias;
	private ArrayList<Tecnico> arrayTecnicos;

	/**
	 * Create the panel.
	 */
	public PanelInformes() {
		setLayout(new CardLayout(0, 0));
		gestorDatos = new GestorFichero();
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel, "name_12057162526800");

		JLabel lblNewLabel = new JLabel("KPI de rendimiento");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));

		JLabel lblNewLabel_1 = new JLabel("% de incidencias cerradas:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblNewLabel_1_1 = new JLabel("% de incidencias abiertas:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblNewLabel_1_1_1 = new JLabel("% de incidencias en progreso:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Tiempo medio de resolucion/incidencia: ");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Tecnico mas resolutivo:");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Tecnico menos resolutivo:");
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		arrayIncidencias = gestorDatos.leerFicheroIncidencias();
		arrayTecnicos = gestorDatos.leerFicheroTecnicos();

		txtCerradas = new JTextField();
		txtCerradas.setEditable(false);
		txtCerradas.setColumns(10);

		txtAbiertas = new JTextField();
		txtAbiertas.setEditable(false);
		txtAbiertas.setColumns(10);

		txtEnProgreso = new JTextField();
		txtEnProgreso.setEditable(false);
		txtEnProgreso.setColumns(10);

		txtResolucion = new JTextField();
		txtResolucion.setEditable(false);
		txtResolucion.setColumns(10);

		txtMvp = new JTextField();
		txtMvp.setEditable(false);
		txtMvp.setColumns(10);

		txtLento = new JTextField();
		txtLento.setEditable(false);
		txtLento.setColumns(10);

		datosEstados();
		mejorTecnico();
		peorTecnico();
		mediaResolucion();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_1_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(txtLento, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtCerradas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtAbiertas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtEnProgreso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtMvp, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
									.addGap(97))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
									.addGap(97))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_1_1_1_1, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE))
									.addGap(18)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtResolucion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(58))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(103)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(txtCerradas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtAbiertas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtEnProgreso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_1_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtResolucion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtMvp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtLento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(108, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);

	}

	public void datosEstados() {
		int progress = 0;
		int cerrada = 0;
		int abierta = 0;
		for (int i = 0; i < this.arrayIncidencias.size(); i++) {
			switch (this.arrayIncidencias.get(i).getEstado()) {
			case "en progreso": {
				progress++;
				break;
			}
			case "cerrada": {
				cerrada++;
				break;
			}
			case "abierta": {
				abierta++;
				break;
			}
			}
		}

		float aux = 0;

		aux = ((abierta) * 10 / (this.arrayIncidencias.size()) * 10);
		txtAbiertas.setText(String.valueOf(aux));

		aux = ((cerrada) * 10 / (this.arrayIncidencias.size()) * 10);
		txtCerradas.setText(String.valueOf(aux));

		aux = ((progress) * 10 / (this.arrayIncidencias.size()) * 10);
		txtEnProgreso.setText(String.valueOf(aux));
	}

	public void mediaResolucion() {
		Double total = 0.00;
		for (int i = 0; i < this.arrayIncidencias.size(); i++) {
			total = total + this.arrayIncidencias.get(i).getTiempoResolucion();
		}

		this.txtResolucion.setText(String.valueOf(total / this.arrayIncidencias.size()));

	}

	public void mejorTecnico() {
		int numeroIncidencias;
		int maximoIncidencias = 0;
		String nombreMaximo = null;
		for (int i = 0; i < this.arrayTecnicos.size(); i++) {
			numeroIncidencias = 0;
			for (int j = 0; j < this.arrayIncidencias.size(); j++) {
				if (this.arrayIncidencias.get(j).getTecnicoAsociado() != null
						&& this.arrayIncidencias.get(j).getTecnicoAsociado().getNombreCompleto()
								.equals(this.arrayTecnicos.get(i).getNombreCompleto())) {
					numeroIncidencias++;
				}
				if (maximoIncidencias < numeroIncidencias) {
					maximoIncidencias = numeroIncidencias;
					nombreMaximo = this.arrayTecnicos.get(i).getNombreCompleto();
				}
			}

		}
		this.txtMvp.setText(nombreMaximo.concat(" (" + String.valueOf(maximoIncidencias) + ")"));
	}

	public void peorTecnico() {
		int numeroIncidencias;
		int minimoIncidencias = 0;
		String nombreMaximo = null;
		nombreMaximo = this.arrayTecnicos.get(0).getNombreCompleto();
		minimoIncidencias = 0;
		for (int i = 0; i < this.arrayIncidencias.size(); i++) {
			if (this.arrayIncidencias.get(i).getTecnicoAsociado() != null
					&& this.arrayIncidencias.get(i).getTecnicoAsociado().getNombreCompleto().equals(nombreMaximo)) {
				minimoIncidencias++;
			}
		}
		for (int i = 0; i < this.arrayTecnicos.size(); i++) {
			numeroIncidencias = 0;
			for (int j = 0; j < this.arrayIncidencias.size(); j++) {
				if (this.arrayIncidencias.get(j).getTecnicoAsociado() != null
						&& this.arrayIncidencias.get(j).getTecnicoAsociado().getNombreCompleto()
								.equals(this.arrayTecnicos.get(i).getNombreCompleto())) {
					numeroIncidencias++;
				}
			}
			if (minimoIncidencias > numeroIncidencias) {
				minimoIncidencias = numeroIncidencias;
				nombreMaximo = this.arrayTecnicos.get(i).getNombreCompleto();
			}
		}

		this.txtLento.setText(nombreMaximo.concat(" (" + String.valueOf(minimoIncidencias) + ")"));
	}
}
