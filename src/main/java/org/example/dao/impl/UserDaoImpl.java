package org.example.dao.impl;

import org.example.config.DatabaseConfig;
import org.example.dao.UserDao;
import org.example.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private final Connection connection=DatabaseConfig.getConnection();

    @Override
    public void createUserTable() {
        String sql= """
        create table if not exists users(
                id serial primary key ,
                first_name varchar,
                last_name varchar,
                email varchar)
        """;

        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Table successfully created");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }



    }

    @Override
    public String saveUser(User user) {
        String sql= """
                insert into users(first_name,last_name,email)
                values(?,?,?)
                """;
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getFirstName());
            preparedStatement.setString(2,user.getLastName());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return user.getFirstName() +" "+ "Successfully saved";


    }

    @Override
    public User getUserById(Long id) {
        String sql="select * from users where id=?";
        User user=new User();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                user.setId(resultSet.getLong("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        String sql="select * from users";
        List<User> users=new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                User user=new User();
                user.setId(resultSet.getLong("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                users.add(user);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public String updateUser(Long userId, User user) {
        String sql= """
                update users 
                set first_name=?,last_name=?,email=?
                where id=?
                """;
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getFirstName());
            preparedStatement.setString(2,user.getLastName());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setLong(4,userId);
            int rowsUpdated=preparedStatement.executeUpdate();
            if (rowsUpdated>0){
                return "User successfully updated";
            }else {
                return "User not found";
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return "Error updating user";
        }

    }

    @Override
    public void deleteUserById(Long id) {
        String sql="delete from users where id=?";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            int rowsDeleted=preparedStatement.executeUpdate();
            if (rowsDeleted>0){
                System.out.println("User deleted successfully");
            }else {
                System.out.println("User not found");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
