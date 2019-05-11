package starbucks.starbucksteam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import starbucks.starbucksteam.model.Menu;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu,Long>{	
	List<Menu> findAllByCategory(String category);
}
