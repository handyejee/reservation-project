package livoi.reservation.service;

import livoi.reservation.dto.AuthUserRequest;
import livoi.reservation.domain.MemberEntity;
import livoi.reservation.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByUserId(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found" + username));

    }

    /**
     * 사용자 회원 가입 로직
     * 아이디를 입력받아 중복여부 확인 후 Member 테이블에 저장
     * @param member AuthEntity.signUp의 인스턴스
     * @return 인코딩 한 값
     */
    public MemberEntity registerMember(AuthUserRequest.signUp member){
        boolean exists = this.memberRepository.existsByUserId(member.getUsername());
        if (exists){
            throw new RuntimeException("User name already exists.");
        }

        member.setPassword(this.passwordEncoder.encode(member.getPassword()));
        return this.memberRepository.save(member.toMemberEntity());
    }

    /**
     * 비밀번호 인증 메서드
     * 아이디 존재 여부 확인 후 비밀번호 일치여부 확인
     * @param member AuthEntity.signUp의 인스턴스
     * @return user
     */
    public MemberEntity authenticate(AuthUserRequest.signIn member){
        var user = this.memberRepository.findByUserId(member.getUsername())
                .orElseThrow(() -> new RuntimeException("User name does not exists."));

        if(!this.passwordEncoder.matches(member.getPassword(), user.getPassword())) {
            throw new RuntimeException("Password does not match.");
        }
        return user;
    }
}
