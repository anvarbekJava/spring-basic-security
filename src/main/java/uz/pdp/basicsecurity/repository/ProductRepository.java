package uz.pdp.basicsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.basicsecurity.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsByNameAndPrice(String name, Double price);
}
