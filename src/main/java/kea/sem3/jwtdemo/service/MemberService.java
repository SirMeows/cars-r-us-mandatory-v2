package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.dto.CarResponse;
import kea.sem3.jwtdemo.dto.MemberResponse;
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

    public MemberResponse getMember(int id, boolean all) throws Exception {
        return new MemberResponse(memberRepository.findById(id).orElseThrow(() -> new Client4xxException("No member with this id")), false);
    }



}
