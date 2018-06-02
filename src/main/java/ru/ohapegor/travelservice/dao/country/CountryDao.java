package ru.ohapegor.travelservice.dao.country;



import ru.ohapegor.travelservice.entities.Country;

import java.util.List;

public interface CountryDao {

    Country getById(Long id);

    Country getByName(String name);

    Country save(Country country);

    boolean deleteById(Long id);

    List<Country> getAll();
}
