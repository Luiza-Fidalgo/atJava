package com.moster.AT.Controller;

import com.moster.AT.exception.ResourceNotFoundException;
import com.moster.AT.exception.ResponsePayload;
import com.moster.AT.model.ClientesPayload;
import com.moster.AT.model.InformacoesPayload;
import com.moster.AT.model.Monster;
import com.moster.AT.service.MonsterService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log
@RestController
@RequestMapping("/monstro")
public class MonsterController {
    @Autowired
    MonsterService monsterService;
    @GetMapping()
    public List<Monster> getAll() {
         List<Monster> monstros = monsterService.getAll();
         return monstros;

    }

    @GetMapping("/{id}")
    public ResponseEntity getAll(@RequestParam(required = false, defaultValue = "10") int size,
                                 @RequestParam(required = false, defaultValue = "1") int page,
                                 @RequestParam(required = false ) Optional<String> nome){
        try {
            if(page < 1) throw new InvalidParameterException("Parametro page Invalido");
            List<Monster> monsters = new ArrayList<>();
            if(nome.isPresent()){
                monsters = monsterService.filterByName(nome.get(),size);
            }else{
                monsters = monsterService.getByPage(page, size);
            }

            int totalSize = monsters.size();
            int qtdPaginas = monsterService.getTotalPaginas(size);
            new InformacoesPayload(totalSize,qtdPaginas);
            InformacoesPayload infoPayload = InformacoesPayload.builder()
                    .totalSize(totalSize)
                    .totalPages(qtdPaginas)
                    .build();
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("total-size", String.valueOf(totalSize));
            responseHeaders.set("total-pages", String.valueOf(qtdPaginas));


            ClientesPayload clientesPayload = new ClientesPayload(monsters,infoPayload);
            return ResponseEntity.status(HttpStatus.OK)
                    .headers(responseHeaders).body(monsters);
        } catch (InvalidParameterException | IndexOutOfBoundsException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponsePayload("Valor invalido para Page"));
        }
    }
    @PostMapping
    public void create(@RequestBody Monster monster){
        monsterService.create(monster);
        log.info(String.valueOf(HttpStatus.CREATED));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        monsterService.deleteById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody Monster atualizado){
        monsterService.update(id,atualizado);
    }
}
