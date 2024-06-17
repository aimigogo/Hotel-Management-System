package hms_backend.controller;

import hms_backend.dto.StockDto;
import hms_backend.services.StockService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    //Create Stock item
    @PostMapping("/createStockItem")
    public ResponseEntity<?> createdStockItem(@RequestBody StockDto stockDto){
        try{
            StockDto createdStock=stockService.createStockItem(stockDto);
            return new ResponseEntity<>(createdStock, HttpStatus.OK);
        }catch (EntityExistsException entityExistsException){
            return new ResponseEntity<>("Stock item already exists",HttpStatus.NOT_ACCEPTABLE);
        }catch (Exception e){
            return new ResponseEntity<>("Stock item not created,please try again",HttpStatus.BAD_REQUEST);
        }
    }

    //Update Stock item
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateStockItem(@PathVariable Long id, @RequestBody StockDto stockDto) {
        try {
            StockDto updatedStockItem = stockService.updateStockItem(id, stockDto);
            return new ResponseEntity<>(updatedStockItem, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Stock item not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Stock item update failed, please try again", HttpStatus.BAD_REQUEST);
        }
    }

    //Delete Stock item
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStockItem(@PathVariable Long id){
        stockService.deleteStockItem(id);
        return ResponseEntity.noContent().build();
    }

    //Get List of all Stock items
    @GetMapping("/stocks")
    public ResponseEntity<List<Object>> getAllStockItems(){
        List<Object> stockItems=stockService.getAllStockItems();
        return ResponseEntity.ok(stockItems);
    }
}
