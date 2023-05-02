package com.everis.d4i.tutorial.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Year;
import java.util.List;

@Entity
@Table(name = "TV_SHOWS")
@Data  @Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class TvShow implements Serializable {

    private static final long serialVersionUID = 4916713904971425156L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SHORT_DESC", nullable = true)
    private String shortDescription;

    @Column(name = "LONG_DESC", nullable = true)
    private String longDescription;

    @Column(name = "YEAR")
    private Year year;

    @Column(name = "RECOMMENDED_AGE")
    private byte recommendedAge;

    @ManyToMany
    @JoinTable(name = "tvShow_categories",
            joinColumns = @JoinColumn(name = "idCategory"),
            inverseJoinColumns = @JoinColumn(name = "idTvShow"))
    private List<Category> categories;

    @Column(name = "ADVERTISING", nullable = true)
    private String advertising;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tvShow")
    private List<Season> seasons;

    @ManyToMany
    @JoinTable(name = "tvShow_rewards",
            joinColumns = @JoinColumn(name = "idReward"),
            inverseJoinColumns = @JoinColumn(name = "idTvShow"))
    private List<Reward> rewards;

    public TvShow(Long id, String name, String shortDescription, String longDescription, Year year, byte recommendedAge, String advertising) {
        this.id = id;
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.year = year;
        this.recommendedAge = recommendedAge;
        this.advertising = advertising;
    }

}
