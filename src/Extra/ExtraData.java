/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Extra;

/**
 *
 * @author diego
 */
//Aquí se encuentran los datos dados en el enunciado
public class ExtraData {
    
    //companias a utilizar
    public final static String[] companies = {
        "Apple",
        "Hp"
    };
    /*
    OJOOO Apple va a tener un total de 18 trabajadores y Hp de 13
    */
    //Tipos de trabajadores a considerar
    public final static String[] workesType = {
        "Productor de placa base",
        "Productor de CPUs",
        "Productor de memoria RAM",
        "Productor de fuente de alimentacion",
        "Productor de tarjetas graficas",
        "Ensamblador",
        "Project Manager",
        "Director"
    };

    //Sueldo por hora que se les paga a c/tipo de trabajador mencionado arriba. Ej prod. de placa base cobra 20$ la hora y el director 60$.
    public final static int[] hourlySalary = {
        20,
        26,
        40,
        16,
        34,
        50,
        40,
        60
    };

    // - El primer array es de Apple y el segundo de HP
    // El primer numero es la cantidad de piezas que produce c/tipo y el segundo es cuantos dia le toma en terminarlo
    public final static int[][][] productionTimes = {
        {{1, 4}, {1, 4}, {1, 1}, {5, 1}, {1, 2}, {1, 2}},
        {{1, 2}, {1, 2}, {3, 1}, {3, 1}, {1, 3}, {1, 2}} 
    };
    
    //Cantidades de piezas que requiere c/compania para armar un computador
    public final static int[][] computerComposition = {
        {2, 1, 4, 4, 2},
        {1, 1, 2, 4, 3}
    };
    
    // Cada 5 computadoras Apple agrega 2 GraphicCards, mientras que Hp agrega 3 c/2 computadoras.
    public final static int[] graphicCardFreq = {5, 2};
    
    //Apple gana 100K al vender un computador y 150K al sacar uno.
    // Hp gana 90K al vender y 140K al sacar.
    public final static int[][] profitPerComputer = {
        {100000, 150000},
        {90000, 140000}
    };
}
