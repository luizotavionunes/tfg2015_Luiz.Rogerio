package br.com.sistematemporeal.persistencia.entidades;

import java.sql.Date;

public class Eventos {
	private Integer id;
	private String valor;
	private Date data;
	private Integer id_sensor;
	private Integer entrada;
	private Integer saida;
	private Date hora_inicio; // time no sql
	private Date hora_fim; // time no sql
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Integer getId_sensor() {
		return id_sensor;
	}
	public void setId_sensor(Integer id_sensor) {
		this.id_sensor = id_sensor;
	}
	public Integer getEntrada() {
		return entrada;
	}
	public void setEntrada(Integer entrada) {
		this.entrada = entrada;
	}
	public Integer getSaida() {
		return saida;
	}
	public void setSaida(Integer saida) {
		this.saida = saida;
	}
	public Date getHora_inicio() {
		return hora_inicio;
	}
	public void setHora_inicio(Date hora_inicio) {
		this.hora_inicio = hora_inicio;
	}
	public Date getHora_fim() {
		return hora_fim;
	}
	public void setHora_fim(Date hora_fim) {
		this.hora_fim = hora_fim;
	}
	
	
}
