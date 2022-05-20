## API基本信息

* 接口的baseUrl: https://api.freemex.com
* 所有接口的响应格式均为JSON格式
* 所有时间戳均为UNIX时间，单位为毫秒

## HTTP返回码

* HTTP 4XX 错误码用于指示错误的请求内容、行为、格式。
* HTTP 403 错误码表示违反WAF限制（Web应用程序防火墙）。
* HTTP 429 错误码表示警告访问频次超限，即将被封IP。
* HTTP 5XX 错误码用于指示Freemex服务侧的问题。

## 接口的基本信息

* GET 方法的接口，参数必须在query string中发送。
* POST 方法的接口，除规定的公共参数需在query string中发送外，接口文档中定义的参数必须使用json格式发送