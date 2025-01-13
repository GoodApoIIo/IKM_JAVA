package spring.database.controllers;

import spring.database.models.entity.Attraction;
import org.springframework.data.jpa.domain.Specification;


public record AttractionFilter(String nameLike, Long cityId) {
    public Specification<Attraction> toSpecification() {
        return Specification.where(nameLikeSpec()).and(cityIdSpec());
    }

    private Specification<Attraction> nameLikeSpec() {
        return (root, query, cb) -> nameLike != null ?
                cb.like(cb.lower(root.get("name")), "%" + nameLike.toLowerCase() + "%") : null;
    }


    private Specification<Attraction> cityIdSpec() {
        return ((root, query, cb) -> cityId != null
                ? cb.equal(root.get("city").get("id"), cityId)
                : null);
    }
}
