package com.example.WebGaneevRM.servlets;

import com.example.WebGaneevRM.data.ContractData;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;

import static com.example.WebGaneevRM.data.RenterData.logger;

@WebServlet(name = "ContractServlet", value = "/ContractServlet")
public class ContractServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("contracts", ContractData.selectContract());
        getServletContext().getRequestDispatcher("/ContractJsp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        switch (request.getParameter("action")) {
            case "ok":
                try {
                    ContractData.addContract(Integer.parseInt(request.getParameter("renterOk")), Integer.parseInt(request.getParameter("landlordOk")), Integer.parseInt(request.getParameter("gameOk")), Date.valueOf(request.getParameter("dateOk")), Date.valueOf(request.getParameter("timeLineOk")), Float.parseFloat(request.getParameter("amountOk")));
                } catch (SQLException e) {
                    logger.log(Level.WARNING,"Ошибка SQL при добавлении записи", e);
                } catch (Exception exception) {
                    logger.log(Level.WARNING,"Ошибка при добавлении записи", exception);
                }
                request.setAttribute("contracts", ContractData.selectContract());
                getServletContext().getRequestDispatcher("/ContractJsp.jsp").forward(request, response);
                break;
            case "delete": {
                String[] listCounter = request.getParameterValues("listCounter");
                String[] listId = request.getParameterValues("listId");
                for (String counter : listCounter) {
                    int counterForMass = Integer.parseInt(counter);
                    try {
                        ContractData.deleteContract(Integer.parseInt(listId[counterForMass]));
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
                request.setAttribute("contracts", ContractData.selectContract());
                getServletContext().getRequestDispatcher("/ContractJsp.jsp").forward(request, response);
                break;
            }
            case "edit": {
                String[] listId = request.getParameterValues("listId");
                String[] listCounter = request.getParameterValues("listCounter");
                String[] listRenter = request.getParameterValues("listRenter");
                String[] listLandlord = request.getParameterValues("listLandlord");
                String[] listGame = request.getParameterValues("listGame");
                String[] listDate = request.getParameterValues("listDate");
                String[] listTimeLine = request.getParameterValues("listTimeLine");
                String[] listAmount = request.getParameterValues("listAmount");

                for (String counter : listCounter) {
                    try {
                        int counterForMass = Integer.parseInt(counter);
                        ContractData.editContract(Integer.parseInt(listId[counterForMass]), Integer.parseInt(listRenter[counterForMass]), Integer.parseInt(listLandlord[counterForMass]), Integer.parseInt(listGame[counterForMass]), Date.valueOf(listDate[counterForMass]), Date.valueOf(listTimeLine[counterForMass]), Float.parseFloat(listAmount[counterForMass]));
                        logger.info("Запись №" + counter + " отредактирована");
                    } catch (SQLException e) {
                        logger.log(Level.WARNING,"Ошибка SQL при редактировании записи", e);
                        break;
                    } catch (Exception exception) {
                        logger.log(Level.WARNING,"Ошибка при редактировании записи", exception);
                        break;
                    }
                }
                request.setAttribute("contracts", ContractData.selectContract());
                getServletContext().getRequestDispatcher("/ContractJsp.jsp").forward(request, response);
                break;
            }
            default:
                request.setAttribute("contracts", ContractData.selectContract());
                getServletContext().getRequestDispatcher("/ContractJsp.jsp").forward(request, response);
                break;
        }
    }
}
