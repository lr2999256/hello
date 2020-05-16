# 名词解析

- SSL：Secure Socket Layer，安全套接字层，它位于TCP层与Application层之间。提供对Application数据的加密保护（密文），完整性保护（不被篡改）等安全服务。
- TLS：Transport Layer Secure，提供安全的传输服务，十分灵活，可以使用在TCP上，也可以使用在UDP上，也可以使用在数据链路层上。
- 公钥：大家公用的，可以被其他人下载，用来加密和验章。
- 私钥：自己私有，须小心保存，用来解密和签章。
- 数字签名：将报文按双方约定的HASH算法计算得到一个固定位数的报文摘要。在数学上保证：只要改动报文中任何一位，重新计算出的报文摘要值就会与原先的值不相符。这样就保证了报文的不可更改性。将该报文摘要值用发送者的私人密钥加密，然后连同原报文一起发送给接收者，而产生的报文即称数字签名。
- 数字证书：数字证书就是互联网通讯中标志通讯各方身份信息的一系列数据，提供了一种在Internet上验证您身份的方式，其作用类似于司机的驾驶执照或日常生活中的身份证。它是由一个由权威机构-----CA机构，又称为证书授权（Certificate Authority）中心发行的，人们可以在网上用它来识别对方的身份。数字证书是一个经证书授权中心数字签名的包含公开密钥拥有者信息以及公开密钥的文件。最简单的证书包含一个公开密钥、名称以及证书授权中心的数字签名。
- CA：Certificate Authority，证书授权中心。是一个单位，来管理发放数字证书的。由它发放的证书就叫 CA 证书，以区别于个人使用工具随意生成的数字证书，查看 CA 证书，里面有两项重要内容，一个是颂发给谁，另一个是由谁颂发的。

SSL/TLS协议的基本思路是采用公钥加密法，也就是说，客户端先向服务器端索要公钥，然后用公钥加密信息，服务器收到密文后，用自己的私钥解密。

# 认证过程

## 单向认证

只需要验证SSL服务器身份，不需要验证SSL客户端身份。



