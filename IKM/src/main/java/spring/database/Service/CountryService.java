package spring.database.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.database.models.entity.Country;
import spring.database.repositories.CountryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    // Метод для получения всех стран из базы данных.
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    // Метод для получения страны по ее ID.
    public Optional<Country> getCountryById(Integer id) {
        return countryRepository.findById(id);
    }

    // Метод для добавления новой страны в базу данных.
    public Country addCountry(Country country) {
        return countryRepository.save(country);
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
        return null;
    }

    // Метод для удаления страны по ее ID.
    public void deleteCountry(Integer id) {
        countryRepository.deleteById(id);
    }
}