package livoi.reservation.service;

import livoi.reservation.domain.PartnerEntity;
import livoi.reservation.domain.ShopEntity;
import livoi.reservation.dto.AddShopRequest;
import livoi.reservation.repository.PartnerRepository;
import livoi.reservation.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 상점 기능을 위한 서비스 로직
 * 상점을 등록, 삭제하고 조회합니다.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ShopService {

    private final ShopRepository shopRepository;
    private final PartnerRepository partnerRepository;

    /**
     * 상점 DTO에서 상점 정보를 받아 파트너 데이터베이스에서 조회한 후 파트너 정보가 존재하는 경우 상점을 등록합니다.
     * @param request 에서 상점 DTO에 매핑된 정보를 shopEntity를 생성하고 저장
     * @return 등록한 상점 정보를 return 합니다.
     */
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

    /**
     * 상점을 삭제하는 서비스 메서드
     * 상점의 id를 받은 뒤 deleteById 메서드를 이용해 데이터베이스에서 데이터를 삭제합니다.
     * @param shopId 값을 받아서
     */
    public void deleteShop(int shopId){
        shopRepository.deleteById(shopId);
    }

    /**
     * 상점 목록 조회하는 서비스 메서드
     * @return 상점 목록 반환
     */
    public List<ShopEntity> findAll() {
        return shopRepository.findAll();
    }

    /**
     * 상점 상세 조회하는 서비스 메서드
     * @param shopId 를 이용해 데이터베이스에서 조회합니다
     * @return 조회한 글을 반환합니다.
     */
    public ShopEntity findById(int shopId){
        return shopRepository.findById(shopId)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + shopId));
    }
}
