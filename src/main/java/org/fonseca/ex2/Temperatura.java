package org.fonseca.ex2;

public class Temperatura {

  private int temperatura;

  public void lerNovaTemperatura() {
    this.temperatura = (int) (Math.random() * 100);
  }

  public boolean isTemperaturaMaiorIgualA(int x) {
    return this.temperatura >= x;
  }

  public boolean isTemperaturaMenorIgualA(int x) {
    return this.temperatura <= x;
  }

  public Temperatura() {
    this.temperatura = 0;
  }

  public int getTemperatura() {
    return this.temperatura;
  }

  public void setTemperatura(int temperatura) {
    this.temperatura = temperatura;
  }

}
