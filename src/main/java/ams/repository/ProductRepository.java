package ams.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ams.model.Product;
import ams.view.ProductView;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Query("SELECT p.id AS id, p.name AS name, p.description AS description, p.price AS price, " +
		       "p.quantity AS quantity, p.imageUrl AS imageUrl, p.farmer.id AS farmerId,p.farmer.name as farmerName " +
		       "FROM Product p WHERE p.id = ?1")
	Optional<ProductView> findByIdView(Long id);
	
	@Query("SELECT p.id AS id, p.name AS name, p.description AS description, p.price AS price, " +
		       "p.quantity AS quantity, p.imageUrl AS imageUrl, p.farmer.id AS farmerId,p.farmer.name as farmerName " +
		       "FROM Product p")
	List<ProductView> findAllView();
	
	@Query("SELECT p.id AS id, p.name AS name, p.description AS description, p.price AS price, " +
		       "p.quantity AS quantity, p.imageUrl AS imageUrl, p.farmer.id AS farmerId,p.farmer.name as farmerName " +
		       "FROM Product p WHERE p.farmer.id = :fid")
	List<ProductView> findProductsByFarmerId(@Param("fid") Long fid);
	
	

}
