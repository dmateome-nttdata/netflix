package com.everis.d4i.tutorial.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ACTOR")
@Data @Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class Actor implements Serializable {

    private static final long serialVersionUID = 180802329613616000L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAMES")
    private String surnames;

    @Column(name = "AGE")
    private Integer age;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "actors")
    private List<Chapter> chapters;
}
