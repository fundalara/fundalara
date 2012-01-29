package controlador.administracion;

import modelo.*;

import org.hibernate.cfg.AnnotationBinder;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;
import org.zkoss.zul.event.ListDataListener;

import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioPersonaJuridica;
import servicio.implementacion.ServicioPersonaNatural;
import servicio.implementacion.ServicioTipoDato;

import java.util.*;

public class CntrlCompromiso extends GenericForwardComposer {

	AnnotateDataBinder binder;
	
	ServicioPersonaNatural servicioPersonaNatural;
	ServicioPersonaJuridica servicioPersonaJuridica;
	ServicioDatoBasico servicioDatoBasico;
	ServicioTipoDato servicioTipoDato;
	ServicioEquipo servicioEquipo;
	ServicioCategoria servicioCategoria;
	
	Persona persona;
	PersonaNatural personaNatural;
	PersonaJuridica personaJuridica;
	DatoBasico tipoPersona;
	Equipo equipo;
	Categoria categoria;
	
	List<DatoBasico> tipoPersonas = new ArrayList<DatoBasico>();
	List<DatoBasico> tipoNaturales = new ArrayList<DatoBasico>();
	List<Equipo> listaEquipos = new ArrayList<Equipo>();
	List<Categoria> listaCategorias = new ArrayList<Categoria>();

	Row rwPersona, rwTipoPersona, rwEquipoCat;
	Combobox cmbPersona, cmbFiltrado, cmbEquipoCat, cmbCompromiso, cmbCompromisoPersonal;
	Button btnGuardar, btnAgregarDistinto, btnQuitarDistinto, btnCancelar, btnSalir,
			btnAgregarPago, btnQuitarPago, btnAgregarCobro, btnQuitarCobro, btnAgregarPersona,
			btnQuitarPersona;
	Doublebox dbxMonto;
	Textbox txtConcepto;
	Radio rdCobrar, rdPagar, rgNatural, rgJuridico, rgPersona, rgEquipo, rgCategoria;
	Spinner spCantidad;
	Listbox lbxJugadoresAsociados, lbxRepresentantesEquipos,
			lbxRepresentantesCategoria, lbxCompromisosCobrar,
			lbxCompromisosDistintos, lbxPersonas;
	Groupbox gbxJugadoresAsociados, gbxRepresentantesEquipos,
			gbxRepresentantesCategoria, gbxPersonas;
	Hbox hbCompromisos;
	Grid gridCompromisos;
	Panel pnCompromisos;
	Label lblEquipoCat;
	Component formulario;

