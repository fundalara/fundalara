package controlador.competencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import modelo.Categoria;
import modelo.Competencia;
import modelo.DatoBasico;
import modelo.DesempennoIndividual;
import modelo.DesempennoIndividualId;
import modelo.EquipoCompetencia;
import modelo.EquipoJuego;
import modelo.IndicadorCategoriaCompetencia;
import modelo.Juego;
import modelo.LineUp;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Rows;

import servicio.implementacion.ServicioCategoriaCompetencia;
import servicio.implementacion.ServicioDatoBasico;
import servicio.implementacion.ServicioDesempennoIndividual;
import servicio.implementacion.ServicioIndicadorCategoriaCompetencia;
import servicio.implementacion.ServicioJuego;
import servicio.implementacion.ServicioLineUp;

public class CntrlFrmResultadosIndividuales extends GenericForwardComposer {

	Component formulario;
	AnnotateDataBinder binder;
	EquipoCompetencia equipoCompetencia;
	Juego juego;
	ServicioLineUp servicioLineUp;
	ServicioJuego servicioJuego;
	ServicioIndicadorCategoriaCompetencia servicioIndicadorCategoriaCompetencia;
	ServicioDatoBasico servicioDatoBasico;
	ServicioDesempennoIndividual servicioDesempennoIndividual;
	List<LineUp> lineups;
	List<LineUp> lineupsReserva;
	List<IndicadorCategoriaCompetencia> indicadoresIndividualesOfensivos;
	List<IndicadorCategoriaCompetencia> indicadoresIndividualesDefensivos;
	List<IndicadorCategoriaCompetencia> indicadoresIndividualesPitcheo;
	Grid gridIndicadoresOfensivos;
	Grid gridIndicadoresOfensivosReserva;
	Grid gridIndicadoresDefensivos;
	Grid gridIndicadoresDefensivosReserva;
	Grid gridIndicadoresPitcheo;
	Grid gridIndicadoresPitcheoReserva;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("cntrl", this, true);
		formulario = comp;
		lineups = new ArrayList<LineUp>();
		lineupsReserva = new ArrayList<LineUp>();
	}

	public void onCreate$FrmResultadosIndividuales() {
		int codigo = (Integer) formulario.getVariable("juego", false);
		juego = servicioJuego.buscarJuego(codigo);
		equipoCompetencia = (EquipoCompetencia) formulario.getVariable(
				"equipo", false);
		lineups = servicioLineUp.listarDefinitivos(juego,
				equipoCompetencia.getEquipo());
		lineupsReserva = servicioLineUp.listarDefinitivosReserva(juego,
				equipoCompetencia.getEquipo());

		Competencia competencia = equipoCompetencia.getCompetencia();
		Categoria categoria = equipoCompetencia.getEquipo().getCategoria();

		// indicadores ofensivos
		DatoBasico modalidad = servicioDatoBasico.buscarPorString("OFENSIVO");
		indicadoresIndividualesOfensivos = servicioIndicadorCategoriaCompetencia
				.listarIndicadoresSencillosIndividuales(categoria, competencia,
						modalidad);
		cargarIndicadores(gridIndicadoresOfensivos,
				indicadoresIndividualesOfensivos, 9);
		cargarIndicadores(gridIndicadoresOfensivosReserva,
				indicadoresIndividualesOfensivos, lineupsReserva.size());

		// indicadores defensivos
		modalidad = servicioDatoBasico.buscarPorString("DEFENSIVO");
		indicadoresIndividualesDefensivos = servicioIndicadorCategoriaCompetencia
				.listarIndicadoresSencillosIndividuales(categoria, competencia,
						modalidad);
		cargarIndicadores(gridIndicadoresDefensivos,
				indicadoresIndividualesDefensivos, 9);
		cargarIndicadores(gridIndicadoresDefensivosReserva,
				indicadoresIndividualesDefensivos, lineupsReserva.size());
		binder.loadAll();

		// indicadores de pitcheo
		modalidad = servicioDatoBasico.buscarPorString("PITCHEO");
		indicadoresIndividualesPitcheo = servicioIndicadorCategoriaCompetencia
				.listarIndicadoresSencillosIndividuales(categoria, competencia,
						modalidad);
		cargarIndicadores(gridIndicadoresPitcheo,
				indicadoresIndividualesPitcheo, 9);
		cargarIndicadores(gridIndicadoresPitcheoReserva,
				indicadoresIndividualesPitcheo, lineupsReserva.size());
		binder.loadAll();

	}

	public void cargarIndicadores(Grid grid,
			List<IndicadorCategoriaCompetencia> lista, int n) {

		// Creando columnas
		Columns cols = grid.getColumns();
		for (int i = 0; i < lista.size(); i++) {
			Column c = new Column();
			c.setWidth("50px");
			c.setLabel(lista.get(i).getIndicador().getAbreviatura());
			cols.appendChild(c);
			grid.invalidate();

		}

		// Creando filas
		Rows rows = grid.getRows();
		for (int i = 0; i < n; i++) {
			Row row = new Row();
			row.setHeight("27px");
			LineUp lineUp = lineups.get(i);
			for (int j = 0; j < lista.size(); j++) {
				IndicadorCategoriaCompetencia icc = lista.get(j);
				DesempennoIndividual di = servicioDesempennoIndividual.obtenerDesempennoPorIndicador(icc, lineUp);
				Spinner spinner = new Spinner(0);
				spinner.setCols(2);
				spinner.setConstraint("min 0");
				if (di != null ){
					int v = (int) di.getValor();
					spinner.setValue(v);
				}
				row.appendChild(spinner);
				grid.invalidate();
			}
			rows.appendChild(row);
		}

	}

	public void guardar(Grid grid,List<IndicadorCategoriaCompetencia> indicadores) {

		int n = grid.getColumns().getChildren().size();
		Rows rows = grid.getRows();
		for (int i = 0; i < rows.getChildren().size(); i++) {
			Row row = (Row) rows.getChildren().get(i);
			LineUp lineUp = lineups.get(i);
			for (int j = 0; j < n; j++) {
				Spinner spnr = (Spinner) row.getChildren().get(j);
				IndicadorCategoriaCompetencia icc = indicadores.get(j);
				DesempennoIndividual di;
				di = servicioDesempennoIndividual.obtenerDesempennoPorIndicador(icc, lineUp);
				if (di == null){
					di = new DesempennoIndividual();
					DesempennoIndividualId id = new DesempennoIndividualId(icc.getCodigoIndicadorCategoriaCompetencia(),lineUp.getCodigoLineUp());		
					di.setId(id);
					di.setValor(spnr.getValue());
					di.setIndicadorCategoriaCompetencia(icc);
					di.setLineUp(lineUp);
				}else{
					di.setValor(spnr.getValue());
				}			
				servicioDesempennoIndividual.agregar(di);
			}
		}

	}

	public void onClick$btnGuardar() throws InterruptedException {
		guardar(gridIndicadoresOfensivos, indicadoresIndividualesOfensivos);
		guardar(gridIndicadoresOfensivosReserva,indicadoresIndividualesOfensivos);
		guardar(gridIndicadoresDefensivos,indicadoresIndividualesDefensivos);
		guardar(gridIndicadoresDefensivosReserva,indicadoresIndividualesOfensivos);
		guardar(gridIndicadoresPitcheo,indicadoresIndividualesPitcheo);
		guardar(gridIndicadoresPitcheoReserva,indicadoresIndividualesPitcheo);
		Messagebox.show("¡Datos almacenados exitosamente!", "Mensaje",Messagebox.OK, Messagebox.EXCLAMATION);

	}

	public List ConvertirConjuntoALista(Set conjunto) {
		List l = new ArrayList();
		for (Iterator i = conjunto.iterator(); i.hasNext();) {
			l.add(i.next());
		}
		return l;
	}

	public EquipoCompetencia getEquipoCompetencia() {
		return equipoCompetencia;
	}

	public void setEquipoCompetencia(EquipoCompetencia equipoCompetencia) {
		this.equipoCompetencia = equipoCompetencia;
	}

	public Juego getJuego() {
		return juego;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}

	public List<LineUp> getLineups() {
		return lineups;
	}

	public void setLineups(List<LineUp> lineups) {
		this.lineups = lineups;
	}

	public List<LineUp> getLineupsReserva() {
		return lineupsReserva;
	}

	public void setLineupsReserva(List<LineUp> lineupsReserva) {
		this.lineupsReserva = lineupsReserva;
	}

}
