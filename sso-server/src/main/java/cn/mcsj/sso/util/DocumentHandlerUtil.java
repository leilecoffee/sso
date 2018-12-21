package cn.mcsj.sso.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class DocumentHandlerUtil {

	private static Configuration configuration = null;
	private static final Logger logger = LoggerFactory.getLogger(DocumentHandlerUtil.class);

	static {
		configuration = new Configuration(Configuration.VERSION_2_3_27);
		configuration.setDefaultEncoding("utf-8");
	}

	/**
	 * 创建excel
	 * 
	 * @param dataMap
	 * @param type
	 * @return
	 */
	public static void createFile(HttpServletResponse response, Map<?, ?> dataMap, String templateName, String fileName,
			String path) {
		configuration.setClassForTemplateLoading(DocumentHandlerUtil.class, path);
		Template template = null;
		try {
			template = configuration.getTemplate(templateName);
		} catch (Exception e) {
			logger.error("找不到导出模板！，templateName: {}", templateName);
			e.printStackTrace();
		}
		try {
			fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
        // 解决中文文件名乱码关键行 
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName+"\"");
		Writer out = null;
		// 生成Excel
		try {
			out = response.getWriter();
			template.process(dataMap, out);
			out.flush();
			out.close();
			response.flushBuffer();
		} catch (TemplateException | IOException e) {
			logger.error("导出失败！");
			e.printStackTrace();
		}
	}
}
