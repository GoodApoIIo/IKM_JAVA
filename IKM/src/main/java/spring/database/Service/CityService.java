package spring.database.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.database.models.entity.City;
import spring.database.repositories.CityRepository;

import java.util.List;
import java.util.Optional;

// Этот сервис управляет бизнес-логикой, связанной с сущностью City.
@Service
public class CityService {
    private final CityRepository cityRepository;

    // @Autowired: Внедрение зависимости CityRepository через конструктор.
    // Это позволяет сервису использовать методы репозитория.
    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    // Метод для получения всех городов из базы данных.
    public List<City> getAllCities() {
        return cityRepository.findAll(); // Использует метод findAll() из JpaRepository.
    }

    // Метод для получения города по его ID.
    public Optional<City> getCityById(Integer id) {
        return cityRepository.findById(id); // Использует метод findById() из JpaRepository.
    }

    // Метод для добавления нового города в базу данных.
    public City addCity(City city) {
        return cityRepository.save(city); // Использует метод save() из JpaRepository.
    }

    // Метод для обновления существующего города.
    public City updateCity(Integer id, City updatedCity) {
        // Проверяем, существует ли город с указанным ID.
        Optional<City> existingCity = cityRepository.findById(id);
        if(existingCity.isPresent()){
            // Если существует, устанавливаем ID для updatedCity и сохраняем изменения.
            updatedCity.setId(id);
            return cityRepository.save(updatedCity);
        }
        // Если города с таким ID не существует, возвращаем null.
        // В реальном приложении можно бросать исключение.
        return null;
    }

    // Метод для удаления города по его ID.
    public void deleteCity(Integer id) {
        cityRepository.deleteById(id); // Использует метод deleteById() из JpaRepository.
    }
}