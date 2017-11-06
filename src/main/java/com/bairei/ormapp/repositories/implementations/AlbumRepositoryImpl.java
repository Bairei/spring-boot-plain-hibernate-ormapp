package com.bairei.ormapp.repositories.implementations;

import com.bairei.ormapp.models.Album;
import com.bairei.ormapp.repositories.AlbumRepository;
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
public class AlbumRepositoryImpl implements AlbumRepository {

    private final Logger log = LoggerFactory.getLogger(AlbumRepositoryImpl.class);

    private SessionFactory sessionFactory;


    public AlbumRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveOrUpdate(Album album) {
        sessionFactory.getCurrentSession().saveOrUpdate(album);
    }

    @Override
    public Album save(Album album) {
        Long id = (Long) sessionFactory.getCurrentSession().save(album);
        log.info("ID:" + id.toString());
        return findById(id);
    }


    @Override
    @Transactional
    public List<Album> listAll() {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Album.class);
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @Override
    @Transactional
    public Album findById(Long aLong) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.load(Album.class, aLong);
    }

    @Override
    public void delete(Album album) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(album);
    }

    @Override
    public void deleteById(Long aLong) {
        Session session = this.sessionFactory.getCurrentSession();
        Album toDelete = session.load(Album.class, aLong);
        if (toDelete != null){
            session.delete(toDelete);
        }
    }

    @Override
    public Integer count() {
        return listAll().size();
    }
}
