package controlador.general;

import java.lang.reflect.Method;  
import java.util.ArrayList;
import java.util.Collections; 
import java.util.Comparator;  
import java.util.List;

import modelo.ActividadEntrenamiento;
import modelo.DatoBasico;
import modelo.EscalaMedicion;
import modelo.TipoDato;
import modelo.ValorEscala;

import org.zkoss.zhtml.Tbody;
import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Window;
import org.zkoss.zul.Row;

import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioTipoDato;

import comun.Util;

public class CntrlDatosBasicos extends GenericForwardComposer {
	Button btnAgregar, btnCancelar, btnSalir, btnQuitar;
	Window wndDatosBasicosSimples;
	Listbox lboxDatos;
	Combobox cmbTipoDato,cmbDatoBasicoA,cmbDatoBasicoB,cmbDatoBasicoC;
	Label lblTipoDato,lblDatoBasicoA,lblDatoBasicoB,lblDatoBasicoC;
	Textbox txtNombre, txtDescripcion;
	Comboitem cmbItem;
	Row rowB;	
	ServicioDatoBasico servicioDatoBasico;
	ServicioTipoDato servicioTipoDato;	
	AnnotateDataBinder binder;
	DatoBasico datoBasico;
	TipoDato tipoDato;
	List<TipoDato> listaTipoDato;
	List<DatoBasico> listaDatoBasicoA, listaDatoBasicoB, listaDatoBasicoC;
	Boolean editar;
	Integer index, pos;
	
	public List<TipoDato> getListaTipoDato() {
		return listaTipoDato;
	}

	public void setListaTipoDato(List<TipoDato> listaTipoDato) {
		this.listaTipoDato = listaTipoDato;
	}
	
	

	public List<DatoBasico> getListaDatoBasicoA() {
		return listaDatoBasicoA;
	}

	public void setListaDatoBasicoA(List<DatoBasico> listaDatoBasicoA) {
		this.listaDatoBasicoA = listaDatoBasicoA;
	}

	public List<DatoBasico> getListaDatoBasicoB() {
		return listaDatoBasicoB;
	}

	public void setListaDatoBasicoB(List<DatoBasico> listaDatoBasicoB) {
		this.listaDatoBasicoB = listaDatoBasicoB;
	}

	public List<DatoBasico> getListaDatoBasicoC() {
		return listaDatoBasicoC;
	}

	public void setListaDatoBasicoC(List<DatoBasico> listaDatoBasicoC) {
		this.listaDatoBasicoC = listaDatoBasicoC;
	}

	public DatoBasico getDatoBasico() {
		return datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);				
		datoBasico = new DatoBasico();
		tipoDato   = new TipoDato();
		listaDatoBasicoA  = new ArrayList<DatoBasico>();
		listaDatoBasicoB = new ArrayList<DatoBasico>();
		listaDatoBasicoC = new ArrayList<DatoBasico>();
		inicializar();
		listaTipoDato = servicioTipoDato.buscarTrue(true);
		llenarComboTipoDato(listaTipoDato);
		
