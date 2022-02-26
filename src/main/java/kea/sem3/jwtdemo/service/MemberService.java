package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.dto.MemberRequest;
import kea.sem3.jwtdemo.dto.MemberResponse;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.error.Client4xxException;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class MemberService {
    MemberRepository memberRepository;

    public List<MemberResponse> getMembers() {
       return MemberResponse.getMembersFromEntities(memberRepository.findAll());
    }

    public MemberResponse getMember(String id, boolean all) throws Exception {
        return new MemberResponse(memberRepository.findById(id)
                .orElseThrow(() -> new Client4xxException("No member with this id")), false);
    }

    public MemberResponse addMember(MemberRequest body) {
        Member newMember = memberRepository.save(new Member(body));
        return new MemberResponse(newMember,true);
    }

    public MemberResponse editMember(MemberRequest body, String userName) {
        Member memberToEdit = new Member(body);
        memberToEdit.setUsername(userName);
        return new MemberResponse(memberToEdit,true);
    }

    public void deleteMember(String userName) {
        memberRepository.findById(userName); {
        }
    }
}
