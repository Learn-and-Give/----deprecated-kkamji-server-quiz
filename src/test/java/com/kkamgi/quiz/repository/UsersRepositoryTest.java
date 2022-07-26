package com.kkamgi.quiz.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UsersRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("회원가입 테스트")
    public void createUserTest() {
        //given
        userRepository.save(Users.builder()
                .name("kim")
                .password("1234")
                .point(0)
                .consecutiveSubmitDate(0)
                .build());

        //when
        List<Users> usersList = userRepository.findAll();

        //then
        Users user = usersList.get(0);
        assertThat(user.getName()).isEqualTo("kim");

        System.out.println("user = " + user);;
    }
}