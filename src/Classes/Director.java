/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;
import Extra.ExtraData;
import Extra.ExtraFunctions;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyecto1so.mainApp;

/**
 *
 * @author nicolagabrielecolarusso
 */
public class Director extends Worker{
    
    //verfificar estado project manager
    private String estado;
    private mainApp app = mainApp.getInstance();
    
    //Constructor del director

    public Director(int company, int workerId, int type, int daysToFinish, int numOfWorkDone, int hourlyWage,
            Storage storage, Semaphore mutex) {
        super(company, workerId, type, daysToFinish, numOfWorkDone, hourlyWage, storage, mutex);
        estado = "Inactivo";
    }
    
    //cambio de estado 
    private void tareaAdministrativaHaciendo() {
        this.setEstado("Trabajando");
    }
    
    //verificando si PM esta trabajando
    private void vigilandoProjectManager() {
        this.estado = "Vigilando PM";
        ComputerCompany compañia = ExtraFunctions.traerCompaniaComputadora(this.company);

        if ("Viendo Anime".equals(compañia.getProjectManagerInstance().getCurrentState())) {

            try {
                // Pedimos permiso al mutex para poder reducir el salario del PM al costo total
                this.getSemaphore().acquire();
                compañia.getProjectManagerInstance().addStrike();
                compañia.setTotalCost(compañia.getTotalCost() - 100);
                // Se calcula las ganancias
                ExtraFunctions.calcularTotalGanancias(this.company);
                ExtraFunctions.calcularBeneficio(this.company);
                this.getSemaphore().release();

            } catch (InterruptedException ex) {
                Logger.getLogger(Director.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    private void getPaid() {
        this.accumulatedSalary += this.hourlySalary * 24;
    }

    @Override
    public String toString() {
        return "Director [Salario acumulado=" + this.accumulatedSalary + ", Estado actual=" + this.getEstado()+ "]";
    }

    public String getEstado() {
        return estado;
    }


    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    private void resetDeadline(int company) {
        ComputerCompany compañia = ExtraFunctions.traerCompaniaComputadora(company);
        compañia.setRemainingDays(app.getDeadline());
    }    
    
    @Override
    public void run() {
        while (true) {
            try {
                getPaid();
                int dayDuration = app.getDayDuration();
                int oneHour = dayDuration / 24;
                // Se determina cuanto son 35 minutos.
                int thirtyFiveMinutes = (int) (oneHour * (35.0 / 60.0));
                int remainingMinutes = oneHour - thirtyFiveMinutes;

                // Se buscan los días restantes.
                int remainingDays = this.company == 0 ? app.getApple().getRemainingDays()
                        : app.getHp().getRemainingDays();

                if (remainingDays <= 0) {
                    this.setEstado("Enviando computadoras");

                    this.getSemaphore().acquire();
                    // Se envian los capitulos
                    this.sendChaptersToTV();

                    ExtraFunctions.calcularCostoTotal(this.company, this.getAccumulatedSalary());
                    this.setAccumulatedSalary(0);

                    // Se calcula las ganancias
                    ExtraFunctions.calcularTotalGanancias(this.company);
                    ExtraFunctions.calcularBeneficio(this.company);

                    this.getSemaphore().release();

                } // Si es un dia diferente al 0 entonces hace sus labores administrativas y
                  // supervisa al PM
                else {
                    Random rand = new Random();
                    int randomHour = rand.nextInt(24);

                    for (int i = 0; i < 24; i++) {
                        if (i == randomHour) {
                            this.estado = "Vigilando PM";
                            vigilandoProjectManager();
                            Thread.sleep(thirtyFiveMinutes);
                            vigilandoProjectManager();
                            // Basta con solo 2 revisadas porque solo puede cambiar 2 veces el status del PM
                            // en 1 hora.
                            Thread.sleep(remainingMinutes);
                        } else {
                            tareaAdministrativaHaciendo();
                            Thread.sleep(oneHour);
                        }
                    }
                }
                Thread.sleep(dayDuration);
            } catch (InterruptedException ex) {
                Logger.getLogger(Director.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void calculateBatchLastProfit(ComputerCompany compañia) {
        float profit = (compañia.getLastNumBasicComputers()
                * ExtraData.profitPerComputer[this.company][0])
                + (compañia.getNumGraphicCardsComputers()
                        * ExtraData.profitPerComputer[this.company][1])
                - (compañia.getLastOpsCost());

        compañia.setBatchLastProfit(profit);
    }


    private void sendChaptersToTV() {
        try {
            this.setEstado("Enviando computadoras");

            // Esperar un día completo (simulado)
            Thread.sleep(app.getDayDuration());
            // Se reinicia el deadline
            this.resetDeadline(this.company);

            ComputerCompany compañia = ExtraFunctions.traerCompaniaComputadora(this.company);

            // Enviamos los capitulos
            compañia.getStorage().resetComputers();

            // Settiamos los valores actuales como los anteriores para estadisticas
            compañia.setLastNumGraphicCardsComputers(compañia.getActualNumGraphicCardsComputers());
            compañia.setLastNumBasicComputers(compañia.getActualNumBasicComputers());

            // Settiamos los valores actuales a 0
            compañia.setActualNumGraphicCardsComputers(0);
            compañia.setActualNumBasicComputers(0);

            // Settiamos el costo operacional del último batch
            compañia.setLastOpsCost(compañia.getTotalCost() - compañia.getLastOpsCost());

            // Calculamos las ganancias del último batch
            calculateBatchLastProfit(compañia);

        } catch (InterruptedException ex) {
            Logger.getLogger(Director.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    

}
