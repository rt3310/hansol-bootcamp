package com.hansol.first.mapper;

import com.hansol.first.dto.MemberDto;
import com.hansol.first.entity.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemberMapper {

    Long save(Member member);

    List<Member> findAll();
    Member findById(Long id);

    void update(Member member);

    void deleteById(Long id);
}
