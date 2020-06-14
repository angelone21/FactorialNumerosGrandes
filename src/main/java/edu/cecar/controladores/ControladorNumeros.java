/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecar.controladores;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author Vincenzo Angelone
 */
public class ControladorNumeros {

    public ArrayList<BigInteger> obtenerNumeros(BigInteger n) {
        ArrayList<BigInteger> lista = new ArrayList<BigInteger>();
        for (BigInteger i = n; i.compareTo(BigInteger.ONE) >= 0; i = i.subtract(BigInteger.valueOf(2))) {
            lista.add(i);
        }
        return lista;
    }

    public BigInteger multiplicar(BigInteger n1, BigInteger n2) {
        BigInteger resultado = n1.multiply(n2);
        return resultado;
    }

}
