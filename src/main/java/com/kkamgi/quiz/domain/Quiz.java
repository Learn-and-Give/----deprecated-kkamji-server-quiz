package com.kkamgi.quiz.domain;

import com.kkamgi.quiz.domain.data.QuizType;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "quiz")
public class Quiz {
    @Id
    @Column(name = "quiz_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "answer", columnDefinition = "TEXT")
    private String answer;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private QuizType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_info_id")
    private ChallengeInfo challangeInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quizbook_id")
    private Quizbook quizbook;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member Member;
}