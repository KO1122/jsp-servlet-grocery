package grocery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ItemDbUtil {
	
	private DataSource dataSource;
	
	public ItemDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Item> getItems() throws Exception {
		List<Item> items = new ArrayList<>();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null; 
		
		try  {
			connection = dataSource.getConnection();
			String sql = "select * from items";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int quantity = resultSet.getInt("quantity");
				float price = resultSet.getFloat("price");
				
				Item item = new Item(id, name, quantity, price);
				items.add(item);
			}
		} 
		finally {
			close(connection, statement, resultSet);
		}
		
		return items;
	}

	private void close(Connection connection, Statement statement, ResultSet resultSet) {
		try {
			if (connection != null) {
				connection.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addItem(Item item) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = dataSource.getConnection();
			String sql = "insert into items " + 
						 "(name, quantity, price) " + 
						 "values (?, ?, ?)";
			
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, item.getName());
			statement.setInt(2, item.getQuantity());
			statement.setFloat(3, item.getPrice());
			
			statement.execute();
		}
		finally {
			close(connection, statement, null);
		}
	}

	public Item getItem(int id) throws Exception {		
		Connection connection = null;
		PreparedStatement statement = null; 
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			String sql = "select * from items where id=?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				String name = resultSet.getString("name");
				int quantity = resultSet.getInt("quantity"); 
				float price = resultSet.getFloat("price");
				
				Item item = new Item(id, name, quantity, price);
				return item;
			}
			else {
				throw new Exception("Could not find item id: " + id);
			}
		}
		finally {
			close(connection, statement, resultSet);
		}
	}

	public void updateItem(Item item) throws Exception {
		Connection connection = null; 
		PreparedStatement statement = null; 
		
		try {
			connection = dataSource.getConnection();
			String sql = "update items " + 
					     "set name=?, quantity=?, price=? " + 
					     "where id=?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, item.getName());
			statement.setInt(2, item.getQuantity());
			statement.setFloat(3, item.getPrice());
			statement.setInt(4, item.getId());
			statement.execute();
		}
		finally {
			close(connection, statement, null);
		}
	}

	public void deleteItem(int id) throws Exception {
		Connection connection = null; 
		PreparedStatement statement = null; 
		
		try {
			connection = dataSource.getConnection();
			String sql = "delete from items " + 
					     "where id=?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.execute();
		}
		finally {
			close(connection, statement, null);
		}
	}

	public List<Item> searchItems(String searchName) throws Exception {
		List<Item> items = new ArrayList<>();
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			
			if (searchName != null && searchName.trim().length() > 0) {
				String sql = "select * from items where lower(name) like ?";
				statement = connection.prepareStatement(sql);
				String searchNameLike = "%" + searchName.toLowerCase() + "%";
				statement.setString(1, searchNameLike);
			} else {
				String sql = "select * from items";
				statement = connection.prepareStatement(sql);
			}
			
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				int quantity = resultSet.getInt("quantity");
				float price = resultSet.getFloat("price");
				Item item = new Item(name, quantity, price);
				items.add(item);
			}
			
			return items;
		}
		finally {
			close(connection, statement, resultSet);
		}
	}
}
