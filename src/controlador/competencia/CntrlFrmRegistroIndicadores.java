package controlador.competencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import modelo.Constante;
import modelo.DatoBasico;
import modelo.Divisa;
import modelo.Indicador;
import modelo.TipoDato;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioConstante;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioIndicador;
import servicio.implementacion.ServicioTipoDato;

public class CntrlFrmRegistroIndicadores extends GenericForwardComposer {

	ServicioTipoDato servicioTipoDato;
	ServicioDatoBasico servicioDatoBasico;
	ServicioIndicador servicioIndicador;
	ServicioConstante servicioConstante;
	List<DatoBasico> listTipoIndicador;
	List<DatoBasico> listModalidadIndicador;
	List<DatoBasico> listMedicionIndicador;
	List<Indicador> listIndicador;
	List<Constante> listConstante;
	AnnotateDataBinder binder;
	Combobox cmbTipo;
	Combobox cmbModalidadSencillo;
	Combobox cmbModalidadCompuesto;
	Combobox cmbMedicion;
	Combobox cmbIndicador;
	Combobox cmbConstante;
	Component formulario;
	Panel pnlSencillo, pnlCompuesto;
	Textbox txtAbreviaturaSencillo;
	Textbox txtAbreviaturaCompuesto;
	Textbox txtNombreSencillo;
	Textbox txtNombreCompuesto;
	Indicador indicador;
	Window winRegistroIndicador;
	Button btnGuardar;
	Button btnEliminar;
	Button btnCancelar;
	Button btnSumar;
	Button btnRestar;
	Button btnMultiplicar;
	Button btnDividir;
	Button btnParentesisAbre;
	Button btnParentesisCierra;

	public List<Constante> getListConstante() {
		return listConstante;
	}

	public void setListConstante(List<Constante> listConstante) {
		this.listConstante = listConstante;
	}

	public List<Indicador> getListIndicador() {
		return listIndicador;
	}

	public void setListIndicador(List<Indicador> listIndicador) {
		this.listIndicador = listIndicador;
	}

	public List<DatoBasico> getListMedicionIndicador() {
		return listMedicionIndicador;
	}

	public void setListMedicionIndicador(List<DatoBasico> listMedicionIndicador) {
		this.listMedicionIndicador = listMedicionIndicador;
	}

	public List<DatoBasico> getListModalidadIndicador() {
		return listModalidadIndicador;
	}

	public void setListModalidadIndicador(
			List<DatoBasico> listModalidadIndicador) {
		this.listModalidadIndicador = listModalidadIndicador;
	}

	public List<DatoBasico> getListTipoIndicador() {
		return listTipoIndicador;
	}

	public void setListTipoIndicador(List<DatoBasico> listTipoIndicador) {
		this.listTipoIndicador = listTipoIndicador;
	}

