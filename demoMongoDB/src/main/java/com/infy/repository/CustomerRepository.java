package com.infy.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.infy.collection.Customer;

public interface CustomerRepository extends MongoRepository<Customer, Long> {
	Optional<Customer> findByEmail(String email);

	List<Customer> findByEmailNull();

	Optional<Customer> findByNameAndEmail(String name, String email);

	@Query(value = "{}", sort = "{'dob':-1}")
	Page<Customer> findAllOrderedByDob(Pageable pageable);
}
