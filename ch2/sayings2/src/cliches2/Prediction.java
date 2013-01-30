package cliches2;

import java.io.Serializable;

// An array of Predictions is to be serialized
// into an XML document, which is returned to 
// the consumer on a request. 
public class Prediction implements Serializable {
    private String who;   // person
    private String what;  // his/her prediction
    private int    id;    // lookup-key in Predictions map

    public Prediction() { }

    public void setWho(String who) {
	this.who = who;
    }
    public String getWho() {
	return this.who;
    }

    public void setWhat(String what) {
	this.what = what;
    }
    public String getWhat() {
	return this.what;
    }

    public void setId(int id) {
	this.id = id;
    }
    public int getId() {
	return this.id;
    }
}