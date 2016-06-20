package personas;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
/**
 * La clase Persona incluye todos los atributos y métodos relacionados con una persona.
 * Debido a que todas las personas son alumnos o profesores, esta clase ser� una clase
 * abstracta y ser� la clase padre de Alumno y Profesor.
 * 
 * @author Brais Piñeiro y Jorge Pose
 * @see personas.Alumno
 * @see personas.Profesor
 */
public abstract class Persona {
	//ATRIBUTOS
	private String DNI;
	private String nombre;
	private String apellidos;
	private GregorianCalendar nacimiento;
	/**
	 * Este es el unico constructor de la clase Persona.
	 * 
	 * @param DNI
	 *            Corresponde con el DNI de la nueva Persona a instanciar.
	 * @param nombre
	 *            Corresponde con el nombre de la nueva Persona a instanciar.
	 * @param apellidos
	 *            Corresponde con los apellidos de la nueva Persona a instanciar.
	 * @param nacimiento
	 *            Corresponde con la fecha de nacimiento de la nueva Persona a
	 *            instanciar.
	 *            
	 */
	public Persona(String DNI, String nombre, String apellidos, GregorianCalendar nacimiento) {
		this.DNI = DNI;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nacimiento = nacimiento;
	}
	/**
	 * Este metodo recibe como parametro una cadena que representa una fecha y devuelve 
	 * dicha fecha convertida en GregorianCalendar
	 * 
	 * @param fecha 
	 * 				Corresponde con el String que contiene la fecha (en formato dd/mm/aa)
	 * 
	 * @return GregorianCalendar
	 */
	public static GregorianCalendar convertirFecha(String fecha){
		String[] partes= fecha.split("/");
		GregorianCalendar calend= new GregorianCalendar();
		calend.set(Integer.parseInt(partes[2]), Integer.parseInt(partes[1])-1, Integer.parseInt(partes[0]));
		return calend;
	}
	/**
	 * Este metodo recibe como parametro una fecha en GregorianCalendar y devuelve 
	 * dicha fecha convertida en un String (dd/mm/aa)
	 * 
	 * @param fecha 
	 * 				Corresponde con la fecha en GregorianCalendar
	 * 
	 * @return String
	 * 					(en formato (dd/mm/aa))
	 */
	public static String convertirFecha(GregorianCalendar fecha){
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	    formato.setCalendar(fecha);
	   return formato.format(fecha.getTime());
	}
	/**
	 * Este método devuelve el nombre de una instancia de Persona
	 * 
	 * @return String
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Este método devuelve los apellidos de una instancia de Persona
	 * 
	 * @return String
	 */
	public String getApellidos() {
		return apellidos;
	}
	/**
	 * Este método devuelve la fecha de nacimiento (en GregorianCalendar) de una instancia de Persona
	 * 
	 * @return GregorianCalendar
	 */
	public GregorianCalendar getNacimiento() {
		return nacimiento;
	}
	/**
	 * Este método devuelve el DNI de una instancia de Persona
	 * 
	 * @return String
	 */
	public String getDNI(){
		return DNI;
	}
	/**
	 * Este método sobreescribe al mismo método de la clase padre y devuelve el
	 * contenido de todos los atributos de una instancia Persona.
	 * 
	 * @return String
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return this.DNI+"\n"+this.nombre+"\n"+this.apellidos+"\n"+convertirFecha(this.nacimiento)+"\n";
	}
}
