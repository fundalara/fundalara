/**
 * Una clase para registrar las actividades de entrenamiento
 * de cada una de las fases de entrenamiento pertenecientes a cada 
 * categoria.
 * @version 1.0, 09/01/12
 * @author Oscar Gutierrez, Alnis Piñero
 */

package controlador.entrenamiento;

import java.util.ArrayList;
import java.util.List;

import modelo.ActividadEntrenamiento;
import modelo.Categoria;
import modelo.DatoBasico;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;

import servicio.implementacion.ServicioActividadEntrenamiento;
import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioTipoDato;

public class CntrlFrmActividadEntrenamiento extends GenericForwardComposer {
	Button btnSalir, btnCancelar, btnAgregar, btnQuitar, btnGuardar;
	Window winActividadEntrenamientoZul;
	Textbox txtActividades;
	Listbox lboxActividades;
	Combobox cmbCategoria, cmbFase;
	ActividadEntrenamiento actividadEntrenamiento;
	ServicioCategoria servicioCategoria;
	ServicioTipoDato servicioTipoDato;
	ServicioDatoBasico servicioDatoBasico;
	ServicioActividadEntrenamiento servicioActividadEntrenamiento;
	List<Categoria> listCategoria;
	List<DatoBasico> listFase;
	List<ActividadEntrenamiento> listActividadEntrenamiento;
	List<ActividadEntrenamiento> listActividadEntrenamientoEliminados;
	AnnotateDataBinder binder;
	boolean editar;
	int index;

	public List<ActividadEntrenamiento> getListActividadEntrenamiento() {
		return listActividadEntrenamiento;
	}

	public void setListActividadEntrenamiento(
			List<ActividadEntrenamiento> listActividadEntrenamiento) {
		this.listActividadEntrenamiento = listActividadEntrenamiento;
	}

	public void cargarFases() {
		listFase = servicioDatoBasico.buscarPorTipoDato(servicioTipoDato
				.buscarPorTipo("FASES DEL ENTRENAMIENTO"));
	}

	public DatoBasico buscarFase() {
		return (DatoBasico) cmbFase.getSelectedItem().getValue();
	}

	public Categoria buscarCategoria() {
		return (Categoria) cmbCategoria.getSelectedItem().getValue();
	}

	public List<DatoBasico> getListFase() {
		return listFase;
	}

	public void setListFase(List<DatoBasico> listFase) {
		this.listFase = listFase;
	}

	public List<Categoria> getListCategoria() {
		return listCategoria;
	}

	public void setListCategoria(List<Categoria> listCategoria) {
		this.listCategoria = listCategoria;
	}

	public ActividadEntrenamiento getActividadEntrenamiento() {
		return actividadEntrenamiento;
	}

