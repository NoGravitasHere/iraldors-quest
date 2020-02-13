package iraldorsquest.View;

import iraldorsquest.characters.*;
import iraldorsquest.items.Inventory;
import iraldorsquest.items.Item;
import iraldorsquest.items.Rarities;
import iraldorsquest.map.*;
import iraldorsquest.parser.*;
import iraldorsquest.parser.Action;
import iraldorsquest.main.Game;;

import java.util.ArrayList;
import java.util.Optional;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

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
    public static final Color TEXT_COLOR = Color.WHITE;
    private static final Color BACKGROUND_COLOR = Color.GRAY;

    private static Border standardBorder;

    private Map map;
    private Player player;
    private ArrayList<NPC> npcs;
    private ArrayList<Place> places;
    private Place playerPlace;
    private Game game;
    private Parser parser;

    private Dimension minSize;

    private InventoryPanel playerInventory;
    private StatsPanel playerStats;

    private JFrame mainFrame;
    private JPanel placePanel;
    private JPanel playerPanel;
    private JPanel roomPanel;
    private JPanel terminalPanel;
    private JTextField terminal;

    // ***********************
    // Constructor
    // ***********************
    /**
     * Creates a view
     *
     * @param game is the game to base the view on
     */
    public View(Game game, Parser parser) {
        Border border = new LineBorder(Color.DARK_GRAY);;
        Border margin = new EmptyBorder(10,10,10,10);
        standardBorder = new CompoundBorder(border, margin);

        minSize = new Dimension(750, 750);
        this.game = game;
        this.parser = parser;
        this.map = game.getMap();
        this.player = game.getPlayer();
        this.npcs = game.getNpcs();
        this.places = new ArrayList<>();
        this.playerPlace = player.getPlace();
        this.playerInventory = new InventoryPanel(player.getInventory());
        terminal = new JTextField();
        initKeyBindings();
        initUI();
        update();
    }

    // ***********************
    // Update methods
    // ***********************
    public void update() {
        for (Place place : places) {
            updatePlace(place);
        }
        playerInventory.update();

    }

    private void updatePlace(Place place) {
        Optional<String> attr = Optional.of("?????");
        String biom = "?????";
        String text = "";
        if (place.isCharted()) {
            attr = place.getAttribute();
            biom = place.getBiome();
            if (place.getAttribute().isPresent()) {
                text = "<html>" + attr.get() + "<br>" + biom + "</html>";
                Color color;
                if(place.isDangerous()) {
                    color = Color.RED;
                } else if (place.isHelpful()) {
                    color = Color.GREEN;
                } else if (place.isNeutral()) {
                    color = Color.GRAY;
                } else {
                    color = Color.LIGHT_GRAY;
                }
                place.setBackground(color);

            } else {
                text = "<html>" + biom + "</html>";
            }
        } else {
            text = "<html>" + attr.get() + "<br>" + biom + "</html>";
        }
        place.getLabel().setText(text);
    }

    // ***********************
    // Initalization methods
    // ***********************
    private void initKeyBindings() {
        addKeyBinding(this, KeyEvent.VK_ESCAPE, "escape", evt -> {
            System.exit(0);
        });

        addKeyBinding(this, KeyEvent.VK_ENTER, "enter", evt -> {
            Action action = parser.parse(terminal.getText());
            game.processInput(action);
            terminal.setText("");
        });
    }

    private void initUI() {
        mainFrame = new JFrame();
        playerPanel = new JPanel();
        mainFrame.setMinimumSize(minSize);

        ArrayList<JPanel> subpanels = new ArrayList<>();
        placePanel = new JPanel();
        playerPanel = new JPanel();
        roomPanel = new JPanel();
        terminalPanel = new JPanel();

        subpanels.add(placePanel);
        subpanels.add(playerPanel);
        subpanels.add(roomPanel);
        subpanels.add(terminalPanel);

        for (JPanel panel : subpanels) {
            panel.setBorder(standardBorder);
            panel.setBackground(BACKGROUND_COLOR);
        }

        initPlaces();
        initPlayer();
        initRoom();
        initTerminal();

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
        this.add(playerPanel, gbc);
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

                places.add(p);
                placePanel.add(p, gbc);
            }
        }
    }

    private void initPlayer() {
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanel.add(new JLabel("PLAYER: "));
        playerPanel.add(new JLabel(player.getName()));
        playerPanel.add(new JLabel(player.getStats()));
        playerPanel.add(playerInventory);
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
        terminalPanel.add(terminal);
    }

    // ***********************
    // Support Methods
    // ***********************
    /**
     * Determins the appropiate color for the rarity.
     * @param rarity the rarity
     * @return the color matching the rarity
     */
    public Color getRarityColor(Rarities rarity) {
        Color c = null;
        switch (rarity) {
            case NORMAL:
                c = Color.WHITE;
                break;
            case UNCOMON:
                c = Color.GREEN;
                break;
            case RARE:
                c = Color.CYAN;
                break;
            case EPIC:
                c = Color.ORANGE;
                break;
            case LEGENDARY:
                c = Color.MAGENTA;
                break;
            default:
                c = TEXT_COLOR;
                break;
        }
        return c;
    }

    /**
     * Adds a key binding to the specifed component.
     *
     * @param comp the component to add to.
     * @param keyCode the key to take action to.
     * @param id the name of the binding.
     * @param lambda the action to trigger.
     */
    public void addKeyBinding(JComponent comp, int keyCode, String id, ActionListener lambda) {
        InputMap im = comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap ap = comp.getActionMap();

        im.put(KeyStroke.getKeyStroke(keyCode, 0, false), id);
        ap.put(id, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lambda.actionPerformed(e);
            }
        });
    }


    class InventoryPanel extends JPanel {

        private Inventory inventory;

        InventoryPanel(Inventory inv) {
            inventory = inv;
            setBackground(BACKGROUND_COLOR);
        }

        private void update() {
            for (Item item : inventory) {
                var color = getRarityColor(item.getRarity());
                var itemLabel = new JLabel(item.getAsHtml());
                itemLabel.setForeground(color);
                add(itemLabel);
            }
        }

    }

    class StatsPanel extends JPanel {

    }
}