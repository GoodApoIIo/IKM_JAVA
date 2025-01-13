package spring.database.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.database.models.entity.Country;
import spring.database.Service.CountryService;

import java.util.List;
import java.util.Optional;

// @RestController: Указывает, что это REST-контроллер.
@RestController
@RequestMapping("/countries") // @RequestMapping: Базовый URL для всех запросов к этому контроллеру.
public class CountryResource {

    private final CountryService countryService;

    // @Autowired: Внедрение зависимости CountryService через конструктор.
    @Autowired
    public CountryResource(CountryService countryService) {
        this.countryService = countryService;
    }

    // @GetMapping: Обрабатывает HTTP GET запросы по URL /countries
    // Возвращает список всех стран.
    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries() {
        return new ResponseEntity<>(countryService.getAllCountries(), HttpStatus.OK);
    }

    // @GetMapping("/{id}"): Обрабатывает HTTP GET запросы по URL /countries/{id}
    // Возвращает страну по ID, если она существует.
    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable Integer id) {
        Optional<Country> country = countryService.getCountryById(id);
        // Если страна найдена, возвращает ее с HTTP-статусом 200 OK.
        // Иначе возвращает HTTP-статус 404 NOT FOUND.
        return country.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // @PostMapping: Обрабатывает HTTP POST запросы по URL /countries
    // Добавляет новую страну.
    @PostMapping
    public ResponseEntity<Country> addCountry(@RequestBody Country country) {
        return new ResponseEntity<>(countryService.addCountry(country), HttpStatus.CREATED);
    }

    // @PutMapping("/{id}"): Обрабатывает HTTP PUT запросы по URL /countries/{id}
    // Обновляет существующую страну по ID.
    @PutMapping("/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable Integer id, @RequestBody Country updatedCountry) {
        // Пытаемся обновить страну.
        Country country = countryService.updateCountry(id, updatedCountry);
        // Если обновление успешно, возвращаем страну с HTTP-статусом 200 OK.
        if(country != null){
            return new ResponseEntity<>(country, HttpStatus.OK);
        }
        // Если страна с таким ID не найдена, возвращаем HTTP-статус 404 NOT FOUND.
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // @DeleteMapping("/{id}"): Обрабатывает HTTP DELETE запросы по URL /countries/{id}
    // Удаляет страну по ID.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Integer id) {
        countryService.deleteCountry(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Возвращает HTTP-статус 204 NO CONTENT.
    }
}