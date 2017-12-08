package com.bairei.ormapp.repositories;

import com.bairei.ormapp.models.Member;

public interface MemberRepository extends GenericRepository<Member, Long> {
    Member findMemberByNameEqualsIgnoreCase(String name);
}
