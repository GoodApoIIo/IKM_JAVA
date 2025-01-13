package spring.database.controllers;

import spring.database.models.entity.City;
import org.springframework.data.jpa.domain.Specification;

public record CityFilter(String nameLike, Long countryId) {
    public Specification<City> toSpecification() {
        return Specification.where(nameLikeSpec().and(countryIdSpec()));
    }

    private Specification<City> nameLikeSpec() {
        return (root, query, cb) -> nameLike != null ?
                cb.like(cb.lower(root.get("name")), "%" + nameLike.toLowerCase() + "%") : null;
    }

    private Specification<City> countryIdSpec() {
        return ((root, query, cb) -> countryId != null
                ? cb.equal(root.get("country").get("id"), countryId)
                : null);
    }
}
