package com.example.WebGaneevRM.data;

import com.example.WebGaneevRM.dto.ConsiderationDTO;
import com.example.WebGaneevRM.tools.DataBaseConnection;

import java.sql.*;
import java.util.LinkedList;
import java.util.logging.Level;

import static com.example.WebGaneevRM.data.RenterData.logger;

public class ConsiderationData {

    public static LinkedList<ConsiderationDTO> selectConsideration() {
        LinkedList<ConsiderationDTO> listCons = new LinkedList<ConsiderationDTO>();
        try (Connection connection = DataBaseConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id_consideration, name_landlord, name_game, number_games, delivery_date \n" +
                    "FROM consideration\n" +
                    "INNER JOIN game on consideration.id_game = game.id_game\n" +
                    "INNER JOIN landlord on consideration.id_landlord = landlord.id_landlord\n" +
                    "ORDER BY id_consideration ASC ");
            while (resultSet.next()) {
                ConsiderationDTO consDTO = new ConsiderationDTO();
                consDTO.setId_сonsideration(Integer.parseInt(resultSet.getString(1)));
                consDTO.setName_landlord(resultSet.getString(2));
                consDTO.setName_game(resultSet.getString(3));
                consDTO.setNumber_games(Integer.parseInt(resultSet.getString(4)));
                consDTO.setDelivery_date(resultSet.getDate(5));
                listCons.add(consDTO);
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING,"Ошибка SQL при считывании данных из таблицы contract", e);
        } catch (Exception exception) {
            logger.log(Level.WARNING,"Ошибка при считывании данных из таблицы contract", exception);
        }
        return listCons;
    }

    public static void addConsideration(int id_landlord, int id_game, int number_games, Date delivery_date) throws SQLException {
        try (Connection connection = DataBaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO consideration VALUES (nextval('seq_id_consideration'), ?, ?, ?, ?);");
            preparedStatement.setInt(1, id_landlord);
            preparedStatement.setInt(2, id_game);
            preparedStatement.setInt(3, number_games);
            preparedStatement.setDate(4, delivery_date);
            preparedStatement.executeUpdate();
        }
    }

    public static void deleteConsideration(int id_сonsideration) throws SQLException {
        try (Connection connection = DataBaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM consideration WHERE id_consideration = ?;");
            preparedStatement.setInt(1, id_сonsideration);
            preparedStatement.executeUpdate();
        }
    }

    public static void editConsideration(int id_сonsideration, int id_landlord, int id_game, int number_games, Date delivery_date) throws SQLException{
        try (Connection connection = DataBaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE consideration SET id_landlord = ?, id_game = ?, number_games = ?, delivery_date = ? WHERE id_consideration = ?;");
            preparedStatement.setInt(1, id_landlord);
            preparedStatement.setInt(2, id_game);
            preparedStatement.setInt(3, number_games);
            preparedStatement.setDate(4, delivery_date);
            preparedStatement.setInt(5, id_сonsideration);
            preparedStatement.executeUpdate();
        }
    }
}
