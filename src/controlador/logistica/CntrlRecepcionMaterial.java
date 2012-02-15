package controlador.logistica;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.DatoBasico;
import modelo.Material;
import modelo.NotaEntrega;
import modelo.RecepcionMaterial;
import modelo.RecepcionMaterialId;
import modelo.TipoDato;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import servicio.interfaz.IServicioDatoBasico;
import servicio.interfaz.IServicioMaterial;
import servicio.interfaz.IServicioNotaEntrega;
import servicio.interfaz.IServicioRecepcionMaterial;
import servicio.interfaz.IServicioTipoDato;

import comun.MensajeMostrar;
import comun.TipoDatoBasico;

public class CntrlRecepcionMaterial extends GenericForwardComposer {

	AnnotateDataBinder binder;

	RecepcionMaterial recepcionMaterial = new RecepcionMaterial();
	TipoDato tipoDato = new TipoDato();
	DatoBasico tipoDatoBasico = new DatoBasico();
	Material tipoMaterial = new Material();

	NotaEntrega notaE = new NotaEntrega();
	DatoBasico clasificacionMaterial = new DatoBasico();

	List<RecepcionMaterial> recepcionMateriales;
	List<TipoDato> tipoDatos;
	List<DatoBasico> claseMateriales;
	List<DatoBasico> tipoMateriales;
	List<Material> Materiales;
	List<NotaEntrega> notaEntregas;
	List<RecepcionMaterial> materialesPrevios = new ArrayList<RecepcionMaterial>();

	BeanFactory beanFactory;
	IServicioRecepcionMaterial rm;
	IServicioTipoDato tp;
	IServicioDatoBasico cm;
	IServicioMaterial mt;
	IServicioNotaEntrega nt;
	private Window wnRecepcion;
	private IServicioDatoBasico servicioDatoBasico;

	org.zkoss.zul.Spinner spCantidad;
	Combobox cmbTipoDato;
	Combobox cmbTD;
	Combobox cmbCM;
	Combobox cmbDM;
	Datebox fechaRecepcion;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		comp.setVariable("cntrl", this, true);
		beanFactory = new ClassPathXmlApplicationContext("ApplicationContext.xml");

		rm = (IServicioRecepcionMaterial) beanFactory.getBean("servicioRecepcionMaterial");
		recepcionMateriales = rm.listarMateriales();

		tp = (IServicioTipoDato) beanFactory.getBean("servicioTipoDato");
		tipoDatos = tp.listarTipoDatos();

		cm = (IServicioDatoBasico) beanFactory.getBean("servicioDatoBasico");

		mt = (IServicioMaterial) beanFactory.getBean("servicioMaterial");

