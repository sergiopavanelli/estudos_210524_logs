import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner teclado = new Scanner(System.in);

        System.out.println ("Digite seu nome: ");
        String nome = teclado.nextLine();

        boolean authenticated = false;

        while (!authenticated) {
            
            System.out.println ("Digite seu e-mail: ");
            String email = teclado.nextLine();
            if ("sair".equalsIgnoreCase(email)) {
                System.out.println ("Programa encerrado.");
                break;

            }
                        
            System.out.println ("Digite sua senha: ");
            String senha = teclado.nextLine();
            if ("sair".equalsIgnoreCase(senha)) {
                System.out.println ("Programa encerrado.");
                break;
            }


            if (senha.equals(LoginSistema.correctPassword) && email.equals(LoginSistema.correctEmail)) {
                System.out.println ("Login efetuado com sucesso!");
                System.out.println ("Bem-vindo " + nome + "!");
                authenticated = true;
            } else {
                System.out.println ("Email ou Senha incorreta. Tente novamente ou escolha 'sair' para encerrar o programa.");
                logFailedAttempt (email, nome, senha);
            }
        }

        teclado.close();
    }

    private static void logFailedAttempt(String email, String nome, String senha) {
        try (FileWriter logFile = new FileWriter(LoginSistema.logFile, true);
             PrintWriter logWriter = new PrintWriter(logFile)) {
            logWriter.println("Tentativa de login falhada:");
            logWriter.println("Email: " + email);
            logWriter.println("Nome: " + nome);
            logWriter.println("Senha incorreta: " + senha);
            logWriter.println("---------------");
        } catch (IOException e) {
            System.err.println("Erro ao gravar no arquivo de log: " + e.getMessage());
        }
    }
}



