package com.bairei.ormapp.repositories.implementations;

import com.bairei.ormapp.models.Genre;
import com.bairei.ormapp.repositories.GenreRepository;
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
public class GenreRepositoryImpl implements GenreRepository {

    private final Logger log = LoggerFactory.getLogger(GenreRepositoryImpl.class);

    private SessionFactory sessionFactory;

    public GenreRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveOrUpdate(Genre genre) {
        sessionFactory.getCurrentSession().saveOrUpdate(genre);
    }

    @Override
    public Genre save(Genre genre) {
        Long id = (Long) sessionFactory.getCurrentSession().save(genre);
        log.info("ID:" + id.toString());
        return findById(id);
    }


    @Override
    @Transactional
    public List<Genre> listAll() {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Genre.class);
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @Override
    public Genre findById(Long aLong) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.load(Genre.class, aLong);
    }

    @Override
    public void delete(Genre genre) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(genre);
    }

    @Override
    public void deleteById(Long aLong) {
        Session session = this.sessionFactory.getCurrentSession();
        Genre toDelete = session.load(Genre.class, aLong);
        if (toDelete != null){
            session.delete(toDelete);
        }
    }

    @Override
    public Integer count() {
        return listAll().size();
    }
}
