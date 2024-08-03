package livoi.reservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "livoi.reservation.model.entity")
public class ReservationProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservationProjectApplication.class, args);
    }

}
