package gob.pe.senamhi.sia.Service.Impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import gob.pe.senamhi.sia.Beans.DashBoard;
import gob.pe.senamhi.sia.Beans.DashBoardResponse;
import gob.pe.senamhi.sia.Beans.Data;
import gob.pe.senamhi.sia.Beans.Nivel;
import gob.pe.senamhi.sia.Dao.DashBoardDao;
import gob.pe.senamhi.sia.Dao.NivelDao;
import gob.pe.senamhi.sia.Service.DashBoardService;

@Service
public class DashBoardServiceImpl implements DashBoardService{
	
	private static final Logger LOGGER = LogManager.getLogger(DashBoardServiceImpl.class);
	
	@Autowired
	private DashBoardDao dashBoardDao;
	
	@Autowired
	private NivelDao nivelDao;
	
	@Override
	public DashBoardResponse obtenerDashBoardByDepRiesgo(String esquema, String tabla, String anio, String dep)
			throws Exception {
		try {
			List<Nivel> ln = nivelDao.findLeyendaByProducto(esquema, tabla);
			List<DashBoard> lb = dashBoardDao.findRiesgoLeyendaByDep(esquema, tabla, Integer.parseInt(anio), dep);
			if(ln.size()==0 || lb.size()==0) {
				LOGGER.error("no hay leyendas para este producto");
				throw new Exception();
			}
			Integer[] valores = {0,0,0,0,0,0,0,0,0,0,0,0};
			return generatorData(ln, lb, valores);
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new Exception();	
		}
	}

	@Override
	public DashBoardResponse obtenerDashBoardByDepProvRiesgo(String esquema, String tabla, String anio, String dep,
			String prov) throws Exception {
		try {
			List<Nivel> ln = nivelDao.findLeyendaByProducto(esquema, tabla);
			List<DashBoard> lb = dashBoardDao.findRiesgoLeyendaByDepProv(esquema, tabla, Integer.parseInt(anio), dep, prov);
			if(ln.size()==0 || lb.size()==0) {
				LOGGER.error("no hay leyendas para este producto");
				throw new Exception();
			}
			Integer[] valores = {0,0,0,0,0,0,0,0,0,0,0,0};
			return generatorData(ln, lb, valores);
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new Exception();	
		}
	}

	@Override
	public DashBoardResponse obtenerDashBoardByDepMonitoreo(String esquema, String tabla, String anio, String dep)
			throws Exception {
		try {
			List<Nivel> ln = nivelDao.findLeyendaByProducto(esquema, tabla);
			List<DashBoard> lb = dashBoardDao.findMonitoreoLeyendaByDep(esquema, tabla, Integer.parseInt(anio), dep);
			if(ln.size()==0 || lb.size()==0) {
				LOGGER.error("no hay leyendas para este producto");
				throw new Exception();
			}
			Integer[] valores = {0,0,0,0,0,0,0,0,0,0,0,0,0};
			return generatorData(ln, lb, valores);
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new Exception();	
		}
	}
	
	
	public static DashBoardResponse generatorData(List<Nivel> ln, List<DashBoard> lb, Integer[] valores) throws Exception {
		try {
			DashBoardResponse  response = new DashBoardResponse();
			ArrayList<Data> ld = new ArrayList<>();
			for(Nivel n : ln) {
				Data data = new Data();
				data.setName(n.getDescripcion());
				data.setData(Arrays.asList(valores));
				OptionalInt index1 = IntStream.range(0, lb.size())
					     .filter(i -> n.getDescripcion().equalsIgnoreCase(lb.get(i).getLeyenda()))
					     .findFirst();
				if(index1.isPresent()) {
					DashBoard db = lb.get(index1.getAsInt());
					ArrayList<Integer> valLey = new ArrayList<Integer>();
					if(valores.length == 11)
						valLey.add(0);
						
					valLey.add(Integer.parseInt(db.getEnero()));
					valLey.add(Integer.parseInt(db.getFebrero()));
					valLey.add(Integer.parseInt(db.getMarzo()));
					valLey.add(Integer.parseInt(db.getAbril()));
					valLey.add(Integer.parseInt(db.getMayo()));
					valLey.add(Integer.parseInt(db.getJunio()));
					valLey.add(Integer.parseInt(db.getJulio()));
					valLey.add(Integer.parseInt(db.getAgosto()));
					valLey.add(Integer.parseInt(db.getSeptiembre()));
					valLey.add(Integer.parseInt(db.getOctubre()));
					valLey.add(Integer.parseInt(db.getNoviembre()));
					valLey.add(Integer.parseInt(db.getDiciembre()));
					 data.setData(valLey);
				}
				ld.add(data);
			}
			response.setSeries(ld);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getLocalizedMessage());
			throw new Exception();
		}
	}
	
}
