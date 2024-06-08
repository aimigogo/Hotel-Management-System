package hms_backend.controller;


import hms_backend.mapper.BaseMapper;
import hms_backend.mapper.StockMapper;
import hms_backend.model.Stock;
import hms_backend.service.BaseService;
import hms_backend.service.StockService;
import hms_backend.transfer.ApiResponse;
import hms_backend.transfer.resource.StockResource;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/stocks")
public class StockController extends BaseController<Stock, StockResource> {
    private StockService stockService;
    private final StockMapper stockMapper;

    @Override
    protected BaseService<Stock, Long> getBaseService() {
        return stockService;
    }

    @Override
    protected BaseMapper<Stock, StockResource> getMapper() {
        return stockMapper;
    }

    @GetMapping("{name}")
    public ResponseEntity<ApiResponse<StockResource>> getItemByName(@PathVariable("name")final String name){
        return ResponseEntity.ok(
                ApiResponse.<StockResource>builder()
                        .data(stockMapper.toResource(stockService.getStockByName(name)))
                        .build());
    }


}
