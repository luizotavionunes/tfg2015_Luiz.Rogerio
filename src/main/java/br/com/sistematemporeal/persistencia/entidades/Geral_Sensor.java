package br.com.sistematemporeal.persistencia.entidades;

import java.sql.Time;

public class Geral_Sensor {
	
	private Integer id_sensor;
	private Integer estado;
	private Integer id_log;
	private Integer monitor;
	private String data;
	private Time hora;
	private Integer preco;
	
	
	public Integer getPreco() {
		return preco;
	}
	public void setPreco(Integer preco) {
		this.preco = preco;
	}
	public Integer getId_log() {
		return id_log;
	}
	public void setId_log(Integer id_log) {
		this.id_log = id_log;
	}
	public Integer getId_sensor() {
		return id_sensor;
	}
	public void setId_sensor(Integer id_sensor) {
		this.id_sensor = id_sensor;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	public Integer getMonitor() {
		return monitor;
	}
	public void setMonitor(Integer monitor) {
		this.monitor = monitor;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Time getHora() {
		return hora;
	}
	public void setHora(Time hora) {
		this.hora = hora;
	}
	@Override
	public String toString() {
		return "Geral_Sensor [id_sensor=" + id_sensor + ", estado=" + estado + ", id_log=" + id_log + ", monitor="
				+ monitor + ", data=" + data + ", hora=" + hora + ", preco=" + preco + "]";
	}


	
	

}
