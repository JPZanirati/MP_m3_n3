package cadastrobd;

import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridicaDAO;

/**
 *
 * @author JPZanirati
 */

public class CadastroBDTeste {
        public static void main(String[] args) {
            
        PessoaFisica pessoaFisica = new PessoaFisica(idPessoa, nome, cidade, endereco, uf, telefone, tipoPessoa, idFisica, cpf, dt_nasc);
        pessoaFisica.setNome("João da Silva");
        pessoaFisica.setCidade("Cidade A");
        pessoaFisica.setEndereco("Endereço A");
        pessoaFisica.setUf("UF A");
        pessoaFisica.setTelefone(123456789);
        pessoaFisica.setTipoPessoa("PF");
        pessoaFisica.setIdFisica(90);
        pessoaFisica.setCpf(1234567890);
        pessoaFisica.setDtNasc("05/12/1995");
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        
        pessoaFisicaDAO.incluir(pessoaFisica);

        pessoaFisica.setNome("Novo Nome Pessoa Física");
        pessoaFisicaDAO.alterar(pessoaFisica);

        System.out.println("Pessoas Físicas no banco de dados:");
        for (PessoaFisica pf : pessoaFisicaDAO.getPessoas()) {
            System.out.println(pf);
        }

        pessoaFisicaDAO.excluir(pessoaFisica);

        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setRazaoSocial("Empresa ABC");
        pessoaJuridica.setCnpj(1234567890);
        pessoaJuridica.setCidade("Cidade B");
        pessoaJuridica.setEndereco("Endereço B");
        pessoaJuridica.setUf("UF B");
        pessoaJuridica.setTelefone(987654321);
        pessoaJuridica.setTipoPessoa("PJ");
        
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
        pessoaJuridicaDAO.incluir(pessoaJuridica);

        pessoaJuridica.setNome("Novo Nome Pessoa Jurídica");
        pessoaJuridicaDAO.alterar(pessoaJuridica);

        System.out.println("Pessoas Jurídicas no banco de dados:");
        for (PessoaJuridica pj : pessoaJuridicaDAO.getPessoas()) {
            System.out.println(pj);
        }

        pessoaJuridicaDAO.excluir(pessoaJuridica);
    }
}
