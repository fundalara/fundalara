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

public class CntrlGestionarGrupos extends GenericForwardComposer {
	Grupo grupo;
	Rol rol, rolAsignado;
	RolGrupo rolGrupo;

	ServicioGrupo servicioGrupo;
	ServicioRol servicioRol;
	ServicioRolGrupo servicioRolGrupo;

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
		listaRolesAsignados = servicioRolGrupo.buscarRolesPorGrupo(listaGrupos
				.get(lbxGrupos.getSelectedIndex()));
		// binder.loadComponent(lbxRolesAgregados);
		// binder.loadComponent(lbxRoles);
		btnAsignarUsuarios.setVisible(true);
		// arbolMenu.clear();
		listaRol = servicioRol.buscarRolesSinAsignar(listaGrupos.get(lbxGrupos
				.getSelectedIndex()));
		// listaRolSin = servicioRol.listarActivos();
		binder.loadComponent(lbxRoles);
		binder.loadComponent(lbxRolesAgregados);
		// binder.loadAll();
		// crearMenuSinAsignar(listaRol, arbolMenu, treeChildren);
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
	public void onPaging$lbxRoles() {
		// seleccionar(lbxRoles);
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
	public void asignarPorArriba(List<Rol> lista, Rol rol,
			List<Rol> listaMenuCon) {
		Rol auxRol = rol.getRol();
		// while (g < lista.size()) {
		if (auxRol != null) {
			listaMenuCon.add(auxRol);
			auxRol = auxRol.getRol();
			if (auxRol != null) {
				listaMenuCon.add(auxRol);
				auxRol = auxRol.getRol();
				if (auxRol != null) {
					listaMenuCon.add(auxRol);
					auxRol = auxRol.getRol();
					if (auxRol != null) {
						listaMenuCon.add(auxRol);
						auxRol = auxRol.getRol();
						if (auxRol != null) {
							listaMenuCon.add(auxRol);
							auxRol = auxRol.getRol();
							if (auxRol != null) {
								listaMenuCon.add(auxRol);
							}
						}
					}
				}
			}
			// if (!auxRol.getNombre().equals("ITEM")) {
			// listaMenuCon.add(auxRol);
			// auxRol = auxRol.getRol();
			// g = 0;
			// }
		}
		// g++;
		// }
	}

	// ---------------------------------------------------------------------------------------------------
	public void asignarPorDebajo(List<Rol> lista, Rol rol,
			List<Rol> listaMenuCon) {
		int g = 0;
		Rol auxRol = rol;
		// while (g < lista.size()) {
		if (auxRol != null) {
			Rol rolAuxiliar = auxRol;
			if (rolAuxiliar != null) {
				List<Rol> listaDebajo = servicioRol.listarPorPadre(rolAuxiliar);
				listaMenuCon.add(rolAuxiliar);
				System.out.println("Nivel 0---- " + rolAuxiliar.getNombre());
				for (int i = 0; i < listaDebajo.size(); i++) {
					// if (buscarItem(listaMenuCon, listaDebajo.get(i))==false)
					// {
					Rol rolAuxiliar2 = new Rol();
					listaMenuCon.add(listaDebajo.get(i));
					List<Rol> listaDebajo2 = servicioRol
							.listarPorPadre(listaDebajo.get(i));
					rolAuxiliar2 = listaDebajo.get(i);
					listaMenuCon.add(rolAuxiliar2);
					System.out.println("Nivel 1---- "
							+ rolAuxiliar2.getNombre());
					for (int l = 0; l < listaDebajo2.size(); l++) {
						Rol rolAuxiliar3 = new Rol();
						rolAuxiliar3 = listaDebajo2.get(l);
						listaMenuCon.add(rolAuxiliar3);
						List<Rol> listaDebajo3 = servicioRol
								.listarPorPadre(rolAuxiliar3);
						System.out.println("Nivel 2---- "
								+ rolAuxiliar3.getNombre());
						for (int f = 0; f < listaDebajo3.size(); f++) {
							Rol rolAuxiliar4 = new Rol();
							rolAuxiliar4 = listaDebajo3.get(f);
							listaMenuCon.add(rolAuxiliar4);
							List<Rol> listaDebajo4 = servicioRol
									.listarPorPadre(rolAuxiliar4);
							System.out.println("Nivel 3---- "
									+ rolAuxiliar4.getNombre());
							for (int w = 0; w < listaDebajo4.size(); w++) {
								listaMenuCon.add(listaDebajo4.get(w));

								Rol rolAuxiliar5 = new Rol();
								rolAuxiliar5 = listaDebajo4.get(w);
								listaMenuCon.add(rolAuxiliar5);
								List<Rol> listaDebajo5 = servicioRol
										.listarPorPadre(rolAuxiliar5);
								System.out.println("Nivel 4---- "
										+ rolAuxiliar4.getNombre());
								for (int z = 0; z < listaDebajo5.size(); z++) {
									listaMenuCon.add(listaDebajo5.get(z));
									System.out.println("Nivel 5---- "
											+ listaDebajo5.get(z).getNombre());
								}
							}
						}
					}

					// }
				}
				// auxRol =
				// servicioRol.buscarPorCodigo(rolAuxiliar.getCodigoRol());
			}
		}
		// g++;
		// }
	}

	// ---------------------------------------------------------------------------------------------------
	public boolean buscarItem(List<Rol> lista, Rol rol) {
		boolean encontrado = false;
		int ind = 0;
		while (encontrado == false && ind < lista.size()) {
			if (lista.get(ind).getCodigoRol() == (rol.getCodigoRol())) {
				lista.remove(ind);
				encontrado = true;
			}
			ind++;
		}
		return encontrado;
	}

	// ---------------------------------------------------------------------------------------------------
	public void eliminarItem(List<Rol> lista, Rol rol) {
		for (int h = 0; h < lista.size(); h++) {
			if (lista.get(h).getRol().equals(rol)) {
				lista.remove(h);
				return;
			}
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void onSelect$arbolMenu() {
		// rol = ((Rol) arbolMenu.getSelectedItem().getValue());
		// System.out.println(rol.getNombre() + "  - ROL");
		// List<Rol> asignados = new ArrayList<Rol>();
		// if (!buscarItem(listaRolSin, rol) == false){
		// asignarPorArriba(listaRol, rol, asignados);
		// crearMenuSinAsignar(asignados, arbolMenuSin, treeChildren2);
		// }

		// asignarPorDebajo(listaRol, rol, asignados);

	}

	// ---------------------------------------------------------------------------------------------------
	public void crearMenuSinAsignar(List<Rol> listaRol, Tree arbol,
			Treechildren treeChildrenArbol) {

		Treerow treerow = new Treerow();
		arbol.getChildren().clear();
		treeChildrenArbol.getChildren().clear();
		Treechildren treech = new Treechildren();
		Treechildren treechAux = new Treechildren();
		Treechildren treech3 = new Treechildren();
		Treechildren treech4 = new Treechildren();
		Treechildren treech5 = new Treechildren();
		Treechildren treech6 = new Treechildren();
		Treeitem treeItem = new Treeitem();
		Treeitem treeItemAux = new Treeitem();
		Treeitem treeItem3 = new Treeitem();
		Treeitem treeItem4 = new Treeitem();
		Treeitem treeItem5 = new Treeitem();
		Treeitem treeItem6 = new Treeitem();

		for (int i = 0; i < listaRol.size(); i++) {
			listaAuxRol = servicioRol.listarPorPadre(listaRol.get(i));
			treech = new Treechildren();
			treeItem = new Treeitem(listaRol.get(i).getNombre());
			treeItem.setValue(listaRol.get(i));
			// System.out.println(listaRol.get(i).getNombre());
			if (listaAuxRol.size() != 0) {

				for (int j = 0; j < listaAuxRol.size(); j++) {
					List<Rol> listaNivel3 = servicioRol
							.listarPorPadre(listaAuxRol.get(j));
					treeItemAux = new Treeitem(listaAuxRol.get(j).getNombre());
					treeItemAux.setValue(listaAuxRol.get(j));

					if (listaNivel3.size() != 0) {
						treechAux = new Treechildren();
						for (int k = 0; k < listaNivel3.size(); k++) {
							treeItem3 = new Treeitem(listaNivel3.get(k)
									.getNombre());
							System.out.println(listaNivel3.get(k).getNombre());
							treeItem3.setValue(listaNivel3.get(k));
							List<Rol> listaNivel4 = servicioRol
									.listarPorPadre(listaNivel3.get(k));
							if (listaNivel4.size() != 0) {
								treech4 = new Treechildren();
								for (int l = 0; l < listaNivel4.size(); l++) {
									treeItem4 = new Treeitem(listaNivel4.get(l)
											.getNombre());
									treeItem4.setValue(listaNivel4.get(l));
									List<Rol> listaNivel5 = servicioRol
											.listarPorPadre(listaNivel4.get(l));
									if (listaNivel5.size() != 0) {
										treech5 = new Treechildren();
										for (int m = 0; m < listaNivel5.size(); m++) {
											treeItem5 = new Treeitem(
													listaNivel5.get(m)
															.getNombre());
											treeItem5.setValue(listaNivel5
													.get(m));
											List<Rol> listaNivel6 = servicioRol
													.listarPorPadre(listaNivel5
															.get(m));
											if (listaNivel6.size() != 0) {
												treech6 = new Treechildren();
												for (int n = 0; n < listaNivel6
														.size(); n++) {
													treeItem6 = new Treeitem(
															listaNivel6
																	.get(n)
																	.getNombre());
													treeItem6
															.setValue(listaNivel6
																	.get(n));
													treech6.appendChild(treeItem6);
													eliminarItem(listaRol,
															listaNivel6.get(n)
																	.getRol());
												}
												treeItem5.appendChild(treech6);
											}
											treech5.appendChild(treeItem5);
											eliminarItem(listaRol, listaNivel5
													.get(m).getRol());
										}
										treeItem4.appendChild(treech5);
									}
									treech4.appendChild(treeItem4);
									eliminarItem(listaRol, listaNivel4.get(l)
											.getRol());
								}
								treeItem3.appendChild(treech4);
							}
							treechAux.appendChild(treeItem3);
							eliminarItem(listaRol, listaNivel3.get(k).getRol());
						}
						treeItemAux.appendChild(treechAux);
					}
					treech.appendChild(treeItemAux);
					eliminarItem(listaRol, listaAuxRol.get(j).getRol());

				}
				treeItem.appendChild(treech);
			}
			treeChildrenArbol.appendChild(treeItem);
			arbol.appendChild(treeChildrenArbol);
			// listaRol.remove(i);
			// i--;
		}

		// arbolMenu = new Tree();
	}

	// ---------------------------------------------------------------------------------------------------

	public void onClick$btnAgregarRol() {
		int pag = 0;
		// lbxRoles.getPaging().setActivePage(0);
		try {

			for (int i = 0; i < lbxRoles.getItemCount(); i++) {

				System.out.println(i);
				System.out.println(pag);

				// if (pag == 15) {
				// System.out.println("--------------");
				// System.out.println((i / pag));
				// lbxRoles.getPaging().setActivePage(i / pag);
				// System.out.println("--------------");
				// pag = 0;
				// }
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

					// rolGrupo = new RolGrupo();
					// rolGrupo.setGrupo(listaGrupos.get(lbxGrupos
					// .getSelectedIndex()));
					// rolGrupo.setRol(servicioRol.buscarPorCodigo(0));
					// rolGrupo.setId(new RolGrupoId(listaRol.get(i)
					// .getCodigoRol(), listaGrupos.get(
					// lbxGrupos.getSelectedIndex()).getCodigoGrupo()));
					// rolGrupo.setEstatus("A");
					//
					// listaRolesAsigAux.add(rolGrupo);

					listaRol.remove(i);

				}
				pag++;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		binder.loadComponent(lbxRoles);
		binder.loadComponent(lbxRolesAgregados);
		// lbxRoles.getPaging().setActivePage(0);
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
									RolGrupo aux = servicioRolGrupo.buscarRolExistente(auxRol,listaGrupos.get(i));
									System.out.println(aux);
									if (aux == null) {
										aux = new RolGrupo();
										aux.setGrupo(listaGrupos.get(i));
										aux.setRol(auxRol);
										aux.setId(new RolGrupoId(auxRol.getCodigoRol(), listaGrupos.get(i).getCodigoGrupo()));
										aux.setEstatus("A");
										System.out.println(auxRol.getCodigoRol());
										System.out.println(listaGrupos.get(i).getCodigoGrupo());
										servicioRolGrupo.actualizar(aux);
									} else {
										aux.setEstatus("A");
										servicioRolGrupo.actualizar(aux);
										System.out.println("despues");
									}

								}
								System.out.println("");
								
								for (int i = 0; i < listaRolesEliminados.size(); i++) {
									servicioRolGrupo
											.actualizar(listaRolesEliminados
													.get(i));
								}

								for (int i = 0; i < listaRolesAsignados.size(); i++) {
									servicioRolGrupo
											.actualizar(listaRolesAsignados
													.get(i));
								}

								// for (int i = 0; i < listaRolesAsigAux.size();
								// i++) {
								// servicioRolGrupo
								// .actualizar(listaRolesAsigAux
								// .get(i));
								// }
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
					"/Administracion/Vistas/frmGruposUsuarios.zul", null,
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
		// binder.loadAll();
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
