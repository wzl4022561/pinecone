package cc.pinecone.renren.devicecontroller.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cc.pinecone.renren.devicecontroller.dao.PineconeApi;

import com.tenline.pinecone.platform.model.Device;
import com.tenline.pinecone.platform.model.Entity;
import com.tenline.pinecone.platform.model.User;
import com.tenline.pinecone.platform.model.Variable;
import com.tenline.pinecone.platform.sdk.RESTClient;

/**
 * Sample controller for going to the home page with a message
 */
@Controller
public class PageController {

	@Autowired
	private MessageSource msgSrc;
	
	private static PineconeApi pApi;
	
	private static RESTClient client;
	public RESTClient getRESTClient() {
		if(client == null){
			client = new RESTClient(AppConfig.BASE_URL);
		}
		return client;
	}
	
	public PineconeApi getPineconeAPI(){
		if(pApi == null){
			pApi = new PineconeApi();
		}
		return pApi;
	}

	private static final Logger logger = LoggerFactory
			.getLogger(PageController.class);

	@RequestMapping(value = "/redirect.html")
	public String redirect(Model model) {
//		String datePat = msgSrc.getMessage("application.title", null, Locale.CHINA);
//		System.out.println("get message:"+datePat);
		logger.info("redirect");
		System.out.println("redirect");
		model.addAttribute("appid", AppConfig.APP_ID);
		return "redirect";
	}
	
	@RequestMapping(value = "/home.html")
	public ModelAndView home(HttpServletRequest request,HttpServletResponse response) {
//		String access_token = request.getParameter("access_token");
//		String xn_sig_user = request.getParameter("xn_sig_user");
//		String xn_sig_session_key = request.getParameter("xn_sig_session_key");
//		
//		System.out.println(access_token+"///"+xn_sig_user+"///"+xn_sig_session_key);
//		String accessToken = model.
//		if(EnvConfig.getAccess_token() == null){
//			Window.alert("跳转到登陆页面");
//			return;
//		}
//		
//		service.isExist(EnvConfig.getUserid(), new AsyncCallback<User>(){
//
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert("连接Pinecone平台失败！");
//			}
//
//			@Override
//			public void onSuccess(User result) {
//				if(result == null){
//					if(Window.confirm("你的信息还没有在Pinecone平台注册，是否同意注册？")){
//						service.register(EnvConfig.getUserid(), "", "", new AsyncCallback<User>(){
//
//							@Override
//							public void onFailure(Throwable caught) {
//								Window.alert("初始化新用户信息失败！");
//							}
//
//							@Override
//							public void onSuccess(User result) {
//								EnvConfig.setUser(result);
//								RootPanel.get().add(new MainPanel());
//							}
//							
//						});
//					}
//				}else{
//					EnvConfig.setUser(result);
//					RootPanel.get().add(new MainPanel());
//				}
//			}});
			
		
//		logger.info("home.jsp");
//		System.out.println("home.jsp");
//		model.addAttribute("appid", AppConfig.APP_ID);
//		return "home";
		return new ModelAndView("home");
	}


	@RequestMapping(value = "/login.html")
	public String login(Model model) {
		logger.info("login.html");
		System.out.println("login.html");
		model.addAttribute("reject", false);
		return "login";
	}
	
	@RequestMapping(value = "/register.html")
	public String register(Model model) {
		logger.info("register.html");
		System.out.println("register.html");
		return "register";
	}
	
	@RequestMapping(value = "/index.html")
	public String index(HttpServletRequest request,HttpServletResponse response) {
		logger.info("index.html");
		System.out.println("index.html");
		
		//TODO test
//		TestAPI t = new TestAPI();
//		ArrayList<Device> list = t.getAllDevice1();
//		System.out.println("list size:"+list.size());
		
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		System.out.println("username:"+username+"\npassword:"+password);
//		
//		User user = this.getPineconeAPI().login(username, password);
//		if(user != null){
//			System.out.println("login success");
//			ModelAndView mav = new ModelAndView("index");
//			mav.addObject("reject", false);
//			mav.addObject("user", user);
//			return mav;
//		}else{
//			System.out.println("login fail");
//			ModelAndView mav = new ModelAndView("login");
//			mav.addObject("reject",true);
//			return mav;
//		}
//		ModelAndView mav = new ModelAndView("index");
//		mav.addObject("list", list);
//		request.setAttribute("list", list);
		return "index";
	}
	
	@RequestMapping(value = "/queryvariable.html", method=RequestMethod.GET)
	public String queryVariable(HttpServletRequest request,HttpServletResponse response){
		logger.info("devices.html");
		System.out.println("queryVariable");
		String id = request.getParameter("id");
		System.out.println(id);
		
		List<Variable> list = new ArrayList<Variable>();
		try {
			ArrayList<Entity> vars = (ArrayList<Entity>) this.getRESTClient().get("/device/"+id+"/variables","admin","admin");
			for(Entity ent:vars){
				Variable var = (Variable)ent;
				list.add(var);
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("list", list);
		
		return "variable";
	}

	@RequestMapping(value = "/devices.html")
	public String devices(HttpServletRequest request,HttpServletResponse response) {
		logger.info("devices.html");
		System.out.println("devices.html");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("username:"+username+"\npassword:"+password);
		
		User user = pApi.login(username, password);
		return "devices";
	}

	@RequestMapping(value = "/setting.html")
	public String registry(Model model) {
		logger.info("setting.html");
		System.out.println("setting.html");
		return "setting";
	}

	@RequestMapping(value = "/user.html")
	public String sensation(Model model) {
		logger.info("user.html");
		System.out.println("user.html");
		return "user";
	}

}