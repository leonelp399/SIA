package gob.pe.senamhi.sia.Service.Impl;

import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import gob.pe.senamhi.sia.Beans.UsuarioRq;
import gob.pe.senamhi.sia.Beans.UsuarioRs;
import gob.pe.senamhi.sia.Service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	private static final Logger LOGGER = LogManager.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public UsuarioRs login(UsuarioRq request) throws Exception {
		try {
			ResourceBundle rs = ResourceBundle.getBundle("config");
			request.setSistema(rs.getString("client.id"));
			ResponseEntity<UsuarioRs> response = restTemplate.postForEntity(rs.getString("login.service"), request, UsuarioRs.class);
			if(response.getStatusCodeValue() != 200) {
				LOGGER.error("error http en el servicio de login");
				throw new Exception();
			}
			return response.getBody();
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new Exception();
		}
	}

	
	
}
