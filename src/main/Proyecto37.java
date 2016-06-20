package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import docencia.Aprobada;
import docencia.Asignatura;
import docencia.Clase;
import docencia.Grupo;
import personas.Alumno;
import personas.Asociado;
import personas.Persona;
import personas.Profesor;
import personas.Titular;

/**
 * Esta es la clase Main, la principal de nuestro programa, que representa un
 * centro universitario. En esta clase se incluyen los atributos del centro y
 * los métodos para ejecutar cada una de sus funciones. A su vez, la clase
 * implementa de la interfaz Comparator del util de java, usada por alg�n
 * método.
 * 
 * @author Brais Piñeiro y Jorge Pose
 * @see personas.Asociado
 * @see personas.Profesor
 * @see personas.Titular
 * @see personas.Persona
 * @see personas.Alumno
 * @see main.Aviso
 * @see main.Ventana
 * @see docencia.Asignatura
 * @see docencia.Aprobada
 * @see docencia.Clase
 * @see docencia.Grupo
 */
public class Proyecto37 {
	// ATRIBUTOS
	public static HashMap<String, Profesor> totalprofesores = new HashMap<String, Profesor>();
	public static HashMap<String, Alumno> totalalumnos = new HashMap<String, Alumno>();
	public static HashMap<Integer, Asignatura> totalasignaturas = new HashMap<Integer, Asignatura>();

