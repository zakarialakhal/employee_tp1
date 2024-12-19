package dao;

import model.Holiday;

import java.util.List;


public interface GenericDAO {
    boolean addHoliday(Holiday holiday);
    boolean updateEmployer(Holiday holiday );

    boolean updateHoliday(Holiday holiday);

    boolean deleteHoliday(int id);
    List<Holiday> getAllHoliday();
}
