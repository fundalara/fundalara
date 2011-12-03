package modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dato_social database table.
 * 
 */
@Entity
@Table(name="dato_social")
public class DatoSocial implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DatoSocialPK id;

	private String estatus;

	@Column(name="fecha_inicio")
	private String fechaInicio;

	@Column(name="horas_dedicadas")
	private Integer horasDedicadas;

	//bi-directional many-to-one association to Institucion
    @ManyToOne
	@JoinColumn(name="codigo_institucion")
	private Institucion institucion;

	//bi-directional many-to-one association to Jugador
    @ManyToOne
	@JoinColumn(name="cedula_jugador")
	private Jugador jugador;

	//bi-directional many-to-one association to TipoActividadSocial
    @ManyToOne
	@JoinColumn(name="codigo_actividad_social")
	private TipoActividadSocial tipoActividadSocial;

    public DatoSocial() {
    }

	public DatoSocialPK getId() {
		return this.id;
	}

	public void setId(DatoSocialPK id) {
		this.id = id;
	}
	
	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Integer getHorasDedicadas() {
		return this.horasDedicadas;
	}

	public void setHorasDedicadas(Integer horasDedicadas) {
		this.horasDedicadas = horasDedicadas;
	}

	public Institucion getInstitucion() {
		return this.institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}
	
	public Jugador getJugador() {
		return this.jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	
	public TipoActividadSocial getTipoActividadSocial() {
		return this.tipoActividadSocial;
	}

	public void setTipoActividadSocial(TipoActividadSocial tipoActividadSocial) {
		this.tipoActividadSocial = tipoActividadSocial;
	}
	
}