package controlador.competencia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;

import modelo.Categoria;
import modelo.Constante;
import modelo.ConstanteCategoria;
import modelo.IndicadorCategoriaCompetencia;


import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Button;

import servicio.implementacion.ServicioCategoria;
import servicio.implementacion.ServicioConstante;
import servicio.implementacion.ServicioConstanteCategoria;


public class CntrlFrmConstante extends GenericForwardComposer{
	
	//Atributos
	AnnotateDataBinder binder;
	Component formulario;
	
	//Servicios Utilizados
	Constante constante;
	ConstanteCategoria constanteCategoria;
	ServicioConstante servicioConstante;
	ServicioCategoria servicioCategoria;
	ServicioConstanteCategoria servicioConstanteCategoria;
	List<Categoria> categorias;
	List<ConstanteCategoria> categoriasSeleccionadas;
	List<ConstanteCategoria> listaEliminar;
	
	//Vista
	Textbox txtAbreviatura;
	Textbox txtNombre;
	Textbox txtValor;
	Listbox lsbxCategorias;
	Listbox lsbxCategoriasSeleccionadas;
	Button btnEliminar;
	
	Boolean lista = false;
	Boolean actualizar;
	
	
	//SETTERS Y GETTERS
	
	public Constante getConstante() {
		return constante;
	}

