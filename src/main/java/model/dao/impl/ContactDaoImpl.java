package model.dao.impl;

import Config.Configuration;
import model.Contact;
import model.Phone;
import model.dao.ContactDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDaoImpl implements ContactDao {

    @Override
    public void createContactTable() {
        String sql = "create table if not exists contacts(" +
                "id serial primary key," +
                "contact_name varchar," +
                "contact_number varchar);";
        try(Connection connection = Configuration.connectionToDataBace();
            Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
            System.out.println("contac table id created...");
        }catch (SQLException a){
            System.out.println(a.getMessage());
        }

    }

    @Override
    public void saveContact(Contact contact) {
        String sql = "insert into contacts(" +
                "contact_name,contact_number)" +
                "values(?,?);";
        try(Connection connection = Configuration.connectionToDataBace();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,contact.getContactName());
            preparedStatement.setString(2,contact.getPhoneNumber());
            preparedStatement.executeUpdate();
            System.out.println("save...");

        }catch (SQLException a){
            System.out.println(a.getMessage());
        }

    }

    @Override
    public Contact getContactById(Long id) {
        Contact contact = null;
        String sql = "select * from contacts where id = ?;";
        try(Connection connection = Configuration.connectionToDataBace();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                contact= new Contact();
                contact.setId(resultSet.getLong("id"));
                contact.setContactName(resultSet.getString("contact_name "));
                contact.setPhoneNumber(resultSet.getString("contact_number"));

            }
        }catch (SQLException a){
            System.out.println(a.getMessage());
        }
        return contact;
    }

    @Override
    public List<Contact> getAllContacts() {
        List <Contact> getAll = new ArrayList<>();
        String sql = "select * from contacts;";
        try(Connection connection = Configuration.connectionToDataBace();
        Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                getAll.add(new Contact(
                        resultSet.getLong("id"),
                        resultSet.getString("contact_name"),
                        resultSet.getString("contact_number"),
                        resultSet.getLong("phone_id")
                ));
            }
        }catch (SQLException a){
            System.out.println(a.getMessage());
        }
        return getAll;
    }

    @Override
    public List<Contact> getAllPhoneContacts(Long contactId) {
        List<Contact> contacts = new ArrayList<>();
        String sql = "select contacts.* from contacts join phones p on contacts.phone_id = p.id where contacts.id = ?  ";
        try(Connection connection =Configuration.connectionToDataBace();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1,contactId);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                contacts.add(new Contact(
                        result.getLong("id"),
                        result.getString("name"),
                        result.getString("phone_number"),
                        result.getLong("phone_id")
                ));
            }
        }catch(SQLException e ){
            System.out.println(e.getMessage());
        }
        return contacts;
    }

    @Override
    public List<Contact> getAllUserContacts(Long userId) {
        List<Contact>contacts = new ArrayList<>();
        String sql = "select contacts .* from contacts join phones p on contacts.phone_id = p.id join users u on p.user_id = u.id where u.id = ?;";
        try(Connection connection =Configuration.connectionToDataBace();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1,userId);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                contacts.add(new Contact(
                        result.getLong("id"),
                        result.getString("phone_name"),
                        result.getString("phone_number"),
                        result.getLong("phone_id")
                ));
            }
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
        return contacts;
    }

    @Override
    public void getPhoneContactsCount(Long phoneId) {
        String sql = "select count (*) from contacts join phones p on contacts.phone_id = p.id   where p.id = ? ;";
        try(Connection connection =Configuration.connectionToDataBace();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong (1,phoneId);
            statement.execute();
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                System.out.println(result.getInt("count"));


            }
        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void getUserContactsCount(Long userId) {
        String sql = "select count(*) from contacts join phones p on contacts.phone_id = p.id join users u on p.user_id= u.id where u.id = ?;";
        try(Connection connection = Configuration.connectionToDataBace();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1,userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getInt("count"));
            }

        }catch (SQLException e ){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateContactInfo(Long id, Contact contact) {
        String sql = "update contacts set contact_name = ?,phone_number = ?,phone_id = ? where id = ?;";
        try(Connection connection =Configuration.connectionToDataBace();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, contact.getContactName());
            statement.setString(2,contact.getPhoneNumber());
            statement.setLong(3,contact.getPhone_id());
            statement.setLong(4,id);
            statement.executeUpdate();
//            System.out.println("updated!");

        }catch(SQLException e ){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteAllPhoneContactsByPhoneId(Long phoneId) {
        String sql = "delete from contacts where phone_id = ?;";
        try(Connection connection =Configuration.connectionToDataBace();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setLong(1,phoneId);
            statement.executeUpdate();
            System.out.println(" deleted...");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
