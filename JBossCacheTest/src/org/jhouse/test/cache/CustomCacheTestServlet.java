package org.jhouse.test.cache;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.cache.Cache;
import org.jboss.cache.CacheManager;

/**
 * Servlet implementation class CustomCacheTestServlet
 */
public class CustomCacheTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomCacheTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act= request.getParameter("act");
		String customCacheName= request.getParameter("customCacheName");
		String cacheKey= request.getParameter("cacheKey");
		String cacheValue= request.getParameter("cacheValue");
		StringBuffer resultHtml = new StringBuffer();
		PrintWriter out = response.getWriter();
		
		CacheManager cacheManager;
		Cache cache;
		try {
			cacheManager = (CacheManager)new InitialContext().lookup("java:/CacheManager");
			cache = cacheManager.getCache(customCacheName, false); 
			
			resultHtml.append("<script>	function goToUrl(){	location.href='http://localhost:8080/JBossCacheTest/cache_form.html';	}</script>");

			if(act.equals("input")){
				cache.put("/a/b/c", cacheKey, cacheValue);
				resultHtml.append("<h1>Data is stored in custom cache successfully.</h1>\n");
				resultHtml.append("<p>Custom cache :"+ customCacheName +"</p>");
				resultHtml.append("<p>Key :"+ cacheKey +" Value : "+ cacheValue +"</p>");
			}else{
				String storedData=(String)cache.get("/a/b/c",cacheKey);
				
				resultHtml.append("<h1>Data is retrieved from custome cache successfully.</h1>\n");
				resultHtml.append("<p>Custom cache :"+ customCacheName +"</p>");
				resultHtml.append("<p>Key :"+ cacheKey +" Value : "+ storedData +"</p>");
			}
			resultHtml.append("<input type='button'  onclick='goToUrl()' value='Home'/>");
			out.println(resultHtml.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
