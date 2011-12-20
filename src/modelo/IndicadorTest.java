package modelo;

// Generated 19-dic-2011 14:08:48 by Hibernate Tools 3.4.0.CR1

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
@Table(name = "indicador_test")
public class IndicadorTest implements java.io.Serializable {

	private int codigoIndicadorTest;
	private EscalaMedicion escalaMedicion;
	private TestEvaluativo testEvaluativo;
	private DatoBasico datoBasico;
	private char estatus;
	private Set<TestJugador> testJugadors = new HashSet<TestJugador>(0);

	public IndicadorTest() {
	}

	public IndicadorTest(int codigoIndicadorTest,
			EscalaMedicion escalaMedicion, TestEvaluativo testEvaluativo,
			DatoBasico datoBasico, char estatus) {
		this.codigoIndicadorTest = codigoIndicadorTest;
		this.escalaMedicion = escalaMedicion;
		this.testEvaluativo = testEvaluativo;
		this.datoBasico = datoBasico;
		this.estatus = estatus;
	}

	public IndicadorTest(int codigoIndicadorTest,
			EscalaMedicion escalaMedicion, TestEvaluativo testEvaluativo,
			DatoBasico datoBasico, char estatus, Set<TestJugador> testJugadors) {
		this.codigoIndicadorTest = codigoIndicadorTest;
		this.escalaMedicion = escalaMedicion;
		this.testEvaluativo = testEvaluativo;
		this.datoBasico = datoBasico;
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
	@JoinColumn(name = "codigo_escala", nullable = false)
	public EscalaMedicion getEscalaMedicion() {
		return this.escalaMedicion;
	}

	public void setEscalaMedicion(EscalaMedicion escalaMedicion) {
		this.escalaMedicion = escalaMedicion;
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
	@JoinColumn(name = "indicador", nullable = false)
	public DatoBasico getDatoBasico() {
		return this.datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
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
