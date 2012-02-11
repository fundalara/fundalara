package dao.general;
import javax.servlet.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 *
 * @author JavAdicto
 */
public class SessionManager implements Filter
{
    private static final SessionFactory sessionFactory;

    static
    {
        try
        {
            //Aqui creamos una session de la fabrica de session usando
            //el archivo de configuracion hibernate.cfg.xml
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        }
        catch (Throwable ex)
        {
           
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

   //Aqui en donde deberia crear una session de la fabrica de sessiones por que es el metodo init y este metodo se hizo para
   //inicializar valores o solicitar recursos, pero eso ya lo hice arriba.
    public void init(FilterConfig filterConfig) throws ServletException
    {        
    }

    //Este metodo se debe declarar por que viene especificado en la interfaz y es donde se deberia asignar una session
    //a un thread local, pero eso ya viene especificado en el archivo de configuracion hibernate.cfg.xml
    //<property name="hibernate.current_session_context_class">thread</property>
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    {
    }

    //Para obtener una session
    public static Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    //El metodo destroy se manda a llamar automaticamente al terminar el request, de esta manera
   //aseguramos no dejar la session abierta
    public void destroy()
    {
        try
        {
            sessionFactory.close();
        }
        catch (HibernateException ex)
        {
            throw new RuntimeException(ex);
        }
    }
}
