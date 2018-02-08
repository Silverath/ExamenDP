package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.WarningNote;

@Repository
public interface WarningNoteRepository extends JpaRepository<WarningNote, Integer> {

	@Query("select w from WarningNote w where w.ticker = ?1")
	WarningNote findWarningNoteByTicker(String ticker);

	@Query("select w from WarningNote w where w.manager.id = ?1")
	Collection<WarningNote> findByManager(int managerId);

	@Query("select w from WarningNote w where w.trip.id = ?1 and (DATE(w.moment) <= (current_date) or w.moment = null)")
	Collection<WarningNote> findByTrip(int tripId);
	
}
