package com.tangdao.redis;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Component;

import com.tangdao.common.lang.ObjectUtils;

@Component("springSessionDefaultRedisSerializer")
public class RedisObjectSerializer implements RedisSerializer<Object> {

	public RedisObjectSerializer() {
	}

	// 为了方便进行对象与字节数组的转换，所以应该首先准备出两个转换器
	private static final byte[] EMPTY_BYTE_ARRAY = new byte[0]; // 做一个空数组，不是null

	@Override
	public byte[] serialize(Object obj) throws SerializationException {
		if (obj == null) { // 这个时候没有要序列化的对象出现，所以返回的字节数组应该就是一个空数组
			return EMPTY_BYTE_ARRAY;
		}
		// 将对象变为字节数组
		try {
			return ObjectUtils.serializeFst(obj);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			throw new SerializationException(e.getMessage(), e);
		}
	}

	@Override
	public Object deserialize(byte[] data) throws SerializationException {
		if (data == null || data.length == 0) { // 此时没有对象的内容信息
			return null;
		}
		try {
			return ObjectUtils.unserializeFst(data);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			throw new SerializationException(e.getMessage(), e);
		}
	}
}
