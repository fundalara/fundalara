package seguridad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Panel;
import org.zkoss.zul.SimpleTreeModel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;
import org.zkoss.zul.Window;

import comun.MensajeMostrar;

import modelo.DocumentoAcreedor;
import modelo.Grupo;
import modelo.GrupoUsuario;
import modelo.GrupoUsuarioId;
import modelo.Rol;
import modelo.RolGrupo;
import modelo.RolGrupoId;
import modelo.Usuario;
import servicio.implementacion.ServicioGrupo;
import servicio.implementacion.ServicioGrupoUsuario;
import servicio.implementacion.ServicioRol;
import servicio.implementacion.ServicioRolGrupo;
import servicio.implementacion.ServicioUsuario;
import dao.general.DaoRolGrupo;

public class CntrlGestionarGrupos extends GenericForwardComposer {
	Grupo grupo;
	Rol rol, rolAsignado;
	RolGrupo rolGrupo;
	DaoRolGrupo daoRolGrupo = new DaoRolGrupo();
	ServicioGrupo servicioGrupo;
	ServicioRol servicioRol;
	// ServicioRolGrupo servicioRolGrupo;

	List<Grupo> listaGrupos = new ArrayList<Grupo>();
	List<Rol> listaRol = new ArrayList<Rol>();
	List<Rol> listaRolSin = new ArrayList<Rol>();
	List<Rol> listaAuxRol = new ArrayList<Rol>();
	List<RolGrupo> listaRolesAsignados = new ArrayList<RolGrupo>();
	List<RolGrupo> listaRolesAsigAux = new ArrayList<RolGrupo>();
	List<RolGrupo> listaRolesEliminados = new ArrayList<RolGrupo>();
	List<GrupoUsuario> listaUsuariosEliminados = new ArrayList<GrupoUsuario>();

	Textbox txtGrupo;
	Listbox lbxGrupos, lbxRolesAgregados, lbxRoles;
	Button btnAsignarUsuarios;
	Checkbox chRolAsig, chRol;
	AnnotateDataBinder binder;
	Component formulario;
	boolean flag = false;
	// Tree arbolMenu, arbolMenuSin;
	Treechildren treeChildren = new Treechildren();
	Treechildren treeChildren2 = new Treechildren();

