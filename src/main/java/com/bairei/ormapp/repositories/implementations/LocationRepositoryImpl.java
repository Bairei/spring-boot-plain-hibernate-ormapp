package com.bairei.ormapp.repositories.implementations;

import com.bairei.ormapp.models.Location;
import com.bairei.ormapp.repositories.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@Slf4j
public class LocationRepositoryImpl implements LocationRepository {

    private SessionFactory sessionFactory;


    public LocationRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveOrUpdate(Location location) {
        sessionFactory.getCurrentSession().saveOrUpdate(location);
    }

    @Override
    public Location save(Location location) {
        sessionFactory.getCurrentSession().saveOrUpdate(location);
        // log.info("ID:" + location.getId().toString());
        return findById(location.getId());
    }

    @Override
    public List<Location> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Location.class);
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @Override
    public Location findById(Long aLong) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.load(Location.class, aLong);
    }

    @Override
    public void delete(Location location) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(location);
    }

    @Override
    public void deleteById(Long aLong) {
        Session session = this.sessionFactory.getCurrentSession();
        Location toDelete = session.load(Location.class, aLong);
        if (toDelete != null){
            session.delete(toDelete);
        }
    }

    @Override
    public Integer count() {
        return findAll().size();
    }

}
