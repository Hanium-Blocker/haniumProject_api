package kr.ac.skuniv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UrlPathHelper;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		
		System.out.print("들어옴1");

		
		
		return "home";
	}
	
	@RequestMapping(value = "/cand", method = RequestMethod.GET)
	public @ResponseBody String cand(HttpServletRequest request,Model model) {
		
		
		Connection conn = null;

		try {
			
		
			String url = "jdbc:mysql://192.168.0.8:3306/election"; 
			String db_id = "root"; 
			String db_pw = "1234"; 
		
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, db_id, db_pw); 
			System.out.println("success connect");

		} catch (Exception e) { 
			System.out.println("failed connect");
			e.printStackTrace();
		}
		
		java.sql.Statement stmt;
		try {
			stmt = conn.createStatement();
		
		String check = "select * from candidate;";
		ResultSet rs = stmt.executeQuery(check);	
		
		
		while (rs.next()) {
			String name = rs.getString("name");
			
			
			System.out.println(name);
		}
		}catch(Exception e) {
			System.out.println("데이터검색 오류 ");
		}
		
		System.out.print("들어옴");
		
		return "nayana";
	}
	
	
}
