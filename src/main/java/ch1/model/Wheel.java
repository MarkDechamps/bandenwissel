package ch1.model;

import java.util.Date;

public class Wheel {
    private boolean winterTire;
    private Date attached;


    public Wheel(boolean winterTire, Date attached) {
        this.winterTire = winterTire;
        this.attached= attached;
    }

    public boolean isWinterTire() {
        return winterTire;
    }


    public Date getAttachedDate() {
        return attached;
    }
}
