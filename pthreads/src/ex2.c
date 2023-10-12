/*
  Escreva um programa formado por três processos concorrentes,
  observador, tratador1 e tratador2, que executam um loop infinito, e
  que sincronizam suas ações com o uso de semáforos. O processo
  observador lê valores inteiros, que representam a temperatura de
  um certo dispositivo (rand()%100). Se o valor lido é menor ou igual
  a 40, deve ser notificado o processo tratador1. Caso o valor seja
  maior que 50, deve ser notificado o processo tratador2. Cada
  processo tratador deverá fazer um printf a cada 10 notificações
  recebidas. Escreva o programa usando semáforos pthreads.
*/

#include <pthread.h>
#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define QTD_NOTIFICACOES 10

sem_t semaforo_obs, semaforo_t1, semaforo_t2;
int contador_um = 0, contador_dois = 0;

void *observador(void *arg) {
    while (1) {
        int temperatura = rand() % 100;
        printf("Temperatura lida: %d\n", temperatura);

        if (temperatura <= 40) {
            sem_post(&semaforo_t1);
        } else if (temperatura > 50) {
            sem_post(&semaforo_t2);
        }

        sleep(1);
    }
}

void *tratador1(void *arg) {
    while (1) {
        sem_wait(&semaforo_t1);
        contador_um++;
        if (contador_um % QTD_NOTIFICACOES == 0) {
            printf("Tratador 1: Recebi %d notificações\n", contador_um);
        }
    }
}

void *tratador2(void *arg) {
    while (1) {
        sem_wait(&semaforo_t2);
        contador_dois++;
        if (contador_dois % QTD_NOTIFICACOES == 0) {
            printf("Tratador 2: Recebi %d notificações\n", contador_dois);
        }
    }
}

int main() {
    pthread_t t_obs, tt1, tt2;

    sem_init(&semaforo_obs, 0, 1);
    sem_init(&semaforo_t1, 0, 0);
    sem_init(&semaforo_t2, 0, 0);

    pthread_create(&t_obs, NULL, observador, NULL);
    pthread_create(&tt1, NULL, tratador1, NULL);
    pthread_create(&tt2, NULL, tratador2, NULL);

    pthread_join(t_obs, NULL);
    pthread_join(tt1, NULL);
    pthread_join(tt2, NULL);

    sem_destroy(&semaforo_obs);
    sem_destroy(&semaforo_t1);
    sem_destroy(&semaforo_t2);

    return 0;
}
