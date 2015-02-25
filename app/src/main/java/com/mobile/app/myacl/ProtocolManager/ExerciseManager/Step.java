package com.mobile.app.myacl.ProtocolManager.ExerciseManager;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pirave on 15-02-16.
 */
public class Step implements Serializable{
    private int step_num;
    private String  pic_desc,pic_path;
    private String exeTitle;

    public Step(int step_num,String  pic_desc,String pic_path) {
        this.step_num = step_num;
        this.pic_desc = pic_desc;
        this.pic_path = pic_path;
    }

    public int getstepnum() {
        return step_num;
    }
    public String getpicdesc() {
        return pic_desc;
    }
    public String getpicpath() {
        return pic_path;
    }

    public String getExeTitle() {
        return exeTitle;
    }

    public boolean hasPic(){
        return this.pic_path != null;
    }
}
