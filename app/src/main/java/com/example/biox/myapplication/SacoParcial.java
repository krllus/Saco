package com.example.biox.myapplication;


/**
 * Created by João Carlos on 10/23/17.
 * Biox Pecuaria Moderna
 * desenvolvedorberrante@bioxbr.com
 */
public enum SacoParcial {
    SACO_VAZIO("Sem parcial", 0.0),
    UM_QUARTO("1/4 do Saco (25%)", 0.25),
    UM_TERCO("1/3 do Saco (33%)", 0.33),
    METADE("1/2 do Saco (50%)", 0.5);

    private String nome;
    private Double multiplier;

    SacoParcial(String nome, Double multiplier) {
        this.nome = nome;
        this.multiplier = multiplier;
    }

    public String getNome() {
        return nome;
    }

    public Double getMultiplier() {
        return multiplier;
    }
}