package modelo;

// Generated 31/12/2011 11:02:01 AM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Ascenso generated by hbm2java
 */
@Entity
@Table(name = "ascenso", schema = "public")
public class Ascenso implements java.io.Serializable {

	private int codigoAscenso;
	private Roster roster;
	private DatoBasico datoBasico;
	private Date fechaAscenso;
	private char estatus;
	private Set<DocumentoAscenso> documentoAscensos = new HashSet<DocumentoAscenso>(
			0);

	public Ascenso() {
	}

	public Ascenso(int codigoAscenso, Roster roster, DatoBasico datoBasico,
			Date fechaAscenso, char estatus) {
		this.codigoAscenso = codigoAscenso;
		this.roster = roster;
		this.datoBasico = datoBasico;
		this.fechaAscenso = fechaAscenso;
		this.estatus = estatus;
	}

	public Ascenso(int codigoAscenso, Roster roster, DatoBasico datoBasico,
			Date fechaAscenso, char estatus,
			Set<DocumentoAscenso> documentoAscensos) {
		this.codigoAscenso = codigoAscenso;
		this.roster = roster;
		this.datoBasico = datoBasico;
		this.fechaAscenso = fechaAscenso;
		this.estatus = estatus;
		this.documentoAscensos = documentoAscensos;
	}

	@Id
	@Column(name = "codigo_ascenso", unique = true, nullable = false)
	public int getCodigoAscenso() {
		return this.codigoAscenso;
	}

	public void setCodigoAscenso(int codigoAscenso) {
		this.codigoAscenso = codigoAscenso;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_roster", nullable = false)
	public Roster getRoster() {
		return this.roster;
	}

	public void setRoster(Roster roster) {
		this.roster = roster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_tipo_ascenso", nullable = false)
	public DatoBasico getDatoBasico() {
		return this.datoBasico;
	}

	public void setDatoBasico(DatoBasico datoBasico) {
		this.datoBasico = datoBasico;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_ascenso", nullable = false, length = 13)
	public Date getFechaAscenso() {
		return this.fechaAscenso;
	}

	public void setFechaAscenso(Date fechaAscenso) {
		this.fechaAscenso = fechaAscenso;
	}

	@Column(name = "estatus", nullable = false, length = 1)
	public char getEstatus() {
		return this.estatus;
	}

	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ascenso")
	public Set<DocumentoAscenso> getDocumentoAscensos() {
		return this.documentoAscensos;
	}

	public void setDocumentoAscensos(Set<DocumentoAscenso> documentoAscensos) {
		this.documentoAscensos = documentoAscensos;
	}

}
