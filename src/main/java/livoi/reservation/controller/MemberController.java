package livoi.reservation.controller;

import livoi.reservation.dto.AuthUserRequest;
import livoi.reservation.security.TokenProvider;
import livoi.reservation.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@Slf4j
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor

public class MemberController {

    private final MemberService memberService;
    private final TokenProvider tokenProvider;

    @PostMapping("/auth/signup")
    public ResponseEntity<?> signup(@RequestBody AuthUserRequest.signUp request){
        var result = this.memberService.registerMember(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<?> signin(@RequestBody AuthUserRequest.signIn request){
        var member = this.memberService.authenticate(request);
        var token = this.tokenProvider.generateToken(member.getUsername(), Collections.singletonList(member.getRole()));

        return ResponseEntity.ok(token);
    }

}
