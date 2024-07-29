package com.infy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.mongodb.core.query.Query;
import com.infy.collection.Customer;
import com.infy.collection.SequenceId;
import com.infy.dto.CustomerDTO;
import com.infy.exception.InfyBankException;
import com.infy.repository.CustomerRepository;

@Service(value = "customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private MongoOperations mongoOperations;

	private static final String HOSTING_SEQ_KEY = "hosting";

	@Override
	public long getNextSequenceId(String key) throws InfyBankException {
		// TODO Auto-generated method stub
		Query query = new Query(Criteria.where("_id").is(key));
		Update update = new Update();
		update.inc("seq", 1);
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);
		SequenceId sequenceId = mongoOperations.findAndModify(query, update, options, SequenceId.class);
		if (sequenceId == null) {
			throw new InfyBankException("Unable to get sequence id for key : " + key);
		}
		return sequenceId.getSeq();
	}

	@Override
	public Long addCustomer(CustomerDTO customerDTO) throws InfyBankException {
		// TODO Auto-generated method stub
		Optional<Customer> optional = customerRepository.findByEmail(customerDTO.getEmail());
		if (optional.isPresent()) {
			throw new InfyBankException("Service.CUSTOMER_FOUND");
		}
		Customer customer = new Customer();
		customer.setId(getNextSequenceId(HOSTING_SEQ_KEY));
		customer.setName(customerDTO.getName());
		customer.setEmail(customerDTO.getEmail());
		customer.setType(customerDTO.getType());
		customer.setDob(customerDTO.getDob());
		customerRepository.save(customer);
		return customer.getId();
	}

	@Override
	public CustomerDTO getCustomer(Long CustomerId) throws InfyBankException {
		// TODO Auto-generated method stub
		Optional<Customer> optional = customerRepository.findById(CustomerId);
		Customer customer = optional.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(customer.getId());
		customerDTO.setName(customer.getName());
		customerDTO.setName(customer.getName());
		customerDTO.setType(customer.getType());
		customerDTO.setDob(customer.getDob());
		return customerDTO;
	}

	@Override
	public List<CustomerDTO> getAllCustomers() throws InfyBankException {
		// TODO Auto-generated method stub
		Iterable<Customer> customersIterable = customerRepository.findAll();
		List<CustomerDTO> customerDTOs = new ArrayList<CustomerDTO>();
		customersIterable.forEach(customer -> {
			CustomerDTO customerDto = new CustomerDTO();
			customerDto.setId(customer.getId());
			customerDto.setName(customer.getName());
			customerDto.setEmail(customer.getEmail());
			customerDto.setDob(customer.getDob());
			customerDto.setType(customer.getType());
			customerDTOs.add(customerDto);
		});
		return customerDTOs;
	}

	@Override
	public void updateCustomer(Long CustomerId, String email) throws InfyBankException {
		// TODO Auto-generated method stub
		Optional<Customer> optional = customerRepository.findById(CustomerId);
		Customer customer = optional.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
		customer.setEmail(email);
		customerRepository.save(customer);
	}

	@Override
	public void deleteCustomer(Long customerId) throws InfyBankException {
		// TODO Auto-generated method stub
		Optional<Customer> optional = customerRepository.findById(customerId);
		Customer customer = optional.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
		customerRepository.save(customer);
	}

	@Override
	public List<CustomerDTO> findByEmailNull() throws InfyBankException {
		// TODO Auto-generated method stub
		List<Customer> customers = customerRepository.findByEmailNull();
		List<CustomerDTO> customerDTOs = new ArrayList<CustomerDTO>();
		if (customers.isEmpty()) {
			throw new InfyBankException("Service.CUSTOMERS_NOT_FOUND");
		}
		customers.forEach(customer -> {
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setId(customer.getId());
			customerDTO.setName(customer.getName());
			customerDTO.setEmail(customer.getEmail());
			customerDTO.setDob(customer.getDob());
			customerDTO.setType(customer.getType());
			customerDTOs.add(customerDTO);
		});
		return customerDTOs;
	}

	@Override
	public CustomerDTO getCustomerByNameAndEmail(String name, String email) throws InfyBankException {
		// TODO Auto-generated method stub
		Optional<Customer> optional = customerRepository.findByNameAndEmail(name, email);
		Customer customer = optional.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(customer.getId());
		customerDTO.setName(customer.getName());
		customerDTO.setEmail(customer.getEmail());
		customerDTO.setDob(customer.getDob());
		customer.setType(customer.getType());
		return customerDTO;
	}

	@Override
	public List<CustomerDTO> getAllCustomerOrderByDob(Integer pageNO, Integer pageSize) throws InfyBankException {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNO, pageSize);
		Page<Customer> page = customerRepository.findAllOrderedByDob(pageable);
		if (page.isEmpty()) {
			throw new InfyBankException("Service.CUSTOMERS_NOT_FOUND");
		}
		Iterable<Customer> customersIterable = page.getContent();
		List<CustomerDTO> customerDTOs = new ArrayList<CustomerDTO>();
		customersIterable.forEach(customer -> {
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setId(customer.getId());
			customerDTO.setName(customer.getName());
			customerDTO.setEmail(customer.getEmail());
			customerDTO.setDob(customer.getDob());
			customerDTO.setType(customer.getType());
			customerDTOs.add(customerDTO);
		});
		return customerDTOs;
	}

}
