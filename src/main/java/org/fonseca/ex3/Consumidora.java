package org.fonseca.ex3;

import java.util.concurrent.Semaphore;

public class Consumidora implements Runnable {

  private final IntBuffer b0;

  private final IntBuffer b1;

  private final Semaphore semaforo;

  public Consumidora(IntBuffer b0, IntBuffer b1, Semaphore semaforo) {
    this.b0 = b0;
    this.b1 = b1;
    this.semaforo = semaforo;
  }

  @Override
  @SuppressWarnings("InfiniteLoopStatement")
  public void run() {
    while (true) {
      try {
        semaforo.acquire();
        if (b0.temValor()) {
          System.out.println("\u001B[35mConsumindo de \u001B[33mb0: " + b0.consumir());
        }
        if (b1.temValor()) {
          System.out.println("\u001B[35mConsumindo de \u001B[36mb1: " + b1.consumir());
        }
        semaforo.release();
      } catch (InterruptedException e) {
        System.err.println("Erro ao consumir em c" + Thread.currentThread().getId() + ".");
      }
    }
  }
}
