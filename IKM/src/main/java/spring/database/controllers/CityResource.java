package spring.database.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import spring.database.models.entity.City;
import spring.database.Service.CityService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cities")
public class CityResource {
    private final CityService cityService;

    @Autowired
    public CityResource(CityService cityService) {
        this.cityService = cityService;
    }

    // Получение всех городов
    @GetMapping
    public ResponseEntity<List<City>> getAllCities() {
        return new ResponseEntity<>(cityService.getAllCities(), HttpStatus.OK);
    }

    // Получение города по ID
    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable Integer id) {
        Optional<City> cityOptional = cityService.getCityById(id);
        return cityOptional.map(city -> new ResponseEntity<>(city, HttpStatus.OK))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "City with id " + id + " not found"));
    }

    // Получение города со всеми достопримечательностями по ID
    @GetMapping("/{id}/attractions")
    public ResponseEntity<City> getCityWithAttractionsById(@PathVariable Integer id) {
        Optional<City> cityOptional = cityService.getCityWithAttractions(id);
        return cityOptional.map(city -> new ResponseEntity<>(city, HttpStatus.OK))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "City with id " + id + " not found"));
    }

    // Добавление нового города
    @PostMapping
    public ResponseEntity<City> addCity(@RequestBody @Valid City city) {
        return new ResponseEntity<>(cityService.addCity(city), HttpStatus.CREATED);
    }

    // Обновление существующего города по ID
    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(@PathVariable Integer id, @RequestBody @Valid City updatedCity) {
        City city = cityService.updateCity(id, updatedCity);
        if(city == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City with id " + id + " not found");
        }
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    // Удаление города по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Integer id) {
        Optional<City> cityOptional = cityService.getCityById(id);
        if(cityOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City with id " + id + " not found");
        }
        cityService.deleteCity(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}