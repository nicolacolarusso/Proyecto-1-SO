/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1so;

/**
 *
 * @author nicolagabrielecolarusso
 */
public class App {
    
    private static App app;
    
    public static App getApp() {
        return app;
    }


    public static void setApp(App aApp) {
        app = aApp;
    }
    
    public static synchronized App getInstance() {
        if (getApp() == null) {
            setApp(new App());
        }
        return getApp();
    }
}
