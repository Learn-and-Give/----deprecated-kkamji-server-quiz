package com.kkamgi.quiz.domain;

import aj.org.objectweb.asm.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kkamgi.quiz.domain.data.QuizType;
import lombok.*;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@ToString
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

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY)
    private List<QuizKeyword> quizKeywords = new ArrayList<>();

    public Map<String, Object> getContent() {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> dataMap = new HashMap<>();
        try {
            dataMap = mapper.readValue(content, Map.class);
        } catch (IOException e) {
            dataMap.put("status", "ERROR");
            e.printStackTrace();
        }
        return dataMap;
    }
}