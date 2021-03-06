package com.ustcinfo.rpc.protocol;

import java.util.ArrayList;
import java.util.List;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;

public class KryoUtils {
	@SuppressWarnings("rawtypes")
	private static final List<Class> classList = new ArrayList<Class>();
	@SuppressWarnings("rawtypes")
	private static final List<Serializer> serializerList = new ArrayList<Serializer>();
	private static final List<Integer> idList = new ArrayList<Integer>();
	
	private static final ThreadLocal<Kryo> kryos = new ThreadLocal<Kryo>() {
		protected Kryo initialValue() {
			Kryo kryo = new Kryo();
			int size = idList.size();
			for (int i = 0; i < size; i++) {
				kryo.register(classList.get(i), serializerList.get(i), idList.get(i));
			}
			kryo.setRegistrationRequired(true);
			kryo.setReferences(false);
			return kryo;
		}
	};

	private KryoUtils() {
	}

	public static Kryo getKryo() {
		return kryos.get();
	}
}
