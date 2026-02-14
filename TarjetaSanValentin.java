import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TarjetaSanValentin extends JPanel {
    private boolean abierto = false;

    public TarjetaSanValentin() {
        setPreferredSize(new Dimension(500, 400));
        setBackground(new Color(255, 240, 245)); // Rosa pálido
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                abierto = !abierto;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int x = 100, y = 150, ancho = 300, alto = 180;

        // Dibujar la carta si está abierto
        if (abierto) {
            g2.setColor(Color.WHITE);
            g2.fillRect(x + 20, y - 60, ancho - 40, alto);
            g2.setColor(new Color(200, 0, 0));
            g2.setFont(new Font("Arial", Font.BOLD, 16));
            g2.drawString("¡Feliz San Valentín!", x + 65, y - 20);
            g2.drawString("Grupo de Impuestos", x + 65, y + 10);
        }

        // Cuerpo del sobre
        g2.setColor(new Color(230, 190, 150));
        g2.fillRect(x, y, ancho, alto);

        // Solapa
        Polygon solapa = new Polygon();
        if (!abierto) {
            solapa.addPoint(x, y);
            solapa.addPoint(x + ancho, y);
            solapa.addPoint(x + ancho / 2, y + alto / 2);
            g2.setColor(new Color(210, 170, 130));
        } else {
            solapa.addPoint(x, y);
            solapa.addPoint(x + ancho, y);
            solapa.addPoint(x + ancho / 2, y - 70);
            g2.setColor(new Color(190, 150, 110));
        }
        g2.fillPolygon(solapa);
        
        g2.setColor(Color.BLACK);
        g2.drawRect(x, y, ancho, alto);
        g2.drawPolygon(solapa);
        
        if(!abierto) {
            g2.drawString("Haz clic para abrir", x + 90, y + alto + 30);
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Mensaje Especial");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new TarjetaSanValentin());
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}