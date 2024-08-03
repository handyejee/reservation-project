package livoi.reservation.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "partner")

/**
 * 예약 시스템의 사장님을 나타내는 클래스
 * Spring Security에서 제공하는 UserDetails 인터페이스를 구현.
 */
public class PartnerEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "partner_num")
    private int partnerNum;

    @Column(name = "partner_id")
    private String partnerId;

    @Column(name = "partner_pwd")
    private String partnerPwd;

    @OneToMany(mappedBy = "partner")
    private List<ShopEntity> shops;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.partnerPwd;
    }

    @Override
    public String getUsername() {
        return this.partnerId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

}
