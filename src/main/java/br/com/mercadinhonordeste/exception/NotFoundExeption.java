package br.com.mercadinhonordeste.exception;

public class NotFoundExeption extends RuntimeException {
    public NotFoundExeption(String s) {
        super(s);
    }
}
