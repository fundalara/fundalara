package controlador.entrenamiento;

import java.util.Date;

import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;

public class rep_test_ent extends GenericForwardComposer {
	Datebox dtbox1, dtbox2;
	Combobox cmbcategoria, cmbequipo, cmbatleta;
	Button btnimprimir, catalogo;

	public void onChange$dtbox2() {
		Date date = dtbox2.getValue();
		if (date.before(dtbox1.getValue())) {
			alert("La fecha final es menor que la fecha inicial");
		} else {

			cmbcategoria.setDisabled(false);
			cmbequipo.setDisabled(false);
			cmbatleta.setDisabled(false);
			catalogo.setDisabled(false);

		}

	}

	public void onChange$cmbatleta() {

		btnimprimir.setDisabled(false);

	}

	
				
			
		
		
	}

