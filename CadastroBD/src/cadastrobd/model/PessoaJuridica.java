package cadastrobd.model;

/**
 *
 * @author JPZanirati
 */

public class PessoaJuridica extends Pessoa {
    private int idJuridica;
    private long cnpj;
    private String razaoSocial;

    public PessoaJuridica(int idPessoa, String nome, String cidade, String endereco, String uf, int telefone, String tipoPessoa, int idJuridica, long cnpj, String razaoSocial) {
        super(idPessoa, nome, cidade, endereco, uf, telefone, tipoPessoa);
        this.idJuridica = idJuridica;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
    }

    public int getIdJuridica() {
        return idJuridica;
    }

    public void setIdJuridica(int idJuridica) {
        this.idJuridica = idJuridica;
    }

    public long getCnpj() {
        return cnpj;
    }

    public void setCnpj(long cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
    
    @Override
    public void exibir() {
        super.exibir();
        System.out.println("Id Pessoa Juridica: " + idJuridica);
        System.out.println("CNPJ: " + cnpj);
        System.out.println("Razão Social: " + razaoSocial);
    }
}
