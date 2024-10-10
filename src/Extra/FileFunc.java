/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Extra;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import proyecto1so.mainApp;
import Classes.ComputerCompany;


/**
 *
 * @author diego
 */
public class FileFunc {
    
    public static String leerArchivo(File file) {
        String line;
        String data = "";

        try {
            if (!file.exists()) {
                file.createNewFile();

            } else {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);

                while ((line = br.readLine()) != null) {
                    if (!(line.isEmpty())) {
                        data += line + "\n";
                    }
                }
                br.close();
            }
            return data;
        } catch (Exception e) {
        }
        return data;
    }
    
    public static int[] traerParametrosGenerales(String fileData) {
        int startIndex = fileData.indexOf("[General Params]");
        if (startIndex == -1) {
            // La sección de Hp no fue encontrada.
            return null;
        }

        // Se Encuentra el final de la sección de General Params o el final del archivo si no hay más secciones
        int endIndex = fileData.indexOf("[", startIndex + 1);
        if (endIndex == -1) {
            endIndex = fileData.length();
        }
        // Se extrae la sección de General Params
        String cnSection = fileData.substring(startIndex, endIndex);

        // Se divide la sección en líneas
        String[] lines = cnSection.split("\n");

        // Se crea un array para almacenar los valores enteros de la configuración
        int[] generalParams = new int[2];

        // Variable para recorrer el arreglo de líneas
        int valueIndex = 0;

        // Se Itera sobre las líneas, extrayendo los valores enteros
        for (String line : lines) {
            if (line.contains("=")) {
                // Divide la línea en la etiqueta y el valor
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    try {
                        // Se castea el valor entero y lo almacena en el array
                        generalParams[valueIndex++] = Integer.parseInt(parts[1].trim());
                    } catch (NumberFormatException e) {
                        System.out.println("El valor no es entero");
                    }
                }
            }
        }
        return generalParams;
    }
    
    public static int[] traerCompaniaComputadora(int company, String fileData) {
        int[] values = new int[9];
        int startIndex = 0;

        if (company == 0) {
            startIndex = fileData.indexOf("[Apple]");
        } else if (company == 1) {
            startIndex = fileData.indexOf("[Hp]");
        }

        if (startIndex == -1) {
            // La sección no fue encontrada.
            return null;
        }

        // Se Encuentra el final de la sección de o el final del archivo
        int endIndex = fileData.indexOf("[", startIndex + 1);
        if (endIndex == -1) {
            endIndex = fileData.length();
        }

        // Se extrae la sección
        String cnSection = fileData.substring(startIndex, endIndex);

        // Se divide la sección en líneas
        String[] lines = cnSection.split("\n");

        // Variable para recorrer el arreglo de líneas
        int valueIndex = 0;

        // Se Itera sobre las líneas, extrayendo los valores enteros
        for (String line : lines) {
            if (line.contains("=")) {
                // Divide la línea en la etiqueta y el valor
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    try {
                        // Se castea el valor entero y lo almacena en el array
                        values[valueIndex++] = Integer.parseInt(parts[1].trim());
                    } catch (NumberFormatException e) {
                        System.out.println("El valor no es entero");
                    }
                }
            }
        }

        return values;
    }
    
    public static void write(File file) {

        String data = traerParametros();
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(file);
            fileWriter.write(data);
            fileWriter.close();
        } catch (Exception e) {

        }
    }
    
    public static String traerParametros() {
        mainApp app = mainApp.getInstance();
        
        String data = "[General Params]\n";
        //traemos los parametros del dia 
        int dayDuration = mainApp.getDayDuration();
        int deadline = mainApp.getDeadline();

        data += "DayDuration=" + dayDuration + "\n" + "Deadline=" + deadline + "\n\n";

        data += "[Apple]\n";
        //traemos la primera compania
        ComputerCompany apple = app.getApple();
        int prodPlacaBase = apple.countNonNull(apple.getProdPlacaBase());
        int prodCPUs = apple.countNonNull(apple.getProdCPUs());
        int prodMemoriaRAM = apple.countNonNull(apple.getProdMemoriaRAM());
        int prodFuenteAlimentacion = apple.countNonNull(apple.getProdFuenteAlimentacion());
        int prodTarjetaGrafica = apple.countNonNull(apple.getProdTarjetaGrafica());
        int ensamblador = apple.countNonNull(apple.getEnsamblador());
        int projectManager = apple.getProjectManager();
        int director = apple.getDirector();
        int maxCapacity = apple.getMaxWorkersQuantity();

        data += "ProductorDePlacabase=" + prodPlacaBase + "\n" + "ProductorDeCPUs=" + prodCPUs + "\n" + "ProductorDeMemoriaRAM="
                + prodMemoriaRAM + "\n" + "ProductorDeFuenteDeAlimentacion=" + prodFuenteAlimentacion + "\n" + "ProductorDeTarjetasGraficas="
                + prodTarjetaGrafica + "\n" + "Ensambladores=" + ensamblador + "\n" + "ProjectManager=" + projectManager
                + "\n" + "Director=" + director + "\n" + "MaxCapacidad=" + maxCapacity + "\n\n";

        data += "[Hp]\n";
        //traemos la segunda compania
        ComputerCompany hp = app.getHp();
        prodPlacaBase = hp.countNonNull(hp.getProdPlacaBase());
        prodCPUs = hp.countNonNull(hp.getProdCPUs());
        prodMemoriaRAM = hp.countNonNull(hp.getProdMemoriaRAM());
        prodFuenteAlimentacion = hp.countNonNull(hp.getProdFuenteAlimentacion());
        prodTarjetaGrafica = hp.countNonNull(hp.getProdTarjetaGrafica());
        ensamblador = hp.countNonNull(hp.getEnsamblador());
        projectManager = hp.getProjectManager();
        director = hp.getDirector();
        maxCapacity = hp.getMaxWorkersQuantity();

        data += "ProductorDePlacabase=" + prodPlacaBase + "\n" + "ProductorDeCPUs=" + prodCPUs + "\n" + "ProductorDeMemoriaRAM="
                + prodMemoriaRAM + "\n" + "ProductorDeFuenteDeAlimentacion=" + prodFuenteAlimentacion + "\n" + "ProductorDeTarjetasGraficas="
                + prodTarjetaGrafica + "\n" + "Ensambladores=" + ensamblador + "\n" + "ProjectManager=" + projectManager
                + "\n" + "Director=" + director + "\n" + "MaxCapacidad=" + maxCapacity + "\n\n";

        return data;
    }
}
