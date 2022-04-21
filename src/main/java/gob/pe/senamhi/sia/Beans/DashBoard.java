package gob.pe.senamhi.sia.Beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Entity
@Table(name="LEYENDADASH")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_NULL)
public class DashBoard implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "LEYENDA")
	private String leyenda;
	
	@Column(name = "PRIORIDAD")
	private String prioridad;
	
	@Column(name = "1")
	private String enero;
	
	@Column(name = "2")
	private String febrero;
	
	@Column(name = "3")
	private String marzo;
	
	@Column(name = "4")
	private String abril;
	
	@Column(name = "5")
	private String mayo;
	
	@Column(name = "6")
	private String junio;
	
	@Column(name = "7")
	private String julio;
	
	@Column(name = "8")
	private String agosto;
	
	@Column(name = "9")
	private String septiembre;

	@Column(name = "10")
	private String octubre;
	
	@Column(name = "11")
	private String noviembre;
	
	@Column(name = "12")
	private String diciembre;
	

}
