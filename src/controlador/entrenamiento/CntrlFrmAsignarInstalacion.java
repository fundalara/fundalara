package controlador.entrenamiento;

/**
 * Controlador de la Pantalla Asignar Instalaciones para el registro
 * y asignación de las instalaciones para los entrenamientos en el componente
 * agenda por categoría y por equipo.
 * 
 * @version 1.0, 15/02/12
 * @author Laura Colmenarez
 * @email 
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

import java.text.SimpleDateFormat;

import modelo.ActividadCalendario;
import modelo.DatoBasico;
import modelo.Instalacion;
import modelo.InstalacionUtilizada;

import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioInstalacion;
import servicio.implementacion.ServicioActividadCalendario;
import servicio.implementacion.ServicioInstalacionUtilizada;
import servicio.implementacion.ServicioSesion;

import controlador.general.EventoSimpleCalendario;

public class CntrlFrmAsignarInstalacion extends GenericForwardComposer {
	Window wndAsignarInstalaciones;
	Rows filas;
	Button btnCancelar, btnGuardar, btnSalir;
	List<Instalacion> listInstalacion;
	ArrayList<ActividadCalendario> listActividadCalendario;
	List<ActividadCalendario> listAC;
	List<Combobox> listCombobox = new ArrayList<Combobox>();
	List<Button> listButton = new ArrayList<Button>();
	List<Date> listhoras = new ArrayList<Date>();
	ServicioInstalacion servicioInstalacion;
	ServicioCategoria servicioCategoria;
	ServicioEquipo servicioEquipo;
	ServicioSesion servicioSesion;
	ServicioActividadCalendario servicioActividadCalendario;
	ServicioInstalacionUtilizada servicioInstalacionUtilizada;
	ServicioDatoBasico servicioDatoBasico;
	Component window;
	CntrlfrmInstalacionAgenda controladorInstalacionAgenda;
	EventoSimpleCalendario eventoSimpleCalendario;

	public List<Date> getListhoras() {
		return listhoras;
	}

	public void setListhoras(List<Date> listhoras) {
		this.listhoras = listhoras;
	}

	public List<Button> getListButton() {
		return listButton;
	}

	public void setListButton(List<Button> listButton) {
		this.listButton = listButton;
	}

	public List<Combobox> getListCombobox() {
		return listCombobox;
	}

	public void setListCombobox(List<Combobox> listCombobox) {
		this.listCombobox = listCombobox;
	}

	public List<Instalacion> getListInstalacion() {
		return listInstalacion;
	}

	public void setListInstalacion(List<Instalacion> listInstalacion) {
		this.listInstalacion = listInstalacion;
	}

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		window = comp;		
		comp.setVariable("ctrl", this, true);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DatoBasico db = servicioDatoBasico.buscarPorCodigo(221);
		listInstalacion = servicioInstalacion.getDaoInstalacion().listarUnCampoActivos(Instalacion.class, "datoBasico",db);		
		listActividadCalendario = new ArrayList<ActividadCalendario>();
		listActividadCalendario = (ArrayList<ActividadCalendario>) execution.getAttribute("actividadCalendario");
		controladorInstalacionAgenda = (CntrlfrmInstalacionAgenda) execution.getAttribute("controlador");
		eventoSimpleCalendario = (EventoSimpleCalendario) execution.getAttribute("evento");				
		ActividadCalendario actividadCalendario = new ActividadCalendario();
		actividadCalendario = listActividadCalendario.get(0);
		wndAsignarInstalaciones.setTitle("Asignación de instalaciones"+" - "+sdf.format(actividadCalendario.getFechaInicio()));
		limpiarGrid();
		crearGrid();
	}

	public void limpiarGrid() {
		filas.getChildren().clear();
	}

	public void cargarCombo(Combobox cmb) {
		int i = 0;
		while (i < listInstalacion.size()) {
			Comboitem combo = new Comboitem();
			Instalacion tipo = (Instalacion) listInstalacion.get(i);
			combo.setLabel(tipo.getDescripcion());
			combo.setValue(tipo);
			cmb.appendChild(combo);
			i++;
			System.out.println(cmb.getValue());
		}
	}

	public void crearGrid() {
		int n = 0, i = 0;		
		SimpleDateFormat hr = new SimpleDateFormat("hh:mm");
		Date hora = null;	
		if (eventoSimpleCalendario.getHeaderColor().equals(controladorInstalacionAgenda.color[1])) {
			for (ActividadCalendario ac : listActividadCalendario) {
				Row fila = new Row();
				i++;
				if (ac.getHoraInicio().equals(hora)) {
					Label vacio = new Label();
					vacio.setValue("           ");
					vacio.setParent(fila);
					String eq = ac.getSesion().getEquipo().getNombre();
					String cat = ac.getSesion().getEquipo().getCategoria()
							.getNombre();
					Label cateq = new Label();
					cateq.setValue(cat + " - " + eq);
					cateq.setParent(fila);
					Combobox cmbInstalacion = new Combobox();
					cmbInstalacion.setId("cmbIns_" + n + "-" + i);
					cmbInstalacion.addEventListener(Events.ON_SELECT, this);
					InstalacionUtilizada instalacionUtilizada = servicioInstalacionUtilizada.buscarPorSesionFecha(ac.getFechaInicio(),ac.getSesion());					
					cmbInstalacion.setText(instalacionUtilizada.getInstalacion().getDescripcion());
					cmbInstalacion.setReadonly(true);
					cmbInstalacion.setParent(fila);
					listCombobox.add(cmbInstalacion);
					fila.setParent(filas);
					btnGuardar.setVisible(false);
					btnCancelar.setVisible(false);
				} else {
					n++;
					Label bloque = new Label();
					bloque.setValue(hr.format(ac.getHoraInicio()) + "-"
							+ hr.format(ac.getHoraFin()));
					bloque.setParent(fila);
					String eq = ac.getSesion().getEquipo().getNombre();
					String cat = ac.getSesion().getEquipo().getCategoria()
							.getNombre();
					Label cateq = new Label();
					cateq.setValue(cat + "   -   " + eq);
					cateq.setParent(fila);
					Combobox cmbInstalacion = new Combobox();
					cmbInstalacion.setId("cmbIns_" + n + "-" + i);
					cmbInstalacion.addEventListener(Events.ON_SELECT, this);
					InstalacionUtilizada instalacionUtilizada = servicioInstalacionUtilizada.buscarPorSesionFecha(ac.getFechaInicio(),ac.getSesion());					
					cmbInstalacion.setText(instalacionUtilizada.getInstalacion().getDescripcion());
					cmbInstalacion.setReadonly(true);
					cmbInstalacion.setParent(fila);
					listCombobox.add(cmbInstalacion);					
					fila.setParent(filas);
					hora = ac.getHoraInicio();
					btnGuardar.setVisible(false);
					btnCancelar.setVisible(false);
				}
			}
		} else {
			for (ActividadCalendario ac : listActividadCalendario) {
				Row fila = new Row();
				i++;
				if (ac.getHoraInicio().equals(hora)) {
					Label vacio = new Label();
					vacio.setValue("           ");
					vacio.setParent(fila);
					String eq = ac.getSesion().getEquipo().getNombre();
					String cat = ac.getSesion().getEquipo().getCategoria()
							.getNombre();
					Label cateq = new Label();
					cateq.setValue(cat + " - " + eq);
					cateq.setParent(fila);
					Combobox cmbInstalacion = new Combobox();
					cmbInstalacion.setId("cmbIns_" + n + "-" + i);
					cargarCombo(cmbInstalacion);
					cmbInstalacion.addEventListener(Events.ON_SELECT, this);
					cmbInstalacion.setText("--Seleccione--");
					cmbInstalacion.setReadonly(true);
					cmbInstalacion.setParent(fila);
					cmbInstalacion.setAttribute("anterior", 0);
					listCombobox.add(cmbInstalacion);
					fila.setParent(filas);
				} else {
					n++;
					Label bloque = new Label();
					bloque.setValue(hr.format(ac.getHoraInicio()) + "-"
							+ hr.format(ac.getHoraFin()));
					bloque.setParent(fila);
					String eq = ac.getSesion().getEquipo().getNombre();
					String cat = ac.getSesion().getEquipo().getCategoria()
							.getNombre();
					Label cateq = new Label();
					cateq.setValue(cat + "   -   " + eq);
					cateq.setParent(fila);
					Combobox cmbInstalacion = new Combobox();
					cmbInstalacion.setId("cmbIns_" + n + "-" + i);
					cargarCombo(cmbInstalacion);
					cmbInstalacion.addEventListener(Events.ON_SELECT, this);
					cmbInstalacion.setText("--Seleccione--");
					cmbInstalacion.setReadonly(true);
					cmbInstalacion.setParent(fila);
					cmbInstalacion.setAttribute("anterior", 0);
					listCombobox.add(cmbInstalacion);
					Button btnCancelarBloque = new Button();
					btnCancelarBloque.setId("cmbIns_" + n);
					btnCancelarBloque
							.setImage("/Recursos/Imagenes/cancelar.ico");
					btnCancelarBloque.addEventListener(Events.ON_CLICK, this);
					btnCancelarBloque.setParent(fila);
					listButton.add(btnCancelarBloque);
					fila.setParent(filas);
					hora = ac.getHoraInicio();
				}
			}
		}
	}	

	public void onClick$btnGuardar() throws InterruptedException {
		boolean guardar = true;
		for (Combobox combo : listCombobox) {
			if (combo.getText().equals("--Seleccione--")) {
				guardar = !guardar;
				throw new WrongValueException(combo,
						"Seleccione una instalación");
			}
		}
		if (guardar) {
			for (int i = 0; i < listCombobox.size(); i++) {
				InstalacionUtilizada instalacionUtilizada = new InstalacionUtilizada();
				instalacionUtilizada
						.setCodigoInstalacionUtilizada(servicioInstalacionUtilizada
								.generarCodigo());
				instalacionUtilizada.setEstatus('A');
				instalacionUtilizada.setFechaFin(listActividadCalendario.get(i)
						.getFechaCulminacion());
				instalacionUtilizada.setFechaInicio(listActividadCalendario
						.get(i).getFechaInicio());
				instalacionUtilizada.setHoraInicio(listActividadCalendario.get(
						i).getHoraInicio());
				instalacionUtilizada.setHoraFin(listActividadCalendario.get(i)
						.getHoraFin());
				Instalacion instalacion = (Instalacion) listCombobox.get(i)
						.getSelectedItem().getValue();
				instalacionUtilizada.setInstalacion(instalacion);
				instalacionUtilizada.setSesion(listActividadCalendario.get(i)
						.getSesion());
				servicioInstalacionUtilizada.agregar(instalacionUtilizada);
				ActividadCalendario actividadCalendario = new ActividadCalendario();
				actividadCalendario = listActividadCalendario.get(i);
				actividadCalendario.setEstatus('I');
				servicioActividadCalendario.actualizar(actividadCalendario);
			}
			Messagebox.show("Se ha guardado exitosamente",
					"Olimpo - Información", Messagebox.OK,
					Messagebox.INFORMATION);
			eventoSimpleCalendario
					.setContentColor(controladorInstalacionAgenda.color[1]);
			eventoSimpleCalendario
					.setHeaderColor(controladorInstalacionAgenda.color[1]);
			controladorInstalacionAgenda
					.actualizarEvento(eventoSimpleCalendario);
			onClick$btnSalir();
		}
	}

	public void onEvent(Event e) throws Exception {
		if (e.getName().equals("onClick$btnCancelar")) {
			onClick$btnCancelar();
		}
		if (e.getName().equals("onClick$btnGuardar")) {
			onClick$btnGuardar();
		}
		if (e.getName().equals("onClick$btnSalir")) {
			onClick$btnSalir();
		}
		if (e.getName().equals("onSelect")) {
			if (e.getTarget().getClass().equals(Combobox.class)) {
				Combobox cmb = (Combobox) e.getTarget();
				if (cmb.getSelectedIndex() >= 0) {
					for (Combobox cb : listCombobox) {
						if (cb.getId().split("-")[0].equals(cmb.getId().split(
								"-")[0])) {
							if (!cb.getId().equals(cmb.getId())) {
								cb.getItemAtIndex(cmb.getSelectedIndex())
										.setVisible(false);
								Integer num = (Integer) cmb
										.getAttribute("anterior");
								if (num != 0) {
									cb.getItemAtIndex(num).setVisible(true);
								}
							}
						}
					}
					cmb.setAttribute("anterior", cmb.getSelectedIndex());
				}
			}
		}

		if (e.getName().equals("onClick")) {
			if (e.getTarget().getClass().equals(Button.class)) {
				Button boton = (Button) e.getTarget();
				for (Combobox cb : listCombobox) {
					if (cb.getId().split("-")[0].equals(boton.getId())) {
						for (int i = 0; i < cb.getItemCount(); i++) {
							cb.getItemAtIndex(i).setVisible(true);
						}
						cb.setText("--Seleccione--");
					}
				}
			}
		}
	}

	public void onClick$btnCancelar() {
		for (Combobox combo : listCombobox) {
			combo.setText("--Seleccione--");
		}
	}

	public void onClick$btnSalir() {
		wndAsignarInstalaciones.detach();
	}
}