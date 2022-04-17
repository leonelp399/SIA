package gob.pe.senamhi.sia.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gob.pe.senamhi.sia.Beans.Ficha;
import gob.pe.senamhi.sia.Service.FichaService;

@CrossOrigin(origins= {"*"})
@RestController
@RequestMapping(value="/cultivo/ficha")
public class FichaInformativaController {
	
	@Autowired
	private FichaService fichaService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Ficha> obtenerFicha(@PathVariable String id){
		try {
			Ficha response = fichaService.ObtenerFicha(id);
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping()
	public ResponseEntity<String> GuardarFicha(@RequestBody Ficha request){
		try {
			String response = fichaService.guardarFicha(request);
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
