package gob.pe.senamhi.sia.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gob.pe.senamhi.sia.Beans.Valor;

@Repository
public interface ValorDao extends JpaRepository<Valor, String>{
	
	@Query(nativeQuery = true, value = "SELECT V.CULTIVO_ID,\r\n"
									 + "       C.NOMBRE CULTIVO, \r\n"
									 + "       V.LEYENDA_ID,\r\n"
									 + "       L.NOMBRE LEYENDA,\r\n"
									 + "       V.AREA,\r\n"
									 + "       V.PERIMETRO,\r\n"
									 + "       I.IMAGEN\r\n"
									 + "  FROM VALORES V\r\n"
									 + "       INNER JOIN CULTIVO C ON V.CULTIVO_ID=C.ID\r\n"
									 + "       INNER JOIN LEYENDA L ON V.LEYENDA_ID=L.ID\r\n"
									 + "       LEFT JOIN IMAGEN_CULTIVO I ON I.CULTIVO_ID=C.ID AND  EXTRACT(MONTH FROM V.FECHA)=EXTRACT(MONTH FROM I.FECHA) AND EXTRACT(YEAR FROM V.FECHA)=EXTRACT(YEAR FROM I.FECHA)\r\n"
									 + " WHERE C.ID = ?1\r\n"
									 + "   AND EXTRACT(MONTH FROM V.FECHA) = ?2\r\n"
									 + "   AND EXTRACT(YEAR FROM V.FECHA) = ?3\r\n"
									 + " GROUP BY V.CULTIVO_ID,C.NOMBRE,V.LEYENDA_ID,L.NOMBRE,V.AREA,V.PERIMETRO,I.IMAGEN\r\n"
									 + " ORDER BY V.CULTIVO_ID, V.LEYENDA_ID ")
	List<Valor> findValores(Integer cultivo, Integer mes, Integer anio);
	
}
