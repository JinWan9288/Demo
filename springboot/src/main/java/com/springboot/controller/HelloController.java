package com.springboot.controller;
import com.springboot.entity.User;
import com.springboot.mapper.UserMapper;
import com.springboot.service.Impl.UserServiceImpl;
import com.springboot.service.UserService;
import com.springboot.service.UserServiceSave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
// @RestController 是 @Controller 的子集。
// @GetMapping、@PostMapping、@PutMapping、@DeleteMapping 是 @RequestMapping 的子集。
// @RestController注解表示这是一个RESTful控制器,RESTful 风格的接口,处理HTTP请求并返回JSON格式的响应给客户端。
// RestController就表明可以通过getmapping注解获取数据
// getmapping依赖于RestController

@RequestMapping("/user")
// @RequestMapping("/index") 表示这个控制器处理的所有HTTP请求都是以“http://localhost:8090/index”为前缀的。 "http://localhost:8090/user/"
// @GetMapping注解指定了一个处理HTTP GET请求的方法，直接得到数据库数据在浏览器直接访问，该方法的URL路径是"8090/list"
// @PostMapping注解用来处理HTTP POST请求，用来处理客户端发送的 POST 请求，向数据库插入修改数据，将返回值转换为 JSON 或 XML 格式，方便客户端解析处理。也就是修改增加删除操作
public class HelloController {
    @GetMapping("/test")  // 测试
    public String hello(){
        return "hello 测试 只加一个斜杠test";
    }

//    // @Resource和@Autowired注解都是用来实现 依赖注入 的, 可以允许注入其他类。只是@AutoWried按by type自动注入，而@Resource默认按byName自动注入。
    @Resource
    private UserServiceSave userServiceSave;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/select") // 显示所有数据
    public List<User> select(){
        return userMapper.findAll();
    }

    @PostMapping // 新增 更新都在这个接口里
    public Integer save(@RequestBody User user){ // 把json数据转换成user对象
        return userServiceSave.save(user);
    }
// @RequestBody处理前台的发送的json对象, 转换为user 格式

//    @GetMapping("/list") // 另一种方式显示所有数据
//    public List<User> list(){
//        return userServiceSave.list();
//    }

     @DeleteMapping("/{id}")  // 花括号里的id与 Integer id 是一一对应的
     public Integer delete(@PathVariable Integer id){ // 根据传入的id删除数据
        return userMapper.deleteById(id);
     }
//@PathVariable是路径

    @GetMapping("/page") // 分页查询
//  @RequestParam接受pageNum,pageSize
//  limit 起始页码 , 步长
//  limit的第一个参数 ( pageNum -1 ) * pageSize, 第二个参数是 pageSize
    public Map<String, Object> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam String username){
        pageNum = ( pageNum -1 ) * pageSize;

        username = "%" + username + "%";

        List<User> data = userMapper.selectPage(pageNum,pageSize,username); //

        Integer total = userMapper.countTotal(username);
        Map<String,Object> res = new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;
    }
    @GetMapping("/pageAddr") // 分页查询
//  @RequestParam接受pageNum,pageSize
//  limit 起始页码 , 步长
//  limit的第一个参数 ( pageNum -1 ) * pageSize, 第二个参数是 pageSize
    public Map<String, Object> findPageAddr(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam String address){
        pageNum = ( pageNum -1 ) * pageSize;

        address = "%" + address + "%";

        List<User> dataAd = userMapper.selectPageAddr(pageNum,pageSize,address); //

        Integer total = userMapper.countTotalAddr(address);
        Map<String,Object> resAd = new HashMap<>();
        resAd.put("dataAd",dataAd);
        resAd.put("total",total);
        return resAd;
    }

}