package controlador.entrenamiento;

import java.util.List;

import modelo.Jugador;
import modelo.JugadorPlan;
import modelo.Roster;
import modelo.RosterPlan;

import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Textbox;

public class Render implements ListitemRenderer{
	
	private List<Combobox> combos;
	private int indice;
	public Render(List<Combobox> combos) {
		// TODO Auto-generated constructor stub
		this.combos=combos;
		indice=0;
	}
	
	public Combobox copiarInfo(Combobox combobox) {
		Combobox combo= new Combobox();
		Comboitem item;
		for (int i = 0; i < combobox.getItemCount(); i++) {
			item= new Comboitem();
			item.setValue(combobox.getItemAtIndex(i).getValue());
			item.setLabel(combobox.getItemAtIndex(i).getLabel());
			item.setTooltiptext(combobox.getItemAtIndex(i).getTooltiptext());
			combo.appendChild(item);
		}
		combo.setSelectedIndex(0);
		combo.setButtonVisible(true);
		combo.setWidth("50px");
		return combo;
	}

	@Override
	public void render(Listitem item, Object obj) throws Exception {
		// TODO Auto-generated method stub
		item.setWidth("320px");
		List lista = (List)obj;
		for (Object object : lista) {
			if(object instanceof String){
				Listcell cell =new Listcell(""+object);
				cell.setParent(item);
			}
			else if(object instanceof Checkbox){
				Listcell lc = new Listcell();
				lc.appendChild((Checkbox)object);
				lc.setParent(item);
			}
			else if(object instanceof Textbox){
				Listcell lc = new Listcell();
				lc.appendChild((Textbox)object);
				lc.setParent(item);
			}
			else if((object instanceof Roster)||(object instanceof RosterPlan)){
				item.setValue(object);
			}
			else{
				Listcell lc = new Listcell();
				lc.appendChild(copiarInfo(combos.get((Integer)object)));
				lc.setParent(item);
			}
		}
		
	}

}
