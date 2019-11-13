package main;

import java.util.ArrayList;
import characters.*;
import map.*;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;

/**
 * This class creates a vieweble elemnt and prints it to the terminal
 *
 * @author psoderlu
 * @version 42
 */

public class View extends JPanel {

    // ***********************
    // Variables
    // ***********************
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BOLD = "\u001B[1m";
    public static final String ANSI_ITALIC = "\u001B[3m";
    public static final String ANSI_UNDERLINE = "\u001B[4m";
    public static final String ANSI_BLINK = "\u001B[5m";

    public static final String ANSI_CLEAR = "\033\143";

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BRIGHT_BLACK = "\u001B[90m";

    public static final String LIGHT_LINE = ANSI_BRIGHT_BLACK
            + "-------------------------------------------------------------------------" + ANSI_RESET;
    public static final String MEDIUM_LINE = "========================================================================="
            + ANSI_RESET;
    public static final String HEAVY_LINE = ANSI_BRIGHT_BLACK
            + "#########################################################################" + ANSI_RESET;

    private static final Border standardBorder = new LineBorder(Color.DARK_GRAY);
    private static final Color standardBackground = Color.GRAY;

    private Map map;
    private Player player;
    private ArrayList<NPC> npcs;
    private Place playerPlace;

    private JFrame mainFrame;
    private JPanel placePanel;
    private JPanel inventoryPanel;
    private JPanel roomPanel;
    private JPanel terminalPanel;

    // ***********************
    // Constructor
    // ***********************
    /**
     * Creates a view
     *
     * @param map    the map to view
     * @param player the player
     */
    public View(Map map, Player player, ArrayList<NPC> npcs) {
        this.map = map;
        this.player = player;
        this.npcs = npcs;
        this.playerPlace = player.getPlace();

        initUI();
    }

    // ***********************
    // Main Methods
    // ***********************
    private void initUI() {
        mainFrame = new JFrame();
        inventoryPanel = new JPanel();

        ArrayList<JPanel> subpanels = new ArrayList<>();
        placePanel = new JPanel();
        inventoryPanel = new JPanel();
        roomPanel = new JPanel();
        terminalPanel = new JPanel();

        subpanels.add(placePanel);
        subpanels.add(inventoryPanel);
        subpanels.add(roomPanel);
        subpanels.add(terminalPanel);

        for (JPanel panel : subpanels) {
            panel.setBorder(standardBorder);
            panel.setBackground(standardBackground);
        }

        initPlaces();
        initInventory();
        initRoom();
        initTerminal();

        JLabel l = new JLabel();
        l.setText(getPrintableMap());

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        this.add(placePanel, gbc);
        gbc.weightx = 0;
        gbc.weighty = 0;

        gbc.gridx = 1;
        gbc.gridheight = 2;
        this.add(inventoryPanel, gbc);
        gbc.gridheight = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(roomPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        this.add(terminalPanel, gbc);
        gbc.gridwidth = 0;

        mainFrame.add(this);
        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void update() {

    }

    private void initPlaces() {
        placePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                gbc.gridx = j;
                gbc.gridy = i;
                Place p = map.getLocation(j, i);
                Border b = new LineBorder(Color.GRAY);
                p.setBorder(b);
                p.setBackground(Color.LIGHT_GRAY);

                String attr = "?????";
                String biom = "?????";
                if(p.isCharted()) {
                    attr = p.getAttribute();
                    biom = p.getBiome();
                }

                String t = "";
                if(p.getAttribute().equals("    ")) {
                    t = "<html>" + biom + "</html>";
                } else {
                    t = "<html>" + attr + "<br>" + biom + "</html>";
                }
                JLabel label = new JLabel(t);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);
                p.add(label);
                placePanel.add(p, gbc);
            }
        }
        placePanel.setBackground(Color.GREEN);
    }

    private void initInventory() {
        inventoryPanel.setLayout(new BoxLayout(inventoryPanel, BoxLayout.Y_AXIS));
        inventoryPanel.add(new JLabel(player.getName()));
        inventoryPanel.add(new JLabel(player.getStats()));
    }

    private void initRoom() {
        roomPanel.setLayout(new BoxLayout(roomPanel, BoxLayout.Y_AXIS));
        roomPanel.add(new JLabel(playerPlace.getName()));
        roomPanel.add(new JLabel("ITEMS: " + playerPlace.itemsToString()));
        roomPanel.add(new JLabel("NPCs:" + playerPlace.npcsToString()));
    }

    private void initTerminal() {
        terminalPanel.setLayout(new BoxLayout(terminalPanel, BoxLayout.Y_AXIS));
        terminalPanel.add(new JLabel("LOG: "));
        terminalPanel.add(new JTextField());
    }

    public void print() {
        playerPlace = player.getPlace();
        System.out.print(ANSI_CLEAR);
        System.out.println(HEAVY_LINE);
        System.out.println("[Map of the World]");
        System.out.println(MEDIUM_LINE);
        System.out.println(getPrintableMap());
        System.out.println(MEDIUM_LINE);
        System.out.println();

        System.out.println(ANSI_BOLD + "[Player]: " + player.getName() + ANSI_RESET);
        System.out.println(MEDIUM_LINE);
        System.out.println("[Stats]: " + player.getStats());
        System.out.println(LIGHT_LINE);
        System.out.println("[Inventory]:\n" + player.getInventory());
        System.out.println(MEDIUM_LINE);
        System.out.println();

        System.out.println(ANSI_BOLD + "[Place]: " + ANSI_ITALIC + playerPlace.getName() + ANSI_RESET);
        System.out.println(MEDIUM_LINE);
        System.out.println("[NPCs]: " + playerPlace.npcsToString());
        System.out.println(LIGHT_LINE);
        System.out.println("[Items]: " + playerPlace.itemsToString());
        System.out.println(MEDIUM_LINE);
        System.out.println(HEAVY_LINE);
        System.out.println();
    }

    /**
     * Returns a printable map of the world with colors
     *
     * @return a map of the world
     */
    public String getPrintableMap() {
        String s = "";
        int height = map.getHeight();
        int width = map.getWidth();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Place l = map.getLocation(j, i);

                if (l == player.getPlace()) {
                    s += ANSI_BLINK;
                }
                if (l.isCharted()) {
                    if (l.hasNPC()) {
                        s += ANSI_UNDERLINE;
                    }
                    if (l.hasItem()) {
                        s += ANSI_ITALIC;
                    }

                    if (l.isDangerous()) {
                        s += ANSI_RED;
                    } else if (l.isHelpful()) {
                        s += ANSI_GREEN;
                    } else if (l.isNeutral()) {
                        s += ANSI_BRIGHT_BLACK;
                    }
                }
                s += l.getShortName() + ANSI_RESET + "\t";
            }
            s += "\n";
        }
        return s.substring(0, s.length() - 2);
    }

}