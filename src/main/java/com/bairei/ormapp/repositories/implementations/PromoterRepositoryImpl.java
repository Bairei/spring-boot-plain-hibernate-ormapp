package com.bairei.ormapp.repositories.implementations;

import com.bairei.ormapp.models.Promoter;
import com.bairei.ormapp.repositories.PromoterRepository;
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
public class PromoterRepositoryImpl implements PromoterRepository {
    private final Logger log = LoggerFactory.getLogger(PromoterRepositoryImpl.class);

    private SessionFactory sessionFactory;


    public PromoterRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveOrUpdate(Promoter promoter) {
        sessionFactory.getCurrentSession().saveOrUpdate(promoter);
    }

    @Override
    public Promoter save(Promoter promoter) {
        sessionFactory.getCurrentSession().saveOrUpdate(promoter);
        log.info("ID:" + promoter.getId().toString());
        return findById(promoter.getId());
    }


    @Override
    public List<Promoter> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Promoter.class);
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @Override
    public Promoter findById(Long aLong) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.load(Promoter.class, aLong);
    }

    @Override
    public void delete(Promoter promoter) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(promoter);
    }

    @Override
    public void deleteById(Long aLong) {
        Session session = this.sessionFactory.getCurrentSession();
        Promoter toDelete = session.load(Promoter.class, aLong);
        if (toDelete != null){
            session.delete(toDelete);
        }
    }

    @Override
    public Integer count() {
        return findAll().size();
    }
}
