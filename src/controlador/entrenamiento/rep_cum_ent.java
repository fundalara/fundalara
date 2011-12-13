package controlador.entrenamiento;

import java.sql.Date;

import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Textbox;

public class rep_cum_ent extends GenericForwardComposer {
	Datebox dtboxi, dtboxf;
	Combobox cmb_sel_ent, cmb_sel_cat;
	Button btnimp, btncat;
	Textbox txtequipo;

	public void onChange$dtboxf() {
		java.util.Date date = dtboxf.getValue();
		if (date.before(dtboxi.getValue()))
			alert("La Fecha Final es menor que la fecha Inicial");
		else {

			cmb_sel_ent.setDisabled(false);
			cmb_sel_cat.setDisabled(false);
			btncat.setDisabled(false);
		}
	}

	public void onChange$cmb_sel_cat() {
		btnimp.setDisabled(false);
	}
	
	
	}

