/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.util.concurrent.Semaphore;
import Classes.Worker;
import Classes.Director;
import Classes.Storage;
import proyecto1so.mainApp;
/**
 *
 * @author diego
 */
public class ComputerCompany {
    
    //variables
    private String name;
    private int maxWorkersQuantity;
    private int actualWorkersQuantity = 0;
    private Worker[] prodPlacaBase;
    private Worker[] prodCPUs;
    private Worker[] prodMemoriaRAM;
    private Worker[] prodFuenteAlimentacion;
    private Worker[] prodTarjetaGrafica;
    private Worker[] Ensamblador;
    private int projectManager;
    //private ProjectManager prostoragejectManagerInstance;
    private int director;
    private Director directorInstance;
    private static Storage storage;
    private Semaphore semaphore;
    private float totalCost = 0;
    private float earning = 0;
    private float profit = 0;
    private float lastOpsCost = 0;
    private float batchLastProfit = 0;
    private int totalDays = 0;
    private int remainingDays = mainApp.getInstance().getDeadline();
    private int numComputers = 0;
    private int numBasicComputers = 0;
    private int numGraphicCardsComputers = 0;
    private int actualNumComputers = 0;
    private int actualNumBasicComputers = 0;
    private int actualNumGraphicCardsComputers = 0;
    private int lastNumBasicComputers = 0;
    private int lastNumGraphicCardsComputers = 0;
    private int graphicCardTrigger = 0;

    //Constructor de la clase
    public ComputerCompany(String name, int maxWorkersQuantity, Worker[] prodPlacaBase, Worker[] prodCPUs, Worker[] prodMemoriaRAM, 
            Worker[] prodFuenteAlimentacion, Worker[] prodTarjetaGrafica, Worker[] Ensamblador, int projectManager, int director, 
            Storage storage, Semaphore semaphore) {
        this.name = name;
        this.maxWorkersQuantity = maxWorkersQuantity;
        this.prodPlacaBase = prodPlacaBase;
        this.prodCPUs = prodCPUs;
        this.prodMemoriaRAM = prodMemoriaRAM;
        this.prodFuenteAlimentacion = prodFuenteAlimentacion;
        this.prodTarjetaGrafica = prodTarjetaGrafica;
        this.Ensamblador = Ensamblador;
        this.projectManager = projectManager;
        this.director = director;
        this.storage = storage;
        this.semaphore = semaphore;
        this.actualWorkersQuantity();
    }
    
    //funcion para iniciar los hilos (un hilo es un tipo de trabajadores)
    public void start() {
        //por c/trabajador de c/tipo, se revisa que sea distinto de nulo y se inicia. 
        for (int i = 0; i < this.getProdPlacaBase().length; i++) {
            if (this.getProdPlacaBase()[i] != null) {
                this.getProdPlacaBase()[i].start();
            }
        }
        for (int i = 0; i < this.getProdCPUs().length; i++) {
            if (this.getProdCPUs()[i] != null) {
                this.getProdCPUs()[i].start();
            }
        }
        for (int i = 0; i < this.getProdMemoriaRAM().length; i++) {
            if (this.getProdMemoriaRAM()[i] != null) {
                this.getProdMemoriaRAM()[i].start();
            }
        }
        for (int i = 0; i < this.getProdFuenteAlimentacion().length; i++) {
            if (this.getProdFuenteAlimentacion()[i] != null) {
                this.getProdFuenteAlimentacion()[i].start();
            }
        }
        for (int i = 0; i < this.getProdTarjetaGrafica().length; i++) {
            if (this.getProdTarjetaGrafica()[i] != null) {
                this.getProdTarjetaGrafica()[i].start();
            }
        }
        for (int i = 0; i < this.getEnsamblador().length; i++) {
            if (this.getEnsamblador()[i] != null) {
                this.getEnsamblador()[i].start();
            }
        }
        //this.getProjectManagerInstance().start();  CAMBIAR CON LO DE NICOLA
        //this.getDirectorInstance().start();   CAMBIAR CON LO DE NICOLA

    }

    //Funcion para contar que en el arreglo de trabajadores no hayan cantidades nulas
    public int countNonNull(Worker[] workers) {
        int count = 0;
        for (Worker worker : workers) {
            if (workers != null) {
                count++;
            }
        }
        return count;
    }
    
    //Funcion para calcular la cantidad de Trabajadores actuales en el sistema
    public void actualWorkersQuantity() {
        int totalWorkers = 0;
        // Contar empleados no nulos en cada arreglo
        totalWorkers += countNonNull(prodPlacaBase);
        totalWorkers += countNonNull(prodCPUs);
        totalWorkers += countNonNull(prodMemoriaRAM);
        totalWorkers += countNonNull(prodFuenteAlimentacion);
        totalWorkers += countNonNull(prodTarjetaGrafica);
        totalWorkers += countNonNull(Ensamblador);

        this.setActualWorkersQuantity(totalWorkers);
    }
    
