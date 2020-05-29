package com.example.api.domain;

import javax.persistence.Embeddable;


@Embeddable

public class Endereco {
	
	private String cep;
	
	private String logradouro;
	
	private String numero;
	
	private String complemento;
	
	private String referencia;
	
	private String bairro;
	
	private String municipio;
	
	private String uf;
	
	public static Endereco novoEndereco(String cep, String logradouro, String numero, String complemento,
	        String referencia, String bairro, String municipio, String uf) {
	    return new Endereco(cep, logradouro, numero, complemento, referencia, bairro, municipio, uf);
	}
	
	private Endereco(String cep, String logradouro, String numero, String complemento,
            String referencia, String bairro, String municipio, String uf) {
	    this.cep = cep;
	    this.logradouro = logradouro;
	    this.numero = numero;
	    this.complemento = complemento;
	    this.referencia = referencia;
	    this.bairro = bairro;
	    this.municipio = municipio;
	    this.uf = uf;
	}
	
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	
	

}
