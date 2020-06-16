/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecar.modelo;

import edu.cecar.controladores.ControladorNumeros;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 * *
 * @author Vincenzo Angelone
 */
public class FactorialHilo extends Thread {

    public BigInteger resultadoAnterior = new BigInteger("1");
    public BigInteger indice;
    public BigInteger indiceAnterior;
    ControladorNumeros cm = new ControladorNumeros();

    public FactorialHilo() {
    }

    public FactorialHilo(BigInteger size, BigInteger indexAnterior) {
        indice = size;
        indiceAnterior = indexAnterior;
    }

    public BigInteger obtenerValor() {
        return resultadoAnterior;
    }

    @Override
    public void run() {
        
        for (BigInteger i = indiceAnterior; i.compareTo(indice) >= 0; i = i.subtract(BigInteger.valueOf(2))) {
            resultadoAnterior = cm.multiplicar(resultadoAnterior, i);
            System.out.println(resultadoAnterior);
        }

    }

}