    //OJOOOOO falta el de la instancia de project manager
    //GETTERS Y SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxWorkersQuantity() {
        return maxWorkersQuantity;
    }

    public void setMaxWorkersQuantity(int maxWorkersQuantity) {
        this.maxWorkersQuantity = maxWorkersQuantity;
    }

    public int getActualWorkersQuantity() {
        return actualWorkersQuantity;
    }

    public void setActualWorkersQuantity(int actualEmployeesQuantity) {
        this.actualWorkersQuantity = actualEmployeesQuantity;
    }

    public Worker[] getProdPlacaBase() {
        return prodPlacaBase;
    }

    public void setProdPlacaBase(Worker[] prodPlacaBase) {
        this.prodPlacaBase = prodPlacaBase;
    }

    public Worker[] getProdCPUs() {
        return prodCPUs;
    }

    public void setProdCPUs(Worker[] prodCPUs) {
        this.prodCPUs = prodCPUs;
    }

    public Worker[] getProdMemoriaRAM() {
        return prodMemoriaRAM;
    }

    public void setProdMemoriaRAM(Worker[] prodMemoriaRAM) {
        this.prodMemoriaRAM = prodMemoriaRAM;
    }

    public Worker[] getProdFuenteAlimentacion() {
        return prodFuenteAlimentacion;
    }

    public void setProdFuenteAlimentacion(Worker[] prodFuenteAlimentacion) {
        this.prodFuenteAlimentacion = prodFuenteAlimentacion;
    }

    public Worker[] getProdTarjetaGrafica() {
        return prodTarjetaGrafica;
    }

    public void setProdTarjetaGrafica(Worker[] prodTarjetaGrafica) {
        this.prodTarjetaGrafica = prodTarjetaGrafica;
    }

    public Worker[] getEnsamblador() {
        return Ensamblador;
    }

    public void setEnsamblador(Worker[] Ensamblador) {
        this.Ensamblador = Ensamblador;
    }

    public int getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(int projectManager) {
        this.projectManager = projectManager;
    }

    public int getDirector() {
        return director;
    }

    public void setDirector(int director) {
        this.director = director;
    }

    public Director getDirectorInstance() {
        return directorInstance;
    }

    public void setDirectorInstance(Director directorInstance) {
        this.directorInstance = directorInstance;
    }

    public static Storage getStorage() {
        return storage;
    }

    public static void setStorage(Storage storage) {
        ComputerCompany.storage = storage;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }
    
    //funciones de manejo del costo
    public void increaseTotalCost(int cost) {
        this.totalCost += cost;
    }

    public void resetCost() {
        this.totalCost = 0;
    }

    
    public float getEarning() {
        return earning;
    }

    public void setEarning(float earning) {
        this.earning = earning;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public float getLastOpsCost() {
        return lastOpsCost;
    }

    public void setLastOpsCost(float lastOpsCost) {
        this.lastOpsCost = lastOpsCost;
    }

    public float getBatchLastProfit() {
        return batchLastProfit;
    }

    public void setBatchLastProfit(float batchLastProfit) {
        this.batchLastProfit = batchLastProfit;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public int getRemainingDays() {
        return remainingDays;
    }
    
    //funcion de manejo de los dias restantes
    public void decreaceRemainingDays() {
        this.remainingDays--;
    }
    public void resetRemainingDays() {
        this.remainingDays = mainApp.getInstance().getDeadline();
    }
    

    public void setRemainingDays(int remainingDays) {
        this.remainingDays = remainingDays;
    }

    public int getNumComputers() {
        return numComputers;
    }

    public void setNumComputers(int numComputers) {
        this.numComputers = numComputers;
    }

    public int getNumBasicComputers() {
        return numBasicComputers;
    }

    public void setNumBasicComputers(int numBasicComputers) {
        this.numBasicComputers = numBasicComputers;
    }

    public int getNumGraphicCardsComputers() {
        return numGraphicCardsComputers;
    }

    public void setNumGraphicCardsComputers(int numGraphicCardsComputers) {
        this.numGraphicCardsComputers = numGraphicCardsComputers;
    }

    public int getActualNumComputers() {
        return actualNumComputers;
    }

    public void setActualNumComputers(int actualNumComputers) {
        this.actualNumComputers = actualNumComputers;
    }

    public int getActualNumBasicComputers() {
        return actualNumBasicComputers;
    }

    public void setActualNumBasicComputers(int actualNumBasicComputers) {
        this.actualNumBasicComputers = actualNumBasicComputers;
    }

    public int getActualNumGraphicCardsComputers() {
        return actualNumGraphicCardsComputers;
    }

    public void setActualNumGraphicCardsComputers(int actualNumGraphicCardsComputers) {
        this.actualNumGraphicCardsComputers = actualNumGraphicCardsComputers;
    }

    public int getLastNumBasicComputers() {
        return lastNumBasicComputers;
    }

    public void setLastNumBasicComputers(int lastNumBasicComputers) {
        this.lastNumBasicComputers = lastNumBasicComputers;
    }

    public int getLastNumGraphicCardsComputers() {
        return lastNumGraphicCardsComputers;
    }

    public void setLastNumGraphicCardsComputers(int lastNumGraphicCardsComputers) {
        this.lastNumGraphicCardsComputers = lastNumGraphicCardsComputers;
    }

    public int getGraphicCardTrigger() {
        return graphicCardTrigger;
    }

    public void setGraphicCardTrigger(int graphicCardTrigger) {
        this.graphicCardTrigger = graphicCardTrigger;
    }
}
