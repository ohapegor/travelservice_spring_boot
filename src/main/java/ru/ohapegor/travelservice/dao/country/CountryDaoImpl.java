package ru.ohapegor.travelservice.dao.country;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ohapegor.travelservice.entities.Country;
import ru.ohapegor.travelservice.util.TourUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static ru.ohapegor.travelservice.util.LoggingUtil.enterInfo;
import static ru.ohapegor.travelservice.util.LoggingUtil.exitInfo;


@Repository
@Transactional
public class CountryDaoImpl implements CountryDao {

    private static final Logger logger = LoggerFactory.getLogger(CountryDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional(readOnly = true)
    public Country getById(Long id) {
        logger.info(enterInfo(id));
        Country country = entityManager.find(Country.class,id);
        logger.info(exitInfo());
        return country;
    }

    @Override
    @Transactional(readOnly = true)
    public Country getByName(String name) {
        logger.info(enterInfo(name));
        List<Country> country = entityManager
                .createQuery("SELECT c FROM Country c WHERE c.name=:name",Country.class)
                .setParameter("name",name)
                .getResultList();
        logger.info(exitInfo());
        return DataAccessUtils.singleResult(country);
    }

    @Override
    public Country save(Country country) {
        logger.info(enterInfo(country));
        if (TourUtil.isNew(country)) {
            entityManager.persist(country);
            return country;
        } else {
            return entityManager.merge(country);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        logger.info(enterInfo());
        int n = entityManager.createQuery("DELETE from Country c where c.id = " + id).executeUpdate();
        logger.info(exitInfo());
        return n==1;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Country> getAll() {
        logger.info(enterInfo());
        List<Country> countries = entityManager.createQuery("FROM Country c",Country.class).getResultList();
        logger.info(exitInfo());
        return countries;
    }
}
