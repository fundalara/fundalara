package controlador.competencia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;

import modelo.Categoria;
import modelo.Constante;
import modelo.ConstanteCategoria;


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
	
	//Vista
	Textbox txtAbreviatura;
	Textbox txtNombre;
	Textbox txtValor;
	Listbox lsbxCategorias;
	Listbox lsbxCategoriasSeleccionadas;
	Button btnEliminar;
	
	
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
	
	/*public List<Categoria> getCategoriasSeleccionadas() {
		return categoriasSeleccionadas;
	}

	public void setCategoriasSeleccionadas(List<Categoria> categoriasSeleccionadas) {
		this.categoriasSeleccionadas = categoriasSeleccionadas;
	}*/
	
	
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

	//Método que se llama al cargar la ventana
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
	
	
	// Método que permite pasar de una lista a otra
	public void Agregar(Listbox origen, Listbox destino, List lista) {

		Set seleccionados = origen.getSelectedItems();
		for (Iterator i = seleccionados.iterator(); i.hasNext();) {
			boolean sw = false;
			Listitem li = (Listitem) i.next();
			Categoria c1 = (Categoria) li.getValue();
			ConstanteCategoria cc = new ConstanteCategoria();
			//cc.setValor(0);
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
	
	//Método que permite quitar de la lista
	public void Quitar(Listbox origen, List lista) {
		Set seleccionados = origen.getSelectedItems();
		for (Iterator i = seleccionados.iterator(); i.hasNext();) {
			Listitem li = (Listitem) i.next();
			Object o = li.getValue();
			lista.remove(o);
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
			//Este metodo se llama cuando se envia la seÃ±al desde el catalogo
			public void onEvent(Event arg0) throws Exception {
				//se obtiene la constante.
				constante = (Constante) formulario.getVariable("constante",false);
				categoriasSeleccionadas = ConvertirConjuntoALista(constante.getConstanteCategorias());
				categoriasSeleccionadas.get(0);
				btnEliminar.setDisabled(false);
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
        Quitar(lsbxCategoriasSeleccionadas,categoriasSeleccionadas);
		binder.loadAll();
	
	}
	
		
	//BOTONES GUARDAR, ELIMINAR, CANCELAR Y SALIR
	public void onClick$btnGuardar() throws InterruptedException{
		if (txtAbreviatura.getText() != null)
			if (txtNombre.getText() != null)
				if (categoriasSeleccionadas.size() > 0) {
					
					System.out.println(categoriasSeleccionadas.size());
					System.out.println(categoriasSeleccionadas.get(0).getCategoria().getNombre());
					//constante.setConstanteCategorias(ConvertirListaAConjunto(categoriasSeleccionadas));
					servicioConstante.agregar(constante);
					servicioConstanteCategoria.agregar(categoriasSeleccionadas);
					
					Messagebox.show("Datos agregados exitosamente", "Mensaje",
							Messagebox.OK, Messagebox.EXCLAMATION);
					restaurar();
					binder.loadAll();

				} else
					Messagebox.show("Seleccione la Categoria e Ingrese un Valor", "Mensaje",
							Messagebox.OK, Messagebox.EXCLAMATION);
	}
	
	public void onClick$btnEliminar() throws InterruptedException{
		if(Messagebox.show("¿Realmente desea eliminar esta constante","Mensaje",Messagebox.YES+Messagebox.NO,Messagebox.QUESTION) == Messagebox.YES){
			
			servicioConstante.eliminar(constante);
			constante = new Constante();
			binder.loadAll();
			Messagebox.show("Datos eliminados exitosamente","Mensaje",Messagebox.OK,Messagebox.EXCLAMATION);
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
