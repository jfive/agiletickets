package br.com.caelum.agiletickets.controllers;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: rodrigoalmeida
 * Date: 06/04/13
 * Time: 10:21
 * To change this template use File | Settings | File Templates.
 */
public class Extratora {


    private final HttpServletRequest rq;

    public Extratora(HttpServletRequest rq) {
        this.rq = rq;
    }

    public Double calcula() {

        String pS = rq.getParameter("preco");
        String qS = rq.getParameter("quantidade");
        Double preco = Double.parseDouble(pS);
        Double quantidade = Double.parseDouble(qS);
        Double precoTotal = preco * quantidade;

        return precoTotal;


    }

}
