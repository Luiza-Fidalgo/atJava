package com.moster.AT;

import com.moster.AT.Util.MonsterUtil;
import com.moster.AT.exception.ResourceNotFoundException;
import com.moster.AT.model.Monster;
import com.moster.AT.service.MonsterService;
import lombok.extern.java.Log;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Log
@SpringBootTest
public class MonsterUtilTest {
    @Autowired
    MonsterService monsterService;

    @Test
    @DisplayName("TestNaApi: Busca um monstr pelo ID")

    public void deveRetornarUmMonstro(){
        MonsterUtil monsterUtil = new MonsterUtil();
        Monster monstro1 = monsterUtil.getMonster(21);
        List<String> monsterss = monsterUtil.getMonsterElements(monstro1);
        System.out.println(monsterss);
    }

    @Test
    @DisplayName("Teste retorna todos")

    public void deveRetornarTodos(){
        MonsterService monsterService = new MonsterService();
        List<Monster> monstro = monsterService.getAll();
        System.out.println(monstro);
    }

    @Test
    @DisplayName("Deve retornar 1")

    public void deveRetornar1(){
        MonsterService monsterService = new MonsterService();
        Monster monstro = monsterService.getMonstrobyId(21);
        System.out.println(monstro);
    }

    @Test
    @DisplayName("Deve dar erro")
    public void deveDarErro(){
        assertThrows(ResourceNotFoundException.class,()->{
            monsterService.getMonstrobyId(-85);
        });
        log.info("Há um (impostor) erro entre nós");
    }
}

