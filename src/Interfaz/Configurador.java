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
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import proyecto1so.mainApp;

/**
 *
 * @author nicolagabrielecolarusso
 */
public class Configurador extends javax.swing.JFrame {
    private Point initialClick;
    private final mainApp app = mainApp.getInstance();
    private int maxEmployees;
    private int maxEmployees1;
    private int actualEmployees;
    private int actualEmployees1;
    private static Configurador config;
    private ExtraFunctions helper = new ExtraFunctions();
    private FileFunc filefunctions = new FileFunc();
    private File selectedFile = app.getSelectedFile();
    private int dayDuration;
    private int deadline;
    
    private void initializeValues() {
        if (this.app.getHp() != null && this.app.getApple() != null) {
            this.maxEmployees = this.app.getApple().getMaxWorkersQuantity();
            this.maxEmployees1 = this.app.getHp().getMaxWorkersQuantity();
            this.actualEmployees1 = this.app.getHp().getActualWorkersQuantity();
            this.actualEmployees = this.app.getApple().getActualWorkersQuantity();
            this.dayDuration = (int) app.getDayDuration() / 1000;
            this.deadline = app.getDeadline();
            this.dayDurationValue.setText(String.valueOf(dayDuration));
            this.deadlineValue.setText(String.valueOf(deadline));

            this.placaBValues
                    .setText(String.valueOf(countNonNullEmployees(this.app.getApple().getProdPlacaBase())));
            this.cpuValue
                    .setText(String.valueOf(countNonNullEmployees(this.app.getApple().getProdCPUs())));
            this.RAMValues.setText(
                    String.valueOf(countNonNullEmployees(this.app.getApple().getProdMemoriaRAM())));
            this.fAlimentValues
                    .setText(String.valueOf(countNonNullEmployees(this.app.getApple().getProdFuenteAlimentacion())));
            this.tGraficaValues.setText(
                    String.valueOf(countNonNullEmployees(this.app.getApple().getProdTarjetaGrafica())));
            this.assemblerValues
                    .setText(String.valueOf(countNonNullEmployees(this.app.getApple().getEnsamblador())));

            this.placaBValues1
                    .setText(String.valueOf(countNonNullEmployees(this.app.getHp().getProdPlacaBase())));
            this.cpuValue1
                    .setText(String.valueOf(countNonNullEmployees(this.app.getHp().getProdCPUs())));
            this.RAMValues1.setText(
                    String.valueOf(countNonNullEmployees(this.app.getHp().getProdMemoriaRAM())));
            this.fAlimentValues1
                    .setText(String.valueOf(countNonNullEmployees(this.app.getHp().getProdFuenteAlimentacion())));
            this.tGraficaValues1.setText(
                    String.valueOf(countNonNullEmployees(this.app.getHp().getProdTarjetaGrafica())));
            this.assemblerValues1
                    .setText(String.valueOf(countNonNullEmployees(this.app.getHp().getEnsamblador())));
            this.maxCap.setText(String.valueOf(this.maxEmployees) + "     trabajadores");
            this.maxCap1.setText(String.valueOf(this.maxEmployees1) + "     trabajadores");
        }
    }

    private void updateBtnParams() {
        if (this.dayDuration == 1) {
            this.decreaseDay.setEnabled(false);
            this.decreaseDay.setFocusable(false);
        } else {
            this.decreaseDay.setEnabled(true);
            this.decreaseDay.setFocusable(true);
        }

        if (this.deadline == 1) {
            this.decreaseDeadline.setEnabled(false);
            this.decreaseDeadline.setFocusable(false);
        } else {
            this.decreaseDeadline.setEnabled(true);
            this.decreaseDeadline.setFocusable(true);
        }
    }

    public static synchronized Configurador getInstance() {
        if (config == null) {
            config = new Configurador();
        }
        return config;
    }

    private int countNonNullEmployees(Worker[] employees) {
        int count = 0;
        for (Worker employee : employees) {
            if (employee != null) {
                count++;
            }
        }
        return count;
    }

    private JButton[] decreaseBtn = new JButton[6];
    private JButton[] increaseBtn = new JButton[6];

    private int[] values = {
            countNonNullEmployees(this.app.getApple().getProdPlacaBase()),
            countNonNullEmployees(this.app.getApple().getProdCPUs()),
            countNonNullEmployees(this.app.getApple().getProdMemoriaRAM()),
            countNonNullEmployees(this.app.getApple().getProdFuenteAlimentacion()),
            countNonNullEmployees(this.app.getApple().getProdTarjetaGrafica()),
            countNonNullEmployees(this.app.getApple().getEnsamblador())
    };

    private JButton[] decreaseBtn1 = new JButton[6];
    private JButton[] increaseBtn1 = new JButton[6];

