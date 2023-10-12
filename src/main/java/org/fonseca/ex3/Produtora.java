package org.fonseca.ex3;

import java.util.concurrent.Semaphore;

public class Produtora implements Runnable {

  private final IntBuffer b;

  private final Semaphore semaforo;

  public Produtora(IntBuffer b, Semaphore semaforo) {
    this.b = b;
    this.semaforo = semaforo;
  }

  @Override
  @SuppressWarnings("InfiniteLoopStatement")
  public void run() {
    while (true) {
      try {
        semaforo.acquire();
        if (!b.temValor()) {
          b.produzir(1);
          System.out.println("\u001B[32mProduzindo em b1: " + 1);
        }
        semaforo.release();
      } catch (InterruptedException e) {
        System.err.println("Erro ao produzir em b" + Thread.currentThread().getId() + ".");
      }
    }
  }
}
