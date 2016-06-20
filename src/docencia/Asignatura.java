package docencia;

/**
 * La clase Asignatura representa todos los atributos y métodos relacionados con una asignatura de una materia.
 * @author Brais Piñeiro y Jorge Pose
 */
import java.util.ArrayList;
import java.util.HashMap;

public class Asignatura {
	// Atributos
	private int ID;
	private String nombre;
	private String siglas;
	private int curso;
	private String coordinador;
	private ArrayList<Integer> prerrequisitos = new ArrayList<Integer>();
	private HashMap<Integer, Grupo> gruposA = new HashMap<Integer, Grupo>();
	private HashMap<Integer, Grupo> gruposB = new HashMap<Integer, Grupo>();

	/**
	 * Este es el �nico constructor disponible de Asignatura con el cual
	 * instanciamos dicha clase.
	 * 
	 * @param ID
	 *            Corresponde con la ID de la asignatura a instanciar.
	 * @param nombre
	 *            Corresponde con el nombre de la asignatura a instanciar.
	 * @param siglas
	 *            Corresponde con las siglas de la asignatura a instanciar.
	 * @param curso
	 *            Corresponde con el n�mero de curso de la asignatura a
	 *            instanciar, se espera que se un numero del 1 al 4.
	 */
	public Asignatura(int ID, String nombre, String siglas, int curso) {
		this.ID = ID;
		this.nombre = nombre;
		this.siglas = siglas;
		this.curso = curso;
	}

	/**
	 * Este método sobreescribe al mismo método de la clase padre y devuelve el
	 * contenido de todos los atributos de una instancia de Asignatura.
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if (coordinador == null) {
			return new Integer(this.ID).toString() + "\n" + this.nombre + "\n" + this.siglas + "\n"
					+ new Integer(this.curso).toString() + "\n" + "\n";
		} else {
			return new Integer(this.ID).toString() + "\n" + this.nombre + "\n" + this.siglas + "\n"
					+ new Integer(this.curso).toString() + "\n" + coordinador + "\n";
		}
	}

	/**
	 * Este método devuelve el nombre de una instancia de Asignatura.
	 * 
	 * @return String
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Este método devuelve el curso de una instancia de Asignatura.
	 * 
	 * @return int
	 */
	public int getCurso() {
		return curso;
	}

	/**
	 * Este método devuelve la lista de prerrequisitos de una instancia de
	 * Asignatura.
	 * 
	 * @return ArrayList
	 */
	public ArrayList<Integer> getPrerrequisitos() {
		return prerrequisitos;
	}

	/**
	 * Este método devuelve la lista de grupos de tipo A de una instancia de
	 * Asignatura.
	 * 
	 * @return HashMap
	 */
	public HashMap<Integer, Grupo> getGruposA() {
		return gruposA;
	}

	/**
	 * Este método devuelve la lista de grupos de tipo B de una instancia de
	 * Asignatura.
	 * 
	 * @return HashMap
	 */
	public HashMap<Integer, Grupo> getGruposB() {
		return gruposB;
	}

	/**
	 * Este método devuelve la ID de una instancia de Asignatura.
	 * 
	 * @return int
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Este método a�ade un prerrequisito a la lista de una instancia de
	 * Asignatura.
	 * 
	 * @param prerrequisito
	 *            Corresponde con la ID de la asignatura que ser� nuevo
	 *            prerrequisito.
	 */
	public void addPrerrequisitos(int prerrequisito) {
		prerrequisitos.add(prerrequisito);
	}

	/**
	 * Este método a�ade un grupo de tipo A al mapa de grupos de tipo A de una
	 * instancia de Asignatura.
	 * 
	 * @param ID
	 *            Corresponde con la ID del Grupo A.
	 * @param g
	 *            Corresponde con el grupo a a�adir.
	 */
	public void putGruposA(int ID, Grupo g) {
		gruposA.put(ID, g);
	}

	/**
	 * Este método a�ade un grupo de tipo B al mapa de grupos de tipo B de una
	 * instancia de Asignatura.
	 * 
	 * @param ID
	 *            Corresponde con la ID del Grupo B.
	 * @param g
	 *            Corresponde con el grupo a a�adir.
	 */
	public void putGruposB(int ID, Grupo g) {
		gruposB.put(ID, g);
	}

	/**
	 * Este método devuelve el grupo de tipo A con una ID espec�fica de una
	 * instancia de Asignatura.
	 * 
	 * @param ID
	 *            Corresponde a la ID del grupo a devolver.
	 * @return Grupo
	 */
	public Grupo getGrupoA(int ID) {
		return gruposA.get(ID);
	}

	/**
	 * Este método devuelve el grupo de tipo B con una ID espec�fica de una
	 * instancia de Asignatura.
	 * 
	 * @param ID
	 *            Corresponde a la ID del grupo a devolver.
	 * @return Grupo
	 */
	public Grupo getGrupoB(int ID) {
		return gruposB.get(ID);
	}

	/**
	 * Este método devuelve el coordinador de una instancia de Asignatura.
	 * 
	 * @return String
	 */
	public String getCoordinador() {
		return coordinador;
	}

	/**
	 * Este método establece el coordinador de una instancia de Asignatura.
	 * 
	 * @param coordinador
	 *            Corresponde con el DNI del coordinador.
	 */
	public void setCoordinador(String coordinador) {
		this.coordinador = coordinador;
	}

	/**
	 * Este método devuelve las siglas de una instancia de Asignatura.
	 * 
	 * @return String
	 */
	public String getSiglas() {
		return siglas;
	}

}
