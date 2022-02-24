package simulation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;

public class Simulation extends Canvas implements Runnable {

    private final ObjectHandler handler;

    private final int simWidth, simHeight, gridSize;
    private final Color backgroundColor = Color.green;
    private Image backgroundImage = null;

    private Thread mainThread;
    private boolean simulationRunning = false;

    public Simulation() {
        simWidth = SimulationParameters.SIM_WIDTH;
        simHeight = SimulationParameters.SIM_HEIGHT;
        gridSize = SimulationParameters.GRID_SIZE;
        handler = new ObjectHandler();
        initializeSimulation();
    }

    public ObjectHandler getHandler() {
        return this.handler;
    }

    private void initializeSimulation() {
        setPreferredSize(new Dimension(simWidth, simHeight));
        findBackgroundImage();
    }

    private void findBackgroundImage() {
        try {
            backgroundImage = ImageIO.read(new File("images/Grass.png"));
            backgroundImage = backgroundImage.getScaledInstance(gridSize * 2, gridSize * 2, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startSimulation() {
        mainThread = new Thread(this);
        simulationRunning = true;
        mainThread.start();
    }

    public void stopSimulation() {
        try {
            mainThread.join();
            simulationRunning = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (simulationRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (simulationRunning) {
                render();
            }
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS:" + frames);
                frames = 0;
            }
        }
        stopSimulation();
    }

    private void tick() {
        handler.tick();
    }


    private void render() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if (bufferStrategy == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bufferStrategy.getDrawGraphics();

        if (backgroundImage != null) {
            for(int x = 0; x < SimulationParameters.SIM_WIDTH / gridSize / 2; x++) {
                for(int y = 0; y < SimulationParameters.SIM_HEIGHT / gridSize; y++) {
                    g.drawImage(backgroundImage, x * gridSize * 2, y * gridSize * 2, null);
                }
            }
        } else {
            g.setColor(backgroundColor);
            g.fillRect(0, 0, simWidth, simHeight);
        }

        handler.render(g);

        g.dispose();
        bufferStrategy.show();
    }
}
