package org.itstep.domain.entity;

import lombok.*;
import org.graalvm.compiler.lir.alloc.lsra.IntervalWalker;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Post {
    private Integer id;
    private String title;
    private String description;
    private Category category;
}
