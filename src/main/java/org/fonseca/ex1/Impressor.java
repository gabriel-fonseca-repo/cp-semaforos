package org.fonseca.ex1;

import java.util.concurrent.Semaphore;

public class Impressor implements Runnable {

  private final Recurso recurso;

  private final Semaphore semaforo;

  public Impressor(Recurso recurso, Semaphore semaforo) {
    this.recurso = recurso;
    this.semaforo = semaforo;
  }

  @Override
  @SuppressWarnings("InfiniteLoopStatement")
  public void run() {
    while (true) {
      try {
        semaforo.acquire();
        System.out.printf("\r\u001B[35mX -> Impressor -> %-10.10s -> tem o semáforo.\n", recurso.getValor());
        imprimirValor();
      } catch (InterruptedException | StringIndexOutOfBoundsException e) {
        System.err.println("Erro ao tentar adiquirir acesso ao recurso no impressor.");
      } finally {
        System.out.printf("\r\u001B[35mX -> Impressor -> %-10.10s -> solta o semáforo.\n", recurso.getValor());
        semaforo.release();
      }
      System.out.flush();
    }
  }

  private void imprimirValor() {
    if (recurso.ehPrintavel()) {
      if (recurso.getValor().charAt(recurso.getValor().length() - 1) == '\n') {
        System.out.print("String digitada (" + recurso.getValor().length() + "): " + recurso.getValor());
      } else {
        System.out.println("String digitada (" + recurso.getValor().length() + "): " + recurso.getValor());
      }
    }
  }
}
