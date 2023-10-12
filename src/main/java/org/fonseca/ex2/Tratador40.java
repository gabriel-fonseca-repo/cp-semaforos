package org.fonseca.ex2;

import java.util.concurrent.Semaphore;

public class Tratador40 implements Runnable {

  private final Temperatura temperatura;

  private final Semaphore semaforo;

  private int notificacoes;

  public Tratador40(Temperatura recurso, Semaphore semaforo) {
    this.semaforo = semaforo;
    this.temperatura = recurso;
    this.notificacoes = 0;
  }

  @Override
  @SuppressWarnings("InfiniteLoopStatement")
  public void run() {
    while (true) {
      try {
        semaforo.acquire();
        if (temperatura.isTemperaturaMenorIgualA(40)) {
          this.notificacoes++;
        }
        if (this.notificacoes == 10) {
          System.out.println("Thread 1 -> \u001B[34mTemperatura menor que 40: " + temperatura.getTemperatura() + "\u001B[37m");
          this.notificacoes = 0;
        }
        semaforo.release();
      } catch (InterruptedException e) {
        System.err.println("Erro ao tentar adiquirir acesso à temperatura no Tratador40.");
      }
    }
  }
}
