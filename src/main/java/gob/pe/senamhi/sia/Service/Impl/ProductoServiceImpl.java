package gob.pe.senamhi.sia.Service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gob.pe.senamhi.sia.Beans.Dep;
import gob.pe.senamhi.sia.Beans.Historial;
import gob.pe.senamhi.sia.Beans.Leyenda;
import gob.pe.senamhi.sia.Beans.Mes;
import gob.pe.senamhi.sia.Beans.Nivel;
import gob.pe.senamhi.sia.Beans.NivelPrioridad;
import gob.pe.senamhi.sia.Beans.Prioridad;
import gob.pe.senamhi.sia.Beans.Producto;
import gob.pe.senamhi.sia.Beans.Prov;
import gob.pe.senamhi.sia.Beans.Valor;
import gob.pe.senamhi.sia.Dao.MesDao;
import gob.pe.senamhi.sia.Dao.NivelDao;
import gob.pe.senamhi.sia.Dao.ProductoDao;
import gob.pe.senamhi.sia.Dao.ValorDao;
import gob.pe.senamhi.sia.Service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService{
	
	private static final Logger LOGGER = LogManager.getLogger(ProductoServiceImpl.class);
	private static final String ESQUEMA_MONITOREO = "db04";
	private static final String ESQUEMA_RIESGO = "db11";
	private static final String ESQUEMA_PRONOSTICO = "db03";
	
	@Autowired
	private ProductoDao productoDao;
	
	@Autowired
	private MesDao mesDao;
	
	@Autowired
	private ValorDao valorDao;
	
	@Autowired
	private NivelDao nivelDao;

	@Override
	public Producto ObtenerProducto(String esquema, String tabla) throws Exception {
		try {
			Producto producto = productoDao.findById(esquema, tabla);
			if(producto == null) {
				throw new Exception();
			}
			return producto;
		} catch (DataException e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new Exception();
		}
	}

	@Override
	public String guardarFicha(Producto request) throws Exception {
		String result = "GUARDO";
        try {
        	productoDao.sp_ficha_informativa(
                    request.getEsquema(),
                    request.getTabla().replaceAll("_", "."),
                    request.getEnlace()
            );
        } catch (DataException e) {
        	LOGGER.error(e.getCause().getCause().getLocalizedMessage());
        	throw new Exception();
        }
        return result;
	}

	@Override
	public List<String> ObtenerAnios(String esquema,String tabla) throws Exception {
		try {
			ArrayList<String> response = new ArrayList<String>();
			List<Mes> list = new ArrayList<Mes>();
			
			if(esquema.equalsIgnoreCase(ESQUEMA_RIESGO)) {
				list = mesDao.findYearsRiesgo(esquema,tabla);
			}else if(esquema.equalsIgnoreCase(ESQUEMA_MONITOREO)) {
				list = mesDao.findYearsMonitoreo(esquema,tabla);
			}else if(esquema.equalsIgnoreCase(ESQUEMA_PRONOSTICO)) {
				list = mesDao.findYearsPronostico(esquema,tabla);
			}
			
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
	public List<String> ObtenerMeses(String esquema, String tabla, String anio) throws Exception {
		try {
			ArrayList<String> response = new ArrayList<String>();
			List<Mes> list = new ArrayList<Mes>();
			if(esquema.equalsIgnoreCase(ESQUEMA_RIESGO)) {
				list = mesDao.findMonthsRiesgo(esquema,tabla,Integer.parseInt(anio));
			}else if(esquema.equalsIgnoreCase(ESQUEMA_MONITOREO)) {
				list = mesDao.findMonthsMonitoreo(esquema,tabla,Integer.parseInt(anio));
			}else if(esquema.equalsIgnoreCase(ESQUEMA_PRONOSTICO)) {
				list = mesDao.findMonthsMonitoreo(esquema,tabla,Integer.parseInt(anio));
			}
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
	public Historial ObtenerHistorial(String esquema, String tabla, String anio, String mes) throws Exception {
		try {
			Historial response = new Historial();
			List<Valor> list = new ArrayList<Valor>();
			if(esquema.equalsIgnoreCase(ESQUEMA_RIESGO)) {
				list = valorDao.findValoresRiesgo(esquema, tabla, Integer.parseInt(anio), Integer.parseInt(mes));
			}else if(esquema.equalsIgnoreCase(ESQUEMA_MONITOREO)) {
				list = valorDao.findValoresMonitoreo(esquema, tabla, Integer.parseInt(anio), Integer.parseInt(mes));
			}
			if(list.size() == 0) {
				throw new Exception();
			}
			String img = null;
			ArrayList<Leyenda> ll = new ArrayList<Leyenda>();
			for(Valor v : list) {
				Leyenda l = new Leyenda();
				org.springframework.beans.BeanUtils.copyProperties(v, l);
				if(esquema.equalsIgnoreCase(ESQUEMA_RIESGO))
					l.setPerimetro(null);
				ll.add(l);
				img = v.getImagen();
			}
			response.setLeyenda(ll);
			response.setImagen(img);
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
	public Prioridad ObtenerUbigeo(String esquema, String tabla) throws Exception {
		try {
			Prioridad response = new Prioridad();
			List<Nivel> list = new ArrayList<Nivel>();
			if(esquema.equalsIgnoreCase(ESQUEMA_RIESGO)) {
				list = nivelDao.findUbigeoByRiesgo(esquema, tabla);
			}else if(esquema.equalsIgnoreCase(ESQUEMA_MONITOREO)) {
				list = nivelDao.findUbigeoByMonitoreo(esquema, tabla);
			}else if(esquema.equalsIgnoreCase(ESQUEMA_PRONOSTICO)) {
				list = nivelDao.findUbigeoByPronostico(esquema, tabla);
			}
			if(list.size() == 0) {
				throw new Exception();
			}
			String max="",fecha="";
			List<NivelPrioridad> lnp = new ArrayList<NivelPrioridad>();
			for(Nivel n : list) {
				max=n.getDescripcion();
				fecha=n.getFecha();
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
