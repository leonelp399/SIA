package gob.pe.senamhi.sia.Beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="FICHA_INFORMATIVA")
@Data
public class Ficha implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "CULTIVO_ID")
	private String cultivoId;

	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "ENLACE")
	private String enlace;
	
}
