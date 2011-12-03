package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the parroquia database table.
 * 
 */
@Entity
public class Parroquia implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ParroquiaPK id;

	private String nombre;

	//bi-directional many-to-one association to Benefactor
	@OneToMany(mappedBy="parroquia")
	private Set<Benefactor> benefactors;

	//bi-directional many-to-one association to Cliente
	@OneToMany(mappedBy="parroquia")
	private Set<Cliente> clientes;

	//bi-directional many-to-one association to Divisa
	@OneToMany(mappedBy="parroquia")
	private Set<Divisa> divisas;

	//bi-directional many-to-one association to Empleado
	@OneToMany(mappedBy="parroquia")
	private Set<Empleado> empleados;

	//bi-directional many-to-one association to Familiar
	@OneToMany(mappedBy="parroquia")
	private Set<Familiar> familiars;

	//bi-directional many-to-one association to Institucion
	@OneToMany(mappedBy="parroquia")
	private Set<Institucion> institucions;

	//bi-directional many-to-one association to Jugador
	@OneToMany(mappedBy="parroquia")
	private Set<Jugador> jugadors;

	//bi-directional many-to-one association to Municipio
    @ManyToOne
	@JoinColumn(name="cod_municipio")
	private Municipio municipio;

	//bi-directional many-to-one association to Proveedor
	@OneToMany(mappedBy="parroquia")
	private Set<Proveedor> proveedors;

    public Parroquia() {
    }

	public ParroquiaPK getId() {
		return this.id;
	}

	public void setId(ParroquiaPK id) {
		this.id = id;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Benefactor> getBenefactors() {
		return this.benefactors;
	}

	public void setBenefactors(Set<Benefactor> benefactors) {
		this.benefactors = benefactors;
	}
	
	public Set<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(Set<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	public Set<Divisa> getDivisas() {
		return this.divisas;
	}

	public void setDivisas(Set<Divisa> divisas) {
		this.divisas = divisas;
	}
	
	public Set<Empleado> getEmpleados() {
		return this.empleados;
	}

	public void setEmpleados(Set<Empleado> empleados) {
		this.empleados = empleados;
	}
	
	public Set<Familiar> getFamiliars() {
		return this.familiars;
	}

	public void setFamiliars(Set<Familiar> familiars) {
		this.familiars = familiars;
	}
	
	public Set<Institucion> getInstitucions() {
		return this.institucions;
	}

	public void setInstitucions(Set<Institucion> institucions) {
		this.institucions = institucions;
	}
	
	public Set<Jugador> getJugadors() {
		return this.jugadors;
	}

	public void setJugadors(Set<Jugador> jugadors) {
		this.jugadors = jugadors;
	}
	
	public Municipio getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	
	public Set<Proveedor> getProveedors() {
		return this.proveedors;
	}

	public void setProveedors(Set<Proveedor> proveedors) {
		this.proveedors = proveedors;
	}
	
}