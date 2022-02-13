package kea.sem3.jwtdemo.api;

import kea.sem3.jwtdemo.dto.MemberRequest;
import kea.sem3.jwtdemo.dto.MemberResponse;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/members")
public class MemberController {
    MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    // ADMIN
    @GetMapping
    public List<MemberResponse> getMembers() {
        return memberService.getMembers();
    }
    // USER only themselves, ADMIN all details
    @GetMapping("/{id}")
    public MemberResponse getMember(@PathVariable String id)  throws Exception {
        return memberService.getMember(id, false);
    }
    // USER
    @PutMapping
    public MemberResponse addMember(@RequestBody MemberRequest body) {
        return memberService.addMember(body);
    }
    // USER
    @PatchMapping("/{id}")
    public MemberResponse editMember(@RequestBody MemberRequest body, @PathVariable String id) {
        return memberService.editMember(body, id);
    }
    // USER + ADMIN
    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable String id) {
        memberService.deleteMember(id);
    }
}
