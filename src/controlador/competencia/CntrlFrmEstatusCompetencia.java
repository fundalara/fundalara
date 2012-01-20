package controlador.competencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import modelo.Categoria;
import modelo.Competencia;
import modelo.DatoBasico;
import modelo.FaseCompetencia;
import modelo.FaseCompetenciaId;
import modelo.Liga;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import servicio.implementacion.ServicioCompetencia;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioFaseCompetencia;
import servicio.implementacion.ServicioJuego;

public class CntrlFrmEstatusCompetencia extends GenericForwardComposer {

	Component formulario;
	AnnotateDataBinder binder;
	ServicioCompetencia servicioCompetencia;
	ServicioFaseCompetencia servicioFaseCompetencia;
	ServicioJuego servicioJuego;
	Competencia competencia;
	ServicioDatoBasico servicioDatoBasico;
	DatoBasico datoBasico;

	FaseCompetenciaId faseCompetenciaId;
	FaseCompetencia faseCompetencia;

	List listaFases;

	// Vista...
	Button btnAperturar;
	Button btnCulminar;
	Textbox txtCantEquipos;
	Textbox txtJugRoster;
	Textbox txtJuegosProgramados;
	Textbox txtJuegosRegistrados;

	public Competencia getCompetencia() {
		return competencia;
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}

	public FaseCompetenciaId getFaseCompetenciaId() {
		return faseCompetenciaId;
	}

	public void setFaseCompetenciaId(FaseCompetenciaId faseCompetenciaId) {
		this.faseCompetenciaId = faseCompetenciaId;
	}

	public FaseCompetencia getFaseCompetencia() {
		return faseCompetencia;
	}

	public void setFaseCompetencia(FaseCompetencia faseCompetencia) {
		this.faseCompetencia = faseCompetencia;
	}

	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);
		c.setVariable("cntrl", this, true);
		formulario = c;
		restaurar();
	}

	private void restaurar() {
		competencia = new Competencia();

	}

	// Agregado Convierte un conjunto a una lista...
	public List ConvertirConjuntoALista(Set conjunto) {
		List l = new ArrayList();
		for (Iterator i = conjunto.iterator(); i.hasNext();) {
			l.add(i.next());
		}
		return l;
	}

	// BOTONES BUSCAR, CANCELAR,SALIR ................
	public void onClick$btnBuscar() {
		Component catalogo = Executions.createComponents(
				"/Competencias/Vistas/FrmCatalogoEstatusCompetencia.zul", null,
				null);
		catalogo.setVariable("formulario", formulario, false);
		formulario.addEventListener("onCatalogoCerrado", new EventListener() {
			@Override
			// Este metodo se llama cuando se envia la señal desde el catalogo
			public void onEvent(Event arg0) throws Exception {
				// se obtiene la competencia...
				competencia = (Competencia) formulario.getVariable(
						"competencia", false);
				// Habilitar Botones...

				if (competencia.getDatoBasicoByCodigoEstadoCompetencia()
						.getNombre().equals("REGISTRADA"))
					btnAperturar.setDisabled(false);

				if (competencia.getDatoBasicoByCodigoEstadoCompetencia()
						.getNombre().equals("APERTURADA"))
					btnCulminar.setDisabled(false);

				// cambie EquiposIngresan de tipo String
				txtCantEquipos.setValue(String.valueOf(servicioFaseCompetencia
						.EquiposRegistrados(competencia).getEquipoIngresan()));
				txtJugRoster.setValue(String.valueOf(competencia
						.getCantidadJugador()));
				txtJuegosRegistrados.setValue(String.valueOf(competencia
						.getJuegos().size()));
				txtJuegosProgramados.setValue(String.valueOf(servicioJuego.listarJuegosProgramados(datoBasico)));

				binder.loadAll();
			}
		});
	}

	public void onClick$btnAperturar() throws InterruptedException {
		if (Messagebox.show("¿Realmente desea aperturar la competencia",
				"Mensaje", Messagebox.YES + Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES) {

			if (competencia.getDatoBasicoByCodigoEstadoCompetencia()
					.getNombre().equals("REGISTRADA"))
				datoBasico = servicioDatoBasico.buscarPorString("APERTURADA");

			competencia.setDatoBasicoByCodigoEstadoCompetencia(datoBasico);

			restaurar();
			binder.loadAll();
			Messagebox.show("La compentencia ha sido aperturada...", "Mensaje",
					Messagebox.OK, Messagebox.EXCLAMATION);
		}
		onClick$btnCancelar();
	}

	public void onClick$btnCulminar() throws InterruptedException {
		if (Messagebox.show("¿Realmente desea culminar la competencia ",
				"Mensaje", Messagebox.YES + Messagebox.NO, Messagebox.QUESTION) == Messagebox.YES) {
			if (competencia.getDatoBasicoByCodigoEstadoCompetencia()
					.getNombre().equals("APERTURADA"))
				datoBasico = servicioDatoBasico.buscarPorString("CLAUSURADA");

			competencia.setDatoBasicoByCodigoEstadoCompetencia(datoBasico);

			restaurar();
			binder.loadAll();
			Messagebox.show("La competencia ha sido culminada...", "Mensaje",
					Messagebox.OK, Messagebox.EXCLAMATION);
		}
		onClick$btnCancelar();
	}

	public void onClick$btnCancelar() {
		restaurar();

		txtCantEquipos.setValue("");
		txtJugRoster.setValue("");
		txtJuegosProgramados.setValue("");

		btnAperturar.setDisabled(true);
		btnCulminar.setDisabled(true);
		binder.loadAll();
	}

	public void onClick$btnSalir() {
		formulario.detach();

	}

}
