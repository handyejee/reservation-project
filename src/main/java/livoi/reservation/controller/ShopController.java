package livoi.reservation.controller;

import livoi.reservation.domain.ShopEntity;
import livoi.reservation.dto.AddShopRequest;
import livoi.reservation.security.TokenProvider;
import livoi.reservation.service.ShopService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *  Shop
 */

@Slf4j
@RestController
@AllArgsConstructor
public class ShopController {

    private final ShopService shopService;
    private final TokenProvider tokenProvider;

    /**
     *
     * @param request
     * @return 상점 정보
     */
    @PostMapping("/shop")
    public ResponseEntity<ShopEntity> addShop(@RequestBody AddShopRequest request) {
        log.info("Received request: {}", request);

        ShopEntity savedShop = shopService.addShop(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedShop);
    }


}
