package br.com.sistematemporeal.persistencia.entidades;

import java.sql.Date;

public class Faturamento {
	private Integer id;
	private String valor_informado;
	private String estado;
	private Integer cpf_funcionario;
	private Date data;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getValor_informado() {
		return valor_informado;
	}
	public void setValor_informado(String valor_informado) {
		this.valor_informado = valor_informado;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Integer getCpf_funcionario() {
		return cpf_funcionario;
	}
	public void setCpf_funcionario(Integer cpf_funcionario) {
		this.cpf_funcionario = cpf_funcionario;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
}
