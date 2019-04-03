package com.telusko.Demorest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlienRepository {

	Connection con = null;
	private String url = "jdbc:mysql://localhost:3306/restDb";
	private String user = "root";
	private String password = "@siyarampk";

	public AlienRepository() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	public List<Alien> getAliens() {
		String sql = "select * from Alien order by id";
		List<Alien> aliens = new ArrayList<Alien>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			// Allien get all alien;
			// All
			while (rs.next()) {
				Alien a1 = new Alien();
				a1.setId(rs.getInt("id"));
				a1.setName(rs.getString("name"));
				a1.setPoint(rs.getInt("point"));
				//aliens.add(a1);
				aliens.add(a1);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		return aliens;
	}

	public Alien getAlien(int id) {
		String sql = "select * from Alien where id='" + id + "'";
		Alien a1 = new Alien();
		
		// Alien get by id 
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
				
			if (rs.next()) {
				a1.setId(rs.getInt("id"));
				a1.setName(rs.getString("name"));
				a1.setPoint(rs.getInt("point"));
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		return a1;
	}

	public void create(Alien a1) {
		String sql = "insert into alien values(?,?,?)";

		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, a1.getId());
			pst.setString(2, a1.getName());
			pst.setInt(3, a1.getPoint());
			pst.executeUpdate();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public void updateAlien(Alien a1) {
		String sql = "update alien set id=?,name=?,point=? where id=? ";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, a1.getId());
			pst.setString(2, a1.getName());
			pst.setInt(3, a1.getPoint());
			pst.setInt(4, a1.getId());
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void AlienKill(int id) {
		String sql = "Delete from alien where id='" + id + "'";
		try {
			Statement stmt = con.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
