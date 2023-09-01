package br.com.stapassoli.testmidleveljava.repository;

import br.com.stapassoli.testmidleveljava.entity.Parent;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {

    @Query(value = "SELECT p.name FROM parent p", nativeQuery = true)
    List<String> getAllFathersName();

    @Query(value = "SELECT p.name " +
        "FROM parent p " +
        "JOIN children c ON c.father_id = p.id " +
        "GROUP BY p.id, p.name " +
        "HAVING COUNT(c.id) > 2 ", nativeQuery = true)
    List<String> getAllFathersNameMoreThanOneChild();

    @Query(value = "SELECT "+
        "count (p.*) "+
        "FROM parent p "+
        "JOIN children c on (c.father_id = p.id or c.mother_id = p.id) "+
        "WHERE p.name ilike :parentName ", nativeQuery = true)
    Integer getHowManyChildrenParentHas(@Param("parentName") String parentName);


}
