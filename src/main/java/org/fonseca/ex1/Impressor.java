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
        if (recurso.ehPrintavel()) {
          recurso.printarResetar();
        }
      } catch (InterruptedException | StringIndexOutOfBoundsException e) {
        System.err.println("Erro ao tentar adiquirir acesso ao recurso no impressor.");
      } finally {
        semaforo.release();
      }
    }
  }

}
