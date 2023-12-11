package com.moster.AT;

import com.moster.AT.Util.MonsterUtil;
import com.moster.AT.model.Monster;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MonsterUtilTest {


    @Test
    @DisplayName("TestNaApi: Busca um monstr pelo ID")
    public void deveRetornarUmMonstro(){
        MonsterUtil monsterUtil = new MonsterUtil();
        Monster monstro = monsterUtil.getMonster(1);
        System.out.println(monstro);

    }
}
