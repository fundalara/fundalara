package modelo;

// Generated 14/02/2012 08:24:27 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CondicionCompetencia generated by hbm2java
 */
@Entity
@Table(name = "condicion_competencia", schema = "public")
public class CondicionCompetencia implements java.io.Serializable {

	private int codigoCondicionCompetencia;
	private DatoBasico datoBasico;
	private ClasificacionCompetencia clasificacionCompetencia;
	private char estatus;

	public CondicionCompetencia() {
	}

	public CondicionCompetencia(int codigoCondicionCompetencia,
			DatoBasico datoBasico,
			ClasificacionCompetencia clasificacionCompetencia, char estatus) {
		this.codigoCondicionCompetencia = codigoCondicionCompetencia;
		this.datoBasico = datoBasico;
		this.clasificacionCompetencia = clasificacionCompetencia;
		this.estatus = estatus;
	}

	@Id
	@Column(name = "codigo_condicion_competencia", unique = true, nullable = false)
	public int getCodigoCondicionCompetencia() {
		return this.codigoCondicionCompetencia;
	}

	public void setCodigoCondicionCompetencia(int codigoCondicionCompetencia) {
		this.codigoCondicionCompetencia = codigoCondicionCompetencia;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_condicion", nullable = false)
	public DatoBasico getDatoBasico() {
		return this.datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_clasificacion_competencia", nullable = false)
	public ClasificacionCompetencia getClasificacionCompetencia() {
		return this.clasificacionCompetencia;
	}

	public void setClasificacionCompetencia(
			ClasificacionCompetencia clasificacionCompetencia) {
		this.clasificacionCompetencia = clasificacionCompetencia;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

}
