package gob.pe.senamhi.sia.Service.Impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gob.pe.senamhi.sia.Beans.Ficha;
import gob.pe.senamhi.sia.Dao.FichaDao;
import gob.pe.senamhi.sia.Service.FichaService;

@Service
public class FichaServiceImpl implements FichaService{
	
	private static final Logger LOGGER = LogManager.getLogger(FichaServiceImpl.class);
	
	@Autowired
	private FichaDao fichaDao;

	@Override
	public Ficha ObtenerFicha(String id) throws Exception {
		try {
			Ficha ficha = fichaDao.findById(Integer.parseInt(id));
			if(ficha == null) {
				throw new Exception();
			}
			return ficha;
		} catch (DataException e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new Exception();
		}
		
	}

	@Override
	public String guardarFicha(Ficha request) throws Exception {
		String result = "GUARDO";
        try {
        	fichaDao.sp_ficha_informativa(
                    request.getDescripcion(),
                    request.getEnlace(),
                    Integer.parseInt(request.getCultivoId())
            );
        } catch (DataException e) {
        	LOGGER.error(e.getCause().getCause().getLocalizedMessage());
        	throw new Exception();
        }
        return result;
	}
	
}
