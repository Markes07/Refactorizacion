package ochoreinas;

import java.awt.*;
//
//Eight Reinas puzzle written in Java
//Written by Tim Budd, January 1996
//revised for 1.3 event model July 2001
//


class Reina {
    // datos
private int fila;
private int columna;
private Reina vecina;

    // constructor
Reina (int c, Reina n) {
    fila = 1;
    columna = c;
    vecina = n;
    }

public boolean buscaSolucion() {
    while (vecina != null && vecina.puedeAtacar(fila, columna))
        if (! avanza())
            return false;
    return true;
    }

public boolean avanza() {
    if (fila < 8) {
        fila++;
        return buscaSolucion();
        }
    if (vecina != null) {
        if (! vecina.avanza())
            return false;
        if (! vecina.buscaSolucion())
            return false;
        }
    else
        return false;
    fila = 1;
    return buscaSolucion();
        
    }

private boolean puedeAtacar(int testfila, int testcolumna) {
    int columnaDiferencia = testcolumna - columna;
    if ((fila == testfila) ||
        (fila + columnaDiferencia == testfila) ||
        (fila - columnaDiferencia == testfila))
            return true;
    if (vecina != null)
        return vecina.puedeAtacar(testfila, testcolumna);
    return false;
    }

public void paint (Graphics g) {
        // primero dibuja la vecina vecina
    if (vecina != null)
        vecina.paint(g);
        // despues a ella misna
        // x, y is upper left corner
    int x = (fila - 1) * 50 + 10;
    int y = (columna - 1) * 50 + 40;
    g.drawLine(x+5, y+45, x+45, y+45);
    g.drawLine(x+5, y+45, x+5, y+5);
    g.drawLine(x+45, y+45, x+45, y+5);
    g.drawLine(x+5, y+35, x+45, y+35);
    g.drawLine(x+5, y+5, x+15, y+20);
    g.drawLine(x+15, y+20, x+25, y+5);
    g.drawLine(x+25, y+5, x+35, y+20);
    g.drawLine(x+35, y+20, x+45, y+5);
    g.drawOval(x+20, y+20, 10, 10);
    }
}