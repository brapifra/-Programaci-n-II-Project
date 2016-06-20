package docencia;

/**
 * La clase Grupo incluye los atributos y metodos relacionados con un grupo de una asignatura.
 * @author Brais Piñeiro y Jorge Pose
 *
 */
public class Grupo {
	private int ID;
	private char dia;
	private int horainicio;
	private int horafin;
	public final static int horainiciomin = 9;
	public final static int horainiciomax = 18;

	/**
	 * Este es el �nico constructor disponible de Grupo con el cual
	 * instanciamos dicha clase.
	 * 
	 * @param ID
	 *            Corresponde con la ID de la asignatura del grupo a instanciar.
	 * @param dia
	 *            Corresponde con el dia de la semana en el que se imparte el 
	 *            grupo a instanciar.
	 * @param horainicio
	 *            Corresponde con la hora a la cual da inicio la clase del grupo 
	 *            a instanciar (se espera que no sea inferior a las 9).
	 * @param horafin
	 *            Corresponde con la hora a la cual finaliza la clase del grupo
	 *            a instanciar (se espera que no sea superior a 18).
	 */
	
	public Grupo(int ID, char dia, int horainicio, int horafin) {
		this.ID = ID;
		this.dia = dia;
		this.horainicio = horainicio;
		this.horafin = horafin;
	}
	
	/**
	 * Este método devuelve el ID de una instancia de Grupo.
	 * @return int 
	 */
	public int getID() {
		return ID;
	}
	/**
	 * Este método devuelve el dia de la semana de una instancia de Grupo.
	 * @return char 
	 */
	public char getDia() {
		return dia;
	}
	/**
	 * Este método devuelve la hora de inicio de una instancia de Grupo.
	 * @return int 
	 */
	public int getHorainicio() {
		return horainicio;
	}
	/**
	 * Este método devuelve la hora de fin de una instancia de Grupo.
	 * @return int 
	 */
	public int getHorafin() {
		return horafin;
	}
	/**
	 * Este método sobreescribe al mismo método de la clase padre y devuelve el contenido de
	 * todos los atributos de una instancia de Grupo.
	 * @return String 
	 */
	public String toString(){
		return ID + " " + dia + " " + new Integer(horainicio).toString()+ " " + new Integer(horafin).toString();
	}
}
