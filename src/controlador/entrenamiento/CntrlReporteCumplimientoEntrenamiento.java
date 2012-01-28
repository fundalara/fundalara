package controlador.entrenamiento;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import modelo.Categoria;
import modelo.DatoBasico;
import modelo.Equipo;
import modelo.EscalaMedicion;
import modelo.Personal;
import modelo.PersonalCargo;
import modelo.PersonalEquipo;
import modelo.TipoDato;
import modelo.ValorEscala;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioPersonalCargo;
import servicio.implementacion.ServicioPersonalEquipo;
import servicio.implementacion.ServicioTipoDato;
import servicio.implementacion.ServicioValorEscala;

public class CntrlReporteCumplimientoEntrenamiento extends
		GenericForwardComposer {
	Datebox dtboxi, dtboxf;
	Combobox cmbEntrenador, cmbCategoria;
	Button btnimp, btncat;
	Textbox txtEquipo;
	Window win_cum_ent;
	ServicioCategoria servicioCategoria;
	ServicioDatoBasico servicioDatoBasico;
	ServicioTipoDato servicioTipoDato;
	ServicioPersonalCargo servicioPersonalCargo;
	ServicioPersonalEquipo servicioPersonalEquipo;
	ServicioEquipo servicioEquipo;

	AnnotateDataBinder binder;
	Categoria categoria;
	int pos = 0;

	List<Categoria> listCategoria;
	List<PersonalCargo> listPersonalCargo;
	List<PersonalEquipo> listPersonalEquipo;
	List<Equipo> listEquipo;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		cmbCategoria.setValue("--Seleccione--");
		listCategoria = new ArrayList<Categoria>();
		llenarListaEntrenadores();

	}

	public void llenarListaEntrenadores() {
		DatoBasico db = new DatoBasico();
		db = servicioDatoBasico.buscarPorCodigo(166);
		listPersonalCargo = servicioPersonalCargo.listarPorCargo(db);
		for (Object o : listPersonalCargo) {
			PersonalCargo pc = (PersonalCargo) o;
			Comboitem item = new Comboitem();
			item.setLabel(pc.getPersonal().getPersonaNatural()
					.getPrimerNombre()
					+ " "
					+ pc.getPersonal().getPersonaNatural().getPrimerApellido());
			item.setValue(pc);
			cmbEntrenador.appendChild(item);
		}

	}

	public Textbox getTxtequipo() {
		return txtEquipo;
	}

	public void setTxtequipo(Textbox txtequipo) {
		this.txtEquipo = txtequipo;
	}

	public ServicioEquipo getServicioEquipo() {
		return servicioEquipo;
	}

	public void setServicioEquipo(ServicioEquipo servicioEquipo) {
		this.servicioEquipo = servicioEquipo;
	}

	public void setListEquipo(List<Equipo> listEquipo) {
		this.listEquipo = listEquipo;
	}

	public List<Equipo> getListEquipo() {
		return listEquipo;
	}

	public void onChange$cmbEntrenador() {
		Personal p = new Personal();
		PersonalCargo pc = (PersonalCargo) cmbEntrenador.getSelectedItem().getValue();
		p = pc.getPersonal();
		listPersonalEquipo = servicioPersonalEquipo.buscarEquipo(p);
		for (Object o : listPersonalEquipo) {
			PersonalEquipo pe = (PersonalEquipo) o;
			Comboitem item = new Comboitem();
			item.setLabel(pe.getEquipo().getCategoria().getNombre());
			item.setValue(pe);
			cmbCategoria.appendChild(item);
			cmbCategoria.setDisabled(false);
		}

	}

	public void onChange$cmbCategoria() {
		Equipo c = new Equipo();
		PersonalEquipo pc = (PersonalEquipo) cmbCategoria.getSelectedItem()
				.getValue();
		c = pc.getEquipo();
		txtEquipo.setDisabled(false);
		txtEquipo.setText(c.getNombre());

	}

	public ServicioPersonalEquipo getServicioPersonalEquipo() {
		return servicioPersonalEquipo;
	}

	public void setServicioPersonalEquipo(
			ServicioPersonalEquipo servicioPersonalEquipo) {
		this.servicioPersonalEquipo = servicioPersonalEquipo;
	}

	public List<PersonalEquipo> getListPersonalEquipo() {
		return listPersonalEquipo;
	}

	public void setListPersonalEquipo(List<PersonalEquipo> listPersonalEquipo) {
		this.listPersonalEquipo = listPersonalEquipo;
	}

	public ServicioCategoria getServicioCategoria() {
		return servicioCategoria;
	}

	public void setServicioCategoria(ServicioCategoria servicioCategoria) {
		this.servicioCategoria = servicioCategoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getListCategoria() {
		return listCategoria;
	}

	public void setListCategoria(List<Categoria> listCategoria) {
		this.listCategoria = listCategoria;
	}

	public void onChange$dtboxf() {
		java.util.Date date = dtboxf.getValue();
		if (date.before(dtboxi.getValue()))
			alert("La Fecha Final es menor que la fecha Inicial");
		else {

			cmbEntrenador.setDisabled(false);

			btncat.setDisabled(false);
		}
	}

	public void onClick$btnimp() {

		if (cmbEntrenador.getText().equals("--TODOS--")) {

			alert("Debe seleccionar un Entrenador");
			cmbEntrenador.focus();
		
			
		}

		else if (cmbCategoria.getText().equals("--Seleccione--")) {

			alert("Debe seleccionar una Categoria");
		
		}

	}

}
