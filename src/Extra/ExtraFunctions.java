/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Extra;

import Classes.*;
import Extra.*;
import java.util.concurrent.Semaphore;
import proyecto1so.mainApp;
/**
 *
 * @author diego
 */

//Aquí están las funciones extra usadas en las clases
//OJOOOO HAY QUE CAMBIAR EL DIRECTOR CON LO DE NICOLA
public class ExtraFunctions {
    
    //funcion para traer la compania a la cual pertenece la instancia creada en app
    public static ComputerCompany traerCompaniaComputadora(int company) {
        return company == 0 ? mainApp.getInstance().getApple() : mainApp.getInstance().getHp();
    }
    
    
    //funcion para traerse los parametros del txt o json
    public static void cargarParametros(){
        //traemos el archivo abierto en mainApp
        String fileData = FileFunc.leerArchivo(mainApp.getSelectedFile());
        // Se obtiene los datos del TXT
        int[] params = FileFunc.traerParametrosGenerales(fileData);

        if (params != null && params.length >= 2) {
            //colocamos las duraciones de los dias
            mainApp.setDayDuration(params[0]);
            mainApp.setDeadline(params[1]);
        }
        //traemos la instancia creada en mainApp
        mainApp app = mainApp.getInstance();
        app.setApple(ExtraFunctions.crearCompaniaComputadora(0));
        app.setHp(ExtraFunctions.crearCompaniaComputadora(1));
    }
    
    
    // ojo definimos - 0 para Apple y 1 para Hp
    //Se crean las instancias de las companias con los datos establecidos en Extra Data
    public static ComputerCompany crearCompaniaComputadora(int company) {
        String fileData = FileFunc.leerArchivo(mainApp.getSelectedFile());

        // Se obtiene los datos del TXT
        int[] computerCompanyValues = FileFunc.traerCompaniaComputadora(company, fileData);

        if (computerCompanyValues != null && computerCompanyValues.length >= 9) {
            //nombre de la compania de computadora
            String name = ExtraData.companies[company];
            Worker[][] workers = new Worker[6][];
            Semaphore semaphore = new Semaphore(1);
            //se establece la capacidad del almacen
            Storage storage = new Storage(25, 20, 55, 35, 10);
            int projectManager = 1;
            int director = 1;
            //cantidad maxima de trabajadores
            int maxWorkers = computerCompanyValues[8];

            // Se crean los empleados de cada sección
            for (int type = 0; type <= 5; type++) {
                Worker[] worker = new Worker[maxWorkers];

                for (int j = 0; j < computerCompanyValues[type]; j++) {
                    int workerId = j + 1;
                    int numOfWorkDone = ExtraData.productionTimes[company][type][0];
                    int daysToFinish = ExtraData.productionTimes[company][type][1];
                    int hourlySalary= ExtraData.hourlySalary[type];
                    worker[j] = new Worker(company, workerId, type, daysToFinish, numOfWorkDone, hourlySalary,
                            storage, semaphore);
                }
                workers[type] = worker;
            }     
            ComputerCompany compCompany = new ComputerCompany(name, maxWorkers, workers[0], workers[1], workers[2],
                    workers[3], workers[4],
                    workers[5], projectManager, director, storage, semaphore);

            // Se crea al projectManager y al director, se les pasa la compania.
            ProjectManager projectManagerInstance = new ProjectManager(company, 1, 5, 1, 1,
                    ExtraData.hourlySalary[5], storage, semaphore);
            compCompany.setProjectManagerInstance(projectManagerInstance);
            Director directorInstance = new Director(company, 1, 6, 2, 1, ExtraData.hourlySalary[6], storage,
                    semaphore);
            compCompany.setDirectorInstance(directorInstance);
            return compCompany;
        }
        return null;
    }
    
    
    //funcion necesaria para crear un trabajador mas adelante. (trae el arreglo de trabajadores de una compania)
    private Worker[] traerArregloTrabajadoresPorTipo(ComputerCompany compCompany, int workerType) {
        switch (workerType) {
            case 0:
                return compCompany.getProdPlacaBase();
            case 1:
                return compCompany.getProdCPUs();
            case 2:
                return compCompany.getProdMemoriaRAM();
            case 3:
                return compCompany.getProdFuenteAlimentacion();
            case 4:
                return compCompany.getProdTarjetaGrafica();
            case 5:
                return compCompany.getEnsamblador();
            default:
                return null;
        }
    }

    //OJOOOOO ver si realmente lo necesitamos
    
