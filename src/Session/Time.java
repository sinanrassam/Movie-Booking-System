/*
 * A class that encapsulates integers used to represent hours, minutes and
 * seconds for the time of day in 24 hour format.
 */
package Session;

/**
 *
 * @author sinan
 */
public class Time implements Comparable<Time> {
    
    private int hours;
    private int mins;
    private int secs;
    
    public Time() {
        this(0, 0, 0);
    }
    
    public Time(int hours) {
        this(hours, 0, 0);
    }
    
    public Time(int hours, int mins) {
        this(hours, mins, 0);
    }
    
    public Time(int hours, int mins, int secs) {
        setHours(hours);
        setMinutes(mins);
        setSeconds(secs);
    }
    
    public void setSeconds(int secs) {
        if ((secs < 0) || (secs > 59)) {
            this.secs = 0;
        } else {
            this.secs = secs;
        }
    }
    
    public void setMinutes(int mins) {
        if ((mins < 0) || (mins > 59)) {
            this.mins = 0;
        } else {
            this.mins = mins;
        }
    }
    
    public void setHours(int hours) {
        if ((hours < 0) || (hours > 23)) {
            this.hours = 0;
        } else {
            this.hours = hours;
        }
    }
    
    public int getSeconds() {
        return secs;
    }
    
    public int getMinutes() {
        return mins;
    }
    
    public int getHours() {
        return hours;
    }
    
    @Override
    public boolean equals(Object otherTime) {
        Time other = (Time) otherTime;
        if (this.getHours() == other.getHours()) {
            if (this.getMinutes() == other.getMinutes()) {
                return (this.getSeconds() == other.getSeconds());
            }
            return (this.getMinutes() == other.getMinutes());
        }
        return (this.getHours() == other.getHours());
    }
    
    // override the currrent compareTO
    @Override
    // public method that an integer and accepts a card argument
    // the difference between two cards first by value and otherwise if equal by suit
    public int compareTo(Time otherTime) {
        if (this.getHours() != otherTime.getHours()) {
            return this.getHours() - otherTime.getHours();
        } else if (this.getMinutes() != otherTime.getMinutes()) {
            return (this.getMinutes() - otherTime.getMinutes());
        }
        return (this.getSeconds() - otherTime.getSeconds());
    }
    
    @Override
    public String toString() {
        String returnString = "";
        if (hours < 10) returnString += "0" + hours;
        else returnString += hours;
        
        returnString += ":";
        
        if (mins < 10) returnString += "0" + mins;
        else returnString += mins;
        
        returnString += ":";
        
        if (secs < 10) returnString += "0" + secs;
        else returnString += secs;
        
        return returnString;
    }
}
