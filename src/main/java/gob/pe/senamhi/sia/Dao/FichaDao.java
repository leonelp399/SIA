package gob.pe.senamhi.sia.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gob.pe.senamhi.sia.Beans.Ficha;

@Repository
public interface FichaDao extends JpaRepository<Ficha, String> {
	
	@Query(nativeQuery = true, value = "SELECT CULTIVO_ID, \r\n"
									 + "       DESCRIPCION,\r\n"
									 + "       ENLACE\r\n"
									 + "  FROM FICHA_INFORMATIVA\r\n"
									 + " WHERE CULTIVO_ID = ?1")
    Ficha findById(Integer id);
	
	@Transactional
    @Modifying
    @Query(value = "{CALL SP_FICHA_INFORMATIVA(:descripcion, :enlace, :cultivo)}", nativeQuery = true)
    void sp_ficha_informativa(
            @Param("descripcion") String descripcion,
            @Param("enlace") String enlace,
            @Param("cultivo") int cultivo);
	
}
