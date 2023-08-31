package br.com.stapassoli.testmidleveljava.service;

import br.com.stapassoli.testmidleveljava.entity.Children;
import br.com.stapassoli.testmidleveljava.entity.Parent;
import br.com.stapassoli.testmidleveljava.repository.ChildrenRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ChildrenService {

    @PersistenceContext
    private EntityManager entityManager;

    private final ChildrenRepository childrenRepository;

    private List<Children> readCSV(MultipartFile file) throws IOException {

        List<Children> children = new ArrayList<>();

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
                Long fatherId = fields[2].equalsIgnoreCase("null") ? null : (long) Integer.parseInt(fields[2]);
                Long motherId = fields[3].equalsIgnoreCase("null") ? null : (long) Integer.parseInt(fields[3]);

                children.add(new Children(id, name,fatherId,motherId));

            }
        } catch (IOException e) {
            e.printStackTrace();
            throw (e);
        }

        return children;
    }



    @Transactional
    private void createChildren(Children children) throws Exception {

        final String QUERY = "INSERT INTO children (id,name,father_id,mother_id) VALUES (?,?,?,?)";

        int rowsAffected = entityManager.createNativeQuery(QUERY)
            .setParameter(1, children.getId())
            .setParameter(2, children.getName())
            .setParameter(3, Objects.nonNull(children.getFather().getId()) ? children.getFather().getId() : null)
            .setParameter(4, Objects.nonNull(children.getMother().getId()) ? children.getMother().getId() : null)
            .executeUpdate();

        if (rowsAffected == 0) {
            throw new Exception("Error on persist child entity");
        }

    }

    @Transactional
    public ResponseEntity<?> createParents(MultipartFile file) {

        try {

            for (Children children : readCSV(file)) {
                createChildren(children);
            }

            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }

    }

    public ResponseEntity<String> getFathersName() {
        this.childrenRepository.getFathersName();
        return null;
    }
}
