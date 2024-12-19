package dao;

import enums.HolidayType;
import model.Holiday;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public abstract class HolidayDAO implements GenericDAO {

    private Connection connection;

    public HolidayDAO() {
        try {
            connection = DBConnection.getConnection();
        } catch (SQLException connectionException) {
            connectionException.printStackTrace();
        }
    }


    @Override
    public boolean addHoliday(Holiday holiday) {
        try (PreparedStatement addStatement = connection.prepareStatement(
                "INSERT INTO holiday (startdate, enddate, conge) VALUES (?, ?, ?)")) {
            addStatement.setString(1, getAllHoliday().toString());
            addStatement.setString(2, holiday.getStartdate());
            addStatement.setString(3, holiday.getFindate());
            addStatement.setString(4, holiday.getConge().name());


            return addStatement.executeUpdate() > 0;

        } catch (SQLException addException) {
            addException.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean updateHoliday(Holiday holiday) {
        try (PreparedStatement updateStatement = connection.prepareStatement("UPDATE holiday SET startdate = ?, findate = ?, conge = ? WHERE id = ?")) {

            updateStatement.setString(1, getAllHoliday().toString());
            updateStatement.setString(2, holiday.getStartdate());
            updateStatement.setString(3, holiday.getFindate());
            updateStatement.setString(4, holiday.getConge().name());


            return updateStatement.executeUpdate() > 0;

        } catch (SQLException updateException) {
            updateException.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteHoliday(int id) {
        try (PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM holiday WHERE id = ?")) {

            deleteStatement.setInt(1, id);
            return deleteStatement.executeUpdate() > 0;

        } catch (SQLException deleteException) {
            return false;
        }
    }

    @Override
    public List<Holiday> getAllHoliday() {
        List<Holiday> holidays = new ArrayList<>();
        try (ResultSet getResult = connection.prepareStatement("SELECT * FROM holiday").executeQuery()) {

            while (getResult.next()) {
                holidays.add(new Holiday(
                        getResult.getInt("id"),
                        getResult.getString("startdate"),
                        getResult.getString("findate"),

                        HolidayType.valueOf("Conge"))
                );
            }

        } catch (SQLException getException) {
            getException.printStackTrace();
        }
        return holidays;
    }
}
