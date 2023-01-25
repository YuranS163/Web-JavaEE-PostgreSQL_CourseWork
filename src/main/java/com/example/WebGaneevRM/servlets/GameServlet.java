package com.example.WebGaneevRM.servlets;

import com.example.WebGaneevRM.data.GameData;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;

import static com.example.WebGaneevRM.data.RenterData.logger;

@WebServlet(name = "GameServlet", value = "/GameServlet")
public class GameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("games", GameData.selectGame());
        getServletContext().getRequestDispatcher("/GameJsp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        switch (request.getParameter("action")) {
            case "ok":
                try {
                    GameData.addGame(request.getParameter("nameOk"), Date.valueOf(request.getParameter("dateOk")), Integer.parseInt(request.getParameter("numberOk")), request.getParameter("difficultyOk"));
                } catch (SQLException e) {
                    logger.log(Level.WARNING,"Ошибка SQL при добавлении записи", e);
                } catch (Exception exception) {
                    logger.log(Level.WARNING,"Ошибка при добавлении записи", exception);
                }
                request.setAttribute("games", GameData.selectGame());
                getServletContext().getRequestDispatcher("/GameJsp.jsp").forward(request, response);
                break;
            case "delete": {
                String[] listCounter = request.getParameterValues("listCounter");
                String[] listId = request.getParameterValues("listId");
                for (String counter : listCounter) {
                    int counterForMass = Integer.parseInt(counter);
                    try {
                        GameData.deleteGame(Integer.parseInt(listId[counterForMass]));
                        logger.info("Запись №" + counter + " удалена");
                    } catch (SQLException e) {
                        if (e.getSQLState().equals("23503")) {
                            logger.log(Level.WARNING,"Удаление невозможно. Запись №" + Integer.parseInt(listId[counterForMass]) + " используется в другой таблице", e);
                            request.setAttribute("error", "Удаление невозможно. Запись №" + Integer.parseInt(listId[counterForMass]) + " используется в другой таблице");
                        } else {
                            logger.log(Level.WARNING,"Ошибка SQL при удалении записи", e);
                        }
                        break;
                    } catch (Exception exception) {
                        logger.log(Level.WARNING,"Ошибка при удалении записи", exception);
                        break;
                    }
                }
                request.setAttribute("games", GameData.selectGame());
                getServletContext().getRequestDispatcher("/GameJsp.jsp").forward(request, response);
                break;
            }
            case "edit": {
                String[] listId = request.getParameterValues("listId");
                String[] listCounter = request.getParameterValues("listCounter");
                String[] listName = request.getParameterValues("listName");
                String[] listDate = request.getParameterValues("listDate");
                String[] listNumber = request.getParameterValues("listNumber");
                String[] listDifficulty = request.getParameterValues("listDifficulty");


                for (String counter : listCounter) {
                    try {
                        int counterForMass = Integer.parseInt(counter);
                        GameData.editGame(Integer.parseInt(listId[counterForMass]), listName[counterForMass], Date.valueOf(listDate[counterForMass]), Integer.parseInt(listNumber[counterForMass]), listDifficulty[counterForMass]);
                        logger.info("Запись №" + counter + " отредактирована");
                    } catch (SQLException e) {
                        logger.log(Level.WARNING,"Ошибка SQL при редактировании записи", e);
                        break;
                    } catch (Exception exception) {
                        logger.log(Level.WARNING,"Ошибка при редактировании записи", exception);
                        break;
                    }
                }
                request.setAttribute("games", GameData.selectGame());
                getServletContext().getRequestDispatcher("/GameJsp.jsp").forward(request, response);
                break;
            }
            default:
                request.setAttribute("games", GameData.selectGame());
                getServletContext().getRequestDispatcher("/GameJsp.jsp").forward(request, response);
                break;
        }
    }
}
