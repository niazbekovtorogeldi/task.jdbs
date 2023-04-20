package service;

import model.Phone;

import java.util.List;

public interface PhoneService {
    void createPhoneTable();
    void  savePhone(Phone phone);
    void updatePhone(Long id, Phone phone);
    List<Phone> getAllUserPhone(Long userId);
    Phone getPhoneById(Long id);
    void deletePhoneById(Long id);
}
