package controlador.administracion;

import org.hibernate.cfg.AnnotationBinder;
import org.hibernate.hql.ast.tree.Case2Node;
import org.python.antlr.PythonParser.list_for_return;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.*;

import java.util.*;

import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioDocumentoAcreedor;
import servicio.implementacion.ServicioDocumentoAcreedorMaterial;
import servicio.implementacion.ServicioDocumentoIndumentaria;
import servicio.implementacion.ServicioFamiliar;
import servicio.implementacion.ServicioFamiliarJugador;
import servicio.implementacion.ServicioJugador;
import servicio.implementacion.ServicioPersona;
import servicio.implementacion.ServicioPersonaNatural;
import servicio.implementacion.ServicioTallaPorIndumentaria;
import servicio.implementacion.ServicioTipoDato;
import servicio.implementacion.ServicioTipoIngreso;
import modelo.DatoBasico;
import modelo.DocumentoAcreedor;
import modelo.DocumentoIndumentaria;
import modelo.DocumentoIndumentariaId;
import modelo.Familiar;
import modelo.FamiliarJugador;
import modelo.Jugador;
import modelo.Persona;
import modelo.PersonaNatural;
import modelo.TallaPorIndumentaria;

public class CntrlSolicitudUniforme extends GenericForwardComposer {

	AnnotateDataBinder binder;
	ServicioPersona servicioPersona = new ServicioPersona();
	ServicioPersonaNatural servicioPersonaNatural = new ServicioPersonaNatural();
	ServicioFamiliarJugador servicioFamiliarJugador;
	ServicioJugador servicioJugador;
	ServicioFamiliar servicioFamiliar;
	ServicioDatoBasico servicioUniformes, servicioTallas;
	ServicioTipoDato servicioTipoDato;
	ServicioDocumentoAcreedor servicioDocumentoAcreedor;
	ServicioDocumentoIndumentaria servicioDocumentoIndumentaria;
	ServicioTipoIngreso servicioTipoIngreso;
	DocumentoIndumentaria indumentaria;
	Persona persona, atleta;
	Familiar familiar;
	Jugador jugador;
	PersonaNatural personaNatural;
	FamiliarJugador familiarJugador;
	DatoBasico uniforme, talla, tipoDeUniforme;
	Doublebox dbxPrecio, dbxMontoTotal;
	Combobox cmbCedula, cmbTipoUniforme, cmbUniforme, cmbTalla;
	Textbox txtCedula;
	Button btnBuscar, btnAgregar, btnQuitar, btnAceptar, btnCancelar, btnSalir;
	Textbox txtNombre, txtTotal;
	Grid gridJugadores, gridUniformes;
	Spinner sprCantidad;
	Listbox lbxJugadores, lbxPedidos;
	Component formulario;

	ServicioDatoBasico servicioDatoBasico;
	List<DocumentoIndumentaria> indumentarias = new ArrayList<DocumentoIndumentaria>();
	List<FamiliarJugador> listFamiliarJugador = new ArrayList<FamiliarJugador>();
	List<Jugador> listJugador = new ArrayList<Jugador>();
	List<DatoBasico> uniformes, tallas,
			tiposDeUniformes = new ArrayList<DatoBasico>();
	TallaPorIndumentaria tallaPorIndumentaria = new TallaPorIndumentaria();
	ServicioTallaPorIndumentaria servicioTallaPorIndumentaria;
	DocumentoAcreedor documento = new DocumentoAcreedor();

	int num;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		personaNatural = new PersonaNatural();
		persona = new Persona();
		atleta = new Persona();
		familiarJugador = new FamiliarJugador();
		jugador = new Jugador();
		tiposDeUniformes = servicioDatoBasico
				.listarPorTipoDato("TIPO UNIFORME");
		uniformes = servicioDatoBasico.listarPorTipoDato("INDUMENTARIA");

