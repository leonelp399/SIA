package gob.pe.senamhi.sia.Service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import gob.pe.senamhi.sia.Beans.Dep;
import gob.pe.senamhi.sia.Beans.Historial;
import gob.pe.senamhi.sia.Beans.Leyenda;
import gob.pe.senamhi.sia.Beans.Mes;
import gob.pe.senamhi.sia.Beans.Nivel;
import gob.pe.senamhi.sia.Beans.NivelPrioridad;
import gob.pe.senamhi.sia.Beans.Prioridad;
import gob.pe.senamhi.sia.Beans.Prov;
import gob.pe.senamhi.sia.Beans.Valor;
import gob.pe.senamhi.sia.Dao.MesDao;
import gob.pe.senamhi.sia.Dao.NivelDao;
import gob.pe.senamhi.sia.Dao.ValorDao;
import gob.pe.senamhi.sia.Service.CultivoHistorialService;

@Service
public class CultivoHistorialServiceImpl implements CultivoHistorialService{
	
	private static final Logger LOGGER = LogManager.getLogger(CultivoHistorialServiceImpl.class);
	
	@Autowired
	private MesDao mesDao;
	
	@Autowired
	private ValorDao valorDao;
	
	@Autowired
	private NivelDao nivelDao; 
	
	@Override
	public List<String> ObtenerAnios(String cultivo) throws Exception {
		try {
			ArrayList<String> response = new ArrayList<String>();
			List<Mes> list = mesDao.findYears(Integer.parseInt(cultivo));
			if(list.size() == 0) {
				throw new Exception();
			}
			for(Mes m : list) {
				response.add(m.getAnio());
			}
			return response;
		} catch (DataException e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new Exception();
		}
	}

	@Override
	public List<String> ObtenerMeses(String cultivo,String anio) throws Exception {
		try {
			ArrayList<String> response = new ArrayList<String>();
			List<Mes> list = mesDao.findMonths(Integer.parseInt(cultivo),Integer.parseInt(anio));
			if(list.size() == 0) {
				throw new Exception();
			}
			for(Mes m : list) {
				response.add(m.getMes());
			}
			return response;
		} catch (DataException e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new Exception();
		}
	}

	@Override
	public Historial ObtenerHistorial(String cultivo, String mes, String anio) throws Exception {
		try {
			Historial response = new Historial();
			List<Valor> list = valorDao.findValores(Integer.parseInt(cultivo), Integer.parseInt(mes), Integer.parseInt(anio));
			if(list.size() == 0) {
				throw new Exception();
			}
			String img = null, cult = null;
			ArrayList<Leyenda> ll = new ArrayList<Leyenda>();
			for(Valor v : list) {
				Leyenda l = new Leyenda();
				org.springframework.beans.BeanUtils.copyProperties(v, l);
				ll.add(l);
				img = v.getImagen();
				cult = v.getCultivo();
			}
			response.setLeyenda(ll);
			response.setImagen(img);
			response.setCultivo(cult);
			return response;
		} catch (DataException e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new Exception();
		} catch (Exception e) {
			LOGGER.error("erro 1 " + e.getMessage());
			throw new Exception();
		}
	}

	@Override
	public Prioridad ObtenerPrioridad(String cultivo) throws Exception {
		try {
			Prioridad response = new Prioridad();
			List<Nivel> list = nivelDao.findPrioridadByCultivo(Integer.parseInt(cultivo));
			if(list.size() == 0) {
				throw new Exception();
			}
			String max="",fecha="";
			List<NivelPrioridad> lnp = new ArrayList<NivelPrioridad>();
			for(Nivel n : list) {
				if(n.getPrioridad().equals("1")) {
					max=n.getDescripcion();
					fecha=n.getFecha();
				}
				NivelPrioridad np = new NivelPrioridad();
				np.setLeyenda(n.getDescripcion());
				List<Dep> ld = new ArrayList<Dep>();
				for(String n1 : n.getDepProv().split("#")) {
					String[]b = n1.split(",");
					OptionalInt index1 = IntStream.range(0, ld.size())
						     .filter(i -> b[0].equalsIgnoreCase(ld.get(i).getNomdep()))
						     .findFirst();
					if(!index1.isPresent()) {
						Dep d = new Dep();
						d.setNomdep(b[0]);
						List<Prov> lp = new ArrayList<Prov>();
						Prov p = new Prov();
						p.setNomprov(b[1]);
						lp.add(p);
						d.setProv(lp);
						ld.add(d);
					}else {
						List<Prov> lp = ld.get(index1.getAsInt()).getProv();
						Predicate<Prov> p1 = s -> s.getNomprov().equalsIgnoreCase(b[1]);
						if(!lp.stream().anyMatch(p1)) {
							Prov p = new Prov();
							p.setNomprov(b[1]);
							lp.add(p);
						}
					}
				}
				np.setDep(ld);
				lnp.add(np);
			}
			response.setLeyendas(lnp);
			response.setMaxLeyenda(max);
			response.setFecha(fecha);
			return response;
		} catch (DataException e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new Exception();
		} catch (Exception e) {
			LOGGER.error("erro 1 " + e.getMessage());
			throw new Exception();
		}
	}
	
}
