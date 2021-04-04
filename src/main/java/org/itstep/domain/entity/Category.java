package org.itstep.domain.entity;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Category {
    Integer id;
    @NonNull
    String name;
}
