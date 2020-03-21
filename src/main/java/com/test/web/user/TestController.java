package com.test.web.user;

import com.alibaba.fastjson.JSON;
import com.test.persistence.beans.TTestUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author : Rui
 * @date : 2019/4/24 16:53
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("t1")
    public String test1(@Param("abc") String abc, @Param("bcd") String bcd) {
        return abc;
    }

    @PostMapping("t2")
    public String test2(@Param("abc") String abc, @Param("bcd") String bcd) {
        return "abc" + bcd;
    }

    @PostMapping("t3")
    public String test3(@RequestBody() TTestUser tTestUser) {
        return JSON.toJSONString(tTestUser);
    }

    @PostMapping("t4")
    public String test4(@RequestParam("file") MultipartFile filecontent) throws IOException {
        OutputStream os = null;
        InputStream inputStream = null;
        String fileName = null;
        try {
            inputStream = filecontent.getInputStream();
            fileName = filecontent.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            String path = "D:\\test\\";
            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件
            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath() + File.separator + fileName);
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "111";
    }
}
