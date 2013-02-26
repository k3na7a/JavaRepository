/*  Author:         John Desjardins
 *  Date Created:   Feb 23rd 2013
 *  Last Edited:    Feb 25th 2013
 *  Summary:        Basic musical metronome, added feature of specific Time
 *                  Siginiture input.
 */
package metronome_project;

import java.util.Timer;
import javax.swing.JOptionPane;

/*  Metronome object that handles most of the projects logic.
 */
public class Metronome {
     
     Timer timer = new Timer();
     Beat_Timer t = new Beat_Timer(this);
     Metronome_GUI GUI = new Metronome_GUI(this);
     
     long BPM_long;     // BPM = Beats per Measure
     int BPB_int;       // BPB = Beats per Bar
     long VPB_long;     // VPB = Value per Beat
    
    /*  Method that handles incrementing the beat variable. 
     */
     public void increment(int beat){
         
         GUI.blink( beat, BPB_int );
         
     }
     
    /*  Method that handles the validation of all input when the start button
     *  is pressed. Returns a boolean value of true if all input is validated
     *  and false is any 1 piece is not along with a dialog box stating what
     *  input isn't validated and why.
     */
    public boolean validate_input( String BPM, String BPB, String VPB ){
         
         boolean success = true;
         long period;
         String error_message = "";
         
         if(tryParse(BPM)){
             BPM_long = (long)Integer.parseInt(BPM);
             if (BPM_long < 50 || BPM_long > 250){
                 error_message = error_message + "Beats per Minute must be between 50 and 250\n";
                 success = false;
             }
         }else{
             error_message = error_message + "Beats per Minute is not a valid integer\n";
             success = false;
         }
         
         if(tryParse(BPB)){
             BPB_int = Integer.parseInt(BPB);
             if (BPB_int < 1 || BPB_int > 16){
                 error_message = error_message + "Beats per bar must be between 1 and 16\n";
                 success = false;
             }
         }else{
             error_message = error_message + "Beats per bar is not a valid integer\n";
             success = false;
         }
         
         if(tryParse(VPB)){
             VPB_long = (long)Integer.parseInt(VPB);
             if ( VPB_long < 1 || VPB_long > 16 ){
                 error_message = error_message + "Value per Beat must be between 1 and 16\n";
                 success = false;
             }
         }else{
             error_message = error_message + "Value per Beat is not a valid integer\n";
             success = false;
         }
         
         if( success ){
             period = ( long )( 60000/(BPM_long * ( ( double )VPB_long / 4) ) );
             start( period, BPB_int );
         } else {
             JOptionPane.showMessageDialog( null, error_message );
         }
         
         return success;
         
    }
     
    /*  Method that handles starting the metronome
     */
    public void start(long period, int BPB){
        t.change_BPM(BPB);
        timer.schedule( t, 0, period );
    }
    
    /*  Methos that handles stopping the metronome
     */
    public void stop() {
         t.change_run( false );
         t = new Beat_Timer( this );
    }
    
    
    /*  Method that checks if String input is a valid integer and
     *  returns a boolean value of true if successful and false
     *  otherwise.
     */
    boolean tryParse( String value ) {  
        try{  
            Integer.parseInt( value );  
            return true;  
         } 
         catch( NumberFormatException nfe ){  
             return false;  
        } 
        
    }
}
