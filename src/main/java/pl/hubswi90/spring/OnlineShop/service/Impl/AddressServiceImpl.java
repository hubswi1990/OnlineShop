package pl.hubswi90.spring.OnlineShop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.hubswi90.spring.OnlineShop.domain.Address;
import pl.hubswi90.spring.OnlineShop.domain.User;
import pl.hubswi90.spring.OnlineShop.repository.AddressRepository;
import pl.hubswi90.spring.OnlineShop.repository.UserRepository;
import pl.hubswi90.spring.OnlineShop.service.AddressService;
import pl.hubswi90.spring.OnlineShop.service.UserService;

import java.util.Iterator;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository repo;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Override
    public void saveUserAddressInDatabase(Address address) {

        repo.saveAddress(address);
    }

    @Override
    public void deleteAddress(long userId, long addressId) {
        User user = userService.findUserByQuery(userId);

        for(Iterator<Address> it = user.getAddressSet().iterator(); it.hasNext();){
            Address address = it.next();

            if(address.getAddressId() == addressId) {
                it.remove();
            }
        }

        userRepository.save(user);
        repo.deleteAddressInDatabase(addressId);
    }

    @Override
    public boolean saveAddressAndAddToUser(Address address, long userId) {

        saveUserAddressInDatabase(address);

        User user = userService.findUserByQuery(userId);
        user.getAddressSet().add(address);
        userRepository.save(user);

        return false;
    }

    @Override
    public Address getAddressById(long addressId) {
        return repo.getAddressById(addressId);
    }

    @Override
    public void updateAdress(Address address) {
        repo.updateAddress(address);
    }

    @Override
    public void editUserAddress(Long userId, Address address) {

        User user = userService.findUserByQuery(userId);

        for(Iterator<Address> it = user.getAddressSet().iterator(); it.hasNext();){
            Address addressInDatabase = it.next();

            if(addressInDatabase.getAddressId() == address.getAddressId()) {
                it.remove();
            }
        }

        repo.updateAddress(address);
        user.getAddressSet().add(address);
        userRepository.save(user);
    }
}
