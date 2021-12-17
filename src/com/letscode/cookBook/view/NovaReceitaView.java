package com.letscode.cookBook.view;

import com.letscode.cookBook.domain.Ingrediente;
import com.letscode.cookBook.domain.Receita;
import com.letscode.cookBook.domain.Rendimento;
import com.letscode.cookBook.enums.Categoria;
import com.letscode.cookBook.enums.TipoMedida;
import com.letscode.cookBook.enums.TipoRendimento;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class NovaReceitaView {
    Scanner scanner;
    private Receita receita;
    String nome;

    public NovaReceitaView() {
        this.scanner = new Scanner(System.in);
        this.receita = new Receita();
        askNome();
        askCategoria();
        askTempoPreparo();
        askRendimento();
        askIngredientes();
        askModoPreparo();
    }

    public void askNome() {
        System.out.println("Qual o nome da receita?");
        nome = scanner.nextLine();
        if (nome.isBlank()) {
            System.out.println("Nome inválido!");
            askNome();
        }
        this.receita.setNome(nome);
    }

    public void askCategoria() {
        System.out.println("Qual a categoria da receita?");
        for (Categoria cat : Categoria.values()) {
            System.out.printf("%d - %s %n", cat.ordinal(), cat.name());
        }
        int categoria = scanner.nextInt();
        if (categoria < 0 || categoria >= Categoria.values().length) {
            System.out.println("Categoria inválida!");
            askCategoria();
        }
        for (Categoria cat : Categoria.values()) {
            if (cat.ordinal() == categoria){
                this.receita.setCategoria(Categoria.valueOf(cat.name()));
            }
        }
    }

    public void askTempoPreparo(){
        System.out.println("Qual o tempo de preparo da receita?");
        int tempoPreparo = scanner.nextInt();
        this.receita.setTempoPreparo(tempoPreparo);
    }

    public void askRendimento(){

        String txtRendimento = "";

        System.out.println("Qual o rendimento da receita?");
        int quantidade = scanner.nextInt();

        System.out.println("Qual o tipo de rendimento da receita?");
        for (TipoRendimento tipo: TipoRendimento.values()) {
            System.out.printf("%d - %s %n", tipo.ordinal(), tipo.name());
        }
        int tipoRendimento = scanner.nextInt();
        if (tipoRendimento < 0 || tipoRendimento >= TipoRendimento.values().length){
            System.out.println("Tipo de rendimento inválido");
            askRendimento();
        }

        for (TipoRendimento tipo: TipoRendimento.values()){
            if (tipo.ordinal() == tipoRendimento){
                txtRendimento = tipo.name();
            }
        }

        Rendimento rendimento = new Rendimento(quantidade, TipoRendimento.valueOf(txtRendimento));
        this.receita.setRendimento(rendimento);
    }

    public void askIngredientes(){
        System.out.println("Informe os ingredientes da receita");

        List<Ingrediente> ingredientes = new ArrayList<>();
        String insereIngredientes = "S";

        while ("S".equals(insereIngredientes.toUpperCase(Locale.ROOT))){
            System.out.println("Nome do ingrediente");
            String nomeIngrediente = scanner.next();

            System.out.println("Quantidade do ingrediente");
            double quantidadeIngrediente = scanner.nextDouble();

            System.out.println("Informe o tipo de medida");
            for (TipoMedida medida: TipoMedida.values()) {
                System.out.printf("%d - %s %n", medida.ordinal(), medida.name());
            }

            int tipoMedida = scanner.nextInt();
            if (tipoMedida < 0 || tipoMedida >= TipoMedida.values().length){
                System.out.println("Tipo de medida inválida");
                askIngredientes();
            }

            String txtMedida = "";
            for (TipoMedida tipo: TipoMedida.values()) {
                if (tipo.ordinal() == tipoMedida){
                    txtMedida = tipo.name();
                }
            }

            Ingrediente ingrediente = new Ingrediente(nomeIngrediente, quantidadeIngrediente, TipoMedida.valueOf(txtMedida));
            ingredientes.add(ingrediente);

            System.out.println("Deseja adicionar mais ingredientes? Se sim, digite S. Caso contrário, digite qualquer tecla:");
            insereIngredientes = scanner.next();
        }

        this.receita.setIngredientes(ingredientes);
    }

    public void askModoPreparo(){
        System.out.println("Descreva o modo de preparo da receita");
        Scanner preparo = new Scanner(System.in);
        String descricao = preparo.nextLine();
        String[] modoPreparo = { descricao };
        this.receita.setModoPreparo(modoPreparo);
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }
}
