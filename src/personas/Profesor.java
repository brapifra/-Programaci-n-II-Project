package personas;

import java.util.HashMap;
import docencia.Clase;

/**
 * La clase Profesor incluye todos los atributos relacionados con un profesor,
 * herendando por este motivo de la clase Persona. Se trata de una clase
 * abstracta debido a que no puede existir un profesor sin ser Titular o
 * Asociado, por ello para instanciar a un objecto de tipo Profesor habr� que
 * instanciar antes un Titular o un Asociado.
 * 
 * @author Brais Piñeiro y Jorge Pose
 * @see personas.Titular
 * @see personas.Asociado
 * @see personas.Persona
 */
public abstract class Profesor extends Persona {
	// Atributos
	private String departamento;
	private HashMap<Integer, Clase> asignaturas_impartidas = new HashMap<Integer, Clase>();

	/**
	 * Este es el �nico constructor disponible de la clase Profesor, con el cual
	 * instanciamos dicha clase.
	 * 
	 * @param DNI
	 *            Corresponde con el DNI del nuevo Profesor a instanciar, es
	 *            usado para llamar al constructor de la clase padre Persona.
	 * @param nombre
	 *            Corresponde con el nombre del nuevo Profesor a instanciar, es
	 *            usado para llamar al constructor de la clase padre Persona.
	 * @param apellidos
	 *            Corresponde con los apellidos del nuevo Profesor a instanciar,
	 *            es usado para llamar al constructor de la clase padre Persona.
	 * @param nacimiento
	 *            Corresponde con la fecha de nacimiento del nuevo Profesor a
	 *            instanciar, es usado para llamar al constructor de la clase
	 *            padre Persona.
	 * @param departamento
	 *            Corresponde con el departamento del nuevo Profesor a
	 *            instanciar.
	 */
	public Profesor(String DNI, String nombre, String apellidos, String nacimiento, String departamento) {
		super(DNI, nombre, apellidos, convertirFecha(nacimiento));
		this.departamento = departamento;
	}

	/**
	 * Este método devuelve un objeto de tipo Clase con una determinada ID al
	 * mapa de asignaturas impartidas.
	 * 
	 * @param ID
	 *            Corresponde con la ID del objeto de tipo Clase que queremos
	 *            devolver.
	 * @return Clase
	 */
	public Clase getClase(int ID) {
		return asignaturas_impartidas.get(ID);
	}

	/**
	 * Este método a�ade un objeto de tipo Clase al mapa de asignaturas
	 * impartidas.
	 * 
	 * @param ID
	 *            Corresponde con la ID del objeto de tipo Clase que queremos
	 *            a�adir.
	 * @param c
	 *            Corresponde con el objeto de tipo Clase que queremos a�adir al
	 *            mapa de asignatura impartidas.
	 */
	public void putClase(int ID, Clase c) {
		asignaturas_impartidas.put(ID, c);
	}

	/**
	 * Este método devuelve el departamento de una instancia de Profesor.
	 * 
	 * @return String
	 */
	public String getDepartamento() {
		return this.departamento;
	}

	/**
	 * Este método devuelve el mapa de asignaturas impartidas por una instancia
	 * de Profesor.
	 * 
	 * @return HashMap
	 */
	public HashMap<Integer, Clase> getAsignaturas() {
		return this.asignaturas_impartidas;
	}
}
