package gob.pe.senamhi.sia.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gob.pe.senamhi.sia.Beans.Mes;

@Repository
public interface MesDao extends JpaRepository<Mes,String>{
	
	
	@Query(nativeQuery = true, value = "SELECT ROWNUM LINEA,\r\n"
								 + "           ANIO,\r\n"
								 + "           '' MES\r\n"
								 + "      FROM (\r\n"
								 + "    SELECT EXTRACT(YEAR FROM A.FECHA) ANIO\r\n"
								 + "      FROM AFECTACION A \r\n"
								 + "           INNER JOIN VALORES V ON V.LEYENDA_ID=A.LEYENDA_ID AND V.CULTIVO_ID=?1\r\n"
								 + "     GROUP BY EXTRACT(YEAR FROM A.FECHA))")
	List<Mes> findYears(Integer cultivo);
	
	@Query(nativeQuery = true, value = "SELECT ROWNUM LINEA,\r\n"
								 + "          '' ANIO,\r\n"
								 + "          LPAD(MES,2,'0') MES\r\n"
								 + "     FROM (\r\n"
								 + "    SELECT EXTRACT(MONTH FROM A.FECHA) MES\r\n"
								 + "      FROM AFECTACION A\r\n"
								 + "           INNER JOIN VALORES V ON V.LEYENDA_ID=A.LEYENDA_ID AND V.CULTIVO_ID=?1\r\n"
								 + "     WHERE EXTRACT(YEAR FROM A.FECHA) = ?2\r\n"
								 + "     GROUP BY EXTRACT(MONTH FROM A.FECHA))")
	List<Mes> findMonths(Integer cultivo,Integer anio);
}
