package ru.practicum.ewm.services.compilation.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.dtos.compilation.CompilationDto;
import ru.practicum.ewm.models.compilation.Compilation;
import ru.practicum.ewm.repositories.compilation.CompilationRepository;
import ru.practicum.ewm.repositories.event.EventRepository;

import static ru.practicum.ewm.mappers.compilation.CompilationMapper.compilationDtoToCompilation;

/**
 * Класс бизнес логики подборок событий. (для Администратора)
 *
 * @version 1.0
 * @autor Lobachev
 */
@Service
@RequiredArgsConstructor
@Transactional
public class AdminCompilationServiceImpl implements AdminCompilationService {
    /**
     * Поле зависимость от репозитория CompilationRepository
     */
    private final CompilationRepository compilationRepository;
    /**
     * Поле зависимость от репозитория EventRepository
     */
    private final EventRepository eventRepository;

    /**
     * Метод - создания подборки
     *
     * @param compilationDto - сущность (подборка)
     */
    public Compilation createCompilation(CompilationDto compilationDto) {

        Compilation compilation = compilationDtoToCompilation(compilationDto, eventRepository.findAllById(compilationDto.getEvents()));
        return compilationRepository.save(compilation);
    }

    /**
     * Метод - удаления подборки
     *
     * @param compId - id подборки которую необходимо удалить
     */
    public void deleteCompilation(long compId) {
        compilationRepository.deleteById(compId);
    }

    /**
     * Метод - удаления события из подборки
     *
     * @param compId   - id подборки из которой удаоить
     * @param eventId- id события которое необходимо удалить
     */
    public void deleteEventFromCompilation(long compId, long eventId) {
        compilationRepository.deleteCompilation(compId, eventId);
    }

    /**
     * Метод - добавления события в подборку
     *
     * @param compId   - id подборки в которую необходимо добавить событие
     * @param eventId- id события которое необходимо добавить
     */
    public void addEventToCompilation(long compId, long eventId) {
        compilationRepository.addEvent(compId, eventId);
    }

    /**
     * Метод - открепления подборки с главной страницы
     *
     * @param compId - id подборки в которую необходимо открепить
     */
    public void unpinCompilation(long compId) {
        compilationRepository.updatePin(false, compId);
    }

    /**
     * Метод - прикрепления подборки на главную страницу
     *
     * @param compId - id подборки которую необходимо прикрепить
     */
    public void pinCompilation(long compId) {
        System.out.println("SADDDDDDDDDDDDDDDDD");
        compilationRepository.updatePin(true, compId);
    }
}
