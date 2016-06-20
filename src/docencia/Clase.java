package docencia;

/**
 * La clase Clase incluye los atributos y metodos relacionados con un
 * determinado Grupo y una determinada asignatura a la vez. Clase viene a
 * representar un grupo de una asignatura en concreto.
 * 
 * @author Brais Piñeiro y Jorge Pose
 *
 */
public class Clase {
	private int ID;
	private Asignatura materia;
	private Grupo grupoA;
	private Grupo grupoB;

	/**
	 * Este es el �nico constructor disponible de Clase, con el cual
	 * instanciamos dicha clase.
	 * 
	 * @param ID
	 *            Corresponde a la ID de la asignatura de esta instancia de
	 *            Clase.
	 * @param materia
	 *            Corresponde a la asignatura (instanciada) que se imparte en
	 *            esta instancia de Clase.
	 */
	public Clase(int ID, Asignatura materia) {
		this.ID = ID;
		this.materia = materia;
	}

	/**
	 * Este método devuelve el Grupo A (objeto) de una instancia de Clase.
	 * 
	 * @return Grupo
	 */
	public Grupo getGrupoA() {
		return grupoA;
	}

	/**
	 * Este método establece el grupoA de una instancia de Clase.
	 * 
	 * @param grupoA
	 *            Corresponde con el objeto Grupo (A) de la instancia de Clase.
	 */
	public void setGrupoA(Grupo grupoA) {
		this.grupoA = grupoA;
	}

	/**
	 * Este método devuelve el Grupo B (objeto) de una instancia de Clase.
	 * 
	 * @return Grupo
	 */
	public Grupo getGrupoB() {
		return grupoB;
	}

	/**
	 * Este método establece el Grupo B de una instancia de Clase.
	 * 
	 * @param grupoB
	 *            Corresponde con el objeto Grupo (B) de la instancia de Clase.
	 */
	public void setGrupoB(Grupo grupoB) {
		this.grupoB = grupoB;
	}

	/**
	 * Este método devuelve la ID de una instancia de Clase (corresponde con el
	 * de la materia de esa instancia en concreto).
	 * 
	 * @return int
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Este método establece el ID de una instancia de Clase.
	 * 
	 * @param iD
	 *            Corresponde con la ID de un objeto asignatura y a su vez de
	 *            una instancia de Clase.
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * Este método sobreescribe al mismo método de la clase padre y devuelve el
	 * contenido de todos los atributos de una instancia de Clase (en el caso de
	 * que falte uno o ambos objetos Grupo se omitiran, si no se imprimira la ID
	 * de los grupos que sean atributo de esta instancia en concreto).
	 * 
	 * @return String
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if (grupoA == null && grupoB == null) {
			return new Integer(ID).toString();
		}
		if (grupoA != null && grupoB == null) {
			return new Integer(ID).toString() + " A " + new Integer(grupoA.getID()).toString();
		}
		if (grupoA == null && grupoB != null) {
			return new Integer(ID).toString() + " B " + new Integer(grupoB.getID()).toString();
		} else {
			return new Integer(ID).toString() + " A " + new Integer(grupoA.getID()).toString() + "; "
					+ new Integer(ID).toString() + " B " + new Integer(grupoB.getID()).toString();
		}
	}
}
