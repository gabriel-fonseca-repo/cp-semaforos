import java.io.IOException;

public class Teste {

  public static void main(String[] args) {
    System.out.println("Digite caracteres (Pressione Enter para finalizar):");

    try {
      while (true) {
        // Lê o próximo caractere do console
        int charCode = System.in.read();

        // Verifica se o Enter foi pressionado para finalizar o loop
        if (charCode == '\n' || charCode == '\r') {
          break;
        }

        // Converte o código do caractere para char e imprime
        char character = (char) charCode;
        System.out.print("Você digitou: " + character + "\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
