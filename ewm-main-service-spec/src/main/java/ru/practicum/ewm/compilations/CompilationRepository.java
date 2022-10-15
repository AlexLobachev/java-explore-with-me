package ru.practicum.ewm.compilations;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.compilations.model.Compilation;

@Repository
public interface CompilationRepository extends JpaRepository<Compilation, Long> {

    @Modifying
    @Transactional
    @Query("delete from CompilationEvent ce where ce.compilationId =?1 and ce.eventId =?2")
    void deleteCompilation(long idComp, long idEvent);

    @Modifying
    @Query(value = "insert into Compilations_Events (compilation_id,events_id) VALUES (:id,:ide)", nativeQuery = true)
    @Transactional
    void addEvent(long id, long ide);

    @Modifying
    @Query("update Compilation c set c.pinned =?1 where c.id =?2")
    @Transactional
    void updatePin(Boolean pinned, long idComp);

    Page<Compilation> findAllByPinned(Boolean pinned, Pageable pageable);
}
