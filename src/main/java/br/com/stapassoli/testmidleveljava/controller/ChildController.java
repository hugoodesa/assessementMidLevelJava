package br.com.stapassoli.testmidleveljava.controller;

import br.com.stapassoli.testmidleveljava.DTO.ChildParentProjection;
import br.com.stapassoli.testmidleveljava.service.ChildrenService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/child")
@RequiredArgsConstructor
public class ChildController {

    private final ChildrenService childService;

    @GetMapping("/hellow")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> sayHellow () {
        return ResponseEntity.ok("Hellow");
    }

    @PostMapping
    public ResponseEntity<?> createChild(@RequestParam("file") MultipartFile file) {
        return childService.createParents(file);
    }

    @GetMapping("/children-and-parents-names")
    public ResponseEntity<List<ChildParentProjection>> getChildNamesAndThemParents() {
        return childService.getChildNamesAndThemParents();
    }

}
