package com.everis.d4i.tutorial.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "CHAPTERS")

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Chapter implements Serializable {

    private static final long serialVersionUID = 8725949484031409482L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NUMBER")
    private short number;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DURATION")
    private short duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SEASON_ID", nullable = false)
    private Season season;

    @ManyToMany
    @JoinTable(name = "chapter_actor",
            joinColumns = @JoinColumn(name = "idActor"),
            inverseJoinColumns = @JoinColumn(name = "idChapter"))
    private List<Actor> actors;


}
