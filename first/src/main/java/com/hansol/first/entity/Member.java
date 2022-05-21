package com.hansol.first.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class Member {
    private Long id;
    private String memberName;
    private String memberRank;
    private String phone;

    public Member(String memberName, String memberRank, String phone) {
        this.memberName = memberName;
        this.memberRank = memberRank;
        this.phone = phone;
    }
}
