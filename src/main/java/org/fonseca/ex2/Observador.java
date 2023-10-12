package org.fonseca.ex2;

import java.util.concurrent.Semaphore;

public class Observador implements Runnable {

  private final Temperatura temperatura;

  private final Semaphore semaforo;

  public Observador(Temperatura recurso, Semaphore semaforo) {
    this.semaforo = semaforo;
    this.temperatura = recurso;
  }

  @Override
  @SuppressWarnings("InfiniteLoopStatement")
  public void run() {
    while (true) {
      try {
        semaforo.acquire();
        temperatura.lerNovaTemperatura();
        semaforo.release();
      } catch (InterruptedException e) {
        System.err.println("Erro ao tentar adiquirir acesso à temperatura no Observador.");
      }
    }

  }
}
