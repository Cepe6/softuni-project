package bg.avi.numrec.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bg.avi.numrec.db.entity.RecognitionHistory;

@Repository
public interface RecognitionHistoryRepository extends JpaRepository<RecognitionHistory, Integer> {
	List<RecognitionHistory> findAll();
}
