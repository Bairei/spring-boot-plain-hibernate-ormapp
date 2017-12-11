package com.bairei.ormapp.repositories.implementations;

import com.bairei.ormapp.models.Label;
import com.bairei.ormapp.repositories.LabelRepository;
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
public class LabelRepositoryImpl implements LabelRepository {

    private SessionFactory sessionFactory;


    public LabelRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveOrUpdate(Label label) {
        sessionFactory.getCurrentSession().saveOrUpdate(label);
    }

    @Override
    public Label save(Label label) {
        sessionFactory.getCurrentSession().saveOrUpdate(label);
        // log.info("ID:" + label.getId().toString());
        return findById(label.getId());
    }


    @Override
    public List<Label> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Label.class);
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @Override
    public Label findById(Long aLong) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.load(Label.class, aLong);
    }

    @Override
    public void delete(Label label) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(label);
    }

    @Override
    public void deleteById(Long aLong) {
        Session session = this.sessionFactory.getCurrentSession();
        Label toDelete = session.load(Label.class, aLong);
        if (toDelete != null){
            session.delete(toDelete);
        }
    }

    @Override
    public Integer count() {
        return findAll().size();
    }

}
