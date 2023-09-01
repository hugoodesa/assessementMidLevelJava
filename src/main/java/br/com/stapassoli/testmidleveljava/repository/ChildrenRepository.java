package br.com.stapassoli.testmidleveljava.repository;

import br.com.stapassoli.testmidleveljava.DTO.ChildParentProjection;
import br.com.stapassoli.testmidleveljava.entity.Children;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildrenRepository extends JpaRepository<Children,Long> {


    @Query(value = "SELECT c.name as childrenName, pf.name as fatherName, pm.name as motherName" +
        " FROM children c " +
        " LEFT JOIN parent pf ON pf.id = c.father_id" +
        " LEFT JOIN parent pm ON pm.id = c.mother_id", nativeQuery = true)
    List<ChildParentProjection> getChildrenAndThemParents();

}
