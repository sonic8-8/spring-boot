package hello.order.gauge;

import hello.order.OrderService;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.binder.MeterBinder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class StockConfigV2 {

    @Bean
    public MeterBinder stockSize(OrderService orderSerivce) {
        return registry -> Gauge.builder("my.stock", orderSerivce, service -> {
            log.info("stock gauge call");
            return service.getStock().get();
        }).register(registry);
    }
}
