/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

import java.awt.BasicStroke;
import java.awt.Color;
import javax.swing.Timer;
import proyecto1so.mainApp;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author nicolagabrielecolarusso
 */
public class ManejadorGraf {
    private final mainApp app = mainApp.getInstance();
    private XYSeries compApple;
    private XYSeries compHp;
    private XYSeriesCollection dataset;
    private JFreeChart xyLineChart;
    private Timer updateTimer;

    /**
     * Constructor de ChartManager que inicializa las series de datos,
     * crea el gráfico y comienza el temporizador de actualización.
     */
    
    public ManejadorGraf() {
        initializeSeries();
        initializeChart();
        startDataUpdateTimer();
    }
    
      /**
     * Inicializa las series de datos para Nickelodeon y Cartoon Network
     * y las agrega al conjunto de datos para el gráfico.
     */

    private void initializeSeries() {
        compApple = new XYSeries("Apple");
        compHp = new XYSeries("Hp");
        dataset = new XYSeriesCollection();
        dataset.addSeries(compApple);
        dataset.addSeries(compHp);
    }

    /**
     * Crea el gráfico XY Line usando JFreeChart y configura la apariencia.
     */
    
    private void initializeChart() {
        xyLineChart = ChartFactory.createXYLineChart(
                "Tiempo --vs-- Ganancia", // Título del gráfico
                "Tiempo",             // Etiqueta eje X
                "Ganancia",           // Etiqueta eje Y
                dataset,              // Dataset
                PlotOrientation.VERTICAL,
                true,                 // Mostrar leyenda
                true,                 // Generar tooltips
                false                 // URLs
        );

        customizeChart();
    }

     /**
     * Personaliza la apariencia del gráfico XY Line.
     */
    
    private void customizeChart() {
        XYPlot plot = xyLineChart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        plot.setRenderer(renderer);
    }

     /**
     * Inicia un temporizador que actualiza las series de datos con un intervalo
     * que se obtiene de la duración del día en la aplicación.
     */
    
    private void startDataUpdateTimer() {
        int delay = app.getDayDuration(); 
        updateTimer = new Timer(delay, e -> updateChartData());
        updateTimer.start();
    }

     /**
     * Actualiza las series de datos con las ganancias más recientes de Nickelodeon
     * y Cartoon Network y las agrega al gráfico.
     */
    
    public void updateChartData() {
        // Se obtienen las nuevas ganancias
        double appleProfit = app.getApple().getProfit(); 
        double hpProfit = app.getHp().getProfit(); 
        int newTimePoint = compApple.getItemCount() + 1;

        compApple.addOrUpdate(newTimePoint, appleProfit);
        compHp.addOrUpdate(newTimePoint, hpProfit);
    }

    public ChartPanel getChartPanel() {
        return new ChartPanel(xyLineChart);
    }

    public void stopUpdateTimer() {
        if (updateTimer != null) {
            updateTimer.stop();
        }
    }
}


