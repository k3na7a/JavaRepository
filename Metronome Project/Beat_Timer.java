/*  Author:         John Desjardins
 *  Date Created:   Feb 23rd 2013
 *  Last Edited:    Feb 25th 2013
 *  Summary:        Basic musical metronome, added feature of specific Time
 *                  Siginiture input.
 */
package metronome_project;

import java.util.TimerTask;

/*  Object that deal with the timing portions of the project. 
 */
public class Beat_Timer extends TimerTask {
    
    private int beats = 0;
    private boolean keep_running = true;
    private int BPM; //BPM = Beats Per Measure
    private Metronome metronome;
    
    /*  Initialisation method
     */
    public Beat_Timer( Metronome metronome ){
        this.metronome = metronome;
    }
    
    /*  Method that changes the BPM value
     */
    public void change_BPM( int BPM ){
        this.BPM = BPM;
    }
    
    /*  Method that starts and stops the timer
     */
    public void change_run( boolean keep_running ) {
        this.keep_running = keep_running;
    }
    
    /*  Method that handles what happens when TIMER hits it's next scheduled
     *  execution.
     */
    public void run() {
         
        if ( keep_running ) {
            metronome.increment( beats );
        } else { 
            this.cancel();
        }
        
        if ( beats < BPM - 1 ) {
          beats++;  
        } else {
            beats = 0;
        }
        
    }
}
