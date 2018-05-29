package pl.hubswi90.spring.OnlineShop.service;

import pl.hubswi90.spring.OnlineShop.domain.Address;

public interface AddressService {

    public void saveUserAddressInDatabase(Address address);

    void deleteAddress(long userId, long addressId);

    public boolean saveAddressAndAddToUser(Address address, long userId);

    public Address getAddressById(long addressId);

    public void updateAdress(Address address);

    void editUserAddress(Long userId, Address address);
}
