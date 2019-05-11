package starbucks.starbucksteam.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import starbucks.starbucksteam.model.Menu;

import java.util.List;

public interface MenuRepository extends CrudRepository<Menu,Long>{	
	List<Menu> findAllByCategory(String category);
}
