package projedata_tests.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javax.persistence.*;

@Entity
@Table(name = "funcionario") 
public class Funcionario extends Pessoa {
    @Column(name = "salario")
    private BigDecimal salario;

    @Column(name = "funcao")
    private String funcao;
    

    public Funcionario() {
    }

    public Funcionario(Long cpf,String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(cpf,nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public String getFuncao() {
        return this.funcao;
    }

    public BigDecimal getSalario() {
        return this.salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }
    @Override
    public String toString() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        DecimalFormat df = new DecimalFormat("#,##0.00", symbols);
        return "Funcionario{" +
                "id=" + getCpf() + // Inherited from Pessoa
                ", nome='" + getNome() + '\'' +
                ", dataNascimento=" + getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                ", salario=" + df.format(salario) + // Format the salary using DecimalFormat
                ", funcao='" + funcao + '\'' +
                '}';
    }
}
