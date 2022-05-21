package com.hansol.first.controller;

import com.hansol.first.dto.MemberDto;
import com.hansol.first.entity.Member;
import com.hansol.first.response.Response;
import com.hansol.first.service.MemberService;
import com.hansol.first.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final ResponseService responseService;

    @GetMapping("/members")
    public Response<List<Member>> getMembers() {
        return responseService.getResult("members", memberService.findAll());
    }

    @GetMapping("/member/{id}")
    public Response<Member> getMember(@PathVariable Long id) {
        return responseService.getResult("", memberService.findMemberById(id).orElse(null));
    }

    @PostMapping("/member")
    public Response<Member> saveMember(@RequestBody @Validated MemberDto memberDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return responseService.getFailResult("valid");
        }
        return responseService.getResult("", memberService.saveMember(memberDto));
    }

    @PutMapping("/member/{id}")
    public Response<Member> modifyMember(@PathVariable Long id, @RequestBody @Validated MemberDto memberDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return responseService.getFailResult("valid");
        }
        return responseService.getResult("", memberService.modifyMember(id, memberDto));
    }

    @DeleteMapping("/member/{id}")
    public Response deleteMember(@PathVariable Long id) {
        try {
            memberService.deleteMemberById(id);
        } catch (Exception e) {
            return responseService.getFailResult("member delete");
        }
        return responseService.getSuccessResult();
    }
}
