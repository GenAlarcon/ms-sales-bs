package cl.duoc.ms_sales_bs.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cl.duoc.ms_sales_bs.model.dto.ProductDTO;

@FeignClient(name = "ms-product-bs", url = "http://localhost:8281")
public interface ProductBsFeignClient {

    @GetMapping("/api/product/{id}")
    public ResponseEntity<ProductDTO> findProductById(@PathVariable("id") Long id);

}
