package spring.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.database.models.entity.Attraction;


public interface AttractionRepository extends JpaRepository<Attraction, Integer> {
}
