package controlador.competencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import modelo.Categoria;
import modelo.Competencia;
import modelo.DatoBasico;
import modelo.DesempennoIndividual;
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
import org.zkoss.zul.Row;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Rows;

import servicio.implementacion.ServicioCategoriaCompetencia;
import servicio.implementacion.ServicioDatoBasico;
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
			c.setLabel(lista.get(i).getIndicador().getAbreviatura());
			cols.appendChild(c);
		}
		// Creando filas
		Rows rows = grid.getRows();
		for (int i = 0; i < n; i++) {
			Row row = new Row();
			row.setHeight("27px");
			for (int j = 0; j < lista.size(); j++) {
				Spinner spinner = new Spinner(0);
				spinner.setCols(2);
				spinner.setConstraint("min 0");
				row.appendChild(spinner);
			}
			rows.appendChild(row);
		}

		grid.invalidate();
	}

	public void onClick$btnGuardar() {
		Rows rows = gridIndicadoresOfensivos.getRows();
		List<Row> filas = rows.getChildren();
		
	
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
