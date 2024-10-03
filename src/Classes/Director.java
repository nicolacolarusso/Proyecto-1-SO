/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;
import java.util.Random;

/**
 *
 * @author nicolagabrielecolarusso
 */
public class Director {
    private static final double TARIFA_HORA = 60.0;
    private int diasRestantes;
    private double ganancia;
    private boolean pmViendoAnime;

    public Director(int diasRestantes) {
        this.diasRestantes = diasRestantes;
        this.ganancia = 0.0;
        this.pmViendoAnime = false;
    }

    public void trabajar() {
        if (diasRestantes == 0) {
            enviarComputadoras();
        } else {
            laboresAdministrativas();
            revisarPM();
        }
    }

    private void enviarComputadoras() {
        System.out.println("Enviando computadoras a las distribuidoras...");
        try {
            Thread.sleep(24 * 60 * 60 * 1000); // Simula 24 horas
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        registrarGanancia();
        reiniciarContador();
    }

    private void registrarGanancia() {
        // Supongamos que cada computadora genera $1000 de ganancia
        double gananciaComputadoras = 1000.0; // Este valor puede ser dinámico
        ganancia += gananciaComputadoras;
        System.out.println("Ganancia registrada: $" + gananciaComputadoras);
    }

    private void reiniciarContador() {
        diasRestantes = 30; // Reinicia el contador a 30 días, por ejemplo
        System.out.println("Contador de días reiniciado a " + diasRestantes);
    }

    private void laboresAdministrativas() {
        System.out.println("Realizando labores administrativas...");
        // Simula un día de trabajo administrativo
        try {
            Thread.sleep(8 * 60 * 60 * 1000); // Simula 8 horas de trabajo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void revisarPM() {
        Random random = new Random();
        int horaRevisar = random.nextInt(24); // Hora aleatoria del día
        System.out.println("Revisando al PM a la hora: " + horaRevisar);
        // Simula 35 minutos de revisión
        try {
            Thread.sleep(35 * 60 * 1000); // Simula 35 minutos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (pmViendoAnime) {
            descontarSueldoPM();
        }
    }

    private void descontarSueldoPM() {
        System.out.println("PM visto viendo anime. Descontando $100 de su sueldo.");
        // Aquí se descontaría el sueldo del PM
    }

    public void setPmViendoAnime(boolean pmViendoAnime) {
        this.pmViendoAnime = pmViendoAnime;
    }

    public static void main(String[] args) {
        Director director = new Director(5);
        director.trabajar();
    }
    
}
