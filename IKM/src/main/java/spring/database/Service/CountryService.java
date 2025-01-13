package spring.database.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.database.models.entity.Country;
import spring.database.repositories.CountryRepository;

import java.util.List;
import java.util.Optional;

// Этот сервис управляет бизнес-логикой, связанной с сущностью Country.
@Service
public class CountryService {

    private final CountryRepository countryRepository;

    // @Autowired: Внедрение зависимости CountryRepository через конструктор.
    // Это позволяет сервису использовать методы репозитория.
    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    // Метод для получения всех стран из базы данных.
    public List<Country> getAllCountries() {
        return countryRepository.findAll(); // Использует метод findAll() из JpaRepository.
    }

    // Метод для получения страны по ее ID.
    public Optional<Country> getCountryById(Integer id) {
        return countryRepository.findById(id); // Использует метод findById() из JpaRepository.
    }

    // Метод для добавления новой страны в базу данных.
    public Country addCountry(Country country) {
        return countryRepository.save(country); // Использует метод save() из JpaRepository.
    }

    // Метод для обновления существующей страны.
    public Country updateCountry(Integer id, Country updatedCountry) {
        // Проверяем, существует ли страна с указанным ID.
        Optional<Country> existingCountry = countryRepository.findById(id);
        if (existingCountry.isPresent()) {
            // Если существует, устанавливаем ID для updatedCountry и сохраняем изменения.
            updatedCountry.setId(id);
            return countryRepository.save(updatedCountry);
        }
        // Если страны с таким ID не существует, возвращаем null.
        // В реальном приложении можно бросать исключение.
        return null;
    }

    // Метод для удаления страны по ее ID.
    public void deleteCountry(Integer id) {
        countryRepository.deleteById(id); // Использует метод deleteById() из JpaRepository.
    }
}