package comun;
/**
 * Enumerado para tipificar los estados de las competencias
 * @author Eduardo Ochoa
 * @version 1.0 
 * @email elos3000@gmail.com
 */

public enum EstadoCompetencia {
	REGISTRADA(6),
	APERTURADA(7),
	ELIMINADA(8),
	CLAUSURADA(9);
	int val;
	
	private EstadoCompetencia(int val) {
		this.val = val;
	}
}
