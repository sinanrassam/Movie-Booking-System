package Main;

import Time.Time;
import Reservation.AdultReservation;
import Reservation.SeatReservation;
import Reservation.ElderlyReservation;
import Reservation.ChildReservation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author sinan
 */
public class MovieBookingGUI extends JPanel implements ActionListener, ListSelectionListener {
    
    private JLabel label;
    private JList list;
    private DefaultListModel model;
    private JScrollPane pane;
    private JPanel panel;
    private List<SeatReservation> currentReservation;
    private JButton[][] seatingButtons;
    private JRadioButton adultRadio, elderlyRadio, childRadio;
    private JCheckBox complementaryCheckBox;
    private JButton exitButton, newButton, bookButton;
    private static List<MovieSession> movieSessions;
    
    private static final String TITLE = "Movies N Chill";
    public final int PANEL_HEIGHT = 400;
    public final int PANEL_WIDTH = 500;
    private final int NUM_ROWS = MovieSession.NUM_ROWS;
    private final int NUM_COLS = MovieSession.NUM_COLS;
    private final Color DEFAULT_CLR = Color.GRAY;
    private final Color ADULT_CLR = Color.BLUE;
    private final Color ELDERLY_CLR = Color.WHITE;
    private final Color CHILD_CLR = Color.YELLOW;
    
