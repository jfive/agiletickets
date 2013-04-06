package br.com.caelum.agiletickets.controllers;

import junit.framework.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: rodrigoalmeida
 * Date: 06/04/13
 * Time: 10:24
 * To change this template use File | Settings | File Templates.
 */
public class ExtratoraTest {


    @Test
    public void calculaValorTest() {

        HttpServletRequest rq = Mockito.mock(HttpServletRequest.class);
        Mockito.when(rq.getParameter("preco")).thenReturn("40");
        Mockito.when(rq.getParameter("quantidade")).thenReturn("5");
        Extratora e = new Extratora(rq);

        Double r = e.calcula();
        Assert.assertEquals(200D,r);

    }

}
