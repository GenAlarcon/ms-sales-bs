package cl.duoc.ms_sales_bs.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cl.duoc.ms_sales_bs.model.dto.SalesDTO;

@FeignClient(name = "ms-sales-db-1", url = "http://localhost:8281")
public interface SalesDbFeignClient {

    @GetMapping("/api/sales/{id}")
    public ResponseEntity<SalesDTO> findSalesById(@PathVariable("id") Long id);

    @PostMapping("/api/sales")
    public ResponseEntity<SalesDTO> insertSales(@RequestBody SalesDTO salesDTO);
}
