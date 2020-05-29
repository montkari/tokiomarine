package com.example.api.web.rest;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import com.example.api.domain.Customer;
import com.example.api.domain.exception.EntidadeEmUsoException;
import com.example.api.domain.exception.EntidadeNaoEncontradaException;
import com.example.api.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private static final String PESQUISA_CUSTOMER_VIEW = "PesquisaCustomer";
	private CustomerService service;

	@Autowired
	public CustomerController(CustomerService service) {
		this.service = service;
	}

	
	@RequestMapping("/listar")
	public ModelAndView pesquisar() {
		List<Customer> todosCustomers = service.findAll();
		ModelAndView mv = new ModelAndView(PESQUISA_CUSTOMER_VIEW);
		mv.addObject("customers", todosCustomers);

		return mv;
	}
	
	@GetMapping
	public List<Customer> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Customer findById(@PathVariable Long id) {
		return service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Customer adcionar(@Validated @RequestBody Customer customer) {

		return service.salvar(customer);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Customer> atualizar(@Validated @PathVariable Long id, @RequestBody Customer customer) {

		Customer custumerAtual = findById(id);

		if (custumerAtual != null) {
			BeanUtils.copyProperties(customer, custumerAtual, "id");

			service.salvar(custumerAtual);

			return ResponseEntity.ok(custumerAtual);

		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Customer> excluir(@PathVariable Long id) {

		try {
			service.excluir(id);
			return ResponseEntity.noContent().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}
