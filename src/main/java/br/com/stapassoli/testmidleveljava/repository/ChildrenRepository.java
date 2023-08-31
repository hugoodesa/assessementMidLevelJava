package br.com.stapassoli.testmidleveljava.repository;

import br.com.stapassoli.testmidleveljava.entity.Children;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildrenRepository extends JpaRepository<Children,Long> {

    /*
    * SELECT c.father_id ,p.name
        FROM CHILDREN c
        JOIN parent p on p.id = c.father_id
    * */
    @Query(value = "select from " ,nativeQuery = true)
    List<String> getFathersName();
}
