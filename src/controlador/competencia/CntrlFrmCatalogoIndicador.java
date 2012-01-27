package controlador.competencia;

import java.util.List;

import modelo.Divisa;
import modelo.Indicador;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;

import servicio.implementacion.ServicioIndicador;

public class CntrlFrmCatalogoIndicador extends GenericForwardComposer {
	AnnotateDataBinder binder;
	ServicioIndicador servicioIndicador;
	List<Indicador> listIndicador;
	Listbox lsbxIndicadores;
	Component catalogo;

	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		// Se utiliza para hacer referencia a los objetos desde la vista (ej
		// cntrl.algo). Debe ir siempre aqui
		c.setVariable("cntrl", this, true);
		// se guarda la referencia al catalogo
		catalogo = c;
		// Se listan las divisas activas y se cargan mediante databinding (ver
		// zul)
		listIndicador = servicioIndicador.listarActivos();		

		// si selecciona por defecto el primero de la lista si hay al menos 1
		if (lsbxIndicadores.getItems().size() != 0) {
			lsbxIndicadores.setSelectedIndex(0);
		}

	}

	public void onClick$btnAceptar() throws InterruptedException {
		// Se comprueba que se haya seleccionado un elemento de la lista
		if (lsbxIndicadores.getSelectedIndex() != -1) {
			// se obtiene la divisa seleccionada
			Indicador i = listIndicador.get(lsbxIndicadores.getSelectedIndex());
			// se obtiene la referencia del formulario
			Component formulario = (Component) catalogo.getVariable(
					"formulario", false);
			// se le asigna el objeto divisa al formulario
			formulario.setVariable("indicador", i, false);
			// se le envia una señal al formulario indicado que el formulario
			// se cerro y que los datos se han enviado
			Events.sendEvent(new Event("onCatalogoCerrado", formulario));
			// se cierra el catalogo
			catalogo.detach();

		} else {
			Messagebox.show("Seleccione una divisa", "Mensaje", Messagebox.YES,
					Messagebox.INFORMATION);

		}

	}
	

	public void onClick$btnSalir() {
		catalogo.detach();
	}

	public List<Indicador> getListIndicador() {
		return listIndicador;
	}

	public void setListIndicador(List<Indicador> listIndicador) {
		this.listIndicador = listIndicador;
	}

}
