package livoi.reservation.model.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private int partner_num; // 파트너번호

    private String shop_name; // 매장명
    private String shop_location; // 상점위치
    private String shop_desc; // 상점설명


}
