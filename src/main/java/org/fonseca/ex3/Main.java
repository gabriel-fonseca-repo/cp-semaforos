package org.fonseca.ex3;

/*
  Escreva um programa concorrente formado por três threads, uma
  consumidora (c) e duas produtoras (p0 e p1), que executam um
  loop eterno. A thread consumidora recebe informações (um valor
  inteiro) da thread p0 no buffer b0 e da thread p1 no buffer b1
  (primeiro consome de b0 e depois consome de b1). Os buffers b0 e
  b1 possuem capacidade de armazenar um único elemento. A thread
  consumidora somente pode consumir se existirem informações no
  buffer e as threads produtoras somente podem voltar a produzir
  depois da thread consumidora haver retirado as informações do
  buffer. Escreva o programa usando semáforos Pthreads ou Java.
*/

import java.util.concurrent.Semaphore;

public class Main {

  private static final IntBuffer b0 = new IntBuffer();
  private static final IntBuffer b1 = new IntBuffer();
  private static final Semaphore semaforo = new Semaphore(1, true);

  public static void main(String[] args) {

    Produtora p0 = new Produtora(b0, semaforo);
    Produtora p1 = new Produtora(b1, semaforo);

    Consumidora c = new Consumidora(b0, b1, semaforo);

    Thread tP0 = new Thread(p0);
    Thread tP1 = new Thread(p1);
    Thread tC = new Thread(c);

    tP0.start();
    tP1.start();
    tC.start();

    while (true) {

    }
  }
}