	public Indicador getIndicador() {
		return indicador;
	}

	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}

	public void inicializar() {
		indicador = new Indicador();
		cmbTipo.setValue("--Seleccione--");
		txtAbreviaturaSencillo.setReadonly(true);
		txtNombreSencillo.setReadonly(true);
		btnGuardar.setDisabled(true);
		btnEliminar.setDisabled(true);
		btnCancelar.setDisabled(true);
	}

	public void cargarCombos() {
		TipoDato nombre = servicioTipoDato.buscarPorTipo("TIPO INDICADOR");
		listTipoIndicador = servicioDatoBasico.buscarPorTipoDato(nombre);

		TipoDato modalidadIndicador = servicioTipoDato
				.buscarPorTipo("MODALIDAD INDICADOR");
		listModalidadIndicador = servicioDatoBasico
				.buscarPorTipoDato(modalidadIndicador);

		TipoDato medicionIndicador = servicioTipoDato
				.buscarPorTipo("MEDICION INDICADOR");
		listMedicionIndicador = servicioDatoBasico
				.buscarPorTipoDato(medicionIndicador);

	}

	public void doAfterCompose(Component com) throws Exception {
		super.doAfterCompose(com);
		com.setVariable("cntrl", this, true);
		inicializar();
		cargarCombos();		
		//se guarda la referencia al formulario actual ej (frmRegistroIndicadores)
		formulario = com;
	}

	public void onChange$cmbTipo() {
		if (cmbTipo.getText().equals("SENCILLO")) {
			pnlSencillo.setVisible(true);
			pnlCompuesto.setVisible(false);
		} else {
			pnlSencillo.setVisible(false);
			pnlCompuesto.setVisible(true);
		}
		btnGuardar.setDisabled(false);
		btnEliminar.setDisabled(false);
		btnCancelar.setDisabled(false);
		cmbTipo.setDisabled(true);
	}

	public void onChange$cmbModalidadSencillo() {
		txtAbreviaturaSencillo.setReadonly(false);
		txtNombreSencillo.setReadonly(false);
	}

	public List<Indicador> ConvertirConjuntoALista(Set<Indicador> conjunto) {
		List<Indicador> l = new ArrayList<Indicador>(conjunto);
		return l;
	}

	public void onChange$cmbModalidadCompuesto() {
		DatoBasico DB = (DatoBasico) cmbModalidadCompuesto.getSelectedItem()
				.getValue();
		listIndicador = new ArrayList<Indicador>();
		listIndicador = servicioIndicador.buscarPorDatoBasico(DB);
		cmbIndicador.setDisabled(false);
		cmbConstante.setDisabled(false);
		listConstante = new ArrayList<Constante>();
		listConstante = servicioConstante.listarActivos();
		binder.loadAll();
	}

	public void limpiar() {
		cmbTipo.setDisabled(false);
		cmbModalidadSencillo.setValue("--Seleccione--");
		cmbModalidadCompuesto.setValue("--Seleccione--");
		cmbMedicion.setValue("--Seleccione--");
		txtAbreviaturaSencillo.setValue("");
		txtNombreSencillo.setValue("");
		txtAbreviaturaCompuesto.setValue("");
		txtNombreCompuesto.setValue("");
		txtAbreviaturaSencillo.setReadonly(true);
		txtNombreSencillo.setReadonly(true);
		indicador = new Indicador();
	}

	public void onClick$btnCancelar() {
		limpiar();
	}

	public void onClick$btnSalir() throws InterruptedException {
		if (indicador != null) {
			int result = Messagebox
					.show("Existen elementos en el formulario ¿Realmente desea salir?",
							"Question", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION);
			switch (result) {
			case Messagebox.OK:
				onClick$btnCancelar();
				binder.loadAll();
				winRegistroIndicador.detach();
				break;
			case Messagebox.CANCEL:
				break;
			default:
				break;
			}
		} else {
			onClick$btnCancelar();
			binder.loadAll();
			winRegistroIndicador.detach();
		}

	}

	public void onClick$btnSumar() {
	}

	public void onClick$btnRestar() {
	}

	// public void onClick$btnSumar(){}

	public void onClick$btnGuardar() throws InterruptedException {
		indicador.setDatoBasicoByCodigoTipoIndicador((DatoBasico) cmbTipo
				.getSelectedItem().getValue());
		indicador
				.setDatoBasicoByCodigoModalidad((DatoBasico) cmbModalidadSencillo
						.getSelectedItem().getValue());
		if (cmbTipo.getText().equals("SENCILLO")) {
			DatoBasico medicion = servicioDatoBasico
					.buscarPorString("INDIVIDUAL");
			indicador.setDatoBasicoByCodigoMedicion(medicion);
		} else {
			indicador.setDatoBasicoByCodigoMedicion((DatoBasico) cmbMedicion
					.getSelectedItem().getValue());
		}
		indicador.setEstatus('A');
		indicador.setCodigoIndicador(servicioIndicador.generarCodigo());
		servicioIndicador.agregar(indicador);
		Messagebox.show("Datos agregados exitosamente", "Mensaje",
				Messagebox.OK, Messagebox.EXCLAMATION);
		limpiar();
		cmbTipo.setValue("--Seleccione--");
		binder.loadAll();
	}

	// public void onClick$btnEliminar() throws InterruptedException {
	// indicador.setEstatus('E');
	// servicioIndicador.agregar(indicador);
	// Messagebox.show("Datos eliminados exitosamente", "Mensaje",
	// Messagebox.OK, Messagebox.EXCLAMATION);
	// onClick$btnCancelar();
	// binder.loadAll();
	// }

	// Llama al catalogo

	public void onClick$btnBuscarIndicador() {
		//se crea el catalogo y se llama
				Component catalogo = Executions.createComponents("/Competencias/Vistas/FrmCatalogoIndicador.zul", null, null);
				//asigna una referencia del formulario al catalogo.
				catalogo.setVariable("formulario",formulario, false);
			    		
				formulario.addEventListener("onCatalogoCerrado", new EventListener() {		
					@Override
					//Este metodo se llama cuando se envia la señal desde el catalogo
					public void onEvent(Event arg0) throws Exception {
						//se obtiene la divisa
						indicador = (Indicador) formulario.getVariable("indicador",false);
						cmbTipo.setSelectedIndex(buscar(indicador));
						binder.loadAll();				
					}
				});

	}
	
	public int buscar(Indicador i){
    	int j=-1;
    	for (Iterator<DatoBasico> k = listTipoIndicador.iterator();k.hasNext();){  		  
    		   DatoBasico db = k.next();
    		   j++;
    		   if (db.getNombre().equals(indicador.getDatoBasicoByCodigoTipoIndicador().getNombre())){    			  
    			  return j; 
    		   }
    	}
    	return j;
    }
	
	
}
