package com.example.f1.picado.Data;

/**
 * Created by f1 on 17-06-2016.
 */
public class TaskData {


    public String title;
    public String description;
    public String contact_number;
    public String image_path;
    public String is_alarm;
    public String alarm_time;
    public String alarm_date;
    public String repeat_status;
    public String type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactNumber() {
        return contact_number;
    }

    public void setContactNumber(String contactNumber) {
        this.contact_number= contact_number;
    }


    public String getImage_path(String image_path) {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getIs_alarm(String is_alarm) {
        return is_alarm;
    }

    public void setIs_alarm(String is_alarm) {
        this.is_alarm = is_alarm;
    }
    public String getAlarm_time(String alarm_time) {
        return alarm_time;
    }

    public void setAlarm_time(String alarm_time) {
        this.alarm_time = alarm_time;
    }
    public String getAlarm_date(String alarm_date) {
        return alarm_date;
    }

    public void setAlarm_date(String alarm_date) {
        this.alarm_date= alarm_date;
    }
    public String getRepeat_status(String repeat_status) {
        return repeat_status;
    }

    public void setRepeat_status(String repeat_status) {
        this.repeat_status = repeat_status;
    }
    public String getType(String type) {
        return type;
    }

    public void setType(String type) {
        this.type= type;
    }
}
