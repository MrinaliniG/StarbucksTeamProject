package starbucks.starbucksteam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import starbucks.starbucksteam.model.Card;
import starbucks.starbucksteam.model.User;


public interface CardRepository extends JpaRepository<Card, Integer> {
	 List<Card> findByUser(User user);
}