	// ---------------------------------------------------------------------------------------------------
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		listaGrupos = servicioGrupo.listarActivos();
		formulario = comp;

	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnAgregar() {
		if (txtGrupo.getText().trim() != "") {
			grupo = new Grupo();
			grupo.setCodigoGrupo(servicioGrupo.listar().size() + 1);
			grupo.setEstatus('A');
			grupo.setNombre(txtGrupo.getText().toUpperCase());
			listaGrupos.add(grupo);
			txtGrupo.setText("");
			binder.loadComponent(lbxGrupos);
			servicioGrupo.actualizar(grupo);
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnQuitar() {
		grupo = listaGrupos.get(lbxGrupos.getSelectedIndex());
		grupo.setEstatus('E');
		listaGrupos.remove(lbxGrupos.getSelectedIndex());
		txtGrupo.setText("");
		servicioGrupo.actualizar(grupo);
	}

	// ---------------------------------------------------------------------------------------------------
	public void onSelect$lbxGrupos() {
		// listaRolesAsignados =
		// servicioRolGrupo.buscarRolesPorGrupo(listaGrupos
		// .get(lbxGrupos.getSelectedIndex()));
		listaRolesAsignados = daoRolGrupo.buscarRolesPorGrupo(listaGrupos
				.get(lbxGrupos.getSelectedIndex()));
		btnAsignarUsuarios.setVisible(true);
		listaRol = servicioRol.buscarRolesSinAsignar(listaGrupos.get(lbxGrupos
				.getSelectedIndex()));
		binder.loadComponent(lbxRoles);
		binder.loadComponent(lbxRolesAgregados);
	}

	// ---------------------------------------------------------------------------------------------------

	public void onSelect$lbxRolesAgregados() {
		seleccionar(lbxRolesAgregados);
	}

	// ---------------------------------------------------------------------------------------------------
	public void onSelect$lbxRoles() {
		seleccionar(lbxRoles);
	}

	// ---------------------------------------------------------------------------------------------------
	public void seleccionar(Listbox lista) {
		Listcell celda = (Listcell) lista.getSelectedItem().getChildren()
				.get(0);
		Checkbox ch = (Checkbox) celda.getChildren().get(0);
		if (ch.isChecked()) {
			ch.setChecked(false);
		} else {
			ch.setChecked(true);
		}
	}

	// ---------------------------------------------------------------------------------------------------

	public void onClick$btnAgregarRol() {
		int pag = 0;
		try {

			for (int i = 0; i < lbxRoles.getItemCount(); i++) {
				Listcell celda = (Listcell) lbxRoles.getItemAtIndex(i)
						.getChildren().get(0);
				Checkbox ch = (Checkbox) celda.getChildren().get(0);
				if (ch.isChecked()) {
					rolGrupo = new RolGrupo();
					rolGrupo.setGrupo(listaGrupos.get(lbxGrupos
							.getSelectedIndex()));
					rolGrupo.setRol(listaRol.get(i));
					rolGrupo.setId(new RolGrupoId(listaRol.get(i)
							.getCodigoRol(), listaGrupos.get(
							lbxGrupos.getSelectedIndex()).getCodigoGrupo()));
					rolGrupo.setEstatus("A");
					listaRolesAsignados.add(rolGrupo);

					listaRol.remove(i);

				}
				pag++;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		binder.loadComponent(lbxRoles);
		binder.loadComponent(lbxRolesAgregados);
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnQuitarRol() {
		try {
			if (lbxRolesAgregados.getSelectedCount() != 0) {
				listaRol.add(listaRolesAsignados.get(
						lbxRolesAgregados.getSelectedIndex()).getRol());
				rolGrupo = listaRolesAsignados.get(lbxRolesAgregados
						.getSelectedIndex());
				rolGrupo.setEstatus("E");

				listaRolesEliminados.add(rolGrupo);
				listaRolesAsignados
						.remove(lbxRolesAgregados.getSelectedIndex());
				binder.loadComponent(lbxRolesAgregados);
				binder.loadComponent(lbxRoles);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// ---------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnRegistrar() {
		try {
			Messagebox.show(MensajeMostrar.GUARDAR, MensajeMostrar.TITULO
					+ "Importante", Messagebox.OK | Messagebox.CANCEL,
					Messagebox.QUESTION, new EventListener() {
						@Override
						public void onEvent(Event arg0)
								throws InterruptedException {
							if (arg0.getName().toString() == "onOK") {
								for (int i = 0; i < listaGrupos.size(); i++) {
									servicioGrupo.actualizar(listaGrupos.get(i));

									Rol auxRol = servicioRol.buscarPorCodigo(0);
									RolGrupo aux = daoRolGrupo
											.buscarRolExistente(auxRol,
													listaGrupos.get(i));
									if (aux == null) {
										aux = new RolGrupo();
										aux.setGrupo(listaGrupos.get(i));
										aux.setRol(auxRol);
										aux.setId(new RolGrupoId(auxRol
												.getCodigoRol(), listaGrupos
												.get(i).getCodigoGrupo()));
										aux.setEstatus("A");
										daoRolGrupo.actualizar(aux);
									} else {
										aux.setEstatus("A");
										daoRolGrupo.actualizar(aux);
									}

								}

								for (int i = 0; i < listaRolesEliminados.size(); i++) {
									daoRolGrupo.actualizar(listaRolesEliminados
											.get(i));
								}

								for (int i = 0; i < listaRolesAsignados.size(); i++) {
									daoRolGrupo.actualizar(listaRolesAsignados
											.get(i));
								}

								reiniciarListas();
								btnAsignarUsuarios.setVisible(false);
								binder.loadAll();
								Messagebox.show(MensajeMostrar.PROCESO_EXITOSO,
										MensajeMostrar.TITULO + "Información",
										Messagebox.OK, Messagebox.INFORMATION);
							}
						}
					});
		} catch (Exception e) {
			// ---------
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnAsignarUsuarios() {
		if (lbxGrupos.getSelectedCount() != 0) {
			Map params = new HashMap();
			params.put("grupo", listaGrupos.get(lbxGrupos.getSelectedIndex()));
			Component usuarios = Executions.createComponents(
					"/Seguridad/frmGruposUsuarios.zul", null,
					params);
		} else {
			throw new WrongValueException(lbxGrupos,
					"Seleccione un grupo de la lista");
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void reiniciarListas() {
		listaGrupos = servicioGrupo.listarActivos();
		listaRol = new ArrayList<Rol>();
		listaRolesAsignados = new ArrayList<RolGrupo>();
		listaRolesEliminados = new ArrayList<RolGrupo>();
		listaUsuariosEliminados = new ArrayList<GrupoUsuario>();
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnCancelar() {
		reiniciarListas();
		binder.loadAll();
	}

	// ---------------------------------------------------------------------------------------------------
	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<Grupo> getListaGrupos() {
		return listaGrupos;
	}

	public void setListaGrupos(List<Grupo> listaGrupos) {
		this.listaGrupos = listaGrupos;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public RolGrupo getRolGrupo() {
		return rolGrupo;
	}

	public void setRolGrupo(RolGrupo rolGrupo) {
		this.rolGrupo = rolGrupo;
	}

	public List<Rol> getListaRol() {
		return listaRol;
	}

	public void setListaRol(List<Rol> listaRol) {
		this.listaRol = listaRol;
	}

	public Rol getRolAsignado() {
		return rolAsignado;
	}

	public void setRolAsignado(Rol rolAsignado) {
		this.rolAsignado = rolAsignado;
	}

	public List<RolGrupo> getListaRolesAsignados() {
		return listaRolesAsignados;
	}

	public void setListaRolesAsignados(List<RolGrupo> listaRolesAsignados) {
		this.listaRolesAsignados = listaRolesAsignados;
	}

	public List<Rol> getListaRolSin() {
		return listaRolSin;
	}

	public void setListaRolSin(List<Rol> listaRolSin) {
		this.listaRolSin = listaRolSin;
	}

	// ---------------------------------------------------------------------------------------------------
}
