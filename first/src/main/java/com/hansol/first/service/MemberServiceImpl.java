package com.hansol.first.service;

import com.hansol.first.dto.MemberDto;
import com.hansol.first.entity.Member;
import com.hansol.first.mapper.MemberMapper;
import com.hansol.first.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberMapper memberMapper;
    private final TaskMapper taskMapper;

    @Override
    public Member saveMember(MemberDto memberDto) {
        Member member = memberDto.toMember();
        memberMapper.save(member);
        return member;
    }

    @Override
    public Member modifyMember(Long memberId, MemberDto memberDto) {
        Member member = memberMapper.findById(memberId);
        member.setMemberName(memberDto.getMemberName());
        member.setMemberRank(memberDto.getMemberRank());
        memberMapper.update(member);
        return member;
    }

    @Override
    public List<Member> findAll() {
        return memberMapper.findAll();
    }

    @Override
    public Member findMemberById(Long id) {
        return memberMapper.findById(id);
    }

    @Override
    public void deleteMemberById(Long id) {
        taskMapper.deleteTaskCompanyByMemberId(id);
        taskMapper.deleteTaskCategoryByMemberId(id);
        taskMapper.deleteTaskPhoneByMemberId(id);
        memberMapper.deleteById(id);
    }
}
