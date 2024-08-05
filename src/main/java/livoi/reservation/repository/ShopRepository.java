package livoi.reservation.repository;

import livoi.reservation.domain.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Shop(상점)을 데이터베이스에서 ShopEntity를 통해 등록, 삭제, 수정, 조회 합니다.
 */

@Repository
public interface ShopRepository extends JpaRepository<ShopEntity, Integer> {


}
