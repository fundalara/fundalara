package comun;
/**
 * Enumerado de rutas de vistas
 * 
 * @author Robert A
 * @author German L
 * @version 0.1 10/01/2012
 * 
 */
public enum Ruta {
    JUGADOR("Jugador/Vistas/", "Jugador/Reportes/"), 
    GENERAL("General/", "General/");
    
    private String rutaVista;
    private String rutaReporte;

    private Ruta(String vista, String reporte) {
       rutaVista = vista;
       rutaReporte= reporte;
    }

	public String getRutaVista() {
		return rutaVista;
	}
	
	public String getRutaReporte() {
		return rutaReporte;
	}

	
}
