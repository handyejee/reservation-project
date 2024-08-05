package livoi.reservation.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name= "shop")
@Getter
@ToString
@NoArgsConstructor // argument 없는 생성자
public class ShopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private int shopId; // 매장번호

    @Column(name = "shop_name")
    private String shopName; // 매장명

    @Column(name = "shop_desc")
    private String shopDesc; // 상점설명

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "partner_num") // FK 지정
    private PartnerEntity partner;

    @Builder
    public ShopEntity(String shopName, String shopDesc, PartnerEntity partner) {
        this.shopName = shopName;
        this.shopDesc = shopDesc;
        this.partner = partner;
    }
}
