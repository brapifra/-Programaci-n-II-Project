package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Calendar;
import java.util.GregorianCalendar;
import docencia.Aprobada;
import docencia.Asignatura;
import docencia.Grupo;
import docencia.Clase;
import main.Proyecto37;
import personas.Profesor;
import personas.Alumno;
import personas.Asociado;
import personas.Persona;
import personas.Titular;

/**
 * La clase Aviso incluye todos los métodos y atributos necesarios para
 * comprobar e imprimir en caso necesario en un fichero cualquier aviso
 * relacionado con el mal funcionamiento de los comandos incluidos en
 * ejecucion.txt
 * 
 * @author Brais Piñeiro y Jorge Pose
 */
public class Aviso {
	// Atributos
	public static final HashMap<String, String> siglas = new HashMap<String, String>() {
		{
			put("insertapersona", "IP");
			put("asignacoordinador", "ACOORD");
			put("asignacargadocente", "ACDOC");
			put("matricula", "MAT");
			put("asignagrupo", "AGRUPO");
			put("obtenercalendarioclases", "CALENP");
			put("expediente", "EXP");
			put("evalua", "EVALUA");
		}
	};
	private BufferedWriter txt;

	/**
	 * El �nico constructor de esta clase se encarga de crear el archivo
	 * "avisos.txt" en caso de que no existiese, o de abrirlo en caso contrario.
	 * 
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	public Aviso() throws IOException {
		File archivo = new File("avisos.txt");
		if (!archivo.exists()) {
			archivo.createNewFile();
		}
		txt = new BufferedWriter(new FileWriter(archivo, true));
	}

	/**
	 * Este método se encarga de imprimir el aviso adecuadamente en el fichero
	 * "avisos.txt".
	 * 
	 * @param comando
	 *            Corresponde con el comando de donde proviene el aviso.
	 * @param aviso
	 *            Corresponde con el texto de aviso que se imprimir�.
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	public void printAviso(String comando, String aviso) throws IOException {
		if (siglas.containsKey(comando)) {
			txt.append(siglas.get(comando) + " -- " + aviso);
		} else {
			txt.append(" -- " + aviso);
		}
		txt.newLine();
	}

	/**
	 * Este método cierra el fichero "avisos.txt", se llama �nicamente al final
	 * del programa.
	 * 
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	public void closeAviso() throws IOException {
		txt.close();
	}

	/**
	 * Este método comprueba si un comando tiene el n�mero de argumentos
	 * correcto.
	 * 
	 * @param comando
	 *            Corresponde con el comando que queremos comprobar.
	 * @param argumentos
	 *            Corresponde con el array de tipo String que incluye todos los
	 *            argumentos.
	 * @param num
	 *            Corresponde con el n�mero de argumentos que debe tener el
	 *            comando.
	 * @return boolean Devuelve un false en caso de que se imprima el aviso, y
	 *         true en caso contrario.
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	boolean checkArgumentos(String comando, String[] argumentos, int num) throws IOException {
		if (argumentos.length != num) {
			printAviso(comando, "N�mero argumentos incorrecto");
			return false;
		}
		return true;
	}

	/**
	 * Este método comprueba si existe un profesor con cierto DNI en la lista
	 * todos los profesores existentes.
	 * 
	 * @param comando
	 *            Corresponde con el comando para el que queremos comprobar el
	 *            aviso.
	 * @param DNI
	 *            Corresponde con el DNI del profesor a comprobar
	 * @return boolean Devuelve un false en caso de que se imprima el aviso, y
	 *         true en caso contrario.
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	boolean checkProfesor(String comando, String DNI) throws IOException {
		if (!Proyecto37.totalprofesores.containsKey(DNI)) {
			printAviso(comando, "Profesor inexistente");
			return false;
		}
		return true;
	}

	/**
	 * Este método comprueba si existe un alumno con cierto DNI obtenido de un
	 * fichero, si este no existe se imprimir� el aviso con la línea del fichero
	 * donde se encuentra el DNI err�neo.
	 * 
	 * @param comando
	 *            Corresponde con el comando para el que queremos comprobar el
	 *            aviso.
	 * @param DNI
	 *            Corresponde con el DNI del alumno a comprobar
	 * @param numerolinea
	 *            Corresponde con el n�mero de línea del fichero donde se
	 *            encuentra el DNI pasado.
	 * @return boolean Devuelve un false en caso de que se imprima el aviso, y
	 *         true en caso contrario.
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	boolean checkAlumno(String comando, String DNI, int numerolinea) throws IOException {
		if (!Proyecto37.totalalumnos.containsKey(DNI)) {
			printAviso(comando, "Error en línea " + numerolinea + ": Alumno inexistente: " + DNI);
			return false;
		}
		return true;
	}

	/**
	 * Este método comprueba si existe un alumno con cierto DNI.
	 * 
	 * @param comando
	 *            Corresponde con el comando para el que queremos comprobar el
	 *            aviso.
	 * @param DNI
	 *            Corresponde con el DNI del alumno a comprobar.
	 * @return boolean Devuelve un false en caso de que se imprima el aviso, y
	 *         true en caso contrario.
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	boolean checkAlumno(String comando, String DNI) throws IOException {
		if (!Proyecto37.totalalumnos.containsKey(DNI)) {
			printAviso(comando, "Alumno inexistente");
			return false;
		}
		return true;
	}

	/**
	 * Este método comprueba si un profesor con cierto DNI es titular.
	 * 
	 * @param comando
	 *            Corresponde con el comando para el que queremos comprobar el
	 *            aviso.
	 * @param DNI
	 *            Corresponde con el DNI del profesor a comprobar.
	 * @return boolean Devuelve un false en caso de que se imprima el aviso, y
	 *         true en caso contrario.
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	boolean checkTitular(String comando, String DNI) throws IOException {
		if (Proyecto37.totalprofesores.get(DNI) instanceof Asociado) {
			printAviso(comando, "Profesor no Titular");
			return false;
		}
		return true;
	}

	/**
	 * Este método comprueba si una asignatura con ciertas siglas existe.
	 * 
	 * @param comando
	 *            Corresponde con el comando para el que queremos comprobar el
	 *            aviso.
	 * @param siglas
	 *            Corresponde con las siglas de la asignatura a comprobar.
	 * @return boolean Devuelve un false en caso de que se imprima el aviso, y
	 *         true en caso contrario.
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	boolean checkAsignatura(String comando, String siglas) throws IOException {
		Asignatura a = Proyecto37.getAsignatura(siglas);
		if (a == null) {
			printAviso(comando, "Asignatura Inexistente");
			return false;
		}
		return true;
	}

	/**
	 * Este método comprueba si un profesor con cierto DNI ya es coordinador de
	 * 2 materias.
	 * 
	 * @param comando
	 *            Corresponde con el comando para el que queremos comprobar el
	 *            aviso.
	 * @param DNI
	 * @return boolean Devuelve un false en caso de que se imprima el aviso, y
	 *         true en caso contrario.
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	boolean checkCoordinador(String comando, String DNI) throws IOException {
		int coordinador = 0;
		for (Asignatura a : Proyecto37.totalasignaturas.values()) {
			if (a.getCoordinador() == null)
				continue;
			if (a.getCoordinador().equals(DNI))
				coordinador++;
		}
		if (coordinador >= 2) {
			printAviso(comando, "Profesor ya es coordinador de 2 materias");
			return false;
		}
		return true;
	}

	/**
	 * Este método comprueba si un fichero de notas existe o no.
	 * 
	 * @param comando
	 *            Corresponde con el comando para el que queremos comprobar el
	 *            aviso.
	 * @param archivo
	 *            Corresponde con el archivo que queremos comprobar.
	 * @return boolean Devuelve un false en caso de que se imprima el aviso, y
	 *         true en caso contrario.
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	boolean checkFile(String comando, File archivo) throws IOException {
		if (!archivo.exists()) {
			printAviso(comando, "Fichero de notas inexistente");
			return false;
		}
		return true;
	}

	/**
	 * Este método comprueba si una asignatura ya fu� evaluada en cierto curso
	 * acad�mico (formato aa/aa).
	 * 
	 * @param comando
	 *            Corresponde con el comando para el que queremos comprobar el
	 *            aviso.
	 * @param siglas
	 *            Corresponde con las siglas de la asignatura a comprobar.
	 * @param cursoAcademico
	 *            Corresponde con el curso acad�mico a comprobar.
	 * @return boolean Devuelve un false en caso de que se imprima el aviso, y
	 *         true en caso contrario.
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */

