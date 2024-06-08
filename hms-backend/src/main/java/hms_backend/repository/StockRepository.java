package hms_backend.repository;

import hms_backend.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
@Transactional
public interface StockRepository extends JpaRepository<Stock,Long> {
    Stock getStockByName(final String name);

}
