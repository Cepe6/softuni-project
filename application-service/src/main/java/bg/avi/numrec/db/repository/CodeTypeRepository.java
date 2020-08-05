package bg.avi.numrec.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bg.avi.numrec.db.entity.CodeType;

@Repository
public interface CodeTypeRepository extends JpaRepository<CodeType, Integer> {
	List<CodeType> findAll();
}
