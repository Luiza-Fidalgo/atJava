package com.moster.AT.service;

import com.moster.AT.Util.MonsterUtil;
import com.moster.AT.model.Monster;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@Log
@Service
public class MonsterService {

    public Map<Integer, Monster> monsterMap = retornoElements();

    public Integer idmonstro = (Integer) retornoTodosMonstros().size();




    private Map<Integer, Monster> retornoElements() {

    Map<Integer, Monster> monsterMap = new HashMap<>();
        MonsterUtil monsterUtil = new MonsterUtil();

        for(int i = 1; i <= 15;i++) {

            Monster monster = monsterUtil.getMonster(i);

            Monster monstroX = new Monster(monster.getId() , monster.getName() , monster.getElements());

            monsterMap.put(monstroX.getId(), monstroX);
        }

        return monsterMap;

}


public List<Monster> retornoTodos() {

        return   monsterMap.values().stream().toList();
}


public Monster getMonsterPerID(int id ) {

        Monster monster;
}

}
