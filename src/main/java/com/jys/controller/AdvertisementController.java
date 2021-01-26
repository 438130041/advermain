package com.jys.controller;

import com.alibaba.fastjson.JSONObject;
import com.jys.pojo.Advertisement;
import com.jys.service.AdvertisementService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@RestController
public class AdvertisementController {
    @Autowired
    private AdvertisementService advertisementService;
    @RequestMapping("adverlist")
    public ModelAndView adverlistship(ModelAndView mv){
//        List<Advertisement> advertisementList=advertisementService.listAll();
//        mv.addObject("advertisementList",advertisementList);
        mv.setViewName("adver/advermain");
        return mv;
    }

    @RequestMapping("/addle")
    public ModelAndView saddling(ModelAndView mv){
        mv.setViewName("adver/adder");
        return mv;
    }

    @RequestMapping("/adver_find")
    public JSONObject adver_findrnd(HttpServletRequest request){

        Map<String,Object> userMap=new HashMap<String, Object>();
        // 当前页数
        int page = Integer.parseInt(request.getParameter("page"));
        // 显示条数
        int   limit = Integer.parseInt(request.getParameter("limit"));
        //作为sql语句的限制条件
        int start = limit * (page - 1);
        // 查询出数据的总条数
        int counts = advertisementService.aaacounts();
        List<Advertisement> products = advertisementService.aaagetProducta(start, limit);
        userMap.put("code", 0);
        userMap.put("msg", "lay ui-Table数据绑定数据库");
        userMap.put("count", counts);
        userMap.put("data", products);
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(userMap));
        return jsonObject;
    }

    @RequestMapping("delfor")
    public Map<String,Object> fdelfor(String id){
        Map<String,Object> map=new HashMap<String, Object>();
        log.info("id:"+id);
        advertisementService.deleteById(id);
        map.put("type","yes");
        return map;
    }


    @RequestMapping("/updates")
    public ModelAndView updateg(String id,ModelAndView mv){
        log.info("id:"+id);
        Advertisement advertisement=advertisementService.findById(id);
        log.info("advertisement："+advertisement);
        mv.addObject("advertisement",advertisement);
        mv.setViewName("adver/updateinfo");
        return mv;
    }

    @RequestMapping("updatesubs")
    public Map<String,Object> fupdatesubst(@RequestParam(value = "searchParams",required = false) String searchParams,String demo){
        log.info("searchParams:"+searchParams);
        Map<String,Object> map=new HashMap<String, Object>();
        log.info("searchParams-----"+searchParams);
        Advertisement advertisement = JSONObject.parseObject(searchParams, Advertisement.class);
        log.info("advertisement----"+advertisement);


        Date date = new Date();
        String imgurl="images"+advertisement.getAdverimgurl();
        Advertisement advertisement1=new Advertisement();

        advertisement1.setId(advertisement.getId());
        advertisement1.setAdvername(advertisement.getAdvername());
        advertisement1.setAdvercategory(advertisement.getAdvercategory());
        advertisement1.setAdverphoto(advertisement.getAdverphoto());
        advertisement1.setAdverlink(advertisement.getAdverlink());
        advertisement1.setAdvertanchuang(advertisement.getAdvertanchuang());
        advertisement1.setCreatetime(date);
        advertisement1.setWordshow(advertisement.getWordshow());
        advertisement1.setAdverimgurl(imgurl);
        advertisement1.setAdvertext(demo);
        advertisement1.setIslink(advertisement.getIslink());
           advertisementService.update(advertisement1);





        map.put("type","yes");
        return map;
    }


    @RequestMapping("addsubmit")
    public Map<String,Object> faddsubmit(@RequestParam(value = "searchParams",required = false) String searchParams,String demo){
           log.info("searchParams:"+searchParams);
        Map<String,Object> map=new HashMap<String, Object>();
        log.info("searchParams-----"+searchParams);
        Advertisement advertisement = JSONObject.parseObject(searchParams, Advertisement.class);
        log.info("advertisement----"+advertisement);

        UUID uuid = UUID.randomUUID();
        String guid = uuid.toString().substring(0, 8);
        Date date = new Date();
        String imgurl="images"+advertisement.getAdverimgurl();
        Advertisement advertisement1=new Advertisement();
            advertisement1.setId(guid);
            advertisement1.setAdvername(advertisement.getAdvername());
            advertisement1.setAdvercategory(advertisement.getAdvercategory());
            advertisement1.setAdverphoto(advertisement.getAdverphoto());
            advertisement1.setAdverlink(advertisement.getAdverlink());
            advertisement1.setAdvertanchuang(advertisement.getAdvertanchuang());

            advertisement1.setWordshow(advertisement.getWordshow());

            advertisement1.setCreatetime(date);
            advertisement1.setAdverclick(1);
            advertisement1.setAdverimgurl(imgurl);
            advertisement1.setAdvertext(demo);
            advertisement1.setShowpage(advertisement.getShowpage());
            advertisement1.setIslink(advertisement.getIslink());

            advertisementService.insert(advertisement1);
        map.put("type","yes");
        return map;
    }


