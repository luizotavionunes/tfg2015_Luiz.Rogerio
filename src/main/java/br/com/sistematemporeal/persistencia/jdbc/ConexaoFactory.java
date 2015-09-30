package br.com.sistematemporeal.persistencia.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.sistematemporeal.persistencia.entidades.Funcionarios;

public class ConexaoFactory {

	public static Connection getConnection() {
		
		try {
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/motelbd","postgres","plasma");
		} catch (SQLException e) {
			// relancando a exception
			throw new RuntimeException(e);		
			}
	}
	
}
