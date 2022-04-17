package gob.pe.senamhi.sia.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gob.pe.senamhi.sia.Beans.Nivel;

@Repository
public interface NivelDao extends JpaRepository<Nivel, String>{
	
	@Query(nativeQuery = true, value = "SELECT LE.ID,\r\n"
									 + "       LE.NOMBRE LEYENDA_NOMBRE,\r\n"
									 + "       LE.PRIORIDAD ,\r\n"
									 + "       LPAD(EXTRACT(MONTH FROM A.FECHA),2,'0')||'-'||EXTRACT(YEAR FROM A.FECHA) FECHA,\r\n"
									 + "       LISTAGG(H.ID_DEP||','||H.ID_PROV, '#') WITHIN GROUP (ORDER BY H.ID_DEP,H.ID_PROV) DEPPROV\r\n"
									 + "  FROM AFECTACION A\r\n"
									 + "       INNER JOIN HIST_DEP_PROV H ON H.ID=HIST_DEP_PROV_ID\r\n"
									 + "       INNER JOIN LEYENDA LE ON LE.ID=A.LEYENDA_ID\r\n"
									 + "       INNER JOIN (SELECT V.LEYENDA_ID,\r\n"
									 + "                          MAX(A.FECHA) FECHA\r\n"
									 + "                     FROM AFECTACION A\r\n"
									 + "                          INNER JOIN VALORES V ON V.LEYENDA_ID=A.LEYENDA_ID AND V.CULTIVO_ID=?1\r\n"
									 + "                    GROUP BY V.LEYENDA_ID) L ON L.LEYENDA_ID=A.LEYENDA_ID \r\n"
									 + " WHERE EXTRACT(MONTH FROM A.FECHA) = EXTRACT(MONTH FROM L.FECHA)\r\n"
									 + "   AND EXTRACT(YEAR FROM A.FECHA) = EXTRACT(YEAR FROM L.FECHA)\r\n"
									 + " GROUP BY LE.ID,LE.NOMBRE,LE.PRIORIDAD, LPAD(EXTRACT(MONTH FROM A.FECHA),2,'0')||'-'||EXTRACT(YEAR FROM A.FECHA)\r\n"
									 + " ORDER BY LE.PRIORIDAD")
	List<Nivel> findPrioridadByCultivo(Integer cultivo);
	
}