//    流程：图片先自己上传到后端
//——》后端存储图片，并把存储地址给前端
//——》前端的js代码获取到后端传来的图片存储地址，把这个url赋值给表单中的隐藏的图片输入项
//——》图片输入框中有了地址，并随着表单其他内容一起提交~
    //图片上传测试
    @RequestMapping("upload")
    public Map<String,Object> upload(MultipartFile file, HttpServletRequest request){

        String prefix="";
        String dateStr="";
        //保存上传
        OutputStream out = null;
        InputStream fileInput=null;
        try{
            if(file!=null){
                String originalName = file.getOriginalFilename();

                log.info("originalName:"+originalName);
                prefix=originalName.substring(originalName.lastIndexOf(".")+1);
                log.info("prefix:"+prefix);
                Date date = new Date();
                String uuid = UUID.randomUUID().toString().substring(0,8)+"";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateStr = simpleDateFormat.format(date);
                //String filepath = "D:\\shop\\wxshop\\src\\main\\webapp\\images\\" + dateStr+"\\"+uuid+"." + prefix;
                String filepath =  request.getSession().getServletContext().getRealPath("/")+ "/"+"images"+"/"+uuid+"." + prefix;

                File files=new File(filepath);
                log.info("files:------"+files);
                //打印查看上传路径
                System.out.println("filepath:"+filepath);
                if(!files.getParentFile().exists()){
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
                Map<String,Object> map2=new HashMap<>();
                Map<String,Object> map=new HashMap<>();
                map.put("code",0);
                map.put("msg","");
                map.put("data",map2);
                map2.put("src","/"+uuid+"." + prefix);
                log.info("map2:"+map2);
                return map;
            }

        }catch (Exception e){
        }finally{
            try {
                if(out!=null){
                    out.close();
                }
                if(fileInput!=null){
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("code",1);
        map.put("msg","");
        return map;

    }


    @RequestMapping("delforit")
    public Map<String,Object> delforitr(String id){
        Map<String,Object> map=new HashMap<String, Object>();
        log.info("id:"+id);
        advertisementService.deleteById(id);
        map.put("type","yes");
        return map;
    }


    //前端页面代码
    @RequestMapping("advershow")
    public ModelAndView adverfirstit(ModelAndView mv){

        SimpleDateFormat d = new SimpleDateFormat("HH:mm:ss");// 格式化时间

        String nowtime = d.format(new Date());// 按以上格式 将当前时间转换成字符串

        System.out.println("当前时间：" + nowtime);

        String testtime = "20:50:35";// 测试时间

        System.out.println("测试时间：" + testtime);

        try {
            long result = (d.parse(testtime).getTime() - d.parse(nowtime)
                    .getTime())/ 3600000;// 当前时间减去测试时间
            long result2 = (d.parse(testtime).getTime() - d.parse(nowtime)
                    .getTime())/ 60000;
            long result3 = (d.parse(testtime).getTime() - d.parse(nowtime)
                    .getTime())/ 1000;
            // 这个的除以1000得到秒，相应的60000得到分，3600000得到小时
            System.out.println("当前时间减去测试时间=" + result+"小时");
            System.out.println("当前时间减去测试时间=" + result2+"分");
            System.out.println("当前时间减去测试时间=" + result3+"秒");
            if (result<=0&&result2<=0&&result3<=0){
                 log.info("时间已减完");
                String show="主页面";
                Advertisement shows=advertisementService.findByshow(show);
                log.info("主页面数据ID："+shows.getId());
                mv.addObject("id",shows.getId());

            }
            else {
                log.info("时间还够");
                String show="主页面";
                Advertisement shows=advertisementService.findByshow(show);
                log.info("主页面数据："+shows);
                mv.addObject("shows",shows);

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


        mv.setViewName("adver/adverpage");
        return mv;
    }

    @RequestMapping("adversecond")
    public ModelAndView adversecondit(ModelAndView mv){
        String show="页面1";
        Advertisement shows=advertisementService.findByshow(show);
        log.info("主页面数据："+show);
        mv.addObject("shows",shows);
        mv.setViewName("adver/adversecond");
        return mv;
    }


    //访问人数统计
    @RequestMapping("advertotal")
    public Map<String,Object> advertotals(Integer total,String id){
        log.info("total:"+total);
        Map<String,Object> map=new HashMap<String, Object>();
        int click=advertisementService.findclick(id);
        log.info("查找到的访问次数："+click);

          Integer totals=click+1;
          Advertisement advertisement=new Advertisement();
          advertisement.setAdverclick(totals);
          advertisement.setId(id);
          advertisementService.updateclick(advertisement);
        map.put("type","ok");
        return map;
    }


    @RequestMapping("advermains")
    public ModelAndView advadvermains(ModelAndView mv,String id){

        log.info("id："+id);
        mv.addObject("id",id);

            Advertisement advertisement=advertisementService.findById(id);
             if (advertisement!=null) {
                 mv.addObject("advertisement", advertisement.getAdvertext());
                 mv.addObject("id", advertisement.getId());
             }
             else {

             }
        mv.setViewName("adver/adverlink");
        return mv;
    }


    @ResponseBody
    @RequestMapping(value = "/uploadFile")
    public String uploadFile(HttpServletRequest request,@Param("file") MultipartFile file) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        String res = sdf.format(new Date());
        //服务器上使用
        // String rootPath =request.getServletContext().getRealPath("/resource/uploads/");//target的目录
        String rootPath =  request.getSession().getServletContext().getRealPath("/")+ "/"+"images"+"/";
        log.info("rootPath:"+rootPath);
        //本地使用
        //String rootPath ="/Users/liuyanzhao/Documents/uploads/";
        //原始名称
        String originalFilename = file.getOriginalFilename();
        //新的文件名称
        String newFileName = res+originalFilename.substring(originalFilename.lastIndexOf("."));
        //创建年月文件夹
        Calendar date = Calendar.getInstance();

        File dateDirs = new File(date.get(Calendar.YEAR)
                + File.separator + (date.get(Calendar.MONTH)+1));
        //新文件
        File newFile = new File(rootPath+File.separator+dateDirs+File.separator+newFileName);
           log.info("newFile:"+newFile);

        //判断目标文件所在的目录是否存在
        if(!newFile.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            newFile.getParentFile().mkdirs();
        }
        System.out.println(newFile);
        //将内存中的数据写入磁盘
        file.transferTo(newFile);
        //完整的url
        String fileUrl =  "/images/"+date.get(Calendar.YEAR)+ "/"+(date.get(Calendar.MONTH)+1)+ "/"+ newFileName;
        log.info("fileUrl:"+fileUrl);

        Map<String,Object> map = new HashMap<String,Object>();
        Map<String,Object> map2 = new HashMap<String,Object>();
        map.put("code",0);//0表示成功，1失败
        map.put("msg","上传成功");//提示消息
        map.put("data",map2);
        map2.put("src",fileUrl);//图片url
        map2.put("title",newFileName);//图片名称，这个会显示在输入框里
        String result = new JSONObject(map).toString();
        return result;
    }


}
