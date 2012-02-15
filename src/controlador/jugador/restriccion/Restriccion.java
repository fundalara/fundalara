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
	EMAIL("/.+@.+\\.[a-zA-Z]+/",
			"Ingrese un correo electrónico válido, Ej: correo@servidor.com"),
			HORAS_SEMANAL_SOCIAL("min 1 max 30", "Ingrese una cantidad de horas válidas (01-30)"), 
			CEDULA("no negative,no empty", "Ingrese una cédula válida"),
			CEDULA_COMPLETA("/[V,E,R]{1}[-]{1}[0-9]{8}/", ""),
			TELEFONO("/[0-9]{7}/", "Ingrese un número válido, Ej: 2660011"),
			TELEFONO_COMPLETO("/[0-9]{4}-[0-9]{7}/", "Ingrese un número válido, Ej: 2660011"),
			TEXTO_SIMPLE("/^[a-zA-Z áéíóúAÉÍÓÚÑñ]+$/", "Ingrese un texto válido"),
			FECHA_NACIMIENTO("between " + Util.getFecha(Edad.EDAD_MAXIMA, Util.LIMITE_INFERIOR) + " and "
					+ Util.getFecha(Edad.EDAD_MINIMA, Util.LIMITE_SUPERIOR),"Ingrese una fecha válida"),
			CANTIDAD_EQUIPO("min 1 max 6",""),
			EDAD_INFERIOR("min 3 max 16",""),
			EDAD_SUPERIOR("min 5 max 17",""),
			MIN_JUGADORES("min 12 max 19",""),
			MAX_JUGADORES("min 13 max 20","");

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
