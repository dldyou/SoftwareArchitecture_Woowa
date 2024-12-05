package sa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sa.domain.Delivery;
import sa.domain.Order;
import sa.domain.Store;
import sa.dto.DeliveryAddDto;
import sa.dto.DeliveryResDto;
import sa.dto.SimpleResDto;
import sa.service.DeliveryService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/delivery/users/{userId}/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping(path = "reqeuest")
    public SimpleResDto requestDelivery(@PathVariable Long userId,
                                        @RequestBody DeliveryAddDto deliveryAddDto) {

        Long deliveryId = deliveryService.requestDelivery(userId, deliveryAddDto);

        return new SimpleResDto(deliveryId);
    }

    @DeleteMapping(path = "{deliveryId}")
    public void cancelDelivery(@PathVariable Long userId,
                               @PathVariable Long deliveryId) {

        deliveryService.cancelDelivery(userId, deliveryId);
    }

    @GetMapping(path = "{deliveryId}")
    public DeliveryResDto getDelivery(@PathVariable Long userId,
                                      @PathVariable Long deliveryId) {

        DeliveryResDto deliveryResDto = deliveryService.getDelivery(userId, deliveryId);

        return deliveryResDto; // return delivery result
    }

    @GetMapping(path = "wait")
    public List<DeliveryResDto> getWaitDeliveryList(@PathVariable Long userId) {

        List<DeliveryResDto> deliveryResDtoList = deliveryService.getWaitDeliveryList(userId);

        return deliveryResDtoList; // return delivery result
    }

    @PostMapping(path = "{deliveryId}/accept")
    public SimpleResDto acceptDelivery(@PathVariable Long userId,
                                       @PathVariable Long deliveryId) {

        deliveryService.acceptDelivery(userId, deliveryId);

        return new SimpleResDto(deliveryId);
    }

    @PostMapping(path = "{deliveryId}/deny")
    public SimpleResDto denyDelivery(@PathVariable Long userId,
                                     @PathVariable Long deliveryId) {

        deliveryService.denyDelivery(userId, deliveryId);

        return new SimpleResDto(deliveryId);
    }

    @PostMapping(path = "{deliveryId}/done")
    public SimpleResDto doneDelivery(@PathVariable Long userId,
                                     @PathVariable Long deliveryId) {

        deliveryService.doneDelivery(userId, deliveryId);

        return new SimpleResDto(deliveryId);
    }
}
