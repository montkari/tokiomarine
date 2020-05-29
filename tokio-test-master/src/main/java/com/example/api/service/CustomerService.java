package com.example.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.api.domain.Customer;
import com.example.api.domain.exception.EntidadeEmUsoException;
import com.example.api.domain.exception.EntidadeNaoEncontradaException;
import com.example.api.repository.CustomerRepository;

@Service
public class CustomerService {

	private CustomerRepository repository;

	@Autowired
	public CustomerService(CustomerRepository repository) {
		this.repository = repository;
	}

	public List<Customer> findAll() {
		return repository.findAllByOrderByNameAsc();
	}

	public Optional<Customer> findById(Long id) {
		return repository.findById(id) ;
	}
	
	
	public Customer salvar(Customer customer) {
		return repository.save(customer);
	}
	
	
	public void excluir(Long Id) {

		try {
			repository.deleteById(Id);

		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException( // esta
					String.format("Customer de código %d não pode ser removida, pois está em uso", Id));

		} catch (DataIntegrityViolationException e) {
			// TODO: handle exception
			throw new EntidadeEmUsoException(
					String.format("Customer de código %d não pode ser removida, pois está em uso", Id));
		}
	}
}
