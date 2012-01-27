package controlador.jugador;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import comun.TipoDatoBasico;

import modelo.Categoria;

import modelo.Divisa;
import modelo.Equipo;

import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioDatoBasico;
import modelo.DatoBasico;

import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioDivisa;

/**
 * Clase controladora de los eventos de la vista de igual nombre el presente
 * controlador se encarga de agregar, eliminar logicamente, la modificacion se
 * ha pospuesto momentaneamente por problemas con los combos
 * 
 * @author Robert A
 * @author Miguel B
 * 
 * @version 2.0 29/12/2011
 * 
 * */
public class CntrlConfigurarEquipo extends GenericForwardComposer {
	private ServicioCategoria servicioCategoria;
	private ServicioEquipo servicioEquipo;
	private ServicioDivisa servicioDivisa;
	private ServicioDatoBasico servicioDatoBasico;

	private DatoBasico clasificacion = new DatoBasico();
	private Equipo equipo = new Equipo();
	private Categoria categoria = new Categoria();
	private Divisa divisa = new Divisa();

	Listbox listEquipo;
	List<Equipo> equipos = new ArrayList<Equipo>();
	List<DatoBasico> tip = new ArrayList<DatoBasico>();
	List<DatoBasico> tip2 = new ArrayList<DatoBasico>();

	private Textbox txtNombre;
	private Combobox cmbTipo;
	private Combobox cmbCategoria;
	private Combobox cmbDivisa;

	private AnnotateDataBinder binder;

	public Divisa getDivisa() {
		return divisa;
	}

	public void setDivisa(Divisa divisa) {
		this.divisa = divisa;
	}

	public DatoBasico getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(DatoBasico clasificacion) {
		this.clasificacion = clasificacion;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, false); // Hacemos visible el
														// modelo para el
														// databinder
		equipos = servicioEquipo.listar();
	}

	public List<Categoria> getCategorias() {
		return servicioCategoria.listar();

	}

	public List<Equipo> getEquipos() {
		return equipos;
		// return servicioEquipo.listar();

	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}

	public List<Divisa> getDivisas() {
		return servicioDivisa.listar();

	}

	public List<DatoBasico> getTipos() {
		tip.clear();
		tip2.clear();
		tip = servicioDatoBasico.buscar(TipoDatoBasico.CLASIFICACION_EQUIPO);
		for (int i = 0; i < categoria.getCantidadEquipo(); i++) {
			tip2.add(tip.get(i));
		}
		return tip2;
	}

	public void onClick$btnAgregar() {
		// Divisa y Clasificacion fijas
		// equipo.setDivisa(servicioDivisa.listar().get(0));
      
		for (int i = 0; i < equipos.size(); i++) {
			if (cmbTipo.getSelectedItem().getLabel()
					.equals(equipos.get(i).getDatoBasicoByCodigoClasificacion().getNombre())) {//MODIFICADO

				if (cmbCategoria.getSelectedItem().getLabel()
						.equals(equipos.get(i).getCategoria().getNombre())) {

					alert("Ya existe el equipo para la categoria");
					return;
				}

			}
		}
		equipo.setDivisa(divisa);
		equipo.setEstatus('A');
		equipo.setDatoBasicoByCodigoClasificacion(clasificacion);//MODIFICADO
		equipo.setCategoria(categoria);
		equipo.setNombre(txtNombre.getValue());
		equipo.setCodigoEquipo(servicioEquipo.listar().size() + 1);
		servicioEquipo.agregar(equipo);
		try {
			Messagebox.show("Equipo agregado", "Exito", Messagebox.OK,
					Messagebox.INFORMATION);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		equipos.add(equipo);
		limpiar();
		binder.loadAll();

	}

	public void onSelect$listEquipo() {
		/*
		 * int n = listEquipo.getSelectedIndex();
		 * System.out.println(cmbCategoria.getItemCount());
		 * cmbCategoria.setSelectedItem(cmbCategoria.getItemAtIndex(2));
		 * equipos.get(n).getCodigoEquipo();
		 * txtNombre.setValue(equipos.get(n).getNombre().toString());
		 * cmbCategoria.setValue(equipos.get(n).getCategoria().getNombre());
		 * cmbDivisa.setValue(equipos.get(n).getDivisa().getNombre());
		 * cmbTipo.setValue(equipos.get(n).getDatoBasico().getNombre());
		 */
	}

	/*
	 * public void onClick$btnEditar() { if (listEquipo.getSelectedIndex() >= 0)
	 * { Equipo equipoSeleccionado = (Equipo) listEquipo.getSelectedItem()
	 * .getValue(); if (equipoSeleccionado != null) {
	 * equipos.remove(equipoSeleccionado); equipo.setDivisa(divisa);
	 * equipo.setEstatus('A'); equipo.setDatoBasico(clasificacion);
	 * equipo.setCategoria(categoria); equipo.setNombre(txtNombre.getValue());
	 * equipo.setCodigoEquipo(equipoSeleccionado.getCodigoEquipo());
	 * servicioEquipo.actualizar(equipo); equipos.add(equipo); limpiar(); } } }
	 */

	public void onClick$btnQuitar() {
		if (listEquipo.getSelectedIndex() >= 0) {
			Equipo equipoSeleccionado = (Equipo) listEquipo.getSelectedItem()
					.getValue();
			  if (servicioEquipo.buscarPorCodigo(equipo)==false)
					//System.out.println(servicioCategoria.buscarPorCodigo(categoria));
						alert ("No se puede borrar mientras existan equipos en esta categoria");
			  else
			if (equipoSeleccionado != null) {
				equipos.remove(equipoSeleccionado);
				servicioEquipo.eliminar(equipoSeleccionado);
				limpiar();
			}
		} else {
			alert("Seleccione un dato");
		}
	}

	public void limpiar() {
		equipo = new Equipo();
		categoria = new Categoria();
		divisa = new Divisa();
		binder.loadAll();
		txtNombre.setValue("");
	}

	public void onClick$btnCancelar() {
		limpiar();
	}

}
