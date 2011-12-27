package controlador.jugador.bean;


import modelo.DatoBasico;

/**
 * Clase bean para representar al Jugador
 * @author Robert A
 * @author German L
 * @version 0.1 22/12/2011
 *
 */
public class Jugador extends Persona {

	private String secuencia;
	
	private int numero;
	private TipoSangre tipoSangre;
	private double peso;
	private double altura;
	private DatoBasico brazoLanzar;
	private DatoBasico posicionBateo;
	private DatoBasico tallaCalzado;
	private DatoBasico tallaPantalon;
	private DatoBasico tallaCamisa;
	private DatoBasico paisNac;
	private DatoBasico parroquiaNac;
	
	
	public Jugador() {
		super();
		tipoSangre = new TipoSangre();
		brazoLanzar = new DatoBasico();
		posicionBateo = new DatoBasico();
	}

	public String getSecuencia() {
		return secuencia;
	}

	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}

	

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public TipoSangre getTipoSangre() {
		return tipoSangre;
	}

	public void setTipoSangre(TipoSangre tipoSangre) {
		this.tipoSangre = tipoSangre;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public DatoBasico getBrazoLanzar() {
		return brazoLanzar;
	}

	public void setBrazoLanzar(DatoBasico brazoLanzar) {
		this.brazoLanzar = brazoLanzar;
	}

	public DatoBasico getPosicionBateo() {
		return posicionBateo;
	}

	public void setPosicionBateo(DatoBasico posicionBateo) {
		this.posicionBateo = posicionBateo;
	}

	public DatoBasico getPaisNac() {
		return paisNac;
	}

	public void setPaisNac(DatoBasico paisNac) {
		this.paisNac = paisNac;
	}

	public DatoBasico getParroquiaNac() {
		return parroquiaNac;
	}

	public void setParroquiaNac(DatoBasico parroquiaNac) {
		this.parroquiaNac = parroquiaNac;
	}



	public DatoBasico getTallaCalzado() {
		return tallaCalzado;
	}

	public void setTallaCalzado(DatoBasico tallaCalzado) {
		this.tallaCalzado = tallaCalzado;
	}

	public DatoBasico getTallaPantalon() {
		return tallaPantalon;
	}

	public void setTallaPantalon(DatoBasico tallaPantalon) {
		this.tallaPantalon = tallaPantalon;
	}

	public DatoBasico getTallaCamisa() {
		return tallaCamisa;
	}

	public void setTallaCamisa(DatoBasico tallaCamisa) {
		this.tallaCamisa = tallaCamisa;
	}

	public String getCedulaCompleta() {
		return getNacionalidad().toUpperCase() + "-" + getCedula()
				+ (secuencia == null ? "" : "-" + secuencia);
	}

}
