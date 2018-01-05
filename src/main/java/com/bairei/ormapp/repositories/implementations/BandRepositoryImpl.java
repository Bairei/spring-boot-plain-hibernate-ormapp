package com.bairei.ormapp.repositories.implementations;

import com.bairei.ormapp.models.Band;
import com.bairei.ormapp.repositories.BandRepository;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class BandRepositoryImpl implements BandRepository{

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
        sessionFactory.getCurrentSession().saveOrUpdate(band);
        // log.info("ID:" + band.getId().toString());
        return findById(band.getId());
    }


    @Override
    public List<Band> findAll() {
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
        return findAll().size();
    }

    @Override
    public List<Band> findBandsByGenreNameEqualsIgnoreCase(String genreName){
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Band.class);
        criteria.createAlias("genre", "bandGenre");
        criteria.add(Restrictions.eq("bandGenre.name", genreName).ignoreCase());
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @Override
    public List<Band> findBandsByMembersIncludingMemberNameEqualsIgnoreCase(String name) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Band.class, "band");
        criteria.createAlias("band.members", "member");
        criteria.add(Restrictions.eq("member.name", name).ignoreCase());
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }
}
