<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514
package controlador.competencia;

import modelo.Juego;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Window;



	
public class CntrlFrmAccionJuego extends GenericForwardComposer {
	
    Component c;
    Combobox cmbTarea;
	AnnotateDataBinder binder;
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		c = comp;
	}
	
	public void onCreate$FrmAccionJuego(){
		
		Juego j = (Juego) c.getVariable("juego", false);
		String estado = j.getDatoBasico().getNombre();
		Comboitem ci = new Comboitem();
        Comboitem ci2 = new Comboitem();
		if (estado.equals("POR REALIZAR")){            
             ci2.setLabel("Gestionar Line-Up");
             ci2.setValue("2");
             cmbTarea.appendChild(ci2);   
             ci.setLabel("Registro de Resultados");
             ci.setValue("1");
             cmbTarea.appendChild(ci); 
		}else if (estado.equals("CULMINADO")){
			 ci.setLabel("Ver Resultados");
             ci.setValue("1");
             cmbTarea.appendChild(ci);         
		}else {
			
		}
		
	}    
	
	public void onClick$btnCancelar(){
		 c.detach();  
	}
	
	public void onClick$btnAceptar(){
		String opc = (String) cmbTarea.getSelectedItem().getValue();	
		Window w;
        
		if (opc.equals("1")){
			w =  (Window) Executions.createComponents("/Competencias/Vistas/FrmRegistroResultados.zul",null,null);		
		}else if (opc.equals("2")){
			w = (Window) Executions.createComponents("/Competencias/Vistas/FrmLineUp.zul",null,null);
		}else{
			w = (Window) Executions.createComponents("/Competencias/Vistas/FrmRegistroResultados.zul",null,null);
		}
		Juego j = (Juego) c.getVariable("juego",false);
		w.setVariable("juego",j,false);
		w.setPosition("center");
		w.doHighlighted();
		c.detach();
		
	}
	
}
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
=======
package controlador.competencia;

import modelo.Juego;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Window;



	
public class CntrlFrmAccionJuego extends GenericForwardComposer {
	
    Component c;
    Combobox cmbTarea;
	AnnotateDataBinder binder;
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		c = comp;
	}
	
	public void onCreate$FrmAccionJuego(){
		
		Juego j = (Juego) c.getVariable("juego", false);
		String estado = j.getDatoBasico().getNombre();
		Comboitem ci = new Comboitem();
        Comboitem ci2 = new Comboitem();
		if (estado.equals("POR REALIZAR")){            
             ci2.setLabel("Gestionar Line-Up");
             ci2.setValue("2");
             cmbTarea.appendChild(ci2);   
             ci.setLabel("Registro de Resultados");
             ci.setValue("1");
             cmbTarea.appendChild(ci); 
		}else if (estado.equals("CULMINADO")){
			 ci.setLabel("Ver Resultados");
             ci.setValue("1");
             cmbTarea.appendChild(ci);         
		}else {
			
		}
		
	}    
	
	public void onClick$btnCancelar(){
		 c.detach();  
	}
	
	public void onClick$btnAceptar(){
		String opc = (String) cmbTarea.getSelectedItem().getValue();	
		Window w;
        
		if (opc.equals("1")){
			w =  (Window) Executions.createComponents("/Competencias/Vistas/FrmRegistroResultados.zul",null,null);		
		}else if (opc.equals("2")){
			w = (Window) Executions.createComponents("/Competencias/Vistas/FrmLineUp.zul",null,null);
		}else{
			w = (Window) Executions.createComponents("/Competencias/Vistas/FrmRegistroResultados.zul",null,null);
		}
		Juego j = (Juego) c.getVariable("juego",false);
		w.setVariable("juego",j,false);
		w.setPosition("center");
		w.doHighlighted();
		c.detach();
		
	}
	
}
>>>>>>> 7d4823278d0a354855f5032a99e2b48c65b33e7f
>>>>>>> 9d67536bdd2e3b33aa6400eb3bb09741a4535bb0
>>>>>>> 873ccb018e72b00b831aa9284cc0745bd1c2d514
