package hms_backend.service;

import hms_backend.model.Stock;
import hms_backend.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockServiceImpl extends BaseServiceImpl<Stock> implements StockService{

    private final StockRepository stockRepository;

    protected JpaRepository<Stock,Long> getRepository(){
        return stockRepository;
    }


    @Override
    public Stock create(Stock item) {
        return stockRepository.save(item);
    }

    @Override
    public void update(Stock item) {
        getRepository().save(item);
    }

    @Override
    public Stock getById(Long id) {

        return getRepository().getReferenceById(id);
    }

    @Override
    public void deleteById(Long id) {
        getRepository().deleteById(id);
    }

    @Override
    public void delete(Stock item) {
        getRepository().delete(item);
    }

    @Override
    public Stock getStockByName(String name) {
        return stockRepository.getStockByName(name);
    }

}
