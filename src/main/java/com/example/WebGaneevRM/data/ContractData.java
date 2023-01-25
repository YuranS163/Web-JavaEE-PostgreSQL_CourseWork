package com.example.WebGaneevRM.data;

import com.example.WebGaneevRM.dto.ContractDTO;
import com.example.WebGaneevRM.tools.DataBaseConnection;

import java.sql.*;
import java.util.LinkedList;
import java.util.logging.Level;

import static com.example.WebGaneevRM.data.RenterData.logger;

public class ContractData {
    public static LinkedList<ContractDTO> selectContract() {
        LinkedList<ContractDTO> listContract = new LinkedList<ContractDTO>();
        try (Connection connection = DataBaseConnection.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id_contract, name_renter, name_landlord, name_game, conclusion_date, time_line, amount \n" +
                    "FROM contract\n" +
                    "INNER JOIN renter on contract.id_renter = renter.id_renter\n" +
                    "INNER JOIN landlord on contract.id_landlord = landlord.id_landlord\n" +
                    "INNER JOIN game on contract.id_game = game.id_game\n" +
                    "ORDER BY id_contract ASC ");
            while (resultSet.next()) {
                ContractDTO contractDTO = new ContractDTO();
                contractDTO.setId_сontract(Integer.parseInt(resultSet.getString(1)));
                contractDTO.setName_renter(resultSet.getString(2));
                contractDTO.setName_landlord(resultSet.getString(3));
                contractDTO.setName_game(resultSet.getString(4));
                contractDTO.setConclusion_date(resultSet.getDate(5));
                contractDTO.setTime_line(resultSet.getDate(6));
                contractDTO.setAmount(Float.parseFloat(resultSet.getString(7)));
                listContract.add(contractDTO);
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING,"Ошибка SQL при считывании данных из таблицы contract", e);
        } catch (Exception exception) {
            logger.log(Level.WARNING,"Ошибка при считывании данных из таблицы contract", exception);
        }
        return listContract;
    }

    public static void addContract(int id_renter, int id_landlord, int id_game, Date conclusion_date, Date time_line, float amount) throws SQLException {
        try (Connection connection = DataBaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO contract VALUES (nextval('seq_id_contract'), ?, ?, ?, ?, ?, ?);");
            preparedStatement.setInt(1, id_renter);
            preparedStatement.setInt(2, id_landlord);
            preparedStatement.setInt(3, id_game);
            preparedStatement.setDate(4, conclusion_date);
            preparedStatement.setDate(5, time_line);
            preparedStatement.setFloat(6, amount);
            preparedStatement.executeUpdate();
        }
    }

    public static void deleteContract(int id_сontract) throws SQLException {
        try (Connection connection = DataBaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM contract WHERE id_contract = ?;");
            preparedStatement.setInt(1, id_сontract);
            preparedStatement.executeUpdate();
        }
    }

    public static void editContract(int id_сontract, int id_renter, int id_landlord, int id_game, Date conclusion_date, Date time_line, float amount) throws SQLException{
        try (Connection connection = DataBaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE contract SET id_renter = ?, id_landlord = ?, id_game = ?, conclusion_date = ?, time_line = ?, amount = ? WHERE id_contract = ?;");
            preparedStatement.setInt(1, id_renter);
            preparedStatement.setInt(2, id_landlord);
            preparedStatement.setInt(3, id_game);
            preparedStatement.setDate(4, conclusion_date);
            preparedStatement.setDate(5, time_line);
            preparedStatement.setFloat(6, amount);
            preparedStatement.setInt(7, id_сontract);
            preparedStatement.executeUpdate();
        }
    }
}
