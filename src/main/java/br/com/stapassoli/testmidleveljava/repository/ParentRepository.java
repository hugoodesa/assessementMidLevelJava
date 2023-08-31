package br.com.stapassoli.testmidleveljava.repository;

import br.com.stapassoli.testmidleveljava.entity.Children;
import br.com.stapassoli.testmidleveljava.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {

    @Query(value = "INSERT INTO parent (id,name) VALUES (:id,:name)", nativeQuery = true)
    Children createParent(Long id, String name);

}
