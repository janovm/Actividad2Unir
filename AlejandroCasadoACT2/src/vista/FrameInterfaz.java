package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.GestorFichero;
import modelo.Incidencia;
import modelo.Tecnico;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;

public class FrameInterfaz extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static GestorFichero datos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					datos = new GestorFichero();
					FrameInterfaz frame = new FrameInterfaz();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameInterfaz() {
		setResizable(false);
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 521);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 255));
		panel.setBounds(0, 0, 270, 482);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel content = new JPanel();
		content.setBackground(Color.WHITE);
		content.setBounds(272, 0, 522, 482);
		contentPane.add(content);

		JButton btnAsignarIncidencia = new JButton("Asignar incidencia");
		btnAsignarIncidencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelAsignar p1 = new PanelAsignar();
				p1.setSize(522, 482);
				p1.setLocation(0, 0);
				content.removeAll();
				content.add(p1, BorderLayout.CENTER);
				content.revalidate();
				content.repaint();
			}
		});
		btnAsignarIncidencia.setBackground(Color.WHITE);
		btnAsignarIncidencia.setBounds(40, 110, 200, 30);
		panel.add(btnAsignarIncidencia);

		JButton btnInformes = new JButton("Informes");
		btnInformes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelInformes p1 = new PanelInformes();
				p1.setSize(522, 482);
				p1.setLocation(0, 0);
				content.removeAll();
				content.add(p1, BorderLayout.CENTER);
				content.revalidate();
				content.repaint();
			}
		});
		btnInformes.setBackground(Color.WHITE);
		btnInformes.setBounds(40, 337, 200, 30);
		panel.add(btnInformes);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
		this.setLocation(x, y);

	}

}
