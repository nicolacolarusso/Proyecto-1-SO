/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;

import Classes.Worker;
import Extra.ExtraFunctions;
import Extra.FileFunc;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import proyecto1so.mainApp;

/**
 *
 * @author diego
 */
public class HpSimulador extends javax.swing.JFrame {

    private Point initialClick;
    private final mainApp app = mainApp.getInstance();
    private int maxEmployees;
    private int actualEmployees;
    private static HpSimulador hp;
    private ExtraFunctions helper = new ExtraFunctions();
    private FileFunc filefunctions = new FileFunc();
    private File selectedFile = app.getSelectedFile();
    private JButton[] decreaseBtn = new JButton[6];
    private JButton[] increaseBtn = new JButton[6];
    private int[] values = {
        countNonNullEmployees(this.app.getHp().getProdPlacaBase()),
        countNonNullEmployees(this.app.getHp().getProdCPUs()),
        countNonNullEmployees(this.app.getHp().getProdMemoriaRAM()),
        countNonNullEmployees(this.app.getHp().getProdFuenteAlimentacion()),
        countNonNullEmployees(this.app.getHp().getProdTarjetaGrafica()),
        countNonNullEmployees(this.app.getHp().getEnsamblador())
    };

    private void updateBtnStatus() {
        updateValues();

        if (this.actualEmployees == this.maxEmployees) {
            for (JButton btn : increaseBtn) {
                btn.setEnabled(false);
                btn.setFocusable(false);
            }
        } else {
            for (JButton btn : increaseBtn) {
                btn.setEnabled(true);
                btn.setFocusable(true);
            }
        }

        for (int i = 0; i < this.values.length; i++) {
            if (this.values[i] == 1) {
                this.decreaseBtn[i].setEnabled(false);
                this.decreaseBtn[i].setFocusable(false);
            } else {
                this.decreaseBtn[i].setEnabled(true);
                this.decreaseBtn[i].setFocusable(true);

            }
        }
    }

    private void updateValues() {
        values[0] = countNonNullEmployees(this.app.getHp().getProdPlacaBase());
        values[1] = countNonNullEmployees(this.app.getHp().getProdCPUs());
        values[2] = countNonNullEmployees(this.app.getHp().getProdMemoriaRAM());
        values[3] = countNonNullEmployees(this.app.getHp().getProdFuenteAlimentacion());
        values[4] = countNonNullEmployees(this.app.getHp().getProdTarjetaGrafica());
        values[5] = countNonNullEmployees(this.app.getHp().getEnsamblador());
    }

    public static synchronized HpSimulador getInstance() {
        if (hp == null) {
            hp = new HpSimulador();
        }
        return hp;
    }

    public HpSimulador() {
        try {
            // Código para el Look and Feel
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

            /* hasta              aquiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii llevoooooooooo 

        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        initializeValues();
             hasta              aquiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii llevoooooooooo */

        
        initComponents();
        this.apple = apple;
        apple.setVisible(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setSize(1131, 835);
        

        this.decreaseBtn[0] = decreasePlacaB;
        this.decreaseBtn[1] = decreaseCpu;
        this.decreaseBtn[2] = decreaseRAM;
        this.decreaseBtn[3] = decreaseFAliment;
        this.decreaseBtn[4] = decreaceTGrafica;
        this.decreaseBtn[5] = decreaceAssembler;
        this.increaseBtn[0] = increasePlacaB;
        this.increaseBtn[1] = increaseCpu;
        this.increaseBtn[2] = increaseRAM;
        this.increaseBtn[3] = increaseFAliment;
        this.increaseBtn[4] = increaseTGrafica;
        this.increaseBtn[5] = increaseAssembler;

        updateBtnStatus();
        this.start();

    }

    //
    private void start() {
        // Crear un nuevo hilo para el bucle infinito
        Thread updateThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        // Ejecutar las actualizaciones de la UI en el EDT
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                // Aquí van tus actualizaciones de la UI
                                placaBValueStorage
                                        .setText(String.valueOf(app.getHp().getStorage().getSaved()[0]));
                                cpuValueStorage
                                        .setText(String.valueOf(app.getHp().getStorage().getSaved()[1]));
                                RAMValueStorage
                                        .setText(String.valueOf(app.getHp().getStorage().getSaved()[2]));
                                fAlimentValueStorage
                                        .setText(String.valueOf(app.getHp().getStorage().getSaved()[3]));
                                tGraficaValueStorage
                                        .setText(String.valueOf(app.getHp().getStorage().getSaved()[4]));
                                assemblerValueStorage
                                        .setText(String.valueOf(app.getHp().getStorage().getSaved()[5]));

                                projectManagerStatus
                                        .setText(app.getHp().getProjectManagerInstance().getCurrentState());

                                currentDeadline.setText(
                                        String.valueOf(app.getHp().getRemainingDays()));

                                totalDays.setText(String.valueOf(app.getHp().getTotalDays()));

                                strikeCounter.setText(String
                                        .valueOf(app.getHp().getProjectManagerInstance().getStrikes()));
                                cashPenality.setText(String.valueOf(Integer.parseInt(strikeCounter.getText()) * 100));
                                directorStatus.setText(app.getHp().getDirectorInstance().getEstado());

                                
                                
                                
                                
                                totalComp.setText(
                                        String.valueOf(app.getHp().getNumComputers()));
                                basicCompTotal.setText(
                                        String.valueOf(app.getHp().getNumBasicComputers()));

                                tGraficaCompTotal.setText(
                                        String.valueOf(app.getHp().getNumGraphicCardsComputers()));

                                basicComp.setText(
                                        String.valueOf(app.getHp().getActualNumBasicComputers())
                                );
                                tGraficaComp.setText(
                                        String.valueOf(app.getHp().getActualNumGraphicCardsComputers())
                                );

                                basicCompLast.setText(
                                        String.valueOf(app.getHp().getLastNumBasicComputers())
                                );
                                tGraficaCompLast.setText(
                                        String.valueOf(app.getHp().getLastNumGraphicCardsComputers())
                                );

                                profit.setText(formatNumberAsK((int) app.getHp().getEarning() -  (int) app.getHp().getTotalCost()));
                                cost.setText(formatNumberAsK((int) app.getHp().getTotalCost()));
                                earning.setText(formatNumberAsK((int) app.getHp().getEarning()));
                                batchLastProfit.setText(
                                        formatNumberAsK((int) app.getHp().getBatchLastProfit()));
                            }
                        });

