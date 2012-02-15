package controlador.competencia;

import java.util.ArrayList;
import java.util.List;

import modelo.Categoria;
import modelo.Competencia;
import modelo.Divisa;
import modelo.EquipoCompetencia;
import modelo.PersonaNatural;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import servicio.implementacion.ServicioDivisa;
import servicio.implementacion.ServicioPersonaNatural;

public class CntrlFrmCatalogoPersonaNatural extends GenericForwardComposer {

	AnnotateDataBinder binder;
	ServicioPersonaNatural servicioPersonaNatural;
	List<PersonaNatural> personaNatural,personaNaturales;
	Listbox lsbxPersonas;
	List<EquipoCompetencia> ListPersonas;
	Component catalogo;
	Textbox txtFiltro;

	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		// Se utiliza para hacer referencia a los objetos desde la vista (ej
		// cntrl.algo). Debe ir siempre aqui
		c.setVariable("cntrl", this, true);
		// se guarda la referencia al catalogo
		catalogo = c;
		personaNaturales = new ArrayList<PersonaNatural>();
		personaNatural = new ArrayList<PersonaNatural>();
		personaNatural = servicioPersonaNatural.listar();
		// Se listan las Personas Naturales y se cargan mediante databinding
		// (ver zul)
		
		// si selecciona por defecto el primero de la lista si hay al menos 1
	
		
		

	}
	
	
	public void onCreate$frmDelegados(){
		ListPersonas = new ArrayList<EquipoCompetencia>();
		ListPersonas = (List<EquipoCompetencia>) catalogo.getVariable("lista",
				false);
		
		
		
		
			for (int Recorrido = 0; Recorrido < personaNatural.size(); Recorrido++) {
				for (int Recorrido0 = 0; Recorrido0 < ListPersonas.size(); Recorrido0++) {
				 if (ListPersonas.get(Recorrido0).getPersonaNatural()==personaNatural.get(Recorrido)){
					 personaNatural.remove(Recorrido);
				 }
			}
		}
		binder.loadAll();
		if (lsbxPersonas.getItems().size() != 0) {
			lsbxPersonas.setSelectedIndex(0);
		}
		
	}

	public void onClick$btnAceptar() throws InterruptedException {
		// Se comprueba que se haya seleccionado un elemento de la lista
		if (lsbxPersonas.getSelectedIndex() != -1) {
			// se obtiene la Persona Natural seleccionada
			PersonaNatural d = personaNatural.get(lsbxPersonas
					.getSelectedIndex());
			// se obtiene la referencia del formulario
			Component formulario = (Component) catalogo.getVariable(
					"formulario", false);
			// se le asigna el objeto Persona Natural al formulario
			formulario.setVariable("personaNatural", d, false);
			// se le envia una señal al formulario indicado que el formulario
			// se cerro y que los datos se han enviado
			Events.sendEvent(new Event("onCatalogoCerrado2", formulario));
			// se cierra el catalogo
			catalogo.detach();

		} else {
			Messagebox.show("Seleccione una divisa", "Mensaje", Messagebox.YES,
					Messagebox.INFORMATION);

		}

	}

	public List<PersonaNatural> getPersonaNatural() {
		return personaNatural;
	}

	public void setPersonaNatural(List<PersonaNatural> personaNatural) {
		this.personaNatural = personaNatural;
	}

	public Listbox getLsbxPersonas() {
		return lsbxPersonas;
	}

	public void setLsbxPersonas(Listbox lsbxPersonas) {
		this.lsbxPersonas = lsbxPersonas;
	}

	public void onCtrlKey$txtFiltro() {
		System.out.println("changing...");
	}

	public void onChanging$txtFiltro() {

		// divisas = servicioDivisa.filtar(txtFiltro.getText()+"%");
		binder.loadAll();
	}

	public void onClick$btnSalir() {
		catalogo.detach();
	}

	public List<PersonaNatural> getpersonaNatural() {
		return personaNatural;
	}

	public void setpersonaNatural(List<PersonaNatural> personaNatural) {
		this.personaNatural = personaNatural;
	}


	public AnnotateDataBinder getBinder() {
		return binder;
	}


	public void setBinder(AnnotateDataBinder binder) {
		this.binder = binder;
	}


	public ServicioPersonaNatural getServicioPersonaNatural() {
		return servicioPersonaNatural;
	}


	public void setServicioPersonaNatural(
			ServicioPersonaNatural servicioPersonaNatural) {
		this.servicioPersonaNatural = servicioPersonaNatural;
	}


	public List<PersonaNatural> getPersonaNaturales() {
		return personaNaturales;
	}


	public void setPersonaNaturales(List<PersonaNatural> personaNaturales) {
		this.personaNaturales = personaNaturales;
	}


	public List<EquipoCompetencia> getListPersonas() {
		return ListPersonas;
	}


	public void setListPersonas(List<EquipoCompetencia> listPersonas) {
		ListPersonas = listPersonas;
	}


	public Component getCatalogo() {
		return catalogo;
	}


	public void setCatalogo(Component catalogo) {
		this.catalogo = catalogo;
	}


	public Textbox getTxtFiltro() {
		return txtFiltro;
	}


	public void setTxtFiltro(Textbox txtFiltro) {
		this.txtFiltro = txtFiltro;
	}
	

}
