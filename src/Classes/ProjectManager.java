/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;


import proyecto1so.mainApp;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

import Extra.ExtraFunctions;

/**
 *
 * @author nicolagabrielecolarusso
 */

public class ProjectManager extends Worker{
    
    //variables
    private String currentState;
    private int strikes;
    //constructor de la clase 
    public ProjectManager(int company, int workerId, int type, int daysToFinish, int numOfWorkDone, int hourlyWage,
            Storage storage, Semaphore mutex) {
        super(company, workerId, type, daysToFinish, numOfWorkDone, hourlyWage, storage, mutex);
        this.currentState = "Inactivo";
        this.strikes = 0;
        //variables constantes
    }
// overrride necesario para los hilos
    @Override
    public void run() {
        while (true) {
            try {
                // obtengo dias de duraciion mediante getDayDuration y una hora
                int dayDuration = mainApp.getDayDuration();
                int oneHour = dayDuration / 24;

                // 16 horas del día logra ver anime, Cada intervalo de  30 minutos ve anime, y los siguientes 30 minutos trabaja revisando el  avance del proyecto por lo que hacemos un loop.
                for (int i = 0; i < 32; i++) {
                    if (i % 2 == 0) {
                        this.setCurrentState("Viendo Anime");

                    } else {
                        setCurrentState("Trabajando");

                    }
                    // Duerme el hilo media simulacion
                    Thread.sleep(oneHour / 2);
                }
                // En la segunda parte del dia se intenta actualizar 1 vez
                // Se actualiza una vez al dia el Dailycounter
                setCurrentState("Trabajando");
                Thread.sleep(oneHour * 8);

                // Pago al final del dia
                //
                this.getPaid();

                this.getSemaphore().acquire();

                this.updateDeadlineCountdown();
                this.updateCountdown();
                ExtraFunctions.calcularCostoTotal(this.company, this.accumulatedSalary);
                this.setAccumulatedSalary(0);
                //se asigna de nuevo 

                this.getSemaphore().release();
                //se libera

            } catch (InterruptedException ex) {
                Logger.getLogger(ProjectManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void updateDeadlineCountdown() {
        // Lógica para actualizar el contador de días restantes
        if (this.company == 0) {
            if (app.getApple().getRemainingDays() != 0) {
                app.getApple().decreaceRemainingDays();
            }
        } else {
            if (app.getHp().getRemainingDays() != 0) {
                app.getHp().decreaceRemainingDays();
            }
        }
    }
    //actualizar el contador en cualquier compańia
    private void updateCountdown() {
        if (this.company == 0) {
            app.getApple().setTotalDays(app.getApple().getTotalDays() + 1);
        } else {
            app.getHp().setTotalDays(app.getHp().getTotalDays() + 1);
        }
    }
    
    private void getPaid() {
        // Solo si trabaja 24 horas diarias y anime
        this.setAccumulatedSalary(this.getAccumulatedSalary() + this.getHourlySalary()* 24);
    }

    @Override
    public String toString() {
        // Entrega info 
        return "Project Manager [Salario acumulado del project Manager=" + this.getAccumulatedSalary() + "]";
    }

    /**
     * @return the currentState
     */
    //getter y setter de CurrentState
    public String getCurrentState() {
        return currentState;
    }

  
    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }
    
    //getter y setter de HourlySalary

    public int getHourlySalary() {
        return hourlySalary;
    }

   
    public void setHourlySalary(int hourlyWage) {
        this.hourlySalary = hourlyWage;
    }

      //getter y setter de AccumulatedSalary

    public float getAccumulatedSalary() {
        return accumulatedSalary;
    }

  
    public void setAccumulatedSalary(float accumulatedSalary) {
        this.accumulatedSalary = accumulatedSalary;
    }

      //getter y setter de App

    public mainApp getApp() {
        return app;
    }

   
    public void setApp(mainApp app) {
        this.app = app;
    }

      //getter setter adder y reset de Strikes

    public int getStrikes() {
        return strikes;
    }

    
 
    public void setStrikes(int strikes) {
        this.strikes = strikes;
    }

 
    public void addStrike() {
        this.strikes++;
    }

    public void resetStrikes() {
        this.strikes = 0;
    }
    
}
    