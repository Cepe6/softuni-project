package bg.avi.numrec.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bg.avi.numrec.db.entity.PlateCode;

@Repository
public interface PlateCodeRepository extends JpaRepository<PlateCode, Integer> {
	List<PlateCode> findAll();
}
