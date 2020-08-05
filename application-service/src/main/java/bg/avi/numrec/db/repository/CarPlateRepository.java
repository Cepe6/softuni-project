package bg.avi.numrec.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bg.avi.numrec.db.entity.CarPlate;

@Repository
public interface CarPlateRepository extends JpaRepository<CarPlate, Integer> {
	List<CarPlate> findAll();
}
