package projedata_tests;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import projedata_tests.entity.Funcionario;


public class PrincipalSimples {
    public static void main(String[] args) {

        
        // 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.
        List<Funcionario> funcionarios = new ArrayList<>();
            
        funcionarios.add(new Funcionario(ThreadLocalRandom.current().nextLong(99999999999L), "João", LocalDate.of(1985, 5, 15), new BigDecimal("3500.00"), "Analista"));
        funcionarios.add(new Funcionario(ThreadLocalRandom.current().nextLong(99999999999L), "Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario(ThreadLocalRandom.current().nextLong(99999999999L), "João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario(ThreadLocalRandom.current().nextLong(99999999999L), "Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario(ThreadLocalRandom.current().nextLong(99999999999L), "Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario(ThreadLocalRandom.current().nextLong(99999999999L), "Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario(ThreadLocalRandom.current().nextLong(99999999999L), "Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario(ThreadLocalRandom.current().nextLong(99999999999L), "Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario(ThreadLocalRandom.current().nextLong(99999999999L), "Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario(ThreadLocalRandom.current().nextLong(99999999999L), "Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario(ThreadLocalRandom.current().nextLong(99999999999L), "Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));


        System.out.println("Funcionários:");
        funcionarios.forEach(System.out::println);

        // 3.2 – Remover o funcionário “João” da lista.
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // 3.3 – Imprimir todos os funcionários com todas suas informações
        System.out.println("\nFuncionários após remover João:");
        funcionarios.forEach(System.out::println);

        // 3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
        funcionarios.forEach(funcionario -> {
            BigDecimal aumento = funcionario.getSalario().multiply(new BigDecimal("0.10"));
            funcionario.setSalario(funcionario.getSalario().add(aumento));
        });

        // 3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 – Imprimir os funcionários, agrupados por função.
        System.out.println("\nFuncionários agrupados por função:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(System.out::println);
        });

        // // 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        List<Funcionario> aniversariantes10e12 = funcionarios.stream()
                .filter(funcionario -> {
                    LocalDate dataNascimento = funcionario.getDataNascimento();
                    return dataNascimento.getMonthValue() == 10 || dataNascimento.getMonthValue() == 12;
                })
                .collect(Collectors.toList());

        System.out.println("\nFuncionários com aniversário em outubro e dezembro:");
        aniversariantes10e12.forEach(System.out::println);

        // 3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
        LocalDate hoje = LocalDate.now();
        Funcionario maisVelho = funcionarios.stream()
                .max(Comparator.comparingInt(funcionario -> hoje.minusYears(funcionario.getDataNascimento().getYear()).getYear()))
                .orElse(null);

        if (maisVelho != null) {
            int idade = hoje.minusYears(maisVelho.getDataNascimento().getYear()).getYear();
            System.out.println("\nFuncionário mais velho:");
            System.out.println("Nome: " + maisVelho.getNome());
            System.out.println("Idade: " + idade + " anos");
        }

        // 3.10 – Imprimir a lista de funcionários por ordem alfabética.
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));
        System.out.println("\nFuncionários em ordem alfabética:");
        funcionarios.forEach(System.out::println);

        // 3.11 – Imprimir o total dos salários dos funcionários.
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos salários: " + new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR"))).format(totalSalarios));

        // 3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\nSalários em salários mínimos:");
        funcionarios.forEach(funcionario -> {
            BigDecimal salarioEmSalariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(funcionario.getNome() + ": " + salarioEmSalariosMinimos);
        });

        
    }
}
