package projedata_tests.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.persistence.*;

@Table(name = "pessoa")
@MappedSuperclass
public class Pessoa {
    @Id
    @Column(name = "cpf")
    private Long cpf;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    public Pessoa() {
    }

    public Pessoa(Long cpf, String nome, LocalDate dataNascimento) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public Long getCpf() {
        return cpf;
    }
    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "cpf=" + cpf +
                ", nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                '}';
    }
}
