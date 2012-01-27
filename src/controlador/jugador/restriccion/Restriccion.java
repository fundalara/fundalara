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
			"Ingrese un correo electrónico válido, Ej: correo@servidor.com"), HORAS_SEMANAL_SOCIAL(
			"min 1 max 30", "Ingrese una cantidad de horas válidas (01-30)"), CEDULA(
			"no negative,no empty", "Ingrese una cédula válida"), TELEFONO(
			"/[0-9]{7}/", "Ingrese un número válido, Ej: 2660011"), TEXTO_SIMPLE(
			"/^[a-zA-Z áéíóúAÉÍÓÚÑñ]+$/", "Ingrese un texto válido"), FECHA_NACIMIENTO(
			"between " + Util.getFecha(Edad.EDAD_MAXIMA, '1') + " and "
					+ Util.getFecha(Edad.EDAD_MINIMA, '2'),
			"Ingrese una fecha válida");

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
	 * añadiendo los pasados por parametro
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
