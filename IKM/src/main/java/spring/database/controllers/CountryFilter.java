package spring.database.controllers;

import org.springframework.data.jpa.domain.Specification;
import spring.database.models.entity.Country;

public record CountryFilter(String countryName) {
    public Specification<Country> toSpecification() {
        return Specification.where(countryNameSpec());
    }

    private Specification<Country> countryNameSpec() {
        return (root, query, cb) -> {
            if (countryName == null || countryName.isEmpty()) {
                return null;
            }
            return cb.like(cb.lower(root.get(countryName)), countryName);
        };
    }
}

