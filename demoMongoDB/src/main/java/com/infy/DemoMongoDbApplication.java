package com.infy;

import java.time.LocalDate;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.core.env.Environment;
import com.infy.dto.CustomerDTO;
import com.infy.dto.CustomerType;
import com.infy.service.CustomerServiceImpl;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DemoMongoDbApplication implements CommandLineRunner {
	public final static Logger LOGGER = LogManager.getLogger(DemoMongoDbApplication.class);
	@Autowired
	CustomerServiceImpl customerServiceImpl;
	@Autowired
	Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(DemoMongoDbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		addCustomer();
//		getCustomer();
//		getAllCustomers();
//		updateCustomer();
//		deleteCustomer();
//		findByEmailIdIsNull();
//		getCustomersByNameAndEmail();
		getAllCustomersOrderedByDob();
	}

	public void getAllCustomersOrderedByDob() {
		// TODO Auto-generated method stub
		try {
			customerServiceImpl.getAllCustomerOrderByDob(0, 2).forEach(LOGGER::info);
		} catch (Exception exception) {
			// TODO: handle exception
			exceptionHandling(exception);
		}
	}

	public void getCustomersByNameAndEmail() {
		// TODO Auto-generated method stub
		try {
			CustomerDTO customerDTO = customerServiceImpl.getCustomerByNameAndEmail("Jack", "jack@infy.com");
			LOGGER.info(customerDTO);
		} catch (Exception exception) {
			// TODO: handle exception
			exceptionHandling(exception);
		}
	}

	public void findByEmailIdIsNull() {
		// TODO Auto-generated method stub
		try {
			List<CustomerDTO> customerDTOs = customerServiceImpl.findByEmailNull();
			customerDTOs.forEach(customer -> {
				LOGGER.info(customer);
			});
		} catch (Exception exception) {
			// TODO: handle exception
			exceptionHandling(exception);
		}
	}

	public void deleteCustomer() {
		try {
			customerServiceImpl.deleteCustomer(1L);
			LOGGER.info(environment.getProperty("UserInterface.DELETE_SUCCESS"));
		} catch (Exception exception) {
			// TODO: handle exception
			exceptionHandling(exception);
		}
	}

	public void updateCustomer() {
		// TODO Auto-generated method stub
		try {
			customerServiceImpl.updateCustomer(1L, "martin01@infy.com");
			LOGGER.info(environment.getProperty("UserInterface.UPDATE_SUCCESS"));
		} catch (Exception exception) {
			// TODO: handle exception
			exceptionHandling(exception);
		}
	}

	public void getAllCustomers() {
		// TODO Auto-generated method stub
		try {
			customerServiceImpl.getAllCustomers().forEach(LOGGER::info);
		} catch (Exception exception) {
			// TODO: handle exception
			exceptionHandling(exception);
		}
	}

	public void getCustomer() {
		// TODO Auto-generated method stub
		try {
			CustomerDTO customerDTO = customerServiceImpl.getCustomer(3L);
			LOGGER.info(customerDTO);
		} catch (Exception exception) {
			// TODO: handle exception
			exceptionHandling(exception);
		}
	}

	public void addCustomer() {
		// TODO Auto-generated method stub
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setName("Harry");
		customerDTO.setEmail("harry@infy.com");
		customerDTO.setDob(LocalDate.of(1996, 03, 22));
		customerDTO.setType(CustomerType.GOLD);
		try {
			Long customerId = customerServiceImpl.addCustomer(customerDTO);
			LOGGER.info(environment.getProperty("UserInterface.INSERT_SUCCESS") + customerId);
		} catch (Exception exception) {
			// TODO: handle exception
			exceptionHandling(exception);
		}
	}

	public void exceptionHandling(Exception exception) {
		if (exception.getMessage() != null) {
			LOGGER.info(environment.getProperty(exception.getMessage(),
					"Something went wrong. Please check log file for more details."));
		}
	}
}
