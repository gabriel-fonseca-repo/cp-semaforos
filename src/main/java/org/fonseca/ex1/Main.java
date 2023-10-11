package org.fonseca.ex1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.Semaphore;

public class Main {

  private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static final Recurso recurso = new Recurso();
  private static final Semaphore semaforo = new Semaphore(1, true);

  public static void main(String[] args) {
    Thread tLeitor = new Thread(new Leitor(recurso, semaforo));
    Thread tImpressor = new Thread(new Impressor(recurso, semaforo));

    tLeitor.start();
    tImpressor.start();

    while (true) {

    }
  }
}
