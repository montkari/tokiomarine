package com.example.api.domain.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.api.dto.CepClientDTO;

import javassist.NotFoundException;



@Service
public class DadosEnderecoClient {

    @Value("${url.consulta.cep}")
    private String urlCep;

    @Value("${url.consulta.estados}")
    private String urlEstados;

    public CepClientDTO buscaEnderecoPorCEP(String cep) throws NotFoundException {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForEntity(urlCep + cep + "/json", CepClientDTO.class).getBody();
        } catch (HttpClientErrorException e) {
            throw new NotFoundException("CEP não encontrado");
        }
    }


}