    public MovieBookingGUI(List<MovieSession> movieSessions) {
        //invoke super class Jpanel constructor
        super(new BorderLayout());
        
        // Setup Title label
        // create new JLabel with title
        label = new JLabel(TITLE);
        // Setup font for JLabel
        label.setFont(new Font("Serif", Font.BOLD, 50));
        // Set position of label hoizantally
        label.setHorizontalAlignment(JLabel.CENTER);
        // add label onto border
        add(label, BorderLayout.NORTH);
        
        // Setup JList
        // Model as a default List model
        model = new DefaultListModel();
        // Setup List as a defualt list model
        list = new JList(model);
        // Setup a JScrollPane for the list
        pane = new JScrollPane(list);
        // Insert all the movieSessions into the JList
        for (MovieSession movieSession : movieSessions) {
            // Add one movieSession at a time
            model.addElement(movieSession);
        }
        // Set the font
        list.setFont(new Font("Arial", Font.BOLD, 12));
        // Set inital selection value to 0, 0
        list.setSelectionInterval(0, 0);
        // Only allow one selection at a time
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Setup JList listener
        list.addListSelectionListener(this);
        // add the JList into the panel
        add(pane, BorderLayout.EAST);
        
        // Setup buttons
        // Setup currentReservation
        currentReservation = new ArrayList();
        // Setup buttons
        panel = new JPanel(new GridLayout(NUM_ROWS, NUM_COLS));
        //set JPanel size
        panel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        // Setup button array
        seatingButtons = new JButton[NUM_ROWS][NUM_COLS];
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                char rowPos = MovieSession.convertIndexToRow(i);
                // Setup button text
                seatingButtons[i][j] = new JButton(rowPos + "," + j);
                // add current button to panel
                panel.add(seatingButtons[i][j]);
                // add Action Listener to current button
                seatingButtons[i][j].addActionListener(this);
            }
        }
        setSeatState();
        // Add the button panel into the panel
        add(panel, BorderLayout.CENTER);
        
        adultRadio = new JRadioButton("Adult");
        adultRadio.setSelected(true);
        elderlyRadio = new JRadioButton("Elderly");
        childRadio = new JRadioButton("Child");
        
        complementaryCheckBox = new JCheckBox("Complementary");
        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        newButton = new JButton("New");
        newButton.addActionListener(this);
        bookButton = new JButton("Book");
        bookButton.addActionListener(this);
        
        ButtonGroup group = new ButtonGroup();
        group.add(adultRadio);
        group.add(elderlyRadio);
        group.add(childRadio);
        
        JPanel optionsPanel = new JPanel();
        optionsPanel.add(adultRadio);
        optionsPanel.add(elderlyRadio);
        optionsPanel.add(childRadio);
        optionsPanel.add(complementaryCheckBox);
        optionsPanel.add(exitButton);
        optionsPanel.add(newButton);
        optionsPanel.add(bookButton);
        add(optionsPanel, BorderLayout.SOUTH);
    }
    
    private void setSeatState() {
        for (int rowPos = 0; rowPos < NUM_ROWS; rowPos++) {
            for (int col = 0; col < NUM_COLS; col++) {
                char row = MovieSession.convertIndexToRow(rowPos);
                if (movieSessions.get(list.getSelectedIndex()).getSeat(row, col) instanceof ElderlyReservation) {
                    seatingButtons[rowPos][col].setBackground(ELDERLY_CLR);
                } else if (movieSessions.get(list.getSelectedIndex()).getSeat(row, col) instanceof AdultReservation) {
                    seatingButtons[rowPos][col].setBackground(ADULT_CLR);
                } else if (movieSessions.get(list.getSelectedIndex()).getSeat(row, col) instanceof ChildReservation) {
                    seatingButtons[rowPos][col].setBackground(CHILD_CLR);
                } else {
                    seatingButtons[rowPos][col].setBackground(DEFAULT_CLR);
                }
                seatingButtons[rowPos][col].setEnabled(movieSessions.get(list.getSelectedIndex()).isSeatAvailable(row, col));
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == exitButton) {
            String ObjButtons[] = {"Yes", "No"};
            int PromptResult = JOptionPane.showOptionDialog(null, "ARE YOU SURE YOU WANT TO EXIT?", "Movie Booking System",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
            if (PromptResult == 0) {
                System.exit(0);
            }
        } else if (source == bookButton) {
            if (!(currentReservation.size() > 0)) {
                JOptionPane.showMessageDialog(null, "PLEASE CHOOSE A TICKET TYPE AND ATLEAST ONE SEAT!", "Booking Failed", JOptionPane.ERROR_MESSAGE);
            } else {
                movieSessions.get(list.getSelectedIndex()).applyBookings(currentReservation);
                setSeatState();
                currentReservation.clear();
                complementaryCheckBox.setSelected(false);
            }
        } else if (source == newButton) {
            setSeatState();
            currentReservation.clear();
            complementaryCheckBox.setSelected(false);
        } else if ((adultRadio.isSelected()) || (elderlyRadio.isSelected()) || (childRadio.isSelected())) {
            char row = (e.getActionCommand().charAt(0));
            int rowPos = MovieSession.convertRowsIndex(row);
            int colPos = Integer.parseInt(e.getActionCommand().substring(2, e.getActionCommand().length()));
            SeatReservation booking = null;
            if (adultRadio.isSelected()) {
                seatingButtons[rowPos][colPos].setBackground(ADULT_CLR);
                booking = new AdultReservation(row, colPos);
            } else if (elderlyRadio.isSelected()) {
                seatingButtons[rowPos][colPos].setBackground(ELDERLY_CLR);
                booking = new ElderlyReservation(row, colPos);
            } else if (childRadio.isSelected()) {
                seatingButtons[rowPos][colPos].setBackground(CHILD_CLR);
                booking = new ChildReservation(row, colPos);
            }
            seatingButtons[rowPos][colPos].setEnabled(false);
            booking.setComplementary(complementaryCheckBox.isSelected());
            currentReservation.add(booking);
        }
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        setSeatState();
        currentReservation.clear();
    }
    
    public static void main(String argsp[]) {
        // Setup an ArrayList of movieSessions
        movieSessions = new ArrayList();
        // Setup new movies
        movieSessions.add(new MovieSession("Luis and the Aliens", 'G', new Time(10, 30, 00)));
        movieSessions.add(new MovieSession("Slenderman", 'M', new Time(10, 00, 00)));
        movieSessions.add(new MovieSession("The Happytime Murders", 'R', new Time(10, 00, 00)));
        movieSessions.add(new MovieSession("The Meg", 'M', new Time(12, 00, 00)));
        movieSessions.add(new MovieSession("The Equalizer 2", 'R', new Time(13, 00, 00)));
        movieSessions.add(new MovieSession("Incredibles 2", 'G', new Time(14, 30, 00)));
        // Order the movies by time and then by name
        Collections.sort(movieSessions);
        
        // Setup a new MovieBookingGUI object
        MovieBookingGUI bookingPanel = new MovieBookingGUI(movieSessions);
        // Setup a new JFrame
        JFrame frame = new JFrame(TITLE);
        // Setup default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // get frame's content and add it to bookingPanel
        frame.getContentPane().add(bookingPanel);  //add instance of MyGUI to the frame
        frame.pack(); //resize frame to fit our Jpanel
        //Position frame on center of screen
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int screenHeight = d.height;
        int screenWidth = d.width;
        frame.setLocation(new Point((screenWidth / 2) - (frame.getWidth() / 2), (screenHeight / 2) - (frame.getHeight() / 2)));
        //show the frame
        frame.setVisible(true);
    }
    
}
