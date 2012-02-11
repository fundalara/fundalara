package modelo;

// Generated 10/02/2012 11:18:51 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * IngresoInscripcion generated by hbm2java
 */
@Entity
@Table(name = "ingreso_inscripcion", schema = "public")
public class IngresoInscripcion implements java.io.Serializable {

	private IngresoInscripcionId id;
	private TipoIngreso tipoIngreso;
	private int cantidad;
	private int adelantos;
	private char estatus;

	public IngresoInscripcion() {
	}

	public IngresoInscripcion(IngresoInscripcionId id, TipoIngreso tipoIngreso,
			int cantidad, int adelantos, char estatus) {
		this.id = id;
		this.tipoIngreso = tipoIngreso;
		this.cantidad = cantidad;
		this.adelantos = adelantos;
		this.estatus = estatus;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codigoTipoIngreso", column = @Column(name = "codigo_tipo_ingreso", nullable = false)),
			@AttributeOverride(name = "codigoInscripcion", column = @Column(name = "codigo_inscripcion", nullable = false)) })
	public IngresoInscripcionId getId() {
		return this.id;
	}

	public void setId(IngresoInscripcionId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_tipo_ingreso", nullable = false, insertable = false, updatable = false)
	public TipoIngreso getTipoIngreso() {
		return this.tipoIngreso;
	}

	public void setTipoIngreso(TipoIngreso tipoIngreso) {
		this.tipoIngreso = tipoIngreso;
	}

	@Column(name = "cantidad", nullable = false)
	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Column(name = "adelantos", nullable = false)
	public int getAdelantos() {
		return this.adelantos;
	}

	public void setAdelantos(int adelantos) {
		this.adelantos = adelantos;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

}
