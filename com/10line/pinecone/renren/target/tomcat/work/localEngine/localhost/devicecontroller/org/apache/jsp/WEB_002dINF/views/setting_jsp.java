package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class setting_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<link rel=\"stylesheet\"  href=\"css/style.css\">\r\n");
      out.write("<script src=\"js/jquery.js\" type=\"text/javascript\" charset=\"utf-8\"></script>\r\n");
      out.write("<script src=\"js/tanchu.js\" type=\"text/javascript\" charset=\"utf-8\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("$(function(){\r\n");
      out.write("\t\r\n");
      out.write("\t$(\".showbox\").click(function(){\r\n");
      out.write("\t\t$(\"#TB_overlayBG\").css({\r\n");
      out.write("\t\t\tdisplay:\"block\",height:$(document).height()\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t$(\".box\").css({\r\n");
      out.write("\t\t\tleft:($(\"body\").width()-$(\".box\").width())/2-20+\"px\",\r\n");
      out.write("\t\t\ttop:($(window).height()-$(\".box\").height())/2+$(window).scrollTop()+\"px\",\r\n");
      out.write("\t\t\tdisplay:\"block\"\r\n");
      out.write("\t\t});\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\t$(\".close\").click(function(){\r\n");
      out.write("\t\t$(\"#TB_overlayBG\").css(\"display\",\"none\");\r\n");
      out.write("\t\t$(\".box \").css(\"display\",\"none\");\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("})\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<title>松果后台</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div class=\"mian\">\r\n");
      out.write("  <div class=\"title\"></div>\r\n");
      out.write("  <div class=\"content\">\r\n");
      out.write("    <div class=\"left\">\r\n");
      out.write("      <ul class=\"navlist\">\r\n");
      out.write("        <li class=\"top\"> 设备信息参数 </li>\r\n");
      out.write("        <li><a id=\"List_devices\" href=\"devices.html\">设备列表</a></li>\r\n");
      out.write("        <li><a style=\"background: url(images/select_bg.jpg) no-repeat 0 0px; color: #fff;\" id=\"Device_parameters\" href=\"setting.html\">设备参数</a></li>\r\n");
      out.write("        <li><a href=\"user.html\">用户信息</a></li>\r\n");
      out.write("      </ul>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"right\">\r\n");
      out.write("            <div class=\"Guide\"><p>控制台 ><a href=\"#\">基本信息管理</a></p><span><a href=\"login.html\">注销</a></span></div>\r\n");
      out.write("      <div class=\"name\">\r\n");
      out.write("        <p>设备参数</p>\r\n");
      out.write("        <div class=\"timing\"><a href=\"#\"><img src=\"images/timing.jpg\" /></a></div>\r\n");
      out.write("      </div>\r\n");
      out.write("      <table style=\"text-align: center;\"width=\"980\" cellpadding=\"0\" cellspacing=\"0\">\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td width=\"68\" height=\"45\" style=\"border-bottom: 1px solid #c6c6c6;  background: url(images/table_top.jpg) repeat-x;\"><input type=\"checkbox\" value=\"checkbox\"></td>\r\n");
      out.write("          <td width=\"87\" height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; background: url(images/table_top.jpg) repeat-x; text-align: left;\">用户姓名</td>\r\n");
      out.write("          <td width=\"172\" height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; border-left: 1px solid #c6c6c6; background: url(images/table_top.jpg) repeat-x;\">邮件</td>\r\n");
      out.write("          <td height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; border-left: 1px solid #c6c6c6;   background: url(images/table_top.jpg) repeat-x;\">好友列表</td>\r\n");
      out.write("          <td height=\"45\" colspan=\"2\" style=\"border-bottom: 1px solid #c6c6c6;  background: url(images/table_top.jpg) repeat-x; text-align: left;\">&nbsp;</td>\r\n");
      out.write("          </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td width=\"68\" height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; \"><input type=\"checkbox\" value=\"checkbox\"></td>\r\n");
      out.write("          <td width=\"87\" height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; text-align: left;\">机器猫</td>\r\n");
      out.write("          <td width=\"172\" height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; border-left: 1px solid #c6c6c6;\">Arek_kajda@gmail.com</td>\r\n");
      out.write("          <td width=\"157\" style=\"border-bottom: 1px solid #c6c6c6; border-left: 1px solid #c6c6c6; \">\r\n");
      out.write("          \t<img class=\"showbox\" src=\"images/speaker_se.jpg\" />\r\n");
      out.write("\r\n");
      out.write("\t<div id=\"TB_overlayBG\" class=\"close\"></div>\r\n");
      out.write("\t<div class=\"box\" style=\"display:none\">\r\n");
      out.write("    \r\n");
      out.write("    <img src=\"images/music.gif\" />\r\n");
      out.write("\t</div>\r\n");
      out.write("          \r\n");
      out.write("          \r\n");
      out.write("          \r\n");
      out.write("          \r\n");
      out.write("          </td>\r\n");
      out.write("          <td width=\"226\" height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; text-align: left;  \">\r\n");
      out.write("          \t<img class=\"popbox-link\" src=\"images/Video.jpg\" />\r\n");
      out.write("            <div class=\"close-btn\" id=\"screen\"></div>\r\n");
      out.write("<div class=\"popbox\">\r\n");
      out.write("<img src=\"images/voide.gif\" />\r\n");
      out.write("</div>\r\n");
      out.write("            \r\n");
      out.write("          </td>\r\n");
      out.write("          <td width=\"268\" style=\"border-bottom: 1px solid #c6c6c6; text-align: left; padding-left: 20px;\"></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td width=\"68\" height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; \"><input type=\"checkbox\" value=\"checkbox\"></td>\r\n");
      out.write("          <td width=\"87\" height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; text-align: left;\">机器猫</td>\r\n");
      out.write("          <td width=\"172\" height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; border-left: 1px solid #c6c6c6;\">Arek_kajda@gmail.com</td>\r\n");
      out.write("          <td style=\"border-bottom: 1px solid #c6c6c6; border-left: 1px solid #c6c6c6;\">&nbsp;</td>\r\n");
      out.write("          <td height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; \">&nbsp;</td>\r\n");
      out.write("          <td height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; \">&nbsp;</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td width=\"68\" height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; \"><input type=\"checkbox\" value=\"checkbox\"></td>\r\n");
      out.write("          <td width=\"87\" height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; text-align: left;\">机器猫</td>\r\n");
      out.write("          <td width=\"172\" height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; border-left: 1px solid #c6c6c6;\">Arek_kajda@gmail.com</td>\r\n");
      out.write("          <td style=\"border-bottom: 1px solid #c6c6c6; border-left: 1px solid #c6c6c6;\">&nbsp;</td>\r\n");
      out.write("          <td height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; \">&nbsp;</td>\r\n");
      out.write("          <td height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; \">&nbsp;</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td width=\"68\" height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; \"><input type=\"checkbox\" value=\"checkbox\"></td>\r\n");
      out.write("          <td width=\"87\" height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; text-align: left;\">机器猫</td>\r\n");
      out.write("          <td width=\"172\" height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; border-left: 1px solid #c6c6c6;\">Arek_kajda@gmail.com</td>\r\n");
      out.write("          <td style=\"border-bottom: 1px solid #c6c6c6; border-left: 1px solid #c6c6c6;\">&nbsp;</td>\r\n");
      out.write("          <td height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; \">&nbsp;</td>\r\n");
      out.write("          <td height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; \">&nbsp;</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td width=\"68\" height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; \"><input type=\"checkbox\" value=\"checkbox\"></td>\r\n");
      out.write("          <td width=\"87\" height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; text-align: left;\">机器猫</td>\r\n");
      out.write("          <td width=\"172\" height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; border-left: 1px solid #c6c6c6;\">Arek_kajda@gmail.com</td>\r\n");
      out.write("          <td style=\"border-bottom: 1px solid #c6c6c6; border-left: 1px solid #c6c6c6;\">&nbsp;</td>\r\n");
      out.write("          <td height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; \">&nbsp;</td>\r\n");
      out.write("          <td height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; \">&nbsp;</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td width=\"68\" height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; \"><input type=\"checkbox\" value=\"checkbox\"></td>\r\n");
      out.write("          <td width=\"87\" height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; text-align: left;\">机器猫</td>\r\n");
      out.write("          <td width=\"172\" height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; border-left: 1px solid #c6c6c6;\">Arek_kajda@gmail.com</td>\r\n");
      out.write("          <td style=\"border-bottom: 1px solid #c6c6c6; border-left: 1px solid #c6c6c6;\">&nbsp;</td>\r\n");
      out.write("          <td height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; \">&nbsp;</td>\r\n");
      out.write("          <td height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; \">&nbsp;</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td width=\"68\" height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; \"><input type=\"checkbox\" value=\"checkbox\"></td>\r\n");
      out.write("          <td width=\"87\" height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; text-align: left;\">机器猫</td>\r\n");
      out.write("          <td width=\"172\" height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; border-left: 1px solid #c6c6c6;\">Arek_kajda@gmail.com</td>\r\n");
      out.write("          <td style=\"border-bottom: 1px solid #c6c6c6; border-left: 1px solid #c6c6c6;\">&nbsp;</td>\r\n");
      out.write("          <td height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; \">&nbsp;</td>\r\n");
      out.write("          <td height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; \">&nbsp;</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td width=\"68\" height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; \"><input type=\"checkbox\" value=\"checkbox\"></td>\r\n");
      out.write("          <td width=\"87\" height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; text-align: left;\">机器猫</td>\r\n");
      out.write("          <td width=\"172\" height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; border-left: 1px solid #c6c6c6;\">Arek_kajda@gmail.com</td>\r\n");
      out.write("          <td style=\"border-bottom: 1px solid #c6c6c6; border-left: 1px solid #c6c6c6;\">&nbsp;</td>\r\n");
      out.write("          <td height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; \">&nbsp;</td>\r\n");
      out.write("          <td height=\"45\" style=\"border-bottom: 1px solid #c6c6c6; \">&nbsp;</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("      </table>\r\n");
      out.write("            <div class=\"page_list\">\r\n");
      out.write("            \t<ul>\r\n");
      out.write("                \t<li><a href=\"#\"><img src=\"images/page_down.jpg\" /></a></li>\r\n");
      out.write("                \t<li><a href=\"#\">4</a></li>\r\n");
      out.write("                \t<li><a href=\"#\">3</a></li>\r\n");
      out.write("                \t<li><a href=\"#\">...</a></li>\r\n");
      out.write("                \t<li><a href=\"#\">2</a></li>\r\n");
      out.write("                \t<li><a href=\"#\">1</a></li>\r\n");
      out.write("                \t<li><a href=\"#\"><img src=\"images/page_up.jpg\" /></a></li>\r\n");
      out.write("                </ul>\r\n");
      out.write("            </div>\r\n");
      out.write("    </div>\r\n");
      out.write("  </div>\r\n");
      out.write("</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}