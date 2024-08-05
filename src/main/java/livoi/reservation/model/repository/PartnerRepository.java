package livoi.reservation.model.repository;

import livoi.reservation.model.entity.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartnerRepository extends JpaRepository<PartnerEntity, Integer> {

    Optional<PartnerEntity> findByPartnerId(String partnerId);

    // 아이디 중복여부 확인
    boolean existsByPartnerId(String partnerId);


}
