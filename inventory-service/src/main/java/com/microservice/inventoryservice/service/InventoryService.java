package com.microservice.inventoryservice.service;

import com.microservice.inventoryservice.dto.InventoryResponseDto;
import com.microservice.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryResponseDto> isInStock(List<String> skuCode){
        /*log.info("Wait started");
        Thread.sleep(1000);
        log.info("Wait ended");*/
      return inventoryRepository.findBySkuCodeIn(skuCode)
              .stream().map(inventory ->
                  InventoryResponseDto.builder()
                          .skuCode(inventory.getSkuCode())
                          .isInStock(inventory.getQuantity() > 0).build()
              ).collect(Collectors.toList());
    }
}
