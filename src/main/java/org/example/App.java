package org.example;

import Config.Configuration;
import model.Contact;
import model.Phone;
import model.User;
import service.Impl.ContactServiceImpl;
import service.Impl.PhoneServiceImpl;
import service.Impl.UserServiceImpl;
import service.PhoneService;
import service.UserService;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
//        System.out.println(Configuration.connectionToDataBace());
        UserService userService = new UserServiceImpl();
//        userService.createTable();
//        userService.saveUser(new  User("torogeldi",16));
//        userService.saveUser(new  User("dastan",18));
//        userService.saveUser(new  User("aibike",10));
//        System.out.println(userService.getUserById(4L));
//        userService.updateUserInfo(4L,new User("dastan",15));
//        userService.cleanUserTable();
//        userService.dropUserTable();
//        userService.deleteById(1L);
//        userService.getAllSortedUsers("asc");
        PhoneServiceImpl phoneService = new PhoneServiceImpl();
//        phoneService.createPhoneTable();
//        phoneService.savePhone(new Phone("samsung s 10","samsung",16000,1L));
//        phoneService.savePhone(new Phone("apple pro max 14","apple",2000,2L));
//        phoneService.updatePhone(2L,new Phone("asus s 3","asus",3000,2L));
//        System.out.println(phoneService.getAllUserPhone(2L));
//        System.out.println(phoneService.getAllUserSortedUserPhone(2L, "asc"));
//        System.out.println(phoneService.getYoungerUserPhone());
//        System.out.println(phoneService.getPhoneById(2L));
//        phoneService.deletePhoneById(2L);
        ContactServiceImpl contactService = new ContactServiceImpl();
//        contactService.createContactTable();
//        contactService.saveContact(new Contact("torogeldi","0505104433",1L));
//        contactService.saveContact(new Contact("dastan","0707104433",2L));
//        contactService.saveContact(new Contact("ttt","0707104413",3L));
//        System.out.println(contactService.getContactById(2L));
//        System.out.println(contactService.getAllContacts());
//        System.out.println(contactService.getAllUserContacts(3L));
//        System.out.println(contactService.getAllUserContacts(1L));
//        contactService.getPhoneContactsCount(2L);
//        contactService.getPhoneContactsCount(1L);
//        contactService.updateContactInfo(1L,new Contact("dastan","055555555",1L));
//        contactService.deleteAllPhoneContactsByPhoneId(1L);

    }
}