		nt = (IServicioNotaEntrega) beanFactory.getBean("servicioNotaEntrega");
		notaEntregas = nt.listar();

	}

	public DatoBasico getClasificacionMaterial() {
		return clasificacionMaterial;
	}

	public org.zkoss.zul.Spinner getSpCantidad() {
		return spCantidad;
	}

	public void setSpCantidad(org.zkoss.zul.Spinner spCantidad) {
		this.spCantidad = spCantidad;
	}

	public Datebox getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Datebox fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public void setClasificacionMaterial(DatoBasico clasificacionMaterial) {
		this.clasificacionMaterial = clasificacionMaterial;
	}

	public NotaEntrega getNotaE() {
		return notaE;
	}

	public void setNotaE(NotaEntrega notaE) {
		this.notaE = notaE;
	}

	public List<NotaEntrega> getNotaEntregas() {
		return notaEntregas;
	}

	public void setNotaEntregas(List<NotaEntrega> notaEntregas) {
		this.notaEntregas = notaEntregas;
	}

	public List<DatoBasico> getTipoMateriales() {
		tipoMateriales = cm.buscar(TipoDatoBasico.TIPOS_MATERIALES);
		return tipoMateriales;
	}

	public void setTipoMateriales(List<DatoBasico> tipoMateriales) {
		this.tipoMateriales = tipoMateriales;
	}

	public DatoBasico getTipoDatoBasico() {
		return tipoDatoBasico;
	}

	public void setTipoDatoBasico(DatoBasico tipoDatoBasico) {
		this.tipoDatoBasico = tipoDatoBasico;
	}

	public List<DatoBasico> getClaseMateriales() {

		return claseMateriales;
	}

	public void setClaseMateriales(List<DatoBasico> claseMateriales) {
		this.claseMateriales = claseMateriales;
	}

	public Material getTipoMaterial() {
		return tipoMaterial;
	}

	public void setTipoMaterial(Material tipoMaterial) {
		this.tipoMaterial = tipoMaterial;
	}

	public List<Material> getMateriales() {

		return Materiales;
	}

	public void setMateriales(List<Material> materiales) {
		Materiales = materiales;
	}

	public RecepcionMaterial getRecepcionMaterial() {

		return recepcionMaterial;
	}

	public void setRecepcionMaterial(RecepcionMaterial recepcionMaterial) {
		this.recepcionMaterial = recepcionMaterial;
	}

	public List<RecepcionMaterial> getRecepcionMateriales() {
		recepcionMateriales = rm.listarMateriales();
		return recepcionMateriales;
	}

	public void setRecepcionMateriales(List<RecepcionMaterial> recepcionMateriales) {
		this.recepcionMateriales = recepcionMateriales;
	}

	public List<TipoDato> getTipoDatos() {
		return tipoDatos;
	}

	public List<RecepcionMaterial> getMaterialesPrevios() {
		return materialesPrevios;
	}

	public void setMaterialesPrevios(List<RecepcionMaterial> materialesPrevios) {
		this.materialesPrevios = materialesPrevios;
	}

	public void setTipoDatos(List<TipoDato> tipoDatos) {
		this.tipoDatos = tipoDatos;
	}

	public void onSelect$cmbTD() {

		claseMateriales = cm.buscarDatosPorRelacion(tipoDatoBasico);

	}

	public void onSelect$cmbCM() {

		Materiales = mt.buscarDatosPorRelacion(clasificacionMaterial);

	}

	/**
	 * Este metodo se ejecuta al darle click al boton agregar el material
	 * agregado es cargado a un listbox
	 * 
	 */

	public void onClick$btnAgregar() throws InterruptedException {
		if (validar()) {
			recepcionMaterial = new RecepcionMaterial();
			RecepcionMaterialId rmid = new RecepcionMaterialId();
			rmid.setCodigoMaterial(tipoMaterial.getCodigoMaterial());

			recepcionMaterial.setEstatus('A');

			recepcionMaterial.setId(rmid);

			recepcionMaterial.setObservaciones(((Combobox) wnRecepcion.getVariable("cmbDM", false)).getText());
			recepcionMaterial.setCantidadRecibida(((org.zkoss.zul.Spinner) wnRecepcion.getVariable("spCantidad", false)).getValue());
			recepcionMaterial.setMaterial(tipoMaterial);

			materialesPrevios.add(recepcionMaterial);
		}

	}

	/**
	 * Este metodo es llamado cuando se intentan guardar todos los materiales
	 * previamente cargados al listbox
	 * 
	 */

	public void onClick$btnGuardar() throws InterruptedException {

		if (validar1()) {
			notaE = new NotaEntrega();
			notaE.setCodigoNotaEntrega(nt.devolverUltimo() + 1);
			notaE.setCuentaPagar(null);
			notaE.setDocumentoAcreedor(null);
			notaE.setEstatus('A');
			notaE.setFechaRecepcion(((Datebox) wnRecepcion.getVariable("fechaRecepcion", false)).getValue());
			nt.agregar(notaE);
			notaE.setCodigoNotaEntrega(nt.devolverUltimo());

			if (materialesPrevios.size() == 1) {
				RecepcionMaterial rem = materialesPrevios.get(0);
				rem.setNotaEntrega(notaE);
				rem.getId().setCodigoNotaEntrega(notaE.getCodigoNotaEntrega());
				rm.agregar(rem);
				rm.actualizar(rem);

			} else {
				for (Iterator<RecepcionMaterial> iterator = materialesPrevios.iterator(); iterator.hasNext();) {

					RecepcionMaterial rem = (RecepcionMaterial) iterator.next();
					rem.setNotaEntrega(notaE);
					rem.getId().setCodigoNotaEntrega(notaE.getCodigoNotaEntrega());

					rm.agregar(rem);

				}

			}
			this.materialesPrevios.clear();
			this.onClick$btnCancelar();

			Messagebox.show("Datos agregados exitosamente", MensajeMostrar.TITULO + "Información", Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}

	/**
	 * Este metodo retorna el codigo del material seleccionado en la lista de
	 * materiales
	 */
	public int onSelect$lboxRecepcionMateriales() throws InterruptedException {

		int codigo = recepcionMaterial.getId().getCodigoMaterial();

		return codigo;

	}

	/**
	 * Este Metodo es ejeutado cuando se intenta quitar un material de la lista
	 * 
	 */
	public void onClick$btnQuitar() throws InterruptedException {

		int c = onSelect$lboxRecepcionMateriales();

		for (int i = 0; i < materialesPrevios.size(); i++)

		{

			if (materialesPrevios.get(i).getId().getCodigoMaterial() == c) {

				materialesPrevios.remove(i);

			}

		}

	}

	/**
	 * Cuando se ejecuta el boton cancelar y se encarga de limpiar todos los
	 * campos que esten llenos en ese momento
	 */
	public void onClick$btnCancelar() {

		claseMateriales = new ArrayList<DatoBasico>();
		tipoMateriales = new ArrayList<DatoBasico>();
		Materiales = new ArrayList<Material>();
		spCantidad.setValue(0);

		binder.loadAll();

	}

	public void onClick$btnSalir() {
		this.wnRecepcion.detach();
	}

	public boolean validar() throws InterruptedException {

		boolean valido = false;

		if (fechaRecepcion.getValue() == null) {
			throw new WrongValueException(fechaRecepcion, "Seleccione Una Fecha");
		} else {
			if (cmbTD.getValue().isEmpty()) {
				throw new WrongValueException(cmbTD, "Seleccione Un Tipo");
			} else {
				if (cmbCM.getValue().isEmpty()) {
					throw new WrongValueException(cmbCM, "Seleccione Una Clasificación");
				} else {
					if (cmbDM.getValue().isEmpty()) {
						throw new WrongValueException(cmbDM, "Seleccione Una Descripcion del Material");
					} else {
						if (spCantidad.getValue() <= 0 || spCantidad.getValue() == null) {
							throw new WrongValueException(spCantidad, "Solo se Aceptan Valores Positivos");
						}
					}

				}

			}

			valido = true;
		}
		return valido;

	}

	public boolean validar1() throws InterruptedException {
		boolean valido = false;

		if (materialesPrevios.isEmpty()) {
			Messagebox.show("Debe Agregar Materiales a la Lista", MensajeMostrar.TITULO + "Importante", Messagebox.OK, Messagebox.INFORMATION);
		} else {
			if (fechaRecepcion.getValue() == null) {
				Messagebox.show("Debe Agregar Materiales a la Lista", MensajeMostrar.TITULO + "Importante", Messagebox.OK, Messagebox.INFORMATION);
			}
			valido = true;
		}

		return valido;

	}
}