	public void setActividadEntrenamiento(
			ActividadEntrenamiento actividadEntrenamiento) {
		this.actividadEntrenamiento = actividadEntrenamiento;
	}

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("ctrl", this, true);
		inicializar();
		listActividadEntrenamiento = new ArrayList<ActividadEntrenamiento>();
		listActividadEntrenamientoEliminados = new ArrayList<ActividadEntrenamiento>();
		listCategoria = servicioCategoria.listarActivos();
		cargarFases();
		// actividadEntrenamiento = new ActividadEntrenamiento();
		// editar = false;
	}

	// Cierra la ventana al hacer click en el boton salir
	public void onClick$btnSalir() {
		winActividadEntrenamientoZul.detach();
	}

	// Actualiza las actividades mostradas en el LboxActividades
	public void actualizarListActividades() {
		if (cmbCategoria.getSelectedItem() != null
				&& cmbFase.getSelectedItem() != null) {
			listActividadEntrenamiento.clear();
			ActividadEntrenamiento act;
			for (Object o : servicioActividadEntrenamiento.buscarTodo(
					buscarCategoria(), buscarFase())) {
				act = (ActividadEntrenamiento) o;
				listActividadEntrenamiento.add(act);
			}
			binder.loadAll();
		}
	}

	public void onChange$cmbCategoria() {
		if (cmbCategoria.getSelectedItem() != null
				&& cmbFase.getSelectedItem() != null) {
			actualizarListActividades();
			binder.loadAll();
		} else {
			cmbFase.setDisabled(false);
			btnCancelar.setDisabled(false);
		}
	}

	public void onChange$cmbFase() {
		txtActividades.setDisabled(false);
		actualizarListActividades();
		binder.loadAll();
	}

	// Inicializa los componentes de la pantalla
	public void inicializar() {
		cmbCategoria.setValue("--SELECCIONE--");
		cmbFase.setValue("--SELECCIONE--");
		cmbFase.setDisabled(true);
		txtActividades.setText("");
		txtActividades.setDisabled(true);
		btnQuitar.setVisible(false);
		editar = false;
		actividadEntrenamiento = new ActividadEntrenamiento();
		btnGuardar.setDisabled(true);
	}

	public void onSelect$lboxActividades() {
		if (lboxActividades.getSelectedItem() != null) {
			btnQuitar.setVisible(true);
			Listitem item = lboxActividades.getSelectedItem();
			index = item.getIndex();
			if (item.getIndex() >= 0) {
				actividadEntrenamiento = (ActividadEntrenamiento) lboxActividades
						.getSelectedItem().getValue();
				txtActividades.setValue(actividadEntrenamiento.getNombre());
				editar = true;
			}
		} else {
			throw new WrongValueException(lboxActividades, "No existen items");
		}
	}

	public void onClick$btnAgregar() {
		if (!editar) {
			if (cmbCategoria.getSelectedItem() == null) {
				throw new WrongValueException(cmbCategoria,
						"Seleccione la Categoria");
			} else if (cmbFase.getSelectedItem() == null) {
				throw new WrongValueException(cmbFase, "Seleccione la Fase");
			} else if (txtActividades.getValue() == "") {
				throw new WrongValueException(txtActividades,
						"Ingrese una Actividad Nueva");
			} else {
				int i = 0;
				while (i < listActividadEntrenamiento.size()) {
					if (listActividadEntrenamiento.get(i).getNombre()
							.equals(txtActividades.getValue().toUpperCase())) {
						throw new WrongValueException(lboxActividades,
								"No puede agregar una actividad duplicada");
					}
					i++;
				}
				actividadEntrenamiento.setNombre(actividadEntrenamiento
						.getNombre().toUpperCase());
				actividadEntrenamiento.setCategoria(buscarCategoria());
				actividadEntrenamiento.setDatoBasico(buscarFase());
				actividadEntrenamiento.setEstatus('A');
				listActividadEntrenamiento.add(actividadEntrenamiento);
				actividadEntrenamiento = new ActividadEntrenamiento();
				binder.loadAll();
			}
		} else {
			if (txtActividades.getValue().isEmpty()) {
				throw new WrongValueException(txtActividades,
						"Ingrese una Actividad Nueva");
			} else {

				actividadEntrenamiento.setNombre(actividadEntrenamiento
						.getNombre().toUpperCase());
				listActividadEntrenamiento.set(index, actividadEntrenamiento);
				actividadEntrenamiento = new ActividadEntrenamiento();
				editar = false;
				cmbCategoria.setDisabled(false);
				cmbFase.setDisabled(false);
				binder.loadAll();
			}
		}
		btnGuardar.setDisabled(false);
	}

	public void onClick$btnGuardar() throws InterruptedException {
		int result = Messagebox.show("Desea guardar los cambios realizados?",
				"Question", Messagebox.OK | Messagebox.CANCEL,
				Messagebox.QUESTION);
		switch (result) {
		case Messagebox.OK:
			for (ActividadEntrenamiento ae : listActividadEntrenamiento) {
				if (ae.getCodigoActividadEntrenamiento() == 0) {
					ae.setCodigoActividadEntrenamiento(servicioActividadEntrenamiento
							.generarCodigo());
				}
				servicioActividadEntrenamiento.agregar(ae);
			}

			for (ActividadEntrenamiento ae : listActividadEntrenamientoEliminados) {
				ae.setEstatus('E');
				servicioActividadEntrenamiento.actualizar(ae);
			}
			actividadEntrenamiento = new ActividadEntrenamiento();
			alert("Cambios guardados con exito");
			onClick$btnCancelar();
			break;
		case Messagebox.CANCEL:
			actualizarListActividades();
			break;
		default:
			break;
		}

	}

	public void onClick$btnQuitar() {
		if (lboxActividades.getItems().size() != 0) {
			actividadEntrenamiento = (ActividadEntrenamiento) lboxActividades
					.getSelectedItem().getValue();
			// actividadEntrenamiento.setEstatus('E');
			listActividadEntrenamientoEliminados.add(actividadEntrenamiento);
			listActividadEntrenamiento.remove(lboxActividades
					.getSelectedIndex());
			editar = false;
			actividadEntrenamiento = new ActividadEntrenamiento();
			binder.loadAll();
		}
	}

	public void onClick$btnCancelar() {
		inicializar();
		int numElementos = lboxActividades.getItems().size();
		for (int I = 0; I < numElementos; I++) {
			lboxActividades.removeItemAt(0);
		}
	}

}
