package gob.pe.senamhi.sia.Beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="VALORES")
@Data
public class Valor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "CULTIVO_ID")
	private String cultivoId;
	
	@Column(name = "CULTIVO")
	private String cultivo;
	
	@Id
	@Column(name = "LEYENDA_ID")
	private String leyendaId;
	
	@Column(name = "LEYENDA")
	private String descripcion;
	
	@Column(name = "AREA")
	private String area;
	
	@Column(name = "PERIMETRO")
	private String perimetro;
	
	@Column(name = "IMAGEN")
	private String imagen;
}
