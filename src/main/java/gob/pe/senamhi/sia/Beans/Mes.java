package gob.pe.senamhi.sia.Beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="AFECTACION")
@Data
public class Mes implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "LINEA")
	private String linea;
	
	@Column(name = "MES")
	private String mes;
	
	@Column(name = "ANIO")
	private String anio;

}
