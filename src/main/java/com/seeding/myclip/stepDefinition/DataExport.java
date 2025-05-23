package com.seeding.myclip.stepDefinition;

public class DataExport {
    private String phone;
    private int id_video;
    private int pre_view;
    private int after_view;
    private String message_error;

    public DataExport(){
        super();
    }

    public DataExport(String phone, int id_video, int pre_view, int after_view, String message_error){
        super();
        this.phone = phone;
        this.id_video = id_video;
        this.pre_view = pre_view;
        this.after_view = after_view;
        this.message_error = message_error;
    }

    public String getPhone(){
        return phone;
    }

    public int getId_video(){
        return id_video;
    }

    public int getPre_view(){
        return pre_view;
    }

    public int getAfter_view(){
        return after_view;
    }

    public String getMessage_error(){
        return message_error;
    }

}
