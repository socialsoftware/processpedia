package pt.ist.processpedia.server.filter;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import pt.ist.processpedia.server.PropertiesManager;
import edu.yale.its.tp.cas.client.CASReceipt;
import edu.yale.its.tp.cas.client.CASAuthenticationException;
import edu.yale.its.tp.cas.client.ProxyTicketValidator;

public class CASAuthenticationFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    final String serverName = request.getServerName();
    final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    final String ticket = httpServletRequest.getParameter("ticket");
    if (ticket != null) {
        //AuthenticationAction.logout(httpServletRequest);

        final String requestURL = httpServletRequest.getRequestURL().toString();
        try {
      final CASReceipt receipt = getCASReceipt(serverName, ticket, requestURL);
      final String username = receipt.getUserName();
      //AuthenticationAction.login(httpServletRequest, username, null);
        } catch (CASAuthenticationException e) {
      e.printStackTrace();
      // do nothing ... the user just won't have a session
        }
    }
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {

  }
  
  public static CASReceipt getCASReceipt(final String serverName, final String casTicket, final String requestURL) throws UnsupportedEncodingException, CASAuthenticationException {
    final String casValidateUrl = PropertiesManager.getProperty("cas.service.validate.url");
    final String casServiceUrl = URLEncoder.encode(requestURL.replace("http://", "https://").replace(":8080", ""), "UTF-8");
    
    ProxyTicketValidator pv = new ProxyTicketValidator();
    pv.setCasValidateUrl(casValidateUrl);
    pv.setServiceTicket(casTicket);
    pv.setService(casServiceUrl);
    pv.setRenew(false);
    
    return CASReceipt.getReceipt(pv);
  }

}
