package org.sop.postservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "viewed-posts")
public class ViewedPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "seen_at")
    private LocalDate seenAt;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "post_id")
    private Long postId;
}
