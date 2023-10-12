/*
  Escreva um programa formado por dois processos
  concorrentes (threads), leitor e impressor, que executam um
  loop infinito, e que sincronizam suas ações com o uso de
  semáforos. O processo leitor fica lendo caracteres do
  teclado (getchar()) e colocando em um buffer de N
  posições. Quando o buffer está cheio o ou o caracter de
  quebra de linha for encontrado (‘\n’) o processo impressor
  deve imprimir o conteúdo do buffer. Escreva o programa
  usando semáforos pthreads.
*/

#include <pthread.h>
#include <semaphore.h>
#include <stdio.h>
#include <string.h>

#define N 10

char buffer[N];
int count = 0;

sem_t sem_leitor, sem_impressor;

void *leitor(void *arg) {
    char c;
    while (1) {
        c = getchar();
        sem_wait(&sem_leitor);
        buffer[count++] = c;

        if (count == N || c == '\n') {
            sem_post(&sem_impressor);
            count = 0;
        } else {
            sem_post(&sem_leitor);
        }
    }
}

void *impressor(void *arg) {
    while (1) {
        sem_wait(&sem_impressor);
        printf("Conteúdo do buffer: %s\n", buffer);
        memset(buffer, 0, N);
        sem_post(&sem_leitor);
    }
}

int main() {
    pthread_t leitor_thread, impressor_thread;

    sem_init(&sem_leitor, 0, 1);
    sem_init(&sem_impressor, 0, 0);

    pthread_create(&leitor_thread, NULL, leitor, NULL);
    pthread_create(&impressor_thread, NULL, impressor, NULL);

    pthread_join(leitor_thread, NULL);
    pthread_join(impressor_thread, NULL);

    sem_destroy(&sem_leitor);
    sem_destroy(&sem_impressor);

    return 0;
}
