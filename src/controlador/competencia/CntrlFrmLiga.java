package controlador.competencia;

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
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;

import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioLiga;

public class CntrlFrmLiga extends GenericForwardComposer {

	Component formulario;
	AnnotateDataBinder binder;
	ServicioLiga servicioLiga;
	// Objeto Liga que recibe la Liga que se selecciona en el catalogo...
	Liga liga;
	Categoria cat;
	Listbox lsbxCategorias;
	Listbox lsbxCategoriaSeleccionada;
	Set<Categoria> categoriaSeleccionada;
	List<Categoria> categorias;
	ServicioCategoria servicioCategoria;


	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Categoria getCat() {
		return cat;
	}

	public void setCat(Categoria cat) {
		this.cat = cat;
	}

	public Set<Categoria> getCategoriaSeleccionada() {
		return categoriaSeleccionada;
	}

	public void setCategoriaSeleccionada(Set<Categoria> categoriaSeleccionada) {
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
		categoriaSeleccionada = new HashSet<Categoria>();

	}

	public void onClick$btnGuardar() throws InterruptedException {
		servicioCategoria.agregar(cat);
		Messagebox.show("Datos agregados exitosamente", "Mensaje",Messagebox.OK, Messagebox.EXCLAMATION);
		restaurar();
		binder.loadAll();
	}

	public void onClick$btnBuscar() {
		Component catalogo = Executions.createComponents("/Competencias/Vistas/FrmCatalogoLigas.zul", null, null);
		catalogo.setVariable("formulario", formulario, false);
		formulario.addEventListener("onCatalogoCerrado", new EventListener() {
			@Override
			// Este metodo se llama cuando se envia la señal desde el catalogo
			public void onEvent(Event arg0) throws Exception {
				// se obtiene la divisa
				liga = (Liga) formulario.getVariable("liga", false);
				categoriaSeleccionada = liga.getCategorias();
				binder.loadAll();
			}
		});
	}

	public void onClick$btnAgregar() {
		Agregar(lsbxCategorias, lsbxCategoriaSeleccionada, categoriaSeleccionada);
		binder.loadAll();
	}

	public void onClick$btnQuitar() {
		Quitar(lsbxCategoriaSeleccionada, categoriaSeleccionada);
		binder.loadAll();
	}

	public void Agregar(Listbox origen, Listbox destino, Set conjunto) {
		Set seleccionados = origen.getSelectedItems();
		for (Iterator i = seleccionados.iterator(); i.hasNext();) {
			boolean sw = false;
			Listitem li = (Listitem) i.next();
			Categoria c1 = (Categoria) li.getValue();
			List seleccionDestino = destino.getItems();
			for (Iterator j = seleccionDestino.iterator(); j.hasNext();) {
				Listitem li2 = (Listitem) j.next();
				Categoria c2 = (Categoria) li2.getValue();
				if (c1.getNombre().equals(c2.getNombre())){
					sw = true;
					break;
				}
			}
			if (!sw) {
				conjunto.add(c1);
			}
		}

	}
	
	public void Quitar(Listbox origen, Set conjunto) {
		Set seleccionados = origen.getSelectedItems();
		for (Iterator i = seleccionados.iterator(); i.hasNext();) {
			Listitem li = (Listitem) i.next();
			Object o = li.getValue();
			conjunto.remove(o);
		}
	}

	public void onClick$btnCancelar() {
		restaurar();
		binder.loadAll();
	}

	public void onClick$btnSalir() {
		formulario.detach();

	}

}
