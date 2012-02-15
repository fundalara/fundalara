package controlador.jugador;

import java.util.List;

import modelo.DatoBasico;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Include;

import servicio.implementacion.ServicioDatoBasico;

import comun.Ruta;
import comun.TipoDatoBasico;
import comun.Util;

/**
 * @author María F
 * @version 0.1 23/12/2011
 */
public class CntrlAscenderJugador extends GenericForwardComposer {
	// componentes visuales
	private Combobox cmbTipoAscenso;
	private Include incCuerpo;

	// Servicio
	private ServicioDatoBasico servicioDatoBasico;

	// Modelo
	private DatoBasico tipoAscenso = new DatoBasico();

	// Binder
	private AnnotateDataBinder binder;

	//Ruta
	private String rutasJug = Ruta.JUGADOR.getRutaVista();

	// Getters y setters
	public Include getIncCuerpo() {
		return incCuerpo;
	}

	public void setIncCuerpo(Include incCuerpo) {
		this.incCuerpo = incCuerpo;
	}

	public DatoBasico getTipoAscenso() {
		return tipoAscenso;
	}

	public void setTipoAscenso(DatoBasico tipoAscenso) {
		this.tipoAscenso = tipoAscenso;
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, false);
	}

	// Para cargar el combo
	public List<DatoBasico> getAscensos() {
		DatoBasico a = servicioDatoBasico.buscarTipo(TipoDatoBasico.PROCESO,"ASCENSO");
		return servicioDatoBasico.buscarDatosPorRelacion(a);		
	}
	
	// Evento
	public void onChange$cmbTipoAscenso() {
		String src = "";
		String valor = cmbTipoAscenso.getSelectedItem().getLabel();
		Util enlace = new Util();
		if (valor.equalsIgnoreCase("Edad")) {
			src = "frmAscensoEdad.zul";
		} else {
			src = "frmAscensoEspecial.zul";
		}
		src = rutasJug + src;
		incCuerpo.setDynamicProperty("tipoAscenso", tipoAscenso);
		enlace.insertarContenido(incCuerpo, src);
	}
	
}