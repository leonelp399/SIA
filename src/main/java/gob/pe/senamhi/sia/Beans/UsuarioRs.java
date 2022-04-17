package gob.pe.senamhi.sia.Beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties
public class UsuarioRs {
	
	private String token;
	private String id;
	private List<String> roles;
	
}
