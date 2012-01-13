package controlador.competencia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import modelo.Categoria;
import modelo.Divisa;
import modelo.Liga;

import org.zkoss.zk.ui.util.GenericForwardComposer;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioLiga;

public class CntrlFrmLiga extends GenericForwardComposer {

	Component formulario;
	AnnotateDataBinder binder;
	ServicioLiga servicioLiga;
	// Objeto Liga que recibe la Liga que se selecciona en el catalogo...
	Liga liga;
	List<Categoria> categoriaSeleccionada;
	List<Categoria> categorias;
	ServicioCategoria servicioCategoria;

	// Vista...
	Textbox txtNombre;
	Textbox txtLocalidad;
	Listbox lsbxCategorias;
	Listbox lsbxCategoriaSeleccionada;
	Button btnEliminar;

	
	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Categoria> getCategoriaSeleccionada() {
		return categoriaSeleccionada;
	}

	public void setCategoriaSeleccionada(List<Categoria> categoriaSeleccionada) {
		this.categoriaSeleccionada = categoriaSeleccionada;
	}

	public Liga getLiga() {
		return liga;
	}

	public void setLiga(Liga liga) {
		this.liga = liga;
	}

	
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		c.setVariable("cntrl", this, true);
		formulario = c;
		restaurar();
		categorias = servicioCategoria.listar();
	}
	
	
	

	private void restaurar() {
		liga = new Liga();
		categoriaSeleccionada = new ArrayList<Categoria>();
	}

	
	// Agregado Convierte un conjunto a una lista...
	public List ConvertirConjuntoALista(Set conjunto) {
		List l = new ArrayList();
		for (Iterator i = conjunto.iterator(); i.hasNext();) {
			l.add(i.next());
		}
		return l;
	}
	

	// Agregado Convierte una lista a un conjunto...
	public Set ConvertirListaAConjunto(List lista) {
		Set c = new HashSet();
		for (Iterator i = lista.iterator(); i.hasNext();) {
			c.add(i.next());
		}
		return c;
	}

	
	public void Agregar(Listbox origen, Listbox destino, List lista) {

		Set seleccionados = origen.getSelectedItems();
		for (Iterator i = seleccionados.iterator(); i.hasNext();) {
			boolean sw = false;
			Listitem li = (Listitem) i.next();
			Categoria c1 = (Categoria) li.getValue();
			List seleccionDestino = destino.getItems();
			for (Iterator j = seleccionDestino.iterator(); j.hasNext();) {
				Listitem li2 = (Listitem) j.next();
				Categoria c2 = (Categoria) li2.getValue();
				if (c1.getNombre().equals(c2.getNombre())) {
					sw = true;
					break;
				}
			}
			if (!sw) {
				lista.add(c1);
			}
		}
	}
	

	public void Quitar(Listbox origen, List lista) {
		Set seleccionados = origen.getSelectedItems();
		for (Iterator i = seleccionados.iterator(); i.hasNext();) {
			Listitem li = (Listitem) i.next();
			Object o = li.getValue();
			lista.remove(o);
		}
	}

	// BOTONES BUSCAR, AGREGAR,QUITAR................

	public void onClick$btnBuscar() {
		Component catalogo = Executions.createComponents(
				"/Competencias/Vistas/FrmCatalogoLigas.zul", null, null);
		catalogo.setVariable("formulario", formulario, false);
		formulario.addEventListener("onCatalogoCerrado", new EventListener() {
			@Override
			// Este metodo se llama cuando se envia la señal desde el catalogo
			public void onEvent(Event arg0) throws Exception {
				// se obtiene la divisa
				liga = (Liga) formulario.getVariable("liga", false);
				categoriaSeleccionada = ConvertirConjuntoALista(liga
						.getCategorias());
				btnEliminar.setDisabled(false);
				binder.loadAll();
			}
		});
	}
	

	public void onClick$btnAgregar() {
		Agregar(lsbxCategorias, lsbxCategoriaSeleccionada,
				categoriaSeleccionada);
		binder.loadAll();
	}
	

	public void onClick$btnQuitar() {
		Quitar(lsbxCategoriaSeleccionada, categoriaSeleccionada);
		binder.loadAll();
	}
	
	
	

	// BOTONES GUARDAR,ELIMINAR,CANCELAR,SALIR................

	public void onClick$btnGuardar() throws InterruptedException {
		if (txtNombre.getText() != null)
			if (txtLocalidad.getText() != null)
				if (lsbxCategoriaSeleccionada.getItems().size() > 0) {
					liga.setCategorias(ConvertirListaAConjunto(categoriaSeleccionada));
					servicioLiga.agregar(liga);
					Messagebox.show("Datos agregados exitosamente", "Mensaje",
							Messagebox.OK, Messagebox.EXCLAMATION);
					restaurar();
					binder.loadAll();
				} 
				else
					Messagebox.show("Seleccione la Categoria", "Mensaje",
							Messagebox.OK, Messagebox.EXCLAMATION);
	}
	

	public void onClick$btnEliminar() throws InterruptedException{
		
		if (Messagebox.show("¿Realmente desea eliminar esta liga", "Mensaje",
				Messagebox.YES + Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES) {
			servicioLiga.eliminar(liga);
            restaurar();
            binder.loadAll();
			Messagebox.show("Datos eliminados exitosamente", "Mensaje",	Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}
	
	
	public void onClick$btnCancelar() {
		restaurar();
		btnEliminar.setDisabled(true);
		binder.loadAll();
	}
	

	public void onClick$btnSalir() {
		formulario.detach();

	}

}
