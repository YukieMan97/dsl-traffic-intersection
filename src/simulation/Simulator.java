package simulation;

import javax.swing.*;

public class Simulator {


    public Simulator(Simulation sim) {
        createMainFrame(sim);
    }

    private void createMainFrame(Simulation simulation) {
        JFrame mainFrame = new JFrame("Road Intersection Simulator");

        mainFrame.add(simulation);
        mainFrame.pack();

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);

        simulation.startSimulation();
    }
}
