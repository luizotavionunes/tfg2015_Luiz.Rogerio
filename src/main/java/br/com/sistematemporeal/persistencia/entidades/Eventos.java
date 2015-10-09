package br.com.sistematemporeal.persistencia.entidades;

import java.sql.Date;
import java.sql.Time;

public class Eventos {
	private Integer id;
	private String valor;
	private Date data_inicio;
	private Integer id_sensor;
	private Integer entrada;
	private Integer saida;
	private Time hora_inicio; // time no sql
	private Time hora_fim; // time no sql
	private Date data_fim;
	private Integer id_fat;
	private Integer valor_total;
	private Date tempo_i;
	private Date tempo_f;

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

	public Date getData_inicio() {
		return data_inicio;
	}

	public void setData_inicio(Date data_inicio) {
		this.data_inicio = data_inicio;
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

	public Time getHora_inicio() {
		return hora_inicio;
	}

	public void setHora_inicio(Time hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	public Time getHora_fim() {
		return hora_fim;
	}

	public void setHora_fim(Time hora_fim) {
		this.hora_fim = hora_fim;
	}

	public Date getData_fim() {
		return data_fim;
	}

	public void setData_fim(Date data_fim) {
		this.data_fim = data_fim;
	}

	public Integer getId_fat() {
		return id_fat;
	}

	public void setId_fat(Integer id_fat) {
		this.id_fat = id_fat;
	}

	public Integer getValor_total() {
		return valor_total;
	}

	public void setValor_total(Integer valor_total) {
		this.valor_total = valor_total;
	}

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

	@Override
	public String toString() {
		return "Eventos [id=" + id + ", valor=" + valor + ", data_inicio=" + data_inicio + ", id_sensor=" + id_sensor
				+ ", entrada=" + entrada + ", saida=" + saida + ", hora_inicio=" + hora_inicio + ", hora_fim="
				+ hora_fim + ", data_fim=" + data_fim + ", id_fat=" + id_fat + ", valor_total=" + valor_total
				+ ", tempo_i=" + tempo_i + ", tempo_f=" + tempo_f + ", getId()=" + getId() + ", getValor()="
				+ getValor() + ", getData_inicio()=" + getData_inicio() + ", getId_sensor()=" + getId_sensor()
				+ ", getEntrada()=" + getEntrada() + ", getSaida()=" + getSaida() + ", getHora_inicio()="
				+ getHora_inicio() + ", getHora_fim()=" + getHora_fim() + ", getData_fim()=" + getData_fim()
				+ ", getId_fat()=" + getId_fat() + ", getValor_total()=" + getValor_total() + ", getTempo_i()="
				+ getTempo_i() + ", getTempo_f()=" + getTempo_f() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}



	
	




}
