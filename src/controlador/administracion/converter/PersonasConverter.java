package controlador.administracion.converter;

import modelo.*;

import org.zkoss.zkplus.databind.TypeConverter;

public class PersonasConverter implements TypeConverter {
	private static String valorRetornado = "";
	private static PersonaNatural personaNatural;
	private static Persona persona;
	private static PersonaJuridica personaJuridica;

	public Object coerceToBean(java.lang.Object val,
			org.zkoss.zk.ui.Component comp) {
		return null;
	}

	public Object coerceToUi(java.lang.Object val,
			org.zkoss.zk.ui.Component comp) {
		String segundoA, segundoN = "";

		if (val instanceof Persona) {
			persona = ((Persona) val);

			if (persona.getPersonaNatural() != null) {
				personaNatural = persona.getPersonaNatural();
				if (personaNatural.getSegundoNombre() == null)
					segundoN = "";
				else
					segundoN = personaNatural.getSegundoNombre();

				if (personaNatural.getSegundoApellido() == null)
					segundoA = "";
				else
					segundoA = personaNatural.getSegundoApellido();

				valorRetornado = personaNatural.getPrimerNombre() + " "
						+ segundoN + " " + personaNatural.getPrimerApellido()
						+ " " + segundoA;
			}
			
			if (persona.getPersonaJuridica() != null) {
				personaJuridica = persona.getPersonaJuridica();
				valorRetornado = personaJuridica.getRazonSocial();
			}
		}
		return valorRetornado;
	}
}