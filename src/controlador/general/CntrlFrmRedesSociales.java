package controlador.general;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;

import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioEquipo;
import servicio.implementacion.ServicioFamiliarJugador;
import servicio.implementacion.ServicioLapsoDeportivo;
import servicio.implementacion.ServicioRedesSociales;
import modelo.DatoBasico;
import modelo.Equipo;
import modelo.Categoria;
import modelo.FamiliarJugador;
import modelo.LapsoDeportivo;
import modelo.Persona;
import modelo.Roster;


public class CntrlFrmRedesSociales extends GenericForwardComposer {

	ServicioCategoria servicioCategoria;
	ServicioEquipo servicioEquipo;
	ServicioRedesSociales servicioRedesSociales;
	ServicioLapsoDeportivo servicioLapsoDeportivo;
	ServicioDatoBasico servicioDatoBasico;
	ServicioFamiliarJugador servicioFamiliarJugador;
	List<Equipo> listEquipo;
	List<Categoria> listCategoria;
	Equipo equipo;
	
	AnnotateDataBinder binder;
	Combobox cmbCategoria,cmbEquipo;
	Button btnEnviarFacebook,btnEnviarTwitter,btnEnviarEmail;
	Textbox txtEmail,txtAsunto,txtTwitter,txtFacebook;
	Listbox lbxDestinatario;
	Tabbox tbxPestanas;
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("ctrl", this, true);	
		listCategoria=servicioCategoria.listarActivos();
		String ruta=Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/classes/ParametrosRedesSociales.properties");
		servicioRedesSociales.setRuta(ruta);
	}
	
	public void onSelect$tbxPestanas() {
		cargarListado();
	}
	
	private void cargarListado() {
		if (cmbEquipo.getSelectedItem()==null || cmbEquipo.getSelectedItem().getLabel().compareTo("--Seleccione--")==0)
			return;
		switch (tbxPestanas.getSelectedIndex()) {
		case 0:{
			limpiarLista();
			lbxDestinatario.setDisabled(true);
		}
			
			break;
		case 1:{
			lbxDestinatario.setDisabled(false);
			cargarEquipo();
			cargarTwitter();
		}
			
			break;
		case 2:{
			lbxDestinatario.setDisabled(false);
			cargarEquipo();
			cargarCorreo();
		}
			
			break;

		}

		
	}

	private void cargarEquipo() {
		// TODO Auto-generated method stub
		if (cmbEquipo.getSelectedItem()==null || cmbEquipo.getSelectedItem().getLabel().compareTo("--Seleccione--")==0)
			return;
		equipo =(Equipo) servicioEquipo.getDaoEquipo().buscarUnCampoActivos(Equipo.class, "codigoEquipo", ((Equipo)cmbEquipo.getSelectedItem().getValue()).getCodigoEquipo());

	}
	
	public void limpiarLista() {
		int cant = lbxDestinatario.getItemCount();
		for (int i = 0; i < cant; i++) 
			lbxDestinatario.removeItemAt(0);
	}

	private void cargarTwitter() {
		// TODO Auto-generated method stub
		limpiarLista();
		DatoBasico datoBasico = (DatoBasico)servicioDatoBasico.buscarPorCodigo(160);
		if(((LapsoDeportivo)servicioLapsoDeportivo.getDaoLapsoDeportivo().buscarDosCampos(LapsoDeportivo.class,"datoBasico",datoBasico, "estatus", "A")).getDatoBasico().getNombre().compareTo("TEMPORADA REGULAR")==0)
			for (Roster roster : equipo.getRosters()) {
				List<FamiliarJugador> familiarJugadors = servicioFamiliarJugador.buscarPorJugador(roster.getJugador());
				for (FamiliarJugador familiarJugador : familiarJugadors) {
					Persona persona =familiarJugador.getFamiliar().getPersonaNatural().getPersona();
					if(persona.getTwitter()!=null){
						lbxDestinatario.appendItem(persona.getPersonaNatural().getPrimerNombre()+" "+persona.getPersonaNatural().getPrimerApellido(), persona.getTwitter());
					}
				}
			
		}
	}
	
	public void reiniciar() {
		lbxDestinatario.clearSelection();
		txtAsunto.setValue("");
		txtEmail.setValue("");
		txtFacebook.setValue("");
		txtTwitter.setValue("");
	}
	
	private void cargarCorreo() {
		// TODO Auto-generated method stub
		limpiarLista();
		DatoBasico datoBasico = (DatoBasico)servicioDatoBasico.buscarPorCodigo(160);
		if(((LapsoDeportivo)servicioLapsoDeportivo.getDaoLapsoDeportivo().buscarDosCampos(LapsoDeportivo.class,"datoBasico",datoBasico, "estatus", "A")).getDatoBasico().getNombre().compareTo("TEMPORADA REGULAR")==0)
			for (Roster roster : equipo.getRosters()) {
					List<FamiliarJugador> familiarJugadors = servicioFamiliarJugador.buscarPorJugador(roster.getJugador());
					for (FamiliarJugador familiarJugador : familiarJugadors) {
						Persona persona =familiarJugador.getFamiliar().getPersonaNatural().getPersona();
						if(persona.getCorreoElectronico()!=null){
							lbxDestinatario.appendItem(persona.getPersonaNatural().getPrimerNombre()+" "+persona.getPersonaNatural().getPrimerApellido(), persona.getCorreoElectronico());
						}
					}
				
			}
	}

	public void onChange$cmbCategoria() {
		listEquipo=servicioEquipo.buscarPorCategoria((Categoria)cmbCategoria.getSelectedItem().getValue());
		binder.loadComponent(cmbEquipo);
		cmbEquipo.setDisabled(false);
	}
	
	
	public void onChange$cmbEquipo() {
		cargarListado();
	}
	
	public void onClick$btnEnviarFacebook() throws InterruptedException {
		List<String> destinatarios = new ArrayList<String>();
		if (txtFacebook.getValue().isEmpty()){
			Messagebox.show("Debe escribir el mensaje que se va a enviar", "Olimpo - Informacion ", Messagebox.OK,Messagebox.INFORMATION);
			return;
		}
		servicioRedesSociales.enviarFacebook(txtFacebook.getValue());
		Messagebox.show("Mensaje Enviado a Facebook Exitosamente", "Olimpo - Informacion ", Messagebox.OK,Messagebox.INFORMATION);
		reiniciar();
	}
	
	public void onClick$btnEnviarTwitter() throws InterruptedException {
		List<String> destinatarios = new ArrayList<String>();
		if (txtTwitter.getValue().isEmpty()){
			Messagebox.show("Debe escribir el mensaje que se va a enviar", "Olimpo - Informacion ", Messagebox.OK,Messagebox.INFORMATION);
			return;
		}
		for(Object object :lbxDestinatario.getSelectedItems()){
			Listitem item = (Listitem)object;
			destinatarios.add(""+item.getValue());
		}
		servicioRedesSociales.enviarTwitter(destinatarios, txtTwitter.getValue());
		Messagebox.show("Mensaje Enviado a Twitter Exitosamente", "Olimpo - Informacion ", Messagebox.OK,Messagebox.INFORMATION);
		reiniciar();
	}
	
	public void onClick$btnEnviarEmail() throws InterruptedException {
		List<String> destinatarios = new ArrayList<String>();
		if (txtEmail.getValue().isEmpty()){
			Messagebox.show("Debe escribir el mensaje que se va a enviar", "Olimpo - Informacion ", Messagebox.OK,Messagebox.INFORMATION);
			return;
		}
		if(lbxDestinatario.getSelectedItems().size()==0){
			Messagebox.show("Debe seleccionar los destinatarios", "Olimpo - Informacion ", Messagebox.OK,Messagebox.INFORMATION);
			return;
		}
		for(Object object :lbxDestinatario.getSelectedItems()){
			Listitem item = (Listitem)object;
			destinatarios.add(""+item.getValue());
		}
		String asunto="";
		if(txtAsunto.getValue().isEmpty())
			asunto="Sin Asunto";
		else
			asunto=txtAsunto.getValue();
		servicioRedesSociales.enviarEmail(destinatarios, asunto, txtEmail.getValue());
		Messagebox.show("Correos Enviados Exitosamente", "Olimpo - Informacion ", Messagebox.OK,Messagebox.INFORMATION);
		reiniciar();
	}


	public List<Equipo> getListEquipo() {
		return listEquipo;
	}


	public void setListEquipo(List<Equipo> listEquipo) {
		this.listEquipo = listEquipo;
	}


	public List<Categoria> getListCategoria() {
		return listCategoria;
	}


	public void setListCategoria(List<Categoria> listCategoria) {
		this.listCategoria = listCategoria;
	}
	
	
}
