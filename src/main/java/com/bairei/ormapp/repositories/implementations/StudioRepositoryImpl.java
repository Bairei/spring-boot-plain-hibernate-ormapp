package com.bairei.ormapp.repositories.implementations;

import com.bairei.ormapp.models.Studio;
import com.bairei.ormapp.repositories.StudioRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@Slf4j
public class StudioRepositoryImpl implements StudioRepository {

    private SessionFactory sessionFactory;


    public StudioRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveOrUpdate(Studio studio) {
        sessionFactory.getCurrentSession().saveOrUpdate(studio);
    }

    @Override
    public Studio save(Studio studio) {
        sessionFactory.getCurrentSession().saveOrUpdate(studio);
        // log.info("ID:" + studio.getId().toString());
        return findById(studio.getId());
    }


    @Override
    public List<Studio> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Studio.class);
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @Override
    public Studio findById(Long aLong) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.load(Studio.class, aLong);
    }

    @Override
    public void delete(Studio studio) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(studio);
    }

    @Override
    public void deleteById(Long aLong) {
        Session session = this.sessionFactory.getCurrentSession();
        Studio toDelete = session.load(Studio.class, aLong);
        if (toDelete != null){
            session.delete(toDelete);
        }
    }

    @Override
    public Integer count() {
        return findAll().size();
    }

    @Override
    public List<Studio> findStudiosByLocationPlaceIncluding(String place) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Studio.class, "studio");
        criteria.createAlias("studio.location", "location");
        criteria.add(Restrictions.like("location.place", place, MatchMode.ANYWHERE));
        return criteria.list();
    }

    @Override
    public List<Studio> findStudiosByLocationPlaceEqualsIgnoreCase(String place) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Studio.class, "studio");
        criteria.createAlias("studio.location", "location");
        criteria.add(Restrictions.like("location.place", place).ignoreCase());
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }
}
