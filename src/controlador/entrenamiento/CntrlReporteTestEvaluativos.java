package controlador.entrenamiento;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.ActividadEntrenamiento;
import modelo.Categoria;
import modelo.Equipo;
import modelo.Jugador;
import modelo.Personal;
import modelo.PersonalCargo;
import modelo.PersonalEquipo;
import modelo.Roster;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;

import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioRoster;

public class CntrlReporteTestEvaluativos extends GenericForwardComposer {
	Datebox dtbox1, dtbox2;
	Combobox cmbcategoria, cmbequipo, cmbatleta;
	Button btnimprimir, catalogo;
	ServicioCategoria servicioCategoria;
	ServicioEquipo servicioEquipo;
	ServicioRoster servicioRoster;
	List<Roster> listRoster;
	List<Equipo> listEquipo;
	List<Categoria> listCategoria;
	AnnotateDataBinder binder;
	Categoria categoria = new Categoria();

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("ctrl", this, true);
		listCategoria = servicioCategoria.listar();
		listEquipo = new ArrayList<Equipo>();
		listRoster = new ArrayList<Roster>();
	}

	public void onChange$cmbcategoria() {

		listEquipo.clear();
		Categoria cc = (Categoria) cmbcategoria.getSelectedItem().getValue();
		listEquipo = servicioEquipo.buscarporCategoria(cc);
		// System.out.println(cc);
		// System.out.println(listEquipo);
		cmbequipo.setDisabled(false);

		binder.loadComponent(cmbequipo);
		cmbequipo.setValue("--Seleccione--");

	}

	public void onChange$cmbequipo() {

		listRoster.clear();
		// Jugador jj= (Jugador)cmbequipo.getSelectedItem().getValue();
		Equipo rr = (Equipo) cmbequipo.getSelectedItem().getValue();
		listRoster = servicioRoster.buscarEquipo(rr);
		System.out.println(rr);

		// System.out.println(cc);
		// System.out.println(listEquipo);
		cmbatleta.setDisabled(false);
		catalogo.setDisabled(false);
		binder.loadComponent(cmbatleta);

		cmbatleta.setValue("--Seleccione--");

	}

	public List<Roster> getListRoster() {
		return listRoster;
	}

	public void setListRoster(List<Roster> listRoster) {
		this.listRoster = listRoster;
	}

	public List<Equipo> getListEquipo() {
		return listEquipo;
	}

	public void setListEquipo(List<Equipo> listEquipo) {
		this.listEquipo = listEquipo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Categoria buscarCategoria() {
		Categoria c;
		int codigo;
		for (Object o : servicioCategoria.listar()) {
			c = (Categoria) o;
			codigo = Integer.parseInt(""
					+ cmbcategoria.getSelectedItem().getValue());
			if (codigo == c.getCodigoCategoria()) {
				return c;
			}
		}
		return null;
	}

	public List<Categoria> getListCategoria() {
		return listCategoria;
	}

	public void setListCategoria(List<Categoria> listCategoria) {
		this.listCategoria = listCategoria;
	}

	public void onChange$dtbox2() {
		Date date = dtbox2.getValue();
		if (date.before(dtbox1.getValue())) {
			alert("La fecha final es menor que la fecha inicial");
		} else {

			cmbcategoria.setDisabled(false);

		}

	}

	public void onClick$btnimprimir() {

		{
			if (cmbcategoria.getText().equals("--Seleccione--")) {

				alert("Debe seleccionar una Categoria");
				cmbcategoria.focus();

			} else if (cmbequipo.getText().equals("--Seleccione--")) {

				alert("Debe seleccionar un equipo");
				cmbequipo.focus();
			}

			else if (cmbatleta.getText().equals("--Seleccione--")) {

				alert("Debe seleccionar un jugador");
				cmbatleta.focus();
			}

		}

	}

}
