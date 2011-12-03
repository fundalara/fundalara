package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the institucion database table.
 * 
 */
@Entity
public class Institucion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_institucion")
	private String codigoInstitucion;

	private String direccion;

	private String estatus;

	private String nombre;

	//bi-directional many-to-one association to DatoAcademico
	@OneToMany(mappedBy="institucion")
	private Set<DatoAcademico> datoAcademicos;

	//bi-directional many-to-one association to DatoSocial
	@OneToMany(mappedBy="institucion")
	private Set<DatoSocial> datoSocials;

	//bi-directional many-to-one association to Parroquia
    @ManyToOne
	@JoinColumns({
		@JoinColumn(name="cod_municipio", referencedColumnName="cod_parroquia"),
		@JoinColumn(name="cod_parroquia", referencedColumnName="cod_municipio")
		})
	private Parroquia parroquia;

	//bi-directional many-to-one association to TipoInstitucion
    @ManyToOne
	@JoinColumn(name="codigo_tipo")
	private TipoInstitucion tipoInstitucion;

    public Institucion() {
    }

	public String getCodigoInstitucion() {
		return this.codigoInstitucion;
	}

	public void setCodigoInstitucion(String codigoInstitucion) {
		this.codigoInstitucion = codigoInstitucion;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEstatus() {
		return this.estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<DatoAcademico> getDatoAcademicos() {
		return this.datoAcademicos;
	}

	public void setDatoAcademicos(Set<DatoAcademico> datoAcademicos) {
		this.datoAcademicos = datoAcademicos;
	}
	
	public Set<DatoSocial> getDatoSocials() {
		return this.datoSocials;
	}

	public void setDatoSocials(Set<DatoSocial> datoSocials) {
		this.datoSocials = datoSocials;
	}
	
	public Parroquia getParroquia() {
		return this.parroquia;
	}

	public void setParroquia(Parroquia parroquia) {
		this.parroquia = parroquia;
	}
	
	public TipoInstitucion getTipoInstitucion() {
		return this.tipoInstitucion;
	}

	public void setTipoInstitucion(TipoInstitucion tipoInstitucion) {
		this.tipoInstitucion = tipoInstitucion;
	}
	
}