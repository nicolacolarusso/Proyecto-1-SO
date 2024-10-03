/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1so;

import java.io.File;

/**
 *
 * @author diego
 */
public class mainApp {
    /* FIle params VER CUAL VAMOS A USAR BIEN
    private static String selectedPath = "test//params.txt";
    private static File selectedFile = new File(selectedPath);
    private static FileFunctions fileFunctions = new FileFunctions();
*/
    // General params
    private static int dayDuration;
    private static int deadline;

    /*General variables
    private ??? apple;
    private ?? Hp;
    private static ChartManager chartManager;

*/
    private static mainApp app;

    public static synchronized mainApp getInstance() {
        if (getApp() == null) {
            setApp(new mainApp());
        }
        return getApp();
    }

    public void start() {
        //ExtraFunctions.loadParams();
      
        
        /*Inicia la simulacion
        getApple().start();
        getHp().start();
        chartManager = new ChartManager();
        */

        /*Home home = new Home();
        home.setVisible(true);*/
    }

    //GETTERS Y SETTERS
    /*
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

    public static FileFunctions getFileFunctions() {
        return fileFunctions;
    }


    public static void setFileFunctions(FileFunctions aFileFunctions) {
        fileFunctions = aFileFunctions;
    }

    */
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

    /*
    public ??? getApple() {
        return apple;
    }
    
    public void setApple(???? apple) {
        this.apple = apple;
    }

    public ??? getHp() {
        return Hp;
    }

    public void setHp(???? Hp) {
        this.Hp = Hp;
    }
*/
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
