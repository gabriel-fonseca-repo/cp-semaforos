package org.fonseca.ex3;

public class IntBuffer {

  private int buffer = 0;

  public int getBuffer() {
    return buffer;
  }

  public void setBuffer(int buffer) {
    this.buffer = buffer;
  }

  public void produzir(int i) {
    this.buffer = i;
  }

  public boolean temValor() {
    return this.buffer != 0;
  }

  public int consumir() {
    int returnValue = buffer;
    buffer = 0;
    return returnValue;
  }
}
