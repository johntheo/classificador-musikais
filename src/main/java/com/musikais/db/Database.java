package com.musikais.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {

	private static final String INSERT_MUSICA_ECHONEST = "insert into musica_echonest values (?,?,?,?,?,?,?,?)";

	public void inserirMusicaEchonest(String idMusica, String idArtista,
			String nomeArtista, String nomeMusica, double tempo,
			double energia, double valencia, boolean disponibilidade) {

		try {
			Connection connection = DBUtil.dataSource();
			PreparedStatement preparedStatement = connection
					.prepareStatement(INSERT_MUSICA_ECHONEST);
			preparedStatement.setString(1, idMusica);
			preparedStatement.setString(2, idArtista);
			preparedStatement.setString(3, nomeArtista);
			preparedStatement.setString(4, nomeMusica);
			preparedStatement.setDouble(5, tempo);
			preparedStatement.setDouble(6, energia);
			preparedStatement.setDouble(7, valencia);
			preparedStatement.setBoolean(8, disponibilidade);
			System.out.println("QUERY A SER EXECUTADA: "
					+ preparedStatement.toString());
			preparedStatement.executeUpdate();
			System.out.println("REGISTRO INSERIDO COM SUCESSO: " + idMusica);

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.print("ERRO: " + e.getMessage());
		}

	}

}
