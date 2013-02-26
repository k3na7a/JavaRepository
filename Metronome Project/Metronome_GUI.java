/*  Author:         John Desjardins
 *  Date Created:   Feb 23rd 2013
 *  Last Edited:    Feb 25th 2013
 *  Summary:        Basic musical metronome, added feature of specific Time
 *                  Siginiture input.
 */
package metronome_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/*  Class that handles creation and manipulation of all the projects GUI
 *  (Graphical User Interface). Yes I am aware it's ugly, it's a WIP.
 */
public class Metronome_GUI extends JFrame implements ActionListener {
 
    private Metronome metronome;
    
    private JPanel panel = new JPanel();
    private JPanel top_panel = new JPanel();
    private JPanel bottom_panel = new JPanel();
    
    private JLabel BPM_label = new JLabel();
    private JLabel BPB_label = new JLabel();
    private JLabel VPB_label = new JLabel();
    
    private JTextField BPM_text = new JTextField();
    private JTextField BPB_text = new JTextField();
    private JTextField VPB_text = new JTextField();
    
    private JButton start_stop = new JButton();
    private JButton[] buttonArray = new JButton[16];
    
    /*  Method that handles the creation, initialisation and placement of all
     *  graphical elements. 
     */
    public Metronome_GUI( Metronome metronome ) {
        
        this.metronome = metronome;
        
        panel.setLayout( new BorderLayout() );
        top_panel.setLayout( new GridLayout( 2,4 ) );
        bottom_panel.setLayout( new GridLayout( 2,8 ) );
        
        BPM_label.setText( "BPM" );
        BPM_label.setHorizontalAlignment( SwingConstants.CENTER );
        BPM_text.setText( "120" );
        BPB_label.setText( "Beats per Bar" );
        BPB_label.setHorizontalAlignment( SwingConstants.CENTER );
        BPB_text.setText( "4" );
        VPB_label.setText( "Value per Beat" );
        VPB_label.setHorizontalAlignment( SwingConstants.CENTER );
        VPB_text.setText( "4" );
        
        start_stop.setText( "Start" );
        start_stop.addActionListener( this );
                
        top_panel.add( new JLabel() );
        top_panel.add( BPM_label );
        top_panel.add( BPB_label );
        top_panel.add( BPB_text );
        top_panel.add( start_stop );
        top_panel.add( BPM_text );
        top_panel.add( VPB_label );
        top_panel.add( VPB_text );
        
        for( int i = 0; i < 16; i++ ){
            buttonArray[i] = new JButton();
            buttonArray[i].setBackground( Color.gray );
            bottom_panel.add( buttonArray[i] );
        }
        
        panel.add( top_panel, BorderLayout.NORTH );
        panel.add( bottom_panel, BorderLayout.CENTER );
        this.add( panel );
        this.setVisible( true );
        this.setTitle( "Victorious Metronome of the Twilight Vanquisher" );
        this.setSize( 400, 200 );
        this.setDefaultCloseOperation( EXIT_ON_CLOSE );
        
    }

    /*  Method that controls the colour of the "beat" buttons on screen
     *  to display what beat the metronome is currently on. Current beat
     *  is green and all other beats in the bar are red. Unused buttons are
     *  displayed as grey initially and when the stop button is clicked.
     */
    public void blink( int green, int max ){
        
        for ( int i=0; i < max; i++ ){
            buttonArray[i].setBackground( Color.RED );
        }
        buttonArray[green].setBackground( Color.GREEN );
        Toolkit.getDefaultToolkit().beep();
                
    }
   
    /*  Methos that controls the Start/Stop button and handles the graphical
     *  portion of what happens when the button is pushed.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if((JButton)(ae.getSource()) == start_stop){
            if(start_stop.getText() == "Start"){
                if( metronome.validate_input(BPM_text.getText(),BPB_text.getText(), VPB_text.getText()) ){
                    start_stop.setText("Stop");
                }
            }else{
                metronome.stop();
                start_stop.setText( "Start" );
                
                for (int i=0; i < 16; i++ ){
                    buttonArray[i].setBackground( Color.GRAY );
                }
                
            }
        }
    }    
}