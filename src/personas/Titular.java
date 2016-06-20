package personas;

/**
 * La clase Titular incluye todos los atributos relacionados con un profesor de
 * tipo titular, por ello hereda de la clase Profesor, y esta a su vez de
 * Persona.
 * 
 * @author Brais Piñeiro y Jorge Pose
 * @see personas.Profesor
 * @see personas.Persona
 */
public class Titular extends Profesor {
	// Atributos
	public static final int horasdocenciamax = 20;
	private int horasdocencia;

	/**
	 * Este es el �nico constructor disponible de la clase Titular con el cual
	 * instanciamos dicha clase.
	 * 
	 * @param DNI
	 *            Corresponde con el DNI del nuevo Titular a instanciar, es
	 *            usado para llamar al constructor de la clase padre Persona.
	 * @param nombre
	 *            Corresponde con el nombre del nuevo Titular a instanciar, es
	 *            usado para llamar al constructor de la clase padre Persona.
	 * @param apellidos
	 *            Corresponde con los apellidos del nuevo Titular a instanciar,
	 *            es usado para llamar al constructor de la clase padre Persona.
	 * @param nacimiento
	 *            Corresponde con la fecha de nacimiento del nuevo Titular a
	 *            instanciar, es usado para llamar al constructor de la clase
	 *            padre Persona.
	 * @param departamento
	 *            Corresponde con el departamento del nuevo Titular a
	 *            instanciar, es usado para llamar al constructor de la clase
	 *            padre Profesor.
	 * @param horasdocencia
	 *            Corresponde con las horas de docencia asignables del nuevo
	 *            Titular a instanciar.
	 */
	public Titular(String DNI, String nombre, String apellidos, String nacimiento, String departamento,
			int horasdocencia) {
		super(DNI, nombre, apellidos, nacimiento, departamento);
		this.horasdocencia = horasdocencia;
	}

	/**
	 * Este método sobreescribe al mismo método de la clase padre y devuelve el
	 * contenido de todos los atributos de una instancia de Titular y los
	 * atributos de las clases de las cuales hereda.
	 * 
	 * @return String
	 */
	public String toString() {
		return super.toString() + "titular\n" + super.getDepartamento() + "\n"
				+ new Integer(this.horasdocencia).toString() + "\n";
	}

	/**
	 * Este método devuelve el numero de horas asignables de una instancia de
	 * Titular.
	 * 
	 * @return int
	 */
	public int getHorasdocencia() {
		return horasdocencia;
	}
}
