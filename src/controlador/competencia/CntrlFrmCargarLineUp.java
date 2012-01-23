package controlador.competencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import modelo.Equipo;
import modelo.EquipoCompetencia;
import modelo.Roster;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

public class CntrlFrmCargarLineUp extends GenericForwardComposer {
	
	
	Component formulario;
	AnnotateDataBinder binder;
	EquipoCompetencia equipoCompetencia;
	Textbox txtDelegado;
    List<Roster> rosters;
    Listbox lsbxRoster;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
			comp.setVariable("cntrl", this, true);
	    formulario = comp;
	    	    
	}
	
	public void onCreate$FrmCargarLineUp(){
		equipoCompetencia =  (EquipoCompetencia) formulario.getVariable("equipo",false);
		rosters = ConvertirConjuntoALista(equipoCompetencia.getEquipo().getRosters());
		String nombre = equipoCompetencia.getPersonaNatural().getPrimerNombre();
		String apellido= equipoCompetencia.getPersonaNatural().getPrimerApellido();
		txtDelegado.setText(nombre+" "+apellido);
		binder.loadAll();
		
      
	}
	
	public List ConvertirConjuntoALista(Set conjunto) {
		List l = new ArrayList();
		for (Iterator i = conjunto.iterator(); i.hasNext();) {
			l.add(i.next());
		}
		return l;
	}

	public EquipoCompetencia getEquipoCompetencia() {
		return equipoCompetencia;
	}

	public void setEquipoCompetencia(EquipoCompetencia equipoCompetencia) {
		this.equipoCompetencia = equipoCompetencia;
	}

	public List<Roster> getRosters() {
		return rosters;
	}

	public void setRosters(List<Roster> rosters) {
		this.rosters = rosters;
	}

	public Listbox getLsbxRoster() {
		return lsbxRoster;
	}

	public void setLsbxRoster(Listbox lsbxRoster) {
		this.lsbxRoster = lsbxRoster;
	}
	
	
    
	
	
	
}
