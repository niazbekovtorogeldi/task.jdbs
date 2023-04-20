package model.dao.impl;

import Config.Configuration;
import model.Phone;
import model.dao.PhoneDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PhoneDaoImpl implements PhoneDao {
    @Override
    public void createPhoneTable() {
        String sql = "create table phones(" +
                "id serial primary key," +
                "model varchar," +
                "brand varchar," +
                "price int," +
                "user_id int references users(id));";
        try(Connection connection = Configuration.connectionToDataBace();
            Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
            System.out.println("Phone table create....");
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void savePhone(Phone phone) {
        String sql = "insert into phones(" +
                "model,brand,price,user_id)" +
                "values(?,?,?,?);";
        try(Connection connection=Configuration.connectionToDataBace();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, phone.getModel());
            preparedStatement.setString(2, phone.getBrand());
            preparedStatement.setInt(3, phone.getPrice());
            preparedStatement.setLong(4,phone.getUser_id());
            preparedStatement.executeUpdate();
            System.out.println("save...");
        }catch (SQLException a){
            System.out.println(a.getMessage());
        }

    }

    @Override
    public void updatePhone(Long id, Phone phone) {
        String sql = "update phones " +
                "set model=?," +
                "brand =?," +
                "price =? where id=?;";
        try(Connection connection = Configuration.connectionToDataBace();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,phone.getModel());
            preparedStatement.setString(2,phone.getBrand());
            preparedStatement.setInt(3,phone.getPrice());
            preparedStatement.setLong(4,id);
            preparedStatement.executeUpdate();
            System.out.println("user with id:"+id+"successfully update!");

        }catch (SQLException a){
            System.out.println(a.getMessage());
        }

    }

    @Override
    public List<Phone> getAllUserPhone(Long userId) {
        List<Phone> getAll = new ArrayList<>();
        String sql = "SELECT  *  FROM phones WHERE user_id = ?;";
        try (Connection connection = Configuration.connectionToDataBace();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1,userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                getAll.add(new Phone(
                        resultSet.getLong("id"),
                        resultSet.getString("model"),
                        resultSet.getString("brand"),
                        resultSet.getInt("price")  ,
                        resultSet.getLong("user_id")

                        ));
            }
        }catch (SQLException a){
            System.out.println(a.getMessage());
        }
        return getAll;
    }

    @Override
    public List<Phone> getAllUserSortedUserPhone(Long userId, String ascOrDesc) {
        List<Phone>sort = new ArrayList<>();
        String sql = "SELECT * FROM phones WHERE user_id = ? ORDER BY model  "+ascOrDesc;
        try(Connection connection = Configuration.connectionToDataBace();
       PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1,userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                sort.add(new Phone(
                        resultSet.getLong("id"),
                        resultSet.getString("model"),
                        resultSet.getString("brand"),
                        resultSet.getInt("price"),
                        resultSet.getLong("user_id")
                ));

            }
        }catch (SQLException a){
            System.out.println(a.getMessage());
        }
        return sort;
    }

    @Override
    public Phone getYoungerUserPhone() {
        Phone phone = null;
        String sql = "select phones.* from phones join users u on phones.user_id = u.id order by u.age asc limit 1";
        try (Connection connection = Configuration.connectionToDataBace();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                phone = new Phone();
                phone.setModel(result.getString("model"));
                phone.setBrand(result.getString("brand"));
                phone.setPrice(result.getInt("price"));
                phone.setUser_id(result.getLong("user_id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return phone;
    }

    @Override
    public Phone getPhoneById(Long id) {
        Phone phone = null;
        String sql ="select * from phones where id=?;";
        try(Connection connection = Configuration.connectionToDataBace();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                phone = new Phone();
                phone.setId(resultSet.getLong("id"));
                phone.setModel(resultSet.getString("model"));
                phone.setBrand(resultSet.getString("brand"));
                phone.setPrice(resultSet.getInt("price"));

            }
        }catch (SQLException a){
            System.out.println(a.getMessage());
        }
        return phone;
    }

    @Override
    public void deletePhoneById(Long id) {
        String sql = "delete from phones where id=?;";
        try(Connection connection = Configuration.connectionToDataBace();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,id);
            int deleteRoo= preparedStatement.executeUpdate();
            if (deleteRoo>0){
                System.out.println("deleted");
            }else {
                System.out.println("not faunt");
            }
        }catch (SQLException a){
            System.out.println(a.getMessage());
        }

    }
}