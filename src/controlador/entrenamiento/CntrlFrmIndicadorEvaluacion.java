package controlador.entrenamiento;

import java.util.List;
import java.util.Set;
import modelo.ActividadEntrenamiento;
import modelo.Categoria;
import modelo.DatoBasico;
import modelo.EscalaMedicion;
import modelo.IndicadorActividadEscala;
import modelo.TipoDato;
import modelo.ValorEscala;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioActividadEntrenamiento;
import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioEscalaMedicion;
import servicio.implementacion.ServicioIndicadorActividadEscala;
import servicio.implementacion.ServicioTipoDato;

public class CntrlFrmIndicadorEvaluacion extends GenericForwardComposer {
	Combobox cmbEscala, cmbCategoria, cmbActividad, cmbIndicador,
			cmbTipoEscala;
	Textbox txtValores;
	Window wndIndicadorEvaluacion;
	Listbox lboxIndicador;
	Button btnAgregar, btnCancelar, btnSalir, btnModificar, btnQuitar;
	ServicioCategoria servicioCategoria;
	ServicioActividadEntrenamiento servicioActividadEntrenamiento;
	ServicioTipoDato servicioTipoDato;
	ServicioDatoBasico servicioDatoBasico;
	ServicioEscalaMedicion servicioEscalaMedicion;
	ServicioIndicadorActividadEscala servicioIndicadorActividadEscala;
	IndicadorActividadEscala indicador;
	boolean editar;
	List<Categoria> listCategoria;
	List<EscalaMedicion> listEscala;
	List<ActividadEntrenamiento> listActividad;
	List<DatoBasico> listIndicador;
	List<IndicadorActividadEscala> listIndicadorAE;
	List<DatoBasico> listTipoEscala;
	Integer codigoI;
	AnnotateDataBinder binder;
	int index;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("ctrl", this, true);
		listCategoria = servicioCategoria.listar();
		listEscala = servicioEscalaMedicion.listar();
		listActividad = servicioActividadEntrenamiento.listar();
		listIndicador = servicioDatoBasico.buscarPorTipoDato(servicioTipoDato
				.buscarPorTipo("INDICADOR ENTRENAMIENTO"));
		listTipoEscala = servicioDatoBasico.buscarPorTipoDato(servicioTipoDato
				.buscarPorTipo("ESCALA MEDICION"));
		codigoI = (Integer) servicioIndicadorActividadEscala
				.getDaoIndicadorActividadEscala().generarCodigo(
						IndicadorActividadEscala.class);
		editar = false;
		index = 0;
		indicador = new IndicadorActividadEscala();
	}

	public List<DatoBasico> getListTipoEscala() {
		return listTipoEscala;
	}

	public void setListTipoEscala(List<DatoBasico> listTipoEscala) {
		this.listTipoEscala = listTipoEscala;
	}

	public List<ActividadEntrenamiento> getListActividad() {
		return listActividad;
	}

	public List<DatoBasico> getListIndicador() {
		return listIndicador;
	}

	public void setListIndicador(List<DatoBasico> listIndicador) {
		this.listIndicador = listIndicador;
	}

	public void setListActividad(List<ActividadEntrenamiento> listActividad) {
		this.listActividad = listActividad;
	}

	public IndicadorActividadEscala getIndicador() {
		return indicador;
	}

	public void setIndicador(IndicadorActividadEscala indicador) {
		this.indicador = indicador;
	}

	public List<EscalaMedicion> getListEscala() {
		return listEscala;
	}

	public void setListEscala(List<EscalaMedicion> listEscala) {
		this.listEscala = listEscala;
	}

	public List<Categoria> getListCategoria() {
		return listCategoria;
	}

	public void setListCategoria(List<Categoria> listCategoria) {
		this.listCategoria = listCategoria;
	}

	public void limpiarCombos(Combobox combo) {
		combo.getItems().clear();
	}

	public void limpiarListbox(Listbox listbox) {
		listbox.getItems().clear();
	}

	public void onChange$cmbCategoria() {
		if (cmbCategoria.getSelectedItem() != null) {
			txtValores.setValue("");
			cmbActividad.setDisabled(!true);
			limpiarCombos(cmbActividad);
			cmbActividad.setValue("-Seleccione-");
			limpiarListbox(lboxIndicador);
			ActividadEntrenamiento ae;
			Categoria cat = (Categoria) cmbCategoria.getSelectedItem()
					.getValue();
			listActividad = servicioActividadEntrenamiento
					.buscarPorCategoria(cat);
			if (listActividad.size() > 0) {
				for (Object o : listActividad) {
					ae = (ActividadEntrenamiento) o;
					Comboitem combo = new Comboitem();
					combo.setLabel(ae.getNombre());
					combo.setValue(ae);
					cmbActividad.appendChild(combo);
				}
				cmbActividad.setSelectedIndex(-1);
			} else {
				cmbCategoria.setSelectedIndex(-1);
				cmbCategoria.setValue("-Seleccione-");
				throw new WrongValueException(cmbCategoria,
						"No existen Actividades asociadas a esta Categoria");
			}
		}
	}

	public void onCreate$wndIndicadorEvaluacion() {
		onClick$btnCancelar();
	}

	public void inicializar() {
		cmbIndicador.setValue("-Seleccione-");
	}

	public void llenarListbox(String item1, String item2,
			Set<ValorEscala> escalas, Integer cod) {
		Listitem nvoItem = new Listitem();
		nvoItem.appendChild(new Listcell(item1));
		String s = "[";
		for (ValorEscala ve : escalas) {
			s += "" + ve.getValor() + ",";
		}
		s = s.substring(0, s.length() - 1);
		s += "]";
		nvoItem.appendChild(new Listcell(item2 + " " + s));
		nvoItem.setValue(cod);
		lboxIndicador.appendChild(nvoItem);
	}

	public void onClick$btnAgregar() {
		if (!editar) {
			if (cmbCategoria.getSelectedItem() == null
					&& cmbCategoria.getValue().equals("-Seleccione-")) {
				throw new WrongValueException(cmbCategoria,
						"Seleccione la Categoria a la que desea asociar el indicador");
			} else {
				if (cmbActividad.getSelectedItem() == null
						&& cmbActividad.getValue().equals("-Seleccione-")) {
					throw new WrongValueException(cmbCategoria,
							"Seleccione la Actividad a la que desea asociar el indicador");
				} else {
					if (cmbIndicador.getSelectedItem() == null
							&& cmbIndicador.getValue().equals("-Seleccione-")) {
						throw new WrongValueException(cmbCategoria,
								"Seleccione el Indicador que desea Registrar");
					} else {
						if (cmbEscala.getSelectedItem() == null
								&& cmbEscala.getValue().equals("-Seleccione-")) {
							throw new WrongValueException(cmbCategoria,
									"Debe selecionar una Escala de Medición");
						} else {
							int i = 0;
							boolean repetido = false;
							while (i < lboxIndicador.getItems().size()) {
								Listitem item = lboxIndicador.getItemAtIndex(i);
								Listcell lc = (Listcell) item.getChildren()
										.get(0);
								if (cmbIndicador.getSelectedItem().getValue()
										.equals(lc.getLabel())) {
									repetido = true;
									throw new WrongValueException(cmbCategoria,
											"El Indicador ya se encuentra registrado");
								}
								i++;
							}
							if (!repetido) {
								indicador
										.setActividadEntrenamiento((ActividadEntrenamiento) cmbActividad
												.getSelectedItem().getValue());
								indicador
										.setEscalaMedicion((EscalaMedicion) cmbEscala
												.getSelectedItem().getValue());
								indicador
										.setDatoBasico((DatoBasico) cmbIndicador
												.getSelectedItem().getValue());
								indicador.setEstatus('A');
								servicioIndicadorActividadEscala
										.guardar(indicador);
								llenarListbox(
										cmbIndicador.getSelectedItem()
												.getLabel(),
										cmbEscala.getSelectedItem().getLabel(),
										indicador.getEscalaMedicion()
												.getValorEscalas(),
										indicador
												.getCodigoIndicadorActividadEscala());
								alert("El indicador ha sido guardado con Exito!");
								binder.loadAll();
								inicializar();
							}
						}
					}
				}
			}
		} else {
			editar = false;
			int i = 0;
			boolean repetido = false;
			while (i < lboxIndicador.getItems().size()) {
				Listitem item = lboxIndicador.getItemAtIndex(i);
				Listcell lc = (Listcell) item.getChildren().get(0);
				if (cmbIndicador.getSelectedItem().getValue()
						.equals(lc.getLabel())) {
					repetido = true;
					break;
				}
				i++;
			}
			if (!repetido) {
				indicador.setEscalaMedicion((EscalaMedicion) cmbEscala
						.getSelectedItem().getValue());
				indicador.setDatoBasico((DatoBasico) cmbIndicador
						.getSelectedItem().getValue());
				lboxIndicador.removeItemAt(index);
				servicioIndicadorActividadEscala.actualizar(indicador);
				llenarListbox(cmbIndicador.getSelectedItem().getLabel(),
						cmbEscala.getSelectedItem().getLabel(), indicador
								.getEscalaMedicion().getValorEscalas(),
						indicador.getCodigoIndicadorActividadEscala());
				alert("El Indicador se ha actualizado con exito!");
				binder.loadAll();
				inicializar();
			}
		}
	}

	public void onClick$btnCancelar() {
		inicializar();
		cmbCategoria.setValue("--Seleccione--");
		cmbActividad.setValue("--Seleccione--");
		cmbTipoEscala.setValue("--Seleccione--");
		cmbIndicador.setValue("--Seleccione--");
		cmbEscala.setValue("--Seleccione--");
		txtValores.setText("");
		limpiarListbox(lboxIndicador);
		cmbActividad.setDisabled(true);
		cmbIndicador.setDisabled(true);
		cmbTipoEscala.setDisabled(true);
		cmbEscala.setDisabled(true);
		btnQuitar.setVisible(false);
	}

	public void onSelect$lboxIndicador() {
		if (lboxIndicador.getItems().size() > 0) {
			btnQuitar.setVisible(true);
			modificar();
		}
	}

	public void onChange$cmbTipoEscala() {
		if (cmbTipoEscala.getSelectedItem() != null) {
			cmbEscala.setDisabled(!true);
			limpiarCombos(cmbEscala);
			cmbEscala.setValue("-Seleccione-");
			EscalaMedicion escala;
			DatoBasico tipo = (DatoBasico) cmbTipoEscala.getSelectedItem()
					.getValue();
			listEscala = servicioEscalaMedicion.listarPorTipoEscala(tipo);
			if (listEscala.size() > 0) {
				for (Object o : listEscala) {
					escala = (EscalaMedicion) o;
					Comboitem combo = new Comboitem();
					combo.setLabel(escala.getNombre());
					combo.setValue(escala);
					cmbEscala.appendChild(combo);
				}
				cmbEscala.setSelectedIndex(-1);
			} else {
				cmbCategoria.setSelectedIndex(-1);
				cmbCategoria.setValue("-Seleccione-");
				throw new WrongValueException(cmbCategoria,
						"No existen Escalas asociadas a este Tipo Escala");
			}
		}
	}

	public void onChange$cmbActividad() {
		cmbTipoEscala.setDisabled(false);
		limpiarListbox(lboxIndicador);
		ActividadEntrenamiento actividad = (ActividadEntrenamiento) cmbActividad
				.getSelectedItem().getValue();
		listIndicadorAE = servicioIndicadorActividadEscala
				.buscarporActividad(actividad);
		if (listIndicadorAE.size()>0){
			for (IndicadorActividadEscala o : listIndicadorAE) {
				llenarListbox(o.getDatoBasico().getNombre(), o.getEscalaMedicion()
						.getNombre(), o.getEscalaMedicion().getValorEscalas(),
						o.getCodigoIndicadorActividadEscala());
			}
		DatoBasico dato = servicioIndicadorActividadEscala.listar().get(0).getDatoBasico().getDatoBasico();
		moverIndexCombo(cmbTipoEscala, dato.getNombre());
		cmbTipoEscala.setDisabled(true);
		EscalaMedicion escala = listIndicadorAE.get(0).getEscalaMedicion();
		moverIndexCombo(cmbEscala, escala.getNombre());
		cmbEscala.setDisabled(true);
		onChange$cmbEscala();
		}
	}

	public List<DatoBasico> filtroCmbIndicador(List<DatoBasico> lista,
			List<IndicadorActividadEscala> listaI) {
		Integer tam = lista.size();
		for (int i = 0; i < tam; i++) {
			DatoBasico indicador = lista.get(i);
			for (int k = 0; k < listaI.size(); k++)
				if (listaI.get(k).getDatoBasico().getNombre()
						.equals(indicador.getNombre())) {
					lista.remove(indicador);
					tam--;
					i--;
				}
		}
		return lista;
	}

	public void onChange$cmbIndicador() {
		cmbEscala.setDisabled(true);
	}

	public void onChange$cmbEscala() {
		EscalaMedicion escala = (EscalaMedicion) cmbEscala.getSelectedItem()
				.getValue();
		String s = "[";
		for (ValorEscala ve : escala.getValorEscalas()) {
			s += "" + ve.getValor() + ",";
		}
		s = s.substring(0, s.length() - 1);
		s += "]";
		txtValores.setValue(s);
		cmbIndicador.setDisabled(false);
		cmbTipoEscala.setDisabled(true);
		String tipo = cmbTipoEscala.getSelectedItem().getLabel();
		TipoDato td = servicioTipoDato.buscarPorTipo("INDICADOR ENTRENAMIENTO");
		DatoBasico db = servicioDatoBasico.buscarPorString(tipo);
		List<DatoBasico> listInd = (List<DatoBasico>) servicioDatoBasico.getDaoDatoBasico()
				.listarDosCampos(DatoBasico.class, "tipoDato", td,
						"datoBasico", db);
		listIndicador = filtroCmbIndicador(listInd, listIndicadorAE);
		binder.loadComponent(cmbIndicador);
	}

	public void onClick$btnSalir() {
		wndIndicadorEvaluacion.detach();
	}

	public void onClick$btnQuitar() {
		int n = lboxIndicador.getSelectedIndex();
		servicioIndicadorActividadEscala.eliminar(indicador);
		lboxIndicador.removeItemAt(n);
		alert("El Indicador se ha eliminado con exito!");
		index = 0;
		txtValores.setValue("");
		cmbEscala.setSelectedIndex(-1);
		cmbEscala.setValue("-Seleccione-");
		cmbIndicador.setSelectedIndex(-1);
		cmbIndicador.setValue("-Seleccione-");
	}

	public void moverIndexCombo(Combobox combo, String nombre) {
		if (combo.getItems().size() > 0) {
			int ind;
			int c = combo.getItems().size();
			for (int i = 0; i < c; i++) {
				if (nombre.equals(combo.getItemAtIndex(i).getLabel())) {
					ind = i;
					combo.setSelectedIndex(ind);
					break;
				}
			}
		}
	}

	public void modificar() {
		listIndicador = servicioDatoBasico.buscarPorTipoDato(servicioTipoDato
				.buscarPorTipo("INDICADOR ENTRENAMIENTO"));
		binder.loadComponent(cmbIndicador);
		if (lboxIndicador.getSelectedIndex() >= 0) {
			editar = true;
			Integer codigo = (Integer) lboxIndicador.getSelectedItem()
					.getValue();
			indicador = servicioIndicadorActividadEscala
					.buscarporCodigo(codigo);
			moverIndexCombo(cmbIndicador, indicador.getDatoBasico().getNombre());
			moverIndexCombo(cmbEscala, indicador.getEscalaMedicion()
					.getNombre());
			onChange$cmbEscala();
			indicador.setCodigoIndicadorActividadEscala(codigo);
			index = lboxIndicador.getSelectedIndex();
			cmbIndicador.focus();
			cmbEscala.setDisabled(false);
		}
	}
}