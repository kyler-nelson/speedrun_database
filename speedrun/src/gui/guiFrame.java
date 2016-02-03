/**
 *Speedrun Database
* Author: Kyler Nelson
* Class: TCSS 445
* Date: 2015-03-13
 */

package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import query.Database;
import query.Query;
import javax.swing.SwingConstants;

public class guiFrame extends JFrame
{
    /**
     * Default serial id
     */
    private static final long serialVersionUID = 1L;

    private Connection connection;
    
    private JPanel contentPane;
    private JTextField txtUsername;
    private JTextField txtPassword;
    private JTextField txtUrl;
    private JPanel panelInterface;
    private JPanel QueryPanel;
    private JPanel ConnectionPanel;

    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    guiFrame frame = new guiFrame();
                    frame.setVisible(true);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public guiFrame()
    {
        setTitle("Speedrun Database (nelson_kyler_db)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 577, 370);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(2, 1, 0, 0));
        
        panelInterface = new JPanel();
        contentPane.add(panelInterface);
        panelInterface.setLayout(new CardLayout(0, 0));
        
        ConnectionPanel = new JPanel();
        panelInterface.add(ConnectionPanel, "name_101214049428743");
        ConnectionPanel.setLayout(new GridLayout(0, 1, 0, 0));
        
        JPanel panel_1 = new JPanel();
        ConnectionPanel.add(panel_1);
        panel_1.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_2 = new JPanel();
        panel_1.add(panel_2, BorderLayout.CENTER);
        GridBagLayout gbl_panel_2 = new GridBagLayout();
        gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
        gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0};
        gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel_2.setLayout(gbl_panel_2);
        
        JLabel lblNewLabel_2 = new JLabel("URL:");
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.gridwidth = 3;
        gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_2.gridx = 1;
        gbc_lblNewLabel_2.gridy = 0;
        panel_2.add(lblNewLabel_2, gbc_lblNewLabel_2);
        
        txtUrl = new JTextField();
        txtUrl.setText(Database.DB_URL);
        GridBagConstraints gbc_txtUrl = new GridBagConstraints();
        gbc_txtUrl.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtUrl.anchor = GridBagConstraints.WEST;
        gbc_txtUrl.gridwidth = 2;
        gbc_txtUrl.insets = new Insets(0, 0, 5, 0);
        gbc_txtUrl.gridx = 4;
        gbc_txtUrl.gridy = 0;
        panel_2.add(txtUrl, gbc_txtUrl);
        txtUrl.setColumns(2);
        
        JLabel lblNewLabel = new JLabel("Username:");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.gridwidth = 3;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel.gridx = 1;
        gbc_lblNewLabel.gridy = 1;
        panel_2.add(lblNewLabel, gbc_lblNewLabel);
        
        txtUsername = new JTextField();
        txtUsername.setText(Database.USER);
        GridBagConstraints gbc_txtUsername = new GridBagConstraints();
        gbc_txtUsername.anchor = GridBagConstraints.WEST;
        gbc_txtUsername.insets = new Insets(0, 0, 5, 5);
        gbc_txtUsername.gridx = 4;
        gbc_txtUsername.gridy = 1;
        panel_2.add(txtUsername, gbc_txtUsername);
        txtUsername.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Password:");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.gridwidth = 3;
        gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.gridx = 1;
        gbc_lblNewLabel_1.gridy = 2;
        panel_2.add(lblNewLabel_1, gbc_lblNewLabel_1);
        
        txtPassword = new JTextField();
        GridBagConstraints gbc_txtPassword = new GridBagConstraints();
        gbc_txtPassword.insets = new Insets(0, 0, 5, 5);
        gbc_txtPassword.anchor = GridBagConstraints.WEST;
        gbc_txtPassword.gridx = 4;
        gbc_txtPassword.gridy = 2;
        panel_2.add(txtPassword, gbc_txtPassword);
        txtPassword.setColumns(10);
        
        JButton btnNewButton = new JButton("Connect");
        GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
        gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
        gbc_btnNewButton.gridx = 4;
        gbc_btnNewButton.gridy = 3;
        panel_2.add(btnNewButton, gbc_btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Exit");
        GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
        gbc_btnNewButton_1.anchor = GridBagConstraints.WEST;
        gbc_btnNewButton_1.gridx = 5;
        gbc_btnNewButton_1.gridy = 3;
        panel_2.add(btnNewButton_1, gbc_btnNewButton_1);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connectDatabase();
            }
        });
        
        QueryPanel = new JPanel();
        QueryPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panelInterface.add(QueryPanel, "name_101278446640371");
        GridBagLayout gbl_QueryPanel = new GridBagLayout();
        gbl_QueryPanel.columnWidths = new int[]{0, 0, 0};
        gbl_QueryPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
        gbl_QueryPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        gbl_QueryPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        QueryPanel.setLayout(gbl_QueryPanel);
        
        JButton btnNewButton_2 = new JButton("List database tables");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try
                {
                    new Query(connection).listDatabaseTables();
                } catch (SQLException e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
        gbc_btnNewButton_2.anchor = GridBagConstraints.WEST;
        gbc_btnNewButton_2.insets = new Insets(5, 5, 5, 5);
        gbc_btnNewButton_2.gridx = 0;
        gbc_btnNewButton_2.gridy = 0;
        QueryPanel.add(btnNewButton_2, gbc_btnNewButton_2);
        
        JButton btnNewButton_3 = new JButton("List releases by Nintendo");
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try
                {
                    new Query(connection).listNintendoReleases();
                } catch (SQLException e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        
        JButton btnNewButton_6 = new JButton("List average time for any%");
        btnNewButton_6.setHorizontalAlignment(SwingConstants.LEFT);
        btnNewButton_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try
                {
                    new Query(connection).listAverageRunDurationForCategory("any%");
                } catch (SQLException e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
        gbc_btnNewButton_6.anchor = GridBagConstraints.WEST;
        gbc_btnNewButton_6.insets = new Insets(0, 0, 5, 0);
        gbc_btnNewButton_6.gridx = 1;
        gbc_btnNewButton_6.gridy = 0;
        QueryPanel.add(btnNewButton_6, gbc_btnNewButton_6);
        GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
        gbc_btnNewButton_3.anchor = GridBagConstraints.WEST;
        gbc_btnNewButton_3.insets = new Insets(5, 5, 5, 5);
        gbc_btnNewButton_3.gridx = 0;
        gbc_btnNewButton_3.gridy = 1;
        QueryPanel.add(btnNewButton_3, gbc_btnNewButton_3);
        
        JButton btnNewButton_4 = new JButton("List world record (Ocarina of Time)");
        btnNewButton_4.setHorizontalAlignment(SwingConstants.LEFT);
        btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try
                {
                    new Query(connection).listWorldRecord("Ocarina of Time");
                } catch (SQLException e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        
        JButton btnNewButton_7 = new JButton("All personal bests for skaterboy");
        btnNewButton_7.setHorizontalAlignment(SwingConstants.LEFT);
        btnNewButton_7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try
                {
                    new Query(connection).listPersonalBests("skater82297");
                } catch (SQLException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        GridBagConstraints gbc_btnNewButton_7 = new GridBagConstraints();
        gbc_btnNewButton_7.anchor = GridBagConstraints.WEST;
        gbc_btnNewButton_7.insets = new Insets(0, 0, 5, 0);
        gbc_btnNewButton_7.gridx = 1;
        gbc_btnNewButton_7.gridy = 1;
        QueryPanel.add(btnNewButton_7, gbc_btnNewButton_7);
        GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
        gbc_btnNewButton_4.anchor = GridBagConstraints.WEST;
        gbc_btnNewButton_4.insets = new Insets(5, 5, 5, 5);
        gbc_btnNewButton_4.gridx = 0;
        gbc_btnNewButton_4.gridy = 2;
        QueryPanel.add(btnNewButton_4, gbc_btnNewButton_4);
        
        JButton btnNewButton_5 = new JButton("List average run duration (All games)");
        btnNewButton_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try
                {
                    new Query(connection).listAverageRunDurationAllGames();
                } catch (SQLException e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
        gbc_btnNewButton_5.anchor = GridBagConstraints.WEST;
        gbc_btnNewButton_5.insets = new Insets(5, 5, 0, 5);
        gbc_btnNewButton_5.gridx = 0;
        gbc_btnNewButton_5.gridy = 3;
        QueryPanel.add(btnNewButton_5, gbc_btnNewButton_5);
        
        LoggerPanel LoggerPanel = new LoggerPanel();
        contentPane.add(LoggerPanel);
        
        System.setOut(new PrintStream(new Logger("LOG", LoggerPanel, System.out)));
    }

    protected boolean connectDatabase()
    {
        //Check if the fields happen to be null
        if(txtUrl.getText() == null ||
           txtUsername.getText() == null ||
            txtPassword.getText() == null
         )
        {
            throw new NullPointerException("Invalid info:"
                    + ", "+txtUrl.getText() 
                    + ", "+txtUsername.getText()
                    + ", "+txtPassword.getText() );
        }
        
        //Grab a connection from the database
        connection = Database.getConnection(txtUrl.getText(),
                txtUsername.getText(),
                txtPassword.getText());
        
        if(connection == null)
        {
            return false; //Connection failed
        }
        
        Query q;
        try
        {
            q = new Query(connection);
            if( q.databaseExists() )
            {
                System.out.println("Dropping existing database");
                q.dropDatabase();
            }
            System.out.println("Creating database...");
            q.createDatabase();
            System.out.println("Using database...");
            q.useDatabase();
            
            JPanel cards = getPanelInterface();
            CardLayout cardlayout = (CardLayout) (cards.getLayout());
            cardlayout.show(cards, "name_101278446640371");
        } catch (SQLException e1)
        {
            e1.printStackTrace();
        }
        
        return true;
    }

    public JPanel getPanelInterface() {
        return panelInterface;
    }
    public JPanel getQueryPanel() {
        return QueryPanel;
    }
    public JPanel getConnectionPanel() {
        return ConnectionPanel;
    }
}
