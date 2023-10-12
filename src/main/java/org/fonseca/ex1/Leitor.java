package org.fonseca.ex1;

import java.io.IOException;
import java.util.concurrent.Semaphore;

public class Leitor implements Runnable {

  private final Semaphore semaforo;

  private final Recurso recurso;

  public Leitor(Recurso recurso, Semaphore semaforo) {
    this.recurso = recurso;
    this.semaforo = semaforo;
  }

  @Override
  @SuppressWarnings("InfiniteLoopStatement")
  public void run() {
    while (true) {
      try {
        char caractere = lerProximoCaractere();
        semaforo.acquire();
        recurso.append(caractere);
      } catch (InterruptedException ignored) {
        System.err.println("Erro ao tentar adiquirir acesso ao input no leitor.");
      } finally {
        semaforo.release();
      }
    }
  }

  private char lerProximoCaractere() {
    try {
      return (char) System.in.read();
    } catch (IOException ignored) {
      System.err.println("Erro ao ler o caractere.");
    }
    return ' ';
  }

}