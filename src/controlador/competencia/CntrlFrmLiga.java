package controlador.competencia;

import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import modelo.Categoria;
import modelo.CategoriaLiga;
import modelo.CategoriaLigaId;
import modelo.Divisa;
import modelo.Liga;

import org.python.tests.ExceptionTest.Thrower;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.util.GenericForwardComposer;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Image;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import comun.FileLoader;

import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioCategoriaLiga;
import servicio.implementacion.ServicioLiga;

public class CntrlFrmLiga extends GenericForwardComposer {

	Component formulario;
	AnnotateDataBinder binder;
	ServicioLiga servicioLiga;
	// Objeto Liga que recibe la Liga que se selecciona en el catalogo...
	Liga liga;
	List<CategoriaLiga> categoriaSeleccionada;
	List<Categoria> categorias;
	ServicioCategoria servicioCategoria;
	ServicioCategoriaLiga servicioCategoriaLiga;
	Boolean ligaBuscada;
	Image imgLogo;
	// Vista...
	Textbox txtNombre;
	Textbox txtLocalidad;
	Listbox lsbxCategorias;
	Listbox lsbxCategoriaSeleccionada;
	Button btnBuscar;
	byte[] arr = { 0, 0, 0 };

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<CategoriaLiga> getCategoriaSeleccionada() {
		return categoriaSeleccionada;
	}

	public void setCategoriaSeleccionada(
			List<CategoriaLiga> categoriaSeleccionada) {
		this.categoriaSeleccionada = categoriaSeleccionada;
	}

	public Image getImgLogo() {
		return imgLogo;
	}

	public void setImgLogo(Image imgLogo) {
		this.imgLogo = imgLogo;
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
		categoriaSeleccionada = new ArrayList<CategoriaLiga>();
		ligaBuscada = false;
		AImage img = null;
		imgLogo.setContent(img);

	}

	public void onClick$btnExaminar() {
		FileLoader fl = new FileLoader();
		liga.setLogo(fl.cargarImagenEnBean(imgLogo));
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
			CategoriaLiga cl = new CategoriaLiga();
			cl.setCategoria(c1);
			cl.setEstatus('A');
			cl.setLiga(liga);
			List seleccionDestino = destino.getItems();
			for (Iterator j = seleccionDestino.iterator(); j.hasNext();) {
				Listitem li2 = (Listitem) j.next();
				CategoriaLiga cl2 = (CategoriaLiga) li2.getValue();
				if (cl.getCategoria().getNombre()
						.equalsIgnoreCase(cl2.getCategoria().getNombre())) {
					sw = true;
					break;
				}
			}
			if (!sw) {
				lista.add(cl);
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
			// Este metodo se llama cuando se envia la seÃ±al desde el catalogo
			public void onEvent(Event arg0) throws Exception {
				// se obtiene la divisa
				liga = (Liga) formulario.getVariable("liga", false);
				categoriaSeleccionada = ConvertirConjuntoALista(liga.getCategoriaLigas());
				if (!(liga.getLogo() == null)) {
					AImage img = new AImage("", liga.getLogo());
					imgLogo.setContent(img);
				}
				ligaBuscada = true; // se cargaron los datos...
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

		if (!txtNombre.getValue().isEmpty())
			if (!txtLocalidad.getText().isEmpty())
				if (lsbxCategoriaSeleccionada.getItems().size() > 0) {

					servicioLiga.agregar(liga);
					for (CategoriaLiga cl : categoriaSeleccionada) {
						CategoriaLigaId id = new CategoriaLigaId(
								liga.getCodigoLiga(), cl.getCategoria()
										.getCodigoCategoria());
						cl.setId(id);
						servicioCategoriaLiga.agregar(cl);
					}

					Messagebox.show("Datos agregados exitosamente", "Mensaje",
							Messagebox.OK, Messagebox.EXCLAMATION);
					restaurar();
					binder.loadAll();
				} else
					throw new WrongValueException(lsbxCategoriaSeleccionada,
							"Debe seleccionar al menos una 'Categoria'");
			else
				throw new WrongValueException(txtLocalidad,
						"El campo 'Localidad' es obligatorio");
		else
			throw new WrongValueException(txtNombre,
					"El campo 'Nombre' es obligatorio");
	}

	public void onClick$btnEliminar() throws InterruptedException {

		if (ligaBuscada == false)
			throw new WrongValueException(btnBuscar,
					" Debe seleccionar una 'Liga'");

		else if (Messagebox.show("¿Realmente desea eliminar esta liga?",
				"Mensaje", Messagebox.YES + Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES) {
			servicioLiga.eliminar(liga);
			restaurar();
			binder.loadAll();
			Messagebox.show("Datos eliminados exitosamente", "Mensaje",
					Messagebox.OK, Messagebox.EXCLAMATION);
		}

	}

	public void onClick$btnCancelar() {

		
		restaurar();
		binder.loadAll();

	}

	public void onClick$btnSalir() throws InterruptedException {
		if (Messagebox.show("¿Desea salir?", "Mensaje", Messagebox.YES
				+ Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES)
			formulario.detach();

	}

}
