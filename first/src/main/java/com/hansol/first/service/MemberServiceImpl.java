package com.hansol.first.service;

import com.hansol.first.dto.MemberDto;
import com.hansol.first.entity.Member;
import com.hansol.first.mapper.MemberMapper;
import com.hansol.first.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<Member> findAll() {
        return memberMapper.findAll();
    }

    @Override
    public Optional<Member> findMemberById(Long id) {
        return findAll().stream()
                .filter(m -> m.getId() == id)
                .findAny();
    }

    @Override
    public Member modifyMember(Long memberId, MemberDto memberDto) {
        Member member = memberMapper.findById(memberId);
        if (member == null) {
            throw new IllegalStateException("member not exist");
        }
        member.setMemberName(memberDto.getMemberName());
        member.setMemberRank(memberDto.getMemberRank());
        member.setPhone(memberDto.getPhone());
        memberMapper.update(member);
        return member;
    }

    @Override
    public void deleteMemberById(Long id) {
        taskMapper.updateTaskMemberToNull(id);
        memberMapper.deleteById(id);
    }
}
