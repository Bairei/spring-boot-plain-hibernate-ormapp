package com.bairei.ormapp.repositories.implementations;

import com.bairei.ormapp.models.Album;
import com.bairei.ormapp.repositories.AlbumRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
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
        sessionFactory.getCurrentSession().saveOrUpdate(album);
        log.info("ID:" + album.getId().toString());
        return findById(album.getId());
    }


    @Override
    public List<Album> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Album.class);
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @Override
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
        return findAll().size();
    }

    @Override
    public List<Album> findAlbumsByTitleIncluding(String title) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Album.class);
        criteria.add(Restrictions.like("title", title,  MatchMode.ANYWHERE));
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @Override
    public List<Album> findAlbumsByGenreNameEqualsIgnoreCase(String genreName){
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Album.class);
        criteria.createAlias("genre", "albumGenre");
        criteria.add(Restrictions.eq("albumGenre.name", genreName).ignoreCase());
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @Override
    public List<Album> findAlbumsByMembersIncludingMemberNameEqualsIgnoreCase(String name){
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Album.class, "album");
        criteria.createAlias("album.members", "member");
        criteria.add(Restrictions.eq("member.name", name).ignoreCase());
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }
}
