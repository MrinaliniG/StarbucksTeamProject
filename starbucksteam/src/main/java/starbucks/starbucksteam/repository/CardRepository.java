package starbucks.starbucksteam.repository;

import org.springframework.data.repository.CrudRepository;
import starbucks.starbucksteam.model.Card;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CardRepository extends CrudRepository<Card, Integer> {

}