![img](https:////upload-images.jianshu.io/upload_images/6129988-e6ff0255e3de09ce.png?imageMogr2/auto-orient/strip|imageView2/2/w/634/format/webp)

单向认证.png

## 双向认证

要求服务器和客户端双方都有证书，客户端需要校验服务端，服务端也需要校验客户端。



![img](https:////upload-images.jianshu.io/upload_images/6129988-a0600b3cc58eb821.png?imageMogr2/auto-orient/strip|imageView2/2/w/628/format/webp)

双向认证.png

# 自制证书

## CA证书

### 创建私钥

输入以下命令

```shell
openssl genrsa -out ca-key.pem 1024
```

### 创建csr证书请求

​	输入以下命令创建csr证书，如果没有-subj参数，则需要手动输入需要的参数，此时可以看见相应的提示。
 -subj 中参数的含义：

| 参数 |     意义     |
| :--: | :----------: |
|  C   |     国家     |
|  ST  |      州      |
|  L   |   本地名称   |
|  O   |   组织名称   |
|  OU  | 组织单元名称 |
|  CN  |   命令名称   |

```shell
openssl req -new -key ca-key.pem -out ca-req.csr -subj "/C=CN/ST=BJ/L=BJ/O=fish/OU=fish/CN=CA"
```

### ~~除去密码~~

​	~~在加载SSL支持的Nginx并使用上述私钥时必须除去口令，否则会在启动nginx的时候输入密码。去除口令，在命令行中执行此命令：  openssl rsa -in ca-key.pem -out ca.key~~
​	以上这一段是因为如果生成私钥的时候加入-des3参数的时候会有密码输入的过程，而这个密码会在重启nginx的时候需要输入

### 生成crt证书

​	执行以下命令生成CA证书，生成CA证书需要中间产物`ca-req.csr`，这里的in就是输入的文件，out为输出的产物，signkey就是一开始生成的私钥信息。最终输出一个名为ca-cert.pem的CA证书。

```css
openssl x509 -req -in ca-req.csr -out ca-cert.pem -signkey ca-key.pem -days 3650
```

## 服务器端证书

### 创建服务器端私钥

输入以下命令

```shell
openssl genrsa -out server-key.pem 1024
```

### 创建csr证书

输入以下命令创建csr证书，其中-subj参数与CA证书的csr命令含义相同。

```shell
openssl req -new -out server-req.csr -key server-key.pem -subj "/C=CN/ST=BJ/L=BJ/O=fish/OU=fish/CN=*.fish-test.com"
```

**其中输入的信息中最重要的为 CN，这里输入的域名即为我们要使用https访问的域名。同时也可以使用泛域名如\*.webkaka.com来生成所有二级域名可用的网站证书。**

### 生成crt证书

执行以下命令生成服务器端证书，这里的输入为刚刚生成的server-req.csr，签名使用的是服务端刚生成的server-key的私钥文件，-CA表明用的CA证书，也就是一开始生成的CA证书，-CAkey表示CA的私钥，最终输出一个server-cert.pem的服务端证书。

```css
openssl x509 -req -in server-req.csr -out server-cert.pem -signkey server-key.pem -CA ca-cert.pem -CAkey ca-key.pem -CAcreateserial -days 3650
```

~~用CA进行签名~~：
 ~~openssl ca -policy policy_anything -days 1460 -cert ca.crt -keyfile ca.key -in [www.webkaka.com.csr](https://link.jianshu.com?t=http%3A%2F%2Fwww.webkaka.com.csr) -out [www.webkaka.com.crt](https://link.jianshu.com?t=http%3A%2F%2Fwww.webkaka.com.crt)~~

### 确认证书

```css
openssl verify -CAfile ca-cert.pem  server-cert.pem
```

有了以上的文件之后就可以配置单向认证访问了，如果需要双向认证，还需要以下的操作。

## 客户端证书端证书

### 创建客户端私钥

输入以下命令

```shell
openssl genrsa -out client-key.pem 1024
```

### 创建csr证书

输入以下命令创建csr证书，其中-subj参数与CA证书的csr命令含义相同。

```shell
openssl req -new -out client-req.csr -key client-key.pem -subj "/C=CN/ST=BJ/L=BJ/O=fish/OU=fish/CN=dong"
```

这里的CN可以是客户端的IP信息

### 生成crt证书

执行以下命令生成客户端证书,和服务端一样的命令，就不解释了。

```css
openssl x509 -req -in client-req.csr -out client-cert.pem -signkey client-key.pem -CA ca-cert.pem -CAkey ca-key.pem -CAcreateserial -days 3650
```

### 确认证书

```css
openssl verify -CAfile ca-cert.pem client-cert.pem
```

## Nginx配置

### 单向访问

打开nginx.conf配置文，修改你需要的地方，将配置文改为以下形式：

```nginx
    server {
        listen       443 ssl;
        server_name  _;

        # 刚刚生成的服务器端公钥和私钥文件
        ssl_certificate     /etc/nginx/ssl/server-cert.pem;
        ssl_certificate_key /etc/nginx/ssl/server-key.pem;

        # 据官方文档所述，cache中的1m可以存放4000个session。
        ssl_session_cache    shared:SSL:1m;
        ssl_session_timeout  5m;

        ssl_ciphers  HIGH:!aNULL:!MD5;
        ssl_prefer_server_ciphers  on;
        ssl_protocols TLSv1 TLSv1.1 TLSv1.2;
    }
```

然后使用以下命令检测配置和重新加载nginx：

```shell
nginx -t
nginx -s reload
```

或者直接重启服务器，访问网址[https://www.test.com](https://link.jianshu.com?t=https%3A%2F%2Fwww.test.com)就会发现证书认证已经起作用了，其中[www.test.com](https://link.jianshu.com?t=http%3A%2F%2Fwww.test.com)就是发行证书的时候填写的域名，[www.test.com](https://link.jianshu.com?t=http%3A%2F%2Fwww.test.com)也需要能够解析才可以。
 此时的https并不被浏览器信任，如果需要信任证书，则只需要将公钥文件进行导入。

### 添加重定向

pass

### 双向访问

打开nginx.conf配置文，修改你需要的地方，将配置文改为以下形式：

```nginx
    server {
        listen       443 ssl;
        server_name  _;

        ssl_certificate     /etc/nginx/ssl/server-cert.pem;
        ssl_certificate_key /etc/nginx/ssl/server-key.pem;

        ssl_verify_client on;
        #ssl_verify_depth 2;
        ssl_client_certificate /etc/nginx/ssl/ca-cert.pem;
     
        ssl_session_cache    shared:SSL:1m;
        ssl_session_timeout  5m;

        ssl_ciphers  HIGH:!aNULL:!MD5;
        ssl_prefer_server_ciphers  on;
        ssl_protocols TLSv1 TLSv1.1 TLSv1.2;
    }
```

然后重启服务器，访问网址[https://www.test.com](https://link.jianshu.com?t=https%3A%2F%2Fwww.test.com)就会发现依旧没有办法访问，此时需要导入客户端的证书文件，就可以进行访问了，实现了https的双向访问。

## 制作IOS证书

### 制作IOS服务端证书

​	重命名之前服务端证书`server-cert.crt`重命名为`server-cert.crt`，双击，一直下一步就会导入到浏览器中，然后打开IE浏览器，将证书导出为DER的二进制的CER文件即可

### 制作IOS客户端证书

​	在linux上执行命令，client-key.pem为之前生成的客户端私钥，client-cert.pem为之前生成的客户端证书，最终输出client.p12文件。

```shell
openssl pkcs12 -export -inkey client-key.pem -in client-cert.pem -out client.p12
```

​	然后根据提示输入密码即可生成。

## 制作Android证书

1、要生成bks证书，需要bcprov-jdk15on-1.64.jar（走阿里云的中央仓库下载）。且将该文件放${JAVA_HOME}\jre\lib\ext目录下。${JAVA_HOME}\jre\lib\security\java.security在最后加入一行

```properties
security.provider.x=org.bouncycastle.jce.provider.BouncyCastleProvider
```

2、需要后端提供了自签名的服务器证书server.crt，我们需要把这个server.crt转换成Android系统的bks格式证书。使用以下命令行：

```shell
keytool -importcert -trustcacerts -keystore client.bks -file client.cer -storetype BKS -provider org.bouncycastle.jce.provider.BouncyCastleProvider
keytool -importcert -trustcacerts -keystore server.bks -file server.cer -storetype BKS -provider org.bouncycastle.jce.provider.BouncyCastleProvider
```

3,、按照提示重复输入两次密码（在Java的KeyStore对象加载证书时会用到这个密码。），然后就成功将E:\目录下的server.crt转成key.bks证书。