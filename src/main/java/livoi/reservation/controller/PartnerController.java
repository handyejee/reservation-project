package livoi.reservation.controller;

import livoi.reservation.model.entity.AuthEntity;
import livoi.reservation.security.TokenProvider;
import livoi.reservation.service.PartnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/partner")
@RequiredArgsConstructor
public class PartnerController {

    private final PartnerService partnerService;
    private final TokenProvider tokenProvider;

    @PostMapping("/auth/signup")
    public ResponseEntity<?> signup(@RequestBody AuthEntity.signUp request){
        var result = this.partnerService.registerPartner(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<?> signin(@RequestBody AuthEntity.signIn request){
        var partner = this.partnerService.authenticate(request);
        var token = this.tokenProvider.generateToken(partner.getUsername(), partner.getRoles());

        return ResponseEntity.ok(token);
    }



}
