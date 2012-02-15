package controlador.jugador.restriccion;

import comun.Util;

/**
 * Enumerado para mantener los valores constantes de los diferentes
 * restricciones y sus mensajes de error asociados
 * 
 * @author Robert A.
 * @author German L.
 * @version 0.1 03/01/2012
 */

public enum Restriccion {
	EMAIL("/.+@.+\\.[a-z]+/",
			"Ingrese un correo electr�nico v�lido, Ej: correo@servidor.com"), HORAS_SEMANAL_SOCIAL(
			"min 1 max 30", "Ingrese una cantidad de horas v�lidas (01-30)"), CEDULA(
			"no negative,no empty", "Ingrese una c�dula v�lida"), TELEFONO(
			"/[0-9]{7}/", "Ingrese un n�mero v�lido, Ej: 2660011"), TEXTO_SIMPLE(
			"/^[a-zA-Z �����A������]+$/", "Ingrese un texto v�lido"), FECHA_NACIMIENTO(
			"between " + Util.getFecha(Edad.EDAD_MAXIMA, '1') + " and "
					+ Util.getFecha(Edad.EDAD_MINIMA, '2'),
			"Ingrese una fecha v�lida");

	private String constraint;
	private String mensaje;

	private Restriccion(String constraint, String mensaje) {
		this.constraint = constraint;
		this.mensaje = mensaje;

	}

	public String getConstraint() {
		return constraint;
	}

	public String getMensaje() {
		return mensaje;
	}

	public String getRestriccion() {
		return constraint + " : " + mensaje;
	}

	/**
	 * Genera texto de restriccion tomando como base el constraint del enumerado
	 * a�adiendo los pasados por parametro
	 * 
	 * @param restricciones
	 *            arreglo de restricciones validas para objetos inputElement
	 * @return restriccion nueva con su mensaje de error
	 */
	public String asignarRestriccionExtra(String... restricciones) {
		String restriccion = constraint;
		for (String string : restricciones) {
			if (string != "") {
				restriccion += "," + string.trim();
			}
		}
		return restriccion + " : " + mensaje;
	}
}
