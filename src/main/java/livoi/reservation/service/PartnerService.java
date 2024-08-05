package livoi.reservation.service;

import livoi.reservation.model.entity.AuthEntity;
import livoi.reservation.model.entity.PartnerEntity;
import livoi.reservation.model.repository.PartnerRepository;
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
public class PartnerService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final PartnerRepository partnerRepository;

    @Override
    public UserDetails loadUserByUsername(String partnerName) throws UsernameNotFoundException {
        return partnerRepository.findByPartnerId(partnerName)
                .orElseThrow(() -> new UsernameNotFoundException("Partner name not found" + partnerName));

    }

    /**
     * 사용자 회원 가입 로직
     * 아이디를 입력받아 중복여부 확인 후 Member 테이블에 저장
     * @param partner AuthEntity.signUp의 인스턴스
     * @return 인코딩 한 값
     */
    public PartnerEntity registerPartner(AuthEntity.signUp partner){
        boolean exists = this.partnerRepository.existsByPartnerId(partner.getUsername());
        if (exists){
            throw new RuntimeException("Partner name already exists.");
        }

        partner.setPassword(this.passwordEncoder.encode(partner.getPassword()));
        return this.partnerRepository.save(partner.toPartnerEntity());
    }

    /**
     * 비밀번호 인증 메서드
     * 아이디 존재 여부 확인 후 비밀번호 일치여부 확인
     *
     * @param partner AuthEntity.signUp의 인스턴스
     * @return user
     */
    public PartnerEntity authenticate(AuthEntity.signIn partner){
        var user = this.partnerRepository.findByPartnerId(partner.getUsername())
                .orElseThrow(() -> new RuntimeException("Partner name does not exists."));

        if(!this.passwordEncoder.matches(partner.getPassword(), user.getPassword())) {
            throw new RuntimeException("Password does not match.");
        }
        return user;
    }

}