	public void setConstante(Constante constante) {
		this.constante = constante;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public ConstanteCategoria getConstanteCategoria() {
		return constanteCategoria;
	}

	public List<ConstanteCategoria> getCategoriasSeleccionadas() {
		return categoriasSeleccionadas;
	}

	public void setCategoriasSeleccionadas(
			List<ConstanteCategoria> categoriasSeleccionadas) {
		this.categoriasSeleccionadas = categoriasSeleccionadas;
	}

	public void setConstanteCategoria(ConstanteCategoria constanteCategoria) {
		this.constanteCategoria = constanteCategoria;
	}

	//M�todo que se llama al cargar la ventana
	public void doAfterCompose (Component c) throws Exception{		
		super.doAfterCompose(c);
		c.setVariable("cntrl",this,true);
		formulario = c;
		restaurar();
		
		
	}
	
	public void restaurar(){
		constante = new Constante();
		categoriasSeleccionadas = new ArrayList<ConstanteCategoria>();
		categorias = servicioCategoria.listarActivos();
		listaEliminar = new ArrayList<ConstanteCategoria>();
		actualizar = false;
		
	}
	
	// Convierte una conjunto a una lista
	public List ConvertirConjuntoALista(Set conjunto) {
		List l = new ArrayList();
		for (Iterator i = conjunto.iterator(); i.hasNext();) {
			l.add(i.next());
		}
		return l;
	}
	
	// Convierte una lista a un conjunto
			public Set ConvertirListaAConjunto(List lista) {
				Set c = new HashSet();
				for (Iterator i = lista.iterator(); i.hasNext();) {
					c.add(i.next());
				}
				return c;
			}
	
	
	// M�todo que permite pasar de una lista a otra
	public void Agregar(Listbox origen, Listbox destino, List lista) {

		Set seleccionados = origen.getSelectedItems();
		for (Iterator i = seleccionados.iterator(); i.hasNext();) {
			boolean sw = false;
			Listitem li = (Listitem) i.next();
			Categoria c1 = (Categoria) li.getValue();
			ConstanteCategoria cc = new ConstanteCategoria();
			cc.setValor(0);
			cc.setConstante(constante);
			cc.setCategoria(c1);
			List seleccionDestino = destino.getItems();
			for (Iterator j = seleccionDestino.iterator(); j.hasNext();) {
				Listitem li2 = (Listitem) j.next();
				ConstanteCategoria c2 = (ConstanteCategoria) li2.getValue();
				if (c1.getNombre().equals(c2.getCategoria().getNombre())) {
					sw = true;
					break;
				}
			}
			if (!sw) {
				lista.add(cc);
			}
		}
	}
	
	
	/*public void Quitar(Listbox origen, List lista,List elim) {
		Set seleccionados = origen.getSelectedItems();
		for (Iterator i = seleccionados.iterator(); i.hasNext();) {
			Listitem li = (Listitem) i.next();
			IndicadorCategoriaCompetencia icc = (IndicadorCategoriaCompetencia) li.getValue();
			if (icc.getCodigoIndicadorCategoriaCompetencia() != 0)
				eliminar.add(icc);
			lista.remove(icc);
		}
	}*/
	
	//M�todo que permite quitar de la lista
	public void Quitar(Listbox origen, List lista,List elim ) {
		Set seleccionados = origen.getSelectedItems();
		for (Iterator i = seleccionados.iterator(); i.hasNext();) {
			Listitem li = (Listitem) i.next();
			ConstanteCategoria cc = (ConstanteCategoria) li.getValue();
			if(cc.getCodigoConstanteCategoria() != 0)
				listaEliminar.add(cc);
			//Object o = li.getValue();
			lista.remove(cc);
		}
	}
	
	//BOTONES BUSCAR, AGREGAR Y QUITAR
	
	//Llama el catalogo
	public void onClick$btnBuscar() {
		//se crea el catalogo y se llama
		Component catalogo = Executions.createComponents("/Competencias/Vistas/FrmCatalogoConstante.zul", null, null);
		//asigna una referencia del formulario al catalogo.
		catalogo.setVariable("formulario",formulario, false);
	    formulario.addEventListener("onCatalogoCerrado", new EventListener() {		
			@Override
			//Este metodo se llama cuando se envia la señal desde el catalogo
			public void onEvent(Event arg0) throws Exception {
				//se obtiene la constante.
				constante = (Constante) formulario.getVariable("constante",false);
				categoriasSeleccionadas= servicioConstanteCategoria.listarConstantesPorCategoria(constante);
				//categoriasSeleccionadas = ConvertirConjuntoALista(constante.getConstanteCategorias());
				//categoriasSeleccionadas.get(0);
				btnEliminar.setDisabled(false);
				lista = true;
				actualizar = true;
				binder.loadAll();				
			}
		});
	}
	
	//Pasa de una lista a otra
	public void onClick$btnAgregar() {
		Agregar(lsbxCategorias,lsbxCategoriasSeleccionadas,
				categoriasSeleccionadas);
		binder.loadAll();
	}
	
	
	public void onClick$btnQuitar(){
        Quitar(lsbxCategoriasSeleccionadas,categoriasSeleccionadas,listaEliminar);
		binder.loadAll();
	
	}
	
	/*for (int i=0;i<indicadoresSeleccionados.size();i++){
			servicioIndicadorCategoriaCompetencia.agregar(indicadoresSeleccionados.get(i));
		}
		for (int i=0;i<indicadoresSeleccionadosColectivos.size();i++){
			servicioIndicadorCategoriaCompetencia.agregar(indicadoresSeleccionadosColectivos.get(i));
		}
		
		for (int i=0;i<eliminar.size();i++){
			servicioIndicadorCategoriaCompetencia.eliminar(eliminar.get(i));
		}*/
		
	//BOTONES GUARDAR, ELIMINAR, CANCELAR Y SALIR
	public void onClick$btnGuardar() throws InterruptedException{
		if (txtAbreviatura.getValue().isEmpty()){
			throw new WrongValueException(txtAbreviatura, "Debe ingresar la abreviatura");
		}else if (txtNombre.getValue().isEmpty()){
			throw new WrongValueException(txtNombre, "Debe ingresar el nombre");
		}else if (categoriasSeleccionadas.size() > 0) {
				constante.setAbreviatura(constante.getAbreviatura().toUpperCase());
				constante.setNombre(constante.getNombre().toUpperCase());
				servicioConstante.agregar(constante);
				for (int i=0;i<categoriasSeleccionadas.size();i++){
					servicioConstanteCategoria.agregar(categoriasSeleccionadas.get(i));
				}
				for (int i=0;i<listaEliminar.size();i++){
					servicioConstanteCategoria.eliminar(listaEliminar.get(i));
				}				
				if (actualizar == false){
					Messagebox.show("Datos agregados exitosamente", "Mensaje",
							Messagebox.OK, Messagebox.EXCLAMATION);
						
				} else {
					Messagebox.show("Datos actualizados exitosamente", "Mensaje",Messagebox.OK, Messagebox.EXCLAMATION);
				}
				restaurar();
				binder.loadAll();
				

			 } else
					Messagebox.show("Seleccione la Categoria e Ingrese un Valor", "Mensaje",
									Messagebox.OK, Messagebox.EXCLAMATION);
	}
	
	public void onClick$btnEliminar() throws InterruptedException{
		if (lista == true){
			if(Messagebox.show("�Realmente desea eliminar esta constante","Mensaje",Messagebox.YES+Messagebox.NO,Messagebox.QUESTION) == Messagebox.YES){
				
				servicioConstante.eliminar(constante);
				restaurar();
				binder.loadAll();
				Messagebox.show("Datos eliminados exitosamente","Mensaje",Messagebox.OK,Messagebox.EXCLAMATION);
				}
		}else{
			Messagebox.show("Debe seleccionar una constante","Mensaje",Messagebox.OK,Messagebox.EXCLAMATION);
		}
	}
	
	public void onClick$btnCancelar(){
		restaurar();
		binder.loadAll();
	}
	
	public void onClick$btnSalir(){
		formulario.detach();
		
	}

}
