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

    @GetMapping
    public List<MemberResponse> getMembers() {
        return memberService.getMembers();
    }
    @GetMapping("/{id}")
    public MemberResponse getMember(@PathVariable String userName)  throws Exception {
        return memberService.getMember(userName, false);
    }

    @PostMapping
    public MemberResponse addMember(@RequestBody MemberRequest body) {
        return memberService.addMember(body);
    }
}
