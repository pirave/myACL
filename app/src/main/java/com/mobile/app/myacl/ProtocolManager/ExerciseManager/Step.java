package com.mobile.app.myacl.ProtocolManager.ExerciseManager;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pirave on 15-02-16.
 */
public class Step implements Serializable{
    private int step_num;
    private String  step_desc,step_dur,step_rep,step_sets,step_frq,pic_path;
    private String exeTitle;

    public Step(int step_num,String  pic_desc,String step_dur,String step_rep,String step_sets,String step_frq,String pic_path) {
        this.step_num = step_num;
        this.step_desc = pic_desc;
        this.pic_path = pic_path;
        this.step_frq=step_frq;
        this.step_sets=step_sets;
        this.step_dur=step_dur;
        this.step_rep=step_rep;
    }

    public int getstepnum() {
        return step_num;
    }
    public String getstepdesc() {
        return step_desc;
    }
    public String getpicpath() {
        return pic_path;
    }
    public String getstepdur() {
        return step_dur;
    }

    public String getsteprep() {
        return step_rep;
    }

    public String getstepset() {
        return step_sets;
    }

    public String getstepfrq() {
        return step_frq;
    }
    public String getExeTitle() {
        return exeTitle;
    }

    public boolean hasPic(){
        return this.pic_path != null;
    }

}
