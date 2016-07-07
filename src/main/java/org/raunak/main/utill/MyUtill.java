package org.raunak.main.utill;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.raunak.main.core.Exceptions.MyCustomExceptions;
import org.raunak.main.core.enums.MyErrorMessagesEnum;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class MyUtill {
	private static Logger logger = Logger.getLogger(MyUtill.class);

	public static String getJsonFromObject(Object obj, Set<String> propertiesToIgnore) throws JsonProcessingException {
		@JsonFilter("by name")
		abstract class IgnoreStringsMixIn {
		}
		String requestPayload = null;
		if (!MyStringUtill.isNull(obj)) {
			ObjectMapper mapper = new ObjectMapper();
			if (!MyUtill.isEmptyCollection(propertiesToIgnore)) {
				mapper.addMixInAnnotations(Object.class, IgnoreStringsMixIn.class);
				mapper.setSerializationInclusion(Include.NON_NULL);
				ObjectWriter writer = mapper.writer(new SimpleFilterProvider().addFilter("by name",
						SimpleBeanPropertyFilter.serializeAllExcept(propertiesToIgnore)));
				requestPayload = writer.writeValueAsString(obj);
			} else {
				requestPayload = mapper.writeValueAsString(obj);
			}

		}
		return requestPayload;
	}

	public static <T> List<T> getList(String json, Class<T> clazz) {
	    TypeFactory typeFactory = TypeFactory.defaultInstance();
	    List<T> returnObj = null;
	    try {
	    	ObjectMapper objectMapper = new ObjectMapper();
	        returnObj = objectMapper.readValue(json, typeFactory.constructCollectionType(List.class, clazz));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return returnObj;
	}
	
	public static <T> T getObjectFromJson(String json, Class<T> className) throws IOException {
		if (MyStringUtill.isNull(className) || MyStringUtill.isNull(json)) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		T object = null;
		try {
			object = mapper.readValue(json, className);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		return object;
	}

	public static boolean isEmptyCollection(Collection<?> collection) {
		boolean isEmpty = false;

		if (collection == null)
			isEmpty = true;
		else {
			if (collection.isEmpty())
				isEmpty = true;
		}
		return isEmpty;
	}

	public static boolean isEmptyMap(Map<?, ?> map) {
		boolean isEmpty = false;

		if (map == null)
			isEmpty = true;
		else {
			if (map.isEmpty())
				isEmpty = true;
		}
		return isEmpty;
	}

	public static Object invokeURL(String url, Object requestObject, Class responsePojo, HttpHeaders headers,
			HttpMethod httpMethod) throws MyCustomExceptions {
		Object responseObj = null;
		ResponseEntity<Object> responseEntity = null;
		logger.info("In invokeURL()");
		try {
			responseEntity= new RestTemplate().exchange(url, httpMethod,
					new HttpEntity<Object>(requestObject, null), responsePojo);
			logger.info("Response Entity is :" + responseEntity);
			if (responseEntity != null) {
				responseObj = responseEntity.getBody();
			}
		} catch (ResourceAccessException ex) {
			logger.error("Exception Occured : ", ex);
			throw new MyCustomExceptions(MyErrorMessagesEnum.FAILURE_CODE.getValue(),
					MyErrorMessagesEnum.FAILURE_MESSAGE.getValue());
		} catch (HttpClientErrorException ex) {
			logger.error("Exception Occured : ", ex);
			throw new MyCustomExceptions(MyErrorMessagesEnum.FAILURE_CODE.getValue(),
					MyErrorMessagesEnum.FAILURE_MESSAGE.getValue());
		} catch (Exception e) {
			logger.error("Exception Occured : ", e);
			throw new MyCustomExceptions(MyErrorMessagesEnum.FAILURE_CODE.getValue(),
					MyErrorMessagesEnum.FAILURE_MESSAGE.getValue());
		}

		logger.info("Response body of the Response Entity is: " + responseObj);
		logger.info("Out invokeURL()");
		return responseObj;
	}

	public static void closeWebSocketSession(String message, WebSocketSession webSocketSession) throws MyCustomExceptions {
		if (MyStringUtill.isNullOrEmpty(message)) {
			throw new MyCustomExceptions(MyErrorMessagesEnum.FAILURE_CODE.getValue(),
					MyErrorMessagesEnum.FAILURE_MESSAGE.getValue());
		}
		try {
			webSocketSession.sendMessage(new TextMessage(message.getBytes()));
			webSocketSession.close();
		} catch (IOException e) {
			throw new MyCustomExceptions(MyErrorMessagesEnum.FAILURE_CODE.getValue(),
					MyErrorMessagesEnum.FAILURE_MESSAGE.getValue());
		}
	}

	private static char[] letters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
			'q', 'r', 's', 't', 'x', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'A', 'B', 'C', 'D', 'E',
			'F', 'G', 'H', 'J', 'K', 'I', 'O', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'Y', 'U', 'X' };

	public static String createSessionToken() {
		String sessionToken = "";
		for (int i = 0; i < 20; i++) {
			sessionToken += letters[new Double(Math.ceil(Math.random() * letters.length) - 1).intValue()];
		}
		return sessionToken;
	}

	public static boolean isNull(Object obj) {
		boolean isNull = false;
		if (obj == null)
			isNull = true;
		return isNull;
	}

}