    private int[] values1 = {
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

    private void updateBtnStatus1() {
        updateValues1();

        if (this.actualEmployees1 == this.maxEmployees1) {
            for (JButton btn : increaseBtn1) {
                btn.setEnabled(false);
                btn.setFocusable(false);
            }
        } else {
            for (JButton btn : increaseBtn1) {
                btn.setEnabled(true);
                btn.setFocusable(true);
            }
        }

        for (int i = 0; i < this.values1.length; i++) {
            if (this.values1[i] == 1) {
                this.decreaseBtn1[i].setEnabled(false);
                this.decreaseBtn1[i].setFocusable(false);
            } else {
                this.decreaseBtn1[i].setEnabled(true);
                this.decreaseBtn1[i].setFocusable(true);

            }
        }
    }

    
    private void updateValues() {
        values[0] = countNonNullEmployees(this.app.getApple().getProdPlacaBase());
        values[1] = countNonNullEmployees(this.app.getApple().getProdCPUs());
        values[2] = countNonNullEmployees(this.app.getApple().getProdMemoriaRAM());
        values[3] = countNonNullEmployees(this.app.getApple().getProdFuenteAlimentacion());
        values[4] = countNonNullEmployees(this.app.getApple().getProdTarjetaGrafica());
        values[5] = countNonNullEmployees(this.app.getApple().getEnsamblador());
    }

    private void updateValues1() {
        values[0] = countNonNullEmployees(this.app.getHp().getProdPlacaBase());
        values[1] = countNonNullEmployees(this.app.getHp().getProdCPUs());
        values[2] = countNonNullEmployees(this.app.getHp().getProdMemoriaRAM());
        values[3] = countNonNullEmployees(this.app.getHp().getProdFuenteAlimentacion());
        values[4] = countNonNullEmployees(this.app.getHp().getProdTarjetaGrafica());
        values[5] = countNonNullEmployees(this.app.getHp().getEnsamblador());
    }

    public Configurador() {
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

        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        initializeValues();
        updateBtnParams();

        this.decreaseBtn1[0] = decreasePlacaB1;
        this.decreaseBtn1[1] = decreaseCpu1;
        this.decreaseBtn1[2] = decreaseRAM1;
        this.decreaseBtn1[3] = decreaseFAliment1;
        this.decreaseBtn1[4] = decreaceTGrafica1;
        this.decreaseBtn1[5] = decreaceAssembler1;
        this.increaseBtn1[0] = increasePlacaB1;
        this.increaseBtn1[1] = increaseCpu1;
        this.increaseBtn1[2] = increaseRAM1;
        this.increaseBtn1[3] = increaseFAliment1;
        this.increaseBtn1[4] = increaseTGrafica1;
        this.increaseBtn1[5] = increaseAssembler1;
        updateBtnStatus1();

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
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        diasEntrega = new javax.swing.JPanel();
        diasEntregaTitle = new javax.swing.JLabel();
        increaseDeadline = new javax.swing.JButton();
        deadlineValue = new javax.swing.JTextField();
        decreaseDeadline = new javax.swing.JButton();
        duracionDias = new javax.swing.JPanel();
        duracionDiasTitle = new javax.swing.JLabel();
        increaseDay = new javax.swing.JButton();
        dayDurationValue = new javax.swing.JTextField();
        decreaseDay = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        titulo = new javax.swing.JLabel();
        workersConfigurations = new javax.swing.JPanel();
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
        fAlimentValues = new javax.swing.JTextField();
        increaseFAliment = new javax.swing.JButton();
        tGrafica = new javax.swing.JPanel();
        tGraficaTitle = new javax.swing.JLabel();
        increaseTGrafica = new javax.swing.JButton();
        tGraficaValues = new javax.swing.JTextField();
        decreaceTGrafica = new javax.swing.JButton();
        assembler = new javax.swing.JPanel();
        assemblerTitle = new javax.swing.JLabel();
        increaseAssembler = new javax.swing.JButton();
        assemblerValues = new javax.swing.JTextField();
        decreaceAssembler = new javax.swing.JButton();
        hpConfigTitle = new javax.swing.JLabel();
        maxConfigLabel = new javax.swing.JLabel();
        maxCap = new javax.swing.JLabel();
        workersConfigurations1 = new javax.swing.JPanel();
        placaB1 = new javax.swing.JPanel();
        placaBaseTitle1 = new javax.swing.JLabel();
        increasePlacaB1 = new javax.swing.JButton();
        placaBValues1 = new javax.swing.JTextField();
        decreasePlacaB1 = new javax.swing.JButton();
        cpu1 = new javax.swing.JPanel();
        cpuTitle1 = new javax.swing.JLabel();
        cpuValue1 = new javax.swing.JTextField();
        increaseCpu1 = new javax.swing.JButton();
        decreaseCpu1 = new javax.swing.JButton();
        RAM1 = new javax.swing.JPanel();
        memoriaRAMTitle1 = new javax.swing.JLabel();
        RAMValues1 = new javax.swing.JTextField();
        decreaseRAM1 = new javax.swing.JButton();
        increaseRAM1 = new javax.swing.JButton();
        fAliment1 = new javax.swing.JPanel();
        fAlimentTitle1 = new javax.swing.JLabel();
        decreaseFAliment1 = new javax.swing.JButton();
        fAlimentValues1 = new javax.swing.JTextField();
        increaseFAliment1 = new javax.swing.JButton();
        tGrafica1 = new javax.swing.JPanel();
        tGraficaTitle1 = new javax.swing.JLabel();
        increaseTGrafica1 = new javax.swing.JButton();
        tGraficaValues1 = new javax.swing.JTextField();
        decreaceTGrafica1 = new javax.swing.JButton();
        assembler1 = new javax.swing.JPanel();
        assemblerTitle1 = new javax.swing.JLabel();
        increaseAssembler1 = new javax.swing.JButton();
        assemblerValues1 = new javax.swing.JTextField();
        decreaceAssembler1 = new javax.swing.JButton();
        appleConfigTitle = new javax.swing.JLabel();
        maxConfigLabel1 = new javax.swing.JLabel();
        maxCap1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        exit = new javax.swing.JLabel();
        btn_guardar = new javax.swing.JPanel();
        icono5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btn_nuevo_almacen = new javax.swing.JPanel();
        icono4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btn_apple = new javax.swing.JPanel();
        icono3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btn_hp = new javax.swing.JPanel();
        icono7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btn_dashboard = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btn_Inicio = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1209, 808));
        setSize(new java.awt.Dimension(1209, 808));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 204, 0));
        jPanel3.setForeground(new java.awt.Color(255, 204, 0));

        diasEntrega.setBackground(new java.awt.Color(255, 204, 0));
        diasEntrega.setForeground(new java.awt.Color(60, 63, 65));

        diasEntregaTitle.setBackground(new java.awt.Color(255, 204, 0));
        diasEntregaTitle.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        diasEntregaTitle.setForeground(new java.awt.Color(51, 51, 51));
        diasEntregaTitle.setText("Días entre las entregas:");

        increaseDeadline.setBackground(new java.awt.Color(51, 51, 51));
        increaseDeadline.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        increaseDeadline.setForeground(new java.awt.Color(255, 255, 255));
        increaseDeadline.setText("+");
        increaseDeadline.setBorderPainted(false);
        increaseDeadline.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increaseDeadlineMouseClicked(evt);
            }
        });
        increaseDeadline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseDeadlineActionPerformed(evt);
            }
        });

        deadlineValue.setBackground(new java.awt.Color(255, 204, 0));
        deadlineValue.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        deadlineValue.setForeground(new java.awt.Color(51, 51, 51));
        deadlineValue.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        deadlineValue.setText("0");
        deadlineValue.setBorder(null);
        deadlineValue.setFocusable(false);
        deadlineValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deadlineValueActionPerformed(evt);
            }
        });

        decreaseDeadline.setBackground(new java.awt.Color(51, 51, 51));
        decreaseDeadline.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreaseDeadline.setForeground(new java.awt.Color(255, 255, 255));
        decreaseDeadline.setText("-");
        decreaseDeadline.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreaseDeadlineMouseClicked(evt);
            }
        });
        decreaseDeadline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaseDeadlineActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout diasEntregaLayout = new javax.swing.GroupLayout(diasEntrega);
        diasEntrega.setLayout(diasEntregaLayout);
        diasEntregaLayout.setHorizontalGroup(
            diasEntregaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(diasEntregaLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(diasEntregaTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(decreaseDeadline)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deadlineValue, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(increaseDeadline)
                .addGap(16, 16, 16))
        );
        diasEntregaLayout.setVerticalGroup(
            diasEntregaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(diasEntregaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(diasEntregaTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(increaseDeadline)
                .addComponent(deadlineValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(decreaseDeadline))
        );

        duracionDias.setBackground(new java.awt.Color(255, 204, 0));
        duracionDias.setForeground(new java.awt.Color(60, 63, 65));

        duracionDiasTitle.setBackground(new java.awt.Color(255, 204, 0));
        duracionDiasTitle.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        duracionDiasTitle.setForeground(new java.awt.Color(51, 51, 51));
        duracionDiasTitle.setText("Duración de los días (seg):");

        increaseDay.setBackground(new java.awt.Color(51, 51, 51));
        increaseDay.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        increaseDay.setForeground(new java.awt.Color(255, 255, 255));
        increaseDay.setText("+");
        increaseDay.setBorderPainted(false);
        increaseDay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increaseDayMouseClicked(evt);
            }
        });
        increaseDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseDayActionPerformed(evt);
            }
        });

        dayDurationValue.setBackground(new java.awt.Color(255, 204, 0));
        dayDurationValue.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        dayDurationValue.setForeground(new java.awt.Color(51, 51, 51));
        dayDurationValue.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        dayDurationValue.setText("0");
        dayDurationValue.setBorder(null);
        dayDurationValue.setFocusable(false);
        dayDurationValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dayDurationValueActionPerformed(evt);
            }
        });

        decreaseDay.setBackground(new java.awt.Color(51, 51, 51));
        decreaseDay.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreaseDay.setForeground(new java.awt.Color(255, 255, 255));
        decreaseDay.setText("-");
        decreaseDay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreaseDayMouseClicked(evt);
            }
        });
        decreaseDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaseDayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout duracionDiasLayout = new javax.swing.GroupLayout(duracionDias);
        duracionDias.setLayout(duracionDiasLayout);
        duracionDiasLayout.setHorizontalGroup(
            duracionDiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(duracionDiasLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(duracionDiasTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(decreaseDay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dayDurationValue, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(increaseDay)
                .addContainerGap())
        );
        duracionDiasLayout.setVerticalGroup(
            duracionDiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(duracionDiasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(duracionDiasTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(increaseDay)
                .addComponent(dayDurationValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(decreaseDay))
        );

        jButton1.setBackground(new java.awt.Color(34, 46, 60));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Guardar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(duracionDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(diasEntrega, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(diasEntrega, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(duracionDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 1040, 80));

        titulo.setFont(new java.awt.Font("Arial Black", 1, 60)); // NOI18N
        titulo.setForeground(new java.awt.Color(255, 255, 255));
        titulo.setText("Configurar Parámetros");
        jPanel1.add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, -1, -1));

        workersConfigurations.setBackground(new java.awt.Color(255, 255, 255));

        placaB.setBackground(new java.awt.Color(255, 255, 255));
        placaB.setForeground(new java.awt.Color(60, 63, 65));

        placaBaseTitle.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        placaBaseTitle.setForeground(new java.awt.Color(51, 51, 51));
        placaBaseTitle.setText("Prod. Placa Base:");

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

        cpuTitle.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        cpuTitle.setForeground(new java.awt.Color(51, 51, 51));
        cpuTitle.setText("Prod. Cpus:");

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

        memoriaRAMTitle.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        memoriaRAMTitle.setForeground(new java.awt.Color(51, 51, 51));
        memoriaRAMTitle.setText("Prod. Memoria RAM:");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        fAlimentTitle.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        fAlimentTitle.setForeground(new java.awt.Color(51, 51, 51));
        fAlimentTitle.setText("Prod. F. Alimenta...");

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

        fAlimentValues.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        fAlimentValues.setForeground(new java.awt.Color(51, 51, 51));
        fAlimentValues.setText("0");
        fAlimentValues.setBorder(null);
        fAlimentValues.setFocusable(false);
        fAlimentValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fAlimentValuesActionPerformed(evt);
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
                .addComponent(fAlimentValues, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(fAlimentValues, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(increaseFAliment)
                    .addComponent(fAlimentTitle))
                .addContainerGap())
        );

        tGrafica.setBackground(new java.awt.Color(255, 255, 255));
        tGrafica.setForeground(new java.awt.Color(255, 255, 255));

        tGraficaTitle.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        tGraficaTitle.setForeground(new java.awt.Color(51, 51, 51));
        tGraficaTitle.setText("Prod. T. Gráfica:");

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

        tGraficaValues.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        tGraficaValues.setForeground(new java.awt.Color(51, 51, 51));
        tGraficaValues.setText("0");
        tGraficaValues.setBorder(null);
        tGraficaValues.setFocusable(false);
        tGraficaValues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tGraficaValuesActionPerformed(evt);
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
                .addComponent(tGraficaValues, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(tGraficaValues, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(decreaceTGrafica)
                    .addComponent(tGraficaTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );

        assembler.setBackground(new java.awt.Color(255, 255, 255));
        assembler.setForeground(new java.awt.Color(255, 255, 255));

        assemblerTitle.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        assemblerTitle.setForeground(new java.awt.Color(51, 51, 51));
        assemblerTitle.setText("Ensambladores:");

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(assemblerTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

        hpConfigTitle.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        hpConfigTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hpConfigTitle.setText("Apple");

        maxConfigLabel.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        maxConfigLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        maxConfigLabel.setText("Máximo:");

        maxCap.setFont(new java.awt.Font("Montserrat", 1, 19)); // NOI18N
        maxCap.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout workersConfigurationsLayout = new javax.swing.GroupLayout(workersConfigurations);
        workersConfigurations.setLayout(workersConfigurationsLayout);
        workersConfigurationsLayout.setHorizontalGroup(
            workersConfigurationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(hpConfigTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, workersConfigurationsLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(workersConfigurationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(workersConfigurationsLayout.createSequentialGroup()
                        .addComponent(maxConfigLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(maxCap, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(workersConfigurationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(assembler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tGrafica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fAliment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(RAM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cpu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(placaB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(15, 15, 15))
        );
        workersConfigurationsLayout.setVerticalGroup(
            workersConfigurationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(workersConfigurationsLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(hpConfigTitle)
                .addGap(18, 18, 18)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(workersConfigurationsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(maxConfigLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maxCap, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(workersConfigurations, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, -1, -1));

        workersConfigurations1.setBackground(new java.awt.Color(0, 0, 153));

        placaB1.setBackground(new java.awt.Color(255, 255, 255));
        placaB1.setForeground(new java.awt.Color(60, 63, 65));

        placaBaseTitle1.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        placaBaseTitle1.setForeground(new java.awt.Color(51, 51, 51));
        placaBaseTitle1.setText("Prod. Placa Base:");

        increasePlacaB1.setBackground(new java.awt.Color(51, 51, 51));
        increasePlacaB1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        increasePlacaB1.setForeground(new java.awt.Color(255, 255, 255));
        increasePlacaB1.setText("+");
        increasePlacaB1.setBorderPainted(false);
        increasePlacaB1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increasePlacaB1MouseClicked(evt);
            }
        });
        increasePlacaB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increasePlacaB1ActionPerformed(evt);
            }
        });

        placaBValues1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        placaBValues1.setForeground(new java.awt.Color(51, 51, 51));
        placaBValues1.setText("0");
        placaBValues1.setBorder(null);
        placaBValues1.setFocusable(false);
        placaBValues1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                placaBValues1ActionPerformed(evt);
            }
        });

        decreasePlacaB1.setBackground(new java.awt.Color(51, 51, 51));
        decreasePlacaB1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreasePlacaB1.setForeground(new java.awt.Color(255, 255, 255));
        decreasePlacaB1.setText("-");
        decreasePlacaB1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreasePlacaB1MouseClicked(evt);
            }
        });
        decreasePlacaB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreasePlacaB1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout placaB1Layout = new javax.swing.GroupLayout(placaB1);
        placaB1.setLayout(placaB1Layout);
        placaB1Layout.setHorizontalGroup(
            placaB1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(placaB1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(placaBaseTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(27, 27, 27)
                .addComponent(decreasePlacaB1)
                .addGap(18, 18, 18)
                .addComponent(placaBValues1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(increasePlacaB1)
                .addGap(15, 15, 15))
        );
        placaB1Layout.setVerticalGroup(
            placaB1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(placaB1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(placaBaseTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(increasePlacaB1)
                .addComponent(placaBValues1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(decreasePlacaB1))
        );

        cpu1.setBackground(new java.awt.Color(255, 255, 255));
        cpu1.setForeground(new java.awt.Color(60, 63, 65));

        cpuTitle1.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        cpuTitle1.setForeground(new java.awt.Color(51, 51, 51));
        cpuTitle1.setText("Prod. Cpus:");

        cpuValue1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        cpuValue1.setForeground(new java.awt.Color(51, 51, 51));
        cpuValue1.setText("0");
        cpuValue1.setBorder(null);
        cpuValue1.setFocusable(false);
        cpuValue1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpuValue1ActionPerformed(evt);
            }
        });

        increaseCpu1.setBackground(new java.awt.Color(51, 51, 51));
        increaseCpu1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        increaseCpu1.setForeground(new java.awt.Color(255, 255, 255));
        increaseCpu1.setText("+");
        increaseCpu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increaseCpu1MouseClicked(evt);
            }
        });
        increaseCpu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseCpu1ActionPerformed(evt);
            }
        });

        decreaseCpu1.setBackground(new java.awt.Color(51, 51, 51));
        decreaseCpu1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreaseCpu1.setForeground(new java.awt.Color(255, 255, 255));
        decreaseCpu1.setText("-");
        decreaseCpu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreaseCpu1MouseClicked(evt);
            }
        });
        decreaseCpu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaseCpu1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cpu1Layout = new javax.swing.GroupLayout(cpu1);
        cpu1.setLayout(cpu1Layout);
        cpu1Layout.setHorizontalGroup(
            cpu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cpu1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(cpuTitle1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(decreaseCpu1)
                .addGap(18, 18, 18)
                .addComponent(cpuValue1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(increaseCpu1)
                .addGap(14, 14, 14))
        );
        cpu1Layout.setVerticalGroup(
            cpu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cpu1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cpu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cpu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(increaseCpu1)
                        .addComponent(cpuValue1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(decreaseCpu1))
                    .addComponent(cpuTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        RAM1.setBackground(new java.awt.Color(255, 255, 255));
        RAM1.setForeground(new java.awt.Color(255, 255, 255));

        memoriaRAMTitle1.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        memoriaRAMTitle1.setForeground(new java.awt.Color(51, 51, 51));
        memoriaRAMTitle1.setText("Prod. Memoria RAM:");

        RAMValues1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        RAMValues1.setForeground(new java.awt.Color(51, 51, 51));
        RAMValues1.setText("0");
        RAMValues1.setBorder(null);
        RAMValues1.setFocusable(false);
        RAMValues1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RAMValues1ActionPerformed(evt);
            }
        });

        decreaseRAM1.setBackground(new java.awt.Color(51, 51, 51));
        decreaseRAM1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreaseRAM1.setForeground(new java.awt.Color(255, 255, 255));
        decreaseRAM1.setText("-");
        decreaseRAM1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreaseRAM1MouseClicked(evt);
            }
        });
        decreaseRAM1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaseRAM1ActionPerformed(evt);
            }
        });

        increaseRAM1.setBackground(new java.awt.Color(51, 51, 51));
        increaseRAM1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        increaseRAM1.setForeground(new java.awt.Color(255, 255, 255));
        increaseRAM1.setText("+");
        increaseRAM1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increaseRAM1MouseClicked(evt);
            }
        });
        increaseRAM1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseRAM1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RAM1Layout = new javax.swing.GroupLayout(RAM1);
        RAM1.setLayout(RAM1Layout);
        RAM1Layout.setHorizontalGroup(
            RAM1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RAM1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(memoriaRAMTitle1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(decreaseRAM1)
                .addGap(18, 18, 18)
                .addComponent(RAMValues1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(increaseRAM1)
                .addGap(15, 15, 15))
        );
        RAM1Layout.setVerticalGroup(
            RAM1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RAM1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RAM1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(decreaseRAM1)
                    .addComponent(RAMValues1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(increaseRAM1)
                    .addComponent(memoriaRAMTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );

        fAliment1.setBackground(new java.awt.Color(255, 255, 255));
        fAliment1.setForeground(new java.awt.Color(255, 255, 255));

        fAlimentTitle1.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        fAlimentTitle1.setForeground(new java.awt.Color(51, 51, 51));
        fAlimentTitle1.setText("Prod. F. Alimenta...");

        decreaseFAliment1.setBackground(new java.awt.Color(51, 51, 51));
        decreaseFAliment1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreaseFAliment1.setForeground(new java.awt.Color(204, 204, 204));
        decreaseFAliment1.setText("-");
        decreaseFAliment1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreaseFAliment1MouseClicked(evt);
            }
        });
        decreaseFAliment1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaseFAliment1ActionPerformed(evt);
            }
        });

        fAlimentValues1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        fAlimentValues1.setForeground(new java.awt.Color(51, 51, 51));
        fAlimentValues1.setText("0");
        fAlimentValues1.setBorder(null);
        fAlimentValues1.setFocusable(false);
        fAlimentValues1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fAlimentValues1ActionPerformed(evt);
            }
        });

        increaseFAliment1.setBackground(new java.awt.Color(51, 51, 51));
        increaseFAliment1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        increaseFAliment1.setForeground(new java.awt.Color(255, 255, 255));
        increaseFAliment1.setText("+");
        increaseFAliment1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increaseFAliment1MouseClicked(evt);
            }
        });
        increaseFAliment1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseFAliment1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout fAliment1Layout = new javax.swing.GroupLayout(fAliment1);
        fAliment1.setLayout(fAliment1Layout);
        fAliment1Layout.setHorizontalGroup(
            fAliment1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fAliment1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(fAlimentTitle1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(decreaseFAliment1)
                .addGap(18, 18, 18)
                .addComponent(fAlimentValues1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(increaseFAliment1)
                .addGap(15, 15, 15))
        );
        fAliment1Layout.setVerticalGroup(
            fAliment1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fAliment1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fAliment1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(decreaseFAliment1)
                    .addComponent(fAlimentValues1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(increaseFAliment1)
                    .addComponent(fAlimentTitle1))
                .addContainerGap())
        );

        tGrafica1.setBackground(new java.awt.Color(255, 255, 255));
        tGrafica1.setForeground(new java.awt.Color(255, 255, 255));

        tGraficaTitle1.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        tGraficaTitle1.setForeground(new java.awt.Color(51, 51, 51));
        tGraficaTitle1.setText("Prod. T. Gráfica:");

        increaseTGrafica1.setBackground(new java.awt.Color(51, 51, 51));
        increaseTGrafica1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        increaseTGrafica1.setForeground(new java.awt.Color(255, 255, 255));
        increaseTGrafica1.setText("+");
        increaseTGrafica1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increaseTGrafica1MouseClicked(evt);
            }
        });
        increaseTGrafica1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseTGrafica1ActionPerformed(evt);
            }
        });

        tGraficaValues1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        tGraficaValues1.setForeground(new java.awt.Color(51, 51, 51));
        tGraficaValues1.setText("0");
        tGraficaValues1.setBorder(null);
        tGraficaValues1.setFocusable(false);
        tGraficaValues1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tGraficaValues1ActionPerformed(evt);
            }
        });

        decreaceTGrafica1.setBackground(new java.awt.Color(51, 51, 51));
        decreaceTGrafica1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreaceTGrafica1.setForeground(new java.awt.Color(255, 255, 255));
        decreaceTGrafica1.setText("-");
        decreaceTGrafica1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreaceTGrafica1MouseClicked(evt);
            }
        });
        decreaceTGrafica1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaceTGrafica1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tGrafica1Layout = new javax.swing.GroupLayout(tGrafica1);
        tGrafica1.setLayout(tGrafica1Layout);
        tGrafica1Layout.setHorizontalGroup(
            tGrafica1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tGrafica1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(tGraficaTitle1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(decreaceTGrafica1)
                .addGap(18, 18, 18)
                .addComponent(tGraficaValues1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(increaseTGrafica1)
                .addGap(16, 16, 16))
        );
        tGrafica1Layout.setVerticalGroup(
            tGrafica1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tGrafica1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tGrafica1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(increaseTGrafica1)
                    .addComponent(tGraficaValues1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(decreaceTGrafica1)
                    .addComponent(tGraficaTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );

        assembler1.setBackground(new java.awt.Color(255, 255, 255));
        assembler1.setForeground(new java.awt.Color(255, 255, 255));

        assemblerTitle1.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        assemblerTitle1.setForeground(new java.awt.Color(51, 51, 51));
        assemblerTitle1.setText("Ensambladores:");

        increaseAssembler1.setBackground(new java.awt.Color(51, 51, 51));
        increaseAssembler1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        increaseAssembler1.setForeground(new java.awt.Color(255, 255, 255));
        increaseAssembler1.setText("+");
        increaseAssembler1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increaseAssembler1MouseClicked(evt);
            }
        });
        increaseAssembler1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseAssembler1ActionPerformed(evt);
            }
        });

        assemblerValues1.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        assemblerValues1.setForeground(new java.awt.Color(51, 51, 51));
        assemblerValues1.setText("0");
        assemblerValues1.setBorder(null);
        assemblerValues1.setFocusable(false);
        assemblerValues1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assemblerValues1ActionPerformed(evt);
            }
        });

        decreaceAssembler1.setBackground(new java.awt.Color(51, 51, 51));
        decreaceAssembler1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        decreaceAssembler1.setForeground(new java.awt.Color(255, 255, 255));
        decreaceAssembler1.setText("-");
        decreaceAssembler1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreaceAssembler1MouseClicked(evt);
            }
        });
        decreaceAssembler1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaceAssembler1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout assembler1Layout = new javax.swing.GroupLayout(assembler1);
        assembler1.setLayout(assembler1Layout);
        assembler1Layout.setHorizontalGroup(
            assembler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, assembler1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(assemblerTitle1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(decreaceAssembler1)
                .addGap(18, 18, 18)
                .addComponent(assemblerValues1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(increaseAssembler1)
                .addGap(16, 16, 16))
        );
        assembler1Layout.setVerticalGroup(
            assembler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assembler1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(assembler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(increaseAssembler1)
                    .addComponent(assemblerValues1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(decreaceAssembler1)
                    .addComponent(assemblerTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );

        appleConfigTitle.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        appleConfigTitle.setForeground(new java.awt.Color(255, 255, 255));
        appleConfigTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        appleConfigTitle.setText("Hewlette Packard");

        maxConfigLabel1.setFont(new java.awt.Font("Arial Black", 1, 16)); // NOI18N
        maxConfigLabel1.setForeground(new java.awt.Color(255, 255, 255));
        maxConfigLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        maxConfigLabel1.setText("Máximo:");

        maxCap1.setFont(new java.awt.Font("Montserrat", 1, 19)); // NOI18N
        maxCap1.setForeground(new java.awt.Color(255, 255, 255));
        maxCap1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout workersConfigurations1Layout = new javax.swing.GroupLayout(workersConfigurations1);
        workersConfigurations1.setLayout(workersConfigurations1Layout);
        workersConfigurations1Layout.setHorizontalGroup(
            workersConfigurations1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(appleConfigTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, workersConfigurations1Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(workersConfigurations1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(workersConfigurations1Layout.createSequentialGroup()
                        .addComponent(maxConfigLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(maxCap1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(workersConfigurations1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(assembler1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tGrafica1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fAliment1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(RAM1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cpu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(placaB1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(15, 15, 15))
        );
        workersConfigurations1Layout.setVerticalGroup(
            workersConfigurations1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(workersConfigurations1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(appleConfigTitle)
                .addGap(18, 18, 18)
                .addComponent(placaB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cpu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RAM1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fAliment1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tGrafica1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(assembler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(workersConfigurations1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(maxConfigLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maxCap1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(workersConfigurations1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 220, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Captura de pantalla 2024-10-09 174126.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 450, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Captura de pantalla 2024-10-07 204326.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 200, 370, 260));

        exit.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        exit.setForeground(new java.awt.Color(255, 255, 255));
        exit.setText("X");
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exitMousePressed(evt);
            }
        });
        jPanel1.add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 20, -1, -1));

        btn_guardar.setBackground(new java.awt.Color(102, 102, 102));
        btn_guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_guardarMouseClicked(evt);
            }
        });

        icono5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        icono5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icono5MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial Black", 0, 23)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Guardar");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_guardarLayout = new javax.swing.GroupLayout(btn_guardar);
        btn_guardar.setLayout(btn_guardarLayout);
        btn_guardarLayout.setHorizontalGroup(
            btn_guardarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_guardarLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(icono5)
                .addContainerGap(43, Short.MAX_VALUE))
        );
        btn_guardarLayout.setVerticalGroup(
            btn_guardarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_guardarLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(btn_guardarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(icono5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jPanel1.add(btn_guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 170, 60));

        btn_nuevo_almacen.setBackground(new java.awt.Color(255, 204, 0));
        btn_nuevo_almacen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_nuevo_almacenMouseClicked(evt);
            }
        });

        icono4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        icono4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icono4MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial Black", 0, 23)); // NOI18N
        jLabel7.setText("Parámetros");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_nuevo_almacenLayout = new javax.swing.GroupLayout(btn_nuevo_almacen);
        btn_nuevo_almacen.setLayout(btn_nuevo_almacenLayout);
        btn_nuevo_almacenLayout.setHorizontalGroup(
            btn_nuevo_almacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_nuevo_almacenLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(icono4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btn_nuevo_almacenLayout.setVerticalGroup(
            btn_nuevo_almacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_nuevo_almacenLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(btn_nuevo_almacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(icono4, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addGroup(btn_nuevo_almacenLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(0, 7, Short.MAX_VALUE))
        );

        jPanel1.add(btn_nuevo_almacen, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 170, 60));

        btn_apple.setBackground(new java.awt.Color(102, 102, 102));
        btn_apple.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_appleMouseClicked(evt);
            }
        });

        icono3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        icono3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icono3MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial Black", 0, 23)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Apple");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_appleLayout = new javax.swing.GroupLayout(btn_apple);
        btn_apple.setLayout(btn_appleLayout);
        btn_appleLayout.setHorizontalGroup(
            btn_appleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_appleLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(icono3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btn_appleLayout.setVerticalGroup(
            btn_appleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_appleLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(icono3, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
            .addGroup(btn_appleLayout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(btn_apple, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 170, 60));

        btn_hp.setBackground(new java.awt.Color(102, 102, 102));
        btn_hp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_hpMouseClicked(evt);
            }
        });

        icono7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        icono7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                icono7MouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial Black", 0, 23)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Hp");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_hpLayout = new javax.swing.GroupLayout(btn_hp);
        btn_hp.setLayout(btn_hpLayout);
        btn_hpLayout.setHorizontalGroup(
            btn_hpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_hpLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(icono7)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        btn_hpLayout.setVerticalGroup(
            btn_hpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_hpLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(btn_hpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(icono7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jPanel1.add(btn_hp, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 170, 60));

        btn_dashboard.setBackground(new java.awt.Color(102, 102, 102));
        btn_dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_dashboardMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial Black", 0, 23)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Dashboard");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_dashboardLayout = new javax.swing.GroupLayout(btn_dashboard);
        btn_dashboard.setLayout(btn_dashboardLayout);
        btn_dashboardLayout.setHorizontalGroup(
            btn_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_dashboardLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btn_dashboardLayout.setVerticalGroup(
            btn_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_dashboardLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(btn_dashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jPanel1.add(btn_dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 170, 60));

        btn_Inicio.setBackground(new java.awt.Color(102, 102, 102));
        btn_Inicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_InicioMouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial Black", 0, 23)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Inicio");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_InicioLayout = new javax.swing.GroupLayout(btn_Inicio);
        btn_Inicio.setLayout(btn_InicioLayout);
        btn_InicioLayout.setHorizontalGroup(
            btn_InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_InicioLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addContainerGap(71, Short.MAX_VALUE))
        );
        btn_InicioLayout.setVerticalGroup(
            btn_InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_InicioLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(btn_InicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 14, Short.MAX_VALUE))
        );

        jPanel1.add(btn_Inicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 170, -1));

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("TechNexus 2024 ®");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 650, -1, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void increasePlacaBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_increasePlacaBMouseClicked
        if (this.canIncreaseQuantity(0)) {
            this.placaBValues.setText(increaseQuantity(this.placaBValues.getText(), increasePlacaB));
            helper.agregarTrabajador(0, 0);
        }
        updateBtnStatus();
    }//GEN-LAST:event_increasePlacaBMouseClicked

    private void increasePlacaBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increasePlacaBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_increasePlacaBActionPerformed

    private void placaBValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_placaBValuesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_placaBValuesActionPerformed

    private void decreasePlacaBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decreasePlacaBMouseClicked
        updateValues();
        if (canDecreaseQuantity(0)) {
            this.placaBValues.setText(decreaseQuantity(this.placaBValues.getText(), this.decreasePlacaB));
            helper.eliminarTrabajador(0, 0);
        }
        updateBtnStatus();
    }//GEN-LAST:event_decreasePlacaBMouseClicked

    private void decreasePlacaBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreasePlacaBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_decreasePlacaBActionPerformed

    private void cpuValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cpuValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cpuValueActionPerformed

    private void increaseCpuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_increaseCpuMouseClicked
         if (canIncreaseQuantity(1)) {
            this.cpuValue.setText(increaseQuantity(this.cpuValue.getText(), increaseCpu));
            helper.agregarTrabajador(0, 1);
        }
        updateBtnStatus();
    }//GEN-LAST:event_increaseCpuMouseClicked

    private void increaseCpuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseCpuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_increaseCpuActionPerformed

    private void decreaseCpuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decreaseCpuMouseClicked
        if (canDecreaseQuantity(1)) {
            this.cpuValue.setText(decreaseQuantity(this.cpuValue.getText(), decreaseCpu));
            helper.eliminarTrabajador(0, 1);
        }
        updateBtnStatus();
    }//GEN-LAST:event_decreaseCpuMouseClicked

    private void decreaseCpuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreaseCpuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_decreaseCpuActionPerformed

    private void RAMValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RAMValuesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RAMValuesActionPerformed

    private void decreaseRAMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decreaseRAMMouseClicked
        if (canDecreaseQuantity(2)) {
            this.RAMValues.setText(decreaseQuantity(this.RAMValues.getText(), decreaseRAM));
            helper.eliminarTrabajador(0, 2);
        }
        updateBtnStatus();
    }//GEN-LAST:event_decreaseRAMMouseClicked

    private void decreaseRAMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreaseRAMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_decreaseRAMActionPerformed

    private void increaseRAMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_increaseRAMMouseClicked
         if (canIncreaseQuantity(2)) {
            this.RAMValues.setText(increaseQuantity(this.RAMValues.getText(), increaseRAM));
            helper.agregarTrabajador(0, 2);
        }
        updateBtnStatus();
    }//GEN-LAST:event_increaseRAMMouseClicked

    private void increaseRAMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseRAMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_increaseRAMActionPerformed

    private void decreaseFAlimentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decreaseFAlimentMouseClicked
        if (canDecreaseQuantity(3)) {
            this.fAlimentValues.setText(decreaseQuantity(this.fAlimentValues.getText(), decreaseFAliment));
            helper.eliminarTrabajador(0, 3);
        }
        updateBtnStatus();
    }//GEN-LAST:event_decreaseFAlimentMouseClicked

    private void decreaseFAlimentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreaseFAlimentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_decreaseFAlimentActionPerformed

    private void fAlimentValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fAlimentValuesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fAlimentValuesActionPerformed

    private void increaseFAlimentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_increaseFAlimentMouseClicked
        if (canIncreaseQuantity(3)) {
            this.fAlimentValues.setText(increaseQuantity(this.fAlimentValues.getText(), increaseFAliment));
            helper.agregarTrabajador(0, 3);
        }
        updateBtnStatus();
    }//GEN-LAST:event_increaseFAlimentMouseClicked

    private void increaseFAlimentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseFAlimentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_increaseFAlimentActionPerformed

    private void increaseTGraficaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_increaseTGraficaMouseClicked
        if (canIncreaseQuantity(4)) {
            this.tGraficaValues.setText(increaseQuantity(this.tGraficaValues.getText(), increaseTGrafica));
            helper.agregarTrabajador(0, 4);
        }
        updateBtnStatus();
    }//GEN-LAST:event_increaseTGraficaMouseClicked

    private void increaseTGraficaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseTGraficaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_increaseTGraficaActionPerformed

    private void tGraficaValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tGraficaValuesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tGraficaValuesActionPerformed

    private void decreaceTGraficaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decreaceTGraficaMouseClicked
        if (canDecreaseQuantity(4)) {
            this.tGraficaValues.setText(decreaseQuantity(this.tGraficaValues.getText(), decreaceTGrafica));
            helper.eliminarTrabajador(0, 4);
        }
        updateBtnStatus();
    }//GEN-LAST:event_decreaceTGraficaMouseClicked

    private void decreaceTGraficaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreaceTGraficaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_decreaceTGraficaActionPerformed

    private void increaseAssemblerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_increaseAssemblerMouseClicked
        if (canIncreaseQuantity(5)) {
            this.assemblerValues.setText(increaseQuantity(this.assemblerValues.getText(), increaseAssembler));
            helper.agregarTrabajador(0, 5);
        }
        updateBtnStatus();
    }//GEN-LAST:event_increaseAssemblerMouseClicked

    private void increaseAssemblerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseAssemblerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_increaseAssemblerActionPerformed

    private void assemblerValuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assemblerValuesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_assemblerValuesActionPerformed

    private void decreaceAssemblerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decreaceAssemblerMouseClicked
        if (canDecreaseQuantity(5)) {
            this.assemblerValues.setText(decreaseQuantity(this.assemblerValues.getText(), decreaceAssembler));
            helper.eliminarTrabajador(0, 5);
        }
        updateBtnStatus();
    }//GEN-LAST:event_decreaceAssemblerMouseClicked

    private void decreaceAssemblerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreaceAssemblerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_decreaceAssemblerActionPerformed

    private void increasePlacaB1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_increasePlacaB1MouseClicked
        if (this.canIncreaseQuantity1(0)) {
            this.placaBValues1.setText(increaseQuantity1(this.placaBValues1.getText(), increasePlacaB1));
            helper.agregarTrabajador(1, 0);
        }
        updateBtnStatus1();
    }//GEN-LAST:event_increasePlacaB1MouseClicked

    private void increasePlacaB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increasePlacaB1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_increasePlacaB1ActionPerformed

    private void placaBValues1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_placaBValues1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_placaBValues1ActionPerformed

    private void decreasePlacaB1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decreasePlacaB1MouseClicked
        updateValues1();
        if (canDecreaseQuantity1(0)) {
            this.placaBValues1.setText(decreaseQuantity1(this.placaBValues1.getText(), this.decreasePlacaB1));
            helper.eliminarTrabajador(1, 0);
        }
        updateBtnStatus();
    }//GEN-LAST:event_decreasePlacaB1MouseClicked

    private void decreasePlacaB1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreasePlacaB1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_decreasePlacaB1ActionPerformed

    private void cpuValue1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cpuValue1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cpuValue1ActionPerformed

    private void increaseCpu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_increaseCpu1MouseClicked
        if (canIncreaseQuantity1(1)) {
            this.cpuValue1.setText(increaseQuantity1(this.cpuValue1.getText(), increaseCpu1));
            helper.agregarTrabajador(1, 1);
        }
        updateBtnStatus1();
    }//GEN-LAST:event_increaseCpu1MouseClicked

    private void increaseCpu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseCpu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_increaseCpu1ActionPerformed

    private void decreaseCpu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decreaseCpu1MouseClicked
        if (canDecreaseQuantity1(1)) {
            this.cpuValue1.setText(decreaseQuantity1(this.cpuValue1.getText(), decreaseCpu1));
            helper.eliminarTrabajador(1, 1);
        }
        updateBtnStatus1();
    }//GEN-LAST:event_decreaseCpu1MouseClicked

    private void decreaseCpu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreaseCpu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_decreaseCpu1ActionPerformed

    private void RAMValues1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RAMValues1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RAMValues1ActionPerformed

    private void decreaseRAM1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decreaseRAM1MouseClicked
        if (canDecreaseQuantity1(2)) {
            this.RAMValues1.setText(decreaseQuantity1(this.RAMValues1.getText(), decreaseRAM1));
            helper.eliminarTrabajador(1, 2);
        }
        updateBtnStatus1();
    }//GEN-LAST:event_decreaseRAM1MouseClicked

    private void decreaseRAM1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreaseRAM1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_decreaseRAM1ActionPerformed

    private void increaseRAM1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_increaseRAM1MouseClicked
        if (canIncreaseQuantity1(2)) {
            this.RAMValues1.setText(increaseQuantity1(this.RAMValues1.getText(), increaseRAM1));
            helper.agregarTrabajador(1, 2);
        }
        updateBtnStatus1();
    }//GEN-LAST:event_increaseRAM1MouseClicked

    private void increaseRAM1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseRAM1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_increaseRAM1ActionPerformed

    private void decreaseFAliment1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decreaseFAliment1MouseClicked
        if (canDecreaseQuantity1(3)) {
            this.fAlimentValues1.setText(decreaseQuantity1(this.fAlimentValues1.getText(), decreaseFAliment1));
            helper.eliminarTrabajador(1, 3);
        }
        updateBtnStatus1();
    }//GEN-LAST:event_decreaseFAliment1MouseClicked

    private void decreaseFAliment1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreaseFAliment1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_decreaseFAliment1ActionPerformed

    private void fAlimentValues1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fAlimentValues1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fAlimentValues1ActionPerformed

    private void increaseFAliment1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_increaseFAliment1MouseClicked
        if (canIncreaseQuantity1(3)) {
            this.fAlimentValues1.setText(increaseQuantity1(this.fAlimentValues1.getText(), increaseFAliment1));
            helper.agregarTrabajador(1, 3);
        }
        updateBtnStatus1();
    }//GEN-LAST:event_increaseFAliment1MouseClicked

    private void increaseFAliment1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseFAliment1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_increaseFAliment1ActionPerformed

    private void increaseTGrafica1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_increaseTGrafica1MouseClicked
        if (canIncreaseQuantity1(4)) {
            this.tGraficaValues1.setText(increaseQuantity1(this.tGraficaValues1.getText(), increaseTGrafica1));
            helper.agregarTrabajador(1, 4);
        }
        updateBtnStatus1();
    }//GEN-LAST:event_increaseTGrafica1MouseClicked

    private void increaseTGrafica1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseTGrafica1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_increaseTGrafica1ActionPerformed

    private void tGraficaValues1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tGraficaValues1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tGraficaValues1ActionPerformed

    private void decreaceTGrafica1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decreaceTGrafica1MouseClicked
        if (canDecreaseQuantity1(4)) {
            this.tGraficaValues1.setText(decreaseQuantity1(this.tGraficaValues1.getText(), decreaceTGrafica1));
            helper.eliminarTrabajador(1, 4);
        }
        updateBtnStatus1();
    }//GEN-LAST:event_decreaceTGrafica1MouseClicked

    private void decreaceTGrafica1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreaceTGrafica1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_decreaceTGrafica1ActionPerformed

    private void increaseAssembler1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_increaseAssembler1MouseClicked
        if (canIncreaseQuantity1(5)) {
            this.assemblerValues1.setText(increaseQuantity1(this.assemblerValues1.getText(), increaseAssembler1));
            helper.agregarTrabajador(1, 5);
        }
        updateBtnStatus1();
    }//GEN-LAST:event_increaseAssembler1MouseClicked

    private void increaseAssembler1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseAssembler1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_increaseAssembler1ActionPerformed

    private void assemblerValues1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assemblerValues1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_assemblerValues1ActionPerformed

    private void decreaceAssembler1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decreaceAssembler1MouseClicked
        if (canDecreaseQuantity1(5)) {
            this.assemblerValues1.setText(decreaseQuantity1(this.assemblerValues1.getText(), decreaceAssembler1));
            helper.eliminarTrabajador(1, 5);
        }
        updateBtnStatus1();
    }//GEN-LAST:event_decreaceAssembler1MouseClicked

    private void decreaceAssembler1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreaceAssembler1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_decreaceAssembler1ActionPerformed

    private void increaseDeadlineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_increaseDeadlineMouseClicked
        this.deadline += 1;
        app.setDeadline(deadline);
        this.deadlineValue.setText(String.valueOf(app.getDeadline()));
        this.updateBtnParams();
    }//GEN-LAST:event_increaseDeadlineMouseClicked

    private void increaseDeadlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseDeadlineActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_increaseDeadlineActionPerformed

    private void deadlineValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deadlineValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deadlineValueActionPerformed

    private void decreaseDeadlineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decreaseDeadlineMouseClicked
        if (this.canDecreaseDeadline()) {
            this.deadline -= 1;
            app.setDeadline(deadline);
            this.deadlineValue.setText(String.valueOf(app.getDeadline()));
        }
        updateBtnParams();
    }//GEN-LAST:event_decreaseDeadlineMouseClicked

    private void decreaseDeadlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreaseDeadlineActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_decreaseDeadlineActionPerformed

    private void increaseDayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_increaseDayMouseClicked
        this.dayDuration += 1;
        app.setDayDuration(dayDuration * 1000);
        this.dayDurationValue.setText(String.valueOf(app.getDayDuration() / 1000));
        this.updateBtnParams();
    }//GEN-LAST:event_increaseDayMouseClicked

    private void increaseDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseDayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_increaseDayActionPerformed

    private void dayDurationValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dayDurationValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dayDurationValueActionPerformed

    private void decreaseDayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decreaseDayMouseClicked
        if (this.canDecreaseDay()) {
            this.dayDuration -= 1;
            app.setDayDuration(dayDuration * 1000);
            this.dayDurationValue.setText(String.valueOf(app.getDayDuration() / 1000));
        }
        this.updateBtnParams();
    }//GEN-LAST:event_decreaseDayMouseClicked

    private void decreaseDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreaseDayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_decreaseDayActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        try {
            this.filefunctions.write(this.selectedFile);
            JOptionPane.showMessageDialog(this, "El archivo ha sido guardado exitosamente!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al escribir el archivo");
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void exitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMousePressed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exitMousePressed

    private void icono5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_icono5MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        try {
            this.filefunctions.write(this.selectedFile);
            JOptionPane.showMessageDialog(this, "El archivo ha sido guardado exitosamente!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al escribir el archivo");
        }
    }//GEN-LAST:event_jLabel8MouseClicked

    private void btn_guardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_guardarMouseClicked
        try {
            this.filefunctions.write(this.selectedFile);
            JOptionPane.showMessageDialog(this, "El archivo ha sido guardado exitosamente!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al escribir el archivo");
        }
    }//GEN-LAST:event_btn_guardarMouseClicked

    private void icono4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_icono4MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        Configurador configurador = new Configurador();
        configurador.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void btn_nuevo_almacenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_nuevo_almacenMouseClicked
        // TODO add your handling code here:
        Configurador configurador = new Configurador();
        configurador.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_nuevo_almacenMouseClicked

    private void icono3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_icono3MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        AppleSimulador apple = new AppleSimulador();
        apple.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void btn_appleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_appleMouseClicked
        // TODO add your handling code here:
        AppleSimulador apple = new AppleSimulador();
        apple.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_appleMouseClicked

    private void icono7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_icono7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_icono7MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
        HpSimulador hp = new HpSimulador();
        hp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void btn_hpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hpMouseClicked
        // TODO add your handling code here:
        HpSimulador hp = new HpSimulador();
        hp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_hpMouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        Metricas dashboard = Metricas.getInstance();
        dashboard.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void btn_dashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dashboardMouseClicked
        // TODO add your handling code here:
        Metricas dashboard = Metricas.getInstance();
        dashboard.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_dashboardMouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        AppleHp home = new AppleHp();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel9MouseClicked

    private void btn_InicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_InicioMouseClicked
        AppleHp home = new AppleHp();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_InicioMouseClicked

    private boolean canDecreaseDay() {
        return this.dayDuration > 1;
    }

    private boolean canDecreaseDeadline() {
        return this.deadline > 1;
    }

    private String increaseQuantity(String actualValue, JButton btn) {
        int intValue = 0;
        try {
            intValue = Integer.parseInt(actualValue);
            if (actualEmployees < maxEmployees) {
                intValue++;
                actualEmployees++;
            }
            return String.valueOf(intValue);
        } catch (NumberFormatException e) {
            System.err.println("Error al convertir el valor a int: " + e.getMessage());
            return actualValue; // Retorna el valor actual en caso de error
        }
    }

    private String decreaseQuantity(String actualValue, JButton btn) {
        int intValue = 0;
        try {
            intValue = Integer.parseInt(actualValue);
            if (intValue > 1) {
                intValue--;
                actualEmployees--;
                return String.valueOf(intValue);
            } else {
                return String.valueOf(intValue);
            }
        } catch (NumberFormatException e) {
            System.err.println("Error al convertir el valor a int: " + e.getMessage());
        }
        return null;
    }

    private boolean canDecreaseQuantity(int type) {
        updateValues();
        return values[type] > 1;
    }

    private boolean canIncreaseQuantity(int type) {
        updateValues();
        return actualEmployees < maxEmployees;
    }

    private String increaseQuantity1(String actualValue, JButton btn) {
        int intValue = 0;
        try {
            intValue = Integer.parseInt(actualValue);
            if (actualEmployees1 < maxEmployees1) {
                intValue++;
                actualEmployees1++;
            }
            return String.valueOf(intValue);
        } catch (NumberFormatException e) {
            System.err.println("Error al convertir el valor a int: " + e.getMessage());
            return actualValue; // Retorna el valor actual en caso de error
        }
    }

    private String decreaseQuantity1(String actualValue, JButton btn) {
        int intValue = 0;
        try {
            intValue = Integer.parseInt(actualValue);
            if (intValue > 1) {
                intValue--;
                actualEmployees1--;
                return String.valueOf(intValue);
            } else {
                return String.valueOf(intValue);
            }
        } catch (NumberFormatException e) {
            System.err.println("Error al convertir el valor a int: " + e.getMessage());
        }
        return null;
    }

    private boolean canDecreaseQuantity1(int type) {
        updateValues1();
        return values1[type] > 1;
    }

    private boolean canIncreaseQuantity1(int type) {
        updateValues1();
        return actualEmployees1 < maxEmployees1;
    }

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
            java.util.logging.Logger.getLogger(Configurador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Configurador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Configurador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Configurador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Configurador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel RAM;
    private javax.swing.JPanel RAM1;
    private javax.swing.JTextField RAMValues;
    private javax.swing.JTextField RAMValues1;
    private javax.swing.JLabel appleConfigTitle;
    private javax.swing.JPanel assembler;
    private javax.swing.JPanel assembler1;
    private javax.swing.JLabel assemblerTitle;
    private javax.swing.JLabel assemblerTitle1;
    private javax.swing.JTextField assemblerValues;
    private javax.swing.JTextField assemblerValues1;
    private javax.swing.JPanel btn_Inicio;
    private javax.swing.JPanel btn_apple;
    private javax.swing.JPanel btn_dashboard;
    private javax.swing.JPanel btn_guardar;
    private javax.swing.JPanel btn_hp;
    private javax.swing.JPanel btn_nuevo_almacen;
    private javax.swing.JPanel cpu;
    private javax.swing.JPanel cpu1;
    private javax.swing.JLabel cpuTitle;
    private javax.swing.JLabel cpuTitle1;
    private javax.swing.JTextField cpuValue;
    private javax.swing.JTextField cpuValue1;
    private javax.swing.JTextField dayDurationValue;
    private javax.swing.JTextField deadlineValue;
    private javax.swing.JButton decreaceAssembler;
    private javax.swing.JButton decreaceAssembler1;
    private javax.swing.JButton decreaceTGrafica;
    private javax.swing.JButton decreaceTGrafica1;
    private javax.swing.JButton decreaseCpu;
    private javax.swing.JButton decreaseCpu1;
    private javax.swing.JButton decreaseDay;
    private javax.swing.JButton decreaseDeadline;
    private javax.swing.JButton decreaseFAliment;
    private javax.swing.JButton decreaseFAliment1;
    private javax.swing.JButton decreasePlacaB;
    private javax.swing.JButton decreasePlacaB1;
    private javax.swing.JButton decreaseRAM;
    private javax.swing.JButton decreaseRAM1;
    private javax.swing.JPanel diasEntrega;
    private javax.swing.JLabel diasEntregaTitle;
    private javax.swing.JPanel duracionDias;
    private javax.swing.JLabel duracionDiasTitle;
    private javax.swing.JLabel exit;
    private javax.swing.JPanel fAliment;
    private javax.swing.JPanel fAliment1;
    private javax.swing.JLabel fAlimentTitle;
    private javax.swing.JLabel fAlimentTitle1;
    private javax.swing.JTextField fAlimentValues;
    private javax.swing.JTextField fAlimentValues1;
    private javax.swing.JLabel hpConfigTitle;
    private javax.swing.JLabel icono3;
    private javax.swing.JLabel icono4;
    private javax.swing.JLabel icono5;
    private javax.swing.JLabel icono7;
    private javax.swing.JButton increaseAssembler;
    private javax.swing.JButton increaseAssembler1;
    private javax.swing.JButton increaseCpu;
    private javax.swing.JButton increaseCpu1;
    private javax.swing.JButton increaseDay;
    private javax.swing.JButton increaseDeadline;
    private javax.swing.JButton increaseFAliment;
    private javax.swing.JButton increaseFAliment1;
    private javax.swing.JButton increasePlacaB;
    private javax.swing.JButton increasePlacaB1;
    private javax.swing.JButton increaseRAM;
    private javax.swing.JButton increaseRAM1;
    private javax.swing.JButton increaseTGrafica;
    private javax.swing.JButton increaseTGrafica1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel maxCap;
    private javax.swing.JLabel maxCap1;
    private javax.swing.JLabel maxConfigLabel;
    private javax.swing.JLabel maxConfigLabel1;
    private javax.swing.JLabel memoriaRAMTitle;
    private javax.swing.JLabel memoriaRAMTitle1;
    private javax.swing.JPanel placaB;
    private javax.swing.JPanel placaB1;
    private javax.swing.JTextField placaBValues;
    private javax.swing.JTextField placaBValues1;
    private javax.swing.JLabel placaBaseTitle;
    private javax.swing.JLabel placaBaseTitle1;
    private javax.swing.JPanel tGrafica;
    private javax.swing.JPanel tGrafica1;
    private javax.swing.JLabel tGraficaTitle;
    private javax.swing.JLabel tGraficaTitle1;
    private javax.swing.JTextField tGraficaValues;
    private javax.swing.JTextField tGraficaValues1;
    private javax.swing.JLabel titulo;
    private javax.swing.JPanel workersConfigurations;
    private javax.swing.JPanel workersConfigurations1;
    // End of variables declaration//GEN-END:variables
}
