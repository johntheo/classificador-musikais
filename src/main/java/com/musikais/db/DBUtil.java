package com.musikais.db;

import java.sql.Connection;
import java.sql.DriverManager;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class DBUtil {
	private static final int LOCAL_PORT = 4321;
	private static final int REMOTE_PORT = 3306;
	private static final int SSH_PORT = 22;
	private static final String REMOTE_HOST = "127.4.84.2";
	private static final String USER = "552d45935973ca8b2400015f";
	private static final String HOST = "servidor-musikais.rhcloud.com";
	private static final String PASSWORD = "sapoboi8803";
	private static final String DB_NAME = "musikais_curitiba";
	private static final String DB_USER = "adminysiRP9Y";
	private static final String DB_PASS = "chSchRdW_prX";

	public static Connection dataSource() {
		Connection connection = null;

		try {
			JSch jsch = new JSch();
			String privateKey = "~/.ssh/id_rsa";
			jsch.addIdentity(privateKey);
			Session session = jsch.getSession(USER, HOST, SSH_PORT);
			session.setPassword(PASSWORD);
			session.setConfig("StrictHostKeyChecking", "no");
			System.out.println("Estabelecendo conexão pelo tunel SSH...");
			session.connect();
			int assingedPort = session.setPortForwardingL(LOCAL_PORT,
					REMOTE_HOST, REMOTE_PORT);
			System.out.println("localhost:" + assingedPort + " -> "
					+ REMOTE_HOST + ":" + REMOTE_PORT);

			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:4321/" + DB_NAME;
			Class.forName(driver);
			connection = DriverManager.getConnection(url, DB_USER, DB_PASS);

		} catch (Exception e) {
			System.err.print(e);
		}

		return connection;
	}

}
