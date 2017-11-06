package com.bairei.ormapp.repositories.implementations;

import com.bairei.ormapp.models.Band;
import com.bairei.ormapp.repositories.BandRepository;
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
public class BandRepositoryImpl implements BandRepository{
    private final Logger log = LoggerFactory.getLogger(BandRepositoryImpl.class);

    private SessionFactory sessionFactory;


    public BandRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveOrUpdate(Band band) {
        sessionFactory.getCurrentSession().saveOrUpdate(band);
    }

    @Override
    public Band save(Band band) {
        Long id = (Long) sessionFactory.getCurrentSession().save(band);
        log.info("ID:" + id.toString());
        return findById(id);
    }


    @Override
    @Transactional
    public List<Band> listAll() {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Band.class);
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @Override
    public Band findById(Long aLong) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.load(Band.class, aLong);
    }

    @Override
    public void delete(Band band) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(band);
    }

    @Override
    public void deleteById(Long aLong) {
        Session session = this.sessionFactory.getCurrentSession();
        Band toDelete = session.load(Band.class, aLong);
        if (toDelete != null){
            session.delete(toDelete);
        }
    }

    @Override
    public Integer count() {
        return listAll().size();
    }
}
