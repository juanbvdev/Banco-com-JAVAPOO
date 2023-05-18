package com.mycompany.javapoo;

/*Este é um programa Java que implementa um sistema básico de gerenciamento de usuários de um banco fictício chamado "Banco BertacoPOO". O programa possui uma interface gráfica simples usando caixas de diálogo do JOptionPane.

Aqui está a documentação do código, descrevendo suas principais funcionalidades:

Método cpfSemLetra(String cpf): Verifica se uma sequência de caracteres cpf contém letras. Retorna true se houver letras no CPF ou false caso contrário.

Método main(String[] args): Ponto de entrada do programa. Ele contém um loop principal que exibe um menu de opções para o usuário e executa as ações correspondentes com base na entrada do usuário.

Opção 1: Cadastro de usuários - Solicita ao usuário o nome, CPF e número de celular do novo usuário e cria um objeto usuario. Verifica se o CPF já existe no sistema e valida sua formatação. Em seguida, adiciona o novo usuário à lista de usuários.

Opção 2: Mostrar dados - Solicita uma senha de administrador e, se correta, permite a busca de usuários por CPF. Exibe os dados do usuário correspondente se o CPF for encontrado.

Opção 3: Alterar dados - Solicita uma senha de administrador e, se correta, permite a alteração dos dados do usuário, como adicionar crédito, debitar, alterar nome, celular, CPF ou excluir o usuário.

Opção 4: Login de usuário - Solicita o nome e CPF do usuário para efetuar o login. Permite adicionar dinheiro à conta, sacar dinheiro, transferir dinheiro para outra conta ou fazer logout.

Opção 0: Sair - Encerra o programa.

Classe usuario: Representa um usuário do banco e possui os seguintes atributos:

id (int): identificador único do usuário.
nome (String): nome do usuário.
cpf (String): CPF do usuário.
celular (String): número de celular do usuário.
saldo (double): saldo da conta do usuário.
A classe usuario também possui os métodos getter e setter para acessar e modificar os valores dos atributos.

Lista users: Uma lista de objetos usuario que armazena todos os usuários cadastrados no sistema.

Constante admPassword: Senha de administrador usada para autenticação em operações especiais.

O programa permite cadastrar usuários, exibir seus dados, realizar alterações nas informações, fazer login como usuário e executar operações básicas, como adicionar crédito, debitar, transferir dinheiro e fazer logout. Algumas operações exigem a senha de administrador para serem executadas.

É importante observar que o código fornecido tem algumas áreas que podem ser aprimoradas em termos de tratamento de erros, validação de entrada e organização do código. Além disso, o código pode ser refatorado para melhorar sua legibilidade e manutenibilidade.*/

