package spring.database.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.database.models.entity.City;
import spring.database.repositories.CityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    // Метод для получения всех городов из базы данных.
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    // Метод для получения города по его ID.
    public Optional<City> getCityById(Integer id) {
        return cityRepository.findById(id);
    }

    // Метод для добавления нового города в базу данных.
    public City addCity(City city) {
        return cityRepository.save(city);
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
        return null;
    }

    // Метод для удаления города по его ID.
    public void deleteCity(Integer id) {
        cityRepository.deleteById(id);
    }

    // Метод для получения города со всеми его достопримечательностями
    public Optional<City> getCityWithAttractions(Integer id) {
        return cityRepository.findById(id);
    }
}