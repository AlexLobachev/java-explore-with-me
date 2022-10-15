package ru.practicum.ewm.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.categories.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Modifying(clearAutomatically = true)
    @Query("update Category c set c.name =?1 where c.id = ?2")
    void updateCategory(String name, long id);


}
