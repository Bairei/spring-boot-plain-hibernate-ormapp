package com.bairei.ormapp.repositories.implementations;

import com.bairei.ormapp.models.Location;
import com.bairei.ormapp.repositories.LocationRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class LocationRepositoryImpl implements LocationRepository {

    private final Logger log = LoggerFactory.getLogger(LocationRepositoryImpl.class);

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
        Long id = (Long) sessionFactory.getCurrentSession().save(location);
        log.info("ID:" + id.toString());
        return findById(id);
    }

    @Override
    @Transactional
    public List<Location> listAll() {
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
        return listAll().size();
    }

}
