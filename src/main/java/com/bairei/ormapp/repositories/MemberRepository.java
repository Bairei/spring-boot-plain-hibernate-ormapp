package com.bairei.ormapp.repositories;

import com.bairei.ormapp.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends GenericRepository<Member, Long> {
}
