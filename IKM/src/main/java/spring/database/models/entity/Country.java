package spring.database.models.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String countryName;

    @OneToMany(mappedBy = "country")
    private List<City> cities;

    public Country() {
    }

    // Геттер для id
    public Integer getId() {
        return id;
    }

    // Сеттер для id
    public void setId(Integer id) {
        this.id = id;
    }

    // Геттер для countryName
    public String getCountryName() {
        return countryName;
    }

    // Сеттер для countryName
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    // Геттер для cities
    public List<City> getCities() {
        return cities;
    }

    // Сеттер для cities
    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}