/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author diego
 */
//import Extra.ExtraData;

public class Storage {
    //variables
    private int[] saved;
    private int[] maxCapacity;
    private int basicComputers;
    private int graphicCardsComputers;

    //Constructor de la clase
    public Storage(int maxPlacaBase, int maxCPUs, int maxMemoriaRAM, int maxFuenteAlimentacion, int maxTarjetaGrafica) {
        //se guardan las partes y las computadoras completas
        this.saved = new int[6]; 
        this.maxCapacity = new int[] {
                maxPlacaBase,
                maxCPUs,
                maxMemoriaRAM,
                maxFuenteAlimentacion,
                maxTarjetaGrafica
        };
    }
    
    public void saveComputer(int workerType, int productToSave){
        //solo los primeros 5 tipos de trabajadores pueden guardar cosas en el almacen
        if (workerType >=0 && workerType <=5){
            //si el trabajador es de tipo ensamblador se guarda la computadora sin necesidad de revisar la capacidad máxima
            if (workerType ==5){
                this.getSaved()[workerType] += productToSave;
            }else if (this.getSaved()[workerType] < this.getMaxCapacity()[workerType]){
                // Para los productores, se verifica si la capacidad máxima se alcansó antes de guardar el producto.
                if (this.getSaved()[workerType] + productToSave <= this.getMaxCapacity()[workerType]){
                    this.getSaved()[workerType] += productToSave;
                }else{
                    this.getSaved()[workerType] = this.getMaxCapacity()[workerType];
                }    
            }
        }
    }
    
    //no se si lo vamos a usar pero es la forma que veo de usar los datos que guardamos del enunciadio
    /*
    @Override
    public String toString() {
        String str = "Storage Info\n\n";
        for (int i = 0; i <= 5; i++) {
            str += "-" + ExtraData.workesType[i] + "'s storage saved products: " + this.saved[i] + "\n";
            if (i != 5) {
                str += "-" + ExtraData.workesType[i] + "'s max capacity: " + this.maxCapacity[i] + "\n";
            }
        }
        return str;
    }
    */
    
    //GETTERS AND SETTERS
    public int[] getSaved() {
        return saved;
    }

    public void setSaved(int[] saved) {
        this.saved = saved;
    }

    public int[] getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int[] maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getBasicComputers() {
        return basicComputers;
    }

    public void setBasicComputers(int basicComputers) {
        this.basicComputers = basicComputers;
    }

    public int getGraphicCardsComputers() {
        return graphicCardsComputers;
    }

    public void setGraphicCardsComputers(int graphicCardsComputers) {
        this.graphicCardsComputers = graphicCardsComputers;
    }
    
    
    
}
