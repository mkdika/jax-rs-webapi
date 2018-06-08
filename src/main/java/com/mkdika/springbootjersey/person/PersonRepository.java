
package com.mkdika.springbootjersey.person;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Maikel Chandika (mkdika@gmail.com)
 */
public interface PersonRepository extends CrudRepository<Person, Integer> {
    
    List<Person> findByNameContainingAllIgnoreCase(String key);
    
}
