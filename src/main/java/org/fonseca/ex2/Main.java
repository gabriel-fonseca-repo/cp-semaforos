package org.fonseca.ex2;

/*
  Escreva um programa formado por três processos concorrentes,
  observador, tratador1 e tratador2, que executam um loop infinito, e
  que sincronizam suas ações com o uso de semáforos. O processo
  observador lê valores inteiros, que representam a temperatura de
  um certo dispositivo (rand()%100). Se o valor lido é menor ou igual
  a 40, deve ser notificado o processo tratador1. Caso o valor seja
  maior que 50, deve ser notificado o processo tratador2. Cada
  processo tratador deverá fazer um printf a cada 10 notificações
  recebidas. Escreva o programa usando semáforos Pthreads e
  Java.
*/

import java.util.concurrent.Semaphore;

public class Main {

  private static final Temperatura temperatura = new Temperatura();
  private static final Semaphore semaforo = new Semaphore(1, true);

  public static void main(String[] args) {
    Thread tObservador = new Thread(new Observador(temperatura, semaforo));
    Thread tTratador40 = new Thread(new Tratador40(temperatura, semaforo));
    Thread tTratador50 = new Thread(new Tratador50(temperatura, semaforo));

    tObservador.start();
    tTratador40.start();
    tTratador50.start();

    while (true) {

    }
  }
}
