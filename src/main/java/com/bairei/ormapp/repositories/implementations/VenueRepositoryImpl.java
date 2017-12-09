package com.bairei.ormapp.repositories.implementations;

import com.bairei.ormapp.models.Venue;
import com.bairei.ormapp.repositories.VenueRepository;
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
@Transactional
public class VenueRepositoryImpl implements VenueRepository {
    private final Logger log = LoggerFactory.getLogger(VenueRepositoryImpl.class);

    private SessionFactory sessionFactory;


    public VenueRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveOrUpdate(Venue venue) {
        sessionFactory.getCurrentSession().saveOrUpdate(venue);
    }

    @Override
    public Venue save(Venue venue) {
        sessionFactory.getCurrentSession().saveOrUpdate(venue);
        log.info("ID:" + venue.getId().toString());
        return findById(venue.getId());
    }

    @Override
    public List<Venue> listAll() {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Venue.class);
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @Override
    public Venue findById(Long aLong) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.load(Venue.class, aLong);
    }

    @Override
    public void delete(Venue venue) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(venue);
    }

    @Override
    public void deleteById(Long aLong) {
        Session session = this.sessionFactory.getCurrentSession();
        Venue toDelete = session.load(Venue.class, aLong);
        if (toDelete != null){
            session.delete(toDelete);
        }
    }

    @Override
    public Integer count() {
        return listAll().size();
    }

}
