package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.DefaultRowSorter;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

/**
 * La clase Ventana crea con la ayuda de la librer�a Swing de java un entorno
 * gr�fico donde se puede ver el contenido ordenado de los fichero
 * "personas.txt","asignaturas.txt y "avisos.txt" separando las personas entre
 * alumnos y profesores.
 * 
 * @author Brais Piñeiro y Jorge Pose
 *
 */
public class Ventana {
	// Atributos
	final private String[] cabezeraprofes = { "DNI", "Nombre", "Apellidos", "Fecha de Nacimiento", "Categor�a",
			"Departamento", "Horas Asignables", "Docencia Impartida" };
	final private String[] cabezeraalumnos = { "DNI", "Nombre", "Apellidos", "Fecha de Nacimiento", "Fecha de Ingreso",
			"Asignaturas aprobadas", "Docencia Recibida" };
	final private String[] cabezeraasignaturas = { "ID", "Nombre", "Siglas", "Curso", "Coordinador", "Prerrequisitos",
			"Grupos A", "Grupos B" };
	final private String[] cabezeraavisos = { "Comando", "Aviso" };
	private JTable profesores = new JTable();
	private JTable alumnos = new JTable();
	private JTable asignaturas = new JTable();
	private JTable avisos = new JTable();
	private JTabbedPane panel = new JTabbedPane();
	private JFrame frame = new JFrame("Centro Universitario");

	/**
	 * Este es el �nico constructor de la clase Ventana, que se encarga de crear
	 * todos los elementos y tablas necesarias.
	 * 
	 * @throws IOException Excepción que se lanzaría en caso de errores relacionados con la escritura y lectura de ficheros.
	 */
	public Ventana() throws IOException {
		BufferedReader txt = null;
		try {
			txt = new BufferedReader(new FileReader("personas.txt"));
		} catch (Exception e) {
			System.out.println("Fichero inexistente");
			System.exit(0);
		}
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			System.out.println("Error al cambiar Look and Feel");
		}
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DefaultTableModel modeloprofesor = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		DefaultTableModel modeloalumno = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		DefaultTableModel modeloasignatura = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		DefaultTableModel modeloaviso = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modeloprofesor.setColumnIdentifiers(cabezeraprofes);
		modeloalumno.setColumnIdentifiers(cabezeraalumnos);
		modeloasignatura.setColumnIdentifiers(cabezeraasignaturas);
		modeloaviso.setColumnIdentifiers(cabezeraavisos);
		String linea;
		while ((linea = txt.readLine()) != null) {
			if (linea.equals("*"))
				continue;
			if (linea.equals(""))
				continue;
			if (linea.trim().equals("profesor")) {
				String[] dato = { txt.readLine(), txt.readLine(), txt.readLine(), txt.readLine(), txt.readLine(),
						txt.readLine(), txt.readLine(), txt.readLine() };
				modeloprofesor.addRow(dato);
			} else {
				String[] dato = { txt.readLine(), txt.readLine(), txt.readLine(), txt.readLine(), txt.readLine(),
						txt.readLine(), txt.readLine() };
				modeloalumno.addRow(dato);
			}
		}
		txt.close();
		try {
			txt = new BufferedReader(new FileReader("asignaturas.txt"));
		} catch (Exception e) {
			System.out.println("Fichero inexistente");
			System.exit(0);
		}
		while ((linea = txt.readLine()) != null) {
			if (linea.equals("*"))
				continue;
			if (linea.equals(""))
				continue;
			String[] dato = { linea, txt.readLine(), txt.readLine(), txt.readLine(), txt.readLine(), txt.readLine(),
					txt.readLine(), txt.readLine() };
			modeloasignatura.addRow(dato);
		}
		txt.close();
		try {
			txt = new BufferedReader(new FileReader("avisos.txt"));
		} catch (Exception e) {
			System.out.println("Fichero inexistente");
			return;
		}
		ArrayList<String[]> listaavisos = new ArrayList<String[]>();
		while ((linea = txt.readLine()) != null) {
			listaavisos.add(linea.split(" -- "));
		}
		txt.close();
		Collections.reverse(listaavisos);
		for (String[] s : listaavisos) {
			modeloaviso.addRow(s);
		}
		profesores.setModel(modeloprofesor);
		tableSort(profesores, 0);
		alumnos.setModel(modeloalumno);
		tableSort(alumnos, 0);
		asignaturas.setModel(modeloasignatura);
		tableSort(asignaturas, 2);
		avisos.setModel(modeloaviso);
		avisos.setAutoCreateRowSorter(true);
		panel.addTab("Profesores", new JScrollPane(profesores));
		panel.addTab("Alumnos", new JScrollPane(alumnos));
		panel.addTab("Asignaturas", new JScrollPane(asignaturas));
		panel.addTab("Avisos", new JScrollPane(avisos));
		frame.add(panel);
		frame.setVisible(true);
	}

	/**
	 * Este método se encarga de ordenar un tabla a partir de los datos de la
	 * columna que se indique.
	 * 
	 * @param t
	 *            Corresponde con la tabla a ordenar.
	 * @param i
	 *            Corresponde con la columna a partir de la cual se quiere
	 *            ordenar la tabla
	 */
	private static void tableSort(JTable t, int i) {
		t.setAutoCreateRowSorter(true);
		DefaultRowSorter sorter = ((DefaultRowSorter) t.getRowSorter());
		ArrayList list = new ArrayList();
		list.add(new RowSorter.SortKey(i, SortOrder.ASCENDING));
		sorter.setSortKeys(list);
		sorter.sort();
	}
}
