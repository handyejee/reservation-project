package livoi.reservation.service;

import livoi.reservation.domain.PartnerEntity;
import livoi.reservation.domain.ShopEntity;
import livoi.reservation.dto.AddShopRequest;
import livoi.reservation.repository.PartnerRepository;
import livoi.reservation.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 상점 기능을 위한 서비스 로직
 * 상점을 등록, 삭제합니다.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ShopService {

    private final ShopRepository shopRepository;
    private final PartnerRepository partnerRepository;

    public ShopEntity addShop(AddShopRequest request) {

        log.info("PartnerNum from request: {}", request.getPartner().getPartnerNum());

        // PartnerNum을 파트너 데이터베이스에서 조회
        PartnerEntity partner = partnerRepository.findByPartnerNum(request.getPartner().getPartnerNum())
                .orElseThrow(() -> {
                    log.error("Partner number not found: {}", request.getPartner().getPartnerNum());
                    return new RuntimeException("Partner number not found");
                });
        // 새로운 ShopEntity를 생성하고, 조회된 PartnerEntity를 설정
        ShopEntity shop = ShopEntity.builder()
                .shopName(request.getShopName())
                .shopDesc(request.getShopDesc())
                .partner(partner) // 이미 영속 상태의 파트너 엔티티 설정
                .build();

        return shopRepository.save(shop);
    }


}
