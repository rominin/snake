package ru.rominin.snakegame;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Layer extends JPanel {
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.GREEN);
        g.fillRect(Room.game.getWidth() * 10, 0, 10, (Room.game.getHeight() * 10) + 10);
        g.fillRect(0, Room.game.getHeight() * 10, (Room.game.getHeight() * 10) + 10, 10);
        g.fillRect(Room.game.getMouse().getX()*10, Room.game.getMouse().getY()*10, 10, 10);
        List<Section> sections = Room.game.getSnake().getSections();
        for (int i=0; i<sections.size(); i++){
            g.fillRect(sections.get(i).getX()*10, sections.get(i).getY()*10, 10, 10);
        }
    }
}
