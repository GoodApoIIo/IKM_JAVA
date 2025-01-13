package spring.database.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "attractions")
public class Attraction {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String attractionName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    public Attraction() {
        // Конструктор по умолчанию необходим для JPA
    }

    // Геттер для id
    public Integer getId() {
        return id;
    }

    // Сеттер для id (не рекомендуется для @GeneratedValue, но добавлен для полноты)
    public void setId(Integer id) {
        this.id = id;
    }

    // Геттер для attractionName
    public String getAttractionName() {
        return attractionName;
    }

    // Сеттер для attractionName
    public void setAttractionName(String attractionName) {
        this.attractionName = attractionName;
    }

    // Геттер для city
    public City getCity() {
        return city;
    }

    // Сеттер для city
    public void setCity(City city) {
        this.city = city;
    }
}