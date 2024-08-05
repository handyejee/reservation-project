package livoi.reservation.model.repository;

import livoi.reservation.model.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    // userId 기준으로 회원정보 찾기
    Optional<MemberEntity> findByUserId(String userId);

    // 아이디 중복여부 확인
    boolean existsByUserId(String userId);
}
