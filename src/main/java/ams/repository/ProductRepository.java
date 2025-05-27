package ams.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ams.DTO.ProductDTO;
import ams.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
	
	List<Product> findByFarmerId(long farmerId);

	@Query("SELECT new com.example.dto.ProductDTO(p.id, p.name, p.description, p.price, p.quantity,p.imageUrl, f.id, f.name) " +
			"FROM Product p JOIN p.farmer f")
    List<ProductDTO> findAllProductDTOs();

    @Query("SELECT new com.example.dto.ProductDTO(p.id, p.name, p.description, p.price, p.quantity,p.imageUrl, f.id, f.name) " +
			"FROM Product p JOIN p.farmer f WHERE f.id = :farmerId")
    List<ProductDTO> findProductsByFarmerId(@Param("farmerId") Long farmerId);

}
