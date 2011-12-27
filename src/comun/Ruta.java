package comun;

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
