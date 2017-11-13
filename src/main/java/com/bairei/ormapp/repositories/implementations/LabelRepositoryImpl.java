package com.bairei.ormapp.repositories.implementations;

import com.bairei.ormapp.models.Label;
import com.bairei.ormapp.repositories.LabelRepository;
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
public class LabelRepositoryImpl implements LabelRepository {

    private final Logger log = LoggerFactory.getLogger(LabelRepositoryImpl.class);

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
        Long id = (Long) sessionFactory.getCurrentSession().save(label);
        log.info("ID:" + id.toString());
        return findById(id);
    }


    @Override
    @Transactional
    public List<Label> listAll() {
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
        return listAll().size();
    }

}
