package seguridad;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.AnnotateDataBinderInit;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import comun.MensajeMostrar;

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

public class CntrlGestionarGruposUsuarios extends GenericForwardComposer {
	Window frmGruposUsuarios;
	Grupo grupo;
	Usuario usuario, usuarioAsociado;
	RolGrupo rolGrupo;
	GrupoUsuario grupoUsuario;

	ServicioGrupo servicioGrupo;
	ServicioGrupoUsuario servicioGrupoUsuario;
	ServicioUsuario servicioUsuario;

	List<Usuario> listaUsuarios = new ArrayList<Usuario>();
	List<GrupoUsuario> listaUsuariosAsociados = new ArrayList<GrupoUsuario>();
	List<GrupoUsuario> listaUsuariosEliminados = new ArrayList<GrupoUsuario>();

	Textbox txtGrupo;
	Listbox lbxUsuarios, lbxUsuariosAgregados;
	AnnotateDataBinder binderHijo;
	Component formulario;

	// ---------------------------------------------------------------------------------------------------
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		listaUsuarios = servicioUsuario.listarActivos();
		formulario = comp;
		grupo = (Grupo) arg.get("grupo");
		listaUsuariosAsociados = servicioGrupoUsuario
				.buscarUsuarioPorGrupo(grupo);
		listaUsuarios = servicioUsuario.buscarUsuariosSinAsignar(grupo);
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnAgregarUsuario() {
		if (lbxUsuarios.getSelectedCount() != 0) {
			grupoUsuario = new GrupoUsuario();
			grupoUsuario.setGrupo(grupo);
			grupoUsuario.setUsuario(listaUsuarios.get(lbxUsuarios
					.getSelectedIndex()));
			grupoUsuario.setId(new GrupoUsuarioId(listaUsuarios.get(
					lbxUsuarios.getSelectedIndex()).getCedulaRif(), grupo
					.getCodigoGrupo()));
			grupoUsuario.setEstatus('A');
			listaUsuariosAsociados.add(grupoUsuario);
			listaUsuarios.remove(lbxUsuarios.getSelectedIndex());
			binderHijo.loadAll();
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnQuitarUsuario() {
		if (lbxUsuariosAgregados.getSelectedCount() != 0) {
			listaUsuarios.add(listaUsuariosAsociados.get(
					lbxUsuariosAgregados.getSelectedIndex()).getUsuario());
			grupoUsuario = listaUsuariosAsociados.get(lbxUsuariosAgregados
					.getSelectedIndex());
			grupoUsuario.setEstatus('E');
			listaUsuariosEliminados.add(grupoUsuario);
			listaUsuariosAsociados.remove(lbxUsuariosAgregados
					.getSelectedIndex());
			binderHijo.loadAll();
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void onClick$btnRegistrar() {
		for (int i = 0; i < listaUsuariosEliminados.size(); i++) {
			servicioGrupoUsuario.actualizar(listaUsuariosEliminados.get(i));
		}

		for (int i = 0; i < listaUsuariosAsociados.size(); i++) {
			servicioGrupoUsuario.actualizar(listaUsuariosAsociados.get(i));
		}

		listaUsuarios = servicioUsuario.buscarUsuariosSinAsignar(grupo);
		listaUsuariosAsociados = servicioGrupoUsuario
				.buscarUsuarioPorGrupo(grupo);
		binderHijo.loadAll();
		frmGruposUsuarios.detach();
		try {
			Messagebox.show(MensajeMostrar.USUARIOS_ASIGNADOS,
					MensajeMostrar.TITULO + "Información", Messagebox.OK,
					Messagebox.INFORMATION);
		} catch (Exception e) {
			// -----------
		}
	}

	// ---------------------------------------------------------------------------------------------------
	public void reiniciarListas() {
		listaUsuarios = new ArrayList<Usuario>();
		listaUsuariosAsociados = new ArrayList<GrupoUsuario>();
		listaUsuariosEliminados = new ArrayList<GrupoUsuario>();
		binderHijo.loadAll();
	}

	// ---------------------------------------------------------------------------------------------------
	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public RolGrupo getRolGrupo() {
		return rolGrupo;
	}

	public void setRolGrupo(RolGrupo rolGrupo) {
		this.rolGrupo = rolGrupo;
	}

	public GrupoUsuario getGrupoUsuario() {
		return grupoUsuario;
	}

	public void setGrupoUsuario(GrupoUsuario grupoUsuario) {
		this.grupoUsuario = grupoUsuario;
	}

	public Usuario getUsuarioAsociado() {
		return usuarioAsociado;
	}

	public void setUsuarioAsociado(Usuario usuarioAsociado) {
		this.usuarioAsociado = usuarioAsociado;
	}

	public List<GrupoUsuario> getListaUsuariosAsociados() {
		return listaUsuariosAsociados;
	}

	public void setListaUsuariosAsociados(
			List<GrupoUsuario> listaUsuariosAsociados) {
		this.listaUsuariosAsociados = listaUsuariosAsociados;
	}

	// ---------------------------------------------------------------------------------------------------
}
