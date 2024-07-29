package com.infy.service;

import java.util.List;
import com.infy.dto.CustomerDTO;
import com.infy.exception.InfyBankException;

public interface CustomerService {
	long getNextSequenceId(String key) throws InfyBankException;

	public Long addCustomer(CustomerDTO customerDTO) throws InfyBankException;

	public CustomerDTO getCustomer(Long CustomerId) throws InfyBankException;

	public List<CustomerDTO> getAllCustomers() throws InfyBankException;

	public void updateCustomer(Long CustomerId, String email) throws InfyBankException;

	public void deleteCustomer(Long customerId) throws InfyBankException;

	List<CustomerDTO> findByEmailNull() throws InfyBankException;

	public CustomerDTO getCustomerByNameAndEmail(String name, String email) throws InfyBankException;

	public List<CustomerDTO> getAllCustomerOrderByDob(Integer pageNO, Integer pageSize) throws InfyBankException;
}
