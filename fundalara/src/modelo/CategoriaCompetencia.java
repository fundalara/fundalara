package modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;


/**
 * The persistent class for the categoria_competencia database table.
 * 
 */
@Entity
@Table(name="categoria_competencia")
public class CategoriaCompetencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_competencia")
	private String codigoCompetencia;

	@Column(name="duracion_hora")
	private Time duracionHora;

	@Column(name="duracion_inning")
	private Integer duracionInning;

	//bi-directional many-to-one association to Categoria
    @ManyToOne
	@JoinColumn(name="codigo_categoria")
	private Categoria categoria;

	//bi-directional one-to-one association to Competencia
	@OneToOne
	@JoinColumn(name="codigo_competencia")
	private Competencia competencia;

    public CategoriaCompetencia() {
    }

	public String getCodigoCompetencia() {
		return this.codigoCompetencia;
	}

	public void setCodigoCompetencia(String codigoCompetencia) {
		this.codigoCompetencia = codigoCompetencia;
	}

	public Time getDuracionHora() {
		return this.duracionHora;
	}

	public void setDuracionHora(Time duracionHora) {
		this.duracionHora = duracionHora;
	}

	public Integer getDuracionInning() {
		return this.duracionInning;
	}

	public void setDuracionInning(Integer duracionInning) {
		this.duracionInning = duracionInning;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public Competencia getCompetencia() {
		return this.competencia;
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}
	
}