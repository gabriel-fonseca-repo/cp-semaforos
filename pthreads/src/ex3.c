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
  buffer.Escreva o programa usando semáforos pthreads.
*/

#include <pthread.h>
#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>

#define CAPACIDADE 1

int b0, b1;
sem_t sem_b0, sem_b1, sem_consumidor;

void *produtor(void *arg) {
    int id = *((int *)arg);
    while (1) {
        int item = rand() % 100;

        if (id == 0) {
            sem_wait(&sem_b0);
            b0 = item;
            sem_post(&sem_consumidor);

        } else {
            sem_wait(&sem_b1);
            b1 = item;
            sem_post(&sem_consumidor);
        }
    }
}

void *consumidor(void *arg) {
    while (1) {
        sem_wait(&sem_consumidor);

        if (b0 != -1) {
            printf("Consumidor: Consumiu de b0: %d\n", b0);
            b0 = -1;
            sem_post(&sem_b0);
        }

        if (b1 != -1) {
            printf("Consumidor: Consumiu de b1: %d\n", b1);
            b1 = -1;
            sem_post(&sem_b1);
        }
    }
}

int main() {
    pthread_t produtor0, produtor1, consumidor_thread;
    int produtor_um = 0;
    int produtor_dois = 1;

    b0 = -1;
    b1 = -1;

    sem_init(&sem_b0, 0, CAPACIDADE);
    sem_init(&sem_b1, 0, CAPACIDADE);
    sem_init(&sem_consumidor, 0, 0);

    pthread_create(&produtor0, NULL, produtor, &produtor_um);
    pthread_create(&produtor1, NULL, produtor, &produtor_dois);
    pthread_create(&consumidor_thread, NULL, consumidor, NULL);

    pthread_join(produtor0, NULL);
    pthread_join(produtor1, NULL);
    pthread_join(consumidor_thread, NULL);

    sem_destroy(&sem_b0);
    sem_destroy(&sem_b1);
    sem_destroy(&sem_consumidor);

    return 0;
}
