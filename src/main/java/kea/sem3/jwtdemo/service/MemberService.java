package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.repositories.MemberRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class MemberService {
    MemberRepository memberRepository;
}
