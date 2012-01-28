package controlador.entrenamiento;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Datebox;

import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioLapsoDeportivo;
import servicio.implementacion.ServicioTipoDato;
import modelo.DatoBasico;
import modelo.LapsoDeportivo;

;

public class CntrlLapsoDeportivo extends GenericForwardComposer {
	Row rowFecha, rowAscenso;
	Button btnGuardar, btnCancelar, btnSalir;
	Window wndLapsoDeportivo;;
	Textbox txtNombre;
	Datebox dbInicio, dbFinal, dbAscenso, dbAscenso1;
	LapsoDeportivo lapsoDeportivo;;
	ServicioLapsoDeportivo servicioLapsoDeportivo;
	ServicioDatoBasico servicioDatoBasico;
	ServicioTipoDato servicioTipoDato;
	Combobox cmbLapso;
	List<DatoBasico> listlapso;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("ctrl", this, true);
		lapsoDeportivo = new modelo.LapsoDeportivo();
		listlapso = servicioDatoBasico.buscarPorTipoDato(servicioTipoDato
				.buscarPorTipo("TIPO LAPSO DEPORTIVO"));
		reiniciar();
		rowFecha.setVisible(false);
		rowAscenso.setVisible(false);
	}

	public List<DatoBasico> getListlapso() {
		return listlapso;
	}

	public void setListlapso(List<DatoBasico> listlapso) {
		this.listlapso = listlapso;
	}

	// public void onChange$dbFinal() {
	// txtNombre.setValue(cmbLapso.getSelectedItem().getLabel());
	// //txtNombre.setValue(""+(dbInicio.getValue().getYear()+1900)+"-"+(dbFinal.getValue().getYear()+1900));
	// }

	public void onChange$cmbLapso() {
		txtNombre.setValue(cmbLapso.getSelectedItem().getLabel());

		// txtNombre.setValue(""+(dbInicio.getValue().getYear()+1900)+"-"+(dbFinal.getValue().getYear()+1900));
		if (cmbLapso.getSelectedItem().getLabel()
				.equalsIgnoreCase("TEMPORADA REGULAR")) {
			rowAscenso.setVisible(true);
			rowFecha.setVisible(true);
			System.out.println("entre");
		} else {
			rowAscenso.setVisible(false);
			rowFecha.setVisible(false);
		}
	}

	public void onClick$btnGuardar() {
		Date d = new Date();
		if (cmbLapso.getSelectedItem() == null) {
			throw new WrongValueException(cmbLapso,
					"Debe seleccionar un lapso deportivo");
		}
		if (dbInicio.getValue() == null) {
			throw new WrongValueException(dbInicio,
					"Debe seleccionar una fecha de inicio");
		} else if (dbFinal.getValue() == null) {
			throw new WrongValueException(dbFinal,
					"Debe seleccionar una fecha final");
		} else if (dbFinal.getValue().before(dbInicio.getValue())) {
			throw new WrongValueException(dbFinal,
					"La fecha final debe ser mayor a la fecha de inicio");
		} else {

			Integer codigo;
			if (servicioLapsoDeportivo.listar().size() == 0) {
				codigo = 1;
				if (dbInicio.getValue().getYear() < (d.getYear() - 2)) {
					throw new WrongValueException(dbFinal,
							"La fecha de inicio no debe estar tan alejada de la fecha actual");
				} else {
					if (txtNombre.getValue().isEmpty()) {
						txtNombre.setValue(cmbLapso.getSelectedItem()
								.getLabel());
						cmbLapso.getSelectedItem();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
						binderLapso(codigo, sdf.format(dbInicio.getValue())
								+ "-" + sdf.format(dbFinal.getValue()));
					} else
						binderLapso(codigo, txtNombre.getValue());
					servicioLapsoDeportivo.guardar(lapsoDeportivo);
					alert("Lapso deportivo guardado exitosamente");
					reiniciar();
				}
			} else {
				codigo = servicioLapsoDeportivo.listar().size() + 1;
				LapsoDeportivo lapso = servicioLapsoDeportivo.listar().get(
						codigo - 2);
				if (dbInicio.getValue().before(lapso.getFechaFin())) {
					alert("La fecha de inicio debe ser mayor a la fecha final del ultimo lapso deportivo");
					dbInicio.setFocus(true);
				} else {
					if (txtNombre.getValue().isEmpty()) {
						txtNombre.setValue(cmbLapso.getSelectedItem()
								.getLabel());
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
						binderLapso(codigo,
								(dbInicio.getValue().getYear() + 1900) + "-"
										+ (dbFinal.getValue().getYear() + 1900)
										+ " " + txtNombre.getValue());
					} else
						binderLapso(codigo,
								(dbInicio.getValue().getYear() + 1900) + "-"
										+ (dbFinal.getValue().getYear() + 1900)
										+ " " + txtNombre.getValue());
					servicioLapsoDeportivo.guardar(lapsoDeportivo);
					alert("Lapso deportivo guardado exitosamente");
					reiniciar();
				}
			}
		}
	}

	private void reiniciar() {
		cmbLapso.setSelectedIndex(-1);
		txtNombre.setValue("");
		dbInicio.setValue(null);
		dbFinal.setValue(null);
		dbAscenso.setValue(null);
		dbAscenso1.setValue(null);
	}

	private void binderLapso(Integer codigo, String nombre) {
		lapsoDeportivo.setCodigoLapsoDeportivo(codigo);
		lapsoDeportivo.setEstatus('A');
		lapsoDeportivo.setNombre(nombre);
		lapsoDeportivo.setDatoBasico((DatoBasico) cmbLapso.getSelectedItem()
				.getValue());
		lapsoDeportivo.setFechaInicio(dbInicio.getValue());
		lapsoDeportivo.setFechaFin(dbFinal.getValue());

		if (cmbLapso.getSelectedItem().getLabel()
				.equalsIgnoreCase("PLAN VACACIONAL")) {
			lapsoDeportivo.setFechaInicioAscenso(null);
			lapsoDeportivo.setFechaFinAscenso(null);
		} else {
			lapsoDeportivo.setFechaInicioAscenso(dbAscenso.getValue());
			lapsoDeportivo.setFechaFinAscenso(dbAscenso1.getValue());
		}

	}

	public void onClick$btnCancelar() {
		reiniciar();
	}

	public void onClick$btnSalir() {

		wndLapsoDeportivo.detach();
	}

}
