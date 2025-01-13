package spring.database.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.database.models.entity.Attraction;
import spring.database.repositories.AttractionRepository;

import java.util.List;
import java.util.Optional;

// Этот сервис управляет бизнес-логикой, связанной с сущностью Attraction.
@Service
public class AttractionService {
    private final AttractionRepository attractionRepository;

    // @Autowired: Внедрение зависимости AttractionRepository через конструктор.
    // Это позволяет сервису использовать методы репозитория.
    @Autowired
    public AttractionService(AttractionRepository attractionRepository) {
        this.attractionRepository = attractionRepository;
    }

    // Метод для получения всех достопримечательностей из базы данных.
    public List<Attraction> getAllAttractions() {
        return attractionRepository.findAll(); // Использует метод findAll() из JpaRepository.
    }

    // Метод для получения достопримечательности по ее ID.
    public Optional<Attraction> getAttractionById(Integer id) {
        return attractionRepository.findById(id); // Использует метод findById() из JpaRepository.
    }

    // Метод для добавления новой достопримечательности в базу данных.
    public Attraction addAttraction(Attraction attraction) {
        return attractionRepository.save(attraction); // Использует метод save() из JpaRepository.
    }

    // Метод для обновления существующей достопримечательности.
    public Attraction updateAttraction(Integer id, Attraction updatedAttraction) {
        // Проверяем, существует ли достопримечательность с указанным ID.
        Optional<Attraction> existingAttraction = attractionRepository.findById(id);
        if(existingAttraction.isPresent()){
            // Если существует, устанавливаем ID для updatedAttraction и сохраняем изменения.
            updatedAttraction.setId(id);
            return attractionRepository.save(updatedAttraction);
        }
        // Если достопримечательности с таким ID не существует, возвращаем null.
        // В реальном приложении можно бросать исключение.
        return null;
    }

    // Метод для удаления достопримечательности по ее ID.
    public void deleteAttraction(Integer id) {
        attractionRepository.deleteById(id);  // Использует метод deleteById() из JpaRepository.
    }
}
