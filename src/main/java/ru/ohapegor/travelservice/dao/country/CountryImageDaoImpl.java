package ru.ohapegor.travelservice.dao.country;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.ohapegor.travelservice.entities.CountryImage;
import ru.ohapegor.travelservice.util.TourUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("min")
@Transactional
public class CountryImageDaoImpl implements CountryImageDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public CountryImage getById(Long id) {
        CountryImage img = entityManager.find(CountryImage.class,id);
        return img;
    }

    @Override
    @Transactional(readOnly = true)
    public CountryImage getByCountryId(Long countryId) {
        List<CountryImage> imgs = entityManager.createQuery("SELECT i FROM CountryImage i where i.countryId = "+countryId,CountryImage.class).getResultList();
        return DataAccessUtils.singleResult(imgs);
    }

    @Override
    public CountryImage save(CountryImage countryImg) {
        if (TourUtil.isNew(countryImg)) {
            entityManager.persist(countryImg);
            return countryImg;
        } else {
            return entityManager.merge(countryImg);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        int n = entityManager.createQuery("DELETE from CountryImage i where i.id = " + id).executeUpdate();
        return n==1;
    }
}
