package com.moster.AT.Util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.moster.AT.model.Monster;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MonsterUtil {
    private final String URL = "https://mhw-db.com/monsters/";

    public Monster getMonster(int id) {

        try {

            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .version(HttpClient.Version.HTTP_2)
                    .uri(new URI(URL + id))
                    .build();

            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
            Monster MonsterDetails = mapper.readValue(response.body(), Monster.class);

            String nome = MonsterDetails.getName();
            List<String> elementos = getMonsterElements(MonsterDetails);

            Monster monstro = new Monster((int) id, nome, elementos);

            return monstro;

        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getMonsterElements(Monster monster) {
        List<String> elements = new LinkedList<>();
        for (int i = 0; i < monster.getElements().size(); i++) {
            Object tipos = monster.getElements().get(i);
            elements.add((String) tipos);
        }
        return elements;
    }


}
