package spring.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.database.models.entity.City;

public interface CityRepository extends JpaRepository<City, Integer> {
}