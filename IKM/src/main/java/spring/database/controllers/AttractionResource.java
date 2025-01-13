package spring.database.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import spring.database.models.entity.Attraction;
import spring.database.Service.AttractionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/attractions")
public class AttractionResource {

    private final AttractionService attractionService;

    @Autowired
    public AttractionResource(AttractionService attractionService) {
        this.attractionService = attractionService;
    }

    // Получение всех достопримечательностей
    @GetMapping
    public ResponseEntity<List<Attraction>> getAllAttractions() {
        return new ResponseEntity<>(attractionService.getAllAttractions(), HttpStatus.OK);
    }

    // Получение достопримечательности по ID
    @GetMapping("/{id}")
    public ResponseEntity<Attraction> getAttractionById(@PathVariable Integer id) {
        Optional<Attraction> attractionOptional = attractionService.getAttractionById(id);
        return attractionOptional.map(attraction -> new ResponseEntity<>(attraction, HttpStatus.OK))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Attraction with id " + id + " not found"));
    }

    // Добавление новой достопримечательности
    @PostMapping
    public ResponseEntity<Attraction> addAttraction(@RequestBody @Valid Attraction attraction) {
        return new ResponseEntity<>(attractionService.addAttraction(attraction), HttpStatus.CREATED);
    }

    // Обновление существующей достопримечательности по ID
    @PutMapping("/{id}")
    public ResponseEntity<Attraction> updateAttraction(@PathVariable Integer id, @RequestBody @Valid Attraction updatedAttraction) {
        Attraction attraction = attractionService.updateAttraction(id, updatedAttraction);
        if(attraction == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attraction with id " + id + " not found");
        }
        return new ResponseEntity<>(attraction, HttpStatus.OK);
    }

    // Удаление достопримечательности по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttraction(@PathVariable Integer id) {
        Optional<Attraction> attractionOptional = attractionService.getAttractionById(id);
        if(attractionOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attraction with id " + id + " not found");
        }
        attractionService.deleteAttraction(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}