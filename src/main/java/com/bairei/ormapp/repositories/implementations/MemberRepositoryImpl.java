package com.bairei.ormapp.repositories.implementations;

import com.bairei.ormapp.models.Member;
import com.bairei.ormapp.repositories.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@Slf4j
public class MemberRepositoryImpl implements MemberRepository {

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
        sessionFactory.getCurrentSession().saveOrUpdate(member);
        // log.info("ID:" + member.getId().toString());
        return findById(member.getId());
    }

    @Override
    public List<Member> findAll() {
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
        return findAll().size();
    }

    @Override
    public Member findMemberByNameEqualsIgnoreCase(String name) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Member.class);
        criteria.add(Restrictions.eq("name", name).ignoreCase());
        return (Member) criteria.uniqueResult();
    }
}
