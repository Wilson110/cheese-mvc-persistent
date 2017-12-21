package org.launchcode.models.data;

import org.launchcode.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

/*Step 1: Create CategoryDao model class
* Add the interface to the code. Extend CrudRepository class
* with @Repository and @Transactional */

@Repository
@Transactional
public interface CategoryDao extends CrudRepository<Category, Integer> {
}