	boolean checkEvaluado(String comando, String siglas, String cursoAcademico) throws IOException {
		int ID = 0;
		ID = Proyecto37.getAsignatura(siglas).getID();
		for (Alumno a : Proyecto37.totalalumnos.values()) {
			if (a.getAprobadas().containsKey(ID)) {
				Aprobada asignatura = a.getAprobadas().get(ID);
				if (asignatura.getCursoAcademico().equals(cursoAcademico)) {
					printAviso(comando, "Asignatura ya evaluada en ese curso acad�mico");
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Este método comprueba si un alumno con cierto DNI obtenido de un fichero
	 * est� matriculado en una asignatura con ciertas siglas, si no es as� se
	 * imprime tambi�n en el aviso el n�mero de línea del fichero del cual se
	 * obtuvo el DNI.
	 * 
	 * @param comando
	 *            Corresponde con el comando para el que queremos comprobar el
	 *            aviso.
	 * @param DNI
	 *            Corresponde con el DNI del alumno a comprobar.
	 * @param siglas
	 *            Corresponde con las siglas de la asignatura a comprobar.
	 * @param numerolinea
	 *            Corresponde con el n�mero de línea del fichero donde se
	 *            encuentra el DNI pasado.
	 * @return boolean Devuelve un false en caso de que se imprima el aviso, y
	 *         true en caso contrario.
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */

	boolean checkMatriculado(String comando, String DNI, String siglas, int numerolinea) throws IOException {
		int ID = 0;
		ID = Proyecto37.getAsignatura(siglas).getID();
		Alumno a = Proyecto37.totalalumnos.get(DNI);
		if (!a.containsClase(ID)) {
			printAviso(comando, "Error en línea " + numerolinea + ": Alumno no matriculado: " + DNI);
			return false;
		}
		return true;
	}

	/**
	 * Este método comprueba si un alumno con cierto DNI est� matriculado en
	 * cierta asignatura con unas siglas determinadas.
	 * 
	 * @param comando
	 *            Corresponde con el comando para el que queremos comprobar el
	 *            aviso.
	 * @param DNI
	 *            Corresponde con el DNI del alumno a comprobar.
	 * @param siglas
	 *            Corresponde con las siglas de la asignatura a comprobar.
	 * @return boolean Devuelve un false en caso de que se imprima el aviso, y
	 *         true en caso contrario.
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */

	boolean checkMatriculado(String comando, String DNI, String siglas) throws IOException {
		int ID = 0;
		ID = Proyecto37.getAsignatura(siglas).getID();
		Alumno a = Proyecto37.totalalumnos.get(DNI);
		if (!a.containsClase(ID)) {
			printAviso(comando, "Alumno no matriculado");
			return false;
		}
		return true;
	}

	/**
	 * Este método comprueba si un grupo (con una ID espec�fica) de una
	 * asignatura con unas siglas determinadas existe.
	 * 
	 * @param comando
	 *            Corresponde con el comando para el que queremos comprobar el
	 *            aviso.
	 * @param siglas
	 *            Corresponde con las siglas de la asignatura a comprobar.
	 * @param tipogrupo
	 *            Corresponde con el tipo de grupo que queremos comprobar, se
	 *            espera que sea A o B.
	 * @param IDgrupo
	 *            Corresponde con la ID del grupo a comprobar.
	 * @return boolean Devuelve un false en caso de que se imprima el aviso, y
	 *         true en caso contrario.
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */

	boolean checkGrupo(String comando, String siglas, char tipogrupo, int IDgrupo) throws IOException {
		Asignatura a = Proyecto37.getAsignatura(siglas);
		if (tipogrupo != 'A' && tipogrupo != 'B') {
			printAviso(comando, "Tipo de grupo incorrecto");
			return false;
		}
		if (tipogrupo == 'A') {
			if (!a.getGruposA().containsKey(IDgrupo)) {
				printAviso(comando, "Grupo Inexistente");
				return false;
			}
		}
		if (tipogrupo == 'B') {
			if (!a.getGruposB().containsKey(IDgrupo)) {
				printAviso(comando, "Grupo Inexistente");
				return false;
			}
		}
		return true;
	}

	/**
	 * Este método comprueba si un grupo con una ID concreta de una asignatura
	 * tambi�n especificada por su ID ya ha sido asignado a alg�n profesor.
	 * 
	 * @param comando
	 *            Corresponde con el comando para el que queremos comprobar el
	 *            aviso.
	 * @param IDGrupo
	 *            Corresponde con la ID del grupo a comprobar.
	 * @param IDAsignatura
	 *            Corresponde con la ID de la asignatura a comprobar.
	 * @param tipogrupo
	 *            Corresponde con el tipo de grupo que queremos comprobar, se
	 *            espera que sea A o B.
	 * @return boolean Devuelve un false en caso de que se imprima el aviso, y
	 *         true en caso contrario.
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */

	boolean checkGrupoAsignado(String comando, int IDGrupo, int IDAsignatura, char tipogrupo) throws IOException {
		for (Profesor profesor : Proyecto37.totalprofesores.values()) {
			HashMap<Integer, Clase> mapa_aux = profesor.getAsignaturas();
			for (Clase clase_aux : mapa_aux.values()) {

				if (clase_aux.getID() == IDAsignatura) {

					if (tipogrupo == 'A') {

						if ((clase_aux.getGrupoA()).getID() == IDGrupo) {
							printAviso(comando, "Grupo ya asignado");
							return false;
						}
					}

					if (tipogrupo == 'B') {

						if ((clase_aux.getGrupoB()).getID() == IDGrupo) {
							printAviso(comando, "Grupo ya asignado");
							return false;
						}
					}

				}
			}
		}

		return true;
	}

	/**
	 * Este método comprueba si un grupo de una asignatura genera solape sobre
	 * el horario que ya tiene el alumno que se va a asignar a ese grupo.
	 * 
	 * @param comando
	 *            Corresponde con el comando para el que queremos comprobar el
	 *            aviso.
	 * @param a
	 *            Corresponde con la Asignatura con el horario a comprobar.
	 * @param alumno
	 *            Corresponde con el Alumno con el horario a comprobar.
	 * @param grupo
	 *            Corresponde con el grupo que queremos comprobar.
	 * 
	 * @return boolean Devuelve un false en caso de que se imprima el aviso, y
	 *         true en caso contrario.
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	boolean checkSolape(String comando, Asignatura a, Alumno alumno, Grupo grupo) throws IOException {
		for (Clase c : alumno.getRecibidas().values()) {
			Grupo g;
			if (c.getGrupoA() != null) {
				g = c.getGrupoA();
				if (grupo.getHorainicio() >= g.getHorainicio() && grupo.getHorainicio() < g.getHorafin()) {
					printAviso(comando, "Se genera solape");
					return false;
				}
				if (grupo.getHorafin() > g.getHorainicio() && grupo.getHorafin() <= g.getHorafin()) {
					printAviso(comando, "Se genera solape");
					return false;
				}
			}
			if (c.getGrupoB() != null) {
				g = c.getGrupoB();
				if (grupo.getHorainicio() >= g.getHorainicio() && grupo.getHorainicio() < g.getHorafin()) {
					printAviso(comando, "Se genera solape");
					return false;
				}
				if (grupo.getHorafin() > g.getHorainicio() && grupo.getHorafin() <= g.getHorafin()) {
					printAviso(comando, "Se genera solape");
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Este método comprueba si un grupo de una asignatura genera solape sobre
	 * el horario que ya tiene el profesor que se va a asignar a ese grupo.
	 * 
	 * @param comando
	 *            Corresponde con el comando para el que queremos comprobar el
	 *            aviso.
	 * @param a
	 *            Corresponde con la Asignatura con el horario a comprobar.
	 * @param profe
	 *            Corresponde con el Profesor con el horario a comprobar.
	 * @param grupo
	 *            Corresponde con el grupo que queremos comprobar.
	 * 
	 * @return boolean Devuelve un false en caso de que se imprima el aviso, y
	 *         true en caso contrario.
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	boolean checkSolapeProfesor(String comando, Asignatura a, Profesor profe, Grupo grupo) throws IOException {
		for (Clase c : profe.getAsignaturas().values()) {
			Grupo g;
			if (c.getGrupoA() != null) {
				g = c.getGrupoA();
				if (grupo.getHorainicio() >= g.getHorainicio() && grupo.getHorainicio() < g.getHorafin()) {
					printAviso(comando, "Se genera solape");
					return false;
				}
				if (grupo.getHorafin() > g.getHorainicio() && grupo.getHorafin() <= g.getHorafin()) {
					printAviso(comando, "Se genera solape");
					return false;
				}
			}
			if (c.getGrupoB() != null) {
				g = c.getGrupoB();
				if (grupo.getHorainicio() >= g.getHorainicio() && grupo.getHorainicio() < g.getHorafin()) {
					printAviso(comando, "Se genera solape");
					return false;
				}
				if (grupo.getHorafin() > g.getHorainicio() && grupo.getHorafin() <= g.getHorafin()) {
					printAviso(comando, "Se genera solape");
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Este método comprueba si la nota de un grupo, tanto A como B, est�
	 * comprendida entre 0 y 5.
	 * 
	 * @param comando
	 *            Corresponde con el comando para el que queremos comprobar el
	 *            aviso.
	 * @param nota
	 *            Corresponde con la nota a comprobar.
	 * @param numerolinea
	 *            Corresponde con la línea del fichero donde saltar�a el aviso
	 *            (si fuese necesario).
	 * @return boolean Devuelve un false en caso de que se imprima el aviso, y
	 *         true en caso contrario.
	 * 
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	boolean checkNota(String comando, String nota, int numerolinea) throws IOException {
		float notanum = -1;
		try {
			notanum = Float.parseFloat(nota);
		} catch (Exception e) {
			printAviso(comando, "Error en línea " + numerolinea + ": Nota grupo A/B incorrecta");
			return false;
		}
		if ((notanum > 5) || (notanum < 0)) {
			printAviso(comando, "Error en línea " + numerolinea + ": Nota grupo A/B incorrecta");
			return false;
		}
		return true;
	}

	/**
	 * Este método comprueba que el DNI que le pasan como parametro sigue el
	 * formato de 8 d�gitos seguidos de una letra may�scula.
	 * 
	 * @param comando
	 *            Corresponde con el comando para el que queremos comprobar el
	 *            aviso.
	 * @param dni
	 *            Corresponde con el DNI para comprobar si sigue el formato
	 * @return boolean Devuelve un false en caso de que se imprima el aviso, y
	 *         true en caso contrario.
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	boolean checkDNI(String comando, String dni) throws IOException {
		if (dni.length() != 9) {
			printAviso(comando, "DNI incorrecto");
			return false;
		}
		for (int i = 0; i < 8; i++) {
			if ((dni.charAt(i)) < '0' || dni.charAt(i) > '9') {
				printAviso(comando, "DNI incorrecto");
				return false;
			}
		}
		if (dni.charAt(8) < 'A' || dni.charAt(8) > 'Z') {
			printAviso(comando, "DNI incorrecto");
			return false;
		}
		return true;
	}

	/**
	 * Este método comprueba tanto la fecha de nacimiento como la de matricula
	 * de un alumno. Comprueba que las fechas sean correctas en s� mismas (no
	 * permite introducir el d�a 32 de marzo p.e.), que no se pasen de los
	 * m�rgenes establecidos (01/01/1995 y 01/01/2020) y que entre ellas no haya
	 * menos de 15 a�os o m�s de 65.
	 * 
	 * @param comando
	 *            Corresponde con el comando para el que queremos comprobar el
	 *            aviso.
	 * @param fechanac
	 *            Corresponde con la fecha de nacimiento (como String dd/mm/aa).
	 * @param fechamatric
	 *            Corresponde con la fecha de matr�cula (como String dd/mm/aa).
	 * @return boolean Devuelve un false en caso de que se imprima el aviso, y
	 *         true en caso contrario.
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	boolean checkFechaAlumno(String comando, String fechanac, String fechamatric) throws IOException {
		GregorianCalendar fecha1 = Persona.convertirFecha(fechanac);
		GregorianCalendar fecha2 = Persona.convertirFecha(fechamatric);
		GregorianCalendar fechainf = new GregorianCalendar(1950, 0, 1);
		GregorianCalendar fechasup = new GregorianCalendar(2020, 0, 1);
		fecha1.setLenient(false);
		fecha2.setLenient(false);
		try {
			fecha1.getTime();
			fecha2.getTime();

		} catch (IllegalArgumentException e) {
			printAviso(comando, "Fecha Incorrecta");
			return false;
		}

		if (fecha1.get(Calendar.YEAR) < fechainf.get(Calendar.YEAR)
				|| fecha1.get(Calendar.YEAR) >= fechasup.get(Calendar.YEAR)) {
			printAviso(comando, "Fecha Incorrecta");
			return false;
		}
		if (fecha2.get(Calendar.YEAR) < fechainf.get(Calendar.YEAR)
				|| fecha2.get(Calendar.YEAR) >= fechasup.get(Calendar.YEAR)) {
			printAviso(comando, "Fecha Incorrecta");
			return false;
		}
		int anho1 = fecha2.get(Calendar.YEAR);
		int anho2 = fecha1.get(Calendar.YEAR);
		float diaanho1 = fecha2.get(Calendar.DAY_OF_YEAR);
		float diaanho2 = fecha1.get(Calendar.DAY_OF_YEAR);
		if ((diaanho1 % 4 == 0) && ((diaanho1 % 100 != 0) || (diaanho1 % 400 == 0))) {
			diaanho1 /= 366;
		} else
			diaanho1 /= 365;
		if ((diaanho2 % 4 == 0) && ((diaanho2 % 100 != 0) || (diaanho2 % 400 == 0))) {
			diaanho2 /= 366;
		} else
			diaanho2 /= 365;
		float diferencia = (diaanho1 + anho1) - (diaanho2 + anho2);

		if (diferencia < 15) {
			printAviso(comando, "Fecha de Ingreso Incorrecta");
			return false;
		}
		if (diferencia > 65) {
			printAviso(comando, "Fecha de Ingreso Incorrecta");
			return false;
		}

		return true;

	}

	/**
	 * Este método comprueba la fecha de nacimiento de un profesor. Comprueba
	 * que sea correcta en s� misma (no permite introducir el d�a 32 de marzo
	 * p.e.) y que no se pase de los m�rgenes establecidos (01/01/1995 y
	 * 01/01/2020).
	 * 
	 * @param comando
	 *            Corresponde con el comando para el que queremos comprobar el
	 *            aviso.
	 * @param fechanac
	 *            Corresponde con la fecha de nacimiento (como String dd/mm/aa).
	 * @return boolean Devuelve un false en caso de que se imprima el aviso, y
	 *         true en caso contrario.
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	boolean checkFechaProfe(String comando, String fecha) throws IOException {
		GregorianCalendar fecha1 = Persona.convertirFecha(fecha);
		GregorianCalendar fechainf = new GregorianCalendar(1950, 0, 1);
		GregorianCalendar fechasup = new GregorianCalendar(2020, 0, 1);
		fecha1.setLenient(false);

		try {
			fecha1.getTime();

		} catch (IllegalArgumentException e) {
			printAviso(comando, "Fecha Incorrecta");
			return false;
		}

		if (fecha1.get(Calendar.YEAR) < fechainf.get(Calendar.YEAR)
				|| fecha1.get(Calendar.YEAR) >= fechasup.get(Calendar.YEAR)) {
			printAviso(comando, "Fecha Incorrecta");
			return false;
		}

		return true;
	}

	/**
	 * Este método comprueba que el n�mero de horas de un profesor no exceda el
	 * m�ximo que tiene asignado según su categor�a (20 para titulares y 18 para
	 * asociados).
	 * 
	 * @param comando
	 *            Corresponde con el comando para el que queremos comprobar el
	 *            aviso.
	 * @param profe
	 *            Corresponde al profesor a evaluar su n�mero de horas
	 * @return boolean Devuelve un false en caso de que se imprima el aviso, y
	 *         true en caso contrario.
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	boolean checkNumHoras(String comando, Profesor profe) throws IOException {
		if (profe instanceof Titular) {
			if (((Titular) profe).getHorasdocencia() < 0
					|| ((Titular) profe).getHorasdocencia() > Titular.horasdocenciamax) {
				printAviso(comando, "Numero de horas incorrecto");
				return false;
			}

		}
		if (profe instanceof Asociado) {
			if (((Asociado) profe).getHorasdocencia() < 0
					|| ((Asociado) profe).getHorasdocencia() > Asociado.horasdocenciamax) {
				printAviso(comando, "Numero de horas incorrecto");
				return false;
			}
		}

		return true;
	}

	/**
	 * Este método comprueba si ya existe un profesor con un cierto DNI en la
	 * lista todos los profesores existentes.
	 * 
	 * @param comando
	 *            Corresponde con el comando para el que queremos comprobar el
	 *            aviso.
	 * @param DNI
	 *            Corresponde con el DNI del profesor a comprobar
	 * @return boolean Devuelve un false en caso de que se imprima el aviso, y
	 *         true en caso contrario.
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	boolean checkProfesorexiste(String comando, String DNI) throws IOException {
		if (Proyecto37.totalprofesores.containsKey(DNI)) {
			printAviso(comando, "Profesor ya existente");
			return false;
		}

		return true;
	}

	/**
	 * Este método comprueba si ya existe un alumno con un cierto DNI en la
	 * lista todos los alumnos existentes.
	 * 
	 * @param comando
	 *            Corresponde con el comando para el que queremos comprobar el
	 *            aviso.
	 * @param DNI
	 *            Corresponde con el DNI del alumno a comprobar
	 * @return boolean Devuelve un false en caso de que se imprima el aviso, y
	 *         true en caso contrario.
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	boolean checkAlumnoexiste(String comando, String DNI) throws IOException {
		if (Proyecto37.totalalumnos.containsKey(DNI)) {
			printAviso(comando, "Alumno ya existente");
			return false;
		}

		return true;
	}

	/**
	 * Este método comprueba que el n�mero de horas de un profesor no exceda el
	 * m�ximo que tiene asignado según su categor�a (20 para titulares y 18 para
	 * asociados) al asignarle un nuevo grupo a su carga docente.
	 * 
	 * @param comando
	 *            Corresponde con el comando para el que queremos comprobar el
	 *            aviso.
	 * @param profe
	 *            Corresponde al profesor a evaluar su n�mero de horas
	 * @param grupo
	 *            Corresponde al grupo que se va a asignar al profesor en
	 *            cuesti�n
	 * @return boolean Devuelve un false en caso de que se imprima el aviso, y
	 *         true en caso contrario.
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	boolean checkHorasAsig(String comando, Profesor profe, Grupo grupo) throws IOException {
		if (profe instanceof Titular) {
			int h = ((Titular) profe).getHorasdocencia();
			if ((h + grupo.getHorafin() - grupo.getHorainicio()) > Titular.horasdocenciamax) {
				printAviso(comando, "Horas asignables superior al maximo");
				return false;
			}
		}
		if (profe instanceof Asociado) {
			int h = ((Asociado) profe).getHorasdocencia();
			if ((h + grupo.getHorafin() - grupo.getHorainicio()) > Asociado.horasdocenciamax) {
				printAviso(comando, "Horas asignables superior al maximo");
				return false;
			}
		}
		return true;
	}

	/**
	 * Este método comprueba si un alumno en concreto ya est� matriculado en una
	 * asignatura
	 * 
	 * @param comando
	 *            Corresponde con el comando para el que queremos comprobar el
	 *            aviso.
	 * @param alumno
	 *            Corresponde con el alumno a comprobar
	 * @param ID
	 *            Corresponde con el ID de la asignatura a comprobar
	 * @return boolean Devuelve un false en caso de que se imprima el aviso, y
	 *         true en caso contrario.
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	boolean checkYaMatriculado(String comando, Alumno alumno, int ID) throws IOException {
		if (alumno.containsClase(ID)) {
			printAviso(comando, "Ya es alumno de la asignatura indicada");
			return false;
		}
		return true;
	}

	/**
	 * Este metodo comprueba si un alumno dispone de los prerrequisitos
	 * necesarios para matricularse en una determinada asignatura.
	 * 
	 * @param comando
	 *            Corresponde con el comando para el que queremos comprobar el
	 *            aviso.
	 * @param alumno
	 *            Corresponde con el alumno a comprobar sus prerrequisitos
	 * @param asig
	 *            Corresponde con la asignatura a matricular
	 * @return boolean Devuelve un false en caso de que se imprima el aviso, y
	 *         true en caso contrario.
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	boolean checkPrerrequisitos(String comando, Alumno alumno, Asignatura asig) throws IOException {
		for (int prerq : asig.getPrerrequisitos()) {
			if (!alumno.getAprobadas().values().contains(prerq)) {
				printAviso(comando, "No cumple requisitos");
				return false;
			}
		}

		return true;
	}

	/**
	 * Este metodo comprueba si en el expediente de un alumno no figura ninguna
	 * asignatura aprobada
	 * 
	 * @param comando
	 *            Corresponde con el comando para el que queremos comprobar el
	 *            aviso.
	 * @param dni
	 *            Corresponde con el DNI del alumno a comprobar su expediente
	 * @return boolean Devuelve un false en caso de que se imprima el aviso, y
	 *         true en caso contrario.
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	boolean checkExpediente(String comando, String dni) throws IOException {
		if (Proyecto37.totalalumnos.get(dni).getAprobadas().isEmpty()) {
			printAviso(comando, "Expediente vacio");
			return false;
		}
		return true;
	}

	/**
	 * Este metodo comprueba si en el calendario de un profesor no figura
	 * ninguna asignacion
	 * 
	 * @param comando
	 *            Corresponde con el comando para el que queremos comprobar el
	 *            aviso.
	 * @param dni
	 *            Corresponde con el DNI del profesor a comprobar sus
	 *            asignaciones
	 * @return boolean Devuelve un false en caso de que se imprima el aviso, y
	 *         true en caso contrario.
	 * @throws IOException
	 *             Excepción que se lanzaría en caso de errores relacionados con
	 *             la escritura y lectura de ficheros.
	 */
	boolean checkCalendario(String comando, String dni) throws IOException {
		if (Proyecto37.totalprofesores.get(dni).getAsignaturas().isEmpty()) {
			printAviso(comando, "Profesor sin asignaciones");
			return false;
		}
		return true;
	}
}