package com.zilin.springtest.MyFilter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import java.io.IOException;

@Log4j2
public class PreRequestLogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("laiydada");
    }

    @Override
    public void destroy() {

    }
}
