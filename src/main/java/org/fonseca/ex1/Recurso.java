package org.fonseca.ex1;

public class Recurso {

  private String valor;

  public Recurso() {
    this.valor = "";
  }

  public boolean ehPrintavel() {
    if (this.getValor() != null && !this.getValor().isEmpty()) {
      char ultimoCaractere = this.getValor().charAt(this.getValor().length() - 1);
      return ultimoCaractere == '\n' || this.isMaiorQueCinquenta();
    }
    return false;
  }

  public void append(String ap) {
    this.valor = valor + ap;
  }

  public void append(char ap) {
    this.valor = valor + ap;
  }

  public boolean isMaiorQueCinquenta() {
    return this.valor.length() >= 50;
  }

  public String getValor() {
    return this.valor;
  }

  public void setValor(String valor) {
    this.valor = valor;
  }

}
