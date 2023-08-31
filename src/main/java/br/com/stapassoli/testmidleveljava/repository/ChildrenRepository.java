package br.com.stapassoli.testmidleveljava.repository;

import br.com.stapassoli.testmidleveljava.entity.Children;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildrenRepository extends JpaRepository<Children,Long> {
    @Query(value = "INSERT INTO child (name, father_id, mother_id) VALUES (:name, :father_id, :mother_id)" ,nativeQuery = true)
    Children createChild (String name, Long father_id , Long mother_id );

}
