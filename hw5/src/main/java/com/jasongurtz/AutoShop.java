package com.jasongurtz;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 * A generic Auto Shop class
 * <p>
 *  A basic definition of an Auto Shop application
 * </p>
 * @author Jason Gurtz-Cayls
 */
public class AutoShop extends JFrame {
    private WelcomePanel welcome;
    private OthChargePanel other;
    private ServicesPanel services;
    private GrndTotalPanel total;
    private ButtonPanel buttons;

    private double servicesTotal;
    private double laborTotal;
    private double partsTotal;

    public AutoShop() {
        setTitle("Joe's Automotive Shop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        welcome = new WelcomePanel();
        other = new OthChargePanel();
        services = new ServicesPanel();
        total = new GrndTotalPanel();
        buttons = new ButtonPanel();

        add(welcome, BorderLayout.NORTH);
        add(other, BorderLayout.WEST);
        add(services, BorderLayout.CENTER);
        add(total, BorderLayout.EAST);
        add(buttons, BorderLayout.SOUTH);

        pack();
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AutoShop();
    }

    class ResetButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            other.resetState();
            services.resetState();
            total.resetState();
        }
    }

    class GoButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                laborTotal = other.getLaborCost();
                partsTotal = other.getPartsCost();
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Bad value entered, please check your labor and parts entries");
            }
            servicesTotal = services.getValue();

            total.setServices( String.format("%.2f", servicesTotal) );
            total.setLabor( String.format("%.2f", laborTotal) );
            total.setParts( String.format("%.2f", partsTotal) );
            total.setTotal( String.format("%.2f", servicesTotal + laborTotal + partsTotal) );
        }
    }

    class WelcomePanel extends JPanel {
        private JLabel welcomeText;

        public WelcomePanel() {
            welcomeText = new JLabel("Welcome to Joe's Automotive Shop!");
            add(welcomeText);
        }
    }

    class OthChargePanel extends JPanel {
        public final double LABOR_HOURS = 110.0;
        
        private JLabel laborHrs;
        private JLabel partsChrg;
        private JTextField laborHours;
        private JTextField partsCharge;

        public OthChargePanel() {
            Border grayline = BorderFactory.createLineBorder(Color.gray);
            setLayout(new GridLayout(8, 1));
            laborHrs = new JLabel("Labor Hours, $110/hr");
            add(laborHrs);
            laborHours = new JTextField("0.00", 10);
            add(laborHours);
            partsChrg = new JLabel("Parts Charge:,");
            add(partsChrg);
            partsCharge = new JTextField("0.00", 10);
            add(partsCharge);
            setBorder(BorderFactory.createTitledBorder(grayline, "Other Charges"));
        }

        public double getLaborCost() {
            if (laborHours.getText().equals("")) {
                return 0.0;
            }
            else {
                return Double.parseDouble(laborHours.getText()) * 110;
            }
        }

        public double getPartsCost() {
            if (partsCharge.getText().equals("")) {
                return 0.0;
            }
            else {
                return Double.parseDouble(partsCharge.getText());
            }
        }

        public void resetState() {
            laborHours.setText("0.00");
            partsCharge.setText("0.00");
        }
    }
    class ServicesPanel extends JPanel {
        private JCheckBox oilChg;
        private JCheckBox lubeJob;
        private JCheckBox radFlush;
        private JCheckBox transFlsh;
        private JCheckBox insp;
        private JCheckBox muffler;
        private JCheckBox tireRot;

        public ServicesPanel() {
            Border grayline = BorderFactory.createLineBorder(Color.gray);
            setLayout(new GridLayout(7, 1));
            oilChg = new JCheckBox("Oil Change, $26.00");
            add(oilChg);
            lubeJob = new JCheckBox("Lube Job, $18.00");
            add(lubeJob);
            radFlush = new JCheckBox("Radiator Flush, $30.00");
            add(radFlush);
            transFlsh = new JCheckBox("Transmission Flush, $80.00");
            add(transFlsh);
            insp = new JCheckBox("Inspection, $15.00");
            add(insp);
            muffler = new JCheckBox("Muffler Replacement, $100.00");
            add(muffler);
            tireRot = new JCheckBox("Tire Rotation, $20.00");
            add(tireRot);
            setBorder(BorderFactory.createTitledBorder(grayline, "Services"));
        }

        public void resetState() {
            oilChg.setSelected(false);
            lubeJob.setSelected(false);
            radFlush.setSelected(false);
            transFlsh.setSelected(false);
            insp.setSelected(false);
            muffler.setSelected(false);
            tireRot.setSelected(false);
        }

        public double getValue() {
            double total = 0.0;

            if (this.oilChg.isSelected()) { total += 26.00; }
            if (this.lubeJob.isSelected()) { total += 18.00; }
            if (this.radFlush.isSelected()) { total += 30.00; }
            if (this.transFlsh.isSelected()) { total += 80.00; }
            if (this.insp.isSelected()) { total += 15.00; }
            if (this.muffler.isSelected()) { total += 100.00; }
            if (this.tireRot.isSelected()) { total += 20.00; }

            return total;
        }
    }

    class GrndTotalPanel extends JPanel {
        private JLabel svcLbl;
        private JTextField svcTot;
        private JLabel laborLbl;
        private JTextField laborTot;
        private JLabel partsLbl;
        private JTextField partsTot;
        private JLabel grandLbl;
        private JTextField grandTot;

        public GrndTotalPanel() {
            Border grayline = BorderFactory.createLineBorder(Color.gray);
            setLayout(new GridLayout(8, 1));
            svcLbl = new JLabel("Total Services:");
            svcTot = new JTextField("0.00", 10);
            svcTot.setBackground(null);
            svcTot.setBorder(BorderFactory.createLineBorder(Color.black));
            svcTot.setEditable(false);
            svcTot.setHorizontalAlignment(JTextField.TRAILING);
            add(svcLbl);
            add(svcTot);
            laborLbl = new JLabel("Total Labor:");
            laborTot = new JTextField("0.00", 10);
            laborTot.setBackground(null);
            laborTot.setBorder(BorderFactory.createLineBorder(Color.black));
            laborTot.setEditable(false);
            laborTot.setHorizontalAlignment(JTextField.TRAILING);
            add(laborLbl);
            add(laborTot);
            partsLbl = new JLabel("Total Parts:");
            partsTot = new JTextField("0.00", 10);
            partsTot.setBackground(null);
            partsTot.setBorder(BorderFactory.createLineBorder(Color.black));
            partsTot.setEditable(false);
            partsTot.setHorizontalAlignment(JTextField.TRAILING);
            add(partsLbl);
            add(partsTot);
            grandLbl = new JLabel("Grand Total:");
            grandTot = new JTextField("0,00", 10);
            grandTot.setBackground(null);
            grandTot.setBorder(BorderFactory.createLineBorder(Color.black));
            grandTot.setEditable(false);
            grandTot.setHorizontalAlignment(JTextField.TRAILING);
            add(grandLbl);
            add(grandTot);
            setBorder(BorderFactory.createTitledBorder(grayline, "Grand Total"));
        }

        public void setServices(String s) {
            svcTot.setText(s);
        }

        public void setLabor(String l) {
            laborTot.setText(l);
        }

        public void setParts(String p) {
            partsTot.setText(p);
        }

        public void setTotal(String t) {
            grandTot.setText(t);
        }

        public void resetState() {
            svcTot.setText("0.00");
            laborTot.setText("0.00");
            partsTot.setText("0.00");
            grandTot.setText("0.00");
        }
    }

    class ButtonPanel extends JPanel {
        private JButton goBtn;
        private JButton rstBtn;

        public ButtonPanel() {
            goBtn = new JButton("Go!");
            rstBtn = new JButton("Reset");

            goBtn.addActionListener(new GoButtonListener());
            rstBtn.addActionListener(new ResetButtonListener());

            add(goBtn);
            add(rstBtn);
        }
    }
}
