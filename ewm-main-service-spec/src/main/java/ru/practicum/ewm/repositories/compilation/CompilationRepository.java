package ru.practicum.ewm.repositories.compilation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.models.compilation.Compilation;

/**
 * Интерфейс репозиторий подборок событий.
 *
 * @version 1.0
 * @autor Lobachev
 */
@Repository
public interface CompilationRepository extends JpaRepository<Compilation, Long> {
    /**
     * Метод - удаление подборки
     *
     * @param idComp  - id подборки
     * @param idEvent - имя события
     */
    @Modifying
    @Transactional
    @Query("delete from CompilationEvent ce where ce.compilationId =?1 and ce.eventId =?2")
    void deleteCompilation(long idComp, long idEvent);

    /**
     * Метод - добавление событий в подборку
     *
     * @param id  - id подборки
     * @param ide - id событий
     */
    @Modifying
    @Query(value = "insert into Compilations_Events (compilation_id,events_id) VALUES (:id,:ide)", nativeQuery = true)
    @Transactional
    void addEvent(long id, long ide);

    /**
     * Метод - закрепление подборки на главной странице
     *
     * @param idComp - id подборки
     * @param pinned - закрепить/открепить
     */
    @Modifying(clearAutomatically = true)
    @Query("update Compilation c set c.pinned =?1 where c.id =?2")
    void updatePin(Boolean pinned, long idComp);

    /**
     * Метод - получение подборок
     *
     * @param pageable - правила выборки
     * @param pinned   - выборка
     */
    Page<Compilation> findAllByPinned(Boolean pinned, Pageable pageable);
}
