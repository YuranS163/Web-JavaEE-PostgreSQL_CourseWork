package com.example.WebSedyolkinYA.data;

import com.example.WebSedyolkinYA.tools.DataBaseConnection;
import com.example.WebSedyolkinYA.dto.RenterDTO;

import java.sql.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RenterData {
    public static final Logger logger = Logger.getLogger(RenterData.class.getName());

    public static LinkedList<RenterDTO> selectRenter() {
        LinkedList<RenterDTO> listRenter = new LinkedList<RenterDTO>();
        try (Connection connection = DataBaseConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM renter ORDER BY id_renter ASC ");
            while (resultSet.next()) {
                RenterDTO renterDTO = new RenterDTO();
                renterDTO.setId_renter(Integer.parseInt(resultSet.getString(1)));
                renterDTO.setName_renter(resultSet.getString(2));
                renterDTO.setPhone(resultSet.getString(3));
                renterDTO.setEmail(resultSet.getString(4));
                listRenter.add(renterDTO);
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING,"Ошибка SQL при считывании данных из таблицы renter", e);
        } catch (Exception exception) {
            logger.log(Level.WARNING,"Ошибка при считывании данных из таблицы renter", exception);
        }
        return listRenter;
    }

    public static void addRenter(String nameRenter, String phoneRenter, String emailRenter) throws SQLException {
        try (Connection connection = DataBaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO renter VALUES (nextval('seq_id_renter'), ?, ?, ?);");
            preparedStatement.setString(1, nameRenter);
            preparedStatement.setString(2, phoneRenter);
            preparedStatement.setString(3, emailRenter);
            preparedStatement.executeUpdate();
        }
    }

    public static void deleteRenter(int idRenter) throws SQLException {
        try (Connection connection = DataBaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM renter WHERE id_renter = ?;");
            preparedStatement.setInt(1, idRenter);
            preparedStatement.executeUpdate();
        }
    }

    public static void editRenter(int idRenter, String nameRenter, String phoneRenter, String emailRenter) throws SQLException{
        try (Connection connection = DataBaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE renter SET name_renter = ?, phone = ?, email = ? WHERE id_renter = ?;");
            preparedStatement.setString(1, nameRenter);
            preparedStatement.setString(2, phoneRenter);
            preparedStatement.setString(3, emailRenter);
            preparedStatement.setInt(4, idRenter);
            preparedStatement.executeUpdate();
        }
    }
}
