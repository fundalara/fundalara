package controlador.entrenamiento;
//Pendiente por revisar......

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.ActividadEntrenamiento;
import modelo.ActividadPlanificada;
import modelo.ActividadesEjecutadas;
import modelo.AsistenciaPersonalEntrenamiento;
import modelo.AsistenciaPersonalEntrenamientoId;
import modelo.Categoria;
import modelo.DatoBasico;
import modelo.Equipo;
import modelo.IndicadorActividadEscala;
import modelo.Instalacion;
import modelo.InstalacionEjecutada;
import modelo.InstalacionUtilizada;
import modelo.PersonaNatural;
import modelo.Personal;
import modelo.PersonalCargo;
import modelo.PersonalEquipo;
import modelo.Sesion;
import modelo.SesionEjecutada;
import modelo.TipoDato;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Listcell;

import servicio.implementacion.ServicioActividadEntrenamiento;
import servicio.implementacion.ServicioActividadPlanificada;
import servicio.implementacion.ServicioActividadesEjecutadas;
import servicio.implementacion.ServicioAsistenciaPersonalEntrenamiento;
import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioIndicadorActividadEscala;
import servicio.implementacion.ServicioInstalacion;
import servicio.implementacion.ServicioInstalacionEjecutada;
import servicio.implementacion.ServicioInstalacionUtilizada;
import servicio.implementacion.ServicioPersonalCargo;
import servicio.implementacion.ServicioPersonalEquipo;
import servicio.implementacion.ServicioPlanRotacion;
import servicio.implementacion.ServicioSesion;
import servicio.implementacion.ServicioSesionEjecutada;



public class CntrlAsignarInstalaciones extends GenericForwardComposer{

	Window wndAsignarInstalaciones;	
	Button btnGuardar, btnCancelar, btnSalir, btnImprimir;
	
	
	public void onClick$btnGuardar() throws InterruptedException{
		Messagebox.show("Guardado Exitosamente","Olimpo - Informacion", Messagebox.OK,Messagebox.INFORMATION);
		onClick$btnSalir();
	}
	
	public void onClick$btnSalir(){
		wndAsignarInstalaciones.detach();
	}
	
}