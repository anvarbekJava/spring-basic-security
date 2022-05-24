package uz.pdp.basicsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.basicsecurity.entity.Product;
import uz.pdp.basicsecurity.payload.ApiResponce;
import uz.pdp.basicsecurity.payload.ProductDto;
import uz.pdp.basicsecurity.service.ProductService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PreAuthorize(value = "hasAuthority('ADD_PRODUCT')")
    @PostMapping("/add")
    public HttpEntity<?> add(@Valid @RequestBody ProductDto dto){
        ApiResponce apiResponce = productService.add(dto);
        return ResponseEntity.status(apiResponce.isSuccess()?200:409).body(apiResponce);
    }
    @PreAuthorize(value = "hasAuthority('EDET_PRODUCT')")
    @PutMapping("/edet/{id}")
    public HttpEntity<?> edet(@Valid @RequestBody ProductDto dto, @PathVariable Integer id){
        ApiResponce apiResponce = productService.edet(dto, id);
        return ResponseEntity.status(apiResponce.isSuccess()?201:409).body(apiResponce);
    }
    @PreAuthorize(value = "hasAuthority('READ_ONE_PRODUCT')")
    @GetMapping("/getOne/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        Product product = productService.getOne(id);
        return ResponseEntity.ok(product);
    }
    @PreAuthorize(value = "hasAuthority('READ_ALL_PRODUCT')")
    @GetMapping("/getAll")
    public HttpEntity<?> getAll(){
        List<Product> productList = productService.getAll();
        return ResponseEntity.ok(productList);
    }
    @PreAuthorize(value = "hasAuthority('DELETE_PRODUCT')")
    @DeleteMapping("/delet/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponce apiResponce = productService.delet(id);
        return ResponseEntity.status(apiResponce.isSuccess()?200:409).body(apiResponce);
    }
}
