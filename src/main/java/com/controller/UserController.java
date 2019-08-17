package com.controller;

import com.google.gson.Gson;
import com.pojo.User1;
import com.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class UserController {
    @Resource
    UserService userService;

    @RequestMapping(value = "/getImg")
    public void getImg(HttpServletRequest request, HttpServletResponse response, String r1) {
        // 1、创建一个内存图片流对象
        BufferedImage img = new BufferedImage(84, 40,
                BufferedImage.TYPE_INT_RGB);
        // 2、获取图片流的画布对象
        Graphics2D gd = img.createGraphics();
        gd.setFont(new Font("宋体", Font.BOLD, 30));

        // /////生成一个4位的随机数字
        Integer r = new Integer(r1);
        // ///////////////////

        // 3、往画布对象里面加入内容
        gd.drawString(r + "", 6, 30);

        //4、把随机数加入到session里面
        request.getSession().setAttribute("yzm", r);

        // 5、把图片流输出到客户端浏览器
        try {
            ImageIO.write(img, "png", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/checkUserLogin")
    public void checkUserLogin(User1 user1,HttpServletResponse response){
        Gson gson = new Gson();
        if (!userService.checkUserLogin(user1)){
            try {
                response.getWriter().print(gson.toJson("false"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                response.getWriter().print(gson.toJson("true"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
