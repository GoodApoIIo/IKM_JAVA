package spring.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.database.models.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}