package controlador.competencia;

import java.util.List;

import modelo.DatoBasico;
import modelo.Liga;
import modelo.PersonalForaneo;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;

import servicio.implementacion.ServicioPersonalForaneo;

public class CntrlFrmCatalogoUmpire extends GenericForwardComposer {

	AnnotateDataBinder binder;
	Component catalogo;
	ServicioPersonalForaneo servicioPersonalForaneo;
	
	PersonalForaneo umpire;
	DatoBasico datoBasico;
	// Objeto Lista de lisgas que se mostraran en el catalogo...
	List<PersonalForaneo> umpires;
	Listbox lsbxUmpire;

//	public AnnotateDataBinder getBinder() {
//		return binder;
//	}
//
//	public void setBinder(AnnotateDataBinder binder) {
//		this.binder = binder;
//	}
//
//	public Component getCatalogo() {
//		return catalogo;
//	}
//
//	public void setCatalogo(Component catalogo) {
//		this.catalogo = catalogo;
//	}

	public List<PersonalForaneo> getUmpires() {
		return umpires;
	}

	public void setUmpires(List<PersonalForaneo> umpires) {
		this.umpires = umpires;
	}


	public Listbox getLsbxUmpire() {
		return lsbxUmpire;
	}

	public void setLsbxUmpire(Listbox lsbxUmpire) {
		this.lsbxUmpire = lsbxUmpire;
	}

	public PersonalForaneo getUmpire() {
		return umpire;
	}

	public void setUmpire(PersonalForaneo umpire) {
		this.umpire = umpire;
	}
	
	
	
	public ServicioPersonalForaneo getServicioPersonalForaneo() {
		return servicioPersonalForaneo;
	}

	public void setServicioPersonalForaneo(
			ServicioPersonalForaneo servicioPersonalForaneo) {
		this.servicioPersonalForaneo = servicioPersonalForaneo;
	}

	public DatoBasico getDatoBasico() {
		return datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);		
		c.setVariable("cntrl", this, true);
		catalogo = c;
		umpires = servicioPersonalForaneo.listarUmpires();
	}

	public void onClick$btnAceptar() throws InterruptedException {

		if (lsbxUmpire.getSelectedIndex() != -1) {
			PersonalForaneo u = umpires.get(lsbxUmpire.getSelectedIndex());

			Component formulario = (Component) catalogo.getVariable(
					"formulario", false);
			formulario.setVariable("umpire", u, false);
			Events.sendEvent(new Event("onCatalogoCerrado", formulario));
			catalogo.detach();

		} else {
			Messagebox.show("Seleccione un Umpire", "Mensaje", Messagebox.YES,
					Messagebox.INFORMATION);

		}
	}

	public void onClick$btnSalir() {
		catalogo.detach();
	}
}
