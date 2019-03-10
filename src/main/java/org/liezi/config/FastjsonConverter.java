package org.liezi.config;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @Author: Lake
 * @Date: 2018/8/02 11:01
 * @Description:fastjson的转换配置
 */
@Configuration
public class FastjsonConverter {

	@Bean
	public HttpMessageConverters customConverters() {
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		List<MediaType> fastMediaTypes = new ArrayList<>();
		fastJsonConfig.setCharset(Charset.forName("UTF-8"));
		fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		fastConverter.setSupportedMediaTypes(fastMediaTypes);
		fastConverter.setFastJsonConfig(fastJsonConfig);
		StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
		stringConverter.setDefaultCharset(Charset.forName("UTF-8"));
		stringConverter.setSupportedMediaTypes(fastMediaTypes);
		return new HttpMessageConverters(stringConverter,fastConverter);
	}
}