		formulario = comp;
		formulario.setId("frmPersonas");
		formulario.setAttribute("padre", "REPRESENTANTE");
	}

	public void onChange$cmbTipoUniforme() {
		cmbUniforme.setValue("--Seleccione--");
		cmbUniforme.setDisabled(false);
		cmbTalla.setDisabled(true);
		cmbTalla.setValue("--Seleccione--");
	}

	public void clearAll() {
		clear();

	}

	public void clear() {
		indumentarias = new ArrayList<DocumentoIndumentaria>();
		cmbTipoUniforme.setSelectedIndex(-1);
		cmbTalla.setSelectedIndex(-1);
		cmbTalla.setDisabled(true);
		cmbUniforme.setSelectedIndex(-1);
		sprCantidad.setValue(null);
		binder.loadComponent(lbxPedidos);
	}

	public void onSelect$lbxJugadores() {
		System.out.println("asdasd");
		atleta = new Persona();
		atleta = listFamiliarJugador.get(lbxJugadores.getSelectedIndex())
				.getJugador().getPersonaNatural().getPersona();
		System.out.println(atleta.getCedulaRif());
		clear();
	}

	public void onChange$cmbUniforme() {
		cmbTalla.setDisabled(false);
		tallas = servicioDatoBasico.listarPorPadre("TALLA INDUMENTARIA",
				Integer.parseInt(cmbUniforme.getSelectedItem().getContext()));
		binder.loadComponent(cmbTalla);
	}

	public void onChange$cmbTalla() {
		cmbTalla.setContext(cmbTalla.getSelectedItem().getContext());
		tallaPorIndumentaria = new TallaPorIndumentaria();
		tallaPorIndumentaria = servicioTallaPorIndumentaria
				.buscarPorTalla(Integer.parseInt(cmbTipoUniforme
						.getSelectedItem().getContext()), Integer
						.parseInt(cmbTalla.getSelectedItem().getContext()));
		dbxPrecio.setValue(tallaPorIndumentaria.getPrecio());
	}

	public void onClick$btnAgregar() {
		jugador = new Jugador();
		indumentaria = new DocumentoIndumentaria();
		indumentaria.setMonto(dbxPrecio.getValue());
		indumentaria.setTallaPorIndumentaria(tallaPorIndumentaria);
		indumentaria.setCantidad(sprCantidad.getValue());
		indumentaria.setEstatus('A');
		indumentarias.add(indumentaria);
		double monto = 0;
		for (DocumentoIndumentaria doc : indumentarias) {
			monto = monto + (doc.getMonto() * doc.getCantidad());
		}
		dbxMontoTotal.setValue(monto);
		binder.loadComponent(lbxPedidos);

	}

	public void onClick$btnQuitar() {
		indumentarias.remove(lbxPedidos.getSelectedIndex());
		double monto = 0;
		for (DocumentoIndumentaria doc : indumentarias) {
			monto = monto + (doc.getMonto() * doc.getCantidad());
		}
		dbxMontoTotal.setValue(monto);
		binder.loadComponent(lbxPedidos);

	}

	public void onClick$btnAceptar() {
		System.out.println("321");
		documento = new DocumentoAcreedor();

		documento.setPersonaByCedulaRif(persona);
		documento.setTipoIngreso(servicioTipoIngreso
				.buscarPorNombre("ADQUISICION"));
		documento.setConcepto("PEDIDO DE MATERIALES");
		documento.setMonto(dbxMontoTotal.getValue());
		documento.setSaldo(dbxMontoTotal.getValue());
		documento.setFechaEmision(new Date());
		documento.setFechaVencimiento(new Date());
		documento.setPersonaByCedulaAtleta(atleta);
		documento.setEstado('P');
		documento.setEstatus('A');
		System.out.println(persona.getCedulaRif());
		System.out.println(atleta.getCedulaRif());
		documento.setCodigoDocumentoAcreedor(servicioDocumentoAcreedor.listar()
				.size() + 1);
		servicioDocumentoAcreedor.agregar(documento);
		alert("Registrado Satisfactoriamente");

		System.out.println("321");
		for (DocumentoIndumentaria doc : indumentarias) {
			doc.setDocumentoAcreedor(documento);
			doc.setId(new DocumentoIndumentariaId(documento
					.getCodigoDocumentoAcreedor(), doc
					.getTallaPorIndumentaria().getCodigoTallaIndumentaria()));
			servicioDocumentoIndumentaria.agregar(doc);
		}
	}

	public void onClick$btnBuscar() {
		if (txtCedula.getText().trim().equals("")) {

			Component catalogo = Executions
					.createComponents(
							"/Administracion/Vistas/frmCatalogoPersonaNatural.zul",
							formulario, null);

			catalogo.setVariable("formulario", formulario, false);
			formulario.addEventListener("onCatalogoCerrado",
					new EventListener() {
						@Override
						public void onEvent(Event arg0) throws Exception {
							persona = (Persona) formulario.getVariable(
									"persona", false);
							String cadNacionalidad = persona.getCedulaRif()
									.substring(0, 2);
							String cadCedula = persona.getCedulaRif()
									.substring(2);
							cmbCedula.setValue(cadNacionalidad);
							txtCedula.setText(cadCedula);
							String segundoN = "";
							String segundoA = "";
							if (persona.getPersonaNatural().getSegundoNombre() == "")
								segundoN = "";
							if (persona.getPersonaNatural()
									.getSegundoApellido() == "")
								segundoA = "";

							txtNombre.setValue(persona.getPersonaNatural()
									.getPrimerNombre()
									+ " "
									+ segundoN
									+ " "
									+ persona.getPersonaNatural()
											.getPrimerApellido()
									+ " "
									+ segundoA);
							familiar = persona.getPersonaNatural()
									.getFamiliar();
							listFamiliarJugador = servicioFamiliarJugador
									.listarPorRepresentante(familiar);
							if (listFamiliarJugador.isEmpty())
								lbxJugadores.setDisabled(true);
							else
								lbxJugadores.setDisabled(false);
							// listUniformes =
							// servicioUniformes.buscarPorTipoDato(servicioTipoDato.buscarTipo("TIPO DE UNIFORME"));
							binder.loadComponent(lbxJugadores);
						}
					});

		} else {
			persona = servicioPersona.buscarPorCedulaRif(this.cmbCedula
					.getSelectedItem().getValue().toString()
					+ this.txtCedula.getText().toString());
			if (persona == null) {
				alert("Representante no encontrado. Favor verifique.");
				return;
			} else {
				txtNombre.setValue(persona.getPersonaNatural()
						.getPrimerNombre()
						+ " "
						+ persona.getPersonaNatural().getSegundoNombre()
						+ " "
						+ persona.getPersonaNatural().getPrimerApellido()
						+ " "
						+ persona.getPersonaNatural().getSegundoApellido()
						+ " ");
				familiar = servicioFamiliar
						.buscarPorCedulaFamiliar(this.cmbCedula
								.getSelectedItem().getValue().toString()
								+ this.txtCedula.getText().toString());
				listFamiliarJugador = servicioFamiliarJugador
						.listarPorRepresentante(familiar);
				binder.loadComponent(lbxJugadores);
			}
		}

	}

	public ServicioPersona getServicioPersona() {
		return servicioPersona;
	}

	public void setServicioPersona(ServicioPersona servicioPersona) {
		this.servicioPersona = servicioPersona;
	}

	public ServicioPersonaNatural getServicioPersonaNatural() {
		return servicioPersonaNatural;
	}

	public void setServicioPersonaNatural(
			ServicioPersonaNatural servicioPersonaNatural) {
		this.servicioPersonaNatural = servicioPersonaNatural;
	}

	public ServicioFamiliarJugador getServicioFamiliarJugador() {
		return servicioFamiliarJugador;
	}

	public void setServicioFamiliarJugador(
			ServicioFamiliarJugador servicioFamiliarJugador) {
		this.servicioFamiliarJugador = servicioFamiliarJugador;
	}

	public ServicioJugador getServicioJugador() {
		return servicioJugador;
	}

	public void setServicioJugador(ServicioJugador servicioJugador) {
		this.servicioJugador = servicioJugador;
	}

	public ServicioFamiliar getServicioFamiliar() {
		return servicioFamiliar;
	}

	public void setServicioFamiliar(ServicioFamiliar servicioFamiliar) {
		this.servicioFamiliar = servicioFamiliar;
	}

	public ServicioDatoBasico getServicioUniformes() {
		return servicioUniformes;
	}

	public void setServicioUniformes(ServicioDatoBasico servicioUniformes) {
		this.servicioUniformes = servicioUniformes;
	}

	public ServicioDatoBasico getServicioTallas() {
		return servicioTallas;
	}

	public void setServicioTallas(ServicioDatoBasico servicioTallas) {
		this.servicioTallas = servicioTallas;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Familiar getFamiliar() {
		return familiar;
	}

	public void setFamiliar(Familiar familiar) {
		this.familiar = familiar;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public PersonaNatural getPersonaNatural() {
		return personaNatural;
	}

	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
	}

	public FamiliarJugador getFamiliarJugador() {
		return familiarJugador;
	}

	public void setFamiliarJugador(FamiliarJugador familiarJugador) {
		this.familiarJugador = familiarJugador;
	}

	public Listbox getLbxJudadores() {
		return lbxJugadores;
	}

	public void setLbxJudadores(Listbox lbxJudadores) {
		this.lbxJugadores = lbxJugadores;
	}

	public Listbox getLbxPedidos() {
		return lbxPedidos;
	}

	public void setLbxPedidos(Listbox lbxPedidos) {
		this.lbxPedidos = lbxPedidos;
	}

	public List<FamiliarJugador> getListFamiliarJugador() {
		return listFamiliarJugador;
	}

	public void setListFamiliarJugador(List<FamiliarJugador> listFamiliarJugador) {
		this.listFamiliarJugador = listFamiliarJugador;
	}

	public List<Jugador> getListJugador() {
		return listJugador;
	}

	public void setListJugador(List<Jugador> listJugador) {
		this.listJugador = listJugador;
	}

	public DatoBasico getUniforme() {
		return uniforme;
	}

	public void setUniforme(DatoBasico uniforme) {
		this.uniforme = uniforme;
	}

	public DatoBasico getTalla() {
		return talla;
	}

	public void setTalla(DatoBasico talla) {
		this.talla = talla;
	}

	public DatoBasico getTipoDeUniforme() {
		return tipoDeUniforme;
	}

	public void setTipoDeUniforme(DatoBasico tipoDeUniforme) {
		this.tipoDeUniforme = tipoDeUniforme;
	}

	public List<DatoBasico> getUniformes() {
		return uniformes;
	}

	public void setUniformes(List<DatoBasico> uniformes) {
		this.uniformes = uniformes;
	}

	public List<DatoBasico> getTallas() {
		return tallas;
	}

	public void setTallas(List<DatoBasico> tallas) {
		this.tallas = tallas;
	}

	public List<DatoBasico> getTiposDeUniformes() {
		return tiposDeUniformes;
	}

	public void setTiposDeUniformes(List<DatoBasico> tiposDeUniformes) {
		this.tiposDeUniformes = tiposDeUniformes;
	}

	public List<DocumentoIndumentaria> getIndumentarias() {
		return indumentarias;
	}

	public void setIndumentarias(List<DocumentoIndumentaria> indumentarias) {
		this.indumentarias = indumentarias;
	}

	public DocumentoIndumentaria getIndumentaria() {
		return indumentaria;
	}

	public void setIndumentaria(DocumentoIndumentaria indumentaria) {
		this.indumentaria = indumentaria;
	}

}