    private void ponerArregloTrabajadoresPorTipo(ComputerCompany compCompany, int workerType, Worker[] newWorker) {
        switch (workerType) {
            case 0:
                compCompany.setProdPlacaBase(newWorker);
                break;
            case 1:
                compCompany.setProdCPUs(newWorker);
                break;
            case 2:
                compCompany.setProdMemoriaRAM(newWorker);
                break;
            case 3:
                compCompany.setProdFuenteAlimentacion(newWorker);
                break;
            case 4:
                compCompany.setProdTarjetaGrafica(newWorker);
                break;
        }
    }
    
    
    //funcion para agregar un trabajador en la compania de la instancia creada en app
    public void agregarTrabajador(int company, int workerType) {
        ComputerCompany compCompany = company == 0 ? mainApp.getInstance().getApple(): mainApp.getInstance().getHp();
        
        // Se verifica si la cantidad actual de empleados es menor que la cantidad máxima permitida
        if (compCompany.getActualWorkersQuantity() < compCompany.getMaxWorkersQuantity()) {
            
            //traemos el arreglo de trabajadores
            Worker[] worker = traerArregloTrabajadoresPorTipo(compCompany, workerType);

            // Se crea nuevo trabajador
            int workerId = compCompany.getActualWorkersQuantity() + 1;
            int daysToFinish = ExtraData.productionTimes[company][workerType][1];
            int numOfWorkDone = ExtraData.productionTimes[company][workerType][0];
            int hourlySalary = ExtraData.hourlySalary[workerType];
            Worker newWorker = new Worker(company, workerId, workerType, daysToFinish, numOfWorkDone, hourlySalary, compCompany.getStorage(), compCompany.getSemaphore());

            // Se inicia el hilo del nuevo trabajador
            newWorker.start();

            // Se buscar la primera posición vacía en el arreglo y añadir allí el nuevo trabajador
            for (int i = 0; i < worker.length; i++) {
                if (worker[i] == null) {
                    worker[i] = newWorker;
                    compCompany.setActualWorkersQuantity(compCompany.getActualWorkersQuantity()+1);
                    // Actualizar la cantidad de empleados sumando 1 a la cantidad actual de empleados
                    break;
                }
            }
        } else {
            //Avisamos que se alcanso el maximo de trabajadores
            System.out.println("Se ha alcanzado el número máximo de trabajadores.");
        }
    }
    
    //funcion para eliminar un trabajador del arreglo de trabadores de una compania
    public void eliminarTrabajador(int company, int workerType) {
        ComputerCompany compCompany = ExtraFunctions.traerCompaniaComputadora(company);

        // Verifica si hay trabajadores para eliminar
        if (compCompany.getActualWorkersQuantity() > 0) {
            //traemos el array de trabajadores
            Worker[] workers = traerArregloTrabajadoresPorTipo(compCompany, workerType);

            if (workers != null) {
                // Buscar el último trabajador no nulo
                for (int i = workers.length - 1; i >= 0; i--) {
                    if (workers[i] != null) {
                        // Interrumpe el hilo del trabajador
                        workers[i].interrupt();
                        // Elimina el trabajador del arreglo
                        workers[i] = null;
                        // Actualiza la cantidad de empleados restando 1
                        compCompany.setActualWorkersQuantity(compCompany.getActualWorkersQuantity() - 1);
                        break;
                    }
                }
            }
        } else {
            System.out.println("No hay trabajadores para eliminar.");
        }
    }
    
    //funcion para calcular los costos totales
    public static void calcularCostoTotal(int company, float accumulatedSalary) {
        ComputerCompany compComp = traerCompaniaComputadora(company);
        compComp.setTotalCost(compComp.getTotalCost() + accumulatedSalary);
    }

    //funcion para calcular las ganancias totales 
    public static void calcularTotalGanancias(int company) {
        ComputerCompany compComp = traerCompaniaComputadora(company);
        float earning = (compComp.getNumBasicComputers() * ExtraData.profitPerComputer[company][0])
                + (compComp.getNumGraphicCardsComputers() * ExtraData.profitPerComputer[company][1]);
        compComp.setEarning(earning);
    }

    //funcion para calcular el beneficio obtenido con las ganancias y los costos
    public static void calcularBeneficio(int company) {
        ComputerCompany compComp = traerCompaniaComputadora(company);
        float profit = compComp.getEarning() - compComp.getTotalCost();
        compComp.setProfit(profit);
    }
}
