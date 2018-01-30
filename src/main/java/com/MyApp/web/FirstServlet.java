package com.MyApp.web;

import com.MyApp.controller.Controller;
import com.MyApp.controller.CreateUserController;
import com.MyApp.controller.LoginUserController;
import com.MyApp.controller.ProfileController;
import com.MyApp.dao.UserDaoImpl;
import com.MyApp.dbConnect.Factory;
import com.MyApp.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FirstServlet extends HttpServlet {

    private static final Map<Request, Controller> controllerMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        controllerMap.put(new Request("/servlet/login", "GET"), Factory.getSomething(LoginUserController::new)
                .compose(UserServiceImpl::new)
                .compose(UserDaoImpl::new)
                .apply(Factory.getConnection()));
        controllerMap.put(new Request("/servlet/login", "POST"), Factory.getSomething(LoginUserController::new)
                .compose(UserServiceImpl::new)
                .compose(UserDaoImpl::new)
                .apply(Factory.getConnection()));
        controllerMap.put(new Request("/servlet/register", "GET"), Factory.getSomething(CreateUserController::new)
                .compose(UserServiceImpl::new)
                .compose(UserDaoImpl::new)
                .apply(Factory.getConnection()));
        controllerMap.put(new Request("/servlet/register", "POST"), Factory.getSomething(CreateUserController::new)
                .compose(UserServiceImpl::new)
                .compose(UserDaoImpl::new)
                .apply(Factory.getConnection()));
        controllerMap.put(new Request("/servlet/profile", "GET"), Factory.getSomething(ProfileController::new)
                .compose(UserServiceImpl::new)
                .compose(UserDaoImpl::new)
                .apply(Factory.getConnection()));
        controllerMap.put(new Request("/servlet/profile", "POST"), Factory.getSomething(ProfileController::new)
                .compose(UserServiceImpl::new)
                .compose(UserDaoImpl::new)
                .apply(Factory.getConnection()));
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Request request = new Request(req.getRequestURI(), req.getMethod());

        Controller controller = controllerMap.get(request);
        if (controller == null) {
            req.getRequestDispatcher("/WEB-INF/error.jsp").forward(req, resp);
        }
        ViewModel vm = null;
        if (controller != null) {
            vm = controller.process(req, resp);
        }
        forward(req, resp, vm);
    }

    private void forward(HttpServletRequest req, HttpServletResponse resp, ViewModel vm) throws ServletException, IOException {
        processAttribute(req, vm);
        req.getRequestDispatcher(vm.getView()).forward(req, resp);

    }

    private void processAttribute(HttpServletRequest req, ViewModel vm) {
        if (vm.getArgumentsMap().isEmpty()) {
            return;
        }
        vm.getArgumentsMap().forEach(req::setAttribute);
    }
}





/*

    private static final Map<Request, Controller> controllerMap = new HashMap<>();


    public void init() {

        controllerMap.put(new Request("/login", POST.toString()), Factory.getSomething(CreateUserController::new)
                .compose(UserServiceImpl::new)
                .compose(UserDaoImpl::new)
                .apply(Factory.getConnection()));

    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);

        Controller controller = controllerMap.get(request);

        if(controller == null) {
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        }
        ViewModel vm = controller.process(request, response);

        forward(request, response, vm);


    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Request request1 = new Request(request.getRequestURI(), request.getMethod());


    }

*/

