
package com.mkdika.springbootjersey.api.address;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Maikel Chandika (mkdika@gmail.com)
 */
public interface AddressRepository extends CrudRepository<Address, Integer> {
    
    List<Address> findByAddressContainingAllIgnoreCase(String key);
    
}
