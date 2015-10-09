package br.com.sistematemporeal.persistencia.entidades;

import java.sql.Date;
import java.sql.Time;

public class Faturamento {
	private Integer id;
	private String valor_informado;
	private String estado;
	private Integer cpf_funcionario;
	private Date data;
	private Date data_inicio;
	private Time hora_inicio;
	private Time hora;
	private Integer total_eventos;
	private Date tempo_i;
	private Date tempo_f;
	
	public Date getTempo_i() {
		return tempo_i;
	}
	public void setTempo_i(Date tempo_i) {
		this.tempo_i = tempo_i;
	}
	public Date getTempo_f() {
		return tempo_f;
	}
	public void setTempo_f(Date tempo_f) {
		this.tempo_f = tempo_f;
	}
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
	public Date getData_inicio() {
		return data_inicio;
	}
	public void setData_inicio(java.sql.Date date) {
		this.data_inicio = (Date) date;
	}
	public Time getHora_inicio() {
		return hora_inicio;
	}
	public void setHora_inicio(Time timeDB) {
		this.hora_inicio = timeDB;
	}
	public Time getHora() {
		return hora;
	}
	public void setHora(Time hora) {
		this.hora = hora;
	}
	public Integer getTotal_eventos() {
		return total_eventos;
	}
	public void setTotal_eventos(Integer total_eventos) {
		this.total_eventos = total_eventos;
	}
	
	
	
}
