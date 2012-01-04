package controlador.competencia;

import java.util.List;

import modelo.DatoBasico;
import modelo.TipoDato;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Panel;

import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioTipoDato;

public class CntrlFrmRegistroIndicadores extends GenericForwardComposer {

	ServicioTipoDato servicioTipoDato;
	ServicioDatoBasico servicioDatoBasico;
	List<DatoBasico> listTipoIndicador;
	AnnotateDataBinder binder;
	Combobox cmbTipo;
	Panel pnlSencillo, pnlCompuesto;
	
	public List<DatoBasico> getListTipoIndicador() {
		return listTipoIndicador;
	}

	public void setListTipoIndicador(List<DatoBasico> listTipoIndicador) {
		this.listTipoIndicador = listTipoIndicador;
	}
	
	public void doAfterCompose(Component com) throws Exception{
		super.doAfterCompose(com);
		com.setVariable("cntrl", this, true);
		TipoDato nombre = servicioTipoDato.buscarTipo("TIPO INDICADOR");
		listTipoIndicador = servicioDatoBasico.buscarPorTipoDato(nombre);
	}

	public void onChange$cmbTipo (){
		if (cmbTipo.getText().equals("SENCILLO")) {
			pnlSencillo.setVisible(true);
			pnlCompuesto.setVisible(false);
		} else {
			pnlSencillo.setVisible(false);
			pnlCompuesto.setVisible(true);
		}
		
	}
	
	
}
