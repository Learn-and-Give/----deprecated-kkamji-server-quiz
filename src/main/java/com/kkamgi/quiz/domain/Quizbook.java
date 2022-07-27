package com.kkamgi.quiz.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "quizbook")
public class Quizbook {
    @Id
    @Column(name = "quizbook_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    private Integer price;
}