	// ---------------------------------------------------------------------------------------------------
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		formulario = comp;
		formulario.setId("frmPersonas");
		cmbPersona.setValue("--Seleccione--");
	}
	// ---------------------------------------------------------------------------------------------------
	public void onCheck$rgPersona() {
		reiniciarFormilario();
		rwPersona.setVisible(true);
		rwTipoPersona.setVisible(true);
		gbxPersonas.setVisible(true);
		pnCompromisos.setVisible(true);
		lbxCompromisosCobrar.setVisible(true);
		gridCompromisos.setVisible(true);
		hbCompromisos.setVisible(true);
		btnGuardar.setVisible(true);
	}
	// ---------------------------------------------------------------------------------------------------
	public void onCheck$rgEquipo() {
		reiniciarFormilario();
		rwTipoPersona.setVisible(false);
		rwEquipoCat.setVisible(true);
		gbxPersonas.setVisible(false);
		rwPersona.setVisible(false);
		gbxJugadoresAsociados.setVisible(false);
		rwEquipoCat.setVisible(true);
		cmbEquipoCat.setVisible(true);
		lblEquipoCat.setValue("Equipo");
		limpiarCombos(cmbEquipoCat);
		
		listaEquipos = servicioEquipo.listarActivos();
		for (int i = 0; i < listaEquipos.size(); i++) {
			equipo = listaEquipos.get(i);
			Comboitem item = new Comboitem();
			item.setLabel(equipo.getNombre());
			item.setValue(equipo);
			cmbEquipoCat.appendChild(item);
		}
		gbxRepresentantesCategoria.setVisible(false);
		gbxRepresentantesEquipos.setVisible(true);
		pnCompromisos.setVisible(true);
		lbxCompromisosCobrar.setVisible(true);
		gridCompromisos.setVisible(true);
		hbCompromisos.setVisible(true);
		btnGuardar.setVisible(true);
	}
	// ---------------------------------------------------------------------------------------------------
	public void onCheck$rgCategoria() {
		reiniciarFormilario();
		rwTipoPersona.setVisible(false);
		rwEquipoCat.setVisible(true);
		rwPersona.setVisible(true);
		gbxJugadoresAsociados.setVisible(false);
		rwPersona.setVisible(false);
		cmbEquipoCat.setVisible(true);
		lblEquipoCat.setValue("Categoria");
		limpiarCombos(cmbEquipoCat);
		
		listaCategorias = servicioCategoria.listarActivos();
		for (int i = 0; i < listaCategorias.size(); i++) {
			categoria = listaCategorias.get(i);
			Comboitem item = new Comboitem();
			item.setLabel(categoria.getNombre());
			item.setValue(categoria);
			cmbEquipoCat.appendChild(item);
		}
		gbxRepresentantesEquipos.setVisible(false);
		gbxRepresentantesCategoria.setVisible(true);
		pnCompromisos.setVisible(true);
		gridCompromisos.setVisible(true);
		hbCompromisos.setVisible(true);
		btnGuardar.setVisible(true);
		throw new WrongValueException(cmbCompromiso, "Error");
	}
	// ---------------------------------------------------------------------------------------------------
	public void onCheck$rgNatural() {
		try{
			cmbPersona.setValue("--Seleccione--");
			tipoPersonas = servicioDatoBasico.buscarDatosPorRelacion(servicioDatoBasico.buscarPorString("PERSONA NATURAL"));
		
		} catch (Exception e) {
			//---------------
		}
		binder.loadAll();
	}
	// ---------------------------------------------------------------------------------------------------
	public void onCheck$rgJuridico() {
		try{
			cmbPersona.setValue("--Seleccione--");
			tipoPersonas = servicioDatoBasico.buscarDatosPorRelacion(servicioDatoBasico.buscarPorString("PERSONA JURIDICA"));
			binder.loadAll();
		} catch (Exception e) {
			//---------------
		}
	}
	// ---------------------------------------------------------------------------------------------------
	public void reiniciarFormilario(){
		cmbCompromiso.setValue("--Seleccione--");
		cmbEquipoCat.setValue("--Seleccione--");
		cmbPersona.setValue("--Seleccione--");
		rwPersona.setVisible(false);
		rwTipoPersona.setVisible(false);
		cmbEquipoCat.setVisible(false);
		gbxJugadoresAsociados.setVisible(false);
		gbxRepresentantesCategoria.setVisible(false);
		gbxRepresentantesEquipos.setVisible(false);
		gridCompromisos.setVisible(false);
		rwEquipoCat.setVisible(false);
		pnCompromisos.setVisible(false);
		hbCompromisos.setVisible(false);
		btnGuardar.setVisible(false);
		
	}
	// ---------------------------------------------------------------------------------------------------	
	public void onClick$btnAgregarPersona(){
		if (rgJuridico.isChecked()){
		
			
		} 
		if (rgNatural.isChecked()){
			formulario.setAttribute("padre", ((DatoBasico) cmbPersona.getSelectedItem().getValue()).getNombre());
			Component catalogo = Executions
					.createComponents(
							"/Administracion/Vistas/frmCatalogoPersonasNaturales.zul",
							formulario, null);
			formulario.addEventListener("onCierreJuridico",
					new EventListener() {
						@Override
						public void onEvent(Event arg0) throws Exception {
							persona = (Persona) formulario.getVariable("persona", false);
							
						}
					});
		}
	}
	// ---------------------------------------------------------------------------------------------------
	public void onSelect$cmbPersona(){
		try{
			if (((DatoBasico)cmbPersona.getSelectedItem().getValue()).getNombre().equals("FAMILIAR")){
				gbxJugadoresAsociados.setVisible(true);
			} else gbxJugadoresAsociados.setVisible(false);
			
		} catch (Exception e) {
			//---------------
			System.out.println("Excepcion");
		}
	}
	// ---------------------------------------------------------------------------------------------------
	public void onSelect$cmbFiltrado(){
		try{
		if (cmbFiltrado.getSelectedIndex() != -1){
			if (((String)cmbFiltrado.getSelectedItem().getValue()).equals("UNICO")
					&& ((DatoBasico)cmbPersona.getSelectedItem().getValue()).getNombre().equals("REPRESENTANTE")){
				gbxJugadoresAsociados.setVisible(true);
				gbxRepresentantesCategoria.setVisible(false);
				gbxRepresentantesEquipos.setVisible(false);
				pnCompromisos.setVisible(true);
				lbxCompromisosCobrar.setVisible(true);
				gridCompromisos.setVisible(true);
				rwEquipoCat.setVisible(false);
				hbCompromisos.setVisible(true);
				btnGuardar.setVisible(true);
			}
		}
			binder.loadAll();
		} catch (Exception e) {
			//---------------
			System.out.println("Excepcion");
		}
	}
	// ---------------------------------------------------------------------------------------------------
	public void onSelect$cmbEquipoCat(){
		if (lblEquipoCat.getValue().equals("Categoria")){
			
		}
	}
	// ---------------------------------------------------------------------------------------------------
	public void agregarItem(Combobox combo, String dato){
		Comboitem item = new Comboitem();
		item.setLabel(dato);
		item.setValue(dato);
		combo.appendChild(item);
	}
	// ---------------------------------------------------------------------------------------------------
	public void limpiarCombos(Combobox combo){
		int tamanno = combo.getItemCount();
		for (int j = 0; j < tamanno; j++) {
			combo.removeItemAt(0);
		}
	}
	//-------------- GETTERS AND SETTERS
	// ---------------------------------------------------------------------------------------------------

	public DatoBasico getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(DatoBasico tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public List<DatoBasico> getTipoPersonas() {
		return tipoPersonas;
	}

	public void setTipoPersonas(List<DatoBasico> tipoPersonas) {
		this.tipoPersonas = tipoPersonas;
	}
	
	// ---------------------------------------------------------------------------------------------------
}