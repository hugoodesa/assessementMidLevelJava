package br.com.stapassoli.testmidleveljava.controller;

import br.com.stapassoli.testmidleveljava.service.ParentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/parent")
@RequiredArgsConstructor
public class ParentController {

    private final ParentService parentService;

    @PostMapping
    public ResponseEntity<?> createParents(@RequestParam("file") MultipartFile file) {
        return parentService.createParents(file);
    }

    @GetMapping("/all-fathers-name")
    public ResponseEntity<List<String>> getAllFathersName() {
        return parentService.getAllFathersName();
    }

    @GetMapping("/all-fathers-name-nome-than-two-child")
    public ResponseEntity<List<String>> getAllFathersNameMoreThanOneChild() {
        return parentService.getAllFathersNameMoreThanOneChild();
    }

    @GetMapping("/get-number-of-children-that-parent-has")
    public ResponseEntity<Integer> getHowManyChildrenParentHas(@RequestParam(name = "parent") String parentName) {
        return parentService.getHowManyChildrenParentHas(parentName);
    }

}
