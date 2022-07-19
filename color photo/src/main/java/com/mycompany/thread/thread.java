/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.thread;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class thread {

    public static boolean[][] colored = new boolean[2000][2000];

    public static void init() {
        for (int i = 0; i < colored.length; i++) {
            for (int j = 0; j < colored[i].length; j++) {
                colored[i][j] = false;
            }
        }
    }

    public static boolean intensity(Color a, Color b, int threshould) {
        int red_a = a.getRed();
        int green_a = a.getGreen();
        int blue_a = a.getBlue();
        int intensity_a = (red_a + green_a + blue_a) / 3;

        int red_b = b.getRed();
        int green_b = b.getGreen();
        int blue_b = b.getBlue();
        int intensity_b = (red_b + green_b + blue_b) / 3;

        if (intensity_a >= intensity_b) {
            if (intensity_a - intensity_b <= threshould) {
                return true;
            }
        } else if (intensity_b - intensity_a <= threshould) {
            return true;
        }
        return false;

    }

    public static void colored(BufferedImage image, Color color, Point dot, int threshould) {

        Queue<Point> queue = new LinkedList<>();
        boolean[][] visit = new boolean[image.getWidth()][image.getHeight()];
        for (int x = 0; x < visit.length; x++) {
            for (int y = 0; y < visit[0].length; y++) {
                visit[x][y] = false;
            }
        }
        queue.add(dot);
        while (!queue.isEmpty()) {
            Point p = queue.remove();

            if (p.x >= 0 && p.y >= 0) {
                if (colored[p.x][p.y] == false) {
////////////////////////////////////////////////////////////////////////
                    if (p.x >= 0 && p.x < image.getWidth() && p.y >= 0 && p.y < image.getHeight()) {
                        visit[p.x][p.y] = true;
                        colored[p.x][p.y] = true;
                        image.setRGB(p.x, p.y, color.getRGB());
                    }
////////////////////////////////////////////////////////////////////////
                    if (p.x - 1 >= 0 && p.y - 1 >= 0 && p.x - 1 < image.getWidth() && p.y - 1 < image.getHeight()) {
                        Color a = new Color(image.getRGB(p.x, p.y), true);
                        Color b = new Color(image.getRGB(p.x - 1, p.y - 1), true);
                        if (intensity(a, b, threshould)) {

                            if (visit[p.x - 1][p.y - 1] == false) {
                                queue.add(new Point(p.x - 1, p.y - 1));
                                visit[p.x - 1][p.y - 1] = true;
                            }
                        }
                    }
                    if (p.x + 1 < image.getWidth() && p.y + 1 < image.getHeight()) {
                        Color a = new Color(image.getRGB(p.x, p.y), true);
                        Color b = new Color(image.getRGB(p.x + 1, p.y + 1), true);
                        if (intensity(a, b, threshould)) {
                            if (visit[p.x + 1][p.y + 1] == false) {
                                queue.add(new Point(p.x + 1, p.y + 1));
                                visit[p.x + 1][p.y + 1] = true;
                            }
                        }
                    }
                    if (p.x - 1 > 0 && p.y + 1 < image.getHeight() && p.x - 1 < image.getWidth()) {
                        Color a = new Color(image.getRGB(p.x, p.y), true);
                        Color b = new Color(image.getRGB(p.x - 1, p.y + 1), true);
                        if (intensity(a, b, threshould)) {
                            if (visit[p.x - 1][p.y + 1] == false) {
                                queue.add(new Point(p.x - 1, p.y + 1));
                                visit[p.x - 1][p.y + 1] = true;
                            }
                        }
                    }
                    if (p.x + 1 < image.getWidth() && p.y - 1 > 0 && p.y - 1 < image.getHeight()) {
                        Color a = new Color(image.getRGB(p.x, p.y), true);
                        Color b = new Color(image.getRGB(p.x + 1, p.y - 1), true);
                        if (intensity(a, b, threshould)) {
                            if (visit[p.x + 1][p.y - 1] == false) {
                                queue.add(new Point(p.x + 1, p.y - 1));
                                visit[p.x + 1][p.y - 1] = true;
                            }
                        }
                    }
                    if (p.y - 1 > 0 && p.y - 1 < image.getHeight() && p.x < image.getWidth()) {
                        Color a = new Color(image.getRGB(p.x, p.y), true);
                        Color b = new Color(image.getRGB(p.x, p.y - 1), true);
                        if (intensity(a, b, threshould)) {
                            if (visit[p.x][p.y - 1] == false) {
                                queue.add(new Point(p.x, p.y - 1));
                                visit[p.x][p.y - 1] = true;
                            }
                        }
                    }
                    if (p.y + 1 < image.getHeight() && p.x < image.getWidth()) {
                        Color a = new Color(image.getRGB(p.x, p.y), true);
                        Color b = new Color(image.getRGB(p.x, p.y + 1), true);
                        if (intensity(a, b, threshould)) {
                            if (visit[p.x][p.y + 1] == false) {
                                queue.add(new Point(p.x, p.y + 1));
                                visit[p.x][p.y + 1] = true;
                            }
                        }
                    }
                    if (p.x - 1 > 0 && p.x - 1 < image.getWidth() && p.y < image.getHeight()) {
                        Color a = new Color(image.getRGB(p.x, p.y), true);
                        Color b = new Color(image.getRGB(p.x - 1, p.y), true);
                        if (intensity(a, b, threshould)) {
                            if (visit[p.x - 1][p.y] == false) {
                                queue.add(new Point(p.x - 1, p.y));
                                visit[p.x - 1][p.y] = true;
                            }
                        }
                    }
                    if (p.x + 1 < image.getWidth() && p.y < image.getHeight()) {
                        Color a = new Color(image.getRGB(p.x, p.y), true);
                        Color b = new Color(image.getRGB(p.x + 1, p.y), true);
                        if (intensity(a, b, threshould)) {
                            if (visit[p.x + 1][p.y] == false) {
                                queue.add(new Point(p.x + 1, p.y));
                                visit[p.x + 1][p.y] = true;
                            }
                        }
                    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////
                }
            }
        }

    }

    public static void colored_image(BufferedImage image, ArrayList<Point> pos, ArrayList<Color> colors, int threshould) {
        init();
        for (int i = 0; i < pos.size(); i++) {
            colored(image, colors.get(i), pos.get(i), threshould);
        }
    }

    public static void main(String[] args) throws IOException {

        ArrayList<Point> pos = new ArrayList();
        ArrayList<Color> color = new ArrayList();
        ArrayList<String> path = new ArrayList();
        ArrayList<Color> one = new ArrayList();

        JFrame frame1 = new JFrame("Display an image from a JFileChooser");
        JButton btn = new JButton("Browse");
        btn.setBounds(150, 310, 100, 40);
        JLabel l = new JLabel();
        l.setBounds(10, 10, 365, 290);
        frame1.add(btn);
        frame1.add(l);
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser file = new JFileChooser();
                file.setCurrentDirectory(new File(System.getProperty("user.home")));
                //filtering files
                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "png");
                file.addChoosableFileFilter(filter);
                int res = file.showSaveDialog(null);
                //if the user clicks on save in Jfilechooser
                if (res == JFileChooser.APPROVE_OPTION) {
                    File selFile = file.getSelectedFile();
                    String name = selFile.getAbsolutePath();
                    path.add(name);
////////////////////////////////////////////////////////////////////////sssss////////////////////////////////////
                    try {

                        File file1 = new File(path.get(0));
                        BufferedImage i = ImageIO.read(file1);
                        BufferedImage img = new BufferedImage(i.getWidth(), i.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
                        img.getGraphics().drawImage(i, 0, 0, null);

                        JFrame frame = new JFrame();
                        JPanel panel = new JPanel();
                        frame.add(panel);
                        JLabel outputLabel = new JLabel(new ImageIcon(img));
                        panel.add(outputLabel);
                        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        frame.setSize(img.getWidth(), img.getHeight());
                        frame.setVisible(true);

                        panel.addMouseMotionListener(new MouseMotionAdapter() {

                            public void mouseDragged(MouseEvent e) {

                                Point p = new Point(e.getX(), e.getY());
                                if (!one.isEmpty()) {
                                    colored(img, one.get(0), p, 40);
                                } else {
                                    colored(img, Color.BLACK, p, 40);
                                }

                                pos.add(new Point(e.getX(), e.getY()));
                                img.setRGB(e.getX(), e.getY(), one.get(0).getRGB());
                                outputLabel.setIcon(new ImageIcon(img));
                            }

                        });
                        panel.addMouseListener(new MouseAdapter() {
                            public void mouseReleased(MouseEvent e) {

                                System.out.println("relesed");
                                Color onec = JColorChooser.showDialog(panel, "Choose", Color.ORANGE);
                                one.add(0, onec);
                                panel.setBackground(onec);
                                if (pos.isEmpty()) {
                                    color.add(onec);
                                }
                                for (int i = color.size(); i < pos.size(); i++) {
                                    color.add(onec);
                                }
                            }
                        });
                        frame.addWindowListener(new java.awt.event.WindowAdapter() {
                            @Override
                            public void windowClosing(java.awt.event.WindowEvent windowEvent) {

                                System.out.println("GUI has been closed");
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
                                // colored_image(img, pos, color, 77);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                File outputfile = new File("C:\\Users\\ASUS\\Desktop\\pro\\modify.png");
                                try {
                                    ImageIO.write(img, "png", outputfile);
                                } catch (IOException e) {
                                    System.out.println("catch error");
                                }
                                //THEN you can exit the program
                                System.exit(0);
                            }
                        });

                        System.out.println(path.get(0));
                    } catch (IOException e1) {
                        System.out.println("catch error");
                    }
///////////////////////////////////////////////////////////////////////fffff////////////////////////////////////////
                }
            }
        });

        frame1.setLayout(null);
        frame1.setLocationRelativeTo(null);
        frame1.setSize(400, 400);
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

/////////////////////////////////////
    }
}
