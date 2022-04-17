package gob.pe.senamhi.sia.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gob.pe.senamhi.sia.Beans.UsuarioRq;
import gob.pe.senamhi.sia.Beans.UsuarioRs;
import gob.pe.senamhi.sia.Service.UsuarioService;

@CrossOrigin(origins= {"*"})
@RestController
@RequestMapping(value="/login")
public class LoginController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping()
	public ResponseEntity<UsuarioRs> login(@RequestBody UsuarioRq request){
		try {
			UsuarioRs response = usuarioService.login(request);
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
