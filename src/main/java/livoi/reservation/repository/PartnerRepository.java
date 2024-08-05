package livoi.reservation.repository;

import livoi.reservation.domain.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 데이터베이스 에서 데이터를 Partner 엔티티와 연결해주는 인터페이스
 * Partner을 등록, 삭제, 조회, 수정하는 기능을 수행합니다.
 */
@Repository
public interface PartnerRepository extends JpaRepository<PartnerEntity, Integer> {

    // 파트너아이디 존재여부 조회
    Optional<PartnerEntity> findByPartnerId(String partnerId);

    // 파트너번호 존재여부 조회
    Optional<PartnerEntity> findByPartnerNum(int partnerNum);

    // 아이디 중복여부 확인
    boolean existsByPartnerId(String partnerId);

}
