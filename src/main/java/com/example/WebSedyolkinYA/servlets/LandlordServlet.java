package com.example.WebSedyolkinYA.servlets;

import com.example.WebSedyolkinYA.data.LandlordData;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;

import static com.example.WebSedyolkinYA.data.RenterData.logger;

@WebServlet(name = "LandlordServlet", value = "/LandlordServlet")
public class LandlordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("landlords", LandlordData.selectLandlord());
        getServletContext().getRequestDispatcher("/LandlordJsp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        switch (request.getParameter("action")) {
            case "ok":
                try {
                    LandlordData.addLandlord(request.getParameter("nameOk"), request.getParameter("phoneOk"), request.getParameter("emailOk"));
                } catch (SQLException e) {
                    logger.log(Level.WARNING,"Ошибка SQL при добавлении записи", e);
                } catch (Exception exception) {
                    logger.log(Level.WARNING,"Ошибка при добавлении записи", exception);
                }
                request.setAttribute("landlords", LandlordData.selectLandlord());
                getServletContext().getRequestDispatcher("/LandlordJsp.jsp").forward(request, response);
                break;
            case "delete": {
                String[] listCounter = request.getParameterValues("listCounter");
                String[] listId = request.getParameterValues("listId");
                for (String counter : listCounter) {
                    int counterForMass = Integer.parseInt(counter);
                    try {
                        LandlordData.deleteLandlord(Integer.parseInt(listId[counterForMass]));
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
                request.setAttribute("landlords", LandlordData.selectLandlord());
                getServletContext().getRequestDispatcher("/LandlordJsp.jsp").forward(request, response);
                break;
            }
            case "edit": {
                String[] listId = request.getParameterValues("listId");
                String[] listCounter = request.getParameterValues("listCounter");
                String[] listName = request.getParameterValues("listName");
                String[] listPhone = request.getParameterValues("listPhone");
                String[] listEmail = request.getParameterValues("listEmail");

                for (String counter : listCounter) {
                    try {
                        int counterForMass = Integer.parseInt(counter);
                        LandlordData.editLandlord(Integer.parseInt(listId[counterForMass]), listName[counterForMass], listPhone[counterForMass], listEmail[counterForMass]);
                        logger.info("Запись №" + counter + " отредактирована");
                    } catch (SQLException e) {
                        logger.log(Level.WARNING,"Ошибка SQL при редактировании записи", e);
                        break;
                    } catch (Exception exception) {
                        logger.log(Level.WARNING,"Ошибка при редактировании записи", exception);
                        break;
                    }
                }
                request.setAttribute("landlords", LandlordData.selectLandlord());
                getServletContext().getRequestDispatcher("/LandlordJsp.jsp").forward(request, response);
                break;
            }
            default:
                request.setAttribute("landlords", LandlordData.selectLandlord());
                getServletContext().getRequestDispatcher("/LandlordJsp.jsp").forward(request, response);
                break;
        }
    }
}
