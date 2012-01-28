/**
 * Una clase para registrar las actividades de entrenamiento
 * de cada una de las fases de entrenamiento pertenecientes a cada 
 * categoria.
 * @version 1.0, 09/01/12
 * @author Oscar Gutierrez, Alnis Piñero
 */

package controlador.entrenamiento;

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
	Button btnSalir, btnCancelar, btnAgregar, btnQuitar;
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
	AnnotateDataBinder binder;
	boolean editar;
	int index;

	public void cargarFases() {
		listFase = servicioDatoBasico.buscarPorTipoDato(servicioTipoDato
				.buscarPorTipo("FASE"));
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
		actividadEntrenamiento = new ActividadEntrenamiento();
		listCategoria = servicioCategoria.getDaoCategoria().listarActivos(
				Categoria.class);
		cargarFases();
		editar = false;
	}

	// Cierra la ventana al hacer click en el boton salir
	public void onClick$btnSalir() {
		winActividadEntrenamientoZul.detach();
	}

	//
	public void onCreate$winActividadEntrenamiento() {
		inicializar();
	}

	// Actualiza las actividades mostradas en el LboxActividades
	public void actualizarLboxActividades() {
		if (cmbCategoria.getSelectedItem() != null
				&& cmbFase.getSelectedItem() != null) {
			int cant = lboxActividades.getItemCount();
			for (int i = 0; i < cant; i++) {
				lboxActividades.removeItemAt(0); // Borra todas las actividades
													// que esten en el
													// lboxActividades
			}
			ActividadEntrenamiento act;
			for (Object o : servicioActividadEntrenamiento.buscarTodo(
					buscarCategoria(), buscarFase())) {
				act = (ActividadEntrenamiento) o;
				lboxActividades.appendItem(act.getNombre(),
						"" + act.getCodigoActividadEntrenamiento());
			}
		}
	}

	public void onSelect$cmbCategoria() {
		cmbFase.setDisabled(false);
		btnCancelar.setDisabled(false);
		actualizarLboxActividades();
	}

	public void onSelect$cmbFase() {
		txtActividades.setDisabled(false);
		actualizarLboxActividades();
	}

	public void inicializar() {
		cmbCategoria.setValue("--Seleccione--");
		cmbFase.setValue("--Seleccione--");
		txtActividades.setText("");
		btnQuitar.setVisible(false);
	}

	public void onSelect$lboxActividades() {
		if (lboxActividades.getSelectedItem()!= null){
		btnQuitar.setVisible(true);
		Listitem item = lboxActividades.getSelectedItem();
		if (item.getIndex() >= 0) {
			actividadEntrenamiento = servicioActividadEntrenamiento
					.buscarClaveForegn(
							buscarCategoria(),
							buscarFase(),
							Integer.parseInt(""
									+ lboxActividades.getSelectedItem()
											.getValue()));
			binder.loadAll();
			editar = true;
		}
		index = lboxActividades.getSelectedIndex();
		}else{
			throw new WrongValueException(lboxActividades,
					"No existen items");
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
				while (i < lboxActividades.getItemCount()) {
					if (lboxActividades.getItemAtIndex(i).getLabel()
							.equals(txtActividades.getValue().toUpperCase())) {
						throw new WrongValueException(lboxActividades,
								"No puede agregar una actividad duplicada");
					}
					i++;
				}
				lboxActividades.appendItem(txtActividades.getValue()
						.toUpperCase(), String
						.valueOf((servicioActividadEntrenamiento.listar()
								.size() + 1)));
				txtActividades.setText("");
				actividadEntrenamiento
						.setCodigoActividadEntrenamiento(servicioActividadEntrenamiento
								.listar().size() + 1);
				actividadEntrenamiento.setCategoria(buscarCategoria());
				actividadEntrenamiento.setDatoBasico(buscarFase());
				actividadEntrenamiento.setNombre(actividadEntrenamiento
						.getNombre().toUpperCase());
				actividadEntrenamiento.setEstatus('A');
				servicioActividadEntrenamiento.agregar(actividadEntrenamiento);
				actividadEntrenamiento.setNombre("");
				binder.loadAll();
			}
		} else {
			if (txtActividades.getValue().isEmpty()) {
				throw new WrongValueException(txtActividades,
						"Ingrese una Actividad Nueva");
			} else {
				editar = false;
				servicioActividadEntrenamiento
						.actualizar(actividadEntrenamiento);
				lboxActividades.appendItem(
						txtActividades.getValue().toUpperCase(),
						""
								+ actividadEntrenamiento
										.getCodigoActividadEntrenamiento());
				lboxActividades.removeItemAt(index);
				cmbCategoria.setDisabled(false);
				cmbFase.setDisabled(false);
				txtActividades.setValue("");
			}
		}
	}

	public void onClick$btnQuitar() {
		if (lboxActividades.getItems().size() != 0) {
			actividadEntrenamiento = servicioActividadEntrenamiento
					.buscarClaveForegn(
							buscarCategoria(),
							buscarFase(),
							Integer.parseInt(""
									+ lboxActividades.getSelectedItem()
											.getValue()));
			actividadEntrenamiento.setEstatus('E');
			servicioActividadEntrenamiento.actualizar(actividadEntrenamiento);
			lboxActividades.removeItemAt(lboxActividades.getSelectedItem()
					.getIndex());
			actividadEntrenamiento.setNombre("");
			binder.loadAll();
		}
	}

	public void onClick$btnCancelar() {
		inicializar();
		int numElementos = lboxActividades.getItems().size();
		for (int I = 0; I < numElementos; I++) {
			lboxActividades.removeItemAt(0);
		}
		cmbFase.setDisabled(true);
		txtActividades.setDisabled(true);
	}

}
