package com.example.WebSedyolkinYA.servlets;

import com.example.WebSedyolkinYA.data.ConsiderationData;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;

import static com.example.WebSedyolkinYA.data.RenterData.logger;

@WebServlet(name = "ConsiderationServlet", value = "/ConsiderationServlet")
public class ConsiderationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("cons", ConsiderationData.selectConsideration());
        getServletContext().getRequestDispatcher("/ConsiderationJsp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        switch (request.getParameter("action")) {
            case "ok":
                try {
                    ConsiderationData.addConsideration(Integer.parseInt(request.getParameter("landlordOk")), Integer.parseInt(request.getParameter("gameOk")), Integer.parseInt(request.getParameter("numberOk")), Date.valueOf(request.getParameter("dateOk")));
                } catch (SQLException e) {
                    logger.log(Level.WARNING,"Ошибка SQL при добавлении записи", e);
                } catch (Exception exception) {
                    logger.log(Level.WARNING,"Ошибка при добавлении записи", exception);
                }
                request.setAttribute("cons", ConsiderationData.selectConsideration());
                getServletContext().getRequestDispatcher("/ConsiderationJsp.jsp").forward(request, response);
                break;
            case "delete": {
                String[] listCounter = request.getParameterValues("listCounter");
                String[] listId = request.getParameterValues("listId");
                for (String counter : listCounter) {
                    int counterForMass = Integer.parseInt(counter);
                    try {
                        ConsiderationData.deleteConsideration(Integer.parseInt(listId[counterForMass]));
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
                request.setAttribute("cons", ConsiderationData.selectConsideration());
                getServletContext().getRequestDispatcher("/ConsiderationJsp.jsp").forward(request, response);
                break;
            }
            case "edit": {
                String[] listId = request.getParameterValues("listId");
                String[] listCounter = request.getParameterValues("listCounter");
                String[] listLandlord = request.getParameterValues("listLandlord");
                String[] listGame = request.getParameterValues("listGame");
                String[] listNumber = request.getParameterValues("listNumber");
                String[] listDate = request.getParameterValues("listDate");

                for (String counter : listCounter) {
                    try {
                        int counterForMass = Integer.parseInt(counter);
                        ConsiderationData.editConsideration(Integer.parseInt(listId[counterForMass]), Integer.parseInt(listLandlord[counterForMass]), Integer.parseInt(listGame[counterForMass]), Integer.parseInt(listNumber[counterForMass]), Date.valueOf(listDate[counterForMass]));
                        logger.info("Запись №" + counter + " отредактирована");
                    } catch (SQLException e) {
                        logger.log(Level.WARNING,"Ошибка SQL при редактировании записи", e);
                        break;
                    } catch (Exception exception) {
                        logger.log(Level.WARNING,"Ошибка при редактировании записи", exception);
                        break;
                    }
                }
                request.setAttribute("cons", ConsiderationData.selectConsideration());
                getServletContext().getRequestDispatcher("/ConsiderationJsp.jsp").forward(request, response);
                break;
            }
            default:
                request.setAttribute("cons", ConsiderationData.selectConsideration());
                getServletContext().getRequestDispatcher("/ConsiderationJsp.jsp").forward(request, response);
                break;
        }
    }
}