	/**
	 * Este método es el main, donde se realiza la lectura de los txt
	 * correspondientes para gestionar el centro universitario y desde donde se
	 * hacen las llamadas a los métodos particulares que regulan esas funciones
	 * del centro.
	 * 
	 * @param args
	 *            No se usa en este programa.
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 * @throws NumberFormatException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la conversión de un String a un Integer.
	 */
	public static void main(String[] args) throws IOException, NumberFormatException {
		Aviso aviso = new Aviso();
		BufferedReader txt = null;
		String linea;
		try {
			txt = new BufferedReader(new FileReader("asignaturas.txt"));
		} catch (Exception e) {
			System.out.println("Fichero inexistente");
			System.exit(0);
		}
		while ((linea = txt.readLine()) != null) {
			linea = linea.trim();
			if (linea.equals("*"))
				continue;
			if (linea.equals(""))
				continue;
			int ID = Integer.parseInt(linea);
			totalasignaturas.put(ID, new Asignatura(ID, txt.readLine().trim(), txt.readLine().trim(),
					Integer.parseInt(txt.readLine().trim())));
			Asignatura a = totalasignaturas.get(ID);
			String DNIcoord = txt.readLine();
			if (!DNIcoord.trim().isEmpty()) {
				a.setCoordinador(DNIcoord);
				DNIcoord = null;
			}
			String[] prerrequisitos = txt.readLine().split(",");
			for (String s : prerrequisitos) {
				if (s.trim().isEmpty())
					break;
				a.addPrerrequisitos(Integer.parseInt(s.trim()));
			}
			String[] grupos = txt.readLine().split(";");
			for (String s : grupos) {
				if (s.trim().isEmpty())
					break;
				s = s.trim();
				String[] infogrupo = s.split("\\s+");
				ID = Integer.parseInt(infogrupo[0].trim());
				a.putGruposA(ID, new Grupo(ID, infogrupo[1].charAt(0), Integer.parseInt(infogrupo[2].trim()),
						Integer.parseInt(infogrupo[3].trim())));
			}
			grupos = txt.readLine().split(";");
			for (String s : grupos) {
				if (s.trim().isEmpty())
					break;
				s = s.trim();
				String[] infogrupo = s.split("\\s+");
				ID = Integer.parseInt(infogrupo[0].trim());
				a.putGruposB(ID, new Grupo(ID, infogrupo[1].charAt(0), Integer.parseInt(infogrupo[2].trim()),
						Integer.parseInt(infogrupo[3].trim())));
			}
		}
		txt.close();
		try {
			txt = new BufferedReader(new FileReader("personas.txt"));
		} catch (Exception e) {
			System.out.println("Fichero inexistente");
			System.exit(0);
		}
		while ((linea = txt.readLine()) != null) {
			linea = linea.trim();
			Persona p = null;
			if (linea.trim().equals("*"))
				continue;
			if (linea.equals(""))
				continue;
			String[] datospersona = { txt.readLine(), txt.readLine(), txt.readLine(), txt.readLine() };
			for (int i = 0; i < datospersona.length; i++) {
				datospersona[i] = datospersona[i].trim();
			}
			if (linea.equals("alumno")) {
				p = new Alumno(datospersona[0], datospersona[1], datospersona[2], datospersona[3],
						txt.readLine().trim());
				String[] asignaturas = txt.readLine().split(";");
				for (String a : asignaturas) {
					if (a.trim().isEmpty())
						break;
					a = a.trim();
					String[] infoasignatura = a.split("\\s+");
					int ID = Integer.parseInt(infoasignatura[0]);
					((Alumno) p).putAprobada(ID,
							new Aprobada(ID, infoasignatura[1], Float.parseFloat(infoasignatura[2])));
				}
				asignaturas = txt.readLine().split(";");
				if (asignaturas.length > 0 && !totalasignaturas.isEmpty()) {
					for (String a : asignaturas) {
						if (a.trim().isEmpty())
							break;
						a = a.trim();
						String[] infoasignatura = a.split("\\s+");
						int ID = Integer.parseInt(infoasignatura[0]);
						Asignatura x = totalasignaturas.get(ID);
						Clase c = ((Alumno) p).getClase(ID);
						if (c == null) {
							((Alumno) p).putClase(ID, new Clase(ID, x));
							c = ((Alumno) p).getClase(ID);
						}
						if (infoasignatura.length == 1)
							continue;
						if (infoasignatura[1].equals("A")) {
							c.setGrupoA(x.getGrupoA(Integer.parseInt(infoasignatura[2])));
						} else {
							c.setGrupoB(x.getGrupoB(Integer.parseInt(infoasignatura[2])));
						}

					}
				}
				totalalumnos.put(p.getDNI(), (Alumno) p);
			}
			if (linea.equals("profesor")) {
				if (txt.readLine().trim().equals("titular")) {
					p = new Titular(datospersona[0], datospersona[1], datospersona[2], datospersona[3],
							txt.readLine().trim(), Integer.parseInt(txt.readLine().trim()));
				} else {
					p = new Asociado(datospersona[0], datospersona[1], datospersona[2], datospersona[3],
							txt.readLine().trim(), Integer.parseInt(txt.readLine().trim()));
				}
				String[] asignaturas = txt.readLine().split(";");
				if (asignaturas.length > 0 && !totalasignaturas.isEmpty()) {
					for (String a : asignaturas) {
						if (a.trim().isEmpty())
							break;
						a = a.trim();
						String[] infoasignatura = a.split("\\s+");
						int ID = Integer.parseInt(infoasignatura[0]);
						Asignatura x = totalasignaturas.get(ID);
						Clase c = ((Profesor) p).getClase(ID);
						if (c == null) {
							((Profesor) p).putClase(ID, new Clase(ID, x));
							c = ((Profesor) p).getClase(ID);
						}
						if (infoasignatura.length == 1)
							continue;
						if (infoasignatura[1].equals("A")) {
							c.setGrupoA(x.getGrupoA(Integer.parseInt(infoasignatura[2])));
						} else {
							c.setGrupoB(x.getGrupoB(Integer.parseInt(infoasignatura[2])));
						}
					}
				}
				totalprofesores.put(p.getDNI(), (Profesor) p);
			}
		}
		txt.close();
		try {
			txt = new BufferedReader(new FileReader("ejecucion.txt"));
		} catch (Exception e) {
			System.out.println("Fichero inexistente");
			System.exit(0);
		}

		while ((linea = txt.readLine()) != null) {
			String[] comando;
			linea = linea.trim();
			if (linea.startsWith("*"))
				continue;
			if (linea.equals(""))
				continue;
			if (linea.toLowerCase().startsWith("insertapersona")) {
				comando = linea.split("\"");
				insertaPersona(comando, aviso);
				continue;
			}
			if (linea.toLowerCase().startsWith("asignacoordinador")) {
				comando = linea.split("\\s+");
				asignaCoordinador(comando, aviso);
				continue;
			}
			if (linea.toLowerCase().startsWith("asignacargadocente")) {
				comando = linea.split("\\s+");
				asignaCargaDocente(comando, aviso);
				continue;
			}
			if (linea.toLowerCase().startsWith("matricula")) {
				comando = linea.split("\\s+");
				matricular(comando, aviso);
				continue;
			}
			if (linea.toLowerCase().startsWith("expediente")) {
				comando = linea.split("\\s+");
				expediente(comando, aviso);
				continue;
			}
			if (linea.toLowerCase().startsWith("obtenercalendarioclases")) {
				comando = linea.split("\\s+");
				calendarioProfesor(comando, aviso);
				continue;
			}
			if (linea.toLowerCase().startsWith("evalua")) {
				comando = linea.split("\\s+");
				evaluaAsignatura(comando, aviso);
				continue;
			}
			if (linea.toLowerCase().startsWith("asignagrupo")) {
				comando = linea.split("\\s+");
				asignaGrupo(comando, aviso);
				continue;
			} else {
				aviso.printAviso(null, "Comando mal introducido");
				continue;
			}
		}
		txt.close();
		aviso.closeAviso();
		printExit();
		Ventana j = new Ventana();
	}

