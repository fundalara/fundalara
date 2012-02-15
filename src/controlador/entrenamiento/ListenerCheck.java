package controlador.entrenamiento;

import java.util.Iterator;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;

public class ListenerCheck implements EventListener{
	
	private Listbox lista;
	private Checkbox chcGeneral;
	public ListenerCheck(Listbox lista,Checkbox chcGeneral) {
		// TODO Auto-generated constructor stub
		this.lista=lista;
		this.chcGeneral=chcGeneral;
	}

	@Override
	public void onEvent(Event arg0) throws Exception {
		// TODO Auto-generated method stub
		Checkbox chc =(Checkbox)arg0.getTarget();
		Listitem item;
		Listcell cell;
		boolean c = chc.isChecked();
		if(chc.getId().equals("chcGeneral")){
			for (Object o: lista.getItems()) {
				item = (Listitem)o;
				cell =(Listcell)item.getChildren().get(0);
				if (cell.getChildren().size()==0)
					continue;
				chc =(Checkbox)cell.getChildren().get(0);
				chc.setChecked(c);
				item.setDisabled(!c);
				chequearComponentes(item, c);
			}
		}
		else{
			item=(Listitem)chc.getParent().getParent();
			item.setDisabled(!c);
			if(!c)
				chcGeneral.setChecked(c);
			chequearComponentes(item, c);
		}
		
	}
	
	public void chequearComponentes(Listitem item, boolean c) {
		for (int i = 2; i < item.getChildren().size(); i++) {
			Listcell cell=(Listcell)item.getChildren().get(i);
			Object object = cell.getChildren().get(0);
			if(object instanceof Checkbox)
				((Checkbox)object).setDisabled(!c);
			else if(object instanceof Textbox)
				((Textbox)object).setDisabled(!c);
			else if(object instanceof Combobox)
				((Combobox)object).setDisabled(!c);
		}
	}

}
