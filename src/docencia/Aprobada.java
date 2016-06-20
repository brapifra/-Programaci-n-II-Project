package docencia;
import main.Proyecto37;

/**
 * La clase Aprobada incluye los atributos relacionados con una asignatura aprobada.
 * @author Brais Piñeiro y Jorge Pose
 *
 */
public class Aprobada implements Comparable<Aprobada> {
	private int ID;
	private String cursoAcademico;
	private float nota;
	/**
	 * Este es el �nico constructor disponible de Aprobada, con el cual instanciamos dicha clase.
	 * @param ID Corresponde a la ID de la asignatura aprobada.
	 * @param cursoAcademico Corresponde al curso acad�mico en el que se aprob� la asignatura en formato (aa/aa)
	 * @param nota Corresponde a la nota con la que se aprob� la asignatura
	 */
	public Aprobada(int ID,String cursoAcademico,float nota){
		this.ID=ID;
		this.cursoAcademico=cursoAcademico;
		this.nota=nota;
	}
	/**
	 * Este método devuelve el curso acad�mico de una instancia de Aprobada
	 * @return String 
	 */
	public String getCursoAcademico(){
		return this.cursoAcademico;
	}
	/**
	 * Este método sobreescribe al mismo método de la clase padre y devuelve el contenido de
	 * todos los atributos de una instancia de Aprobada.
	 * @return String 
	 */
	public String toString(){
		return new Integer(ID).toString()+" "+cursoAcademico+" "+new Float(nota).toString();
	}
	/**
	 * Este método devuelve la ID de una instancia de Aprobada
	 * @return int
	 */
	public int getID(){
		return ID;
	}
	/**
	 * Este método devuelve la nota de una instancia de Aprobada
	 * @return float
	 */
	public float getNota(){
		return nota;
	}
	/**
	 * Este método es la implementaci�n de la interfaz Comparable de Java, y devuelve un entero negativo si
	 * la instancia de Aprobada tiene un nombre alfabéticamente menor que el nombre de la Aprobada que el método
	 * recibe como par�metro.
	 * 
	 * @param aprobada
	 * 				Corresponde a la instancia de Aprobada con el nombre a comparar
	 * @return int
	 */
	public int compareTo(Aprobada aprobada) {
		return String.CASE_INSENSITIVE_ORDER.compare(Proyecto37.totalasignaturas.get(this.getID()).getNombre(),Proyecto37.totalasignaturas.get(aprobada.getID()).getNombre());
	}
	
	
}
