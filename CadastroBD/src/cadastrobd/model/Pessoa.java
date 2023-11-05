package cadastrobd.model;

/**
 *
 * @author JPZanirati
 */

public class Pessoa {
    private int idPessoa;
    private String nome;
    private String cidade;
    private String endereco;
    private String uf;
    private int telefone;
    private String tipoPessoa;

    public Pessoa(int idPessoa, String nome, String cidade, String endereco, String uf, int telefone, String tipoPessoa) {
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.cidade = cidade;
        this.endereco = endereco;
        this.uf = uf;
        this.telefone = telefone;
        this.tipoPessoa = tipoPessoa;
    }
    
    public int getIdPessoa(){
        return idPessoa;
    }

    public String getNome(){
        return nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getCidade(){
        return cidade;
    }
    
    public void setCidade(String cidade){
        this.cidade = cidade;
    }
    
    public String getEndereco(){
        return endereco;
    }
    
    public void setEndereco(String endereco){
        this.endereco = endereco;
    }
    
    public String getUf(){
        return uf;
    }
    
    public void setUf(String uf){
        this.uf = uf;
    }
    
    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }
    
    public void exibir() {
        System.out.println("ID: " + idPessoa);
        System.out.println("Nome: " + nome);
        System.out.println("Cidade: " + cidade);
        System.out.println("Endereço: " + endereco);
        System.out.println("Estado: " + uf);
        System.out.println("Telefone: " + telefone);
        System.out.println("Tipo: " + tipoPessoa);
    }
}
