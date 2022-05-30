package vista;

import javax.swing.JPanel;

import modelo.Incidencia;
import modelo.Tecnico;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import controlador.GestorFichero;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextArea;

public class PanelAsignar extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GestorFichero gestorDatos;
	public ArrayList<Incidencia> incidencias;
	public ArrayList<Tecnico> tecnicos;
	private JTextField txtEstado;
	private JTextField trtCreacion;
	private JTextField txtAsignado;
	private JTextField txtTiempo;

	/**
	 * Create the panel.
	 */
	public PanelAsignar() {
		setLayout(new CardLayout(0, 0));
		gestorDatos = new GestorFichero();
		this.incidencias = gestorDatos.leerFicheroIncidencias();
		this.tecnicos = gestorDatos.leerFicheroTecnicos();
		JPanel txtFecha = new JPanel();
		txtFecha.setBackground(Color.WHITE);
		add(txtFecha, "name_12037000531300");

		JLabel lblNewLabel_1 = new JLabel("Incidencia:");

		JLabel lblNewLabel_1_1 = new JLabel("Tecnico:");

		ArrayList<String> tecnicosNombre = new ArrayList<String>();
		for (int i = 0; i < this.tecnicos.size(); i++) {
			tecnicosNombre.add(this.tecnicos.get(i).getNombreCompleto());
		}
		ArrayList<String> idStrings = new ArrayList<String>();
		for (int i = 0; i < this.incidencias.size(); i++) {
			idStrings.add(this.incidencias.get(i).getId());
		}
		JTextArea txtDescripcion = new JTextArea();

		txtEstado = new JTextField();
		txtEstado.setColumns(10);

		trtCreacion = new JTextField();
		trtCreacion.setColumns(10);

		txtAsignado = new JTextField();
		txtAsignado.setEditable(false);
		txtAsignado.setColumns(10);

		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Tiempo (H): ");

		txtTiempo = new JTextField();
		txtTiempo.setColumns(10);
		JComboBox comboTecnicos = new JComboBox(tecnicosNombre.toArray());
		JComboBox comboIncidencias = new JComboBox(idStrings.toArray());
		Incidencia auxiliar = new Incidencia();
		auxiliar = gestorDatos.buscarIncidencia(comboIncidencias.getSelectedItem().toString());
		if (auxiliar.getTecnicoAsociado()!=null) {
			txtAsignado.setText(auxiliar.getTecnicoAsociado().getNombreCompleto());
		}else {
			txtAsignado.setText("Sin asignacion");
		}
		txtEstado.setText(auxiliar.getEstado());
		txtDescripcion.setText(auxiliar.getDescripcionIncidencia());
		trtCreacion.setText(auxiliar.getFechaCreacion());
		txtTiempo.setText(String.valueOf(auxiliar.getTiempoResolucion()));
		comboIncidencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Incidencia auxiliar = new Incidencia();
				auxiliar = gestorDatos.buscarIncidencia(comboIncidencias.getSelectedItem().toString());
				if (auxiliar.getTecnicoAsociado()!=null) {
					txtAsignado.setText(auxiliar.getTecnicoAsociado().getNombreCompleto());
				}else {
					txtAsignado.setText("Sin asignacion");
				}
				txtEstado.setText(auxiliar.getEstado());
				txtDescripcion.setText(auxiliar.getDescripcionIncidencia());
				trtCreacion.setText(auxiliar.getFechaCreacion());
				txtTiempo.setText(String.valueOf(auxiliar.getTiempoResolucion()));
			}
		});
		JButton btnAsignar = new JButton("Asignar");
		btnAsignar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(comboIncidencias.getSelectedItem().toString());
				// verifico si se encuentra cerrada
				if (estadoIncidencia(comboIncidencias.getSelectedItem().toString())) {
					// verifico si tiene tecnico asignado
					if (incidenciaTieneTecnico(comboIncidencias.getSelectedItem().toString())) {
						JFrame jFrame = new JFrame();
						int result = JOptionPane.showConfirmDialog(jFrame,
								"La incidencia seleccionada tiene asignacion, ¿Desea sobreescribir?");

						if (result == 0) {
							gestorDatos.reasignarIncidencia(comboIncidencias.getSelectedItem().toString(),
									comboTecnicos.getSelectedItem().toString());
							Incidencia auxiliar = new Incidencia();
							auxiliar = gestorDatos.buscarIncidencia(comboIncidencias.getSelectedItem().toString());
							if (auxiliar.getTecnicoAsociado()!=null) {
								txtAsignado.setText(auxiliar.getTecnicoAsociado().getNombreCompleto());
							}else {
								txtAsignado.setText("Sin asignacion");
							}
							txtEstado.setText(auxiliar.getEstado());
							txtDescripcion.setText(auxiliar.getDescripcionIncidencia());
							trtCreacion.setText(auxiliar.getFechaCreacion());
							txtTiempo.setText(String.valueOf(auxiliar.getTiempoResolucion()));
							JFrame jFrame2 = new JFrame();
							JOptionPane.showMessageDialog(jFrame2, "Asignacion tramitada");
						} else if (result == 1)
							System.out.println("No");
						else
							System.out.println("Cancel");
					} else {
						gestorDatos.reasignarIncidencia(comboIncidencias.getSelectedItem().toString(),
								comboTecnicos.getSelectedItem().toString());
						Incidencia auxiliar = new Incidencia();
						auxiliar = gestorDatos.buscarIncidencia(comboIncidencias.getSelectedItem().toString());
						if (auxiliar.getTecnicoAsociado()!=null) {
							txtAsignado.setText(auxiliar.getTecnicoAsociado().getNombreCompleto());
						}else {
							txtAsignado.setText("Sin asignacion");
						}
						txtEstado.setText(auxiliar.getEstado());
						txtDescripcion.setText(auxiliar.getDescripcionIncidencia());
						trtCreacion.setText(auxiliar.getFechaCreacion());
						txtTiempo.setText(String.valueOf(auxiliar.getTiempoResolucion()));
						JFrame jFrame = new JFrame();
						JOptionPane.showMessageDialog(jFrame, "Asignacion tramitada");
					}
				}
			}
		});

		JLabel lblNewLabel_2 = new JLabel("Descripcion:");

		JLabel lblNewLabel_2_1 = new JLabel("Estado:");

		JLabel lblNewLabel_2_2 = new JLabel("Informacion de la incidencia");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblNewLabel_2_1_1 = new JLabel("Fecha de creacion:");

		JLabel lblNewLabel_2_1_1_1 = new JLabel("Asignada a: ");

		

		JButton btnNewButton = new JButton("Actualizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Incidencia incidencia = new Incidencia();
				incidencia.setDescripcionIncidencia(txtDescripcion.getText());
				incidencia.setId(comboIncidencias.getSelectedItem().toString());
				incidencia.setEstado(txtEstado.getText());
				incidencia.setFechaCreacion(trtCreacion.getText());
				incidencia.setTecnicoAsociado(gestorDatos.buscarTecnico(comboTecnicos.getSelectedItem().toString()));
				incidencia.setTiempoResolucion(Double.parseDouble(txtTiempo.getText()));
				gestorDatos.sustituirIncidencia(comboIncidencias.getSelectedItem().toString(), incidencia);
				Incidencia auxiliar = new Incidencia();
				auxiliar = gestorDatos.buscarIncidencia(comboIncidencias.getSelectedItem().toString());
				if (auxiliar.getTecnicoAsociado()!=null) {
					txtAsignado.setText(auxiliar.getTecnicoAsociado().getNombreCompleto());
				}else {
					txtAsignado.setText("Sin asignacion");
				}
				txtEstado.setText(auxiliar.getEstado());
				txtDescripcion.setText(auxiliar.getDescripcionIncidencia());
				trtCreacion.setText(auxiliar.getFechaCreacion());
				txtTiempo.setText(String.valueOf(auxiliar.getTiempoResolucion()));
				JFrame jFrame = new JFrame();
				JOptionPane.showMessageDialog(jFrame, "Actualizacion tramitada");
			}
		});

		GroupLayout gl_txtFecha = new GroupLayout(txtFecha);
		gl_txtFecha.setHorizontalGroup(
			gl_txtFecha.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_txtFecha.createSequentialGroup()
					.addGroup(gl_txtFecha.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_txtFecha.createSequentialGroup()
							.addGap(30)
							.addGroup(gl_txtFecha.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_txtFecha.createSequentialGroup()
									.addGroup(gl_txtFecha.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_txtFecha.createSequentialGroup()
											.addGap(72)
											.addComponent(comboIncidencias, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
										.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
									.addGap(47)
									.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(comboTecnicos, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblNewLabel_2_2, GroupLayout.PREFERRED_SIZE, 416, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_txtFecha.createSequentialGroup()
									.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_txtFecha.createSequentialGroup()
									.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtEstado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_txtFecha.createSequentialGroup()
									.addComponent(lblNewLabel_2_1_1, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(trtCreacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_txtFecha.createSequentialGroup()
									.addComponent(lblNewLabel_2_1_1_1, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtAsignado, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_txtFecha.createSequentialGroup()
									.addComponent(lblNewLabel_2_1_1_1_1, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtTiempo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnAsignar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
									.addGap(47))))
						.addGroup(gl_txtFecha.createSequentialGroup()
							.addGap(125)
							.addComponent(btnNewButton)))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		gl_txtFecha.setVerticalGroup(
			gl_txtFecha.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_txtFecha.createSequentialGroup()
					.addGap(65)
					.addGroup(gl_txtFecha.createParallelGroup(Alignment.LEADING)
						.addComponent(comboIncidencias, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_txtFecha.createSequentialGroup()
							.addGap(4)
							.addComponent(lblNewLabel_1))
						.addGroup(gl_txtFecha.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_1_1)
							.addComponent(comboTecnicos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(37)
					.addComponent(lblNewLabel_2_2)
					.addGap(36)
					.addGroup(gl_txtFecha.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(txtDescripcion, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_txtFecha.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2_1)
						.addComponent(txtEstado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_txtFecha.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2_1_1)
						.addComponent(trtCreacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_txtFecha.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2_1_1_1)
						.addComponent(txtAsignado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(gl_txtFecha.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2_1_1_1_1)
						.addComponent(txtTiempo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAsignar))
					.addGap(31)
					.addComponent(btnNewButton)
					.addGap(64))
		);
		txtFecha.setLayout(gl_txtFecha);

	}

	private Incidencia buscarIncidenciaId(String id) {
		for (int i = 0; i < this.incidencias.size(); i++) {
			if (this.incidencias.get(i).getId().equalsIgnoreCase(id)) {
				return this.incidencias.get(i);
			}
		}
		return null;
	}

	private boolean incidenciaTieneTecnico(String id) {
		for (int i = 0; i < this.incidencias.size(); i++) {
			if (this.incidencias.get(i).getId().equalsIgnoreCase(id)
					&& this.incidencias.get(i).getTecnicoAsociado() != null) {
				return true;
			}
		}
		return false;
	}

	// Si la incidencia esta cerrada no la puedo asignar
	private boolean estadoIncidencia(String id) {
		for (int i = 0; i < this.incidencias.size(); i++) {
			if (this.incidencias.get(i).getId().equalsIgnoreCase(id)
					&& this.incidencias.get(i).getEstado().equalsIgnoreCase("cerrada")) {
				JFrame jFrame = new JFrame();
				JOptionPane.showMessageDialog(jFrame, "La incidencia indicada se encuentra cerrada");
				return false;
			}
		}
		return true;
	}
}
