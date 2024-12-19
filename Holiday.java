package model;

import enums.HolidayType;

public class Holiday {
    private  int id;
    private String startdate;
    private String findate;
    private HolidayType conge;
    public  Holiday(int id, String startdate, String findate, HolidayType conge){
        this.conge=conge;
        this.id=id;
        this.startdate=startdate;
        this.findate=findate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getFindate() {
        return findate;
    }

    public void setFindate(String findate) {
        this.findate = findate;
    }

    public HolidayType getConge() {
        return conge;
    }

    public void setConge(HolidayType conge) {
        this.conge = conge;
    }
}
