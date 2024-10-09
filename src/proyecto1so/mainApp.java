/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1so;

import Interfaz.HpSimulador;
import java.io.File;
import Classes.ComputerCompany;
import Extra.*;
import Interfaz.*;
import javax.swing.JOptionPane;

/**
 *
 * @author diego
 */
public class mainApp {
    //Parametros del archivo 
    private static String selectedPath = "test//params.txt";
    private static File selectedFile = new File(selectedPath);
    private static FileFunc fileFunctions = new FileFunc();

    // Parametros del dia y variables
    private static int dayDuration;
    private static int deadline;
    private ComputerCompany apple;
    private ComputerCompany Hp;
    //private static ChartManager chartManager;
    private static mainApp app;

    //funcion que genera la instancia global a usar en las demas clases
    public static synchronized mainApp getInstance() {
        if (getApp() == null) {
            setApp(new mainApp());
        }
        return getApp();
    }

    public void start() {
        /*ExtraFunctions.cargarParametros();
        //Inicia la simulacion
        getApple().start();
        getHp().start();
        //chartManager = new ChartManager();]*/
        AppleHp apple = new AppleHp();
        apple.setVisible(true);
    }

    //GETTERS Y SETTERS
   
    public static String getSelectedPath() {
        return selectedPath;
    }

    public static void setSelectedPath(String aSelectedPath) {
        selectedPath = aSelectedPath;
    }

    
    public static File getSelectedFile() {
        return selectedFile;
    }

    public static void setSelectedFile(File aSelectedFile) {
        selectedFile = aSelectedFile;
    }

    public static FileFunc getFileFun() {
        return fileFunctions;
    }


    public static void setFileFunc(FileFunc aFileFunctions) {
        fileFunctions = aFileFunctions;
    }

    
    public static int getDayDuration() {
        return dayDuration;
    }

    
    public static void setDayDuration(int aDayDuration) {
        dayDuration = aDayDuration;
    }

    public static int getDeadline() {
        return deadline;
    }

    public static void setDeadline(int aDeadline) {
        deadline = aDeadline;
    }

    
    public ComputerCompany getApple() {
        return apple;
    }
    
    public void setApple(ComputerCompany apple) {
        this.apple = apple;
    }

    public ComputerCompany getHp() {
        return Hp;
    }

    public void setHp(ComputerCompany Hp) {
        this.Hp = Hp;
    }

    public static mainApp getApp() {
        return app;
    }

    public static void setApp(mainApp aApp) {
        app = aApp;
    }
/*
     public static ChartManager getChartManager() {
        return chartManager;
    }*/
}
