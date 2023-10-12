package org.fonseca.ex2;

import java.util.concurrent.Semaphore;

public class Tratador50 implements Runnable {

  private final Temperatura temperatura;

  private final Semaphore semaforo;

  private int notificacoes;

  public Tratador50(Temperatura recurso, Semaphore semaforo) {
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
        if (temperatura.isTemperaturaMaiorIgualA(50)) {
          this.notificacoes++;
        }
        if (this.notificacoes == 10) {
          System.out.println("Thread 2 -> \u001B[31mTemperatura maior que 50: " + temperatura.getTemperatura() + "\u001B[37m");
          this.notificacoes = 0;
        }
        semaforo.release();
      } catch (InterruptedException e) {
        System.err.println("Erro ao tentar adiquirir acesso à temperatura no Tratador50.");
      }
    }
  }
}
