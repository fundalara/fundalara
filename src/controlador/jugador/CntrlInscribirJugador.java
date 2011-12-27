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


public class CntrlInscribirJugador extends GenericForwardComposer {
	//componentes visuales
	private Combobox cmbTipoInscrip;
	private Include incCuerpo;
	
	//Servicios
	private ServicioDatoBasico servicioDatoBasico;
	//Nodelo
	private DatoBasico tipoIncripcion = new DatoBasico();
	// Binder
	private AnnotateDataBinder binder;
	
	private String rutasJug = Ruta.JUGADOR.getRutaVista();
	
	
	//Getters y setters
	public DatoBasico getTipoIncripcion() {
		return tipoIncripcion;
	}

	public void setTipoIncripcion(DatoBasico tipoIncripcion) {
		this.tipoIncripcion = tipoIncripcion;
	}
	
	public Include getIncCuerpo() {
		return incCuerpo;
	}

	public void setIncCuerpo(Include incCuerpo) {
		this.incCuerpo = incCuerpo;
	}

	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, false);
	}

	//Metodos para la carga del combo
	public List<DatoBasico> getInscripciones() {
		return servicioDatoBasico.buscar(TipoDatoBasico.INSCRIPCION);
	}
	
	//Eventos
	public void onChange$cmbTipoInscrip() {
		String src = "";
		String valor = cmbTipoInscrip.getSelectedItem().getLabel();
		Util enlace = new Util();
		if (valor.equalsIgnoreCase("Nuevo Ingreso")) {
			src = "frmRegistrarJugador.zul";
		} else if (valor.equalsIgnoreCase("Reingreso")) {
			src = "frmReingresarJugador.zul";
		} else {
			src = "frmRegistrarPlanVacacional.zul";
		}
		src = rutasJug+src;
		incCuerpo.setDynamicProperty("tipoInscripcion", tipoIncripcion);
		enlace.insertarContenido(incCuerpo, src);
	}
	
	

}
