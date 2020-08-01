package ru.rominin.snakegame;

import java.awt.event.KeyEvent;
import java.util.Random;

public class Room {
    private int width, height;
    private Snake snake;
    private Mouse mouse;
    public static Room game;

    public Room(int width, int height, Snake snake) {
        this.width = width;
        this.height = height;
        this.snake = snake;
        game = this;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Snake getSnake() {
        return snake;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public void run() {
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        while (snake.isAlive()) {
            if (keyboardObserver.hasKeyEvents()) {
                KeyEvent event = keyboardObserver.getEventFromTop();
                if(event.getKeyChar() == 'q' || event.getKeyChar() == 'Q')
                    return;
                else if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    snake.setDirection(Direction.LEFT);
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    snake.setDirection(Direction.RIGHT);
                else if (event.getKeyCode() == KeyEvent.VK_UP)
                    snake.setDirection(Direction.UP);
                else if (event.getKeyCode() == KeyEvent.VK_DOWN)
                    snake.setDirection(Direction.DOWN);
            }
            snake.move();
            print();
            sleep();
        }
        System.out.println("Game over!");
    }

    public void print() {
        if (KeyboardObserver.frame!=null) {
            KeyboardObserver.frame.setContentPane(new Layer());
            KeyboardObserver.frame.setVisible(true);
        }
    }

    public void createMouse() {
        do {
            mouse = new Mouse(new Random().nextInt(width), new Random().nextInt(height));
        } while (snake.getSections().contains(mouse));
    }

    public void eatMouse() {
        createMouse();
    }

    public void sleep() {
        try {
            if (snake.getSections().size() <= 11)
                Thread.sleep(520 - snake.getSections().size()*20);
            else if (snake.getSections().size() <= 15)
                Thread.sleep(325 - (25 * snake.getSections().size() - 10));
            else
                Thread.sleep(200);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Snake snake = new Snake(new Random().nextInt(40), new Random().nextInt(40));
        game = new Room(40, 40, snake);
        snake.setDirection(Direction.values()[new Random().nextInt(3)]);
        game.createMouse();
        game.run();
    }
}
