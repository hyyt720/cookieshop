package filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
@WebFilter(filterName = "EncodeFilter",urlPatterns = "/*")
public class EncodingFilter implements Filter{
    public void destroy(){}

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        chain.doFilter(req,resp);
    }
    public void init(FilterConfig config) throws ServletException {}
}
