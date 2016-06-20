package personas;


import java.util.GregorianCalendar;
import java.util.HashMap;
import docencia.Aprobada;
import docencia.Clase;
/**
 * La clase Alumno incluye todos los atributos y métodos relacionados con un alumno,
 * herendando por este motivo de la clase Persona. 
 * 
 * @author Brais Piñeiro y Jorge Pose
 * @see personas.Persona
 */
public class Alumno extends Persona {
	//Atributos
	private GregorianCalendar ingreso;
	private HashMap<Integer,Aprobada> asignaturas_aprobadas=new HashMap<Integer,Aprobada>();
	public HashMap<Integer,Clase> asignaturas_recibidas=new HashMap<Integer,Clase>();
	/**
	 * Este es el �nico constructor disponible de la clase Alumno, con el cual
	 * instanciamos dicha clase.
	 * 
	 * @param DNI
	 *            Corresponde con el DNI del nuevo Alumno a instanciar, es
	 *            usado para llamar al constructor de la clase padre Persona.
	 * @param nombre
	 *            Corresponde con el nombre del nuevo Alumno a instanciar, es
	 *            usado para llamar al constructor de la clase padre Persona.
	 * @param apellidos
	 *            Corresponde con los apellidos del nuevo Alumno a instanciar,
	 *            es usado para llamar al constructor de la clase padre Persona.
	 * @param nacimiento
	 *            Corresponde con la fecha de nacimiento del nuevo Alumno a
	 *            instanciar, es usado para llamar al constructor de la clase
	 *            padre Persona.
	 * @param ingreso
	 *            Corresponde con la fecha de matricula del nuevo Alumno a
	 *            instanciar.
	 */
	public Alumno(String DNI, String nombre, String apellidos, String nacimiento, String ingreso) {
		super(DNI, nombre, apellidos,convertirFecha(nacimiento));
		this.ingreso = convertirFecha(ingreso);
	}
	/**
	 * Este método a�ade una asignatura aprobada al mapa de aprobadas de una
	 * instancia de Alumno.
	 * 
	 * @param ID
	 *            Corresponde con la ID de la asignatura aprobada.
	 * @param a
	 *            Corresponde con la asignatura aprobada a a�adir.
	 */
	public void putAprobada(int ID,Aprobada a){
		asignaturas_aprobadas.put(ID, a);
	}
	/**
	 * Este método a�ade un objeto de tipo Clase al mapa de asignaturas
	 * recibidas.
	 * 
	 * @param ID
	 *            Corresponde con la ID del objeto de tipo Clase que queremos
	 *            a�adir.
	 * @param c
	 *            Corresponde con el objeto de tipo Clase que queremos a�adir al
	 *            mapa de asignatura impartidas.
	 */
	public void putClase(int ID,Clase c){
		asignaturas_recibidas.put(ID, c);
	}
	/**
	 * Este método devuelve un objeto de tipo Clase con una determinada ID al
	 * mapa de asignaturas recibidas.
	 * 
	 * @param ID
	 *            Corresponde con la ID del objeto de tipo Clase que queremos
	 *            devolver.
	 * @return Clase
	 */
	public Clase getClase(int ID){
		return asignaturas_recibidas.get(ID);
	}
	/**
	 * Este método elimina un objeto de tipo Clase con una determinada ID del 
	 * mapa de asignaturas recibidas.
	 * 
	 * @param ID
	 *           Corresponde con la ID del objeto de tipo Clase que queremos 
	 *           eliminar.
	 */
	public void removeClase(int ID){
		asignaturas_recibidas.remove(ID);
	}
	/**
	 * Este método comprueba si ya existe un objeto de tipo Clase con una determinada ID
	 *  en el mapa de asignaturas recibidas.
	 * 
	 * @param ID
	 *           Corresponde con la ID del objeto de tipo Clase que queremos 
	 *   		 comprobar si ya existe en el mapa.
	 *   @return boolean
	 */
	public boolean containsClase(int ID){
		return asignaturas_recibidas.containsKey(ID);
	}
	/**
	 * Este metodo devuelve el mapa de las asignaturas aprobadas por el alumno
	 * 
	 * @return HashMap
	 */
	public HashMap<Integer,Aprobada> getAprobadas(){
		return asignaturas_aprobadas;
	}
	/**
	 * Este metodo devuelve el mapa de las asignaturas que recibe el alumno
	 * 
	 * @return HashMap
	 */
	public HashMap<Integer,Clase> getRecibidas(){
		return asignaturas_recibidas;
	}
	/**
	 * Este metodo devuelve la fecha de matricula de una instancia de Alumno
	 * 
	 * @return GregorianCalendar
	 */
	public GregorianCalendar getIngreso(){
		return this.ingreso;
	}
}
