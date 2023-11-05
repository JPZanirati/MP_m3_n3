package cadastrobd.model;

import java.util.Date;

/**
 *
 * @author JPZanirati
 */

public class PessoaFisica extends Pessoa {
    private int idFisica;
    private long cpf;
    private Date dt_nasc;    

    public PessoaFisica(int idPessoa, String nome, String cidade, String endereco, String uf, int telefone, String tipoPessoa, int idFisica, long cpf, Date dt_nasc) {
        super(idPessoa, nome, cidade, endereco, uf, telefone, tipoPessoa);
        this.idFisica = idFisica;
        this.cpf = cpf;
        this.dt_nasc = dt_nasc;
    }

    public int getIdFisica() {
        return idFisica;
    }

    public void setIdFisica(int idFisica) {
        this.idFisica = idFisica;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public java.sql.Date getDtNasc() {
        return new java.sql.Date(dt_nasc.getTime()) ;
    }

    public void setDtNasc(Date dt_nasc) {
        this.dt_nasc = dt_nasc;
    }
    
    @Override
    public void exibir() {
        super.exibir();
        System.out.println("ID Pessoa Fisica: " + idFisica);
        System.out.println("CPF: " + cpf);
        System.out.println("Data de Nascimento: " + dt_nasc);
    }
}

