package controlador.competencia;


import modelo.Regla;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Messagebox;
import servicio.competencia.ServicioRegla;

public class CntrlFrmRegla extends GenericForwardComposer 

{
     
	 Component comp;
     AnnotateDataBinder binder;
	 ServicioRegla servicioRegla;
     Regla regla;
  
     
     public Regla getRegla() {
		return regla;
	}




	public void setRegla(Regla regla) {
		this.regla = regla;
	}




	public void doAfterCompose (Component c) throws Exception{		
    	super.doAfterCompose(c);
 		c.setVariable("cntrl",this,true);
 		comp = c;
 		regla = new Regla();
 		
 		
 		
        
 	}
 	
     
    
  
     public void onClick$btnGuardar() throws InterruptedException{
    	int cod = servicioRegla.listar().size()+1;
  		//regla.setCodigo(String.valueOf(cod));
 		servicioRegla.agregar(regla);
 		regla = new Regla();
 		binder.loadAll();
 		Messagebox.show("Datos agregados exitosamente","Mensaje",Messagebox.OK,Messagebox.EXCLAMATION);
 		
 	}
     
     
     
	public void onClick$btnCancelar(){
		regla = new Regla();
		binder.loadAll();
	}
	
	
	public void onClick$btnSalir() {
		comp.detach();
		
	}
	public void onClick$btnEliminar() throws InterruptedException{
		if(Messagebox.show("¿Realmente desea eliminar esta divisa","Mensaje",Messagebox.YES+Messagebox.NO,Messagebox.QUESTION) == Messagebox.YES){
			
			servicioRegla.eliminar(regla);
			regla = new Regla();
			binder.loadAll();
			Messagebox.show("Datos eliminados exitosamente","Mensaje",Messagebox.OK,Messagebox.EXCLAMATION);
		}
	}

    
    
    
  
  
  
}
