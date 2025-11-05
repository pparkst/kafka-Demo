package com.pparkst.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pparkst.api.domain.Member;
import com.pparkst.api.enums.MemberEnum.Status;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    public abstract List<Member> findByStatus(Status status);
}
