package bg.avi.numrec.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bg.avi.numrec.db.entity.PlatePattern;

@Repository
public interface PlatePatternRepository extends JpaRepository<PlatePattern, Integer> {
	List<PlatePattern> findAll();
}
