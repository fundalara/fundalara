package modelo;

// Generated 13/02/2012 08:44:08 PM by Hibernate Tools 3.4.0.CR1

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
 * DesempennoIndividual generated by hbm2java
 */
@Entity
@Table(name = "desempenno_individual", schema = "public")
public class DesempennoIndividual implements java.io.Serializable {

	private DesempennoIndividualId id;
	private IndicadorCategoriaCompetencia indicadorCategoriaCompetencia;
	private LineUp lineUp;
	private float valor;

	public DesempennoIndividual() {
	}

	public DesempennoIndividual(DesempennoIndividualId id,
			IndicadorCategoriaCompetencia indicadorCategoriaCompetencia,
			LineUp lineUp, float valor) {
		this.id = id;
		this.indicadorCategoriaCompetencia = indicadorCategoriaCompetencia;
		this.lineUp = lineUp;
		this.valor = valor;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codigoIndicadorCategoriaCompetencia", column = @Column(name = "codigo_indicador_categoria_competencia", nullable = false)),
			@AttributeOverride(name = "codigoLineUp", column = @Column(name = "codigo_line_up", nullable = false)) })
	public DesempennoIndividualId getId() {
		return this.id;
	}

	public void setId(DesempennoIndividualId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_indicador_categoria_competencia", nullable = false, insertable = false, updatable = false)
	public IndicadorCategoriaCompetencia getIndicadorCategoriaCompetencia() {
		return this.indicadorCategoriaCompetencia;
	}

	public void setIndicadorCategoriaCompetencia(
			IndicadorCategoriaCompetencia indicadorCategoriaCompetencia) {
		this.indicadorCategoriaCompetencia = indicadorCategoriaCompetencia;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_line_up", nullable = false, insertable = false, updatable = false)
	public LineUp getLineUp() {
		return this.lineUp;
	}

	public void setLineUp(LineUp lineUp) {
		this.lineUp = lineUp;
	}

	@Column(name = "valor", nullable = false, precision = 8, scale = 8)
	public float getValor() {
		return this.valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

}
