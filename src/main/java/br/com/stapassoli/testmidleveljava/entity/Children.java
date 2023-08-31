package br.com.stapassoli.testmidleveljava.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Children implements ConvertFromCSV<Children> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mother_id")
    private Parent mother;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "father_id")
    private Parent father;

    @Override
    public Children tranformFieldsToEntity(String[] fields) {
        Long id = (long) Integer.parseInt(fields[0]);
        String name = fields[1];
        Long fatherId = (long) Integer.parseInt(fields[2]);
        Long motherId = (long) Integer.parseInt(fields[3]);

        return Children
            .builder()
            .id(id)
            .name(name)
            .father(Parent.builder().id(fatherId).build())
            .mother(Parent.builder().id(motherId).build())
            .build();
    }
}
