package com.moster.AT.service;

import com.moster.AT.Util.MonsterUtil;
import com.moster.AT.exception.ResourceNotFoundException;
import com.moster.AT.model.Monster;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Log
@Service
public class MonsterService {

    public Map<Integer, Monster> monsterMap = retornoElements();

    public Integer idmonstro = (Integer) getAll().size();


    private Map<Integer, Monster> retornoElements() {

        Map<Integer, Monster> monsterMap = new HashMap<>();
        MonsterUtil monsterUtil = new MonsterUtil();

        for (int i = 1; i < 41 ; i++) {

            Monster monster = monsterUtil.getMonster(i);

            Monster monstroX = new Monster(monster.getId(), monster.getName(), monster.getElements());

            monsterMap.put(monstroX.getId(), monstroX);
        }

        return monsterMap;

    }


    public List<Monster> getAll() {

        return monsterMap.values().stream().toList();
    }

    public Monster getMonstrobyId(int Id) {
       Monster monster = monsterMap.get(Id);
        if (monster == null){
            throw new ResourceNotFoundException("Pokemon não encontrado");
        }
        return monster;
    }

    public void create(Monster monster) {
        int id = ++this.idmonstro;
        monster.setId(id);
        monsterMap.put(id, monster);
    }

    public void deleteById(int id) {
        monsterMap.remove(id);
    }

    public void update(int id, Monster atualizado) {
        monsterMap.replace(id, atualizado);
    }

    public int getTotalPaginas(int size) {
        int totalSize = count();
        return (int) Math.ceil((double )totalSize / (double)size);
    }

    public List<Monster> filterByName(String name, int size) {
        List<Monster> all = getAll();
        return all.stream().filter(monster -> monster.getName().startsWith(name)).toList();
    }
    public int count(){
        return monsterMap.size();
    }
    public List<Monster> getByPage(int page, int size) {
        int qtdPaginas = getTotalPaginas(size);
        if(page > qtdPaginas) throw new InvalidParameterException("Page invalido");
        List<Monster> all = getAll();
        int count = count();
        int start = (page -1) * size;
        int end = size + start;
        if(end > count) end = count;
        return all.subList(start,end);
    }
}
