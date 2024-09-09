package cn.lb.gateway.exception;

import cn.dev33.satoken.exception.SaTokenException;
import cn.lb.gateway.entity.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 网关异常处理器
 *
 * @author RainSoul
 * @create 2024-09-09
 */
// 定义一个全局异常处理器类，用于处理网关级别的异常
@Component
public class GatewayExceptionHandler implements ErrorWebExceptionHandler {

    // 初始化一个对象映射器实例，用于将Java对象转换为JSON格式
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 实现ErrorWebExceptionHandler的handle方法，用于处理不可预料的错误
     * 当发生异常时，该方法负责生成适当的HTTP响应
     *
     * @param exchange 当前的服务器Web交换对象，包含请求和响应
     * @param ex       异常对象，表示发生的错误
     * @return 返回一个Mono<Void>类型的响应，表示异步处理的结果
     */
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex){
        // 获取当前的请求对象
        ServerHttpRequest request = exchange.getRequest();
        // 获取当前的响应对象
        ServerHttpResponse response = exchange.getResponse();

        // 初始化HTTP响应状态码，默认为200
        Integer code = 200;

        // 初始化错误信息，默认为空
        String message = "";

        // 检查异常类型，并设置适当的响应码和消息
        if (ex instanceof SaTokenException) {
            // 如果是SaTokenException，表示权限问题，设置响应码为401，消息为"用户无权限"
            code = 401;
            message = "用户无权限";
            ex.printStackTrace();
        } else {
            // 其他类型的异常，设置响应码为500，消息为"系统异常"
            code = 500;
            message = "系统异常";
            ex.printStackTrace();
        }

        // 创建一个Result对象，表示处理结果，这里使用自定义的Result类
        Result result = Result.fail(code, message);

        // 设置响应的内容类型为JSON
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        // 将Result对象转换为JSON格式，并写入响应体
        return response.writeWith(Mono.fromSupplier(() -> {
            // 获取数据缓冲区工厂
            DataBufferFactory dataBufferFactory = response.bufferFactory();
            // 将Result对象转换为字节数组
            byte[] bytes = null;
            try {
                bytes = objectMapper.writeValueAsBytes(result);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            // 使用工厂创建数据缓冲区，并包装字节数组
            return dataBufferFactory.wrap(bytes);
        }));
    }
}
