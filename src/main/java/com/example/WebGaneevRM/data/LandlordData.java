package com.example.WebGaneevRM.data;

import com.example.WebGaneevRM.dto.LandlordDTO;
import com.example.WebGaneevRM.tools.DataBaseConnection;

import java.sql.*;
import java.util.LinkedList;
import java.util.logging.Level;

import static com.example.WebGaneevRM.data.RenterData.logger;

public class LandlordData {
    public static LinkedList<LandlordDTO> selectLandlord() {
        LinkedList<LandlordDTO> listLandlord = new LinkedList<LandlordDTO>();
        try (Connection connection = DataBaseConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM landlord ORDER BY id_landlord ASC ");
            while (resultSet.next()) {
                LandlordDTO landlordDTO = new LandlordDTO();
                landlordDTO.setId_landlord(Integer.parseInt(resultSet.getString(1)));
                landlordDTO.setName_landlord(resultSet.getString(2));
                landlordDTO.setPhone(resultSet.getString(3));
                landlordDTO.setEmail(resultSet.getString(4));
                listLandlord.add(landlordDTO);
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING,"Ошибка SQL при считывании данных из таблицы landlord", e);
        } catch (Exception exception) {
            logger.log(Level.WARNING,"Ошибка при считывании данных из таблицы landlord", exception);
        }
        return listLandlord;
    }

    public static void addLandlord(String nameLandlord, String phoneLandlord, String emailLandlord) throws SQLException{
        try (Connection connection = DataBaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO landlord VALUES (nextval('seq_id_landlord'), ?, ?, ?);");
            preparedStatement.setString(1, nameLandlord);
            preparedStatement.setString(2, phoneLandlord);
            preparedStatement.setString(3, emailLandlord);
            preparedStatement.executeUpdate();
        }
    }

    public static void deleteLandlord(int idLandlord) throws SQLException {
        try (Connection connection = DataBaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM landlord WHERE id_landlord = ?;");
            preparedStatement.setInt(1, idLandlord);
            preparedStatement.executeUpdate();
        }
    }

    public static void editLandlord(int idLandlord, String nameLandlord, String phoneLandlord, String emailLandlord) throws SQLException{
        try (Connection connection = DataBaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE landlord SET name_landlord = ?, phone = ?, email = ? WHERE id_landlord = ?;");
            preparedStatement.setString(1, nameLandlord);
            preparedStatement.setString(2, phoneLandlord);
            preparedStatement.setString(3, emailLandlord);
            preparedStatement.setInt(4, idLandlord);
            preparedStatement.executeUpdate();
        }
    }
}
