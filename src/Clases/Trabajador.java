/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.concurrent.Semaphore;

/**
 *
 * @author nicolagabrielecolarusso
 */
public class Trabajador extends Thread  {
    
    int compañia;
    private int trabajadorID;
    private int tipo;
    private int diasterminar;
    private int trabajohecho_num;
    int hourlyWage;
    float accumulatedSalary;
    private float dailyProgress;
    private float totalWork;

    public Trabajador(int compañia, int trabajadorID, int tipo, int diasterminar, int trabajohecho_num, int hourlyWage, float accumulatedSalary, float dailyProgress, float totalWork) {
        this.compañia = compañia;
        this.trabajadorID = trabajadorID;
        this.tipo = tipo;
        this.diasterminar = diasterminar;
        this.trabajohecho_num = trabajohecho_num;
        this.hourlyWage = hourlyWage;
        this.accumulatedSalary = accumulatedSalary;
        this.dailyProgress = dailyProgress;
        this.totalWork = totalWork;
    }

    public int getCompañia() {
        return compañia;
    }

    public void setCompañia(int compañia) {
        this.compañia = compañia;
    }

    public int getTrabajadorID() {
        return trabajadorID;
    }

    public void setTrabajadorID(int trabajadorID) {
        this.trabajadorID = trabajadorID;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getDiasterminar() {
        return diasterminar;
    }

    public void setDiasterminar(int diasterminar) {
        this.diasterminar = diasterminar;
    }

    public int getTrabajohecho_num() {
        return trabajohecho_num;
    }

    public void setTrabajohecho_num(int trabajohecho_num) {
        this.trabajohecho_num = trabajohecho_num;
    }

    public int getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(int hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    public float getAccumulatedSalary() {
        return accumulatedSalary;
    }

    public void setAccumulatedSalary(float accumulatedSalary) {
        this.accumulatedSalary = accumulatedSalary;
    }

    public float getDailyProgress() {
        return dailyProgress;
    }

    public void setDailyProgress(float dailyProgress) {
        this.dailyProgress = dailyProgress;
    }

    public float getTotalWork() {
        return totalWork;
    }

    public void setTotalWork(float totalWork) {
        this.totalWork = totalWork;
    }
    
    
    
}
