package controlador.entrenamiento;

import java.util.List;

import modelo.ActividadEntrenamiento;
import modelo.Categoria;
import modelo.DatoBasico;
import modelo.EscalaMedicion;
import modelo.IndicadorActividadEscala;
import modelo.ValorEscala;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioActividadEntrenamiento;
import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioEscalaMedicion;
import servicio.implementacion.ServicioIndicadorActividadEscala;
import servicio.implementacion.ServicioTipoDato;

public class CntrlGestionarIndicadores extends GenericForwardComposer {
	Combobox cmbEscala, cmbCategoria, cmbActividad, cmbIndicador;
	Window wndIndicadorEvaluacion;
	Listbox lbIndicador;
	Button btnAgregar, btnCancelar, btnSalir, btnModificar, btnQuitar;
	ServicioCategoria servicioCategoria;
	ServicioActividadEntrenamiento servicioActividadEntrenamiento;
	ServicioTipoDato servicioTipoDato;
	ServicioDatoBasico servicioDatoBasico;
	ServicioEscalaMedicion servicioEscalaMedicion;
	ServicioIndicadorActividadEscala servicioIndicador;
	IndicadorActividadEscala indicador;
	boolean editar;
	List<Categoria> listCategoria;
	List<EscalaMedicion> listEscala;
	List<ActividadEntrenamiento> listActividad;
	List<DatoBasico> listIndicador;

	AnnotateDataBinder binder;

	public List<DatoBasico> getListIndicador() {
		return listIndicador;
	}

