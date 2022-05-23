package com.hansol.first.dto;

import com.hansol.first.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class MemberDto {
    @NotEmpty
    private String memberName;
    @NotNull
    private String memberRank;
    @NotNull
    private String phone;

    public Member toMember() {
        return new Member(memberName, memberRank, phone);
    }
}
