package com.everis.d4i.tutorial.json;

import com.everis.d4i.tutorial.entities.Chapter;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActorRest implements Serializable {

    private static final long serialVersionUID = 180802329613616000L;

    private Long id;


    private String name;


    private String surnames;


    private Integer age;

    private List<Chapter> chapters;

    @Override
    public String toString() {
        return "ActorRest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surnames='" + surnames + '\'' +
                ", age=" + age +
                ", chapters=" + chapters +
                '}';
    }
}
