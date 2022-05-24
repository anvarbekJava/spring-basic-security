package uz.pdp.basicsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.basicsecurity.entity.Product;
import uz.pdp.basicsecurity.payload.ApiResponce;
import uz.pdp.basicsecurity.payload.ProductDto;
import uz.pdp.basicsecurity.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public ApiResponce add(ProductDto dto) {
        if (productRepository.existsByNameAndPrice(dto.getName(), dto.getPrice()))
            return new ApiResponce("Product mavjud boshqa kiriting", false);
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        productRepository.save(product);
        return new ApiResponce("Saqlandi", true);
    }

    public ApiResponce edet(ProductDto dto, Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return new ApiResponce("Topilmadi", false);
        Product product = optionalProduct.get();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        productRepository.save(product);
        return new ApiResponce("Eded product", true);
    }

    public Product getOne(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return null;
        Product product = optionalProduct.get();
        return product;
    }

    public List<Product> getAll() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    public ApiResponce delet(Integer id) {
        try {
            productRepository.deleteById(id);
            return new ApiResponce("Deleted product", true);
        }catch (Exception e){
            return new ApiResponce("No deleted product", false);
        }
    }
}
