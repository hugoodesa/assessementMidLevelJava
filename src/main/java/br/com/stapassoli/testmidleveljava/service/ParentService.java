package br.com.stapassoli.testmidleveljava.service;

import br.com.stapassoli.testmidleveljava.entity.Parent;
import br.com.stapassoli.testmidleveljava.repository.ParentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ParentService {

    @PersistenceContext
    private EntityManager entityManager;

    private final ParentRepository parentRepository;

    private List<Parent> readCSV(MultipartFile file) throws IOException {

        List<Parent> parents = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                // Process each CSV line
                System.out.println(line);

                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] fields = line.split(";");
                Long id = (long) Integer.parseInt(fields[0]);
                String name = fields[1];

                parents.add(new Parent(id, name));

            }
        } catch (IOException e) {
            e.printStackTrace();
            throw (e);
        }

        return parents;
    }

    @Transactional
    private void createParent(Parent parent) throws Exception {

        final String QUERY = "INSERT INTO parent (id,name) VALUES (?,?)";

        int rowsAffected = entityManager.createNativeQuery(QUERY)
            .setParameter(1, parent.getId())
            .setParameter(2, parent.getName())
            .executeUpdate();

        if (rowsAffected == 0) {
            throw new Exception("Error on persist parent entity");
        }

    }

    @Transactional
    public ResponseEntity<?> createParents(MultipartFile file) {

        try {

            for (Parent parent : readCSV(file)) {
                createParent(parent);
            }

            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }

    }

    public ResponseEntity<List<String>> getAllFathersName() {
        return ResponseEntity.ok(this.parentRepository.getAllFathersName());
    }

    public ResponseEntity<List<String>> getAllFathersNameMoreThanOneChild() {
        return ResponseEntity.ok(this.parentRepository.getAllFathersNameMoreThanOneChild());
    }

    public ResponseEntity<Integer> getHowManyChildrenParentHas(String parentName) {
        return ResponseEntity.ok(this.parentRepository.getHowManyChildrenParentHas(parentName));
    }

}
