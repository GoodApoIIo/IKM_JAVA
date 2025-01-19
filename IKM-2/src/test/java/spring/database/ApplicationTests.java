package spring.database;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.database.models.entity.Attraction;
import spring.database.models.entity.City;
import spring.database.models.entity.Country;
import spring.database.Service.AttractionService;
import spring.database.Service.CityService;
import spring.database.Service.CountryService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {
    }


}
