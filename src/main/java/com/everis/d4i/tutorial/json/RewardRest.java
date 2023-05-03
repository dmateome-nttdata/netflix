package com.everis.d4i.tutorial.json;

import com.everis.d4i.tutorial.entities.TvShow;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RewardRest implements Serializable {

    private static final long serialVersionUID = 180802329613616000L;

    private Long id;

    private String name;

    @Override
    public String toString() {
        return "RewardRest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