		editar = false;
	}
		
	public void onChange$cmbTipoDato(){
		lboxDatos.getItems().clear();
		tipoDato = (TipoDato) cmbTipoDato.getSelectedItem().getValue();	
		if (tipoDato.getTipoDato()!=null){		
			if(tipoDato.getTipoDato().getTipoDato()!=null){		
				if(tipoDato.getTipoDato().getTipoDato().getTipoDato()!=null){									
					if(tipoDato.getTipoDato().getTipoDato().getTipoDato().getTipoDato()!=null){									
					}
					else{	
						inicializar();
						pos=3;
						rowB.setVisible(true);
						cmbDatoBasicoA.setVisible(true);
						cmbDatoBasicoB.setVisible(true); 
						cmbDatoBasicoC.setVisible(true);
						lblDatoBasicoA.setVisible(true);
						lblDatoBasicoB.setVisible(true);
						lblDatoBasicoC.setVisible(true);																																											
						lblDatoBasicoA.setValue(altenarMayuscula(tipoDato.getTipoDato().getTipoDato().getTipoDato().getNombre()));
						lblDatoBasicoB.setValue(altenarMayuscula(tipoDato.getTipoDato().getTipoDato().getNombre())); 						
						lblDatoBasicoC.setValue(altenarMayuscula(tipoDato.getTipoDato().getNombre()));
						cmbDatoBasicoA.setDisabled(false);
												 					       
						listaDatoBasicoA = servicioDatoBasico.buscarPorTipoDato(tipoDato.getTipoDato().getTipoDato().getTipoDato());						
						
						if (listaDatoBasicoA.size() > 0) {							
							llenarCombo(cmbDatoBasicoA, listaDatoBasicoA);							
						}	     				
					}						
				}
				else {
					inicializar();
					rowB.setVisible(true);
					pos = 2;
					cmbDatoBasicoA.setVisible(true);
					cmbDatoBasicoB.setVisible(true);
					lblDatoBasicoA.setVisible(true);
					lblDatoBasicoB.setVisible(true);
					lblDatoBasicoA.setValue(altenarMayuscula(tipoDato.getTipoDato().getTipoDato().getNombre()));
					lblDatoBasicoB.setValue(altenarMayuscula(tipoDato.getTipoDato().getNombre()));												
					cmbDatoBasicoA.setDisabled(false);
																
					listaDatoBasicoA = servicioDatoBasico.buscarPorTipoDato(tipoDato.getTipoDato().getTipoDato());
					
					if (listaDatoBasicoA.size() > 0) {
						llenarCombo(cmbDatoBasicoA, listaDatoBasicoA);					
					}												
				}		
			}
			else{
				inicializar();
				pos=1;
				cmbDatoBasicoA.setVisible(true);				
				lblDatoBasicoA.setVisible(true);
				lblDatoBasicoA.setValue(altenarMayuscula(tipoDato.getTipoDato().getNombre()));
				cmbDatoBasicoA.setDisabled(false);
				
				listaDatoBasicoA = servicioDatoBasico.buscarPorTipoDato(tipoDato.getTipoDato());												
				
				if (listaDatoBasicoA.size() > 0) {
					llenarCombo(cmbDatoBasicoA, listaDatoBasicoA);
				} else {
					alert("No existen registros de "+ lblDatoBasicoA.getValue()+ " al que le desea asociar el(la) "
							+ altenarMayuscula(cmbTipoDato.getSelectedItem().getLabel()));
				}
			}			
		}
		else{
			pos=0;
			inicializar();					
			llenarListaDatos();				
		}		
	}
	
	public void onChange$cmbDatoBasicoA(){
		inicializarCombo(cmbDatoBasicoB);
		inicializarCombo(cmbDatoBasicoC);
		DatoBasico db = (DatoBasico) cmbDatoBasicoA.getSelectedItem().getValue();			
		listaDatoBasicoB = servicioDatoBasico.buscarDatosPorRelacion(db);
		binder.loadComponent(cmbDatoBasicoB);
		
		if (listaDatoBasicoB.size() > 0) {
			llenarCombo(cmbDatoBasicoB, listaDatoBasicoB);
			cmbDatoBasicoB.setDisabled(false);
			if (pos.equals(1)) {
				llenarListaDatos();
			}
		} else if (cmbDatoBasicoB.isVisible()) {
			alert("No existen registros de " + lblDatoBasicoB.getValue() + " asociados a ese " + lblDatoBasicoA.getValue());
			lboxDatos.getItems().clear();
		}		
	}
		
	public void onChange$cmbDatoBasicoB(){
		System.out.println(cmbDatoBasicoB.getSelectedItem().getValue());
		inicializarCombo(cmbDatoBasicoC);
		DatoBasico db = (DatoBasico) cmbDatoBasicoB.getSelectedItem().getValue();		
		listaDatoBasicoC = servicioDatoBasico.buscarDatosPorRelacion(db);		
		
		if (listaDatoBasicoC.size() > 0) {
			llenarCombo(cmbDatoBasicoC, listaDatoBasicoC);
			cmbDatoBasicoC.setDisabled(false);
			if (pos.equals(2)){		
				llenarListaDatos();			
			}
		}else if (cmbDatoBasicoC.isVisible()){
			alert("No existen registros de " + lblDatoBasicoC.getValue() + " asociados a ese " + lblDatoBasicoB.getValue() );	
			lboxDatos.getItems().clear();
		}		
	}
		
	public void onChange$cmbDatoBasicoC(){			
		llenarListaDatos();
	}
	
	public void llenarListaDatos(){
		List<DatoBasico> lista = new ArrayList<DatoBasico>();;
		lboxDatos.getItems().clear();
		if (pos.equals(1)) {
			lista = servicioDatoBasico.buscarDatosPorRelacion((DatoBasico)cmbDatoBasicoA.getSelectedItem().getValue());
		} else if (pos.equals(2)) {
			lista = servicioDatoBasico.buscarDatosPorRelacion((DatoBasico)cmbDatoBasicoB.getSelectedItem().getValue());
		} else if (pos.equals(3)) {
			lista = servicioDatoBasico.buscarDatosPorRelacion((DatoBasico)cmbDatoBasicoC.getSelectedItem().getValue());
		}else if (pos.equals(0)){
			lista = servicioDatoBasico.buscarPorTipoDato(tipoDato);
		}
		
		ordenarLista(lista);	
		for (Object o : lista) {
			DatoBasico dato = (DatoBasico) o;
			llenarListbox(lboxDatos,dato.getNombre(),dato.getDescripcion(),""+dato.getCodigoDatoBasico());
		}	
	}
	
	public void onClick$btnAgregar() {
		if (cmbTipoDato.getValue().equals("--SELECCIONE--")) {
			alert("Seleccione el Tipo de Dato que desea registrar");
			cmbTipoDato.focus();
		} else {
			String nombreTipoDato = altenarMayuscula(cmbTipoDato
					.getSelectedItem().getLabel());
			if (!editar) {
				if (cmbDatoBasicoA.isVisible()
						&& cmbDatoBasicoA.getValue().equals("--SELECCIONE--")) {
					alert("Seleccione el " + lblDatoBasicoA.getValue()
							+ " al que le desea asociar el(la) "
							+ nombreTipoDato);
					cmbDatoBasicoA.focus();
				} else if (cmbDatoBasicoB.isVisible()
						&& cmbDatoBasicoB.getValue().equals("--SELECCIONE--")) {
					alert("Seleccione el " + lblDatoBasicoB.getValue()
							+ " al que le desea asociar el(la) "
							+ nombreTipoDato);
					cmbDatoBasicoB.focus();
				} else if (cmbDatoBasicoC.isVisible()
						&& cmbDatoBasicoC.getValue().equals("--SELECCIONE--")) {
					alert("Seleccione el " + lblDatoBasicoC.getValue()
							+ " al que le desea asociar el(la) "
							+ nombreTipoDato);
					cmbDatoBasicoC.focus();
				} else if (txtNombre.getValue().isEmpty()) {
					alert("Debe introducir el Nombre del(de la) "
							+ nombreTipoDato + " que desea Registrar");
					txtNombre.focus();
				} else if (txtDescripcion.getValue().isEmpty()) {
					txtDescripcion.setValue(" ");
				} else {
					int i = 0;
					boolean repetido = false;
					while (i < lboxDatos.getItems().size()) {
						Listitem item = lboxDatos.getItemAtIndex(i);
						Listcell lc = (Listcell) item.getChildren().get(0);
						if (txtNombre.getValue().equals(lc.getLabel())) {
							alert("El(la) " + nombreTipoDato
									+ " ya se encuentra registrado(a)");
							txtNombre.focus();
							repetido = true;
							break;
						}
						i++;
					}
					if (!repetido) {
						datoBasico.setCodigoDatoBasico(servicioDatoBasico
								.listar().size() + 2);
						datoBasico.setTipoDato((TipoDato) cmbTipoDato
								.getSelectedItem().getValue());
						if (cmbDatoBasicoC.isVisible()) {
							datoBasico
									.setDatoBasico((DatoBasico) cmbDatoBasicoC
											.getSelectedItem().getValue());
						} else {
							if (cmbDatoBasicoB.isVisible()) {
								datoBasico
										.setDatoBasico((DatoBasico) cmbDatoBasicoB
												.getSelectedItem().getValue());
							} else {
								if (cmbDatoBasicoA.isVisible()) {
									datoBasico
											.setDatoBasico((DatoBasico) cmbDatoBasicoA
													.getSelectedItem()
													.getValue());
								} else {
									datoBasico.setDatoBasico(null);
								}
							}
						}
						datoBasico
								.setNombre(txtNombre.getValue().toUpperCase());
						datoBasico.setDescripcion(txtDescripcion.getValue()
								.toUpperCase());
						datoBasico.setEstatus('A');
						
						llenarListbox(lboxDatos, (String) txtNombre.getValue()
								.toUpperCase(), (String) txtDescripcion
								.getValue().toUpperCase(), ""
								+ datoBasico);
						
						txtNombre.setValue("");
						txtDescripcion.setValue("");
						servicioDatoBasico.agregar(datoBasico);
						llenarListaDatos();
						try {
							Messagebox.show("El(la) " + nombreTipoDato
									+ " ha sido guardado con Exito");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						binder.loadAll();
					}
				}
			} else {

				int i = 0;
				boolean repetido = false;
				Listcell lc = (Listcell) lboxDatos.getSelectedItem()
						.getChildren().get(0);
				String nombre = (String) lc.getLabel();
				while (i < lboxDatos.getItems().size()) {
					Listitem item = lboxDatos.getItemAtIndex(i);
					Listcell lcA = (Listcell) item.getChildren().get(0);
					if (txtNombre.getValue().toUpperCase()
							.equals(lcA.getLabel())) {
						if (txtNombre.getValue().toUpperCase() != nombre) {
							alert("El(la) " + nombreTipoDato
									+ " ya se encuentra registradoq(a)");
							txtNombre.focus();
							repetido = true;
							break;
						}
					}
					i++;
				}

				if (txtNombre.getValue().isEmpty()) {
					alert("Debe introducir el Nombre del(de la) "
							+ nombreTipoDato + " que desea Registrar");
					txtNombre.focus();
				} else if (txtDescripcion.getValue().isEmpty()) {
					txtDescripcion.setValue(" ");
				} else if (!repetido) {
					datoBasico.setNombre(txtNombre.getValue().toUpperCase());
					datoBasico.setDescripcion(txtDescripcion.getValue()
							.toUpperCase());
					servicioDatoBasico.actualizar(datoBasico);
					lboxDatos.removeItemAt(index);
					llenarListaDatos();
					txtNombre.setValue("");
					txtDescripcion.setValue("");
					alert("El(la) " + nombreTipoDato
							+ " se ha actualizado con exito");
					editar = false;
				}
			}
		}
	}
	
	public void onClick$btnQuitar() {		
		if (lboxDatos.getItems().size() != 0) {		
			datoBasico.setEstatus('E');			
			servicioDatoBasico.actualizar(datoBasico);
			lboxDatos.removeItemAt(index);
			alert("El(la) " + altenarMayuscula(cmbTipoDato.getSelectedItem().getLabel()) + " se ha eliminado con exito");
			editar = false;
			txtNombre.setValue("");
			txtDescripcion.setValue("");		
			binder.loadAll();
		}
	}
	

	public void onClick$btnCancelar() {
		inicializar();
		cmbTipoDato.setValue("--SELECCIONE--");
		lboxDatos.getItems().clear();
	}

	public void onClick$btnSalir() {
		wndDatosBasicosSimples.detach();
	}

	public void onSelect$lboxDatos() {
		editar=true;
		
		datoBasico = servicioDatoBasico.buscarPorCodigo(Integer.valueOf(lboxDatos.getSelectedItem().getValue()+"")); 
		if (lboxDatos.getSelectedItem().getIndex() >= 0) {
			Listcell lc1 = (Listcell) lboxDatos.getSelectedItem().getChildren()
					.get(0);
			Listcell lc2 = (Listcell) lboxDatos.getSelectedItem().getChildren()
					.get(1);
			txtNombre.setText(lc1.getLabel());
			txtDescripcion.setText(lc2.getLabel());
			txtNombre.focus();
		}
		index = lboxDatos.getSelectedIndex();
	}
	
	
	
	public static void ordenarLista(List lista) {
		Collections.sort(lista, new Comparator<Object>() {
			public int compare(Object obj1, Object obj2) {  
			      
				Class clase = obj1.getClass();
				String getter = "get"
						+ Character.toUpperCase("nombre".charAt(0))
						+ "nombre".substring(1);
				try {
					Method getPropiedad = clase.getMethod(getter);

					Object propiedad1 = getPropiedad.invoke(obj1);
					Object propiedad2 = getPropiedad.invoke(obj2);

					if (propiedad1 instanceof Comparable
							&& propiedad2 instanceof Comparable) {
						Comparable prop1 = (Comparable) propiedad1;
						Comparable prop2 = (Comparable) propiedad2;
						return prop1.compareTo(prop2);
					} else {
						if (propiedad1.equals(propiedad2))
							return 0;
						else
							return 1;

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				return 0;
			}
		});
	}
		   
		   
		
	public void llenarComboTipoDato(List<TipoDato> lista){
		if (lista.size() > 0) {							
			ordenarLista(lista);
			for (Object o : lista) {
				TipoDato db = (TipoDato) o;
				Comboitem item = new Comboitem();
				item.setLabel(db.getNombre());
				item.setValue(db);
				cmbTipoDato.appendChild(item);
			}								
		}	
	}
	
	public void llenarCombo(Combobox combo, List lista){
		ordenarLista(lista);
		for (Object o : lista) {
			DatoBasico db = (DatoBasico) o;
			Comboitem item = new Comboitem();
			item.setLabel(db.getNombre());
			item.setValue(db);
			combo.appendChild(item);
		}		
	}
	
	public void inicializarCombo(Combobox combo){
		combo.getItems().clear();
		combo.setValue("--SELECCIONE--");
		combo.setDisabled(true);	
		
	}
	
	public void llenarListbox(Listbox list, String txtA, String txtB, String txtC) {
		Listitem nvoItem = new Listitem();
		nvoItem.appendChild(new Listcell(txtA));
		nvoItem.appendChild(new Listcell(txtB));
		nvoItem.setValue(txtC);
		nvoItem.setHeight("25px");
		list.appendChild(nvoItem);	
	}
	
	public String altenarMayuscula(String variable){
		String nombre = variable.toLowerCase().substring(0, 1).toUpperCase()
				      + variable.toLowerCase().substring(1, variable.length());
		return nombre;
	}
	
	public void inicializar(){
		rowB.setVisible(false);
		inicializarCombo(cmbDatoBasicoA);
		inicializarCombo(cmbDatoBasicoB);
		inicializarCombo(cmbDatoBasicoC);
		cmbDatoBasicoA.setVisible(false);
		cmbDatoBasicoB.setVisible(false);
		cmbDatoBasicoC.setVisible(false);	
		lblDatoBasicoA.setVisible(false);
		lblDatoBasicoB.setVisible(false);
		lblDatoBasicoC.setVisible(false);
		txtNombre.setValue("");
		txtDescripcion.setValue("");	
	}
}