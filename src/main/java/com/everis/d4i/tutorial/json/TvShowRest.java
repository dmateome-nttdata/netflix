package com.everis.d4i.tutorial.json;

import java.io.Serializable;
import java.time.Year;
import java.util.List;

import com.everis.d4i.tutorial.entities.Reward;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TvShowRest implements Serializable {

	private static final long serialVersionUID = 4916713904971425156L;

	private Long id;
	private String name;
	private String shortDescription;
	private String longDescription;
	private Year year;
	private byte recommendedAge;
	private CategoryRest category;
	private String advertising;
	private List<Reward> rewards;
}
