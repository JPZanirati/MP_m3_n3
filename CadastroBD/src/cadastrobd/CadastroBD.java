package cadastrobd;

import cadastro.model.util.ConectorBD;
import cadastro.model.util.SequenceManager;
import java.sql.Connection;
import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaJuridicaDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author JPZanirati
 */

public class CadastroBD {
   public static void main(String[] args) {
        Connection connection = null;
                        
        try {
            connection = ConectorBD.getConnection();
            Scanner scanner = new Scanner(System.in);
            SequenceManager sequence = new SequenceManager();
                    
            while (true) {
                System.out.println("Opções:");
                System.out.println("1 - Incluir Pessoa");
                System.out.println("2 - Alterar Pessoa");
                System.out.println("3 - Excluir Pessoa");
                System.out.println("4 - Buscar pelo ID");
                System.out.println("5 - Exibir Todos");
                System.out.println("0 - Sair");
                System.out.println("Selecione uma opção:");
                
                int opcao;
                opcao = scanner.nextInt();
                
                if (opcao == 0) {
                    break;
                }
                
                switch (opcao) {
                    case 1 -> {
                        System.out.println("Escolha o tipo (1 - Pessoa Física, 2 - Pessoa Jurídica):");
                        
                        int tipo = scanner.nextInt();
                        int idPessoa = sequence.getValue();
                        
                        if (tipo == 1) {
                            int idFisica = sequence.getValue();
                            
                            scanner.nextLine();
                            System.out.print("Nome: ");                           
                            String nome = scanner.nextLine();
                            System.out.print("Cidade: ");
                            String cidade = scanner.nextLine();
                            System.out.print("Endereço: ");
                            String endereco = scanner.nextLine();
                            System.out.print("UF: ");
                            String uf = scanner.nextLine();
                            System.out.print("Telefone: ");
                            int telefone = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("CPF: ");
                            long cpf = scanner.nextLong();
                            scanner.nextLine();
                            System.out.print("Tipo Pessoa: ");
                            String tipoPessoa = scanner.nextLine();
                            System.out.print("Data de nascimento: ");
                            String date = scanner.nextLine();
                            
                            SimpleDateFormat formatter = new SimpleDateFormat(date);
                            Date dt_nasc = formatter.parse(date);
                            
                            PessoaFisica pessoaFisica = new PessoaFisica(idPessoa, nome, cidade, endereco, uf, telefone, tipoPessoa, idFisica, cpf, dt_nasc);
                            
                            PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
                            pessoaFisicaDAO.incluir(pessoaFisica);
                            
                            scanner.close();
                            
                        } else if (tipo == 2) {
                            int idJuridica = sequence.getValue();
                            
                            scanner.nextLine();
                            System.out.print("Nome: ");                           
                            String nome = scanner.nextLine();
                            System.out.print("Cidade: ");
                            String cidade = scanner.nextLine();
                            System.out.print("Endereço: ");
                            String endereco = scanner.nextLine();
                            System.out.print("UF: ");
                            String uf = scanner.nextLine();
                            System.out.print("Telefone: ");
                            int telefone = scanner.nextInt();
                            System.out.print("Tipo Pessoa: ");
                            String tipoPessoa = scanner.nextLine();
                            System.out.print("Cnpj: ");
                            int cnpj = scanner.nextInt();
                            System.out.print("Razão Social: ");
                            String razaoSocial = scanner.nextLine();
                            PessoaJuridica pessoaJuridica = new PessoaJuridica(idPessoa, nome, cidade, endereco, uf, telefone, tipoPessoa, idJuridica, cnpj, razaoSocial);
                            
                            PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
                            pessoaJuridicaDAO.incluir(pessoaJuridica);
                            
                            scanner.close();

                        }
                    }
                        
                    case 2 -> {
                        System.out.println("Escolha o tipo (1 - Pessoa Física, 2 - Pessoa Jurídica):");
                        int tipo = scanner.nextInt();
                        
                        if (tipo == 1) {
                            
                            scanner.nextLine();
                            System.out.print("ID: ");
                            int idPessoa = scanner.nextInt();
                            PessoaFisica pessoaFisica = PessoaFisicaDAO.getPessoa(idPessoa);
                            pessoaFisica.exibir();

                            scanner.nextLine();
                            System.out.print("Nome: ");                           
                            String nome = scanner.nextLine();
                            System.out.print("Cidade: ");
                            String cidade = scanner.nextLine();
                            System.out.print("Endereço: ");
                            String endereco = scanner.nextLine();
                            System.out.print("UF: ");
                            String uf = scanner.nextLine();
                            System.out.print("Telefone: ");
                            int telefone = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Tipo Pessoa: ");
                            String tipoPessoa = scanner.nextLine();
                            System.out.print("CPF: ");
                            long cpf = scanner.nextLong();
                            scanner.nextLine();
                            System.out.print("Data de nascimento: ");
                            String date = scanner.nextLine();
                            SimpleDateFormat formatter = new SimpleDateFormat(date);
                            Date dt_nasc = formatter.parse(date);
                            PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO(idPessoa, nome, cidade, endereco, uf, telefone, tipoPessoa, idFisica, cpf, dt_nasc);
                            pessoaFisicaDAO.alterar(pessoaFisica);
                            
                            scanner.close();

                        } else if (tipo == 2) {
                            scanner.nextLine();
                            System.out.print("ID: ");
                            int idPessoa = scanner.nextInt();
                            
                            PessoaJuridica pessoaJuridica = PessoaJuridicaDAO.getPessoa(idPessoa);
                            pessoaJuridica.exibir();
                            
                            scanner.nextLine();
                            System.out.print("Nome: ");                           
                            String nome = scanner.nextLine();
                            System.out.print("Cidade: ");
                            String cidade = scanner.nextLine();
                            System.out.print("Endereço: ");
                            String endereco = scanner.nextLine();
                            System.out.print("UF: ");
                            String uf = scanner.nextLine();
                            System.out.print("Telefone: ");
                            int telefone = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Tipo Pessoa: ");
                            String tipoPessoa = scanner.nextLine();                          
                            System.out.print("CNPJ: ");
                            long cnpj = scanner.nextLong();
                            scanner.nextLine();
                            System.out.print("Razão Social: ");
                            String razaoSocial = scanner.nextLine();

                            PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO(idPessoa, nome, cidade, endereco, uf, telefone, tipoPessoa, idJuridica, cnpj, razaoSocial);
                            pessoaJuridicaDAO.alterar(pessoaJuridica);
                        }
                    }
                        
                    case 3 -> {
                        System.out.println("Escolha o tipo (1 - Pessoa Física, 2 - Pessoa Jurídica):");
                        int tipoExclusao = scanner.nextInt();
                        
                        if (tipoExclusao == 1) {
                            
                            scanner.nextLine();
                            System.out.print("ID que seja excluir: ");
                            int idPessoa = scanner.nextInt();
                            
                            PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
                            PessoaFisica pessoaFisica = PessoaFisicaDAO.getPessoa(idPessoa);
                            pessoaFisica.exibir();
                            
                            System.out.print("Tem certeza que deseja excluir? (S para Sim, N para Não): ");
                            String confirm = scanner.next();
                            
                            if (confirm.equalsIgnoreCase("S")) {
                                
                                pessoaFisicaDAO.excluir(pessoaFisica);

                                System.out.println("Pessoa Física excluída com sucesso.");
                            } else {
                            System.out.println("Exclusão cancelada.");
                            }
                           
                        } else if (tipoExclusao == 2) {
                            
                            scanner.nextLine();
                            System.out.print("ID que seja excluir: ");
                            int idPessoa = scanner.nextInt();
                            
                            PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
                            PessoaJuridica pessoaJuridica = PessoaJuridicaDAO.getPessoa(idPessoa);
                            pessoaJuridica.exibir();
                            
                            System.out.print("Tem certeza que deseja excluir? (S para Sim, N para Não): ");
                            String confirm = scanner.next();
                            
                            if (confirm.equalsIgnoreCase("S")) {
                                
                                pessoaJuridicaDAO.excluir(pessoaJuridica);

                                System.out.println("Pessoa Física excluída com sucesso.");
                            } else {
                            System.out.println("Exclusão cancelada.");
                            }
                        }
                    }
                        
                    case 4 -> {
                        System.out.println("Escolha o tipo (1 - Pessoa Física, 2 - Pessoa Jurídica):");
                        int tipoObter = scanner.nextInt();
                        
                        if (tipoObter == 1) {
                           scanner.nextLine();
                           
                            System.out.print("ID que seja exibir: ");
                            int idPessoa = scanner.nextInt();
                            
                            PessoaFisica pessoaFisica = PessoaFisicaDAO.getPessoa(idPessoa);
                            pessoaFisica.exibir();
                            
                        } else if (tipoObter == 2) {
                            scanner.nextLine();
                           
                            System.out.print("ID que seja exibir: ");
                            int idPessoa = scanner.nextInt();
                            
                            PessoaJuridica pessoaJuridica = PessoaJuridicaDAO.getPessoa(idPessoa);
                            pessoaJuridica.exibir();
                        }
                    }
                        
                    case 5 -> {
                        System.out.println("Escolha o tipo (1 - Pessoa Física, 2 - Pessoa Jurídica):");
                        int tipoObterTodos = scanner.nextInt();
                        
                        if (tipoObterTodos == 1) {
                            PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
                            pessoaFisicaDAO.getPessoas();
                            
                        } else if (tipoObterTodos == 2) {
                            PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();
                            pessoaJuridicaDAO.getPessoas();
                        }
                    }
                        
                    default -> System.out.println("Opção inválida.");
                }
            
            ConectorBD.close(connection);
            scanner.close();
        }   
        } catch (ParseException e) {
            System.out.println(e);
        }
    }
}

