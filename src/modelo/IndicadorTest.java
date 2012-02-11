package modelo;

// Generated 11/02/2012 01:49:19 AM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * IndicadorTest generated by hbm2java
 */
@Entity
@Table(name = "indicador_test", schema = "public")
public class IndicadorTest implements java.io.Serializable {

	private int codigoIndicadorTest;
	private TestEvaluativo testEvaluativo;
	private IndicadorActividadEscala indicadorActividadEscala;
	private char estatus;
	private Set<TestJugador> testJugadors = new HashSet<TestJugador>(0);

	public IndicadorTest() {
	}

	public IndicadorTest(int codigoIndicadorTest,
			TestEvaluativo testEvaluativo,
			IndicadorActividadEscala indicadorActividadEscala, char estatus) {
		this.codigoIndicadorTest = codigoIndicadorTest;
		this.testEvaluativo = testEvaluativo;
		this.indicadorActividadEscala = indicadorActividadEscala;
		this.estatus = estatus;
	}

	public IndicadorTest(int codigoIndicadorTest,
			TestEvaluativo testEvaluativo,
			IndicadorActividadEscala indicadorActividadEscala, char estatus,
			Set<TestJugador> testJugadors) {
		this.codigoIndicadorTest = codigoIndicadorTest;
		this.testEvaluativo = testEvaluativo;
		this.indicadorActividadEscala = indicadorActividadEscala;
		this.estatus = estatus;
		this.testJugadors = testJugadors;
	}

	@Id
	@Column(name = "codigo_indicador_test", unique = true, nullable = false)
	public int getCodigoIndicadorTest() {
		return this.codigoIndicadorTest;
	}

	public void setCodigoIndicadorTest(int codigoIndicadorTest) {
		this.codigoIndicadorTest = codigoIndicadorTest;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_test", nullable = false)
	public TestEvaluativo getTestEvaluativo() {
		return this.testEvaluativo;
	}

	public void setTestEvaluativo(TestEvaluativo testEvaluativo) {
		this.testEvaluativo = testEvaluativo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_indicador_actividad_escala", nullable = false)
	public IndicadorActividadEscala getIndicadorActividadEscala() {
		return this.indicadorActividadEscala;
	}

	public void setIndicadorActividadEscala(
			IndicadorActividadEscala indicadorActividadEscala) {
		this.indicadorActividadEscala = indicadorActividadEscala;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "indicadorTest")
	public Set<TestJugador> getTestJugadors() {
		return this.testJugadors;
	}

	public void setTestJugadors(Set<TestJugador> testJugadors) {
		this.testJugadors = testJugadors;
	}

}
