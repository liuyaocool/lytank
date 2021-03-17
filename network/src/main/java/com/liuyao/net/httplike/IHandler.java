package com.liuyao.net.httplike;

@FunctionalInterface
public interface IHandler {

    void handler(Request request, Response response);

}
