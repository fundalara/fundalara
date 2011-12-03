package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the afeccion_jugador database table.
 * 
 */
@Entity
@Table(name="afeccion_jugador")
public class AfeccionJugador implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AfeccionJugadorPK id;

	@Column(name="detalle_afeccion")
	private String detalleAfeccion;

	private byte[] documento1;

	private byte[] documento2;

	private Integer duracion;

	private String estatus;

    @Temporal( TemporalType.DATE)
	@Column(name="fecha_reincorporacion")
	private Date fechaReincorporacion;

    @Temporal( TemporalType.DATE)
	@Column(name="fecha_revision")
	private Date fechaRevision;

	//bi-directional many-to-one association to DatoMedico
    @ManyToOne
	@JoinColumn(name="codigo_registro")
	private DatoMedico datoMedico;

	//bi-directional many-to-one association to TipoAfeccion
    @ManyToOne
	@JoinColumn(name="codigo_afeccion")
	private TipoAfeccion tipoAfeccion;

    public AfeccionJugador() {
    }

	public AfeccionJugadorPK getId() {
		return this.id;
	}

	public void setId(AfeccionJugadorPK id) {
		this.id = id;
	}
	
	public String getDetalleAfeccion() {
		return this.detalleAfeccion;
	}

	public void setDetalleAfeccion(String detalleAfeccion) {
		this.detalleAfeccion = detalleAfeccion;
	}

	public byte[] getDocumento1() {
		return this.documento1;
	}

	public void setDocumento1(byte[] documento1) {
		this.documento1 = documento1;
	}

	public byte[] getDocumento2() {
		return this.documento2;
	}

	public void setDocumento2(byte[] documento2) {
		this.documento2 = documento2;
	}

	public Integer getDuracion() {
		return this.duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Date getFechaReincorporacion() {
		return this.fechaReincorporacion;
	}

	public void setFechaReincorporacion(Date fechaReincorporacion) {
		this.fechaReincorporacion = fechaReincorporacion;
	}

	public Date getFechaRevision() {
		return this.fechaRevision;
	}

	public void setFechaRevision(Date fechaRevision) {
		this.fechaRevision = fechaRevision;
	}

	public DatoMedico getDatoMedico() {
		return this.datoMedico;
	}

	public void setDatoMedico(DatoMedico datoMedico) {
		this.datoMedico = datoMedico;
	}
	
	public TipoAfeccion getTipoAfeccion() {
		return this.tipoAfeccion;
	}

	public void setTipoAfeccion(TipoAfeccion tipoAfeccion) {
		this.tipoAfeccion = tipoAfeccion;
	}
	
}