import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class JavaPOO {

   
    public static boolean cpfSemLetra(String cpf){
        int c = 0;
        
        for(c = 0; c < 11; c++){
            if (!cpf.substring(0,11).matches("[0-9]*")){
                return true;
            }
        }
        
        return false;
    }
    
    
    public static void main(String[] args) {
    
        List <usuario> users = new ArrayList();
        
        final String admPassword = "000";
        
        boolean cpfTemLetra;
        
        int id=0, op =0;
        
        String dado;
        
        
        
        do{
        
            op = parseInt(JOptionPane.showInputDialog("Banco BertacoPOO\n\n1 - cadastro\n2 - mostrar dados\n3 - alterar dados\n4 - login\n0 - sair\nescolha: "));

            switch(op){
                case 1 -> {

                    usuario user = new usuario();

                    while(true){
                        dado = JOptionPane.showInputDialog("\nNome: ");
                        while(dado.isEmpty() == true){
                            dado = JOptionPane.showInputDialog("\nNome: ");
                        }
                        user.setNome(dado);
                        break;
                    }

                    while(true){
                        int i = 0;
                        
                        dado = JOptionPane.showInputDialog("CPF: ");
                        for(i = 0; i < users.size(); i++){
                            while(true){
                                if(users.get(i).getCpf().equals(dado)){
                                JOptionPane.showMessageDialog(null, "Desculpe, mas este CPF já consta no sistema.");
                                dado = JOptionPane.showInputDialog("CPF: ");
                                }else{
                                    break;
                                }
                            }
                            
                        }
                        if(dado.length() != 11){
                            JOptionPane.showMessageDialog(null, "Informe um CPF válido.");
                        }else{
                            cpfTemLetra = cpfSemLetra(dado);
                            if(cpfTemLetra == true){
                                JOptionPane.showMessageDialog(null, "Informe um CPF válido.");
                            }else{
                                break;
                            }
                        }
                    }
                    user.setCpf(dado);

                    dado = JOptionPane.showInputDialog("Celular: ");
                    user.setCelular(dado);
                    
                    user.setSaldo(0);
                    
                    id++;
                    user.setId(id);

                    users.add(user);
                    
                    break;
                }
                case 2 -> {
                    int i=0;
                    String buscaCPF, admTry;
                    boolean cpfMatch = false;
                    
                    admTry = JOptionPane.showInputDialog("Senha de administrador: ");
                    if(!admTry.equals(admPassword)){
                        JOptionPane.showMessageDialog(null, "Senha incorreta.");
                        break;
                    }
                    
                    buscaCPF = JOptionPane.showInputDialog("CPF: ");
                    for(i = 0; i < users.size(); i++){
                        if(users.get(i).getCpf().equals(buscaCPF)){
                            cpfMatch = true;
                            break;
                        }
                    }
                    
                    if(cpfMatch == true){
                        JOptionPane.showMessageDialog(null, users.get(i).toString());
                    }else{
                        JOptionPane.showMessageDialog(null, "Não foi possível encontrar o CPF \"" + buscaCPF + "\"");
                        break;
                    }
                }
                case 3 -> {
                    double valor, novo = 0;
                    int i, op2, confirm = 0;
                    String buscaCPF, novoDado, admTry;
                    boolean cpfMatch = false;
                    boolean logout = false;
                    
                    admTry = JOptionPane.showInputDialog("Senha de Administrador: ");
                    if(!admTry.equals(admPassword)){
                        JOptionPane.showMessageDialog(null, "Senha incorreta...");
                        break;
                    }
                    
                    buscaCPF = JOptionPane.showInputDialog("CPF: ");
                    for(i = 0; i < users.size(); i++){
                        
                        if(users.get(i).getCpf().equals(buscaCPF)){
                            cpfMatch = true;
                            break;
                        }
                    }
                        
                    if(cpfMatch == true){
                        JOptionPane.showMessageDialog(null, "Alterando Dados de " + users.get(i).getNome());
                    
                        while(true){
                            if(logout == true){
                                break;
                            }
                            
                            op2 = parseInt(JOptionPane.showInputDialog("Dados de " + users.get(i).getNome() + "\n1 - Adicionar crédito\n2 - Debitar\n3 - alterar nome\n4 - alterar celular\n5 - alterar CPF\n6 - deletar usuário\n0 - voltar\nEscolha: "));
                            switch(op2){
                                case 1 -> {
                                    JOptionPane.showMessageDialog(null, "Saldo atual de " + users.get(i).getNome() + ": " + users.get(i).getSaldo());
                                    valor = Double.parseDouble(JOptionPane.showInputDialog(null, "Crédito a adicionar: "));
                                    novo = users.get(i).getSaldo() + valor;
                                    users.get(i).setSaldo(novo);
                                    JOptionPane.showMessageDialog(null, "Saldo atual de " + users.get(i).getNome() + ": " + users.get(i).getSaldo());
                                }
                                case 2 -> {
                                    JOptionPane.showMessageDialog(null, "Saldo atual de " + users.get(i).getNome() + ": " + users.get(i).getSaldo());
                                    valor = Double.parseDouble(JOptionPane.showInputDialog(null, "Valor a debitar: "));
                                    novo = users.get(i).getSaldo() - valor;
                                    if(novo < 0){
                                        JOptionPane.showMessageDialog(null, "Desculpe, mas você não pode debitar este valor.");
                                        break;
                                    }
                                    users.get(i).setSaldo(novo);
                                    JOptionPane.showMessageDialog(null, "Saldo atual de " + users.get(i).getNome() + ": " + users.get(i).getSaldo());
                                }
                                case 3 -> {
                                    JOptionPane.showMessageDialog(null, "Nome atual: " + users.get(i).getNome());
                                    novoDado = JOptionPane.showInputDialog("Novo nome: ");
                                    confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que quer alterar o nome de \"" + users.get(i).getNome() + "\" para \"" + novoDado + "\"?");
                                    if(confirm != 0){
                                        JOptionPane.showMessageDialog(null, "Operação cancelada");
                                        break;
                                    }else{
                                        users.get(i).setNome(novoDado);
                                        JOptionPane.showMessageDialog(null, "Novo nome: " + novoDado);
                                    }
                                }
                                case 4 -> {
                                    JOptionPane.showMessageDialog(null, "Número atual de " + users.get(i).getNome() + ": " + users.get(i).getCelular());
                                    novoDado = JOptionPane.showInputDialog("Novo número: ");
                                    confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que quer alterar o celular para \"" + novoDado + "\"?");
                                    if(confirm != 0){
                                        JOptionPane.showMessageDialog(null, "Operação cancelada");
                                    }else{
                                        users.get(i).setCelular(novoDado);
                                        JOptionPane.showMessageDialog(null, "Novo Número: " + novoDado);
                                    }
                                }
                                case 5 -> {
                                    JOptionPane.showMessageDialog(null, "CPF atual de " + users.get(i).getNome() + ": " + users.get(i).getCpf());
                                    novoDado = JOptionPane.showInputDialog("Novo CPF: ");
                                    confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que quer alterar o CPF para \"" + novoDado + "\"?");
                                    if(confirm != 0){
                                        JOptionPane.showMessageDialog(null, "Operação cancelada");
                                    }else{
                                        users.get(i).setCpf(novoDado);
                                        JOptionPane.showMessageDialog(null, "Novo CPF: " + novoDado);
                                    }
                                }
                                case 6 -> {
                                    confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja deletar o usuário \"" + users.get(i).getNome() + "\"?");
                                    if(confirm != 0){
                                        JOptionPane.showMessageDialog(null, "Operação cancelada");
                                    }else{
                                        admTry = JOptionPane.showInputDialog("Senha de Administrador: ");
                                        if(!admTry.equals(admPassword)){
                                            JOptionPane.showMessageDialog(null, "Senha incorreta...");
                                            break;
                                        }
                                        users.remove(i);
                                        logout = true;
                                    }
                                }
                                case 0 -> {
                                    logout = true;
                                    break;
                                }
                            }
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Não foi possível encontrar o CPF \"" + buscaCPF + "\"");
                        break;
                    }
                }
                
                case 4 -> {
                    String nome;
                    String cpf;
                    int i, op2 = 0;
                    double valor, novo, trans = 0;
                    boolean logout = false;
                    boolean nomeMatch = false;
                    boolean cpfMatch = false;
                    
                    
                    nome = JOptionPane.showInputDialog("Nome: ");
                    for(i = 0; i < users.size(); i++){
                        if(users.get(i).getNome().equals(nome)){
                            nomeMatch = true;
                            break;
                        }
                    }
                    cpf = JOptionPane.showInputDialog("CPF: ");
                    for(i = 0; i < users.size(); i++){
                        if(users.get(i).getCpf().equals(cpf)){
                            cpfMatch = true;
                            break;
                        }
                    }
                    
                    if(nomeMatch == true && cpfMatch == true){
                        while(true){

                            if(logout == true){
                                break;
                            }

                            op2 = parseInt(JOptionPane.showInputDialog(null, "Bem Vindo " + nome + "\n1 - acrescentar dinheiro\n2 - sacar dinheiro\n3 - transferir dinheiro\n4 - logout\nEscolha: "));

                            switch(op2){
                                case 1 -> {
                                    JOptionPane.showMessageDialog(null, "Seu saldo atual: " + users.get(i).getSaldo());
                                    valor = Double.parseDouble(JOptionPane.showInputDialog(null, "Dinheiro à acrescentar: "));
                                    novo = users.get(i).getSaldo() + valor;
                                    users.get(i).setSaldo(novo);
                                    JOptionPane.showMessageDialog(null, "Seu saldo atual: " + users.get(i).getSaldo());
                                }
                                case 2 -> {
                                    JOptionPane.showMessageDialog(null, "Seu saldo atual: " + users.get(i).getSaldo());
                                    valor = Double.parseDouble(JOptionPane.showInputDialog(null, "Saque: "));
                                    novo = users.get(i).getSaldo() - valor;
                                    if(novo < 0){
                                        JOptionPane.showMessageDialog(null, "Você não saldo o suficiente.");
                                        break;
                                    }
                                    users.get(i).setSaldo(novo);
                                    JOptionPane.showMessageDialog(null, "Seu saldo atual: " + users.get(i).getSaldo());
                                }
                                case 3 -> {
                                    String buscaCPF;
                                    int c, confirm = 0;

                                    JOptionPane.showMessageDialog(null, "Insira o CPF da conta para qual você deseja transferir.");
                                    buscaCPF = JOptionPane.showInputDialog("CPF: ");
                                    for(c = 0; c < users.size(); c++){
                                        if(users.get(c).getCpf().equals(buscaCPF)){
                                            confirm = JOptionPane.showConfirmDialog(null, "Você deseja transferir para " + users.get(c).getNome() + "?");
                                            if(confirm != 0){
                                                JOptionPane.showMessageDialog(null, "Transação cancelada.");
                                            }else{
                                                valor = parseInt(JOptionPane.showInputDialog("Valor a ser transferido: "));
                                                novo = users.get(i).getSaldo() - valor;
                                                trans = users.get(c).getSaldo() + valor;
                                                if(novo < 0){
                                                    JOptionPane.showMessageDialog(null, "Você não saldo o suficiente.");
                                                    break;
                                                }
                                                users.get(c).setSaldo(trans);
                                                users.get(i).setSaldo(novo);
                                                JOptionPane.showMessageDialog(null, "Transferência concluída!\nSeu saldo atual: " + users.get(i).getSaldo());
                                            }
                                        }
                                    }
                                }
                                case 4 -> {
                                    JOptionPane.showMessageDialog(null, "Até mais " + nome + "!");
                                    logout = true;
                                }
                            }
                        }
                    }
                        
                }
                
                case 0 -> {
                    
                    JOptionPane.showMessageDialog(null, "Obrigado por usar Banco BertacoPOO");
                }
            }
        }while(op != 0);
    }
}