	/**
	 * Este método se encarga de introducir una persona en el sistema,
	 * considerando una serie de situaciones no permitidas por el sistema y
	 * provocando los avisos pertinentes a cada una de esas situaciones
	 * 
	 * @param comando
	 *            La línea del txt de ejecución donde viene la información
	 *            necesaria para la función
	 * @param aviso
	 *            Un objeto aviso para poder hacer saltar los diferentes avisos
	 *            en su txt
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	public static void insertaPersona(String[] comando, Aviso aviso) throws IOException {
		String s = "insertapersona";
		String[] datos0 = comando[0].split("\\s+");
		String[] datos1 = null;
		int horas = 0;
		if (!aviso.checkArgumentos(s, datos0, 3)) {

			return;
		}
		if (datos0[1].trim().equals("profesor")) {
			if (!aviso.checkArgumentos(s, comando, 7)) {

				return;
			}
			datos1 = comando[4].split("\\s+");
			if (!aviso.checkArgumentos(s, datos1, 3)) {
				return;
			}
			horas = Integer.parseInt(comando[6].trim());

		}
		if (datos0[1].trim().equals("alumno")) {
			if (!aviso.checkArgumentos(s, comando, 5)) {
				return;
			}
			datos1 = comando[4].split("\\s+");

			if (!aviso.checkArgumentos(s, datos1, 3)) {
				return;
			}
		}

		if (!aviso.checkDNI(s, datos0[2].trim())) {
			return;
		}

		if (datos0[1].trim().equals("profesor")) {
			Profesor nuevoprofesor = null;
			if (!aviso.checkFechaProfe(s, datos1[1].trim())) {
				return;
			}
			if (datos1[2].trim().equals("titular")) {
				nuevoprofesor = new Titular(datos0[2].trim(), comando[1].trim(), comando[3].trim(), datos1[1].trim(),
						comando[5], horas);
				if (!aviso.checkNumHoras(s, nuevoprofesor)) {
					return;
				}
				if (!aviso.checkProfesorexiste(s, nuevoprofesor.getDNI())) {
					return;
				}
				totalprofesores.put(nuevoprofesor.getDNI(), nuevoprofesor);
			}
			if (datos1[2].trim().equals("asociado")) {
				nuevoprofesor = new Asociado(datos0[2].trim(), comando[1].trim(), comando[3].trim(), datos1[1].trim(),
						comando[5], horas);
				if (!aviso.checkNumHoras(s, nuevoprofesor)) {
					return;
				}
				if (!aviso.checkProfesorexiste(s, nuevoprofesor.getDNI())) {
					return;
				}
				totalprofesores.put(nuevoprofesor.getDNI(), nuevoprofesor);
			}
		}
		if (datos0[1].trim().equals("alumno")) {
			if (!aviso.checkFechaAlumno(s, datos1[1].trim(), datos1[2].trim())) {
				return;
			}
			Alumno nuevoalumno = new Alumno(datos0[2].trim(), comando[1].trim(), comando[3].trim(), datos1[1].trim(),
					datos1[2].trim());
			if (!aviso.checkAlumnoexiste(s, nuevoalumno.getDNI())) {
				return;
			}
			totalalumnos.put(nuevoalumno.getDNI(), nuevoalumno);

		}

	}

	/**
	 * Este método se encarga de asignar un grupo de una asignatura a un
	 * determinado profesor del sistema, considerando una serie de situaciones
	 * no permitidas por el sistema y provocando los avisos pertinentes a cada
	 * una de esas situaciones
	 * 
	 * @param comando
	 *            La línea del txt de ejecución donde viene la información
	 *            necesaria para la función
	 * @param aviso
	 *            Un objeto aviso para poder hacer saltar los diferentes avisos
	 *            en su txt
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	public static void asignaCargaDocente(String[] comando, Aviso aviso) throws IOException {
		String s = "asignacargadocente";
		if (!aviso.checkArgumentos(s, comando, 5))
			return;
		if (!aviso.checkProfesor(s, comando[1]))
			return;
		if (!aviso.checkAsignatura(s, comando[2]))
			return;
		if (!aviso.checkGrupo(s, comando[2], comando[3].charAt(0), Integer.parseInt(comando[4]))) {
			return;
		}
		Profesor profesorasig = totalprofesores.get(comando[1]);
		Asignatura asig_aux = getAsignatura(comando[2]);
		Grupo grupo_aux;

		if (!aviso.checkGrupoAsignado(s, Integer.parseInt(comando[4]), asig_aux.getID(), comando[3].charAt(0))) {
			return;
		}
		if (comando[3].charAt(0) == 'A') {
			grupo_aux = asig_aux.getGrupoA(Integer.parseInt(comando[4]));
		} else {
			grupo_aux = asig_aux.getGrupoB(Integer.parseInt(comando[4]));
		}
		if (!aviso.checkHorasAsig(s, profesorasig, grupo_aux)) {
			return;
		}
		if (!aviso.checkSolapeProfesor(s, asig_aux, profesorasig, grupo_aux)) {
			return;
		}
		Clase clase_aux = new Clase(asig_aux.getID(), asig_aux);
		if (comando[3].charAt(0) == 'A') {
			clase_aux.setGrupoA(grupo_aux);

		} else {
			clase_aux.setGrupoB(grupo_aux);
		}
		profesorasig.putClase(clase_aux.getID(), clase_aux);
	}

	/**
	 * Este método se encarga de matricular un alumno del sistema en una
	 * asignatura del sistema, considerando una serie de situaciones no
	 * permitidas por el sistema y provocando los avisos pertinentes a cada una
	 * de esas situaciones
	 * 
	 * @param comando
	 *            La línea del txt de ejecución donde viene la información
	 *            necesaria para la función
	 * @param aviso
	 *            Un objeto aviso para poder hacer saltar los diferentes avisos
	 *            en su txt
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	public static void matricular(String[] comando, Aviso aviso) throws IOException {
		String s = "matricula";
		if (!aviso.checkArgumentos(s, comando, 3)) {
			return;
		}
		if (!aviso.checkAlumno(s, comando[1])) {
			return;
		}
		if (!aviso.checkAsignatura(s, comando[2])) {
			return;
		}

		Alumno alumno_aux = totalalumnos.get(comando[1]);
		Asignatura asig_aux = getAsignatura(comando[2]);
		if (!aviso.checkYaMatriculado(s, alumno_aux, asig_aux.getID())) {
			return;
		}
		if (!aviso.checkPrerrequisitos(s, alumno_aux, asig_aux)) {
			return;
		}
		Clase clase_aux = new Clase(asig_aux.getID(), asig_aux);
		alumno_aux.putClase(clase_aux.getID(), clase_aux);

	}

	/**
	 * Este método se encarga crear un fichero txt y cubrirlo con el expediente
	 * de un alummno del sistema ordenando las asignaturas según su curso y,
	 * después, alfabéticamente, considerando una serie de situaciones no
	 * permitidas por el sistema y provocando los avisos pertinentes a cada una
	 * de esas situaciones
	 * 
	 * @param comando
	 *            La línea del txt de ejecución donde viene la información
	 *            necesaria para la función
	 * @param aviso
	 *            Un objeto aviso para poder hacer saltar los diferentes avisos
	 *            en su txt
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	public static void expediente(String[] comando, Aviso aviso) throws IOException {
		String s = "expediente";
		if (!aviso.checkArgumentos(s, comando, 3)) {
			return;
		}
		if (!aviso.checkAlumno(s, comando[1])) {
			return;
		}
		if (!aviso.checkExpediente(s, comando[1])) {
			return;
		}
		File archivo = new File(comando[2].trim());
		archivo.createNewFile();
		BufferedWriter cursor = new BufferedWriter(new FileWriter(archivo));
		Alumno alumno_aux = totalalumnos.get(comando[1]);
		ArrayList<Aprobada> arrayaprobadas = new ArrayList<Aprobada>(alumno_aux.getAprobadas().values());
		Collections.sort(arrayaprobadas);
		for (int i = 1; i < 5; i++) {
			for (Aprobada a : arrayaprobadas) {
				if (totalasignaturas.get(a.getID()).getCurso() == i) {
					cursor.write(i + ";" + totalasignaturas.get(a.getID()).getNombre() + ";"
							+ alumno_aux.getAprobadas().get(a.getID()).getNota() + ";"
							+ alumno_aux.getAprobadas().get(a.getID()).getCursoAcademico());
					cursor.newLine();
				}

			}
		}
		cursor.close();
	}

	/**
	 * Este método se encarga crear un fichero txt y cubrirlo con el horario de
	 * un profesor del sistema ordenando los grupos que tiene asignados
	 * cronol�gicamente, y considerando una serie de situaciones no permitidas
	 * por el sistema y provocando los avisos pertinentes a cada una de esas
	 * situaciones
	 * 
	 * @param comando
	 *            La línea del txt de ejecución donde viene la información
	 *            necesaria para la función
	 * @param aviso
	 *            Un objeto aviso para poder hacer saltar los diferentes avisos
	 *            en su txt
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	public static void calendarioProfesor(String[] comando, Aviso aviso) throws IOException {
		String s = "obtenercalendarioclases";
		if (!aviso.checkArgumentos(s, comando, 3)) {
			return;
		}
		if (!aviso.checkProfesor(s, comando[1])) {
			return;
		}
		if (!aviso.checkCalendario(s, comando[1])) {
			return;
		}
		File archivo = new File(comando[2].trim());
		archivo.createNewFile();
		BufferedWriter cursor = new BufferedWriter(new FileWriter(archivo));
		Profesor profe_aux = totalprofesores.get(comando[1]);

		char[] dias = { 'L', 'M', 'X', 'J', 'V' };
		for (int i = 0; i < 5; i++) {
			for (int j = 9; j < 19; j++) {
				for (Clase c : profe_aux.getAsignaturas().values()) {
					if (c.getGrupoB() == null) {
						if ((c.getGrupoA().getDia() == dias[i]) && (c.getGrupoA().getHorainicio() == j)) {
							cursor.write(dias[i] + ";" + j + ";" + totalasignaturas.get(c.getID()).getSiglas() + ";A;"
									+ c.getGrupoA().getID());
							cursor.newLine();
						}
					} else {
						if ((c.getGrupoB().getDia() == dias[i]) && (c.getGrupoB().getHorainicio() == j)) {
							cursor.write(dias[i] + ";" + j + ";" + totalasignaturas.get(c.getID()).getSiglas() + ";B;"
									+ c.getGrupoA().getID());
							cursor.newLine();
						}
					}
				}
			}
		}
		cursor.close();

	}

	/**
	 * Este método se encarga de asignar a una asignatura un determinado
	 * profesor titular del sistema como su coordinador, considerando una serie
	 * de situaciones no permitidas por el sistema y provocando los avisos
	 * pertinentes a cada una de esas situaciones
	 * 
	 * @param comando
	 *            La línea del txt de ejecución donde viene la información
	 *            necesaria para la función
	 * @param aviso
	 *            Un objeto aviso para poder hacer saltar los diferentes avisos
	 *            en su txt
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	public static void asignaCoordinador(String[] comando, Aviso aviso) throws IOException {
		String s = "asignacoordinador";
		if (!aviso.checkArgumentos(s, comando, 3))
			return;
		if (!aviso.checkProfesor(s, comando[1]))
			return;
		if (!aviso.checkTitular(s, comando[1]))
			return;
		if (!aviso.checkAsignatura(s, comando[2]))
			return;
		if (!aviso.checkCoordinador(s, comando[1]))
			return;
		for (Asignatura a : totalasignaturas.values()) {
			if (a.getSiglas().equals(comando[2])) {
				a.setCoordinador(comando[1]);
				break;
			}
		}
	}

	/**
	 * Este método se encarga de evaluar una asignatura para unos determinados
	 * alumnos y en un determinado curso acad�mico a partir de un fichero de
	 * entrada con los datos que necesita, considerando una serie de situaciones
	 * no permitidas por el sistema y provocando los avisos pertinentes a cada
	 * una de esas situaciones
	 * 
	 * @param comando
	 *            La línea del txt de ejecución donde viene la información
	 *            necesaria para la función
	 * @param aviso
	 *            Un objeto aviso para poder hacer saltar los diferentes avisos
	 *            en su txt
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	public static void evaluaAsignatura(String[] comando, Aviso aviso) throws IOException {
		String s = "evalua";
		if (!aviso.checkArgumentos(s, comando, 4))
			return;
		File archivo = new File(comando[3]);
		if (!aviso.checkAsignatura(s, comando[1]))
			return;
		if (!aviso.checkFile(s, archivo))
			return;
		if (!aviso.checkEvaluado(s, comando[1], comando[2]))
			return;
		BufferedReader notas = new BufferedReader(new FileReader(archivo));
		String linea;
		int numerolinea = 0;
		while ((linea = notas.readLine()) != null) {
			numerolinea++;
			String[] datos;
			linea = linea.trim();
			if (linea.startsWith("*"))
				continue;
			if (linea.equals(""))
				continue;
			datos = linea.split("\\s+");
			if (!aviso.checkAlumno(s, datos[0], numerolinea))
				continue;
			if (!aviso.checkMatriculado(s, datos[0], comando[1], numerolinea))
				continue;
			if (!aviso.checkNota(s, datos[1], numerolinea))
				continue;
			if (!aviso.checkNota(s, datos[2], numerolinea))
				continue;
			float notaA = Float.parseFloat(datos[1]);
			float notaB = Float.parseFloat(datos[2]);
			Alumno a = totalalumnos.get(datos[0]);
			int ID = 0;
			for (Asignatura aprobada : totalasignaturas.values()) {
				if (aprobada.getSiglas().equals(comando[1])) {
					ID = aprobada.getID();
					break;
				}
			}
			a.removeClase(ID);
			if ((notaA + notaB) >= 5) {
				a.putAprobada(ID, new Aprobada(ID, comando[2], notaA + notaB));
			}
		}
	}

	/**
	 * Este método actualiza la información en los ficheros de asignaturas.txt y
	 * de personas.txt con las modificaciones que se han producido debido a la
	 * ejecución del programa
	 * 
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	public static void printExit() throws IOException {
		BufferedWriter txt = null;
		try {
			txt = new BufferedWriter(new FileWriter("asignaturas.txt"));
		} catch (Exception e) {
			System.out.println("Fichero inexistente");
			System.exit(0);
		}
		if (!totalasignaturas.isEmpty()) {
			int t = totalasignaturas.size();
			int k = 0;
			for (Asignatura a : totalasignaturas.values()) {
				k++;
				txt.write(a.toString());
				if (!a.getPrerrequisitos().isEmpty()) {
					int tamaño = a.getPrerrequisitos().size();
					int contador = 0;
					for (int i : a.getPrerrequisitos()) {
						contador++;
						txt.write(new Integer(i).toString());
						if (contador != tamaño) {
							txt.write(", ");
						}
					}
				}
				txt.newLine();
				if (!a.getGruposA().isEmpty()) {
					int tamaño = a.getGruposA().size();
					int contador = 0;
					for (Grupo g : a.getGruposA().values()) {
						contador++;
						txt.write(g.toString());
						if (contador != tamaño) {
							txt.write("; ");
						}
					}
				}
				txt.newLine();
				if (!a.getGruposB().isEmpty()) {
					int tamaño = a.getGruposB().size();
					int contador = 0;
					for (Grupo g : a.getGruposB().values()) {
						contador++;
						txt.write(g.toString());
						if (contador != tamaño) {
							txt.write("; ");
						}
					}
				}
				txt.newLine();
				if (k != t)
					txt.write("*\n");
			}
		}
		txt.close();
		try {
			txt = new BufferedWriter(new FileWriter("personas.txt"));
		} catch (Exception e) {
			System.out.println("Fichero inexistente");
			System.exit(0);
		}
		int k = 0;
		int tamaño = totalprofesores.size() + totalalumnos.size();
		if (!totalprofesores.isEmpty()) {
			for (Profesor p : totalprofesores.values()) {
				k++;
				txt.write("profesor\n");
				if (p instanceof Titular) {
					txt.write(((Titular) p).toString());
				} else {
					txt.write(((Asociado) p).toString());
				}
				if (!p.getAsignaturas().isEmpty()) {
					int t = p.getAsignaturas().size();
					int contador = 0;
					for (Clase c : p.getAsignaturas().values()) {
						contador++;
						txt.write(c.toString());
						if (contador != t) {
							txt.write("; ");
						}
					}
				}
				txt.newLine();
				if (k != tamaño) {
					txt.write("*\n");
				}
			}
		}
		if (!totalalumnos.isEmpty()) {
			for (Alumno a : totalalumnos.values()) {
				k++;
				txt.write("alumno\n");
				txt.write(((Persona) a).toString());
				txt.write(((Persona) a).convertirFecha(a.getIngreso()) + "\n");
				if (!a.getAprobadas().isEmpty()) {
					int t = a.getAprobadas().size();
					int contador = 0;
					for (Aprobada c : a.getAprobadas().values()) {
						contador++;
						txt.write(c.toString());
						if (contador != t) {
							txt.write("; ");
						}
					}
				}
				txt.newLine();
				if (!a.getRecibidas().isEmpty()) {
					int t = a.getRecibidas().size();
					int contador = 0;
					for (Clase c : a.getRecibidas().values()) {
						contador++;
						txt.write(c.toString());
						if (contador != t) {
							txt.write("; ");
						}
					}
				}
				txt.newLine();
				if (k != tamaño) {
					txt.write("*\n");
				}
			}
		}
		txt.close();
	}

	/**
	 * Este método se encarga de asignar a un alumno ya matriculado en una
	 * asignatura un grupo A o B de esa asignatura en concreto, considerando una
	 * serie de situaciones no permitidas por el sistema y provocando los avisos
	 * pertinentes a cada una de esas situaciones
	 * 
	 * @param comando
	 *            La línea del txt de ejecución donde viene la información
	 *            necesaria para la función
	 * @param aviso
	 *            Un objeto aviso para poder hacer saltar los diferentes avisos
	 *            en su txt
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	public static void asignaGrupo(String[] comando, Aviso aviso) throws IOException {
		String s = "asignagrupo";
		if (!aviso.checkArgumentos(s, comando, 5))
			return;
		if (!aviso.checkAlumno(s, comando[1]))
			return;
		if (!aviso.checkAsignatura(s, comando[2]))
			return;
		if (!aviso.checkMatriculado(s, comando[1], comando[2]))
			return;
		if (!aviso.checkGrupo(s, comando[2], comando[3].charAt(0), Integer.parseInt(comando[4])))
			return;
		Asignatura a = Proyecto37.getAsignatura(comando[2]);
		Alumno alumno = Proyecto37.totalalumnos.get(comando[1]);
		Grupo grupo;
		if (comando[3].charAt(0) == 'A')
			grupo = a.getGrupoA(Integer.parseInt(comando[4]));
		else {
			grupo = a.getGrupoB(Integer.parseInt(comando[4]));
		}
		if (!aviso.checkSolape(s, a, alumno, grupo))
			return;
		if (comando[3].charAt(0) == 'A')
			alumno.getClase(a.getID()).setGrupoA(grupo);
		else {
			alumno.getClase(a.getID()).setGrupoB(grupo);
		}
	}

	/**
	 * Este metodo busca una asignatura por sus siglas en el total del centro
	 * 
	 * @param siglas
	 *            Corresponde a las siglas de la asignatura a buscar
	 * @return Asignatura
	 */
	public static Asignatura getAsignatura(String siglas) {
		for (Asignatura a : Proyecto37.totalasignaturas.values()) {
			if (a.getSiglas().equals(siglas)) {
				return a;
			}
		}
		return null;
	}

}