                        // Pausar el hilo separado, no el EDT
                        Thread.sleep(app.getDayDuration() / 48);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        });

        // Iniciar el hilo
        updateThread.start();
    }

    private void initializeValues() {
        if (this.app.getHp() != null) {
            this.maxEmployees = this.app.getHp().getMaxWorkersQuantity();
            this.actualEmployees = this.app.getHp().getActualWorkersQuantity();
            this.placaBValues
                    .setText(String.valueOf(countNonNullEmployees(this.app.getHp().getProdPlacaBase())));
            this.cpuValue
                    .setText(String.valueOf(countNonNullEmployees(this.app.getHp().getProdCPUs())));
            this.RAMValues.setText(
                    String.valueOf(countNonNullEmployees(this.app.getHp().getProdMemoriaRAM())));
            this.FAlimentValues
                    .setText(String.valueOf(countNonNullEmployees(this.app.getHp().getProdFuenteAlimentacion())));
            this.TGraficaValues.setText(
                    String.valueOf(countNonNullEmployees(this.app.getHp().getProdTarjetaGrafica())));
            this.assemblerValues
                    .setText(String.valueOf(countNonNullEmployees(this.app.getHp().getEnsamblador())));
            this.maxCap.setText(String.valueOf(this.maxEmployees) + "     trabajadores");
        }
    }

    private int countNonNullEmployees(Worker[] workers) {
        int count = 0;
        for (Worker workeer : workers) {
            if (workeer != null) {
                count++;
            }
        }
        return count;
    }

    public String formatNumberAsK(int number) {
        // Se onverte el número a miles
        double thousands = number / 1000.0;

        // Se redondea a dos dígitos significativos
        double rounded = Math.round(thousands * 100.0) / 100.0;

        // Se convierte a cadena y se añade 'K'
        return rounded + "K";
    }


    
    /* hasta              aquiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii llevoooooooooo */
    
    
    
    
    
    public static AppleHp apple;
    
    public HpSimulador(AppleHp apple) {
        initComponents();
        this.apple = apple;
        apple.setVisible(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setSize(1131, 835);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        driveTitle4 = new javax.swing.JLabel();
        plotTwistChapters = new javax.swing.JTextField();
        standardChapters = new javax.swing.JTextField();
        driveTitle5 = new javax.swing.JLabel();
        driveTitle25 = new javax.swing.JLabel();
        totalChapters = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        hpLabel = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();
        InicioBoton = new javax.swing.JButton();
        AppleBoton = new javax.swing.JButton();
        ConfiguracionBoton = new javax.swing.JButton();
        DashboardBoton = new javax.swing.JButton();
        GuardarBoton = new javax.swing.JButton();
        workersConfigurations = new javax.swing.JPanel();
        configTrabajTitle2 = new javax.swing.JLabel();
        placaB = new javax.swing.JPanel();
        placaBaseTitle = new javax.swing.JLabel();
        increasePlacaB = new javax.swing.JButton();
        placaBValues = new javax.swing.JTextField();
        decreasePlacaB = new javax.swing.JButton();
        cpu = new javax.swing.JPanel();
        cpuTitle = new javax.swing.JLabel();
        cpuValue = new javax.swing.JTextField();
        increaseCpu = new javax.swing.JButton();
        decreaseCpu = new javax.swing.JButton();
        RAM = new javax.swing.JPanel();
        memoriaRAMTitle = new javax.swing.JLabel();
        RAMValues = new javax.swing.JTextField();
        decreaseRAM = new javax.swing.JButton();
        increaseRAM = new javax.swing.JButton();
        fAliment = new javax.swing.JPanel();
        fAlimentTitle = new javax.swing.JLabel();
        decreaseFAliment = new javax.swing.JButton();
        FAlimentValues = new javax.swing.JTextField();
        increaseFAliment = new javax.swing.JButton();
        tGrafica = new javax.swing.JPanel();
        tGraficaTitle = new javax.swing.JLabel();
        increaseTGrafica = new javax.swing.JButton();
        TGraficaValues = new javax.swing.JTextField();
        decreaceTGrafica = new javax.swing.JButton();
        assembler = new javax.swing.JPanel();
        assemblerTitle = new javax.swing.JLabel();
        increaseAssembler = new javax.swing.JButton();
        assemblerValues = new javax.swing.JTextField();
        decreaceAssembler = new javax.swing.JButton();
        configTrabajTitle = new javax.swing.JLabel();
        maxConfTrabLabel = new javax.swing.JLabel();
        maxCap = new javax.swing.JLabel();
        diasPanel = new javax.swing.JPanel();
        diasEntregaTitle = new javax.swing.JLabel();
        currentDeadline = new javax.swing.JTextField();
        diasTitle = new javax.swing.JLabel();
        totalDays = new javax.swing.JTextField();
        diasPanel2 = new javax.swing.JPanel();
        driveTitle9 = new javax.swing.JLabel();
        tGraficaCompTotal = new javax.swing.JTextField();
        basicCompTotal = new javax.swing.JTextField();
        driveTitle10 = new javax.swing.JLabel();
        driveTitle27 = new javax.swing.JLabel();
        totalComp = new javax.swing.JTextField();
        storagePanel = new javax.swing.JPanel();
        storageTitle = new javax.swing.JLabel();
        storageTitle2 = new javax.swing.JLabel();
        placaBStorage = new javax.swing.JPanel();
        placaBTitle1 = new javax.swing.JLabel();
        placaBLimit1 = new javax.swing.JLabel();
        placaBValueStorage = new javax.swing.JTextField();
        cpuStorage = new javax.swing.JPanel();
        cpuTitle1 = new javax.swing.JLabel();
        cpuLimit1 = new javax.swing.JLabel();
        cpuValueStorage = new javax.swing.JTextField();
        RAMStorage = new javax.swing.JPanel();
        memoriaRAMTitle1 = new javax.swing.JLabel();
        RAMLimit1 = new javax.swing.JLabel();
        RAMValueStorage = new javax.swing.JTextField();
        fAlimentStorage = new javax.swing.JPanel();
        fAlimentTitle1 = new javax.swing.JLabel();
        fAlimentLimit1 = new javax.swing.JLabel();
        fAlimentValueStorage = new javax.swing.JTextField();
        tGraficaStorage = new javax.swing.JPanel();
        tGraficaLimit1 = new javax.swing.JLabel();
        tGraficaTitle1 = new javax.swing.JLabel();
        tGraficaValueStorage = new javax.swing.JTextField();
        assemblerStorage = new javax.swing.JPanel();
        assemblerLimit3 = new javax.swing.JLabel();
        assemblerTitle1 = new javax.swing.JLabel();
        assemblerValueStorage = new javax.swing.JTextField();
        costosGanancias = new javax.swing.JPanel();
        earningTitle = new javax.swing.JLabel();
        costTitle = new javax.swing.JLabel();
        driveTitle11 = new javax.swing.JLabel();
        profitTitle = new javax.swing.JLabel();
        cost = new javax.swing.JTextField();
        earning = new javax.swing.JTextField();
        profit = new javax.swing.JTextField();
        PM = new javax.swing.JPanel();
        statusPMTitle = new javax.swing.JLabel();
        driveTitle13 = new javax.swing.JLabel();
        driveTitle16 = new javax.swing.JLabel();
        projectManagerStatus = new javax.swing.JTextField();
        strikeCounter = new javax.swing.JTextField();
        cashPenality = new javax.swing.JTextField();
        driveTitle15 = new javax.swing.JLabel();
        director = new javax.swing.JPanel();
        statusDirectTitle = new javax.swing.JLabel();
        directorStatus = new javax.swing.JTextField();
        directorTitle = new javax.swing.JLabel();
        driveTitle = new javax.swing.JLabel();
        loteActual = new javax.swing.JPanel();
        driveTitle20 = new javax.swing.JLabel();
        tGraficaComp = new javax.swing.JTextField();
        basicComp = new javax.swing.JTextField();
        driveTitle24 = new javax.swing.JLabel();
        driveTitle26 = new javax.swing.JLabel();
        ultimoLote = new javax.swing.JPanel();
        driveTitle7 = new javax.swing.JLabel();
        tGraficaCompLast = new javax.swing.JTextField();
        basicCompLast = new javax.swing.JTextField();
        driveTitle22 = new javax.swing.JLabel();
        driveTitle28 = new javax.swing.JLabel();
        batchLastProfit = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        slogan = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        driveTitle4.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        driveTitle4.setForeground(new java.awt.Color(204, 204, 204));
        driveTitle4.setText("Capítulos plotTwist:");
        driveTitle4.setFocusable(false);

        plotTwistChapters.setEditable(false);
        plotTwistChapters.setBackground(new java.awt.Color(34, 46, 60));
        plotTwistChapters.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        plotTwistChapters.setForeground(new java.awt.Color(255, 255, 255));
        plotTwistChapters.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        plotTwistChapters.setText("0");
        plotTwistChapters.setBorder(null);
        plotTwistChapters.setFocusable(false);

        standardChapters.setEditable(false);
        standardChapters.setBackground(new java.awt.Color(34, 46, 60));
        standardChapters.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        standardChapters.setForeground(new java.awt.Color(255, 255, 255));
        standardChapters.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        standardChapters.setText("0");
        standardChapters.setBorder(null);
        standardChapters.setFocusable(false);

        driveTitle5.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        driveTitle5.setForeground(new java.awt.Color(204, 204, 204));
        driveTitle5.setText("Capítulos normales:");
        driveTitle5.setFocusable(false);

        driveTitle25.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        driveTitle25.setForeground(new java.awt.Color(204, 204, 204));
        driveTitle25.setText("Capítulos (total):");
        driveTitle25.setFocusable(false);

        totalChapters.setEditable(false);
        totalChapters.setBackground(new java.awt.Color(34, 46, 60));
        totalChapters.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        totalChapters.setForeground(new java.awt.Color(255, 255, 255));
        totalChapters.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalChapters.setText("0");
        totalChapters.setBorder(null);
        totalChapters.setFocusable(false);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1260, 810));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 204, 0));

        hpLabel.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        hpLabel.setText("Hp");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(hpLabel)
                .addContainerGap(70, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(hpLabel)
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 160, 50));

        titulo.setFont(new java.awt.Font("Arial Black", 1, 48)); // NOI18N
        titulo.setForeground(new java.awt.Color(255, 255, 255));
        titulo.setText("Hewlette Packard");
        jPanel1.add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, -1, -1));

        InicioBoton.setBackground(new java.awt.Color(102, 102, 102));
        InicioBoton.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        InicioBoton.setForeground(new java.awt.Color(255, 255, 255));
        InicioBoton.setText("Inicio");
        InicioBoton.setBorder(null);
        InicioBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InicioBotonActionPerformed(evt);
            }
        });
        jPanel1.add(InicioBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 160, 50));

        AppleBoton.setBackground(new java.awt.Color(102, 102, 102));
        AppleBoton.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        AppleBoton.setForeground(new java.awt.Color(255, 255, 255));
        AppleBoton.setText("Apple");
        AppleBoton.setBorder(null);
        AppleBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AppleBotonActionPerformed(evt);
            }
        });
        jPanel1.add(AppleBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 160, 50));

        ConfiguracionBoton.setBackground(new java.awt.Color(102, 102, 102));
        ConfiguracionBoton.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        ConfiguracionBoton.setForeground(new java.awt.Color(255, 255, 255));
        ConfiguracionBoton.setText("Configuración");
        ConfiguracionBoton.setBorder(null);
        ConfiguracionBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfiguracionBotonActionPerformed(evt);
            }
        });
        jPanel1.add(ConfiguracionBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 160, 50));

        DashboardBoton.setBackground(new java.awt.Color(102, 102, 102));
        DashboardBoton.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        DashboardBoton.setForeground(new java.awt.Color(255, 255, 255));
        DashboardBoton.setText("Dashboard");
        DashboardBoton.setBorder(null);
        DashboardBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DashboardBotonActionPerformed(evt);
            }
        });
        jPanel1.add(DashboardBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 160, 50));

        GuardarBoton.setBackground(new java.awt.Color(102, 102, 102));
        GuardarBoton.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        GuardarBoton.setForeground(new java.awt.Color(255, 255, 255));
        GuardarBoton.setText("Guardar");
        GuardarBoton.setBorder(null);
        GuardarBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarBotonActionPerformed(evt);
            }
        });
        jPanel1.add(GuardarBoton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 160, 50));

        workersConfigurations.setBackground(new java.awt.Color(0, 51, 153));

        configTrabajTitle2.setFont(new java.awt.Font("Arial Black", 1, 19)); // NOI18N
        configTrabajTitle2.setForeground(new java.awt.Color(255, 255, 255));
        configTrabajTitle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        configTrabajTitle2.setText("TRABAJADORES");

        placaB.setBackground(new java.awt.Color(255, 255, 255));
        placaB.setForeground(new java.awt.Color(60, 63, 65));

        placaBaseTitle.setBackground(new java.awt.Color(255, 255, 255));
        placaBaseTitle.setFont(new java.awt.Font("Arial Black", 0, 16)); // NOI18N
        placaBaseTitle.setForeground(new java.awt.Color(51, 51, 51));
        placaBaseTitle.setText("Prod. Placa Base");

        increasePlacaB.setBackground(new java.awt.Color(51, 51, 51));
        increasePlacaB.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        increasePlacaB.setForeground(new java.awt.Color(255, 255, 255));
        increasePlacaB.setText("+");
        increasePlacaB.setBorderPainted(false);
        increasePlacaB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increasePlacaBMouseClicked(evt);
            }
        });
        increasePlacaB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increasePlacaBActionPerformed(evt);
            }
        });

        placaBValues.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        placaBValues.setForeground(new java.awt.Color(51, 51, 51));
        placaBValues.setText("0");
        placaBValues.setBorder(null);
        placaBValues.setFocusable(false);
        placaBValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                placaBValuesActionPerformed(evt);
            }
        });

        decreasePlacaB.setBackground(new java.awt.Color(51, 51, 51));
        decreasePlacaB.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreasePlacaB.setForeground(new java.awt.Color(255, 255, 255));
        decreasePlacaB.setText("-");
        decreasePlacaB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreasePlacaBMouseClicked(evt);
            }
        });
        decreasePlacaB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreasePlacaBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout placaBLayout = new javax.swing.GroupLayout(placaB);
        placaB.setLayout(placaBLayout);
        placaBLayout.setHorizontalGroup(
            placaBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(placaBLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(placaBaseTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(36, 36, 36)
                .addComponent(decreasePlacaB)
                .addGap(18, 18, 18)
                .addComponent(placaBValues, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(increasePlacaB)
                .addGap(15, 15, 15))
        );
        placaBLayout.setVerticalGroup(
            placaBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(placaBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(placaBaseTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(increasePlacaB)
                .addComponent(placaBValues, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(decreasePlacaB))
        );

        cpu.setBackground(new java.awt.Color(255, 255, 255));
        cpu.setForeground(new java.awt.Color(60, 63, 65));
        cpu.setPreferredSize(new java.awt.Dimension(257, 44));

        cpuTitle.setBackground(new java.awt.Color(255, 255, 255));
        cpuTitle.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        cpuTitle.setForeground(new java.awt.Color(51, 51, 51));
        cpuTitle.setText("Prod. Cpus");

        cpuValue.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        cpuValue.setForeground(new java.awt.Color(51, 51, 51));
        cpuValue.setText("0");
        cpuValue.setBorder(null);
        cpuValue.setFocusable(false);
        cpuValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpuValueActionPerformed(evt);
            }
        });

        increaseCpu.setBackground(new java.awt.Color(51, 51, 51));
        increaseCpu.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        increaseCpu.setForeground(new java.awt.Color(255, 255, 255));
        increaseCpu.setText("+");
        increaseCpu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increaseCpuMouseClicked(evt);
            }
        });
        increaseCpu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseCpuActionPerformed(evt);
            }
        });

        decreaseCpu.setBackground(new java.awt.Color(51, 51, 51));
        decreaseCpu.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreaseCpu.setForeground(new java.awt.Color(255, 255, 255));
        decreaseCpu.setText("-");
        decreaseCpu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreaseCpuMouseClicked(evt);
            }
        });
        decreaseCpu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaseCpuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cpuLayout = new javax.swing.GroupLayout(cpu);
        cpu.setLayout(cpuLayout);
        cpuLayout.setHorizontalGroup(
            cpuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cpuLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(cpuTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(decreaseCpu)
                .addGap(18, 18, 18)
                .addComponent(cpuValue, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(increaseCpu)
                .addGap(14, 14, 14))
        );
        cpuLayout.setVerticalGroup(
            cpuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cpuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cpuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cpuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(increaseCpu)
                        .addComponent(cpuValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(decreaseCpu))
                    .addComponent(cpuTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        RAM.setBackground(new java.awt.Color(255, 255, 255));
        RAM.setForeground(new java.awt.Color(255, 255, 255));
        RAM.setPreferredSize(new java.awt.Dimension(257, 44));

        memoriaRAMTitle.setBackground(new java.awt.Color(255, 255, 255));
        memoriaRAMTitle.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        memoriaRAMTitle.setForeground(new java.awt.Color(51, 51, 51));
        memoriaRAMTitle.setText("Prod. Memoria RAM");

        RAMValues.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        RAMValues.setForeground(new java.awt.Color(51, 51, 51));
        RAMValues.setText("0");
        RAMValues.setBorder(null);
        RAMValues.setFocusable(false);
        RAMValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RAMValuesActionPerformed(evt);
            }
        });

        decreaseRAM.setBackground(new java.awt.Color(51, 51, 51));
        decreaseRAM.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreaseRAM.setForeground(new java.awt.Color(255, 255, 255));
        decreaseRAM.setText("-");
        decreaseRAM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreaseRAMMouseClicked(evt);
            }
        });
        decreaseRAM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaseRAMActionPerformed(evt);
            }
        });

        increaseRAM.setBackground(new java.awt.Color(51, 51, 51));
        increaseRAM.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        increaseRAM.setForeground(new java.awt.Color(255, 255, 255));
        increaseRAM.setText("+");
        increaseRAM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increaseRAMMouseClicked(evt);
            }
        });
        increaseRAM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseRAMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RAMLayout = new javax.swing.GroupLayout(RAM);
        RAM.setLayout(RAMLayout);
        RAMLayout.setHorizontalGroup(
            RAMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RAMLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(memoriaRAMTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(decreaseRAM)
                .addGap(18, 18, 18)
                .addComponent(RAMValues, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(increaseRAM)
                .addGap(15, 15, 15))
        );
        RAMLayout.setVerticalGroup(
            RAMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RAMLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RAMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(decreaseRAM)
                    .addComponent(RAMValues, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(increaseRAM)
                    .addComponent(memoriaRAMTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );

        fAliment.setBackground(new java.awt.Color(255, 255, 255));
        fAliment.setForeground(new java.awt.Color(255, 255, 255));
        fAliment.setPreferredSize(new java.awt.Dimension(257, 44));

        fAlimentTitle.setBackground(new java.awt.Color(255, 255, 255));
        fAlimentTitle.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        fAlimentTitle.setForeground(new java.awt.Color(51, 51, 51));
        fAlimentTitle.setText("Prod. F. Alimenta..");

        decreaseFAliment.setBackground(new java.awt.Color(51, 51, 51));
        decreaseFAliment.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreaseFAliment.setForeground(new java.awt.Color(204, 204, 204));
        decreaseFAliment.setText("-");
        decreaseFAliment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreaseFAlimentMouseClicked(evt);
            }
        });
        decreaseFAliment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaseFAlimentActionPerformed(evt);
            }
        });

        FAlimentValues.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        FAlimentValues.setForeground(new java.awt.Color(51, 51, 51));
        FAlimentValues.setText("0");
        FAlimentValues.setBorder(null);
        FAlimentValues.setFocusable(false);
        FAlimentValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FAlimentValuesActionPerformed(evt);
            }
        });

        increaseFAliment.setBackground(new java.awt.Color(51, 51, 51));
        increaseFAliment.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        increaseFAliment.setForeground(new java.awt.Color(255, 255, 255));
        increaseFAliment.setText("+");
        increaseFAliment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increaseFAlimentMouseClicked(evt);
            }
        });
        increaseFAliment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseFAlimentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout fAlimentLayout = new javax.swing.GroupLayout(fAliment);
        fAliment.setLayout(fAlimentLayout);
        fAlimentLayout.setHorizontalGroup(
            fAlimentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fAlimentLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(fAlimentTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(decreaseFAliment)
                .addGap(18, 18, 18)
                .addComponent(FAlimentValues, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(increaseFAliment)
                .addGap(15, 15, 15))
        );
        fAlimentLayout.setVerticalGroup(
            fAlimentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fAlimentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fAlimentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(decreaseFAliment)
                    .addComponent(FAlimentValues, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(increaseFAliment)
                    .addComponent(fAlimentTitle))
                .addContainerGap())
        );

        tGrafica.setBackground(new java.awt.Color(255, 255, 255));
        tGrafica.setForeground(new java.awt.Color(255, 255, 255));
        tGrafica.setPreferredSize(new java.awt.Dimension(257, 44));

        tGraficaTitle.setBackground(new java.awt.Color(255, 255, 255));
        tGraficaTitle.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        tGraficaTitle.setForeground(new java.awt.Color(51, 51, 51));
        tGraficaTitle.setText("Prod. T. Gráfica");

        increaseTGrafica.setBackground(new java.awt.Color(51, 51, 51));
        increaseTGrafica.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        increaseTGrafica.setForeground(new java.awt.Color(255, 255, 255));
        increaseTGrafica.setText("+");
        increaseTGrafica.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increaseTGraficaMouseClicked(evt);
            }
        });
        increaseTGrafica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseTGraficaActionPerformed(evt);
            }
        });

        TGraficaValues.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        TGraficaValues.setForeground(new java.awt.Color(51, 51, 51));
        TGraficaValues.setText("0");
        TGraficaValues.setBorder(null);
        TGraficaValues.setFocusable(false);
        TGraficaValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TGraficaValuesActionPerformed(evt);
            }
        });

        decreaceTGrafica.setBackground(new java.awt.Color(51, 51, 51));
        decreaceTGrafica.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreaceTGrafica.setForeground(new java.awt.Color(255, 255, 255));
        decreaceTGrafica.setText("-");
        decreaceTGrafica.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreaceTGraficaMouseClicked(evt);
            }
        });
        decreaceTGrafica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaceTGraficaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tGraficaLayout = new javax.swing.GroupLayout(tGrafica);
        tGrafica.setLayout(tGraficaLayout);
        tGraficaLayout.setHorizontalGroup(
            tGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tGraficaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(tGraficaTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(decreaceTGrafica)
                .addGap(18, 18, 18)
                .addComponent(TGraficaValues, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(increaseTGrafica)
                .addGap(16, 16, 16))
        );
        tGraficaLayout.setVerticalGroup(
            tGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tGraficaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(increaseTGrafica)
                    .addComponent(TGraficaValues, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(decreaceTGrafica)
                    .addComponent(tGraficaTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );

        assembler.setBackground(new java.awt.Color(255, 255, 255));
        assembler.setForeground(new java.awt.Color(255, 255, 255));
        assembler.setPreferredSize(new java.awt.Dimension(257, 44));

        assemblerTitle.setBackground(new java.awt.Color(255, 255, 255));
        assemblerTitle.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        assemblerTitle.setForeground(new java.awt.Color(51, 51, 51));
        assemblerTitle.setText("Ensambladores");

        increaseAssembler.setBackground(new java.awt.Color(51, 51, 51));
        increaseAssembler.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        increaseAssembler.setForeground(new java.awt.Color(255, 255, 255));
        increaseAssembler.setText("+");
        increaseAssembler.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increaseAssemblerMouseClicked(evt);
            }
        });
        increaseAssembler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseAssemblerActionPerformed(evt);
            }
        });

        assemblerValues.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        assemblerValues.setForeground(new java.awt.Color(51, 51, 51));
        assemblerValues.setText("0");
        assemblerValues.setBorder(null);
        assemblerValues.setFocusable(false);
        assemblerValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assemblerValuesActionPerformed(evt);
            }
        });

        decreaceAssembler.setBackground(new java.awt.Color(51, 51, 51));
        decreaceAssembler.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreaceAssembler.setForeground(new java.awt.Color(255, 255, 255));
        decreaceAssembler.setText("-");
        decreaceAssembler.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreaceAssemblerMouseClicked(evt);
            }
        });
        decreaceAssembler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaceAssemblerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout assemblerLayout = new javax.swing.GroupLayout(assembler);
        assembler.setLayout(assemblerLayout);
        assemblerLayout.setHorizontalGroup(
            assemblerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, assemblerLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(assemblerTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(decreaceAssembler)
                .addGap(18, 18, 18)
                .addComponent(assemblerValues, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(increaseAssembler)
                .addGap(16, 16, 16))
        );
        assemblerLayout.setVerticalGroup(
            assemblerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assemblerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(assemblerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(increaseAssembler)
                    .addComponent(assemblerValues, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(decreaceAssembler)
                    .addComponent(assemblerTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );

        configTrabajTitle.setFont(new java.awt.Font("Arial Black", 1, 19)); // NOI18N
        configTrabajTitle.setForeground(new java.awt.Color(255, 255, 255));
        configTrabajTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        configTrabajTitle.setText("CONFIGURACIÓN");

        maxConfTrabLabel.setFont(new java.awt.Font("Arial Black", 1, 19)); // NOI18N
        maxConfTrabLabel.setForeground(new java.awt.Color(255, 255, 255));
        maxConfTrabLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        maxConfTrabLabel.setText("Máximo:");

        maxCap.setFont(new java.awt.Font("Montserrat", 1, 19)); // NOI18N
        maxCap.setForeground(new java.awt.Color(51, 51, 51));
        maxCap.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout workersConfigurationsLayout = new javax.swing.GroupLayout(workersConfigurations);
        workersConfigurations.setLayout(workersConfigurationsLayout);
        workersConfigurationsLayout.setHorizontalGroup(
            workersConfigurationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(configTrabajTitle2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(configTrabajTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, workersConfigurationsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(workersConfigurationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(workersConfigurationsLayout.createSequentialGroup()
                        .addComponent(maxConfTrabLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(maxCap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(tGrafica, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                    .addComponent(fAliment, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                    .addComponent(RAM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                    .addComponent(cpu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                    .addComponent(placaB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(assembler, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        workersConfigurationsLayout.setVerticalGroup(
            workersConfigurationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(workersConfigurationsLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(configTrabajTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(configTrabajTitle2)
                .addGap(26, 26, 26)
                .addComponent(placaB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cpu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fAliment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(assembler, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(workersConfigurationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(maxConfTrabLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maxCap, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(workersConfigurations, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 350, 490));

        diasPanel.setBackground(new java.awt.Color(255, 204, 0));
        diasPanel.setForeground(new java.awt.Color(51, 51, 51));

        diasEntregaTitle.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        diasEntregaTitle.setText("Días para la entrega:");
        diasEntregaTitle.setFocusable(false);

        currentDeadline.setEditable(false);
        currentDeadline.setBackground(new java.awt.Color(255, 204, 51));
        currentDeadline.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        currentDeadline.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        currentDeadline.setText("0");
        currentDeadline.setBorder(null);
        currentDeadline.setFocusable(false);
        currentDeadline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                currentDeadlineActionPerformed(evt);
            }
        });

        diasTitle.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        diasTitle.setText("Días:");
        diasTitle.setFocusable(false);

        totalDays.setEditable(false);
        totalDays.setBackground(new java.awt.Color(255, 204, 0));
        totalDays.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        totalDays.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalDays.setText("0");
        totalDays.setBorder(null);
        totalDays.setFocusable(false);
        totalDays.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalDaysActionPerformed(evt);
            }
        });

        diasPanel2.setBackground(new java.awt.Color(255, 204, 0));
        diasPanel2.setForeground(new java.awt.Color(255, 204, 0));

        driveTitle9.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        driveTitle9.setText("Capítulos plotTwist:");
        driveTitle9.setFocusable(false);

        tGraficaCompTotal.setEditable(false);
        tGraficaCompTotal.setBackground(new java.awt.Color(255, 204, 0));
        tGraficaCompTotal.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        tGraficaCompTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tGraficaCompTotal.setText("0");
        tGraficaCompTotal.setBorder(null);
        tGraficaCompTotal.setFocusable(false);
        tGraficaCompTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tGraficaCompTotalActionPerformed(evt);
            }
        });

        basicCompTotal.setEditable(false);
        basicCompTotal.setBackground(new java.awt.Color(255, 204, 0));
        basicCompTotal.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        basicCompTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        basicCompTotal.setText("0");
        basicCompTotal.setBorder(null);
        basicCompTotal.setFocusable(false);
        basicCompTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                basicCompTotalActionPerformed(evt);
            }
        });

        driveTitle10.setBackground(new java.awt.Color(255, 204, 0));
        driveTitle10.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        driveTitle10.setText("Capítulos normales:");
        driveTitle10.setFocusable(false);

        driveTitle27.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        driveTitle27.setText("Capítulos (total):");
        driveTitle27.setFocusable(false);

        totalComp.setEditable(false);
        totalComp.setBackground(new java.awt.Color(255, 204, 0));
        totalComp.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        totalComp.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalComp.setText("0");
        totalComp.setBorder(null);
        totalComp.setFocusable(false);
        totalComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalCompActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout diasPanel2Layout = new javax.swing.GroupLayout(diasPanel2);
        diasPanel2.setLayout(diasPanel2Layout);
        diasPanel2Layout.setHorizontalGroup(
            diasPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(diasPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(diasPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(diasPanel2Layout.createSequentialGroup()
                        .addGroup(diasPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(driveTitle9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(driveTitle10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(diasPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(basicCompTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tGraficaCompTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(diasPanel2Layout.createSequentialGroup()
                        .addComponent(driveTitle27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(totalComp, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(370, 370, 370))
        );
        diasPanel2Layout.setVerticalGroup(
            diasPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(diasPanel2Layout.createSequentialGroup()
                .addGroup(diasPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(driveTitle27, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalComp, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(diasPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(basicCompTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(driveTitle10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(diasPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(driveTitle9)
                    .addComponent(tGraficaCompTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout diasPanelLayout = new javax.swing.GroupLayout(diasPanel);
        diasPanel.setLayout(diasPanelLayout);
        diasPanelLayout.setHorizontalGroup(
            diasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(diasPanelLayout.createSequentialGroup()
                .addGap(270, 270, 270)
                .addGroup(diasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(diasTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(diasEntregaTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(diasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(diasPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(currentDeadline, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(totalDays, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                .addGap(82, 82, 82)
                .addComponent(diasPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(177, 177, 177))
        );
        diasPanelLayout.setVerticalGroup(
            diasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(diasPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(diasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(diasTitle)
                    .addComponent(totalDays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(diasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentDeadline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(diasEntregaTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
            .addComponent(diasPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jPanel1.add(diasPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 950, 100));

        storagePanel.setBackground(new java.awt.Color(0, 153, 255));

        storageTitle.setFont(new java.awt.Font("Arial Black", 1, 19)); // NOI18N
        storageTitle.setForeground(new java.awt.Color(255, 255, 255));
        storageTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        storageTitle.setText("DISPONIBILIDAD EN");

        storageTitle2.setFont(new java.awt.Font("Arial Black", 1, 19)); // NOI18N
        storageTitle2.setForeground(new java.awt.Color(255, 255, 255));
        storageTitle2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        storageTitle2.setText("ALMACENAMIENTO");

        placaBStorage.setBackground(new java.awt.Color(255, 255, 255));
        placaBStorage.setForeground(new java.awt.Color(60, 63, 65));
        placaBStorage.setPreferredSize(new java.awt.Dimension(218, 44));

        placaBTitle1.setBackground(new java.awt.Color(255, 255, 255));
        placaBTitle1.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        placaBTitle1.setForeground(new java.awt.Color(51, 51, 51));
        placaBTitle1.setText("Placa Base:");

        placaBLimit1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        placaBLimit1.setForeground(new java.awt.Color(51, 51, 51));
        placaBLimit1.setText("/25");
        placaBLimit1.setFocusable(false);

        placaBValueStorage.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        placaBValueStorage.setForeground(new java.awt.Color(51, 51, 51));
        placaBValueStorage.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        placaBValueStorage.setText("0");
        placaBValueStorage.setBorder(null);
        placaBValueStorage.setFocusable(false);
        placaBValueStorage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                placaBValueStorageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout placaBStorageLayout = new javax.swing.GroupLayout(placaBStorage);
        placaBStorage.setLayout(placaBStorageLayout);
        placaBStorageLayout.setHorizontalGroup(
            placaBStorageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(placaBStorageLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(placaBTitle1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(placaBValueStorage, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(placaBLimit1)
                .addGap(16, 16, 16))
        );
        placaBStorageLayout.setVerticalGroup(
            placaBStorageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(placaBStorageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(placaBStorageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(placaBValueStorage, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(placaBTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(placaBLimit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        cpuStorage.setBackground(new java.awt.Color(255, 255, 255));
        cpuStorage.setForeground(new java.awt.Color(60, 63, 65));
        cpuStorage.setPreferredSize(new java.awt.Dimension(218, 44));

        cpuTitle1.setBackground(new java.awt.Color(255, 255, 255));
        cpuTitle1.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        cpuTitle1.setForeground(new java.awt.Color(51, 51, 51));
        cpuTitle1.setText("Cpu:");

        cpuLimit1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        cpuLimit1.setForeground(new java.awt.Color(51, 51, 51));
        cpuLimit1.setText("/20");
        cpuLimit1.setFocusable(false);

        cpuValueStorage.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        cpuValueStorage.setForeground(new java.awt.Color(51, 51, 51));
        cpuValueStorage.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cpuValueStorage.setText("0");
        cpuValueStorage.setBorder(null);
        cpuValueStorage.setFocusable(false);
        cpuValueStorage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpuValueStorageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cpuStorageLayout = new javax.swing.GroupLayout(cpuStorage);
        cpuStorage.setLayout(cpuStorageLayout);
        cpuStorageLayout.setHorizontalGroup(
            cpuStorageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cpuStorageLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(cpuTitle1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cpuValueStorage, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cpuLimit1)
                .addGap(15, 15, 15))
        );
        cpuStorageLayout.setVerticalGroup(
            cpuStorageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cpuStorageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cpuStorageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cpuTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(cpuLimit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cpuValueStorage))
                .addContainerGap())
        );

        RAMStorage.setBackground(new java.awt.Color(255, 255, 255));
        RAMStorage.setForeground(new java.awt.Color(60, 63, 65));
        RAMStorage.setPreferredSize(new java.awt.Dimension(218, 44));

        memoriaRAMTitle1.setBackground(new java.awt.Color(255, 255, 255));
        memoriaRAMTitle1.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        memoriaRAMTitle1.setForeground(new java.awt.Color(51, 51, 51));
        memoriaRAMTitle1.setText("Memoria RAM:");

        RAMLimit1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        RAMLimit1.setForeground(new java.awt.Color(51, 51, 51));
        RAMLimit1.setText("/55");
        RAMLimit1.setFocusable(false);

        RAMValueStorage.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        RAMValueStorage.setForeground(new java.awt.Color(51, 51, 51));
        RAMValueStorage.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        RAMValueStorage.setText("0");
        RAMValueStorage.setBorder(null);
        RAMValueStorage.setFocusable(false);
        RAMValueStorage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RAMValueStorageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RAMStorageLayout = new javax.swing.GroupLayout(RAMStorage);
        RAMStorage.setLayout(RAMStorageLayout);
        RAMStorageLayout.setHorizontalGroup(
            RAMStorageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RAMStorageLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(memoriaRAMTitle1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RAMValueStorage, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RAMLimit1)
                .addGap(14, 14, 14))
        );
        RAMStorageLayout.setVerticalGroup(
            RAMStorageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RAMStorageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RAMStorageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RAMValueStorage, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(memoriaRAMTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RAMLimit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        fAlimentStorage.setBackground(new java.awt.Color(255, 255, 255));
        fAlimentStorage.setForeground(new java.awt.Color(60, 63, 65));
        fAlimentStorage.setPreferredSize(new java.awt.Dimension(218, 44));

        fAlimentTitle1.setBackground(new java.awt.Color(255, 255, 255));
        fAlimentTitle1.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        fAlimentTitle1.setForeground(new java.awt.Color(51, 51, 51));
        fAlimentTitle1.setText("F. Alimentación:");

        fAlimentLimit1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        fAlimentLimit1.setForeground(new java.awt.Color(51, 51, 51));
        fAlimentLimit1.setText("/35");
        fAlimentLimit1.setFocusable(false);

        fAlimentValueStorage.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        fAlimentValueStorage.setForeground(new java.awt.Color(51, 51, 51));
        fAlimentValueStorage.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        fAlimentValueStorage.setText("0");
        fAlimentValueStorage.setBorder(null);
        fAlimentValueStorage.setFocusable(false);
        fAlimentValueStorage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fAlimentValueStorageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout fAlimentStorageLayout = new javax.swing.GroupLayout(fAlimentStorage);
        fAlimentStorage.setLayout(fAlimentStorageLayout);
        fAlimentStorageLayout.setHorizontalGroup(
            fAlimentStorageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fAlimentStorageLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(fAlimentTitle1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(fAlimentValueStorage, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fAlimentLimit1)
                .addGap(14, 14, 14))
        );
        fAlimentStorageLayout.setVerticalGroup(
            fAlimentStorageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fAlimentStorageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fAlimentStorageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fAlimentValueStorage, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(fAlimentTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fAlimentLimit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tGraficaStorage.setBackground(new java.awt.Color(255, 255, 255));
        tGraficaStorage.setForeground(new java.awt.Color(60, 63, 65));
        tGraficaStorage.setFocusable(false);
        tGraficaStorage.setPreferredSize(new java.awt.Dimension(218, 44));

        tGraficaLimit1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        tGraficaLimit1.setForeground(new java.awt.Color(51, 51, 51));
        tGraficaLimit1.setText("/10");
        tGraficaLimit1.setFocusable(false);

        tGraficaTitle1.setBackground(new java.awt.Color(255, 255, 255));
        tGraficaTitle1.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        tGraficaTitle1.setForeground(new java.awt.Color(51, 51, 51));
        tGraficaTitle1.setText("Tarjeta Gráfica:");

        tGraficaValueStorage.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        tGraficaValueStorage.setForeground(new java.awt.Color(51, 51, 51));
        tGraficaValueStorage.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tGraficaValueStorage.setText("0");
        tGraficaValueStorage.setBorder(null);
        tGraficaValueStorage.setFocusable(false);
        tGraficaValueStorage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tGraficaValueStorageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tGraficaStorageLayout = new javax.swing.GroupLayout(tGraficaStorage);
        tGraficaStorage.setLayout(tGraficaStorageLayout);
        tGraficaStorageLayout.setHorizontalGroup(
            tGraficaStorageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tGraficaStorageLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(tGraficaTitle1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tGraficaValueStorage, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tGraficaLimit1)
                .addGap(14, 14, 14))
        );
        tGraficaStorageLayout.setVerticalGroup(
            tGraficaStorageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tGraficaStorageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tGraficaStorageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tGraficaValueStorage, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(tGraficaTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tGraficaLimit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        assemblerStorage.setBackground(new java.awt.Color(255, 255, 255));
        assemblerStorage.setForeground(new java.awt.Color(60, 63, 65));
        assemblerStorage.setFocusable(false);
        assemblerStorage.setPreferredSize(new java.awt.Dimension(218, 44));

        assemblerLimit3.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        assemblerLimit3.setForeground(new java.awt.Color(51, 51, 51));
        assemblerLimit3.setFocusable(false);

        assemblerTitle1.setBackground(new java.awt.Color(255, 255, 255));
        assemblerTitle1.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        assemblerTitle1.setForeground(new java.awt.Color(51, 51, 51));
        assemblerTitle1.setText("Ensambladores:");

        assemblerValueStorage.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        assemblerValueStorage.setForeground(new java.awt.Color(51, 51, 51));
        assemblerValueStorage.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        assemblerValueStorage.setText("0");
        assemblerValueStorage.setBorder(null);
        assemblerValueStorage.setFocusable(false);
        assemblerValueStorage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assemblerValueStorageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout assemblerStorageLayout = new javax.swing.GroupLayout(assemblerStorage);
        assemblerStorage.setLayout(assemblerStorageLayout);
        assemblerStorageLayout.setHorizontalGroup(
            assemblerStorageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, assemblerStorageLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(assemblerTitle1)
                .addGap(18, 18, 18)
                .addComponent(assemblerValueStorage, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(assemblerLimit3)
                .addGap(14, 14, 14))
        );
        assemblerStorageLayout.setVerticalGroup(
            assemblerStorageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assemblerStorageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(assemblerStorageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(assemblerLimit3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(assemblerTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(assemblerValueStorage))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout storagePanelLayout = new javax.swing.GroupLayout(storagePanel);
        storagePanel.setLayout(storagePanelLayout);
        storagePanelLayout.setHorizontalGroup(
            storagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(storagePanelLayout.createSequentialGroup()
                .addGroup(storagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(storageTitle2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(storagePanelLayout.createSequentialGroup()
                        .addGroup(storagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(storagePanelLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(storagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fAlimentStorage, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                                    .addComponent(RAMStorage, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                                    .addComponent(cpuStorage, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                                    .addComponent(placaBStorage, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                                    .addComponent(tGraficaStorage, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                                    .addComponent(assemblerStorage, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)))
                            .addGroup(storagePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(storageTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 6, Short.MAX_VALUE)))
                .addContainerGap())
        );
        storagePanelLayout.setVerticalGroup(
            storagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(storagePanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(storageTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(storageTitle2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(placaBStorage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cpuStorage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RAMStorage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fAlimentStorage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tGraficaStorage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(assemblerStorage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jPanel1.add(storagePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 230, 270, 490));

        costosGanancias.setBackground(new java.awt.Color(0, 51, 153));
        costosGanancias.setForeground(new java.awt.Color(51, 51, 51));
        costosGanancias.setEnabled(false);

        earningTitle.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        earningTitle.setForeground(new java.awt.Color(204, 204, 204));
        earningTitle.setText("Ganancia bruta:");
        earningTitle.setFocusable(false);

        costTitle.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        costTitle.setForeground(new java.awt.Color(204, 204, 204));
        costTitle.setText("Costos operativos:");
        costTitle.setFocusable(false);

        driveTitle11.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        driveTitle11.setForeground(new java.awt.Color(204, 204, 204));
        driveTitle11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        driveTitle11.setText("COSTOS/ GANANCIAS");
        driveTitle11.setFocusable(false);

        profitTitle.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        profitTitle.setForeground(new java.awt.Color(204, 204, 204));
        profitTitle.setText("Ganancia neta:");
        profitTitle.setFocusable(false);

        cost.setBackground(new java.awt.Color(0, 51, 153));
        cost.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        cost.setForeground(new java.awt.Color(255, 255, 255));
        cost.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cost.setText("0");
        cost.setBorder(null);
        cost.setFocusable(false);
        cost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                costActionPerformed(evt);
            }
        });

        earning.setBackground(new java.awt.Color(0, 51, 153));
        earning.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        earning.setForeground(new java.awt.Color(255, 255, 255));
        earning.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        earning.setText("0");
        earning.setBorder(null);
        earning.setFocusable(false);
        earning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                earningActionPerformed(evt);
            }
        });

        profit.setBackground(new java.awt.Color(0, 51, 153));
        profit.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        profit.setForeground(new java.awt.Color(255, 255, 255));
        profit.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        profit.setText("0");
        profit.setBorder(null);
        profit.setFocusable(false);
        profit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout costosGananciasLayout = new javax.swing.GroupLayout(costosGanancias);
        costosGanancias.setLayout(costosGananciasLayout);
        costosGananciasLayout.setHorizontalGroup(
            costosGananciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, costosGananciasLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(costosGananciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(costosGananciasLayout.createSequentialGroup()
                        .addComponent(costTitle)
                        .addGap(11, 11, 11))
                    .addGroup(costosGananciasLayout.createSequentialGroup()
                        .addGroup(costosGananciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(earningTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(profitTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(costosGananciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(earning, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cost, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(profit, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
            .addComponent(driveTitle11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        costosGananciasLayout.setVerticalGroup(
            costosGananciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, costosGananciasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(driveTitle11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(costosGananciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(costTitle)
                    .addComponent(cost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(costosGananciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(earningTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(earning, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(costosGananciasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(profitTitle)
                    .addComponent(profit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        jPanel1.add(costosGanancias, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 230, 300, 120));

        PM.setBackground(new java.awt.Color(0, 51, 153));
        PM.setForeground(new java.awt.Color(51, 51, 51));
        PM.setEnabled(false);

        statusPMTitle.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        statusPMTitle.setForeground(new java.awt.Color(204, 204, 204));
        statusPMTitle.setText("Estado:");
        statusPMTitle.setFocusable(false);

        driveTitle13.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        driveTitle13.setForeground(new java.awt.Color(204, 204, 204));
        driveTitle13.setText("Faltas:");
        driveTitle13.setFocusable(false);

        driveTitle16.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        driveTitle16.setForeground(new java.awt.Color(204, 204, 204));
        driveTitle16.setText("Penalización ($):");
        driveTitle16.setFocusable(false);

        projectManagerStatus.setBackground(new java.awt.Color(0, 51, 153));
        projectManagerStatus.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        projectManagerStatus.setForeground(new java.awt.Color(255, 255, 255));
        projectManagerStatus.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        projectManagerStatus.setText("Por comenzar");
        projectManagerStatus.setBorder(null);
        projectManagerStatus.setFocusable(false);
        projectManagerStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projectManagerStatusActionPerformed(evt);
            }
        });

        strikeCounter.setBackground(new java.awt.Color(0, 51, 153));
        strikeCounter.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        strikeCounter.setForeground(new java.awt.Color(255, 255, 255));
        strikeCounter.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        strikeCounter.setText("0");
        strikeCounter.setBorder(null);
        strikeCounter.setFocusable(false);
        strikeCounter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                strikeCounterActionPerformed(evt);
            }
        });

        cashPenality.setBackground(new java.awt.Color(0, 51, 153));
        cashPenality.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        cashPenality.setForeground(new java.awt.Color(255, 255, 255));
        cashPenality.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        cashPenality.setText("0");
        cashPenality.setBorder(null);
        cashPenality.setFocusable(false);
        cashPenality.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cashPenalityActionPerformed(evt);
            }
        });

        driveTitle15.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        driveTitle15.setForeground(new java.awt.Color(204, 204, 204));
        driveTitle15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        driveTitle15.setText("PROJECT MANAGER");
        driveTitle15.setFocusable(false);

        javax.swing.GroupLayout PMLayout = new javax.swing.GroupLayout(PM);
        PM.setLayout(PMLayout);
        PMLayout.setHorizontalGroup(
            PMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PMLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(PMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(driveTitle13, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(driveTitle16)
                    .addComponent(statusPMTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(PMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PMLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(strikeCounter, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(PMLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(PMLayout.createSequentialGroup()
                                .addComponent(projectManagerStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(31, Short.MAX_VALUE))
                            .addGroup(PMLayout.createSequentialGroup()
                                .addComponent(cashPenality)
                                .addGap(34, 34, 34))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PMLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(driveTitle15, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PMLayout.setVerticalGroup(
            PMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PMLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(driveTitle15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusPMTitle)
                    .addComponent(projectManagerStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(driveTitle13)
                    .addComponent(strikeCounter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(driveTitle16)
                    .addComponent(cashPenality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );

        jPanel1.add(PM, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 660, 300, 120));

        director.setBackground(new java.awt.Color(0, 51, 153));
        director.setForeground(new java.awt.Color(51, 51, 51));

        statusDirectTitle.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        statusDirectTitle.setForeground(new java.awt.Color(204, 204, 204));
        statusDirectTitle.setText("Estado:");
        statusDirectTitle.setFocusable(false);

        directorStatus.setBackground(new java.awt.Color(0, 51, 153));
        directorStatus.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        directorStatus.setForeground(new java.awt.Color(255, 255, 255));
        directorStatus.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        directorStatus.setText("0");
        directorStatus.setBorder(null);
        directorStatus.setFocusable(false);
        directorStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                directorStatusActionPerformed(evt);
            }
        });

        directorTitle.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        directorTitle.setForeground(new java.awt.Color(204, 204, 204));
        directorTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        directorTitle.setText("DIRECTOR");
        directorTitle.setFocusable(false);

        javax.swing.GroupLayout directorLayout = new javax.swing.GroupLayout(director);
        director.setLayout(directorLayout);
        directorLayout.setHorizontalGroup(
            directorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(directorLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(statusDirectTitle)
                .addGap(67, 67, 67)
                .addComponent(directorStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(directorTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        directorLayout.setVerticalGroup(
            directorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, directorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(directorTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(directorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusDirectTitle)
                    .addComponent(directorStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        jPanel1.add(director, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 600, 300, 60));

        driveTitle.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        driveTitle.setForeground(new java.awt.Color(51, 51, 51));
        driveTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        driveTitle.setText("Lote Actual");
        jPanel1.add(driveTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 380, 300, 30));

        loteActual.setBackground(new java.awt.Color(255, 255, 255));
        loteActual.setForeground(new java.awt.Color(51, 51, 51));

        driveTitle20.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        driveTitle20.setText("Computadores T.Gráfica:");
        driveTitle20.setFocusable(false);

        tGraficaComp.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        tGraficaComp.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tGraficaComp.setText("0");
        tGraficaComp.setBorder(null);
        tGraficaComp.setFocusable(false);
        tGraficaComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tGraficaCompActionPerformed(evt);
            }
        });

        basicComp.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        basicComp.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        basicComp.setText("0");
        basicComp.setBorder(null);
        basicComp.setFocusable(false);
        basicComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                basicCompActionPerformed(evt);
            }
        });

        driveTitle24.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        driveTitle24.setText("Computadores Estándar:");
        driveTitle24.setFocusable(false);

        javax.swing.GroupLayout loteActualLayout = new javax.swing.GroupLayout(loteActual);
        loteActual.setLayout(loteActualLayout);
        loteActualLayout.setHorizontalGroup(
            loteActualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loteActualLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loteActualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(driveTitle20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(driveTitle24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(loteActualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loteActualLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(tGraficaComp, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(basicComp, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(56, 56, 56))
        );
        loteActualLayout.setVerticalGroup(
            loteActualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loteActualLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loteActualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(basicComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(driveTitle24, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(loteActualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(driveTitle20)
                    .addComponent(tGraficaComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(loteActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 400, 270, -1));

        driveTitle26.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        driveTitle26.setForeground(new java.awt.Color(255, 255, 255));
        driveTitle26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        driveTitle26.setText("Último Lote");
        jPanel1.add(driveTitle26, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 460, 300, 30));

        ultimoLote.setBackground(new java.awt.Color(255, 255, 255));
        ultimoLote.setForeground(new java.awt.Color(51, 51, 51));

        driveTitle7.setBackground(new java.awt.Color(255, 255, 255));
        driveTitle7.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        driveTitle7.setText("Computadores T.Grádica:");
        driveTitle7.setFocusable(false);

        tGraficaCompLast.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        tGraficaCompLast.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        tGraficaCompLast.setText("0");
        tGraficaCompLast.setBorder(null);
        tGraficaCompLast.setFocusable(false);
        tGraficaCompLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tGraficaCompLastActionPerformed(evt);
            }
        });

        basicCompLast.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        basicCompLast.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        basicCompLast.setText("0");
        basicCompLast.setBorder(null);
        basicCompLast.setFocusable(false);
        basicCompLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                basicCompLastActionPerformed(evt);
            }
        });

        driveTitle22.setBackground(new java.awt.Color(255, 255, 255));
        driveTitle22.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        driveTitle22.setText("Computadores Estándar:");
        driveTitle22.setFocusable(false);

        driveTitle28.setBackground(new java.awt.Color(255, 255, 255));
        driveTitle28.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        driveTitle28.setText("Ganancia neta:");
        driveTitle28.setFocusable(false);

        batchLastProfit.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        batchLastProfit.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        batchLastProfit.setText("0");
        batchLastProfit.setBorder(null);
        batchLastProfit.setFocusable(false);
        batchLastProfit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batchLastProfitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ultimoLoteLayout = new javax.swing.GroupLayout(ultimoLote);
        ultimoLote.setLayout(ultimoLoteLayout);
        ultimoLoteLayout.setHorizontalGroup(
            ultimoLoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ultimoLoteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ultimoLoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(ultimoLoteLayout.createSequentialGroup()
                        .addComponent(driveTitle28, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(batchLastProfit))
                    .addGroup(ultimoLoteLayout.createSequentialGroup()
                        .addGroup(ultimoLoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(driveTitle7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(driveTitle22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(ultimoLoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tGraficaCompLast, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(basicCompLast, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        ultimoLoteLayout.setVerticalGroup(
            ultimoLoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ultimoLoteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ultimoLoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(basicCompLast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(driveTitle22, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ultimoLoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(driveTitle7)
                    .addComponent(tGraficaCompLast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ultimoLoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ultimoLoteLayout.createSequentialGroup()
                        .addComponent(driveTitle28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1, 1, 1))
                    .addComponent(batchLastProfit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );

        jPanel1.add(ultimoLote, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 480, 270, 80));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Captura de pantalla 2024-10-07 204326.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 350, -1, -1));

        slogan.setBackground(new java.awt.Color(255, 204, 0));
        slogan.setForeground(new java.awt.Color(255, 204, 0));

        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel2.setText("“Make it Matter Keep Reinventing”");

        javax.swing.GroupLayout sloganLayout = new javax.swing.GroupLayout(slogan);
        slogan.setLayout(sloganLayout);
        sloganLayout.setHorizontalGroup(
            sloganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sloganLayout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(jLabel2)
                .addContainerGap(148, Short.MAX_VALUE))
        );
        sloganLayout.setVerticalGroup(
            sloganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sloganLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(15, 15, 15))
        );

        jPanel1.add(slogan, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 720, 630, 60));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/hpLogo.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 10, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1260, 810));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void InicioBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InicioBotonActionPerformed
        
    }//GEN-LAST:event_InicioBotonActionPerformed

    private void AppleBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AppleBotonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AppleBotonActionPerformed

    private void ConfiguracionBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfiguracionBotonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ConfiguracionBotonActionPerformed

    private void DashboardBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DashboardBotonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DashboardBotonActionPerformed

    private void GuardarBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarBotonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GuardarBotonActionPerformed

    private void increasePlacaBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_increasePlacaBMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_increasePlacaBMouseClicked

    private void increasePlacaBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increasePlacaBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_increasePlacaBActionPerformed

    private void placaBValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_placaBValuesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_placaBValuesActionPerformed

    private void decreasePlacaBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decreasePlacaBMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_decreasePlacaBMouseClicked

    private void decreasePlacaBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreasePlacaBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_decreasePlacaBActionPerformed

    private void cpuValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cpuValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cpuValueActionPerformed

    private void increaseCpuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_increaseCpuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_increaseCpuMouseClicked

    private void increaseCpuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseCpuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_increaseCpuActionPerformed

    private void decreaseCpuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decreaseCpuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_decreaseCpuMouseClicked

    private void decreaseCpuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreaseCpuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_decreaseCpuActionPerformed

    private void RAMValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RAMValuesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RAMValuesActionPerformed

    private void decreaseRAMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decreaseRAMMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_decreaseRAMMouseClicked

    private void decreaseRAMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreaseRAMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_decreaseRAMActionPerformed

    private void increaseRAMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_increaseRAMMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_increaseRAMMouseClicked

    private void increaseRAMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseRAMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_increaseRAMActionPerformed

    private void decreaseFAlimentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decreaseFAlimentMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_decreaseFAlimentMouseClicked

    private void decreaseFAlimentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreaseFAlimentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_decreaseFAlimentActionPerformed

    private void FAlimentValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FAlimentValuesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FAlimentValuesActionPerformed

    private void increaseFAlimentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_increaseFAlimentMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_increaseFAlimentMouseClicked

    private void increaseFAlimentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseFAlimentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_increaseFAlimentActionPerformed

    private void increaseTGraficaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_increaseTGraficaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_increaseTGraficaMouseClicked

    private void increaseTGraficaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseTGraficaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_increaseTGraficaActionPerformed

    private void TGraficaValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TGraficaValuesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TGraficaValuesActionPerformed

    private void decreaceTGraficaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decreaceTGraficaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_decreaceTGraficaMouseClicked

    private void decreaceTGraficaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreaceTGraficaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_decreaceTGraficaActionPerformed

    private void increaseAssemblerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_increaseAssemblerMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_increaseAssemblerMouseClicked

    private void increaseAssemblerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseAssemblerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_increaseAssemblerActionPerformed

    private void assemblerValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assemblerValuesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_assemblerValuesActionPerformed

    private void decreaceAssemblerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decreaceAssemblerMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_decreaceAssemblerMouseClicked

    private void decreaceAssemblerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreaceAssemblerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_decreaceAssemblerActionPerformed

    private void placaBValueStorageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_placaBValueStorageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_placaBValueStorageActionPerformed

    private void cpuValueStorageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cpuValueStorageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cpuValueStorageActionPerformed

    private void RAMValueStorageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RAMValueStorageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RAMValueStorageActionPerformed

    private void fAlimentValueStorageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fAlimentValueStorageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fAlimentValueStorageActionPerformed

    private void tGraficaValueStorageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tGraficaValueStorageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tGraficaValueStorageActionPerformed

    private void assemblerValueStorageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assemblerValueStorageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_assemblerValueStorageActionPerformed

    private void currentDeadlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_currentDeadlineActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_currentDeadlineActionPerformed

    private void totalDaysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalDaysActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalDaysActionPerformed

    private void costActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_costActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_costActionPerformed

    private void earningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_earningActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_earningActionPerformed

    private void profitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_profitActionPerformed

    private void projectManagerStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projectManagerStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_projectManagerStatusActionPerformed

    private void strikeCounterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_strikeCounterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_strikeCounterActionPerformed

    private void cashPenalityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cashPenalityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cashPenalityActionPerformed

    private void directorStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_directorStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_directorStatusActionPerformed

    private void tGraficaCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tGraficaCompActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tGraficaCompActionPerformed

    private void basicCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_basicCompActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_basicCompActionPerformed

    private void tGraficaCompLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tGraficaCompLastActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tGraficaCompLastActionPerformed

    private void basicCompLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_basicCompLastActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_basicCompLastActionPerformed

    private void batchLastProfitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batchLastProfitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_batchLastProfitActionPerformed

    private void tGraficaCompTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tGraficaCompTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tGraficaCompTotalActionPerformed

    private void basicCompTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_basicCompTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_basicCompTotalActionPerformed

    private void totalCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalCompActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalCompActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HpSimulador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HpSimulador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HpSimulador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HpSimulador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HpSimulador(apple).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AppleBoton;
    private javax.swing.JButton ConfiguracionBoton;
    private javax.swing.JButton DashboardBoton;
    private javax.swing.JTextField FAlimentValues;
    private javax.swing.JButton GuardarBoton;
    private javax.swing.JButton InicioBoton;
    private javax.swing.JPanel PM;
    private javax.swing.JPanel RAM;
    private javax.swing.JLabel RAMLimit1;
    private javax.swing.JPanel RAMStorage;
    private javax.swing.JTextField RAMValueStorage;
    private javax.swing.JTextField RAMValues;
    private javax.swing.JTextField TGraficaValues;
    private javax.swing.JPanel assembler;
    private javax.swing.JLabel assemblerLimit3;
    private javax.swing.JPanel assemblerStorage;
    private javax.swing.JLabel assemblerTitle;
    private javax.swing.JLabel assemblerTitle1;
    private javax.swing.JTextField assemblerValueStorage;
    private javax.swing.JTextField assemblerValues;
    private javax.swing.JTextField basicComp;
    private javax.swing.JTextField basicCompLast;
    private javax.swing.JTextField basicCompTotal;
    private javax.swing.JTextField batchLastProfit;
    private javax.swing.JTextField cashPenality;
    private javax.swing.JLabel configTrabajTitle;
    private javax.swing.JLabel configTrabajTitle2;
    private javax.swing.JTextField cost;
    private javax.swing.JLabel costTitle;
    private javax.swing.JPanel costosGanancias;
    private javax.swing.JPanel cpu;
    private javax.swing.JLabel cpuLimit1;
    private javax.swing.JPanel cpuStorage;
    private javax.swing.JLabel cpuTitle;
    private javax.swing.JLabel cpuTitle1;
    private javax.swing.JTextField cpuValue;
    private javax.swing.JTextField cpuValueStorage;
    private javax.swing.JTextField currentDeadline;
    private javax.swing.JButton decreaceAssembler;
    private javax.swing.JButton decreaceTGrafica;
    private javax.swing.JButton decreaseCpu;
    private javax.swing.JButton decreaseFAliment;
    private javax.swing.JButton decreasePlacaB;
    private javax.swing.JButton decreaseRAM;
    private javax.swing.JLabel diasEntregaTitle;
    private javax.swing.JPanel diasPanel;
    private javax.swing.JPanel diasPanel2;
    private javax.swing.JLabel diasTitle;
    private javax.swing.JPanel director;
    private javax.swing.JTextField directorStatus;
    private javax.swing.JLabel directorTitle;
    private javax.swing.JLabel driveTitle;
    private javax.swing.JLabel driveTitle10;
    private javax.swing.JLabel driveTitle11;
    private javax.swing.JLabel driveTitle13;
    private javax.swing.JLabel driveTitle15;
    private javax.swing.JLabel driveTitle16;
    private javax.swing.JLabel driveTitle20;
    private javax.swing.JLabel driveTitle22;
    private javax.swing.JLabel driveTitle24;
    private javax.swing.JLabel driveTitle25;
    private javax.swing.JLabel driveTitle26;
    private javax.swing.JLabel driveTitle27;
    private javax.swing.JLabel driveTitle28;
    private javax.swing.JLabel driveTitle4;
    private javax.swing.JLabel driveTitle5;
    private javax.swing.JLabel driveTitle7;
    private javax.swing.JLabel driveTitle9;
    private javax.swing.JTextField earning;
    private javax.swing.JLabel earningTitle;
    private javax.swing.JPanel fAliment;
    private javax.swing.JLabel fAlimentLimit1;
    private javax.swing.JPanel fAlimentStorage;
    private javax.swing.JLabel fAlimentTitle;
    private javax.swing.JLabel fAlimentTitle1;
    private javax.swing.JTextField fAlimentValueStorage;
    private javax.swing.JLabel hpLabel;
    private javax.swing.JButton increaseAssembler;
    private javax.swing.JButton increaseCpu;
    private javax.swing.JButton increaseFAliment;
    private javax.swing.JButton increasePlacaB;
    private javax.swing.JButton increaseRAM;
    private javax.swing.JButton increaseTGrafica;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel loteActual;
    private javax.swing.JLabel maxCap;
    private javax.swing.JLabel maxConfTrabLabel;
    private javax.swing.JLabel memoriaRAMTitle;
    private javax.swing.JLabel memoriaRAMTitle1;
    private javax.swing.JPanel placaB;
    private javax.swing.JLabel placaBLimit1;
    private javax.swing.JPanel placaBStorage;
    private javax.swing.JLabel placaBTitle1;
    private javax.swing.JTextField placaBValueStorage;
    private javax.swing.JTextField placaBValues;
    private javax.swing.JLabel placaBaseTitle;
    private javax.swing.JTextField plotTwistChapters;
    private javax.swing.JTextField profit;
    private javax.swing.JLabel profitTitle;
    private javax.swing.JTextField projectManagerStatus;
    private javax.swing.JPanel slogan;
    private javax.swing.JTextField standardChapters;
    private javax.swing.JLabel statusDirectTitle;
    private javax.swing.JLabel statusPMTitle;
    private javax.swing.JPanel storagePanel;
    private javax.swing.JLabel storageTitle;
    private javax.swing.JLabel storageTitle2;
    private javax.swing.JTextField strikeCounter;
    private javax.swing.JPanel tGrafica;
    private javax.swing.JTextField tGraficaComp;
    private javax.swing.JTextField tGraficaCompLast;
    private javax.swing.JTextField tGraficaCompTotal;
    private javax.swing.JLabel tGraficaLimit1;
    private javax.swing.JPanel tGraficaStorage;
    private javax.swing.JLabel tGraficaTitle;
    private javax.swing.JLabel tGraficaTitle1;
    private javax.swing.JTextField tGraficaValueStorage;
    private javax.swing.JLabel titulo;
    private javax.swing.JTextField totalChapters;
    private javax.swing.JTextField totalComp;
    private javax.swing.JTextField totalDays;
    private javax.swing.JPanel ultimoLote;
    private javax.swing.JPanel workersConfigurations;
    // End of variables declaration//GEN-END:variables
}
