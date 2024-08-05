package livoi.reservation.controller;

import livoi.reservation.domain.ShopEntity;
import livoi.reservation.dto.AddShopRequest;
import livoi.reservation.dto.ShopResponse;
import livoi.reservation.security.TokenProvider;
import livoi.reservation.service.ShopService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  상점 관련된 Shop 컨트롤러
 */

@Slf4j
@RestController
@AllArgsConstructor
public class ShopController {

    private final ShopService shopService;
    private final TokenProvider tokenProvider;

    /**
     * 상점을 등록하는 메서드
     * @param request 등록하고자 하는 상점 정보를 요청으로 받아옵니다.
     * @return 등록한 상점 정보를 반환합니다
     */
    @PostMapping("/shops")
    public ResponseEntity<ShopEntity> addShop(@RequestBody AddShopRequest request) {
        log.info("Received request: {}", request);

        ShopEntity savedShop = shopService.addShop(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedShop);
    }

    /**
     * 상점 리스트를 조회하는 메서드
     * @return 상점 리스트 목록을 반환합니다
     */
    @GetMapping("/shops")
    public ResponseEntity<List<ShopResponse>> findAllShops(){
        List<ShopResponse> shops = shopService.findAll()
                .stream()
                .map(ShopResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(shops);
    }

    /**
     * 상점을 삭제하는 메서드
     * @param shopId 를 기준으로 삭제합니다.
     * @return 삭제 결과를 반환합니다.
     */
    @DeleteMapping("/shop/{shopId}")
    public ResponseEntity<Void> deleteShop(@PathVariable int shopId) {
        shopService.deleteShop(shopId);

        return ResponseEntity.ok()
                .build();
    }


}
