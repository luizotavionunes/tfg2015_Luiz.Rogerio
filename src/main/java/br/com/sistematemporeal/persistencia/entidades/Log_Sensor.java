package br.com.sistematemporeal.persistencia.entidades;

import java.sql.Date;
import java.sql.Time;

public class Log_Sensor {
	private Integer id;
	private Integer cpf_funcionario;
	private Integer id_sensor;
	private Date data;
	
	private String observacao;
	private Date data_inicio;
	private Time hora;
	private Time hora_inicio;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCpf_funcionario() {
		return cpf_funcionario;
	}

	public void setCpf_funcionario(Integer cpf_funcionario) {
		this.cpf_funcionario = cpf_funcionario;
	}

	public Integer getId_sensor() {
		return id_sensor;
	}

	public void setId_sensor(Integer id_sensor) {
		this.id_sensor = id_sensor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getData_inicio() {
		return data_inicio;
	}

	public void setData_inicio(Date data_inicio) {
		this.data_inicio = data_inicio;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public Time getHora_inicio() {
		return hora_inicio;
	}

	public void setHora_inicio(Time hora_inicio) {
		this.hora_inicio = hora_inicio;
	}
	
	

}
