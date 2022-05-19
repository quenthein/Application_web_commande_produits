package fr.epsi.rennes.poec.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import fr.epsi.rennes.poec.domain.User;

@Repository
public class UserDAO {

	@Autowired
	private DataSource ds;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User getUserByEmail(String email) throws SQLException {
		String sql =
				"select email, password, role "
				+ "from user where email = '" + email + "' ";
		Statement stmt = ds.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			User user = new User();
			user.setEmail(rs.getString(1));
			user.setPassword(rs.getString(2));
			user.setRole(rs.getString(3));
			return user;
		} else {
			return null;
		}
	}
	public void addUser(User user) throws SQLException {
		String password = passwordEncoder.encode(user.getPassword());
		String sql =
				"insert into user (email, password) " +
				"values ('" + user.getEmail() + "', '" + password + "') ";
		
		Statement stmt = ds.getConnection().createStatement();
		stmt.executeUpdate(sql);
	}
}
