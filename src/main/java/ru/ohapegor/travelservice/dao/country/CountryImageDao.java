package ru.ohapegor.travelservice.dao.country;


import ru.ohapegor.travelservice.entities.CountryImage;

public interface CountryImageDao {

    CountryImage getById(Long id);

    CountryImage getByCountryId(Long countryId);

    CountryImage save(CountryImage country);

    boolean deleteById(Long id);

}
