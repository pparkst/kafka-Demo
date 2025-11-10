package com.pparkst.consumer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pparkst.consumer.domain.Member;

@Repository
public interface MembersRepository extends JpaRepository<Member, Long> {

    public abstract Optional<Member> findByAccount(String account);
}