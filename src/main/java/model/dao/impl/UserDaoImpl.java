package model.dao.impl;

import Config.Configuration;
import model.User;
import model.dao.UserDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public void createTable() {
        String query=""+
                "create table if not exists users (id serial primary key," +
                "full_name varchar," +
                "age int);";
        try(Connection connection = Configuration.connectionToDataBace();
        Statement statement = connection.createStatement()){
            statement.executeUpdate(query);

            System.out.println("users table id created...");

        }catch (SQLException a){
            System.out.println(a.getMessage());
        }


    }

    @Override
    public void saveUser(User user) {
        String sql = "insert into users(" +
                "full_name,age)" +
                "values(?,?);";
        try(Connection connection=Configuration.connectionToDataBace();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,user.getFullName());
            preparedStatement.setInt(2,user.getAge());
            preparedStatement.executeUpdate();
            System.out.println("save...");


        }catch (SQLException a){
            System.out.println(a.getMessage());
        }

    }

    @Override
    public User getUserById(Long id) {
        User user =null;
         String sql ="select * from users where id=?;";
        try (Connection connection =Configuration.connectionToDataBace() ;
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet =preparedStatement.executeQuery();
            if (resultSet.next()){
                user =new User();
                user.setId(resultSet.getLong("id"));
                user.setFullName(resultSet.getString("full_name"));
                user.setAge(resultSet.getInt("age"));
            }

        }catch (SQLException a){
            System.out.println(a.getMessage());
        }
        return user;

    }

    @Override
    public List<User> getAllUsers() {
        List<User>getUsers= new ArrayList<>();
        String sql = "select * from users;";
        try(Connection connection = Configuration.connectionToDataBace();
            Statement statement =connection.createStatement()) {
            ResultSet resultSet= statement.executeQuery(sql);
            while (resultSet.next()){
                getUsers.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("full_name"),
                        resultSet.getInt("age")
                ));
            }
        }catch (SQLException a){
            System.out.println(a.getMessage());
        }
        return null;
    }

    @Override
    public void updateUserInfo(Long id, User user) {
        String sql ="update users " +
                "set full_name =?," +
                "age =?  where id =?;";
        try (Connection connection =Configuration.connectionToDataBace();
        PreparedStatement preparedStatemen= connection.prepareStatement(sql)){

            preparedStatemen.setString(1,user.getFullName());
            preparedStatemen.setInt(2,user.getAge());
            preparedStatemen.setLong(3,id);
            preparedStatemen.executeUpdate();
            System.out.println("user with id: "+id+"successfully updated!");
        }catch (SQLException a){
            System.out.println(a.getMessage());
        }

    }

    @Override
    public void cleanUserTable() {
        String sql = "truncate table users;";
        try(Connection connection = Configuration.connectionToDataBace();
        Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
            System.out.println("clear is users");

        }catch (SQLException a){
            System.out.println(a.getMessage());
        }


    }

    @Override
    public void dropUserTable() {
        String sql ="drop table  if exists users;";
        try(Connection connection =Configuration.connectionToDataBace();
        Statement statement=connection.createStatement()){
            statement.executeUpdate(sql);
            System.out.println("users table is delete...");

        }catch (SQLException a){
            System.out.println(a.getMessage());
        }
    }
    @Override
    public void deleteById(Long id) {
        String sql ="delete from  users where id =?;";
        try(Connection connection =Configuration.connectionToDataBace();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,id);
            int deleteRoo = preparedStatement.executeUpdate();
            if (deleteRoo>0){
                System.out.println("deleted");
            }else {
                System.out.println("not faunt");
            }

        }catch (SQLException a) {
            System.out.println(a.getMessage());
        }


    }

    @Override
    public List<User> getAllSortedUsers(String ascOrDesc) {
        List<User>sortedUsers =  new ArrayList<>();
        String sql ="select * from users order by full_name desc;";
        try (Connection connection = Configuration.connectionToDataBace();
             Statement statement =connection.createStatement()){
            ResultSet resultSet  = statement.executeQuery(sql);
            while (resultSet.next()){
                sortedUsers.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("full_name"),
                        resultSet.getInt("age")
                )
                );
            }
        }catch (SQLException a){
            System.out.println(a.getMessage());
        }
        return null;
    }

}
