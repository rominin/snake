package ru.rominin.snakegame;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<Section> sections;
    private boolean isAlive;
    private Direction direction;

    public Snake(int x, int y) {
        sections = new ArrayList<Section>();
        sections.add(new Section(x, y));
        isAlive = true;
    }

    public List<Section> getSections() {
        return sections;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public int getX() {
        return sections.get(0).getX();
    }

    public int getY() {
        return sections.get(0).getY();
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void move(int dx, int dy) {
        Section head = new Section(sections.get(0).getX() + dx, sections.get(0).getY() + dy);

        checkBorders(head);
        if (!isAlive) return;

        checkBody(head);
        if (!isAlive) return;

        if (head.getX() == Room.game.getMouse().getX() && head.getY() == Room.game.getMouse().getY())
            Room.game.eatMouse();
        else
            getSections().remove(getSections().get(getSections().size() - 1));

        getSections().add(0, head);
    }

    public void move() {
        if (isAlive == true) {
            switch (direction) {
                case UP:
                    move(0, -1);
                    break;
                case RIGHT:
                    move(1, 0);
                    break;
                case DOWN:
                    move(0, 1);
                    break;
                case LEFT:
                    move(-1, 0);
                    break;
            }
        }
    }

    public void checkBorders(Section head) {
        if (head.getY() >= Room.game.getHeight() || head.getY() < 0
        || head.getX() >= Room.game.getWidth() || head.getX() < 0)
            isAlive = false;
    }

    public void checkBody(Section head) {
        if (sections.contains(head))
            isAlive = false;
    }
}
