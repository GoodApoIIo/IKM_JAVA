package spring.database.models.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "cities")
public class City {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String cityName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @OneToMany(mappedBy = "city")
    private List<Attraction> attractions;

    public City() {
        // Конструктор по умолчанию необходим для JPA
    }

    // Геттер для id
    public Integer getId() {
        return id;
    }

    // Сеттер для id
    public void setId(Integer id) {
        this.id = id;
    }

    // Геттер для cityName
    public String getCityName() {
        return cityName;
    }

    // Сеттер для cityName
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    // Геттер для country
    public Country getCountry() {
        return country;
    }

    // Сеттер для country
    public void setCountry(Country country) {
        this.country = country;
    }

    // Геттер для attractions
    public List<Attraction> getAttractions() {
        return attractions;
    }

    // Сеттер для attractions
    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }
}
