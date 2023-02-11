package com.example.WebSedyolkinYA.data;

import com.example.WebSedyolkinYA.dto.GameDTO;
import com.example.WebSedyolkinYA.tools.DataBaseConnection;

import java.sql.*;
import java.util.LinkedList;
import java.util.logging.Level;

import static com.example.WebSedyolkinYA.data.RenterData.logger;

public class GameData {
    public static LinkedList<GameDTO> selectGame() {
        LinkedList<GameDTO> listGame = new LinkedList<GameDTO>();
        try (Connection connection = DataBaseConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM game ORDER BY id_game ASC ");
            while (resultSet.next()) {
                GameDTO gameDTO = new GameDTO();
                gameDTO.setId_game(Integer.parseInt(resultSet.getString(1)));
                gameDTO.setName_game(resultSet.getString(2));
                gameDTO.setRelease_date(resultSet.getDate(3));
                gameDTO.setNumber_players(resultSet.getInt(4));
                gameDTO.setDifficulty(resultSet.getString(5));
                listGame.add(gameDTO);
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING,"Ошибка SQL при считывании данных из таблицы game", e);
        } catch (Exception exception) {
            logger.log(Level.WARNING,"Ошибка при считывании данных из таблицы game", exception);
        }
        return listGame;
    }

    public static void addGame(String name_game, Date release_date, int number_players, String difficulty) throws SQLException {
        try (Connection connection = DataBaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO game VALUES (nextval('seq_id_game'), ?, ?, ?, ?);");
            preparedStatement.setString(1, name_game);
            preparedStatement.setDate(2, release_date);
            preparedStatement.setInt(3, number_players);
            preparedStatement.setString(4, difficulty);
            preparedStatement.executeUpdate();
        }
    }

    public static void deleteGame(int id_game) throws SQLException {
        try (Connection connection = DataBaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM game WHERE id_game = ?;");
            preparedStatement.setInt(1, id_game);
            preparedStatement.executeUpdate();
        }
    }

    public static void editGame(int id_game, String name_game, Date release_date, int number_players, String difficulty) throws SQLException{
        try (Connection connection = DataBaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE game SET name_game = ?, release_date = ?, number_players = ?, difficulty = ? WHERE id_game = ?;");
            preparedStatement.setString(1, name_game);
            preparedStatement.setDate(2, release_date);
            preparedStatement.setInt(3, number_players);
            preparedStatement.setString(4, difficulty);
            preparedStatement.setInt(5, id_game);
            preparedStatement.executeUpdate();
        }
    }
}
