package modelo;

// Generated 31/12/2011 11:02:01 AM by Hibernate Tools 3.4.0.CR1

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
 * TestJugador generated by hbm2java
 */
@Entity
@Table(name = "test_jugador", schema = "public")
public class TestJugador implements java.io.Serializable {

	private TestJugadorId id;
	private Roster roster;
	private IndicadorTest indicadorTest;
	private String puntuacion;
	private char estatus;

	public TestJugador() {
	}

	public TestJugador(TestJugadorId id, Roster roster,
			IndicadorTest indicadorTest, String puntuacion, char estatus) {
		this.id = id;
		this.roster = roster;
		this.indicadorTest = indicadorTest;
		this.puntuacion = puntuacion;
		this.estatus = estatus;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "codigoIndicadorTest", column = @Column(name = "codigo_indicador_test", nullable = false)),
			@AttributeOverride(name = "codigoRoster", column = @Column(name = "codigo_roster", nullable = false)) })
	public TestJugadorId getId() {
		return this.id;
	}

	public void setId(TestJugadorId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_roster", nullable = false, insertable = false, updatable = false)
	public Roster getRoster() {
		return this.roster;
	}

	public void setRoster(Roster roster) {
		this.roster = roster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_indicador_test", nullable = false, insertable = false, updatable = false)
	public IndicadorTest getIndicadorTest() {
		return this.indicadorTest;
	}

	public void setIndicadorTest(IndicadorTest indicadorTest) {
		this.indicadorTest = indicadorTest;
	}

	@Column(name = "puntuacion", nullable = false)
	public String getPuntuacion() {
		return this.puntuacion;
	}

	public void setPuntuacion(String puntuacion) {
		this.puntuacion = puntuacion;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

}
