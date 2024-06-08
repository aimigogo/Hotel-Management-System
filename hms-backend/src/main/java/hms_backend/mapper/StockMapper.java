package hms_backend.mapper;


import hms_backend.model.Stock;
import hms_backend.transfer.resource.StockResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",config = IgnoreUnmappedMapperConfig.class)
public interface StockMapper extends BaseMapper<Stock, StockResource>{

}
