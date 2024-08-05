package livoi.reservation.dto;

import livoi.reservation.domain.ShopEntity;
import lombok.Getter;

/**
 * Shop 엔티티에서 상점 목록 조회하는 DTO
 */
@Getter
public class ShopResponse {
    private final int shopId;
    private final String shopDesc;

    public ShopResponse(ShopEntity shopEntity){
        this.shopId = shopEntity.getShopId();
        this.shopDesc = shopEntity.getShopDesc();
    }
}
