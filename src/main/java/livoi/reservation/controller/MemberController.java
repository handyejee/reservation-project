package livoi.reservation.controller;

import livoi.reservation.model.entity.AuthEntity;
import livoi.reservation.security.TokenProvider;
import livoi.reservation.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor

public class MemberController {

    private final MemberService memberService;
    private final TokenProvider tokenProvider;

    @PostMapping("/auth/signup")
    public ResponseEntity<?> signup(@RequestBody AuthEntity.signUp request){
        var result = this.memberService.registerMember(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<?> signin(@RequestBody AuthEntity.signIn request){
        var member = this.memberService.authenticate(request);
        var token = this.tokenProvider.generateToken(member.getUsername(), member.getRoles());

        return ResponseEntity.ok(token);
    }

}
