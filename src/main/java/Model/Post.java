package Model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    private String postData;

    @OneToOne
    private PostUser user;

    private LocalDateTime dateTime;

    @OneToMany
    private Set<PostComment> comments = new HashSet<>();

    public void addComment(PostComment postComment){
        comments.add(postComment);
    }

}
