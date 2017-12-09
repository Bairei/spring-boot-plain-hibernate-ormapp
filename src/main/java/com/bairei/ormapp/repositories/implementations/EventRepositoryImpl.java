package com.bairei.ormapp.repositories.implementations;

import com.bairei.ormapp.models.Event;
import com.bairei.ormapp.repositories.EventRepository;
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
public class EventRepositoryImpl implements EventRepository {
    private final Logger log = LoggerFactory.getLogger(EventRepositoryImpl.class);

    private SessionFactory sessionFactory;


    public EventRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveOrUpdate(Event event) {
        sessionFactory.getCurrentSession().saveOrUpdate(event);
    }

    @Override
    public Event save(Event event) {
        sessionFactory.getCurrentSession().saveOrUpdate(event);
        log.info("ID:" + event.getId().toString());
        return findById(event.getId());
    }


    @Override
    public List<Event> listAll() {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Event.class);
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @Override
    public Event findById(Long aLong) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.load(Event.class, aLong);
    }

    @Override
    public void delete(Event event) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(event);
    }

    @Override
    public void deleteById(Long aLong) {
        Session session = this.sessionFactory.getCurrentSession();
        Event toDelete = session.load(Event.class, aLong);
        if (toDelete != null){
            session.delete(toDelete);
        }
    }

    @Override
    public Integer count() {
        return listAll().size();
    }
}
