package web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/SesionesServlet")
public class SessionesServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession sesion = request.getSession();
        String titulo = null;

        //le Solicitamos un ala secion el contadorVisitas
          //usamos el sesion.getAttribute("contadorVisitas") para pasale el numero a contadorVisitas
          
        Integer contadorVisitas = (Integer) sesion.getAttribute("contadorVisitas");
        //si es nulo es la primera vez que accedemos ala aplicacion
        if (contadorVisitas == null) {
            contadorVisitas = 1;
            titulo = "Bienvenido por primera vez";
        } else {
            contadorVisitas++;
            titulo = "Bienvenido nuevamente";
        }
        //Agregamos el nuevo valor ala sesion
        //  sesion.setAttribute se comporta como un mapa la llave y el valor asociado
        sesion.setAttribute("contadorVisitas", contadorVisitas);
        //mandamos al respuesta al cliente
        PrintWriter out = response.getWriter();
        out.print(titulo);
        out.print("<br>");
        out.print("no. accesos al recurso : " + contadorVisitas);
        out.print("<br>");
        out.print("Id de la secion " + sesion.getId());
        out.close();

    }

}
