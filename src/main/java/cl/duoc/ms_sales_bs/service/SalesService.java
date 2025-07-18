package cl.duoc.ms_sales_bs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.ms_sales_bs.clients.ProductBsFeignClient;
import cl.duoc.ms_sales_bs.clients.SalesDbFeignClient;
import cl.duoc.ms_sales_bs.model.dto.ProductDTO;
import cl.duoc.ms_sales_bs.model.dto.SalesDTO;
import cl.duoc.ms_sales_bs.model.dto.SalesDetailDTO;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class SalesService {

    @Autowired
    SalesDbFeignClient salesDbFeignClient;

    @Autowired
    ProductBsFeignClient productBsFeignClient;


     public SalesDTO findSalesById(Long id){
        SalesDTO salesDTO = salesDbFeignClient.findSalesById(id).getBody();
     
        for(SalesDetailDTO salesDetailDTO: salesDTO.getSalesDetailDtoList()){
            Long idProducto = salesDetailDTO.getProduct().getId();
            ProductDTO product = productBsFeignClient.findProductById(idProducto).getBody();
            salesDetailDTO.setProduct(product);
        }
        return salesDTO;
     }

     public SalesDTO insertSale(SalesDTO salesDTO){

        SalesDTO dto = salesDbFeignClient.insertSales(salesDTO).getBody();
        
        for(SalesDetailDTO salesDetailDTO: dto.getSalesDetailDtoList()){
            Long idProducto = salesDetailDTO.getProduct().getId();
            ProductDTO product = productBsFeignClient.findProductById(idProducto).getBody();
            salesDetailDTO.setProduct(product);
        }
        return dto;    
    }
}
