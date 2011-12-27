package comun;

/**
 * Enumerado para mantener los valores constantes de los diferentes tipos de datos básicos 
 * @author Robert A.
 * @author German L.
 * @version 0.1 22/12/2011
 */
public enum TipoDatoBasico {
	AFECCION(49),
	TIPO_PERSONA(54),
	PAIS(64),
	ESTADO_VENEZUELA(65), 
    MUNICIPIO(66),
	PARROQUIA(67),
	CODIGO_AREA(68),
	CODIGO_CELULAR(69),
	GRUPO_SANGUINEO(70),
	FACTOR_RH(71),
	TIPO_AFECCION(72),
	CURSO(73),
	ANNO_ESCOLAR(74),
	ACTIVIDAD_SOCIAL(75),
	CLASIFICACION_EQUIPO(76),
	INDUMENTARIA(77),
	TALLA_INDUMENTARIA(78),
	PARENTESCO(79),
	PROFESION(80),
	IMPORTANCIA(81),
	PROCESO(82),
	DOCUMENTO(83),
	TIPO_DOCUMENTO(84),
	INSCRIPCION(85),
	INSTITUCION(86),
	SANCION(87),
	SUSPENSION(88),
	RETIRO(89),
	ESPECIALIDAD(90),
	LOGRO(91),
	BRAZO_LANZAR(96),
	POSICION_BATEO(97),
	GENERO(98),
	COMISION(102);

	private int codigo;

    private TipoDatoBasico(int codigo) {
       this.codigo = codigo;
    }

    public int getCodigo() {
		return codigo;
	}
}