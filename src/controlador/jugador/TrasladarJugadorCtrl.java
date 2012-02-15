package controlador.jugador;

import java.util.List;

import modelo.DatoBasico;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Include;

import servicio.implementacion.ServicioDatoBasico;

import comun.Ruta;
import comun.TipoDatoBasico;
import comun.Util;

public class TrasladarJugadorCtrl extends GenericForwardComposer {
	private Combobox cmbTipoTraslado;
	private Include incCuerpo;

	//Servicios
	private ServicioDatoBasico servicioDatoBasico;
	//Nodelo
	private DatoBasico tipoOperacion = new DatoBasico();
	// Binder
	private AnnotateDataBinder binder;
	
	private String rutasJug = Ruta.JUGADOR.getRutaVista();	

	
	//Get y Ser
	public Include getIncCuerpo() {
		return incCuerpo;
	}

	public void setIncCuerpo(Include incCuerpo) {
		this.incCuerpo = incCuerpo;
	}

	public DatoBasico getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(DatoBasico tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, false);
	}

	//Metodos para la carga del combo
	public List<DatoBasico> getOperaciones() {
		return servicioDatoBasico.buscar(TipoDatoBasico.TIPO_OPERACION);
	}


	public void onChange$cmbTipoTraslado() {
/*		String src = "";
		String valor = cmbTipoTraslado.getSelectedItem().getValue().toString();
		Util enlace = new Util();
		if (valor.equals("1")) {
			src = "Jugador/Vistas/frmRetirarJugador.zul";
		} else {
			src = "Jugador/Vistas/frmRegistrarPase.zul";
		}
		enlace.insertarContenido(incCuerpo, src);

*/
	
		String src = "";
		String valor = cmbTipoTraslado.getSelectedItem().getLabel();
		Util enlace = new Util();
		if (valor.equalsIgnoreCase("RETIRO")) {
			src = "frmRetirarJugador.zul";
		} else if (valor.equalsIgnoreCase("PASE")) {
			src = "frmRegistrarPase.zul";
		}
		src = rutasJug+src;
		incCuerpo.setDynamicProperty("tipoOperacion", tipoOperacion);
		enlace.insertarContenido(incCuerpo, src);	
	}
}
