<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.awt.*,java.awt.image.*,java.util.*,javax.imageio.*,java.io.OutputStream,com.cqut.util.SessionUtil" %>
<%
 String sRand="";
 try{
   response.setHeader("Pragma","No-cache");
   response.setHeader("Cache-Control","no-cache");
   response.setDateHeader("Expires", 0);
   int width=110, height=30;
   BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
   OutputStream os=response.getOutputStream();
   Graphics g = image.getGraphics();
   Random random = new Random();
   g.setColor(getRandColor(200,250));
   g.fillRect(0, 0, width, height);

   g.setFont(new Font("Times New Roman",Font.PLAIN,30));
   g.setColor(getRandColor(160,200));
   for (int i=0;i<155;i++){
     int x = random.nextInt(width);
     int y = random.nextInt(height);
     int xl = random.nextInt(12);
     int yl = random.nextInt(12);
     g.drawLine(x,y,x+xl,y+yl);
   }
   String[] str={"0","1","2","3","4","5","6","7","8","9",
    "a","b","c","d","e","f","g","h","i","j",
    "k","l","m","n","p","q","r","s","t"};
    //设置上下波动
    for (int j=0;j<4;j++){
	     int w=0;
	     int x=(j+1)%3;
	     if(x==random.nextInt(7)){
	      w=25-random.nextInt(3);
	  }else{
	     w=25+random.nextInt(3);
	  }
      String rand=String.valueOf(str[random.nextInt(str.length)]);
      sRand+=rand;
      g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
      g.drawString(rand,22*j+15,w);
  }
  for(int i=0;i<15;i++){
	  int x=random.nextInt(width);
	  int y=random.nextInt(height);
	  int x1=random.nextInt(width);
	  int y1=random.nextInt(height);
	  Color color=new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
	  //随机画各种颜色线
	  g.setColor(color);
	  g.drawLine(x, y, x1, y1);
  } 
   session.setAttribute("rand",sRand);
   g.dispose();

   ImageIO.write(image, "JPEG",os);
   os.flush();
   os.close();
   os=null;
   response.flushBuffer();
   out.clear();
   out = pageContext.pushBody();
 }catch(IllegalStateException e){
   System.out.println(e.getMessage());
   e.printStackTrace();
 }
%>
<%!
 Color getRandColor(int fc,int bc){
   Random random = new Random();
   if(fc>255) fc=255;
   if(bc>255) bc=255;
   int r=fc+random.nextInt(bc-fc);
   int g=fc+random.nextInt(bc-fc);
   int b=fc+random.nextInt(bc-fc);
   return new Color(r,g,b);
 }
%>