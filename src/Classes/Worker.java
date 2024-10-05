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
                //agregar a la produccion diaria
                this.addDailyProduction();
                //trabajar
                this.working();
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
    private void addDailyProduction() {
        if (this.type == 5 && !(this.evaluateAssemble())) {
            this.setTotalWork(0);
        }
        //para los trabajadores normales se acumula el trabajo total con el progreso diario 
        this.setTotalWork(this.getTotalWork() + this.getDailyProgress());
    }
    
    
    private boolean evaluateAssemble(){
        //traemos la compania a la cual pertenece el ensamblador
        ComputerCompany compCompany = ExtraFunctions.traerCompaniaComputadora(this.company);

        // Requisito minimo para armar un computador basico segun la compania
        for (int i = 0; i < compCompany.getStorage().getSaved().length - 2; i++) {
            // Si no hay la cantidad minima entonces el assembler no puede tranbajar
            if (compCompany.getStorage().getSaved()[i] < ExtraData.computerComposition[this.company][i]) {
                return false;
            }
        }
        // si es con tarjeta grafica
        boolean isNextGraphicCard = (compCompany.getNumComputers() != 0
                && ((compCompany.getNumComputers()) % ExtraData.graphicCardFreq[this.company]) == 0);

        if (isNextGraphicCard) {
            // Verifica si NO hay para hacer un plottwist
            if (compCompany.getStorage().getSaved()[4] < ExtraData.computerComposition[this.company][4]) {
                return false;
            }
        }
        return true;
    }
    
    
    private void working() {
        if (getTotalWork() >= 1) {
            try {
                this.getSemaphore().acquire();
                int workToUpload = (int) Math.floor(this.getTotalWork());

                if (this.type != 5) {
                    this.getStorage().saveComputer(getType(), workToUpload);
                } else {
                    this.createComputer();
                }

                ExtraFunctions.calcularCostoTotal(this.company, this.accumulatedSalary);
                setAccumulatedSalary(0);

                this.setTotalWork(Math.max(0, this.getTotalWork() - workToUpload));
                this.getSemaphore().release();
            } catch (InterruptedException ex) {
                Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    private void createComputer() {
        //traemos la compania 
        ComputerCompany compCompany = ExtraFunctions.traerCompaniaComputadora(this.company);

        if (this.evaluateAssemble()) {
            // Evaluar si el siguiente computador debe ser uno con tarjeta grafica antes de incrementar el numComputer
            boolean isNextGraphicCard = (compCompany.getGraphicCardTrigger() != 0
                    && ((compCompany.getGraphicCardTrigger()) % ExtraData.graphicCardFreq[this.company]) == 0);

            if (isNextGraphicCard) {
                //computador con tarjeta grafica
                for (int i = 0; i < compCompany.getStorage().getSaved().length - 1; i++) {
                    compCompany.getStorage().getSaved()[i] = Math.max(0,
                            compCompany.getStorage().getSaved()[i] - ExtraData.computerComposition[this.company][i]);
                }
                compCompany.setNumGraphicCardsComputers(compCompany.getNumGraphicCardsComputers() + 1);
                compCompany.setActualNumGraphicCardsComputers(compCompany.getActualNumGraphicCardsComputers()+ 1);
                compCompany.setGraphicCardTrigger(0);
            } else {
                //computador basico 
                for (int i = 0; i < compCompany.getStorage().getSaved().length - 2; i++) {
                    compCompany.getStorage().getSaved()[i] = Math.max(0,
                            compCompany.getStorage().getSaved()[i] - ExtraData.computerComposition[this.company][i]);
                }
                compCompany.setNumBasicComputers(compCompany.getNumBasicComputers()+ 1);
                compCompany.setActualNumBasicComputers(compCompany.getActualNumBasicComputers() + 1);
                compCompany.setGraphicCardTrigger(compCompany.getGraphicCardTrigger() + 1);
            }

            // Incrementa el número de computadoras
            compCompany.setNumComputers(compCompany.getNumComputers() + 1);
            compCompany.setActualNumComputers(compCompany.getActualNumComputers() + 1);
            this.getStorage().getSaved()[5] += 1;
        } else {
            this.setTotalWork(0);
        }
    }
    
    
    //OJOOO VER SI REALMENTE LO NECESITAMOS MAS ADELANTE
    private boolean isGraphicCatd(ComputerCompany compCompany) {
        return true;
    }
    
    
    //ES NECESARIO?????
    /*
    @Override
    public String toString() {
        return """
                Worker {
                """ + "-Company= " + ExtraData.companies[getCompany()] + "\n"
                + "-workerId= " + getWorkerId() + "\n"
                + "-type= " + ExtraData.workesType[getType()] + "\n"
                + "-Days to Finish his part= " + getDaysToFinish() + "\n"
                + "- Num of work done per days= " + getNumOfWorkDone() + "\n"
                + "-hourlySalary= " + getHourlySalary() + "\n"
                + "-accumulatedSalary= " + getAccumulatedSalary() + "\n"
                + "-dailyProgress= " + getDailyProgress() + "\n"
                + "-Total Work= " + getTotalWork() + "\n"
                + "-storage= " + (getStorage() != null ? "assigned" : "not assigned") + "\n"
                + "-semaphore= " + (getSemaphore() != null ? "assigned" : "not assigned") + "\n"
                + "\n}";
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
