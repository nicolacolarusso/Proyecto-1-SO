/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import Extra.ExtraData;
import Extra.ExtraFunctions;
import Classes.Storage;
import proyecto1so.mainApp;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author diego
 */

/* Aquí utilizaremos semáforos e hilos*/
public class Worker extends Thread{
    
    // se declaran las variables necesarias
    int company;
    private int workerId;
    private int type;
    private int daysToFinish;
    private int numOfWorkDone;
    //hacemos una instancia de app para asegurar una única instancia global
    mainApp app = mainApp.getInstance();
    Semaphore semaphore;
    int hourlySalary;
    float accumulatedSalary;
    private float dailyProgress;
    private float totalWork;
    private int graphicCardCounter = 0;
    private Storage storage;
    
    //se inicializan las variables en el constructor de la clase
    public Worker(int company, int workerId, int type, int daysToFinish, int numOfWorkDone, int hourlySalary, Storage storage, Semaphore semaphore) {
        this.company = company;
        this.workerId = workerId;
        this.type = type;
        this.daysToFinish = daysToFinish;
        this.numOfWorkDone = numOfWorkDone;
        this.semaphore = semaphore;
        this.hourlySalary = ExtraData.hourlySalary[type];
        this.accumulatedSalary = 0;
        // Cantidad de trabajo realizado por dia
        this.dailyProgress = (float) numOfWorkDone / daysToFinish;
        this.totalWork = 0;
        this.storage = storage;
    }
    
    //armamos el esqueleto del hilo. Los hilos serán cada instancia de la clase trabajador. 
    //Donde ejecutan el ciclo de trabajo (proceso de producción, pago y subida de trabajo), hasta que son interrumpidos.
    public void run(){
        while (!Thread.interrupted()){
            try{
                //recibe su pago
                this.getPaid();
                //agregar a la produccion grafica
                
                //trabajar
                
                sleep(mainApp.getDayDuration());
            }catch(InterruptedException ex){
                Logger.getLogger(Worker.class.getName()).log(Level.INFO,
                        "Hilo de Trabajador interrumpido, terminando...");
                break;
            }
        }
    }
    
    //funcion de pago a los trabajadores
    private void getPaid(){
        this.setAccumulatedSalary(this.getAccumulatedSalary()+ (this.getHourlySalary()*24));
    }
    
    //funcion para revisar si un ensamblador no puede ensamblar un computador se reinicie su trabajo total acumulado.
   /* private void addDailyProduction() {
        if (this.type == 5 && !(this.evaluateAssemble())) {
            this.setTotalWork(0);
        }
        //para los trabajadores normales se acumula el trabajo total con el progreso diario 
        this.setTotalWork(this.getTotalWork() + this.getDailyProgress());
    }*/
    
    /* private boolean evaluateAssemble(){
    }
    */
    
    /* private void working() {
    }
    */
    
    /* private void createComputer() {
    }
    */
    
    
    
    
    
    //GETTERS Y SETTERS
    public void setCompany(int company) {
        this.company = company;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setDaysToFinish(int daysToFinish) {
        this.daysToFinish = daysToFinish;
    }

    public void setNumOfWorkDone(int numOfWorkDone) {
        this.numOfWorkDone = numOfWorkDone;
    }

    public void setApp(mainApp app) {
        this.app = app;
    }

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public void setHourlySalary(int hourlySalary) {
        this.hourlySalary = hourlySalary;
    }

    public void setAccumulatedSalary(float accumulatedSalary) {
        this.accumulatedSalary = accumulatedSalary;
    }

    public void setDailyProgress(float dailyProgress) {
        this.dailyProgress = dailyProgress;
    }

    public void setTotalWork(float totalWork) {
        this.totalWork = totalWork;
    }

    public void setGraphicCardCounter(int graphicCardCounter) {
        this.graphicCardCounter = graphicCardCounter;
    }

    public int getCompany() {
        return company;
    }

    public int getWorkerId() {
        return workerId;
    }

    public int getType() {
        return type;
    }

    public int getDaysToFinish() {
        return daysToFinish;
    }

    public int getNumOfWorkDone() {
        return numOfWorkDone;
    }

    public mainApp getApp() {
        return app;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public int getHourlySalary() {
        return hourlySalary;
    }

    public float getAccumulatedSalary() {
        return accumulatedSalary;
    }

    public float getDailyProgress() {
        return dailyProgress;
    }

    public float getTotalWork() {
        return totalWork;
    }

    public int getGraphicCardCounter() {
        return graphicCardCounter;
    }
    
    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
    
}
