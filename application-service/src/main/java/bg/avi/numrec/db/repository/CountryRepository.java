package bg.avi.numrec.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bg.avi.numrec.db.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
	List<Country> findAll();
}
