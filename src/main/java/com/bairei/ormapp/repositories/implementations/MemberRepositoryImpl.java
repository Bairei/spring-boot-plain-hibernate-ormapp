package com.bairei.ormapp.repositories.implementations;

import com.bairei.ormapp.models.Member;
import com.bairei.ormapp.repositories.MemberRepository;
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
public class MemberRepositoryImpl implements MemberRepository {
    private final Logger log = LoggerFactory.getLogger(MemberRepositoryImpl.class);

    private SessionFactory sessionFactory;


    public MemberRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveOrUpdate(Member member) {
        sessionFactory.getCurrentSession().saveOrUpdate(member);
    }

    @Override
    public Member save(Member member) {
        Long id = (Long) sessionFactory.getCurrentSession().save(member);
        log.info("ID:" + id.toString());
        return findById(id);
    }

    @Override
    public List<Member> listAll() {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Member.class);
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @Override
    public Member findById(Long aLong) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.load(Member.class, aLong);
    }

    @Override
    public void delete(Member member) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(member);
    }

    @Override
    public void deleteById(Long aLong) {
        Session session = this.sessionFactory.getCurrentSession();
        Member toDelete = session.load(Member.class, aLong);
        if (toDelete != null){
            session.delete(toDelete);
        }
    }

    @Override
    public Integer count() {
        return listAll().size();
    }
}
