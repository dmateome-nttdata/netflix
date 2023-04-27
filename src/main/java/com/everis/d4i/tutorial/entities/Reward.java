package com.everis.d4i.tutorial.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ACTOR")
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reward implements Serializable {

    private static final long serialVersionUID = 180802329613616000L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "rewards")
    private List<TvShow> tvShows;
}
