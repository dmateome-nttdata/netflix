package com.everis.d4i.tutorial.entities;

import lombok.*;

import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "CATEGORIES")
@Data @Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class Category implements Serializable {

	private static final long serialVersionUID = 180802329613616000L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NAME", unique = true)
	private String name;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private List<TvShow> tvShows;

	public Category(Long id, String name) {
		this.id = id;
		this.name = name;
	}
}
