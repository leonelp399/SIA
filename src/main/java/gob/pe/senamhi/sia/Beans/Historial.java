package gob.pe.senamhi.sia.Beans;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_NULL)
public class Historial implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String imagen;
	
	private List<Leyenda> leyenda;
	
}
