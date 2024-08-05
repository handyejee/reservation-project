package livoi.reservation.dto;

import livoi.reservation.domain.PartnerEntity;
import livoi.reservation.domain.ShopEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 상점 DTO
 * 상점 등록시 필요한 상점명, 상점 설명과 파트너 번호를 Shop Entity와 매핑하는 DTO
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddShopRequest {

    private String shopId;
    private String shopName;
    private String shopDesc;
    private PartnerRequest partner;

    public ShopEntity toEntity(){
        return ShopEntity.builder()
                .shopName(shopName)
                .shopDesc(shopDesc)
                .partner(partner.toEntity()) // PartnerRequest를 PartnerEntity로 변환
                .build();
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class PartnerRequest {
        private int partnerNum;
        private String role;

        public PartnerEntity toEntity() {
            return PartnerEntity.builder()
                    .partnerNum(partnerNum)
                    .role(role)
                    .build();
        }
    }

}
