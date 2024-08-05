package livoi.reservation.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 예약 시스템의 사용자를 나타내는 클래스.
 * Spring Security에서 제공하는 UserDetails 인터페이스를 구현.
 *
 * <p>사용 예시:</p>
 * <pre>
 *     UserEntity user = new UserEntity("john_doe", "password123", List.of("ROLE_USER"));
 * </pre>
 *
 * @see org.springframework.security.core.userdetails.UserDetails
 */

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
public class MemberEntity implements UserDetails {

    @Id
    @Column(name = "user_id")
    private String userId;

    private String userPwd;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    /**
     * 사용자에게 부여된 권한을 반환. 이 메서드는 UserDetails 인터페이스의 일부.
     *
     * @return 부여된 권한의 컬렉션.
     */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.userId;
    }

    @Override
    public String getUsername() {
        return this.userId;
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
