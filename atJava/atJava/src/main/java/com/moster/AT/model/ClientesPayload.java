package com.moster.AT.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientesPayload {
    private List<Monster> monsterList;
    private InformacoesPayload info;
}