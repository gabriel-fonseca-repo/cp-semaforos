package org.fonseca.ex1;

/*
  Escreva um programa formado por dois processos
  concorrentes (threads), leitor e impressor, que executam um
  loop infinito, e que sincronizam suas ações com o uso de
  semáforos. O processo leitor fica lendo caracteres do
  teclado (getchar()) e colocando em um buffer de N
  posições. Quando o buffer está cheio o ou o caracter de
  quebra de linha for encontrado (‘\n’) o processo impressor
  deve imprimir o conteúdo do buffer. Escreva o programa
  usando semáforos Pthreads e Java.Escreva um programa formado por dois processos
  concorrentes (threads), leitor e impressor, que executam um
  loop infinito, e que sincronizam suas ações com o uso de
  semáforos. O processo leitor fica lendo caracteres do
  teclado (getchar()) e colocando em um buffer de N
  posições. Quando o buffer está cheio o ou o caracter de
  quebra de linha for encontrado (‘\n’) o processo impressor
  deve imprimir o conteúdo do buffer. Escreva o programa
  usando semáforos Pthreads e Java.
*/

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
