package controlador.entrenamiento;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zul.AbstractListModel;
import org.zkoss.zul.ListModelList;

public class ModeloListBox extends ListModelList {
    public ModeloListBox(List list) {
        super(list, true);
    }
}
