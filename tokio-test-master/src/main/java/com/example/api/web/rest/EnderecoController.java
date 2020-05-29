package com.example.api.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.domain.client.DadosEnderecoClient;
import com.example.api.dto.CepClientDTO;

import javassist.NotFoundException;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    
    @Autowired
    private DadosEnderecoClient dadosEnderecoClient;
    
    @GetMapping("/{cep}")
    public CepClientDTO buscarCep(@PathVariable String cep) throws NotFoundException {
        return dadosEnderecoClient.buscaEnderecoPorCEP(cep);
    }
}
