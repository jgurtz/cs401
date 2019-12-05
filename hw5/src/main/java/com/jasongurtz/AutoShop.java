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

    /**
    * Constructor for AutoShop, instantiaing BorderLayout, its five panels, and setting base attributes
    * @author Jason Gurtz-Cayla
    */
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

    /**
    * Entry point to application
    * @author Jason Gurtz-Cayla
    * @param args (unused) arguments given on command line
    */
    public static void main(String[] args) {
        new AutoShop();
    }

    /**
    * Event handler for the reset button; initiates reset of application state
    * @author Jason Gurtz-Cayla
    */
    class ResetButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            other.resetState();
            services.resetState();
            total.resetState();
        }
    }

    /**
    * Event handler for the Go! button; initiates calculation and results display of user entered data
    * @author Jason Gurtz-Cayla
    */
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

    /**
    * JPanel class displays welcome message
    * @author Jason Gurtz-Ca
    */
    class WelcomePanel extends JPanel {
        private JLabel welcomeText;

        public WelcomePanel() {
            welcomeText = new JLabel("Welcome to Joe's Automotive Shop!");
            add(welcomeText);
        }
    }

    /**
    * JPanel class contains fields for other charges: labor hours and parts
    * @author Jason Gurtz-Ca
    */
    class OthChargePanel extends JPanel {
        public final double LABOR_HOURS = 110.0;

        private JLabel laborHrs;
        private JLabel partsChrg;
        private JTextField laborHours;
        private JTextField partsCharge;

        /**
        * Constructor, setting a styled titled border and instantiating widgets
        * @author Jason Gurtz-Cayla
        */
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

        /**
        * Getter method for labor hours
        * @author Jason Gurtz-Cayla
        * @return User-entered labor hours
        */
        public double getLaborCost() {
            if (laborHours.getText().equals("")) {
                return 0.0;
            }
            else {
                return Double.parseDouble(laborHours.getText()) * 110;
            }
        }

        /**
        * Getter method for parts cost
        * @author Jason Gurtz-Cayla
        * @return User-entered cost of parts
        */
        public double getPartsCost() {
            if (partsCharge.getText().equals("")) {
                return 0.0;
            }
            else {
                return Double.parseDouble(partsCharge.getText());
            }
        }

        /**
        * Method which resets fields in this panel to default values
        * @author Jason Gurtz-Cayla
        */
        public void resetState() {
            laborHours.setText("0.00");
            partsCharge.setText("0.00");
        }
    }

    /**
    * JPanel class contains fields for individual service charges
    * @author Jason Gurtz-Ca
    */
    class ServicesPanel extends JPanel {
        private JCheckBox oilChg;
        private JCheckBox lubeJob;
        private JCheckBox radFlush;
        private JCheckBox transFlsh;
        private JCheckBox insp;
        private JCheckBox muffler;
        private JCheckBox tireRot;

        /**
        * Constructor, setting a styled titled border and instantiating widgets
        * @author Jason Gurtz-Cayla
        */
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

        /**
        * Method which resets fields in this panel to default values
        * @author Jason Gurtz-Cayla
        */
        public void resetState() {
            oilChg.setSelected(false);
            lubeJob.setSelected(false);
            radFlush.setSelected(false);
            transFlsh.setSelected(false);
            insp.setSelected(false);
            muffler.setSelected(false);
            tireRot.setSelected(false);
        }

        /**
        * Method which tallys the total for all services and returns tally to caller
        * @author Jason Gurtz-Cayla
        * @return Total cost of all services
        */
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

    /**
    * JPanel class contains fields for displaying calculated totals
    * @author Jason Gurtz-Ca
    */
    class GrndTotalPanel extends JPanel {
        private JLabel svcLbl;
        private JTextField svcTot;
        private JLabel laborLbl;
        private JTextField laborTot;
        private JLabel partsLbl;
        private JTextField partsTot;
        private JLabel grandLbl;
        private JTextField grandTot;

        /**
        * Constructor, setting a styled titled border and instantiating widgets
        * @author Jason Gurtz-Cayla
        */
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

        /**
        * Setter for total service charges
        * @author Jason Gurtz-Cayla
        * @param s Total service charges
        */
        public void setServices(String s) {
            svcTot.setText(s);
        }

        /**
        * Setter for total labor charges
        * @author Jason Gurtz-Cayla
        * @param l Total labor charges
        */
        public void setLabor(String l) {
            laborTot.setText(l);
        }

        /**
        * Setter for total parts charges
        * @author Jason Gurtz-Cayla
        * @param p Total parts charges
        */
        public void setParts(String p) {
            partsTot.setText(p);
        }

        /**
        * Setter for grand total of all charges
        * @author Jason Gurtz-Cayla
        * @param t Sum of all charges
        */
        public void setTotal(String t) {
            grandTot.setText(t);
        }

        /**
        * Method which resets fields in this panel to default values
        * @author Jason Gurtz-Cayla
        */
        public void resetState() {
            svcTot.setText("0.00");
            laborTot.setText("0.00");
            partsTot.setText("0.00");
            grandTot.setText("0.00");
        }
    }

    /**
    * JPanel class contains the Go! and Reset buttons
    * @author Jason Gurtz-Ca
    */
    class ButtonPanel extends JPanel {
        private JButton goBtn;
        private JButton rstBtn;

        /**
        * Constructor, instantiating buttons and attaching their event handlers
        * @author Jason Gurtz-Cayla
        */
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
