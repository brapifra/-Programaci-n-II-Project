package personas;

/**
 * La clase Asociado incluye todos los atributos relacionados con un profesor de
 * tipo asociado, por ello hereda de la clase Profesor, y esta a su vez de
 * Persona.
 * 
 * @author Brais Piñeiro y Jorge Pose
 * @see personas.Profesor
 * @see personas.Persona
 */
public class Asociado extends Profesor {
	// Atributos
	public static final int horasdocenciamax = 15;
	private int horasdocencia;

	/**
	 * Este es el �nico constructor disponible de la clase Asociado con el cual
	 * instanciamos dicha clase.
	 * 
	 * @param DNI
	 *            Corresponde con el DNI del nuevo Asociado a instanciar, es
	 *            usado para llamar al constructor de la clase padre Persona.
	 * @param nombre
	 *            Corresponde con el nombre del nuevo Asociado a instanciar, es
	 *            usado para llamar al constructor de la clase padre Persona.
	 * @param apellidos
	 *            Corresponde con los apellidos del nuevo Asociado a instanciar,
	 *            es usado para llamar al constructor de la clase padre Persona.
	 * @param nacimiento
	 *            Corresponde con la fecha de nacimiento del nuevo Asociado a
	 *            instanciar, es usado para llamar al constructor de la clase
	 *            padre Persona.
	 * @param departamento
	 *            Corresponde con el departamento del nuevo Asociado a
	 *            instanciar, es usado para llamar al constructor de la clase
	 *            padre Profesor.
	 * @param horasdocencia
	 *            Corresponde con las horas de docencia asignables del nuevo
	 *            Asociado a instanciar.
	 */
	public Asociado(String DNI, String nombre, String apellidos, String nacimiento, String departamento,
			int horasdocencia) {
		super(DNI, nombre, apellidos, nacimiento, departamento);
		this.horasdocencia = horasdocencia;
	}

	/**
	 * Este método sobreescribe al mismo método de la clase padre y devuelve el
	 * contenido de todos los atributos de una instancia de Asociado y los
	 * atributos de las clases de las cuales hereda.
	 * 
	 * @return String
	 */
	public String toString() {
		return super.toString() + "asociado\n" + super.getDepartamento() + "\n"
				+ new Integer(this.horasdocencia).toString() + "\n";
	}

	/**
	 * Este método devuelve el numero de horas asignables de una instancia de
	 * Asociado.
	 * 
	 * @return int
	 */
	public int getHorasdocencia() {
		return horasdocencia;
	}
}
