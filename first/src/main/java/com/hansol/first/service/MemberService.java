package com.hansol.first.service;

import com.hansol.first.dto.MemberDto;
import com.hansol.first.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    Member saveMember(MemberDto memberDto);

    List<Member> findAll();
    Optional<Member> findMemberById(Long id);

    Member modifyMember(Long memberId, MemberDto memberDto);

    void deleteMemberById(Long id);
}
