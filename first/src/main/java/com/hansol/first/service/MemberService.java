package com.hansol.first.service;

import com.hansol.first.dto.MemberDto;
import com.hansol.first.entity.Member;

import java.util.List;

public interface MemberService {
    Member saveMember(MemberDto memberDto);
    Member modifyMember(Long memberId, MemberDto memberDto);
    List<Member> findAll();
    Member findMemberById(Long id);
    void deleteMemberById(Long id);
}
