package models;

/**
 * Created by subhashsanghani on 5/25/17.
 */

public class AppointmentModel {
    private String id;
    private String user_id;
    private String bus_id;
    private String appointment_date;
    private String start_time;
    private String time_token;
    private String status;
    private String app_name;
    private String app_email;
    private String app_phone;
    private String created_at;
    private String bus_title;
    private String bus_slug;
    private String bus_email;
    private String bus_latitude;
    private String bus_longitude;
    private String bus_contact;
    private String bus_logo;
    private String bus_fee;
    private String currency;
    private String doct_name;
    private String doct_degree;

    public AppointmentModel(String id, String user_id, String bus_id, String appointment_date, String start_time, String time_token, String status, String app_name, String app_email, String app_phone, String created_at, String bus_title, String bus_slug, String bus_email, String bus_latitude, String bus_longitude, String bus_contact, String bus_logo, String bus_fee, String currency, String doct_name, String doct_degree) {
        this.id = id;
        this.user_id = user_id;
        this.bus_id = bus_id;
        this.appointment_date = appointment_date;
        this.start_time = start_time;
        this.time_token = time_token;
        this.status = status;
        this.app_name = app_name;
        this.app_email = app_email;
        this.app_phone = app_phone;
        this.created_at = created_at;
        this.bus_title = bus_title;
        this.bus_slug = bus_slug;
        this.bus_email = bus_email;
        this.bus_latitude = bus_latitude;
        this.bus_longitude = bus_longitude;
        this.bus_contact = bus_contact;
        this.bus_logo = bus_logo;
        this.bus_fee = bus_fee;
        this.currency = currency;
        this.doct_name = doct_name;
        this.doct_degree = doct_degree;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getBus_id() {
        return bus_id;
    }

    public void setBus_id(String bus_id) {
        this.bus_id = bus_id;
    }

    public String getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(String appointment_date) {
        this.appointment_date = appointment_date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getTime_token() {
        return time_token;
    }

    public void setTime_token(String time_token) {
        this.time_token = time_token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getApp_email() {
        return app_email;
    }

    public void setApp_email(String app_email) {
        this.app_email = app_email;
    }

    public String getApp_phone() {
        return app_phone;
    }

    public void setApp_phone(String app_phone) {
        this.app_phone = app_phone;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }


    public String getBus_title() {
        return bus_title;
    }

    public void setBus_title(String bus_title) {
        this.bus_title = bus_title;
    }

    public String getBus_slug() {
        return bus_slug;
    }

    public void setBus_slug(String bus_slug) {
        this.bus_slug = bus_slug;
    }

    public String getBus_email() {
        return bus_email;
    }

    public void setBus_email(String bus_email) {
        this.bus_email = bus_email;
    }

    public String getBus_latitude() {
        return bus_latitude;
    }

    public void setBus_latitude(String bus_latitude) {
        this.bus_latitude = bus_latitude;
    }

    public String getBus_longitude() {
        return bus_longitude;
    }

    public void setBus_longitude(String bus_longitude) {
        this.bus_longitude = bus_longitude;
    }

    public String getBus_contact() {
        return bus_contact;
    }

    public void setBus_contact(String bus_contact) {
        this.bus_contact = bus_contact;
    }

    public String getBus_logo() {
        return bus_logo;
    }

    public void setBus_logo(String bus_logo) {
        this.bus_logo = bus_logo;
    }

    public String getBus_fee() {
        return bus_fee;
    }

    public void setBus_fee(String bus_fee) {
        this.bus_fee = bus_fee;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


    public String getDoct_name() {
        return doct_name;
    }

    public void setDoct_name(String doct_name) {
        this.doct_name = doct_name;
    }

    public String getDoct_degree() {
        return doct_degree;
    }

    public void setDoct_degree(String doct_degree) {
        this.doct_degree = doct_degree;
    }
}
