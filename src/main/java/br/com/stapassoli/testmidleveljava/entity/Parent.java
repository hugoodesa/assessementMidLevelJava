package br.com.stapassoli.testmidleveljava.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Parent implements ConvertFromCSV<Parent>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Override
    public Parent tranformFieldsToEntity(String[] fields) {
        Long id = (long) Integer.parseInt(fields[0]);
        String name = fields[1];

        return Parent.builder().id(id).name(name).build();
    }
}
