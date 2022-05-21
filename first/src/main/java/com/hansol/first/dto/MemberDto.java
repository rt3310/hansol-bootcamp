package com.hansol.first.dto;

import com.hansol.first.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class MemberDto {
    @NotBlank
    private String memberName;
    @NotBlank
    private String memberRank;
    @NotBlank
    private String phone;

    public Member toMember() {
        return new Member(memberName, memberRank, phone);
    }
}