	public void setListIndicador(List<DatoBasico> listIndicador) {
		this.listIndicador = listIndicador;
	}

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("ctrl", this, true);
		listCategoria = servicioCategoria.listar();
		listEscala = servicioEscalaMedicion.listar();
		listActividad = servicioActividadEntrenamiento.listar();
		System.out.println(servicioTipoDato.buscarTipo(
				"INDICADOR ENTRENAMIENTO").getDescripcion());
		listIndicador = servicioDatoBasico.buscarPorTipoDato(servicioTipoDato
				.buscarTipo("INDICADOR ENTRENAMIENTO"));
		System.out.println(listIndicador.size());
		editar = false;
		indicador = new IndicadorActividadEscala();
	}

	public List<ActividadEntrenamiento> getListActividad() {
		return listActividad;
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

	public Categoria buscarCategoria() {
		Categoria c;
		for (Object o : servicioCategoria.listar()) {
			c = (Categoria) o;
			if (c.getCodigoCategoria() == Integer.parseInt(""
					+ cmbCategoria.getSelectedItem().getValue()))
				;
			return c;
		}
		return null;
	}

	public ActividadEntrenamiento buscarActividad() {
		ActividadEntrenamiento ae;
		ae = servicioActividadEntrenamiento.buscarPorCodigo(Integer.parseInt(""
				+ cmbActividad.getSelectedItem().getValue()));
		return ae;
	}

	public EscalaMedicion buscarEscala() {
		EscalaMedicion c;
		c = servicioEscalaMedicion.buscar(Integer.parseInt(""
				+ (cmbEscala.getSelectedItem().getValue())));
		return c;
	}

	public void onSelect$cmbCategoria() {
		if (cmbCategoria.getSelectedItem() != null) {
			cmbActividad.setDisabled(!true);
			cmbEscala.setDisabled(!true);
			int c = cmbActividad.getItems().size();
			for (int i = 0; i < c; i++)
				cmbActividad.removeItemAt(0);
			ActividadEntrenamiento ae;
			for (Object o : servicioActividadEntrenamiento
					.buscarPorCategoria(buscarCategoria())) {
				ae = (ActividadEntrenamiento) o;
				Comboitem combo = new Comboitem();
				combo.setLabel(ae.getNombre());
				combo.setValue(ae.getCodActividadEntrenamiento());
				cmbActividad.appendChild(combo);
			}
			cmbActividad.setSelectedIndex(-1);
		}
	}

	public void onCreate$wndIndicadorEvaluacion() {
		onClick$btnCancelar();
	}

	public void inicializar() {
		cmbIndicador.setValue("-Seleccione-");
		cmbEscala.setValue("-Seleccione-");
	}

	public void onClick$btnAgregar() {
		if (!editar) {
			if (cmbCategoria.getSelectedItem() != null
					&& cmbCategoria.getValue().equals("-Seleccione-")) {
				alert("Seleccine la Categoria a la que desea asociar el indicador");
				cmbCategoria.focus();

			} else {
				if (cmbActividad.getSelectedItem() != null
						&& cmbActividad.getValue().equals("-Seleccione-")) {
					alert("Seleccione la Actividad a la que desea asociar el indicador");
					cmbActividad.focus();
				} else {
					if (cmbIndicador.getSelectedItem().getLabel() == "-Seleccione-") {
						alert("Seleccione el Indicador que desea Registrar");
						cmbIndicador.focus();
					} else {
						if (cmbEscala.getSelectedItem() != null
								&& cmbEscala.getValue().equals(
										"-Escala de Medicion-")) {
							alert("Debe selecionar una Escala de Medición");
							cmbEscala.focus();
						} else {
							int i = 0;
							boolean repetido = true;
							while (i < lbIndicador.getItems().size()) {
								Listitem item = lbIndicador.getItemAtIndex(i);
								Listcell lc = (Listcell) item.getChildren()
										.get(0);
								if (cmbIndicador.getSelectedItem().getLabel()
										.equals(lc.getLabel())) {
									alert("El Indicador ya se encuentra registrado");
									repetido = false;
								}
								i++;
							}
							if (repetido) {
								indicador
										.setActividadEntrenamiento(buscarActividad());
								Integer codigo;
								if (servicioIndicador.listar().equals(null)){
									codigo = 1;
								}else{
									codigo = servicioIndicador
											.listar().size()+1;
									System.out.println(codigo);
								}
								indicador
										.setCodigoIndicadorActividadEscala(codigo);
								indicador.setEscalaMedicion(buscarEscala());
								indicador.setEstatus('A');

								Listitem nvoItem = new Listitem();
								alert(nvoItem.toString());
								nvoItem.appendChild(new Listcell(cmbIndicador
										.getSelectedItem().getLabel()));
								String s = "[";
								for (ValorEscala ve : indicador
										.getEscalaMedicion().getValorEscalas()) {
									s += "" + ve.getDescripcion() + ",";
								}
								s = s.substring(0, s.length() - 1);
								s += "]";
								nvoItem.appendChild(new Listcell(cmbEscala
										.getSelectedItem().getLabel() + " " + s));
								lbIndicador.appendChild(nvoItem);
								servicioIndicador.guardar(indicador);

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
			boolean repetido = true;
			while (i < lbIndicador.getItems().size()) {
				Listitem item = lbIndicador.getItemAtIndex(i);
				Listcell lc = (Listcell) item.getChildren().get(0);
				if (cmbIndicador.getSelectedItem().getLabel()
						.equals(lc.getLabel())) {
					repetido = false;
					break;
				}
				i++;
			}
			if (repetido) {
				indicador.setEscalaMedicion(buscarEscala());
				Listitem nvoItem = new Listitem();
				alert(nvoItem.toString());
				nvoItem.appendChild(new Listcell(cmbIndicador.getSelectedItem()
						.getLabel()));
				String s = "[";
				for (ValorEscala ve : indicador.getEscalaMedicion()
						.getValorEscalas()) {
					s += "" + ve.getDescripcion() + ",";
				}
				s = s.substring(0, s.length() - 1);
				s += "]";
				nvoItem.appendChild(new Listcell(cmbEscala.getSelectedItem()
						.getLabel() + " " + s));
				lbIndicador.appendChild(nvoItem);
				servicioIndicador.actualizar(indicador);
				binder.loadAll();
				inicializar();
			}
		}
	}

	public void onClick$btnCancelar() {
		inicializar();
		cmbCategoria.setValue("-Seleccione-");
		cmbActividad.setValue("-Seleccione-");
		if (lbIndicador.getItems().size() > 0) {
			int numItems = lbIndicador.getItems().size();
			for (int i = 0; i < numItems; i++) {
				lbIndicador.removeItemAt(0);
			}
		}
		cmbActividad.setDisabled(true);
		cmbIndicador.setDisabled(true);
		cmbEscala.setDisabled(true);

		btnCancelar.setVisible(false);
		btnModificar.setVisible(false);
		btnQuitar.setVisible(false);
	}

	public void onSelect$lbIndicador() {
		if (lbIndicador.getItems().size() > 0) {
			btnModificar.setVisible(true);
			btnQuitar.setVisible(true);

		}
	}

	public void onChange$cmbCategoria() {
		cmbActividad.setDisabled(false);
		btnCancelar.setVisible(true);
	}

	public void onChange$cmbActividad() {
		cmbEscala.setDisabled(false);
		cmbIndicador.setDisabled(false);
	}

	public void onClick$btnSalir() {
		wndIndicadorEvaluacion.detach();
	}

	public void onClick$btnQuitar() {
		int n = lbIndicador.getSelectedIndex();
		lbIndicador.removeItemAt(n);

	}

	public void onClick$btnModificar() {
		if (lbIndicador.getSelectedIndex() >= 0) {
			editar = true;
			Listcell lc1 = (Listcell) lbIndicador.getSelectedItem()
					.getChildren().get(0);
			Listcell lc2 = (Listcell) lbIndicador.getSelectedItem()
					.getChildren().get(1);

			cmbIndicador.setText(lc1.getLabel());
			cmbEscala.setValue(lc2.getLabel());
			lbIndicador.removeItemAt(lbIndicador.getSelectedIndex());
			cmbIndicador.focus();
		}
	}

}
